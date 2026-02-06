package com.bootdo.cpe.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-04-05 20:07:38
 */
public class SurverDesignApplyProjectProfileDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//项目编号
	private String projectId;
	//申报单位
	private String reportingUnit;
	//奖项类别
	private String awardCategory;
	// 项目名称
	private String projectName;
	//推荐等级
	private String recommendedGrade;
	//初评专业组
	private String preliminaryEvaluationGroup;
	//项目简介
	private String projectDescription;
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
	 * 设置：项目编号
	 */
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	/**
	 * 获取：项目编号
	 */
	public String getProjectId() {
		return projectId;
	}
	/**
	 * 设置：申报单位
	 */
	public void setReportingUnit(String reportingUnit) {
		this.reportingUnit = reportingUnit;
	}
	/**
	 * 获取：申报单位
	 */
	public String getReportingUnit() {
		return reportingUnit;
	}
	/**
	 * 设置：奖项类别
	 */
	public void setAwardCategory(String awardCategory) {
		this.awardCategory = awardCategory;
	}
	/**
	 * 获取：奖项类别
	 */
	public String getAwardCategory() {
		return awardCategory;
	}
	/**
	 * 设置： 项目名称
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	/**
	 * 获取： 项目名称
	 */
	public String getProjectName() {
		return projectName;
	}
	/**
	 * 设置：推荐等级
	 */
	public void setRecommendedGrade(String recommendedGrade) {
		this.recommendedGrade = recommendedGrade;
	}
	/**
	 * 获取：推荐等级
	 */
	public String getRecommendedGrade() {
		return recommendedGrade;
	}
	/**
	 * 设置：初评专业组
	 */
	public void setPreliminaryEvaluationGroup(String preliminaryEvaluationGroup) {
		this.preliminaryEvaluationGroup = preliminaryEvaluationGroup;
	}
	/**
	 * 获取：初评专业组
	 */
	public String getPreliminaryEvaluationGroup() {
		return preliminaryEvaluationGroup;
	}
	/**
	 * 设置：项目简介
	 */
	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}
	/**
	 * 获取：项目简介
	 */
	public String getProjectDescription() {
		return projectDescription;
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
