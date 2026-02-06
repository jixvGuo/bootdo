package com.bootdo.cpe.domain;

import java.io.Serializable;
import java.util.Date;



/**
 *
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-03-27 21:44:17
 */
public class SurverStandardApplyTableInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	private Integer id;
	//申报专业
	private String applyMajor;
	//图集名称
	private String galleryName;
	//图集号
	private String atlasNumber;
	//设计起止时间
	private String designStartEnd;
	//批准立项文件号
	private String approvalDocumentNumber;
	//批准实施文件号
	private String approvedDocumentNumber;
	// 主编单位
	private String dditorChief;
	//协作单位
	private String cooperationUnit;
	//申报单位联系人
	private String reportingContactPerson;
	//电话/手机
	private String reportingContactPhone;
	//传真
	private String fax;
	//附件目录
	private String accessoriesCatalog;
	//图集主要内容
	private String mainContentAtlas;
	//图集主要优缺点和效益
	private String mainAdvantagesDisadvantagesBenefits;
	//曾获哪一级奖励
	private String awardReceived;
	//申报单位意见
	private String reportingUnitOpinion;
	//上级主管部门（或建设单位）推荐意见
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
	private String deleted;

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
	 * 设置：图集名称
	 */
	public void setGalleryName(String galleryName) {
		this.galleryName = galleryName;
	}
	/**
	 * 获取：图集名称
	 */
	public String getGalleryName() {
		return galleryName;
	}
	/**
	 * 设置：图集号
	 */
	public void setAtlasNumber(String atlasNumber) {
		this.atlasNumber = atlasNumber;
	}
	/**
	 * 获取：图集号
	 */
	public String getAtlasNumber() {
		return atlasNumber;
	}
	/**
	 * 设置：设计起止时间
	 */
	public void setDesignStartEnd(String designStartEnd) {
		this.designStartEnd = designStartEnd;
	}
	/**
	 * 获取：设计起止时间
	 */
	public String getDesignStartEnd() {
		return designStartEnd;
	}
	/**
	 * 设置：批准立项文件号
	 */
	public void setApprovalDocumentNumber(String approvalDocumentNumber) {
		this.approvalDocumentNumber = approvalDocumentNumber;
	}
	/**
	 * 获取：批准立项文件号
	 */
	public String getApprovalDocumentNumber() {
		return approvalDocumentNumber;
	}
	/**
	 * 设置：批准实施文件号
	 */
	public void setApprovedDocumentNumber(String approvedDocumentNumber) {
		this.approvedDocumentNumber = approvedDocumentNumber;
	}
	/**
	 * 获取：批准实施文件号
	 */
	public String getApprovedDocumentNumber() {
		return approvedDocumentNumber;
	}
	/**
	 * 设置： 主编单位
	 */
	public void setDditorChief(String dditorChief) {
		this.dditorChief = dditorChief;
	}
	/**
	 * 获取： 主编单位
	 */
	public String getDditorChief() {
		return dditorChief;
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
	 * 设置：申报单位联系人
	 */
	public void setReportingContactPerson(String reportingContactPerson) {
		this.reportingContactPerson = reportingContactPerson;
	}
	/**
	 * 获取：申报单位联系人
	 */
	public String getReportingContactPerson() {
		return reportingContactPerson;
	}
	/**
	 * 设置：电话/手机
	 */
	public void setReportingContactPhone(String reportingContactPhone) {
		this.reportingContactPhone = reportingContactPhone;
	}
	/**
	 * 获取：电话/手机
	 */
	public String getReportingContactPhone() {
		return reportingContactPhone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
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
	 * 设置：图集主要内容
	 */
	public void setMainContentAtlas(String mainContentAtlas) {
		this.mainContentAtlas = mainContentAtlas;
	}
	/**
	 * 获取：图集主要内容
	 */
	public String getMainContentAtlas() {
		return mainContentAtlas;
	}
	/**
	 * 设置：图集主要优缺点和效益
	 */
	public void setMainAdvantagesDisadvantagesBenefits(String mainAdvantagesDisadvantagesBenefits) {
		this.mainAdvantagesDisadvantagesBenefits = mainAdvantagesDisadvantagesBenefits;
	}
	/**
	 * 获取：图集主要优缺点和效益
	 */
	public String getMainAdvantagesDisadvantagesBenefits() {
		return mainAdvantagesDisadvantagesBenefits;
	}
	/**
	 * 设置：曾获哪一级奖励
	 */
	public void setAwardReceived(String awardReceived) {
		this.awardReceived = awardReceived;
	}
	/**
	 * 获取：曾获哪一级奖励
	 */
	public String getAwardReceived() {
		return awardReceived;
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
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	/**
	 * 获取：
	 */
	public String getDeleted() {
		return deleted;
	}
}
