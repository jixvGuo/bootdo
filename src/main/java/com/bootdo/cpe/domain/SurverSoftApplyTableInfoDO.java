package com.bootdo.cpe.domain;

import java.io.Serializable;
import java.util.Date;



/**
 *  石油工程建设优秀勘察设计计算机软件奖申报表
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-03-29 20:51:20
 */
public class SurverSoftApplyTableInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	private Integer id;
	//申报专业
	private String applyMajor;
	//软件 全称
	private String softName;
	//软件简称
	private String softShortName;
	//软件类型
	private String softType = "blank";
	//软件类别
	private String softCategory = "blank";
	//软件符合哪类现行国家规范
	private String softNationalStandard;
	//任务来源
	private String softTaskSource;
	//开发起止时间
	private String softStartEnd;
	//试用时间
	private String softTrialTime;
	//鉴定部门
	private String identificationDepartment;
	//鉴定时间
	private String identificationTime;
	//评测公司
	private String evaluationCompany;
	//评测时间
	private String evaluationTime;
	//主编单位
	private String editorChief;
	//协作单位
	private String cooperationUnit;
	//联系人
	private String contactName;
	//电话/手机
	private String contactPhone;
	//传真
	private String fax;
	//保密级别
	private String securityLevel = "blank";
	//运行操作系统名称及版本号
	private String osVersion;
	//支撑环境名称及版本号
	private String supportEnvironment;
	//编程语言名称及版本号
	private String osLan;
	//主要适用行业
	private String mainApplicableIndusty;
	//主要用途
	private String mainPurpose;
	//软件主要功能及创新概述（附查新报告
	private String softwareMainFunctions;
	//该软件与当前国内外同类软件的综合比较（包括存在问题及改进措施）
	private String comprehensiveSimilarSoftware;
	//该软件的经济与社会效益
	private String cconomicSocialBenefits;
	//附件目录
	private String accessoriesCatalog;
	//获奖情况
	private String awards;
	// 申报单位意见
	private String rportingOpinion;
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
	//上级主管部门（或建设单位）推荐意见
	private String upRecommendation;

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
	 * 设置：软件 全称
	 */
	public void setSoftName(String softName) {
		this.softName = softName;
	}
	/**
	 * 获取：软件 全称
	 */
	public String getSoftName() {
		return softName;
	}
	/**
	 * 设置：软件简称
	 */
	public void setSoftShortName(String softShortName) {
		this.softShortName = softShortName;
	}
	/**
	 * 获取：软件简称
	 */
	public String getSoftShortName() {
		return softShortName;
	}
	/**
	 * 设置：软件类型
	 */
	public void setSoftType(String softType) {
		this.softType = softType;
	}
	/**
	 * 获取：软件类型
	 */
	public String getSoftType() {
		return softType;
	}
	/**
	 * 设置：软件类别
	 */
	public void setSoftCategory(String softCategory) {
		this.softCategory = softCategory;
	}
	/**
	 * 获取：软件类别
	 */
	public String getSoftCategory() {
		return softCategory;
	}
	/**
	 * 设置：软件符合哪类现行国家规范
	 */
	public void setSoftNationalStandard(String softNationalStandard) {
		this.softNationalStandard = softNationalStandard;
	}
	/**
	 * 获取：软件符合哪类现行国家规范
	 */
	public String getSoftNationalStandard() {
		return softNationalStandard;
	}
	/**
	 * 设置：任务来源
	 */
	public void setSoftTaskSource(String softTaskSource) {
		this.softTaskSource = softTaskSource;
	}
	/**
	 * 获取：任务来源
	 */
	public String getSoftTaskSource() {
		return softTaskSource;
	}
	/**
	 * 设置：开发起止时间
	 */
	public void setSoftStartEnd(String softStartEnd) {
		this.softStartEnd = softStartEnd;
	}
	/**
	 * 获取：开发起止时间
	 */
	public String getSoftStartEnd() {
		return softStartEnd;
	}
	/**
	 * 设置：试用时间
	 */
	public void setSoftTrialTime(String softTrialTime) {
		this.softTrialTime = softTrialTime;
	}
	/**
	 * 获取：试用时间
	 */
	public String getSoftTrialTime() {
		return softTrialTime;
	}
	/**
	 * 设置：鉴定部门
	 */
	public void setIdentificationDepartment(String identificationDepartment) {
		this.identificationDepartment = identificationDepartment;
	}
	/**
	 * 获取：鉴定部门
	 */
	public String getIdentificationDepartment() {
		return identificationDepartment;
	}
	/**
	 * 设置：鉴定时间
	 */
	public void setIdentificationTime(String identificationTime) {
		this.identificationTime = identificationTime;
	}
	/**
	 * 获取：鉴定时间
	 */
	public String getIdentificationTime() {
		return identificationTime;
	}
	/**
	 * 设置：评测公司
	 */
	public void setEvaluationCompany(String evaluationCompany) {
		this.evaluationCompany = evaluationCompany;
	}
	/**
	 * 获取：评测公司
	 */
	public String getEvaluationCompany() {
		return evaluationCompany;
	}
	/**
	 * 设置：评测时间
	 */
	public void setEvaluationTime(String evaluationTime) {
		this.evaluationTime = evaluationTime;
	}
	/**
	 * 获取：评测时间
	 */
	public String getEvaluationTime() {
		return evaluationTime;
	}
	/**
	 * 设置：主编单位
	 */
	public void setEditorChief(String editorChief) {
		this.editorChief = editorChief;
	}
	/**
	 * 获取：主编单位
	 */
	public String getEditorChief() {
		return editorChief;
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
	 * 设置：联系人
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	/**
	 * 获取：联系人
	 */
	public String getContactName() {
		return contactName;
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
	 * 设置：保密级别
	 */
	public void setSecurityLevel(String securityLevel) {
		this.securityLevel = securityLevel;
	}
	/**
	 * 获取：保密级别
	 */
	public String getSecurityLevel() {
		return securityLevel;
	}
	/**
	 * 设置：运行操作系统名称及版本号
	 */
	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}
	/**
	 * 获取：运行操作系统名称及版本号
	 */
	public String getOsVersion() {
		return osVersion;
	}
	/**
	 * 设置：支撑环境名称及版本号
	 */
	public void setSupportEnvironment(String supportEnvironment) {
		this.supportEnvironment = supportEnvironment;
	}
	/**
	 * 获取：支撑环境名称及版本号
	 */
	public String getSupportEnvironment() {
		return supportEnvironment;
	}
	/**
	 * 设置：编程语言名称及版本号
	 */
	public void setOsLan(String osLan) {
		this.osLan = osLan;
	}
	/**
	 * 获取：编程语言名称及版本号
	 */
	public String getOsLan() {
		return osLan;
	}
	/**
	 * 设置：主要适用行业
	 */
	public void setMainApplicableIndusty(String mainApplicableIndusty) {
		this.mainApplicableIndusty = mainApplicableIndusty;
	}
	/**
	 * 获取：主要适用行业
	 */
	public String getMainApplicableIndusty() {
		return mainApplicableIndusty;
	}
	/**
	 * 设置：主要用途
	 */
	public void setMainPurpose(String mainPurpose) {
		this.mainPurpose = mainPurpose;
	}
	/**
	 * 获取：主要用途
	 */
	public String getMainPurpose() {
		return mainPurpose;
	}
	/**
	 * 设置：软件主要功能及创新概述（附查新报告
	 */
	public void setSoftwareMainFunctions(String softwareMainFunctions) {
		this.softwareMainFunctions = softwareMainFunctions;
	}
	/**
	 * 获取：软件主要功能及创新概述（附查新报告
	 */
	public String getSoftwareMainFunctions() {
		return softwareMainFunctions;
	}
	/**
	 * 设置：该软件与当前国内外同类软件的综合比较（包括存在问题及改进措施）
	 */
	public void setComprehensiveSimilarSoftware(String comprehensiveSimilarSoftware) {
		this.comprehensiveSimilarSoftware = comprehensiveSimilarSoftware;
	}
	/**
	 * 获取：该软件与当前国内外同类软件的综合比较（包括存在问题及改进措施）
	 */
	public String getComprehensiveSimilarSoftware() {
		return comprehensiveSimilarSoftware;
	}
	/**
	 * 设置：该软件的经济与社会效益
	 */
	public void setCconomicSocialBenefits(String cconomicSocialBenefits) {
		this.cconomicSocialBenefits = cconomicSocialBenefits;
	}
	/**
	 * 获取：该软件的经济与社会效益
	 */
	public String getCconomicSocialBenefits() {
		return cconomicSocialBenefits;
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
	 * 设置：获奖情况
	 */
	public void setAwards(String awards) {
		this.awards = awards;
	}
	/**
	 * 获取：获奖情况
	 */
	public String getAwards() {
		return awards;
	}
	/**
	 * 设置： 申报单位意见
	 */
	public void setRportingOpinion(String rportingOpinion) {
		this.rportingOpinion = rportingOpinion;
	}
	/**
	 * 获取： 申报单位意见
	 */
	public String getRportingOpinion() {
		return rportingOpinion;
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
	/**
	 * 设置：上级主管部门（或建设单位）推荐意见
	 */
	public void setUpRecommendation(String upRecommendation) {
		this.upRecommendation = upRecommendation;
	}
	/**
	 * 获取：上级主管部门（或建设单位）推荐意见
	 */
	public String getUpRecommendation() {
		return upRecommendation;
	}
}
