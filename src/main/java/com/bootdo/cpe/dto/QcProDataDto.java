package com.bootdo.cpe.dto;

import com.bootdo.cpe.domain.QcGroupApplyInfoDO;

/**
 * QC奖项目数据信息
 *
 * @author houzb
 * @version 1.0
 * @date 2022-02-05 10:34
 */
public class QcProDataDto extends QcGroupApplyInfoDO {
    /**
     * 填报的账号
     */
    private String optAccount;
    /**
     * 项目编号
     */
    private String proCode;

    /**
     * 项目类别
     */
    private String proType = "QC";

    /**
     * 单位名称
     */
    private String companyName;

    /**
     * 小组成员，多个用逗号隔开
     */
    private String groupMember;

    /**
     * 最新形审结果：参评/完善后参评/缓评/不评
     */
    private String latestReviewResult;

    /**
     * 最新形审评语
     */
    private String latestReviewComment;

    /**
     * 最新形审时间
     */
    private String latestReviewTime;

    /**
     * 任务阶段编码：WAIT_APPLY/APPLYING/CHECKING/CHECK_END
     */
    private String taskStageCode;

    /**
     * 任务阶段名称：等待申请/申请中/形式审查/形审结束
     */
    private String taskStageName;

    public String getTaskStageCode() {
        return taskStageCode;
    }

    public void setTaskStageCode(String taskStageCode) {
        this.taskStageCode = taskStageCode;
    }

    public String getTaskStageName() {
        return taskStageName;
    }

    public void setTaskStageName(String taskStageName) {
        this.taskStageName = taskStageName;
    }

    public String getLatestReviewResult() {
        return latestReviewResult;
    }

    public void setLatestReviewResult(String latestReviewResult) {
        this.latestReviewResult = latestReviewResult;
    }

    public String getLatestReviewComment() {
        return latestReviewComment;
    }

    public void setLatestReviewComment(String latestReviewComment) {
        this.latestReviewComment = latestReviewComment;
    }

    public String getLatestReviewTime() {
        return latestReviewTime;
    }

    public void setLatestReviewTime(String latestReviewTime) {
        this.latestReviewTime = latestReviewTime;
    }

    public String getGroupMember() {
        return groupMember;
    }

    public void setGroupMember(String groupMember) {
        this.groupMember = groupMember;
    }

    public String getOptAccount() {
        return optAccount;
    }

    public void setOptAccount(String optAccount) {
        this.optAccount = optAccount;
    }

    public String getProCode() {
        return proCode;
    }

    public void setProCode(String proCode) {
        this.proCode = proCode;
    }

    public String getProType() {
        return proType;
    }

    public void setProType(String proType) {
        this.proType = proType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

}
