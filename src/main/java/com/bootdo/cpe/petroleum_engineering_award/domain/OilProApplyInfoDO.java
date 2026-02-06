package com.bootdo.cpe.petroleum_engineering_award.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2021-02-20 22:55:27
 */
public class OilProApplyInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//记录id(id)
	private Integer id;
	//
	private String comanyName;
	//企业性质
	private String companyNature;
	//通信地址
	private String mailingAddress;
	//邮政编码
	private String postCode;
	//
	private String emailAddress;
	//资质等级和范围
	private String qualificationLevelScop;
	//法人代表
	private String legalRepresentative;
	//职务
	private String duty;
	//固定电话
	private String fixedTelephone;
	//移动电话
	private String mobilePhone;
	//项目经理
	private String projectManager;
	//电子信箱
	private String emailManager;
	//固定电话
	private String fixedTelephoneManager;
	//移动电话
	private String mobilePhoneManager;
	//联系人
	private String contactName;
	//电子信箱
	private String contactMail;
	//固定电话
	private String contactFixPhone;
	//移动电话
	private String contactMobile;
	//申报单位简介 
	private String applicantBriefIntroduction;
	//
	private String proId;
	//任务id
	private String taskId;
	//申报id
	private String applyId;
	//申报用户id
	private String optUid;
	//创建日期
	private String created;
	//更新日期
	private String updated;
	//项目名称
	private String projectName;

	/**
	 * 设置：记录id(id)
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：记录id(id)
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setComanyName(String comanyName) {
		this.comanyName = comanyName;
	}
	/**
	 * 获取：
	 */
	public String getComanyName() {
		return comanyName;
	}
	/**
	 * 设置：企业性质
	 */
	public void setCompanyNature(String companyNature) {
		this.companyNature = companyNature;
	}
	/**
	 * 获取：企业性质
	 */
	public String getCompanyNature() {
		return companyNature;
	}
	/**
	 * 设置：通信地址
	 */
	public void setMailingAddress(String mailingAddress) {
		this.mailingAddress = mailingAddress;
	}
	/**
	 * 获取：通信地址
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
	 * 设置：
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	/**
	 * 获取：
	 */
	public String getEmailAddress() {
		return emailAddress;
	}
	/**
	 * 设置：资质等级和范围
	 */
	public void setQualificationLevelScop(String qualificationLevelScop) {
		this.qualificationLevelScop = qualificationLevelScop;
	}
	/**
	 * 获取：资质等级和范围
	 */
	public String getQualificationLevelScop() {
		return qualificationLevelScop;
	}
	/**
	 * 设置：法人代表
	 */
	public void setLegalRepresentative(String legalRepresentative) {
		this.legalRepresentative = legalRepresentative;
	}
	/**
	 * 获取：法人代表
	 */
	public String getLegalRepresentative() {
		return legalRepresentative;
	}
	/**
	 * 设置：职务
	 */
	public void setDuty(String duty) {
		this.duty = duty;
	}
	/**
	 * 获取：职务
	 */
	public String getDuty() {
		return duty;
	}
	/**
	 * 设置：固定电话
	 */
	public void setFixedTelephone(String fixedTelephone) {
		this.fixedTelephone = fixedTelephone;
	}
	/**
	 * 获取：固定电话
	 */
	public String getFixedTelephone() {
		return fixedTelephone;
	}
	/**
	 * 设置：移动电话
	 */
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	/**
	 * 获取：移动电话
	 */
	public String getMobilePhone() {
		return mobilePhone;
	}
	/**
	 * 设置：项目经理
	 */
	public void setProjectManager(String projectManager) {
		this.projectManager = projectManager;
	}
	/**
	 * 获取：项目经理
	 */
	public String getProjectManager() {
		return projectManager;
	}
	/**
	 * 设置：电子信箱
	 */
	public void setEmailManager(String emailManager) {
		this.emailManager = emailManager;
	}
	/**
	 * 获取：电子信箱
	 */
	public String getEmailManager() {
		return emailManager;
	}
	/**
	 * 设置：固定电话
	 */
	public void setFixedTelephoneManager(String fixedTelephoneManager) {
		this.fixedTelephoneManager = fixedTelephoneManager;
	}
	/**
	 * 获取：固定电话
	 */
	public String getFixedTelephoneManager() {
		return fixedTelephoneManager;
	}
	/**
	 * 设置：移动电话
	 */
	public void setMobilePhoneManager(String mobilePhoneManager) {
		this.mobilePhoneManager = mobilePhoneManager;
	}
	/**
	 * 获取：移动电话
	 */
	public String getMobilePhoneManager() {
		return mobilePhoneManager;
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
	 * 设置：电子信箱
	 */
	public void setContactMail(String contactMail) {
		this.contactMail = contactMail;
	}
	/**
	 * 获取：电子信箱
	 */
	public String getContactMail() {
		return contactMail;
	}
	/**
	 * 设置：固定电话
	 */
	public void setContactFixPhone(String contactFixPhone) {
		this.contactFixPhone = contactFixPhone;
	}
	/**
	 * 获取：固定电话
	 */
	public String getContactFixPhone() {
		return contactFixPhone;
	}
	/**
	 * 设置：移动电话
	 */
	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}
	/**
	 * 获取：移动电话
	 */
	public String getContactMobile() {
		return contactMobile;
	}
	/**
	 * 设置：申报单位简介 
	 */
	public void setApplicantBriefIntroduction(String applicantBriefIntroduction) {
		this.applicantBriefIntroduction = applicantBriefIntroduction;
	}
	/**
	 * 获取：申报单位简介 
	 */
	public String getApplicantBriefIntroduction() {
		return applicantBriefIntroduction;
	}
	/**
	 * 设置：
	 */
	public void setProId(String proId) {
		this.proId = proId;
	}
	/**
	 * 获取：
	 */
	public String getProId() {
		return proId;
	}
	/**
	 * 设置：任务id
	 */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	/**
	 * 获取：任务id
	 */
	public String getTaskId() {
		return taskId;
	}
	/**
	 * 设置：申报id
	 */
	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
	/**
	 * 获取：申报id
	 */
	public String getApplyId() {
		return applyId;
	}
	/**
	 * 设置：申报用户id
	 */
	public void setOptUid(String optUid) {
		this.optUid = optUid;
	}
	/**
	 * 获取：申报用户id
	 */
	public String getOptUid() {
		return optUid;
	}
	/**
	 * 设置：创建日期
	 */
	public void setCreated(String created) {
		this.created = created;
	}
	/**
	 * 获取：创建日期
	 */
	public String getCreated() {
		return created;
	}
	/**
	 * 设置：更新日期
	 */
	public void setUpdated(String updated) {
		this.updated = updated;
	}
	/**
	 * 获取：更新日期
	 */
	public String getUpdated() {
		return updated;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
}
