package com.bootdo.cpe.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 石油工程建设优秀质量管理小组报告单（创新型课题）
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-01-31 16:01:32
 */
public class QcReportInnovateInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	private Integer id;
	//
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

	private String groupDescNo;

	//选择课题
	private String selTopic;

	private String selTopicNo;

	//设定目标及目标可行性论证
	private String targetProve;
	private String targetProveNo;

	//提出方案并确定最佳方案
	private String planDesc;

	private String planDescNo;

	//制定对策
	private String strategyDesc;
	private String strategyDescNo;

	//对策实施
	private String strategyImplement;
	private String strategyImplementNo;

	//效果检查
	private String effectCheck;
	private String effectCheckNo;

	//标准化
	private String standardDesc;
	private String standardDescNo = "";

	//总结和下一步打算
	private String sumPlan;
	private String sumPlanNo ="" ;

	//
	private Date created;
	//
	private Date updated;
	//
	private Integer deleted;

	public String getGroupDescNo() {
		return groupDescNo;
	}

	public void setGroupDescNo(String groupDescNo) {
		this.groupDescNo = groupDescNo;
	}

	public String getSelTopicNo() {
		return selTopicNo;
	}

	public void setSelTopicNo(String selTopicNo) {
		this.selTopicNo = selTopicNo;
	}

	public String getTargetProveNo() {
		return targetProveNo;
	}

	public void setTargetProveNo(String targetProveNo) {
		this.targetProveNo = targetProveNo;
	}

	public String getPlanDescNo() {
		return planDescNo;
	}

	public void setPlanDescNo(String planDescNo) {
		this.planDescNo = planDescNo;
	}

	public String getStrategyDescNo() {
		return strategyDescNo;
	}

	public void setStrategyDescNo(String strategyDescNo) {
		this.strategyDescNo = strategyDescNo;
	}

	public String getStrategyImplementNo() {
		return strategyImplementNo;
	}

	public void setStrategyImplementNo(String strategyImplementNo) {
		this.strategyImplementNo = strategyImplementNo;
	}

	public String getEffectCheckNo() {
		return effectCheckNo;
	}

	public void setEffectCheckNo(String effectCheckNo) {
		this.effectCheckNo = effectCheckNo;
	}

	public String getStandardDescNo() {
		return standardDescNo;
	}

	public void setStandardDescNo(String standardDescNo) {
		this.standardDescNo = standardDescNo;
	}

	public String getSumPlanNo() {
		return sumPlanNo;
	}

	public void setSumPlanNo(String sumPlanNo) {
		this.sumPlanNo = sumPlanNo;
	}

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
	/**
	 * 设置：
	 */
	public void setOptUid(Integer optUid) {
		this.optUid = optUid;
	}
	/**
	 * 获取：
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
	 * 设置：选择课题
	 */
	public void setSelTopic(String selTopic) {
		this.selTopic = selTopic;
		this.selTopicNo = this.selTopic.replaceAll("\\<.*?>","");
	}
	/**
	 * 获取：选择课题
	 */
	public String getSelTopic() {
		return selTopic;
	}
	/**
	 * 设置：设定目标及目标可行性论证
	 */
	public void setTargetProve(String targetProve) {
		this.targetProve = targetProve;
		this.targetProveNo = this.targetProve.replaceAll("\\<.*?>","");
	}
	/**
	 * 获取：设定目标及目标可行性论证
	 */
	public String getTargetProve() {
		return targetProve;
	}
	/**
	 * 设置：提出方案并确定最佳方案
	 */
	public void setPlanDesc(String planDesc) {
		this.planDesc = planDesc;
		this.planDescNo = this.planDesc.replaceAll("\\<.*?>","");
	}
	/**
	 * 获取：提出方案并确定最佳方案
	 */
	public String getPlanDesc() {
		return planDesc;
	}
	/**
	 * 设置：制定对策
	 */
	public void setStrategyDesc(String strategyDesc) {
		this.strategyDesc = strategyDesc;
		this.strategyDescNo = this.strategyDesc.replaceAll("\\<.*?>","");
	}
	/**
	 * 获取：制定对策
	 */
	public String getStrategyDesc() {
		return strategyDesc;
	}
	/**
	 * 设置：对策实施
	 */
	public void setStrategyImplement(String strategyImplement) {
		this.strategyImplement = strategyImplement;
		this.strategyImplementNo = this.strategyImplement.replaceAll("\\<.*?>","");
	}
	/**
	 * 获取：对策实施
	 */
	public String getStrategyImplement() {
		return strategyImplement;
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
	 * 设置：标准化
	 */
	public void setStandardDesc(String standardDesc) {
		this.standardDesc = standardDesc;
		this.standardDescNo = this.standardDesc.replaceAll("\\<.*?>","");
	}
	/**
	 * 获取：标准化
	 */
	public String getStandardDesc() {
		return standardDesc;
	}
	/**
	 * 设置：总结和下一步打算
	 */
	public void setSumPlan(String sumPlan) {
		this.sumPlan = sumPlan;
		this.sumPlanNo = this.sumPlan.replaceAll("\\<.*?>","");
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
	 * 设置：
	 */
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
	/**
	 * 获取：
	 */
	public Integer getDeleted() {
		return deleted;
	}
}
