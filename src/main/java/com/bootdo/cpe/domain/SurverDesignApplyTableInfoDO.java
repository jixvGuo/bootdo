package com.bootdo.cpe.domain;

import java.io.Serializable;
import java.util.Date;



/**
 *  石油工程建设优秀设计奖项目申报表表格
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-03-28 00:18:04
 */
public class SurverDesignApplyTableInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//申报专业
	private String applyMajor;
	//项目名称
	private String proName;
	//工程起止时间
	private String proStartEndTime;
	//投产时间
	private String useTime;
	//验收部门
	private String acceptanceDepartment;
	//验收时间
	private String acceptanceTime;
	//建筑规模
	private String buildScope;
	//建筑面积
	private String buildArea;
	//设计概(预)算
	private String designBudget;
	//竣工决算
	private String completionFinalAccounts;
	//超概算的
	private String overestimated;
	//主要设计单位
	private String mainDesignCompany;
	//申报单位联系人
	private String applyConcat;
	//协作单位
	private String cooperationUnit;
	//电话/手机
	private String applyPhone;
	//附件目录
	private String accessoriesCatalog;
	//传真
	private String fax;
	//工程概况、工程设计的难点及先进性
	private String proDesc;
	//曾获奖励级别
	private String awardLevel;
	//申报单位意见
	private String unitOpinions;
	//上级主管部门(或建设单位)推荐意见
	private String opinionsHigherAuthorities;
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
	 * 设置：工程起止时间
	 */
	public void setProStartEndTime(String proStartEndTime) {
		this.proStartEndTime = proStartEndTime;
	}
	/**
	 * 获取：工程起止时间
	 */
	public String getProStartEndTime() {
		return proStartEndTime;
	}
	/**
	 * 设置：投产时间
	 */
	public void setUseTime(String useTime) {
		this.useTime = useTime;
	}
	/**
	 * 获取：投产时间
	 */
	public String getUseTime() {
		return useTime;
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
	 * 设置：建筑规模
	 */
	public void setBuildScope(String buildScope) {
		this.buildScope = buildScope;
	}
	/**
	 * 获取：建筑规模
	 */
	public String getBuildScope() {
		return buildScope;
	}
	/**
	 * 设置：建筑面积
	 */
	public void setBuildArea(String buildArea) {
		this.buildArea = buildArea;
	}
	/**
	 * 获取：建筑面积
	 */
	public String getBuildArea() {
		return buildArea;
	}
	/**
	 * 设置：设计概(预)算
	 */
	public void setDesignBudget(String designBudget) {
		this.designBudget = designBudget;
	}
	/**
	 * 获取：设计概(预)算
	 */
	public String getDesignBudget() {
		return designBudget;
	}
	/**
	 * 设置：竣工决算
	 */
	public void setCompletionFinalAccounts(String completionFinalAccounts) {
		this.completionFinalAccounts = completionFinalAccounts;
	}
	/**
	 * 获取：竣工决算
	 */
	public String getCompletionFinalAccounts() {
		return completionFinalAccounts;
	}
	/**
	 * 设置：超概算的
	 */
	public void setOverestimated(String overestimated) {
		this.overestimated = overestimated;
	}
	/**
	 * 获取：超概算的
	 */
	public String getOverestimated() {
		return overestimated;
	}
	/**
	 * 设置：主要设计单位
	 */
	public void setMainDesignCompany(String mainDesignCompany) {
		this.mainDesignCompany = mainDesignCompany;
	}
	/**
	 * 获取：主要设计单位
	 */
	public String getMainDesignCompany() {
		return mainDesignCompany;
	}
	/**
	 * 设置：申报单位联系人
	 */
	public void setApplyConcat(String applyConcat) {
		this.applyConcat = applyConcat;
	}
	/**
	 * 获取：申报单位联系人
	 */
	public String getApplyConcat() {
		return applyConcat;
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
	public void setApplyPhone(String applyPhone) {
		this.applyPhone = applyPhone;
	}
	/**
	 * 获取：电话/手机
	 */
	public String getApplyPhone() {
		return applyPhone;
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
	 * 设置：传真
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}
	/**
	 * 获取：传真
	 */
	public String getFax() {
		return fax;
	}
	/**
	 * 设置：工程概况、工程设计的难点及先进性
	 */
	public void setProDesc(String proDesc) {
		this.proDesc = proDesc;
	}
	/**
	 * 获取：工程概况、工程设计的难点及先进性
	 */
	public String getProDesc() {
		return proDesc;
	}
	/**
	 * 设置：曾获奖励级别
	 */
	public void setAwardLevel(String awardLevel) {
		this.awardLevel = awardLevel;
	}
	/**
	 * 获取：曾获奖励级别
	 */
	public String getAwardLevel() {
		return awardLevel;
	}
	/**
	 * 设置：申报单位意见
	 */
	public void setUnitOpinions(String unitOpinions) {
		this.unitOpinions = unitOpinions;
	}
	/**
	 * 获取：申报单位意见
	 */
	public String getUnitOpinions() {
		return unitOpinions;
	}
	/**
	 * 设置：上级主管部门(或建设单位)推荐意见
	 */
	public void setOpinionsHigherAuthorities(String opinionsHigherAuthorities) {
		this.opinionsHigherAuthorities = opinionsHigherAuthorities;
	}
	/**
	 * 获取：上级主管部门(或建设单位)推荐意见
	 */
	public String getOpinionsHigherAuthorities() {
		return opinionsHigherAuthorities;
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
}
