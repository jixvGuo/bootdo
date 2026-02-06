package com.bootdo.cpe.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 石油工程建设优秀质量管理小组报告单（问题解决型课题）
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-02-06 21:58:24
 */
public class QcReportSolveInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	private Integer id;
	//填写的用户id
	private Integer optUid;
	//
	private Integer proId;
	//
	private String taskId;
	//
	private String applyId;
	//小组名称
	private String groupName;
	//课题名称
	private String topicName;
	//课题类型
	private String topicType;
	//小组简介
	private String groupDesc;

	private String groupDescNo = "";

	//选择课题理由
	private String selReason;

	private String selReasonNo;

	//现状调查/设定目标
	private String investigation;

	private String investigationNo;

	//设定目标/目标可行性论证
	private String feasibilityDemonstrate;

	private String feasibilityDemonstrateNo;

	//原因分析
	private String reasonAnalyze;

	private String reasonAnalyzeNo;

	//确定主要原因
	private String masterReason;

	private String masterReasonNo;

	//制定对策
	private String countermeasure;

	private String countermeasureNo;

	//对策实施
	private String countermeasureDeploy;

	private String countermeasureDeployNo;

	//主管局(公司、院)推荐意见
	private String recommend;
	private String recommendNo;

	//效果检查
	private String effectCheck;

	private String effectCheckNo ="" ;

	//制定巩固措施
	private String consolidationMeasures;

	private String consolidationMeasuresNo="";

	//总结和下一步打算
	private String sumPlan;
	private String sumPlanNo;

	public String getSumPlanNo() {
		return sumPlanNo;
	}

	public void setSumPlanNo(String sumPlanNo) {
		this.sumPlanNo = sumPlanNo;
	}

	//
	private Date created;
	//
	private Date updated;
	//是否删除，0未删除，1已删除
	private Integer deleted;

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}

	public String getGroupDescNo() {
		return groupDescNo;
	}

	public void setGroupDescNo(String groupDescNo) {
		this.groupDescNo = groupDescNo;
	}

	public String getSelReasonNo() {
		return selReasonNo;
	}

	public void setSelReasonNo(String selReasonNo) {
		this.selReasonNo = selReasonNo;
	}

	public String getInvestigationNo() {
		return investigationNo;
	}

	public void setInvestigationNo(String investigationNo) {
		this.investigationNo = investigationNo;
	}

	public String getFeasibilityDemonstrateNo() {
		return feasibilityDemonstrateNo;
	}

	public void setFeasibilityDemonstrateNo(String feasibilityDemonstrateNo) {
		this.feasibilityDemonstrateNo = feasibilityDemonstrateNo;
	}

	public String getReasonAnalyzeNo() {
		return reasonAnalyzeNo;
	}

	public void setReasonAnalyzeNo(String reasonAnalyzeNo) {
		this.reasonAnalyzeNo = reasonAnalyzeNo;
	}

	public String getMasterReasonNo() {
		return masterReasonNo;
	}

	public void setMasterReasonNo(String masterReasonNo) {
		this.masterReasonNo = masterReasonNo;
	}

	public String getCountermeasureNo() {
		return countermeasureNo;
	}

	public void setCountermeasureNo(String countermeasureNo) {
		this.countermeasureNo = countermeasureNo;
	}

	public String getCountermeasureDeployNo() {
		return countermeasureDeployNo;
	}

	public void setCountermeasureDeployNo(String countermeasureDeployNo) {
		this.countermeasureDeployNo = countermeasureDeployNo;
	}

	public String getRecommendNo() {
		return recommendNo;
	}

	public void setRecommendNo(String recommendNo) {
		this.recommendNo = recommendNo;
	}

	public String getEffectCheckNo() {
		return effectCheckNo;
	}

	public void setEffectCheckNo(String effectCheckNo) {
		this.effectCheckNo = effectCheckNo;
	}

	public String getConsolidationMeasuresNo() {
		return consolidationMeasuresNo;
	}

	public void setConsolidationMeasuresNo(String consolidationMeasuresNo) {
		this.consolidationMeasuresNo = consolidationMeasuresNo;
	}

	/**
	 * 设置：填写的用户id
	 */
	public void setOptUid(Integer optUid) {
		this.optUid = optUid;
	}
	/**
	 * 获取：填写的用户id
	 */
	public Integer getOptUid() {
		return optUid;
	}
	/**
	 * 设置：
	 */
	public void setProId(Integer proId) {
		this.proId = proId;
	}
	/**
	 * 获取：
	 */
	public Integer getProId() {
		return proId;
	}
	/**
	 * 设置：
	 */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	/**
	 * 获取：
	 */
	public String getTaskId() {
		return taskId;
	}
	/**
	 * 设置：
	 */
	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
	/**
	 * 获取：
	 */
	public String getApplyId() {
		return applyId;
	}
	/**
	 * 设置：小组名称
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	/**
	 * 获取：小组名称
	 */
	public String getGroupName() {
		return groupName;
	}
	/**
	 * 设置：课题名称
	 */
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	/**
	 * 获取：课题名称
	 */
	public String getTopicName() {
		return topicName;
	}
	/**
	 * 设置：课题类型
	 */
	public void setTopicType(String topicType) {
		this.topicType = topicType;
	}
	/**
	 * 获取：课题类型
	 */
	public String getTopicType() {
		return topicType;
	}
	/**
	 * 设置：小组简介
	 */
	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
		this.groupDescNo = this.groupDesc.replaceAll("\\<.*?>","");
	}
	/**
	 * 获取：小组简介
	 */
	public String getGroupDesc() {
		return groupDesc;
	}
	/**
	 * 设置：选择课题理由
	 */
	public void setSelReason(String selReason) {
		this.selReason = selReason;
		this.selReasonNo = this.selReason.replaceAll("\\<.*?>","");
	}
	/**
	 * 获取：选择课题理由
	 */
	public String getSelReason() {
		return selReason;
	}
	/**
	 * 设置：现状调查/设定目标
	 */
	public void setInvestigation(String investigation) {
		this.investigation = investigation;
		this.investigationNo = this.investigation.replaceAll("\\<.*?>","");

	}
	/**
	 * 获取：现状调查/设定目标
	 */
	public String getInvestigation() {
		return investigation;
	}
	/**
	 * 设置：设定目标/目标可行性论证
	 */
	public void setFeasibilityDemonstrate(String feasibilityDemonstrate) {
		this.feasibilityDemonstrate = feasibilityDemonstrate;
		this.feasibilityDemonstrateNo = this.feasibilityDemonstrate.replaceAll("\\<.*?>","");
	}
	/**
	 * 获取：设定目标/目标可行性论证
	 */
	public String getFeasibilityDemonstrate() {
		return feasibilityDemonstrate;
	}
	/**
	 * 设置：原因分析
	 */
	public void setReasonAnalyze(String reasonAnalyze) {
		this.reasonAnalyze = reasonAnalyze;
		this.reasonAnalyzeNo = this.reasonAnalyze.replaceAll("\\<.*?>","");
	}
	/**
	 * 获取：原因分析
	 */
	public String getReasonAnalyze() {
		return reasonAnalyze;
	}
	/**
	 * 设置：确定主要原因
	 */
	public void setMasterReason(String masterReason) {
		this.masterReason = masterReason;
		this.masterReasonNo = this.masterReason.replaceAll("\\<.*?>","");
	}
	/**
	 * 获取：确定主要原因
	 */
	public String getMasterReason() {
		return masterReason;
	}
	/**
	 * 设置：制定对策
	 */
	public void setCountermeasure(String countermeasure) {
		this.countermeasure = countermeasure;
		this.countermeasureNo =  this.countermeasure.replaceAll("\\<.*?>","");
	}
	/**
	 * 获取：制定对策
	 */
	public String getCountermeasure() {
		return countermeasure;
	}
	/**
	 * 设置：对策实施
	 */
	public void setCountermeasureDeploy(String countermeasureDeploy) {
		this.countermeasureDeploy = countermeasureDeploy;
		this.countermeasureDeployNo = this.countermeasureDeploy.replaceAll("\\<.*?>","");
	}
	/**
	 * 获取：对策实施
	 */
	public String getCountermeasureDeploy() {
		return countermeasureDeploy;
	}
	/**
	 * 设置：主管局(公司、院)推荐意见
	 */
	public void setRecommend(String recommend) {
		this.recommend = recommend;
		this.recommendNo = this.recommend.replaceAll("\\<.*?>","");
	}
	/**
	 * 获取：主管局(公司、院)推荐意见
	 */
	public String getRecommend() {
		return recommend;
	}
	/**
	 * 设置：效果检查
	 */
	public void setEffectCheck(String effectCheck) {
		this.effectCheck = effectCheck;
		this.effectCheckNo = this.effectCheck.replaceAll("\\<.*?>","");
	}
	/**
	 * 获取：效果检查
	 */
	public String getEffectCheck() {
		return effectCheck;
	}
	/**
	 * 设置：制定巩固措施
	 */
	public void setConsolidationMeasures(String consolidationMeasures) {
		this.consolidationMeasures = consolidationMeasures;

		this.consolidationMeasuresNo = this.consolidationMeasures.replaceAll("\\<.*?>","");
	}
	/**
	 * 获取：制定巩固措施
	 */
	public String getConsolidationMeasures() {
		return consolidationMeasures;
	}
	/**
	 * 设置：总结和下一步打算
	 */
	public void setSumPlan(String sumPlan) {
		this.sumPlan = sumPlan;
		this.sumPlanNo =  this.sumPlan.replaceAll("\\<.*?>","");
	}
	/**
	 * 获取：总结和下一步打算
	 */
	public String getSumPlan() {
		return sumPlan;
	}
	/**
	 * 设置：
	 */
	public void setCreated(Date created) {
		this.created = created;
	}
	/**
	 * 获取：
	 */
	public Date getCreated() {
		return created;
	}
	/**
	 * 设置：
	 */
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	/**
	 * 获取：
	 */
	public Date getUpdated() {
		return updated;
	}
	/**
	 * 设置：是否删除，0未删除，1已删除
	 */
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
	/**
	 * 获取：是否删除，0未删除，1已删除
	 */
	public Integer getDeleted() {
		return deleted;
	}
}
