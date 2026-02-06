package com.bootdo.activiti.domain;

import com.bootdo.common.utils.StringUtils;
import com.bootdo.cpe.petroleum_engineering_award.domain.ScienceBaseProjectInfoDO;
import com.bootdo.cpe.utils.EnumUtils;

import java.io.Serializable;

public class EnterpriseProjectInfoDo extends ScienceBaseProjectInfoDO implements Serializable {

    private int id;
    private int itemId;
    /**
     * 显示的编号 从1开始进行
     */
    private int showNum;
    //流程实例ID
    private String procInsId;
    private String chengguo;
    private String chengguoScience;
    private String teamName;
    private String personalName;
    private String proChengguo;
    //创建时可能是用户自己编写的专业
    private String major;
    private Long majorId;
    private String tuandui;
    //主要完成人
    private String person;
    private String proDesc;
    private String publishTaskId;
    private Long createUid;
    private String created;
    private String advancedIndividual;
    private String publishTaskUserId;
    private String taskName;
    private String assignedUserName;

    //形式审查结果
    private String associationReviewRst;
    //形式审查驳回的原因
    private String associationRejectContent;
    private String proScore;
    private String taskAgree;
    //工作流当前运行的任务id
    private String actRunTaskId;
    //项目创建者所属企业
    private String enterpriseName;

    private String rstScienceValiFile;
    private String rstTuanduiValiFile;
    private String rstPersonalValiFile;

    private String scienceLevel;
    private String scienceKnowledgeCount;
    private String scienceVliEnterprise;
    private String scienceApplyTime;
    private String scienceApplyEnterpriseCount;

    private String tuanduiCode;
    private String tuanduiCooperate;
    private String tuanduiSpecialist;

    private String personalIsEmploy;
    private String personalIsWorkEthics;

    private Double scienceAvgScore;
    private Double teamAvgScore;
    private Double personalAvgScore;
    //专家领导选择的审核的菜单:打分统计表，专家推荐意见表,专家评审意见表
    private String leaderSelMenu;
    //奖项名称:科技进步奖，qc奖等
    private String awardName;
    //分数的排名
    private String scienceOrderNum;
    private String teamOrderNum;
    private String personalOrderNum;

    //是否推荐
    private String scienceIsRecommend;
    private String teamIsRecommend;
    private String personalIsRecommend;
    //项目的类型:science_progress 科技进步,science_team科技团队,science_personal科技个人
    private String proType;
    //类型展示
    private String proTypeStr;
    //项目子类型
    private String proSubType;

    private String proTypeStrCate;

    //对外展示的项目名称
    private String showProName;
    //项目名称
    private String proName;
    //申报个人的记录id
    private int personId;

    //项目分组名称
    private String proGroupName;

    private String mainCompleteNames;

    private String proCode ;

    public String getProCode() {
        return proCode;
    }

    public void setProCode(String proCode) {
        this.proCode = proCode;
    }

    public String getMainCompleteNames() {
        return mainCompleteNames;
    }

    public void setMainCompleteNames(String mainCompleteNames) {
        this.mainCompleteNames = mainCompleteNames;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getShowNum() {
        return showNum;
    }

    public void setShowNum(int showNum) {
        this.showNum = showNum;
    }

    public String getProcInsId() {
        return procInsId;
    }

    public void setProcInsId(String procInsId) {
        this.procInsId = procInsId;
    }

    public String getChengguo() {
        if(StringUtils.isNotBlank(this.proChengguo)) {
            return this.proChengguo;
        }
        return chengguo;
    }

    public void setChengguo(String chengguo) {
        this.chengguo = chengguo;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getPersonalName() {
        return personalName;
    }

    public void setPersonalName(String personalName) {
        this.personalName = personalName;
    }

    public String getProChengguo() {
        return proChengguo;
    }

    public void setProChengguo(String proChengguo) {
        this.proChengguo = proChengguo;
    }

    public Long getMajorId() {
        return majorId;
    }

    public void setMajorId(Long majorId) {
        this.majorId = majorId;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getTuandui() {
        return tuandui;
    }

    public void setTuandui(String tuandui) {
        this.tuandui = tuandui;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getProDesc() {
        return proDesc;
    }

    public void setProDesc(String proDesc) {
        this.proDesc = proDesc;
    }

    public String getPublishTaskId() {
        return publishTaskId;
    }

    public void setPublishTaskId(String publishTaskId) {
        this.publishTaskId = publishTaskId;
    }

    public Long getCreateUid() {
        return createUid;
    }

    public void setCreateUid(Long createUid) {
        this.createUid = createUid;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        if(StringUtils.isNotBlank(created)) {
            created = created.replaceAll("\\.0", "");
        }
        this.created = created;
    }

    public String getAdvancedIndividual() {
        return advancedIndividual;
    }

    public void setAdvancedIndividual(String advancedIndividual) {
        this.advancedIndividual = advancedIndividual;
    }

    public String getPublishTaskUserId() {
        return publishTaskUserId;
    }

    public void setPublishTaskUserId(String publishTaskUserId) {
        this.publishTaskUserId = publishTaskUserId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getAssignedUserName() {
        return assignedUserName;
    }

    public void setAssignedUserName(String assignedUserName) {
        this.assignedUserName = assignedUserName;
    }

    public String getAssociationReviewRst() {
        return associationReviewRst;
    }

    public void setAssociationReviewRst(String associationReviewRst) {
        this.associationReviewRst = associationReviewRst;
        this.taskAgree = associationReviewRst;
    }

    public String getAssociationRejectContent() {
        return associationRejectContent;
    }

    public void setAssociationRejectContent(String associationRejectContent) {
        this.associationRejectContent = associationRejectContent;
    }

    public String getProScore() {
        return proScore;
    }

    public void setProScore(String proScore) {
        this.proScore = proScore;
    }

    public String getTaskAgree() {
        return taskAgree;
    }

    public void setTaskAgree(String taskAgree) {
        this.taskAgree = taskAgree;
        this.associationReviewRst = taskAgree;
    }

    public String getActRunTaskId() {
        return actRunTaskId;
    }

    public void setActRunTaskId(String actRunTaskId) {
        this.actRunTaskId = actRunTaskId;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public String getRstScienceValiFile() {
        return rstScienceValiFile;
    }

    public void setRstScienceValiFile(String rstScienceValiFile) {
        this.rstScienceValiFile = rstScienceValiFile;
    }

    public String getRstTuanduiValiFile() {
        return rstTuanduiValiFile;
    }

    public void setRstTuanduiValiFile(String rstTuanduiValiFile) {
        this.rstTuanduiValiFile = rstTuanduiValiFile;
    }

    public String getRstPersonalValiFile() {
        return rstPersonalValiFile;
    }

    public void setRstPersonalValiFile(String rstPersonalValiFile) {
        this.rstPersonalValiFile = rstPersonalValiFile;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getScienceLevel() {
        return scienceLevel;
    }

    public void setScienceLevel(String scienceLevel) {
        this.scienceLevel = scienceLevel;
    }

    public String getScienceKnowledgeCount() {
        return scienceKnowledgeCount;
    }

    public void setScienceKnowledgeCount(String scienceKnowledgeCount) {
        this.scienceKnowledgeCount = scienceKnowledgeCount;
    }

    public String getScienceVliEnterprise() {
        return scienceVliEnterprise;
    }

    public void setScienceVliEnterprise(String scienceVliEnterprise) {
        this.scienceVliEnterprise = scienceVliEnterprise;
    }

    public String getScienceApplyTime() {
        return scienceApplyTime;
    }

    public void setScienceApplyTime(String scienceApplyTime) {
        this.scienceApplyTime = scienceApplyTime;
    }

    public String getScienceApplyEnterpriseCount() {
        return scienceApplyEnterpriseCount;
    }

    public void setScienceApplyEnterpriseCount(String scienceApplyEnterpriseCount) {
        this.scienceApplyEnterpriseCount = scienceApplyEnterpriseCount;
    }

    public String getTuanduiCode() {
        return tuanduiCode;
    }

    public void setTuanduiCode(String tuanduiCode) {
        this.tuanduiCode = tuanduiCode;
    }

    public String getTuanduiCooperate() {
        return tuanduiCooperate;
    }

    public void setTuanduiCooperate(String tuanduiCooperate) {
        this.tuanduiCooperate = tuanduiCooperate;
    }

    public String getTuanduiSpecialist() {
        return tuanduiSpecialist;
    }

    public void setTuanduiSpecialist(String tuanduiSpecialist) {
        this.tuanduiSpecialist = tuanduiSpecialist;
    }

    public String getPersonalIsEmploy() {
        return personalIsEmploy;
    }

    public void setPersonalIsEmploy(String personalIsEmploy) {
        this.personalIsEmploy = personalIsEmploy;
    }

    public String getPersonalIsWorkEthics() {
        return personalIsWorkEthics;
    }

    public void setPersonalIsWorkEthics(String personalIsWorkEthics) {
        this.personalIsWorkEthics = personalIsWorkEthics;
    }

    public Double getScienceAvgScore() {
        return scienceAvgScore;
    }

    public void setScienceAvgScore(Double scienceAvgScore) {
        this.scienceAvgScore = scienceAvgScore;
    }

    public Double getTeamAvgScore() {
        return teamAvgScore;
    }

    public void setTeamAvgScore(Double teamAvgScore) {
        this.teamAvgScore = teamAvgScore;
    }

    public Double getPersonalAvgScore() {
        return personalAvgScore;
    }

    public void setPersonalAvgScore(Double personalAvgScore) {
        this.personalAvgScore = personalAvgScore;
    }

    public String getLeaderSelMenu() {
        return leaderSelMenu;
    }

    public void setLeaderSelMenu(String leaderSelMenu) {
        this.leaderSelMenu = leaderSelMenu;
    }

    public String getAwardName() {
        return awardName;
    }

    public void setAwardName(String awardName) {
        this.awardName = awardName;
    }

    public String getScienceOrderNum() {
        return scienceOrderNum;
    }

    public void setScienceOrderNum(String scienceOrderNum) {
        this.scienceOrderNum = scienceOrderNum;
    }

    public String getTeamOrderNum() {
        return teamOrderNum;
    }

    public void setTeamOrderNum(String teamOrderNum) {
        this.teamOrderNum = teamOrderNum;
    }

    public String getPersonalOrderNum() {
        return personalOrderNum;
    }

    public void setPersonalOrderNum(String personalOrderNum) {
        this.personalOrderNum = personalOrderNum;
    }

    public String getScienceIsRecommend() {
        return scienceIsRecommend;
    }

    public void setScienceIsRecommend(String scienceIsRecommend) {
        this.scienceIsRecommend = scienceIsRecommend;
    }

    public String getTeamIsRecommend() {
        return teamIsRecommend;
    }

    public void setTeamIsRecommend(String teamIsRecommend) {
        this.teamIsRecommend = teamIsRecommend;
    }

    public String getPersonalIsRecommend() {
        return personalIsRecommend;
    }

    public void setPersonalIsRecommend(String personalIsRecommend) {
        this.personalIsRecommend = personalIsRecommend;
    }

    public String getProType() {
        return proType;
    }

    public void setProType(String proType) {
        this.proType = proType;
        this.proTypeStr = EnumUtils.getProjectTypeShowStrByType(proType);
//        : 科技进步,科技团队,科技个人
        if (proType.equalsIgnoreCase("science_progress")){
            this.proTypeStrCate = "科技奖";
        }else if(proType.equalsIgnoreCase("science_team")){
            this.proTypeStrCate = "先进团队";
        }else if(proType.equalsIgnoreCase("science_personal")){
            this.proTypeStrCate = "先进个人";
        }

    }

    public String getProTypeStrCate(){

       return  proTypeStrCate;
    }
    public String getProTypeStr() {
        return proTypeStr;
    }

    public void setProTypeStr(String proTypeStr) {
        this.proTypeStr = proTypeStr;
    }

    public String getProSubType() {
        return proSubType;
    }

    public void setProSubType(String proSubType) {
        this.proSubType = proSubType;
    }

    public String getShowProName() {
        return showProName;
    }

    public void setShowProName(String showProName) {
        this.showProName = showProName;
    }

    public void initShowProName() {
        if(StringUtils.isNotBlank(this.personalName)) {
            this.showProName = this.personalName;
        }else if(StringUtils.isNotBlank(this.chengguoScience)) {
            this.showProName = this.chengguoScience;
        }else if(StringUtils.isNotBlank(this.teamName)) {
            this.showProName = this.teamName;
        }
        this.proName = this.showProName;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getChengguoScience() {
        return chengguoScience;
    }

    public void setChengguoScience(String chengguoScience) {
        this.chengguoScience = chengguoScience;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getProGroupName() {
        return proGroupName;
    }

    public void setProGroupName(String proGroupName) {
        this.proGroupName = proGroupName;
    }
}
