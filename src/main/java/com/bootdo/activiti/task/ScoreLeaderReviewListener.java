package com.bootdo.activiti.task;

import com.bootdo.activiti.domain.AssignProjectDataDo;
import com.bootdo.activiti.domain.EnterpriseProjectInfoDo;
import com.bootdo.activiti.domain.ProjectScoreInfo;
import com.bootdo.activiti.service.AwardEnterpriseProjectService;
import com.bootdo.activiti.service.SpecialistService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.bootdo.common.utils.SpringContextHolder.getBean;

public class ScoreLeaderReviewListener implements TaskListener {
    private Logger logger = LoggerFactory.getLogger(ScoreLeaderReviewListener.class);
    @Override
    public void notify(DelegateTask delegateTask) {
        logger.debug("开始领导复核项目分数");
        Object proIdObj = delegateTask.getVariable("proId");
        if(proIdObj == null) {
            logger.error("score proId Is null ------------------------------------------------------>");
        }else{
            logger.debug("score开始领导复核分数的项目id:{}",proIdObj);
            String proId = proIdObj.toString();
            AwardEnterpriseProjectService awardEnterpriseProjectService = getBean("awardEnterpriseProjectService");
            SpecialistService specialistService = getBean("specialistService");
            //获取项目专业的领导用户id，分配项目同时设置任务
            String proMajorLeaderUid = awardEnterpriseProjectService.getMajorLeaderIdByProId(proId);
            delegateTask.setAssignee(proMajorLeaderUid);

            //项目各个分数计算保存,去掉最高分，去掉最低分，算平均分数
            List<ProjectScoreInfo> scoreInfoList = specialistService.getProScoreList(proId);
            List<Double> scienceScoreList = new ArrayList<>();
            List<Double> teamScoreList = new ArrayList<>();
            List<Double> personalScoreList = new ArrayList<>();
            for(ProjectScoreInfo scoreInfo:scoreInfoList) {
               double science = scoreInfo.getScienceScore();
               scienceScoreList.add(science);
               double teamScore = scoreInfo.getTeamScore();
               teamScoreList.add(teamScore);
               double personalScore = scoreInfo.getPersonalScore();
               personalScoreList.add(personalScore);
            }
            double scienceAvg = getAvg(scienceScoreList);
            double teamAvg = getAvg(teamScoreList);
            double personalAvg = getAvg(personalScoreList);
            EnterpriseProjectInfoDo projectInfo = new EnterpriseProjectInfoDo();
            projectInfo.setScienceAvgScore(scienceAvg);
            projectInfo.setTeamAvgScore(teamAvg);
            projectInfo.setPersonalAvgScore(personalAvg);


            //分配项目给专业领导，
            List<AssignProjectDataDo> assignProjectDataDoList = new ArrayList<>();
            AssignProjectDataDo assignProjectDataDo = new AssignProjectDataDo();
            //分配用户id为-100表示专业领导审核
            assignProjectDataDo.setAssignUid(-100);
            assignProjectDataDo.setProId(Integer.parseInt(proId));
            assignProjectDataDo.setUid(Long.parseLong(proMajorLeaderUid));
            assignProjectDataDoList.add(assignProjectDataDo);
            awardEnterpriseProjectService.assignPro(assignProjectDataDoList);

            projectInfo.setActRunTaskId(delegateTask.getId());
            projectInfo.setId(Integer.parseInt(proId));
            projectInfo.setProcInsId(delegateTask.getProcessInstanceId());
            awardEnterpriseProjectService.update(projectInfo);
        }
    }

    private double getAvg(List<Double> list){
        double total = 0;
        if(list.size() < 4) {
            for(double v:list) {
                total += v;
            }
            double rst = total / list.size();
            return keepDecimalRunding(rst);
        }
        list.sort(Comparator.comparing(Double::intValue));
        for(int i=1;i<list.size() - 1;i++) {
            total += list.get(i);
        }
        double rst = total / (list.size() - 2);
        return keepDecimalRunding(rst);
    }

    private double keepDecimalRunding(double rst) {
        BigDecimal bd = new BigDecimal(rst);
        BigDecimal bd2 = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        return bd2.doubleValue();
    }
}
