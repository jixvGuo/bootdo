package com.bootdo.activiti.service.impl;

import com.bootdo.activiti.dao.AwardEnterpriseProjectDao;
import com.bootdo.activiti.dao.AwardPublishTaskDao;
import com.bootdo.activiti.dao.SpecialistDao;
import com.bootdo.activiti.domain.*;
import com.bootdo.activiti.service.SpecialistService;
import com.bootdo.common.domain.Tree;
import com.bootdo.common.utils.BuildTree;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.cpe.dao.AwardProjectScoreResultDao;
import com.bootdo.cpe.dao.ExpertGroupDao;
import com.bootdo.cpe.dao.SpecialistScoreOverDao;
import com.bootdo.cpe.domain.*;
import com.bootdo.cpe.utils.NumberUtils;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.Collator;
import java.util.*;
import java.util.stream.Collectors;

import static com.bootdo.common.config.Constant.CHINESE_NUMBER_MAP;

@Service("specialistService")
public class SpecialistServiceImpl implements SpecialistService {
    private Logger logger = LoggerFactory.getLogger(SpecialistServiceImpl.class);
    @Autowired
    private SpecialistDao specialistDao;
    @Autowired
    private AwardPublishTaskDao publishTaskDao;
    @Autowired
    private ActTaskServiceImpl actTaskService;
    @Autowired
    private AwardEnterpriseProjectDao awardEnterpriseProjectDao;
    @Autowired
    private SpecialistScoreOverDao specialistScoreOverDao;
    @Autowired
    private AwardProjectScoreResultDao awardProjectScoreResultDao;
    @Autowired
    private ExpertGroupDao expertGroupDao;


    @Override
    public int save(SpecialistSelInfo selInfo) {
        boolean isComplete = selInfo.isComplete();
        if(isComplete){
           //协会工作人员提交完成专家选择
           String publishTaskId = selInfo.getPublishAwardId();
           List<String> proIdList = publishTaskDao.getProIdsByPublishTaskId(publishTaskId);
           //选择专家完成流转到下一个流程节点，专家开始进行打分
           int pocInsId = publishTaskDao.getPublishTaskActProcInsId(publishTaskId);
           List<Task> actTaskList = actTaskService.taskService.createTaskQuery().processInstanceId(pocInsId+"").list();
           actTaskList.stream().forEach(task -> {
              String key = task.getTaskDefinitionKey();
              if("sel_sepcialist".equals(key)) {
                  //从专家选择节点进入专家打分的节点
                  String tId = task.getId();
                  logger.debug("完成专家选择{},{}",publishTaskId,tId);
                  Map<String,Object> params = new HashMap<>();
                  params.put("proIdList",proIdList);
                  actTaskService.complete(tId,params);
              }
           });
        }
        if(selInfo.isCouldSave()) {
            return specialistDao.save(selInfo);
        }
        return 11;
    }

    @Override
    public List<SpecialistSelInfo> listByProId(String proId) {
        Map<String,Object> params = new HashMap<>();
        //临时不用项目id去获取专家
        params.put("proId",proId);
        return specialistDao.list(params);
    }

    @Override
    public List<SpecialistSelInfo> list(Map<String, Object> param) {
        return specialistDao.list(param);
    }

    @Override
    public List<AwardScoreDetailInfo> getProScoreDetails(Map<String,Object> params) {
        return specialistDao.getProScoreDetails(params);
    }

    @Override
    public List<ProjectScoreInfo> getProScoreList(String proId) {
        Map<String,Object> params = new HashMap<>();
        params.put("proId",proId);
        return this.listProScore(params);
    }

    @Override
    public List<ProjectScoreInfo> listProScore(Map<String, Object> params) {
        List<ProjectScoreInfo> list = specialistDao.listProScore(params);
        list.stream().forEach(ps->{
            if(StringUtils.isNotBlank(ps.getChengguo())) {
                ps.setProName(ps.getChengguo());
            }else if(StringUtils.isNotBlank(ps.getTeamName())) {
                ps.setProName(ps.getTeamName());
            }else if(StringUtils.isNotBlank(ps.getPersonName())) {
                ps.setProName(ps.getPersonName());
            }
        });
        return list;
    }

    @Override
    public List<ExpertGroupDO> listSpecialistInfoList(Map<String, Object> params) {
        return expertGroupDao.list(params);
    }

    /**
     * 获取提交打分结束的标记值
     *
     * @param params
     * @return
     */
    @Override
    public int getSubmitScoreOverFlg(Map<String, Object> params) {
        return specialistDao.getSubmitScoreOverFlg(params);
    }

    @Override
    public ProjectScoreInfo getMineScoreProInfo(Map<String, Object> params) {
        return specialistDao.getMineScoreProInfo(params);
    }

    @Override
    public void proCompleteScoreFlow(String proId) {
       String taskRunId = awardEnterpriseProjectDao.getProCurActRunTaskIdByProId(proId);
       EnterpriseProjectInfoDo projectInfoDo = awardEnterpriseProjectDao.get(proId);
       String majorId = projectInfoDo.getMajorId()+"";
       Map<String,Object> params = new HashMap<>();
       params.put("majorId",majorId);
       params.put("proId",proId);
       String proMajorLeaderUid = awardEnterpriseProjectDao.getMajorLeaderIdByProId(params);
       List<String> leaderList = new ArrayList<>();
       leaderList.add(proMajorLeaderUid);
       params.put("leaderList",leaderList);
       actTaskService.complete(taskRunId,params);
    }

    @Override
    public int scorePro(ProjectScoreInfo scoreInfo) {
        int rst = specialistDao.scorePro(scoreInfo);
        specialistDao.removeMineDetailScore(scoreInfo);
        specialistDao.scoreDetailPro(scoreInfo);
        return rst;
    }

    @Override
    public boolean isScoreOverPro(String proId) {
        //1.获取项目的需打分的项
        EnterpriseProjectInfoDo projectInfoDo = awardEnterpriseProjectDao.get(proId);
        String scienceRst = projectInfoDo.getRstScienceValiFile();
        String teamRst = projectInfoDo.getRstTuanduiValiFile();
        String personalRst = projectInfoDo.getRstPersonalValiFile();
        //2.判断各个项是否均打分完成
        boolean isOver = true;
        if(StringUtils.isNotBlank(scienceRst)) {
            //科技团队
            int leftCount = specialistDao.isScoreOverPro(proId,"science_score");
            if(leftCount > 0) {
                isOver = false;
            }
        }
        if(StringUtils.isNotBlank(teamRst)) {
            //团队审核结果
            int leftCount = specialistDao.isScoreOverPro(proId,"team_score");
            if(leftCount > 0) {
                isOver = false;
            }
        }
        if(StringUtils.isNotBlank(personalRst)) {
            int leftCount = specialistDao.isScoreOverPro(proId,"personal_score");
            if(leftCount > 0) {
                isOver = false;
            }
        }

        return isOver;
    }

    @Override
    public Tree<AwardGroupInfo> getTreeAwardGroupInfo(Map<String,Object> params) {
        List<Tree<AwardGroupInfo>> trees = new ArrayList<Tree<AwardGroupInfo>>();
        List<AwardGroupInfo> groupInfos = specialistDao.listAwardGroupInfo(params);
        for (AwardGroupInfo group : groupInfos) {
            //封装奖项信息
            AwardGroupInfo awardInfo = group.getAwardInfo();
            packageAwardGroupTree(awardInfo,trees);
            //封装专业分组信息
            packageAwardGroupTree(group,trees);
            //封装操作菜单
            List<AwardGroupInfo> optMenuList = group.optMenus();
            optMenuList.stream().forEach(g->{packageAwardGroupTree(g,trees);});
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<AwardGroupInfo> t = BuildTree.build(trees);
        return t;
    }

    /**
     * 获取专家各专业的账号信息
     *
     * @param params
     * @return
     */
    @Override
    public List<TreeBootstrap> getSpecialistMajorAccountList(Map<String, Object> params) {
        List<ExpertGroupDO> expertGroupDOList = expertGroupDao.list(params);
        Map<String,List<String>> expertGroupMap = new HashMap<>();
        Map<String, String> accountMap = new HashMap<>();
        for(ExpertGroupDO groupDO:expertGroupDOList) {
            String gName = groupDO.getGroupName();
            List<String> gList = expertGroupMap.get(gName);
            if(gList == null) {
                gList = new ArrayList<>();
                expertGroupMap.put(gName, gList);
            }
            String showTxt = groupDO.getExpertName() + "(" + groupDO.getLoginAccount() + ")";
            gList.add(showTxt);
            accountMap.put(showTxt, groupDO.getLoginAccount());
        }
        List<TreeBootstrap> treeBootstrapList = new ArrayList<>();
        for(String groupName:expertGroupMap.keySet()) {
            List<String> accountsArr = expertGroupMap.get(groupName);
            TreeBootstrap parent = new TreeBootstrap();
            parent.setText(groupName);
            parent.setHref("#major");
            List<TreeBootstrap> nodesList = new ArrayList<>();
            for(String account:accountsArr) {
                TreeBootstrap node = new TreeBootstrap();
                node.setText(account);
                node.setHref("#account");
                node.setParentText(groupName);
                node.putExtData("account", accountMap.get(account));
                nodesList.add(node);
            }
            parent.setNodes(nodesList);
            treeBootstrapList.add(parent);
        }

        Collator comparator = Collator.getInstance(Locale.CHINESE);
        return treeBootstrapList.stream().sorted(new Comparator<TreeBootstrap>() {
            @Override
            public int compare(TreeBootstrap o1, TreeBootstrap o2) {
                Integer o1T = CHINESE_NUMBER_MAP.get(o1.getText().substring(0,1));
                Integer o2T = CHINESE_NUMBER_MAP.get(o2.getText().substring(0,1));
                if(o1T !=null && o2T != null) {
                    return o1T - o2T;
                }
                return comparator.compare(o1.getText(), o2.getText());
            }
        }).collect(Collectors.toList());
    }

    /**
     * 专家提交打分结果结束打分
     *
     * @param scoreUid
     */
    @Override
    public void scoreOver(Long scoreUid,String scoreType) {
        specialistScoreOverDao.scoreOverPro(scoreUid, scoreType);
    }

    /**
     * 分数取消
     * @param scoreUid
     * @param scoreType
     */
    @Override
    public void scoreCancel(Long scoreUid, String scoreType) {
        specialistScoreOverDao.scoreCancelPro(scoreUid, scoreType);
    }

    /**
     * 获取当前的是否提交了评分
     *
     * @param scoreUid
     * @param scoreType
     * @return
     */
    @Override
    public int getScoreIsOver(Long scoreUid, String scoreType) {
        List<Integer> list = specialistScoreOverDao.getScoreIsOver(scoreUid, scoreType);
        return list.size() > 0 ? list.get(0) : 0;
    }

    /**
     * 获取已打分完结的项目列表信息
     *
     * @param params
     * @return
     */
    @Override
    public List<SpecialistScoreProOverCountInfo> getScoreOverSpecialistCountProList(Map<String, Object> params) {
        return specialistScoreOverDao.getScoreOverSpecialistCountProList(params);
    }

    /**
     * 获取某专家项目评分明细
     *
     * @param params
     * @return
     */
    @Override
    public List<SpecialistScoreDetailInfo> getSpecialistScoreProDetailList(Map<String, Object> params) {
        return specialistDao.getSpecialistScoreProDetailList(params);
    }

    /**
     * 计算项目下团队个人科技的得分
     * @param proId
     */
    @Override
    public void calculateProScore(String proId){
        //项目各个分数计算保存,去掉最高分，去掉最低分，算平均分数
            List<ProjectScoreInfo> scoreInfoList = this.getProScoreList(proId);
            Map<Integer,List<Double>> scienceScoreMap = new HashMap<>();
            Map<Integer,List<Double>> teamScoreMap = new HashMap<>();
            Map<Integer,List<Double>> personalScoreMap = new HashMap<>();
            for(ProjectScoreInfo scoreInfo:scoreInfoList) {
                String scoreType = scoreInfo.getScoreType();
                double score = scoreInfo.getTotalScore();
                int itemId = scoreInfo.getItemId();
                if(EnumScienceScoreType.PERSONAL.getScoreType().equals(scoreType)) {
                    addScore(score,personalScoreMap,itemId);
                }else if(EnumScienceScoreType.TEAM.getScoreType().equals(scoreType)) {
                    addScore(score,teamScoreMap,itemId);
                }else if(EnumScienceScoreType.SCIENCE.getScoreType().equals(scoreType)) {
                    addScore(score,scienceScoreMap,itemId);
                }
            }

            //计算分数并保存
            calculateProItemScore(scienceScoreMap,proId);
            calculateProItemScore(teamScoreMap,proId);
            calculateProItemScore(personalScoreMap,proId);

    }

    @Override
    public int isCalculateProScoreOver(String proId) {
        return 0;
    }

    @Override
    public List<SpecialistScoreProInfo> getSpecialistScoreProList(Map<String, Object> params) {
        if(params.get("groupName") == null || StringUtils.isBlank(params.get("groupName").toString())) {
            return new ArrayList<>();
        }
        return specialistDao.getSpecialistScoreProList(params);
    }

    /**
     * 将分数添加到临时记录的Map中存储
     * @param score
     * @param scoreMap
     * @param itemId
     */
    private void addScore(double score,Map<Integer,List<Double>> scoreMap,int itemId) {
        List<Double> scoreList = scoreMap.get(itemId);
        if(scoreList == null) {
            scoreList = new ArrayList<>();
            scoreMap.put(itemId,scoreList);
        }
        scoreList.add(score);
    }

    /**
     * 计算项目下团队个人科技的得分结果
     * @param scoreMap
     * @param proId
     */
    private void calculateProItemScore(Map<Integer,List<Double>> scoreMap,String proId){
         if(scoreMap.size() > 0) {
             int pId = Integer.parseInt(proId);
             List<AwardProjectScoreResultDO> resultDOList = new ArrayList<>();
             for(int itemId:scoreMap.keySet()) {
                 AwardProjectScoreResultDO resultDO = new AwardProjectScoreResultDO();
                 resultDO.setProId(pId);
                 resultDO.setItemId(itemId);
                 double score =  NumberUtils.getAvg(scoreMap.get(itemId));
                 resultDO.setScoreResult(new BigDecimal(score));
                 resultDOList.add(resultDO);
             }
             awardProjectScoreResultDao.batchAdd(resultDOList);
         }
    }

    private void packageAwardGroupTree(AwardGroupInfo group, List<Tree<AwardGroupInfo>> trees){
        Tree<AwardGroupInfo> tree = new Tree<AwardGroupInfo>();
        tree.setId(group.getId()+"");
        tree.setParentId(group.getParentId()+"");
        tree.setText(group.getMajorName());
        Map<String, Object> state = new HashMap<>(16);
        state.put("opened", true);
        tree.setState(state);
        trees.add(tree);
    }
}
