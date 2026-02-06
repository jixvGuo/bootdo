package com.bootdo.cpe.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 标准设计类审查表格
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-04-13 07:07:29
 */
public class SurverReviewStandardResultDO extends SurverReviewProBaseInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	private Integer id;
	//
	private String numCopies;
	//是否申报单位承担
	private String applyUnitIsBear;
	//经国家和省部级主管部门批准出版
	private String approveIsPublish;
	//设计、施工中使用满一年
	private String designIsYear;
	//设计资质
	private String designIsCert;
	//单位三年无事故安全证明
	private String unitIsThreeSafe;
	//是否局级一二等奖
	private String bureauIsFirstAward;
	//上级意见及盖章
	private String superiorIsOpionion;
	//申报材料存在问题
	private String applySourceQuestion;
	//问题回馈情况
	private String feedbackInfo;
	//项目联系人及电话
	private String proContact;
	//主要完成人
	private String mainUser;
	//标准设计图集和文字说明总结
	private String standardIsDesc;
	//主管部门批准立项及出版批文
	private String departmentIsApprove;
	//两个以上用户使用证明(加盖证明单位公章)
	private String twoUserIsUse;
	//经济效益证明(加盖证明单位公章)
	private String economicIsCert;
	//局级一、二等奖证书或文件
	private String bureauAwardIsCert;
	//
	private String reviewResult;
	//
	private String remarks;
	//
	private Integer optUid;
	//
	private String taskId;
	//
	private Date created;
	//
	private Date updated;

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
	public void setNumCopies(String numCopies) {
		this.numCopies = numCopies;
	}
	/**
	 * 获取：
	 */
	public String getNumCopies() {
		return numCopies;
	}
	/**
	 * 设置：是否申报单位承担
	 */
	public void setApplyUnitIsBear(String applyUnitIsBear) {
		this.applyUnitIsBear = applyUnitIsBear;
	}
	/**
	 * 获取：是否申报单位承担
	 */
	public String getApplyUnitIsBear() {
		return applyUnitIsBear;
	}

	public String getApproveIsPublish() {
		return approveIsPublish;
	}

	public void setApproveIsPublish(String approveIsPublish) {
		this.approveIsPublish = approveIsPublish;
	}

	/**
	 * 设置：设计、施工中使用满一年
	 */
	public void setDesignIsYear(String designIsYear) {
		this.designIsYear = designIsYear;
	}
	/**
	 * 获取：设计、施工中使用满一年
	 */
	public String getDesignIsYear() {
		return designIsYear;
	}
	/**
	 * 设置：设计资质
	 */
	public void setDesignIsCert(String designIsCert) {
		this.designIsCert = designIsCert;
	}
	/**
	 * 获取：设计资质
	 */
	public String getDesignIsCert() {
		return designIsCert;
	}
	/**
	 * 设置：单位三年无事故安全证明
	 */
	public void setUnitIsThreeSafe(String unitIsThreeSafe) {
		this.unitIsThreeSafe = unitIsThreeSafe;
	}
	/**
	 * 获取：单位三年无事故安全证明
	 */
	public String getUnitIsThreeSafe() {
		return unitIsThreeSafe;
	}
	/**
	 * 设置：是否局级一二等奖
	 */
	public void setBureauIsFirstAward(String bureauIsFirstAward) {
		this.bureauIsFirstAward = bureauIsFirstAward;
	}
	/**
	 * 获取：是否局级一二等奖
	 */
	public String getBureauIsFirstAward() {
		return bureauIsFirstAward;
	}
	/**
	 * 设置：上级意见及盖章
	 */
	public void setSuperiorIsOpionion(String superiorIsOpionion) {
		this.superiorIsOpionion = superiorIsOpionion;
	}
	/**
	 * 获取：上级意见及盖章
	 */
	public String getSuperiorIsOpionion() {
		return superiorIsOpionion;
	}
	/**
	 * 设置：申报材料存在问题
	 */
	public void setApplySourceQuestion(String applySourceQuestion) {
		this.applySourceQuestion = applySourceQuestion;
	}
	/**
	 * 获取：申报材料存在问题
	 */
	public String getApplySourceQuestion() {
		return applySourceQuestion;
	}
	/**
	 * 设置：问题回馈情况
	 */
	public void setFeedbackInfo(String feedbackInfo) {
		this.feedbackInfo = feedbackInfo;
	}
	/**
	 * 获取：问题回馈情况
	 */
	public String getFeedbackInfo() {
		return feedbackInfo;
	}
	/**
	 * 设置：项目联系人及电话
	 */
	public void setProContact(String proContact) {
		this.proContact = proContact;
	}
	/**
	 * 获取：项目联系人及电话
	 */
	public String getProContact() {
		return proContact;
	}
	/**
	 * 设置：主要完成人
	 */
	public void setMainUser(String mainUser) {
		this.mainUser = mainUser;
	}
	/**
	 * 获取：主要完成人
	 */
	public String getMainUser() {
		return mainUser;
	}
	/**
	 * 设置：标准设计图集和文字说明总结
	 */
	public void setStandardIsDesc(String standardIsDesc) {
		this.standardIsDesc = standardIsDesc;
	}
	/**
	 * 获取：标准设计图集和文字说明总结
	 */
	public String getStandardIsDesc() {
		return standardIsDesc;
	}
	/**
	 * 设置：主管部门批准立项及出版批文
	 */
	public void setDepartmentIsApprove(String departmentIsApprove) {
		this.departmentIsApprove = departmentIsApprove;
	}
	/**
	 * 获取：主管部门批准立项及出版批文
	 */
	public String getDepartmentIsApprove() {
		return departmentIsApprove;
	}
	/**
	 * 设置：两个以上用户使用证明(加盖证明单位公章)
	 */
	public void setTwoUserIsUse(String twoUserIsUse) {
		this.twoUserIsUse = twoUserIsUse;
	}
	/**
	 * 获取：两个以上用户使用证明(加盖证明单位公章)
	 */
	public String getTwoUserIsUse() {
		return twoUserIsUse;
	}
	/**
	 * 设置：经济效益证明(加盖证明单位公章)
	 */
	public void setEconomicIsCert(String economicIsCert) {
		this.economicIsCert = economicIsCert;
	}
	/**
	 * 获取：经济效益证明(加盖证明单位公章)
	 */
	public String getEconomicIsCert() {
		return economicIsCert;
	}
	/**
	 * 设置：局级一、二等奖证书或文件
	 */
	public void setBureauAwardIsCert(String bureauAwardIsCert) {
		this.bureauAwardIsCert = bureauAwardIsCert;
	}
	/**
	 * 获取：局级一、二等奖证书或文件
	 */
	public String getBureauAwardIsCert() {
		return bureauAwardIsCert;
	}
	/**
	 * 设置：
	 */
	public void setReviewResult(String reviewResult) {
		this.reviewResult = reviewResult;
	}
	/**
	 * 获取：
	 */
	public String getReviewResult() {
		return reviewResult;
	}
	/**
	 * 设置：
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * 获取：
	 */
	public String getRemarks() {
		return remarks;
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
}
