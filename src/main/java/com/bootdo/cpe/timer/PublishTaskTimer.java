package com.bootdo.cpe.timer;

import com.bootdo.activiti.dao.AwardEnterpriseProjectDao;
import com.bootdo.activiti.dao.AwardPublishTaskDao;
import com.bootdo.activiti.domain.PublishAwardTaskDo;
import com.bootdo.activiti.service.AwardPublishTaskService;
import com.bootdo.activiti.service.SpecialistService;
import com.bootdo.common.utils.DateUtils;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.cpe.domain.EnumApplyEnterpriseProStat;
import com.bootdo.cpe.domain.EnumApplyTaskStat;
import com.bootdo.cpe.domain.SpecialistScoreProOverCountInfo;
import com.bootdo.cpe.service.AwardProjectScoreResultService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

import static com.bootdo.common.config.Constant.AWARD_TYPE_SCIENCE_ID;

/**
 * @author houzb
 * @Description 任务状态定时任务检查
 * @create 2020-09-26 18:25
 */
@Component
public class PublishTaskTimer {

    public static final List<PublishAwardTaskDo> PUBLISH_AWARD_TASK_DO_LIST = Collections.synchronizedList(new ArrayList<>());
    private static final int TASK_IS_NO_OVER_VAL = 1;
    private Logger logger = LoggerFactory.getLogger(PublishTaskTimer.class);
    private Map<String, Long> taskNoScoreProMap = new HashMap<>();


    @Autowired
    private AwardPublishTaskDao awardPublishTaskDao;
    @Autowired
    private AwardEnterpriseProjectDao awardEnterpriseProjectDao;
    @Autowired
    private SpecialistService specialistService;
    @Autowired
    private AwardPublishTaskService awardPublishTaskService;
    @Autowired
    private AwardProjectScoreResultService awardProjectScoreResultService;

    //    @Scheduled(cron = "*/1 * * * * ?")
    public void checkStat() {
        //TODO 记录任务变更状态的日志记录,存储字段时间，状态，任务id
        checkApplyIsEnd();
        checkValidateIsEnd();
        checkScoreIsEnd();
    }

    @PostConstruct
    @Scheduled(cron = "0 0 */1 * * ?")
    public void getAllNoOverTask() {
        Map<String, Object> params = new HashMap<>();
        params.put("isNoOver", TASK_IS_NO_OVER_VAL);
        PUBLISH_AWARD_TASK_DO_LIST.clear();

        List<PublishAwardTaskDo> taskDoList = awardPublishTaskService.list(params);
        for(PublishAwardTaskDo taskDo:taskDoList) {
             boolean isScoreOver = taskDo.getIsScoreOver();
             if (isScoreOver) {
                 Map<String, Object> paramsPro = new HashMap<>();
                 paramsPro.put("taskId", taskDo.getId());
                 List<SpecialistScoreProOverCountInfo> overProInfoList = specialistService.getScoreOverSpecialistCountProList(paramsPro);
                 if(overProInfoList.size() == 0) {
                     continue;
                 }
                 boolean isCalOver = false;
                 for (SpecialistScoreProOverCountInfo proOverCountInfo : overProInfoList) {
                     String proId = proOverCountInfo.getProId() + "";
                     Map<String, Object> paramsIsOver = new HashMap<>();
                     paramsIsOver.put("proId", proId);
                     int counts = awardProjectScoreResultService.count(paramsIsOver);
                     if(counts > 0) {
                         isCalOver = true;
                         break;
                     }
                 }
                 if(!isCalOver) {
                     PUBLISH_AWARD_TASK_DO_LIST.add(taskDo);
                 }
             }
        }
    }

    /**
     * 科技奖任务评分时间到进行分数汇总处理
     */
    @Scheduled(cron = "0 */5 * * * ?")
    public void scienceAwardScoreOverResult() {
        //任务不存在打分项目的延迟1个小时再次进行验证
        //延迟1小时
        int delayTimeLen = 3600000;
        for (PublishAwardTaskDo taskDo : PUBLISH_AWARD_TASK_DO_LIST) {
            String awardId = taskDo.getAwardId();
            String taskId = taskDo.getId();
            Long noProTime = taskNoScoreProMap.get(taskId);
            if(noProTime != null && System.currentTimeMillis() - noProTime < delayTimeLen) {
                continue;
            }
            if (AWARD_TYPE_SCIENCE_ID.equals(awardId)) {
                //科技进步奖
                taskDo.initStat();
                boolean isScoreOver = taskDo.getIsScoreOver();
                if (isScoreOver) {

                    //打分结束的项目需要计算平均分
                    Map<String, Object> params = new HashMap<>();
                    params.put("taskId", taskId);
                    List<SpecialistScoreProOverCountInfo> overProInfoList = specialistService.getScoreOverSpecialistCountProList(params);
                    int size = overProInfoList.size();
                    if (size == 0) {
                        taskNoScoreProMap.put(taskId, System.currentTimeMillis());
                    }
                    for (SpecialistScoreProOverCountInfo proOverCountInfo : overProInfoList) {
                        String proId = proOverCountInfo.getProId() + "";
                        //计算项目的分数
                        specialistService.calculateProScore(proId);
                    }
                }
            }
        }
    }


    /**
     * 检测申报日期是否结束
     * 根据项目填写的申报截止日期进行检验
     */
    public void checkApplyIsEnd() {
        //检测是否申报结束
        for (PublishAwardTaskDo taskDo : PUBLISH_AWARD_TASK_DO_LIST) {
            String taskStat = taskDo.getTaskStat();
            if (!taskStat.equals(EnumApplyTaskStat.APPLY.getStat())) {
                continue;
            }
            if (StringUtils.isBlank(taskDo.getApplyEndDate())) {
                continue;
            }
            long diff = DateUtils.diffNow(taskDo.getApplyEndDate());
            if (diff > 0) {
                logger.debug("申报结束,进入分派项目给外聘人员 {},{}", taskDo.getId(), taskDo.getTaskName());
                //申报结束,进入分派项目给外聘人员
                PublishAwardTaskDo params = new PublishAwardTaskDo();
                params.setId(taskDo.getId());
                params.setTaskStat(EnumApplyTaskStat.ASSIGN_VALIDATE.getStat());
                awardPublishTaskDao.update(params);
                //更新此任务下所有的项目进入待审核中
                Map<String, Object> proParams = new HashMap<>();
                proParams.put("taskId", taskDo.getId());
                proParams.put("proStat", EnumApplyEnterpriseProStat.TO_VALIDATE.getStat());
                awardEnterpriseProjectDao.updateProStatByTaskId(proParams);

                getAllNoOverTask();
            }
        }
    }

    /**
     * 检测形式审查是否结束
     * 根据申报任务创建时填写的截止日期进行检验
     */
    public void checkValidateIsEnd() {
        //检测是否形式审查结束
        for (PublishAwardTaskDo taskDo : PUBLISH_AWARD_TASK_DO_LIST) {
            String taskStat = taskDo.getTaskStat();
            if (!taskStat.equals(EnumApplyTaskStat.VALIDATE.getStat())) {
                continue;
            }
            long diff = DateUtils.diffNow(taskDo.getCheckEndTime());
            if (diff > 0) {
                logger.debug("形式审查结束，进入专家打分环节 {},{}", taskDo.getId(), taskDo.getTaskName());

                //形式审查结束，进入专家打分环节
                PublishAwardTaskDo params = new PublishAwardTaskDo();
                params.setId(taskDo.getId());
                params.setTaskStat(EnumApplyTaskStat.SCORE.getStat());
                awardPublishTaskDao.update(params);

                getAllNoOverTask();
            }
        }
    }

    /**
     * 检测专家打分是否结束
     * 根据申报任务创建的打分结束时间进行检测
     */
    public void checkScoreIsEnd() {
        //检测是否专家打分结束
        for (PublishAwardTaskDo taskDo : PUBLISH_AWARD_TASK_DO_LIST) {
            String taskStat = taskDo.getTaskStat();
            if (!taskStat.equals(EnumApplyTaskStat.SCORE.getStat())) {
                continue;
            }
            long diff = DateUtils.diffNow(taskDo.getExpertEndTime());
            if (diff > 0) {
                logger.debug("打分结束，进入分数汇总环节 {},{}", taskDo.getId(), taskDo.getTaskName());

                //打分结束，进入分数汇总环节
                PublishAwardTaskDo params = new PublishAwardTaskDo();
                params.setId(taskDo.getId());
                params.setTaskStat(EnumApplyTaskStat.RESULT.getStat());
                awardPublishTaskDao.update(params);
                //TODO 统计分数 生成模板

                //更新此任务下所有的项目进入完结状态
                Map<String, Object> proParams = new HashMap<>();
                proParams.put("taskId", taskDo.getId());
                proParams.put("proStat", EnumApplyEnterpriseProStat.RESULT.getStat());
                awardEnterpriseProjectDao.updateProStatByTaskId(proParams);

                getAllNoOverTask();
            }
        }
    }
}
