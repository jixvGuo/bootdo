package com.bootdo.activiti.domain;


import com.bootdo.common.utils.DateUtils;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.cpe.domain.EnumAwardType;
import com.bootdo.cpe.utils.EnumUtils;

import java.util.Date;
import java.util.List;

public class PublishAwardTaskDo {
    //业务编号
    private String id;
    //流程实例ID
    private String procInsId = "0";
    //奖项的类型:1科技奖 默认为科技奖，后续还有三种奖项类型
    private String awardId = "1";
    private String awardName;
    private String upDocUrl;
    private String awardInfo;
    private String taskName;
    //形式检查的协会工作人员，可以多个
    private String associationUserId;


    private String applyStartDate;
    private String applyEndDate;
    //上传的文件id
    private List<Integer> upDocId;


    public String getApplyEndDate() {
        if (this.applyEndDate == null) {
            return "";
        }
        return this.applyEndDate.replace(".0", "");
    }

    public void setApplyEndDate(String applyEndDate) {
        this.applyEndDate = applyEndDate;
    }

    public String getApplyStartDate() {
        if (this.applyStartDate == null) {
            return "";
        }
        return this.applyStartDate.replace(".0", "");

    }

    public void setApplyStartDate(String applyStartDate) {
        this.applyStartDate = applyStartDate;
    }

    private String taskStartTime;//任务申报开始时间
    private String taskEndTime;  //任务申报结束时间


    private String checkStartTime;// 形式审查阶段 开始时间
    private String checkEndTime;  // 形式审查阶段 结束时间


    private String expertStartTime;// 专家审查阶段  开始时间
    private String expertEndTime;  // 专家审查阶段  结束时间
    private String expertStartTimeSecond;// 第二阶段专家审查阶段  开始时间
    private String expertEndTimeSecond;// 第二阶段专家审查阶段  开始时间


    private String updateTime;//更新时间(每次进行操作都会变更时间)
    private String expertTime;//1:专家评审时间（专家评审结束时间）
    private String reviewTime;//形式审查结束时间/专家评审开时间
    //任务的状态信息，具体值参看:ApplyTaskStatEnum
    private String taskStat;
    //任务状态描述
    private String taskStatStr;

    /**
     * 是否分派外聘
     */
    private boolean isAssign = false;
    /**
     * 是否专业分组管理
     * 分派专家账号
     */
    private boolean isSpecialAdmin = false;

    /**
     * 是否专家打分
     */
    private boolean isScore = false;
    /**
     * 是否打分结束
     */
    private boolean isScoreOver = false;

    /**
     * 是否申请中
     */
    private boolean isApply =false;

    public String getTaskStartTime() {
        if (this.taskStartTime == null) {
            return "";
        }
        return this.taskStartTime.replace(".0", "");

    }

    public void setTaskStartTime(String taskStartTime) {
        this.taskStartTime = taskStartTime;
    }

    public String getTaskEndTime() {
        if (this.taskEndTime == null) {
            return "";
        }
        return this.taskEndTime.replace(".0", "");

    }

    public void setTaskEndTime(String taskEndTime) {
        this.taskEndTime = taskEndTime;
    }

    public String getCheckStartTime() {
        if (this.checkStartTime == null) {
            return "";
        }
        return this.checkStartTime.replace(".0", "");

    }

    public void setCheckStartTime(String checkStartTime) {
        this.checkStartTime = checkStartTime;
    }

    public String getCheckEndTime() {
        if (this.checkEndTime == null) {
            return "";
        }
        return this.checkEndTime.replace(".0", "");

    }

    public void setCheckEndTime(String checkEndTime) {
        this.checkEndTime = checkEndTime;
    }

    public String getExpertStartTime() {
        if (this.expertStartTime == null) {
            return "";
        }
        return this.expertStartTime.replace(".0", "");


    }

    public void setExpertStartTime(String expertStartTime) {
        if ("undefined".equals(expertStartTime)) {
            expertStartTime = "";
        }
        this.expertStartTime = expertStartTime;
    }

    public String getExpertEndTime() {
        if (this.expertEndTime == null) {
            return "";
        }
        return this.expertEndTime.replace(".0", "");
    }

    public void setExpertEndTime(String expertEndTime) {
        if ("undefined".equals(expertEndTime)) {
            expertEndTime = "";
        }
        this.expertEndTime = expertEndTime;
    }

    public String getExpertStartTimeSecond() {
        if(expertStartTimeSecond == null) {
            return "";
        }
        return expertStartTimeSecond.replace(".0", "");
    }

    public void setExpertStartTimeSecond(String expertStartTimeSecond) {
        if ("undefined".equals(expertStartTimeSecond)) {
             expertStartTimeSecond = "";
        }
        this.expertStartTimeSecond = expertStartTimeSecond;
    }

    public String getExpertEndTimeSecond() {
        if(expertEndTimeSecond == null) {
            return "";
        }
        return this.expertEndTimeSecond.replace(".0", "");
    }

    public void setExpertEndTimeSecond(String expertEndTimeSecond) {
        if ("undefined".equals(expertEndTimeSecond)) {
            expertEndTimeSecond = "";
        }
        this.expertEndTimeSecond = expertEndTimeSecond;
    }

    private String validateRequire;
    private String publishDate;

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getExpertTime() {
        if (this.expertTime == null) {
            return "";
        }
        return this.expertTime.replace(".0", "");

    }

    public void setExpertTime(String expertTime) {
        this.expertTime = expertTime;
    }

    public String getReviewTime() {
        if (this.reviewTime == null) {
            return "";
        }
        return this.reviewTime.replace(".0", "");
    }

    public void setReviewTime(String reviewTime) {
        this.reviewTime = reviewTime;
    }

    public int getProcessState() {
        return processState;
    }

    public void setProcessState(int processState) {
        this.processState = processState;
    }

    private String associationUserName;
    private String proId;
    private String fileId;
    private String fileUrl;
    //专业分组
    private String majorIds;
    private String chengguo;
    private String enterpriseName;
    //项目主要完成人
    private String person;
    private String tuandui;
    private String advancedIndividual;
    //是否过期
    private String overDate;
    //项目对应工作流的任务id
    private String actRunTaskId;
    //是否处于选择专家及确定组长的节点,0不是，大于0是
    private int selSpecialist;

    private int processState;//申报开始1，申报结束2，形审开始3，形审结束4，专家打分5，报奖完成6

    private int processStatus;//申报开始1，申报结束2，形审开始3，形审结束4，专家打分5，报奖完成6


    public int getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(int processStatus) {
        this.processStatus = processStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProcInsId() {
        return procInsId;
    }

    public void setProcInsId(String procInsId) {
        this.procInsId = procInsId;
    }

    public String getAwardId() {
        return awardId;
    }

    public void setAwardId(String awardId) {
        this.awardId = awardId;
    }

    public String getAwardName() {
        return awardName;
    }

    public void setAwardName(String awardName) {
        this.awardName = awardName;
    }

    public String getUpDocUrl() {
        return upDocUrl;
    }

    public void setUpDocUrl(String upDocUrl) {
        this.upDocUrl = upDocUrl;
    }

    public String getAwardInfo() {
        return awardInfo;
    }

    public void setAwardInfo(String awardInfo) {
        this.awardInfo = awardInfo;
        String[] arr = awardInfo.split("#");
        this.awardName = arr[0];
        this.upDocUrl = arr[1];
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getAssociationUserId() {
        return associationUserId;
    }

    public void setAssociationUserId(String associationUserId) {
        this.associationUserId = associationUserId;
    }


    public String getValidateRequire() {
        return validateRequire;
    }

    public void setValidateRequire(String validateRequire) {
        this.validateRequire = validateRequire;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getAssociationUserName() {
        return associationUserName;
    }

    public void setAssociationUserName(String associationUserName) {
        this.associationUserName = associationUserName;
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

//    public long getFileId() {
//        return fileId;
//    }
//
//    public void setFileId(long fileId) {
//        this.fileId = fileId;
//    }


    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getMajorIds() {
        return majorIds;
    }

    public void setMajorIds(String majorIds) {
        this.majorIds = majorIds;
    }

    public String getChengguo() {
        return chengguo;
    }

    public void setChengguo(String chengguo) {
        this.chengguo = chengguo;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getTuandui() {
        return tuandui;
    }

    public void setTuandui(String tuandui) {
        this.tuandui = tuandui;
    }

    public String getAdvancedIndividual() {
        return advancedIndividual;
    }

    public void setAdvancedIndividual(String advancedIndividual) {
        this.advancedIndividual = advancedIndividual;
    }

    public String getOverDate() {
        return overDate;
    }

    public void setOverDate(String overDate) {
        this.overDate = overDate;
    }

    public String getActRunTaskId() {
        return actRunTaskId;
    }

    public int getSelSpecialist() {
        return selSpecialist;
    }

    public void setSelSpecialist(int selSpecialist) {
        this.selSpecialist = selSpecialist;
    }

    public void setActRunTaskId(String actRunTaskId) {
        this.actRunTaskId = actRunTaskId;
    }

    public String getTaskStat() {
        return taskStat;
    }

    public void setTaskStat(String taskStat) {
        this.taskStat = taskStat;
        this.taskStatStr = EnumUtils.getTaskStatShowStrByStat(taskStat);
    }

    public String getTaskStatStr() {
        return taskStatStr;
    }

    public void setTaskStatStr(String taskStatStr) {
        this.taskStatStr = taskStatStr;
    }

    public List<Integer> getUpDocId() {
        return upDocId;
    }

    public void setUpDocId(List<Integer> upDocId) {
        this.upDocId = upDocId;
    }

    /**
     * 是否申报结束
     *
     * @return true 申报结束，false 还没有结束
     */
    public boolean isApplyEnd() {
        long diff = DateUtils.diffNow(this.applyEndDate);
        return diff > 0;
    }

    /**
     * 是否开始申请
     *
     * @return true 开始申请 false 还没有开始
     */
    public boolean isApplyStart() {
        long diff = DateUtils.diffNow(this.applyStartDate);
        return diff > 0;
    }

    public boolean getIsAssign() {
        return isAssign;
    }

    public void setAssign(boolean assign) {
        isAssign = assign;
    }

    public boolean getIsSpecialAdmin() {
        return isSpecialAdmin;
    }

    public void setSpecialAdmin(boolean specialAdmin) {
        isSpecialAdmin = specialAdmin;
    }

    public boolean getIsScore() {
        return isScore;
    }

    public void setScore(boolean score) {
        isScore = score;
    }

    public boolean getIsScoreOver() {
        return isScoreOver;
    }

    public void setIsScoreOver(boolean scoreOver) {
        isScoreOver = scoreOver;
    }

    public void setIsApply(boolean isApply) {
        isApply = isApply;
    }
    public boolean getIsApply() {
        return this.isApply;
    }


    public void initStat() {
        //根据申报任务的时间点进行状态设置
        long diff = DateUtils.diffNow(this.applyStartDate);
        if (diff < 0) {
            this.taskStatStr = "等待申请";
            return;
        } else {
            diff = DateUtils.diffNow(this.applyEndDate);
            if (diff < 0) {
                this.taskStatStr = "申请中";
                this.isApply = true;
                return;
            }
        }

        if (StringUtils.isNotBlank(this.checkStartTime)) {
            long diffEnd = DateUtils.diffNow(this.checkEndTime);
            long diffStart = DateUtils.diffNow(this.checkStartTime);
            if(diffStart < 0) {
                //未开始形式审查
                this.isAssign = this.awardId.equals(EnumAwardType.SCIENCE.getAwrdType() + "") ||
                                this.awardId.equals(EnumAwardType.QC.getAwrdType() + "") ||
                                this.awardId.equals(EnumAwardType.SURVER.getAwrdType() + "");
                this.taskStatStr = "分派";
                return;
            }
            if(diffEnd < 0 && diffStart >= 0) {
                //分派外聘，形式审查中
                this.isAssign = this.awardId.equals(EnumAwardType.SCIENCE.getAwrdType() + "") ||
                                this.awardId.equals(EnumAwardType.QC.getAwrdType() + "") ||
                                this.awardId.equals(EnumAwardType.SURVER.getAwrdType() + "");
                this.taskStatStr = "形式审查";
                return;
            }
            if(StringUtils.isBlank(this.expertStartTime) && StringUtils.isBlank(this.expertStartTimeSecond)) {
                this.isScore = false;
                this.isScoreOver = true;
                this.taskStatStr = "完成";
                return;
            }
        }

        if (StringUtils.isNotBlank(this.expertStartTime)) {
            diff = DateUtils.diffNow(this.expertStartTime);
            String prefixStr = StringUtils.isNotBlank(this.expertStartTimeSecond) ? "第一阶段" : "";
            if (diff < 0) {
                this.isSpecialAdmin = true;
                this.taskStatStr = prefixStr + "分派专家";
                return;
            } else {
                diff = DateUtils.diffNow(this.expertEndTime);
                if (diff < 0) {
                    this.taskStatStr = prefixStr + "专家打分";
                    this.isScore = true;
                    return;
                } else {
                    this.isScore = false;
                    this.isScoreOver = true;
                    this.taskStatStr = "完成";
                    return;
                }
            }
        }

        if (StringUtils.isNotBlank(this.expertStartTimeSecond)) {
            diff = DateUtils.diffNow(this.expertStartTimeSecond);
            if (diff < 0) {
                this.isSpecialAdmin = true;
                this.taskStatStr = "第二阶段分派专家";
                return;
            } else {
                diff = DateUtils.diffNow(this.expertEndTimeSecond);
                if (diff < 0) {
                    this.taskStatStr = "第二阶段专家打分";
                    this.isScore = true;
                    return;
                } else {
                    this.isScore = false;
                    this.isScoreOver = true;
                    this.taskStatStr = "完成";
                    return;
                }
            }
        }
    }

    public boolean isApply() {
        return  "申请中".equals(this.taskStatStr);
    }
}
