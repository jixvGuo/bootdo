package com.bootdo.cpe.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 勘察类审查表格
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-04-13 07:07:29
 */
public class SurverReviewSurverResultDO extends SurverReviewProBaseInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	private Integer id;
	//
	private String numCopies;
	//是否申报单位承担
	private String applyUnitIsBear;
	//是否局级一二等奖
	private String bureauIsFirstAward;
	//上级意见及盖章
	private String superiorIsOpionion;
	//建设单位证明的完成时间（勘察、地质、岩土/岩土工程设计、治理）（结构主体工程完成一年以上/地下工程竣工后经一年以上）
	private String unitBuildIsCompleteTime;
	//交（竣）工验收证明（上级主管部门加盖公章）
	private String completeIsCert;
	//单位三年无事故安全证明
	private String unitIsThreeSafe;
	//申报材料存在问题
	private String applySourceQuestion;
	//问题回馈情况
	private String feedbackInfo;
	//项目联系人及电话
	private String proContact;
	//主要完成人
	private String mainUser;
	//规划建设方验收证明（测量）
	private String planIsCert;
	//地下水开采经一年以上观测资料验证（水文地质勘察）
	private String groundwaterIsOneYear;
	//勘察资质
	private String surverIsCert;
	//合同
	private String contractIsCert;
	//上级或建设方评价证明
	private String superiorIsEvaluate;
	//查新报告（报一等奖）
	private String viewNewIsCert;
	//技术审定文件（新技术、新材料）
	private String technologyIsFile;
	//水资源评价（水文地质勘察项目）
	private String waterIsCert;
	//工程勘察图片、照片
	private String surverIsPhoto;
	//勘察报告/测量报告
	private String surverIsReport;
	//需要时提供项目和新技术有关音像制品
	private String audioIsNeed;
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
	 * 设置：建设单位证明的完成时间（勘察、地质、岩土/岩土工程设计、治理）（结构主体工程完成一年以上/地下工程竣工后经一年以上）
	 */
	public void setUnitBuildIsCompleteTime(String unitBuildIsCompleteTime) {
		this.unitBuildIsCompleteTime = unitBuildIsCompleteTime;
	}
	/**
	 * 获取：建设单位证明的完成时间（勘察、地质、岩土/岩土工程设计、治理）（结构主体工程完成一年以上/地下工程竣工后经一年以上）
	 */
	public String getUnitBuildIsCompleteTime() {
		return unitBuildIsCompleteTime;
	}
	/**
	 * 设置：交（竣）工验收证明（上级主管部门加盖公章）
	 */
	public void setCompleteIsCert(String completeIsCert) {
		this.completeIsCert = completeIsCert;
	}
	/**
	 * 获取：交（竣）工验收证明（上级主管部门加盖公章）
	 */
	public String getCompleteIsCert() {
		return completeIsCert;
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
	 * 设置：规划建设方验收证明（测量）
	 */
	public void setPlanIsCert(String planIsCert) {
		this.planIsCert = planIsCert;
	}
	/**
	 * 获取：规划建设方验收证明（测量）
	 */
	public String getPlanIsCert() {
		return planIsCert;
	}
	/**
	 * 设置：地下水开采经一年以上观测资料验证（水文地质勘察）
	 */
	public void setGroundwaterIsOneYear(String groundwaterIsOneYear) {
		this.groundwaterIsOneYear = groundwaterIsOneYear;
	}
	/**
	 * 获取：地下水开采经一年以上观测资料验证（水文地质勘察）
	 */
	public String getGroundwaterIsOneYear() {
		return groundwaterIsOneYear;
	}
	/**
	 * 设置：勘察资质
	 */
	public void setSurverIsCert(String surverIsCert) {
		this.surverIsCert = surverIsCert;
	}
	/**
	 * 获取：勘察资质
	 */
	public String getSurverIsCert() {
		return surverIsCert;
	}
	/**
	 * 设置：合同
	 */
	public void setContractIsCert(String contractIsCert) {
		this.contractIsCert = contractIsCert;
	}
	/**
	 * 获取：合同
	 */
	public String getContractIsCert() {
		return contractIsCert;
	}
	/**
	 * 设置：上级或建设方评价证明
	 */
	public void setSuperiorIsEvaluate(String superiorIsEvaluate) {
		this.superiorIsEvaluate = superiorIsEvaluate;
	}
	/**
	 * 获取：上级或建设方评价证明
	 */
	public String getSuperiorIsEvaluate() {
		return superiorIsEvaluate;
	}
	/**
	 * 设置：查新报告（报一等奖）
	 */
	public void setViewNewIsCert(String viewNewIsCert) {
		this.viewNewIsCert = viewNewIsCert;
	}
	/**
	 * 获取：查新报告（报一等奖）
	 */
	public String getViewNewIsCert() {
		return viewNewIsCert;
	}
	/**
	 * 设置：技术审定文件（新技术、新材料）
	 */
	public void setTechnologyIsFile(String technologyIsFile) {
		this.technologyIsFile = technologyIsFile;
	}
	/**
	 * 获取：技术审定文件（新技术、新材料）
	 */
	public String getTechnologyIsFile() {
		return technologyIsFile;
	}
	/**
	 * 设置：水资源评价（水文地质勘察项目）
	 */
	public void setWaterIsCert(String waterIsCert) {
		this.waterIsCert = waterIsCert;
	}
	/**
	 * 获取：水资源评价（水文地质勘察项目）
	 */
	public String getWaterIsCert() {
		return waterIsCert;
	}
	/**
	 * 设置：工程勘察图片、照片
	 */
	public void setSurverIsPhoto(String surverIsPhoto) {
		this.surverIsPhoto = surverIsPhoto;
	}
	/**
	 * 获取：工程勘察图片、照片
	 */
	public String getSurverIsPhoto() {
		return surverIsPhoto;
	}
	/**
	 * 设置：勘察报告/测量报告
	 */
	public void setSurverIsReport(String surverIsReport) {
		this.surverIsReport = surverIsReport;
	}
	/**
	 * 获取：勘察报告/测量报告
	 */
	public String getSurverIsReport() {
		return surverIsReport;
	}
	/**
	 * 设置：需要时提供项目和新技术有关音像制品
	 */
	public void setAudioIsNeed(String audioIsNeed) {
		this.audioIsNeed = audioIsNeed;
	}
	/**
	 * 获取：需要时提供项目和新技术有关音像制品
	 */
	public String getAudioIsNeed() {
		return audioIsNeed;
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
