package com.bootdo.cpe.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-03-29 23:15:37
 */
public class SurverConsultApplyTableInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//申报专业	
	private String applyMajor;
	//项目名称
	private String proName;
	//项目起止时间
	private String proStartEnd;
	//审查及批复 
	private String reviewApproval;
	//投产时间
	private String productionTime;
	//应用时间
	private String applyTime;
	//验收部门
	private String acceptanceDepartment;
	//验收时间
	private String acceptanceTime;
	//  估（预）算
	private String estimate;
	// 设计概(预)
	private String designOverview;
	//超估（预）算的
	private String overestimated;
	//主要完成单位
	private String majorCompletionUnits;
	//协作单位 
	private String cooperationUnit;
	//电话/手机
	private String contactPhone;
	//申报单位联系人	
	private String contactName;
	//传真 
	private String contactFox;
	//附件目录
	private String accessoriesCatalog;
	//项目概况、难点及先进性
	private String projectOverview;
	//曾获奖励级别	
	private String rewardLevel;
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
	 * 设置：项目起止时间
	 */
	public void setProStartEnd(String proStartEnd) {
		this.proStartEnd = proStartEnd;
	}
	/**
	 * 获取：项目起止时间
	 */
	public String getProStartEnd() {
		return proStartEnd;
	}
	/**
	 * 设置：审查及批复 
	 */
	public void setReviewApproval(String reviewApproval) {
		this.reviewApproval = reviewApproval;
	}
	/**
	 * 获取：审查及批复 
	 */
	public String getReviewApproval() {
		return reviewApproval;
	}
	/**
	 * 设置：投产时间
	 */
	public void setProductionTime(String productionTime) {
		this.productionTime = productionTime;
	}
	/**
	 * 获取：投产时间
	 */
	public String getProductionTime() {
		return productionTime;
	}
	/**
	 * 设置：应用时间
	 */
	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}
	/**
	 * 获取：应用时间
	 */
	public String getApplyTime() {
		return applyTime;
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
	 * 设置：  估（预）算
	 */
	public void setEstimate(String estimate) {
		this.estimate = estimate;
	}
	/**
	 * 获取：  估（预）算
	 */
	public String getEstimate() {
		return estimate;
	}
	/**
	 * 设置： 设计概(预)
	 */
	public void setDesignOverview(String designOverview) {
		this.designOverview = designOverview;
	}
	/**
	 * 获取： 设计概(预)
	 */
	public String getDesignOverview() {
		return designOverview;
	}
	/**
	 * 设置：超估（预）算的
	 */
	public void setOverestimated(String overestimated) {
		this.overestimated = overestimated;
	}
	/**
	 * 获取：超估（预）算的
	 */
	public String getOverestimated() {
		return overestimated;
	}
	/**
	 * 设置：主要完成单位
	 */
	public void setMajorCompletionUnits(String majorCompletionUnits) {
		this.majorCompletionUnits = majorCompletionUnits;
	}
	/**
	 * 获取：主要完成单位
	 */
	public String getMajorCompletionUnits() {
		return majorCompletionUnits;
	}
	/**
	 * 设置：协作单位 
	 */
	public void setCooperationUnit(String cooperationUnit) {
		this.cooperationUnit = cooperationUnit;
	}
	/**
	 * 获取：协作单位 
	 */
	public String getCooperationUnit() {
		return cooperationUnit;
	}
	/**
	 * 设置：电话/手机
	 */
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	/**
	 * 获取：电话/手机
	 */
	public String getContactPhone() {
		return contactPhone;
	}
	/**
	 * 设置：申报单位联系人	
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	/**
	 * 获取：申报单位联系人	
	 */
	public String getContactName() {
		return contactName;
	}
	/**
	 * 设置：传真 
	 */
	public void setContactFox(String contactFox) {
		this.contactFox = contactFox;
	}
	/**
	 * 获取：传真 
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
	 * 设置：项目概况、难点及先进性
	 */
	public void setProjectOverview(String projectOverview) {
		this.projectOverview = projectOverview;
	}
	/**
	 * 获取：项目概况、难点及先进性
	 */
	public String getProjectOverview() {
		return projectOverview;
	}
	/**
	 * 设置：曾获奖励级别	
	 */
	public void setRewardLevel(String rewardLevel) {
		this.rewardLevel = rewardLevel;
	}
	/**
	 * 获取：曾获奖励级别	
	 */
	public String getRewardLevel() {
		return rewardLevel;
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
