package com.bootdo.cpe.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-03-30 23:24:20
 */
public class SurverBaseApplyTableInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//申报专业
	private String applyMajor;
	//项目名称
	private String proName;
	//工程建成时间
	private String proBuildTime;
	//工程起止时间
	private String proStartEnd;
	//验收部门
	private String acceptanceDepartment;
	//验收时间
	private String acceptanceTime;
	//任务来源
	private String taskResource;
	//计划编号
	private String planNumber;
	//视察起止时间
	private String surveyStartEnd;
	//勘察面积或线路长度
	private String surveyAreaLength;
	//主要勘察单位
	private String mainSurveyUnit;
	//写作单位
	private String cooperationUnit;
	//通讯地址
	private String mailingAddress;
	//邮政编码
	private String postCode;
	//单位联系人
	private String contactName;
	//电话号码
	private String contactPhone;
	//传真号码
	private String contactFox;
	//附件目录
	private String accessoriesCatalog;
	//工程勘查项目概况、工程勘察的难点及先进性
	private String generalAdvancedExploration;
	//曾获哪一级奖励
	private String awardReceivedLevel;
	//申报单位意见
	private String reportingUnitOpinion;
	//上级主管部门 （或建设单位） 推荐意见
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
	 * 设置：申报专业
	 */
	public void setApplyMajor(String applyMajor) {
		this.applyMajor = applyMajor;
	}
	/**
	 * 获取：申报专业
	 */
	public String getApplyMajor() {
		return applyMajor;
	}
	/**
	 * 设置：项目名称
	 */
	public void setProName(String proName) {
		this.proName = proName;
	}
	/**
	 * 获取：项目名称
	 */
	public String getProName() {
		return proName;
	}
	/**
	 * 设置：工程建成时间
	 */
	public void setProBuildTime(String proBuildTime) {
		this.proBuildTime = proBuildTime;
	}
	/**
	 * 获取：工程建成时间
	 */
	public String getProBuildTime() {
		return proBuildTime;
	}
	/**
	 * 设置：工程起止时间
	 */
	public void setProStartEnd(String proStartEnd) {
		this.proStartEnd = proStartEnd;
	}
	/**
	 * 获取：工程起止时间
	 */
	public String getProStartEnd() {
		return proStartEnd;
	}
	/**
	 * 设置：验收部门
	 */
	public void setAcceptanceDepartment(String acceptanceDepartment) {
		this.acceptanceDepartment = acceptanceDepartment;
	}
	/**
	 * 获取：验收部门
	 */
	public String getAcceptanceDepartment() {
		return acceptanceDepartment;
	}
	/**
	 * 设置：验收时间
	 */
	public void setAcceptanceTime(String acceptanceTime) {
		this.acceptanceTime = acceptanceTime;
	}
	/**
	 * 获取：验收时间
	 */
	public String getAcceptanceTime() {
		return acceptanceTime;
	}
	/**
	 * 设置：任务来源
	 */
	public void setTaskResource(String taskResource) {
		this.taskResource = taskResource;
	}
	/**
	 * 获取：任务来源
	 */
	public String getTaskResource() {
		return taskResource;
	}
	/**
	 * 设置：计划编号
	 */
	public void setPlanNumber(String planNumber) {
		this.planNumber = planNumber;
	}
	/**
	 * 获取：计划编号
	 */
	public String getPlanNumber() {
		return planNumber;
	}
	/**
	 * 设置：视察起止时间
	 */
	public void setSurveyStartEnd(String surveyStartEnd) {
		this.surveyStartEnd = surveyStartEnd;
	}
	/**
	 * 获取：视察起止时间
	 */
	public String getSurveyStartEnd() {
		return surveyStartEnd;
	}
	/**
	 * 设置：勘察面积或线路长度
	 */
	public void setSurveyAreaLength(String surveyAreaLength) {
		this.surveyAreaLength = surveyAreaLength;
	}
	/**
	 * 获取：勘察面积或线路长度
	 */
	public String getSurveyAreaLength() {
		return surveyAreaLength;
	}
	/**
	 * 设置：主要勘察单位
	 */
	public void setMainSurveyUnit(String mainSurveyUnit) {
		this.mainSurveyUnit = mainSurveyUnit;
	}
	/**
	 * 获取：主要勘察单位
	 */
	public String getMainSurveyUnit() {
		return mainSurveyUnit;
	}
	/**
	 * 设置：写作单位
	 */
	public void setCooperationUnit(String cooperationUnit) {
		this.cooperationUnit = cooperationUnit;
	}
	/**
	 * 获取：写作单位
	 */
	public String getCooperationUnit() {
		return cooperationUnit;
	}
	/**
	 * 设置：通讯地址
	 */
	public void setMailingAddress(String mailingAddress) {
		this.mailingAddress = mailingAddress;
	}
	/**
	 * 获取：通讯地址
	 */
	public String getMailingAddress() {
		return mailingAddress;
	}
	/**
	 * 设置：邮政编码
	 */
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	/**
	 * 获取：邮政编码
	 */
	public String getPostCode() {
		return postCode;
	}
	/**
	 * 设置：单位联系人
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	/**
	 * 获取：单位联系人
	 */
	public String getContactName() {
		return contactName;
	}
	/**
	 * 设置：电话号码
	 */
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	/**
	 * 获取：电话号码
	 */
	public String getContactPhone() {
		return contactPhone;
	}
	/**
	 * 设置：传真号码
	 */
	public void setContactFox(String contactFox) {
		this.contactFox = contactFox;
	}
	/**
	 * 获取：传真号码
	 */
	public String getContactFox() {
		return contactFox;
	}
	/**
	 * 设置：附件目录
	 */
	public void setAccessoriesCatalog(String accessoriesCatalog) {
		this.accessoriesCatalog = accessoriesCatalog;
	}
	/**
	 * 获取：附件目录
	 */
	public String getAccessoriesCatalog() {
		return accessoriesCatalog;
	}
	/**
	 * 设置：工程勘查项目概况、工程勘察的难点及先进性
	 */
	public void setGeneralAdvancedExploration(String generalAdvancedExploration) {
		this.generalAdvancedExploration = generalAdvancedExploration;
	}
	/**
	 * 获取：工程勘查项目概况、工程勘察的难点及先进性
	 */
	public String getGeneralAdvancedExploration() {
		return generalAdvancedExploration;
	}
	/**
	 * 设置：曾获哪一级奖励
	 */
	public void setAwardReceivedLevel(String awardReceivedLevel) {
		this.awardReceivedLevel = awardReceivedLevel;
	}
	/**
	 * 获取：曾获哪一级奖励
	 */
	public String getAwardReceivedLevel() {
		return awardReceivedLevel;
	}
	/**
	 * 设置：申报单位意见
	 */
	public void setReportingUnitOpinion(String reportingUnitOpinion) {
		this.reportingUnitOpinion = reportingUnitOpinion;
	}
	/**
	 * 获取：申报单位意见
	 */
	public String getReportingUnitOpinion() {
		return reportingUnitOpinion;
	}
	/**
	 * 设置：上级主管部门 （或建设单位） 推荐意见
	 */
	public void setUpRecommendation(String upRecommendation) {
		this.upRecommendation = upRecommendation;
	}
	/**
	 * 获取：上级主管部门 （或建设单位） 推荐意见
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
}
