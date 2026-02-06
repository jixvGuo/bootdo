package com.bootdo.system.domain;

import java.io.Serializable;
import java.util.Date;



/**
 *
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-03-27 21:44:17
 */
public class SurverExcellentApplyTableInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	private Integer id;
	//
	private String applyMajor;
	//
	private String proName;
	//
	private String proBuildTime;
	//
	private String proStartEnd;
	//
	private String acceptanceTime;
	//
	private String acceptanceDepartment;
	//
	private String planNumber;
	//
	private String surveyStartEnd;
	//
	private String surveyAreaLength;
	//
	private String mainSurveyUnit;
	//
	private String cooperationUnit;
	//
	private String mailingAddress;
	//
	private String postCode;
	//
	private String contactPhone;
	//
	private String contactName;
	//
	private String contactFox;
	//
	private String accessoriesCatalog;
	//
	private String generalAdvancedExploration;
	//
	private String awardReceivedLevel;
	//
	private String reportingUnitOpinion;
	//
	private String upRecommendation;
	//
	private Integer optUid;
	//
	private Integer proId;
	//
	private String taskId;
	//
	private Date created;
	//
	private Date updated;
	//
	private Integer deleted;

	private String buildScope;

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
	public void setApplyMajor(String applyMajor) {
		this.applyMajor = applyMajor;
	}
	/**
	 * 获取：
	 */
	public String getApplyMajor() {
		return applyMajor;
	}
	/**
	 * 设置：
	 */
	public void setProName(String proName) {
		this.proName = proName;
	}
	/**
	 * 获取：
	 */
	public String getProName() {
		return proName;
	}
	/**
	 * 设置：
	 */
	public void setProBuildTime(String proBuildTime) {
		this.proBuildTime = proBuildTime;
	}
	/**
	 * 获取：
	 */
	public String getProBuildTime() {
		return proBuildTime;
	}
	/**
	 * 设置：
	 */
	public void setProStartEnd(String proStartEnd) {
		this.proStartEnd = proStartEnd;
	}
	/**
	 * 获取：
	 */
	public String getProStartEnd() {
		return proStartEnd;
	}
	/**
	 * 设置：
	 */
	public void setAcceptanceTime(String acceptanceTime) {
		this.acceptanceTime = acceptanceTime;
	}
	/**
	 * 获取：
	 */
	public String getAcceptanceTime() {
		return acceptanceTime;
	}
	/**
	 * 设置：
	 */
	public void setAcceptanceDepartment(String acceptanceDepartment) {
		this.acceptanceDepartment = acceptanceDepartment;
	}
	/**
	 * 获取：
	 */
	public String getAcceptanceDepartment() {
		return acceptanceDepartment;
	}
	/**
	 * 设置：
	 */
	public void setPlanNumber(String planNumber) {
		this.planNumber = planNumber;
	}
	/**
	 * 获取：
	 */
	public String getPlanNumber() {
		return planNumber;
	}
	/**
	 * 设置：
	 */
	public void setSurveyStartEnd(String surveyStartEnd) {
		this.surveyStartEnd = surveyStartEnd;
	}
	/**
	 * 获取：
	 */
	public String getSurveyStartEnd() {
		return surveyStartEnd;
	}
	/**
	 * 设置：
	 */
	public void setSurveyAreaLength(String surveyAreaLength) {
		this.surveyAreaLength = surveyAreaLength;
	}
	/**
	 * 获取：
	 */
	public String getSurveyAreaLength() {
		return surveyAreaLength;
	}
	/**
	 * 设置：
	 */
	public void setMainSurveyUnit(String mainSurveyUnit) {
		this.mainSurveyUnit = mainSurveyUnit;
	}
	/**
	 * 获取：
	 */
	public String getMainSurveyUnit() {
		return mainSurveyUnit;
	}
	/**
	 * 设置：
	 */
	public void setCooperationUnit(String cooperationUnit) {
		this.cooperationUnit = cooperationUnit;
	}
	/**
	 * 获取：
	 */
	public String getCooperationUnit() {
		return cooperationUnit;
	}
	/**
	 * 设置：
	 */
	public void setMailingAddress(String mailingAddress) {
		this.mailingAddress = mailingAddress;
	}
	/**
	 * 获取：
	 */
	public String getMailingAddress() {
		return mailingAddress;
	}
	/**
	 * 设置：
	 */
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	/**
	 * 获取：
	 */
	public String getPostCode() {
		return postCode;
	}
	/**
	 * 设置：
	 */
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	/**
	 * 获取：
	 */
	public String getContactPhone() {
		return contactPhone;
	}
	/**
	 * 设置：
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	/**
	 * 获取：
	 */
	public String getContactName() {
		return contactName;
	}
	/**
	 * 设置：
	 */
	public void setContactFox(String contactFox) {
		this.contactFox = contactFox;
	}
	/**
	 * 获取：
	 */
	public String getContactFox() {
		return contactFox;
	}
	/**
	 * 设置：
	 */
	public void setAccessoriesCatalog(String accessoriesCatalog) {
		this.accessoriesCatalog = accessoriesCatalog;
	}
	/**
	 * 获取：
	 */
	public String getAccessoriesCatalog() {
		return accessoriesCatalog;
	}
	/**
	 * 设置：
	 */
	public void setGeneralAdvancedExploration(String generalAdvancedExploration) {
		this.generalAdvancedExploration = generalAdvancedExploration;
	}
	/**
	 * 获取：
	 */
	public String getGeneralAdvancedExploration() {
		return generalAdvancedExploration;
	}
	/**
	 * 设置：
	 */
	public void setAwardReceivedLevel(String awardReceivedLevel) {
		this.awardReceivedLevel = awardReceivedLevel;
	}
	/**
	 * 获取：
	 */
	public String getAwardReceivedLevel() {
		return awardReceivedLevel;
	}
	/**
	 * 设置：
	 */
	public void setReportingUnitOpinion(String reportingUnitOpinion) {
		this.reportingUnitOpinion = reportingUnitOpinion;
	}
	/**
	 * 获取：
	 */
	public String getReportingUnitOpinion() {
		return reportingUnitOpinion;
	}
	/**
	 * 设置：
	 */
	public void setUpRecommendation(String upRecommendation) {
		this.upRecommendation = upRecommendation;
	}
	/**
	 * 获取：
	 */
	public String getUpRecommendation() {
		return upRecommendation;
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

	public String getBuildScope() {
		return buildScope;
	}

	public void setBuildScope(String buildScope) {
		this.buildScope = buildScope;
	}
}
