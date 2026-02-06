package com.bootdo.cpe.petroleum_engineering_award.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 单位信息表(
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2021-02-20 22:55:27
 */
public class OilAwardUnitInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//单位类型
	private String unitType;
	//单位名称
	private String fullUnitName;
	//通讯地址及邮政编码
	private String mailingAddressPostalCode;
	//联系人及电话
	private String contactPersonTelNumber;
	//项目负责人姓名、职务及身份证号
	private String namePositionNumberProject;
	//
	private String proId;
	//
	private String taskId;
	//
	private String applyId;
	//
	private String optUid;
	//
	private Date created;
	//
	private Date updated;
	//申报单信息id
	private int applyInfoId;

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
	 * 设置：单位类型
	 */
	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}
	/**
	 * 获取：单位类型
	 */
	public String getUnitType() {
		return unitType;
	}
	/**
	 * 设置：单位名称
	 */
	public void setFullUnitName(String fullUnitName) {
		this.fullUnitName = fullUnitName;
	}
	/**
	 * 获取：单位名称
	 */
	public String getFullUnitName() {
		return fullUnitName;
	}
	/**
	 * 设置：通讯地址及邮政编码
	 */
	public void setMailingAddressPostalCode(String mailingAddressPostalCode) {
		this.mailingAddressPostalCode = mailingAddressPostalCode;
	}
	/**
	 * 获取：通讯地址及邮政编码
	 */
	public String getMailingAddressPostalCode() {
		return mailingAddressPostalCode;
	}
	/**
	 * 设置：联系人及电话
	 */
	public void setContactPersonTelNumber(String contactPersonTelNumber) {
		this.contactPersonTelNumber = contactPersonTelNumber;
	}
	/**
	 * 获取：联系人及电话
	 */
	public String getContactPersonTelNumber() {
		return contactPersonTelNumber;
	}
	/**
	 * 设置：项目负责人姓名、职务及身份证号
	 */
	public void setNamePositionNumberProject(String namePositionNumberProject) {
		this.namePositionNumberProject = namePositionNumberProject;
	}
	/**
	 * 获取：项目负责人姓名、职务及身份证号
	 */
	public String getNamePositionNumberProject() {
		return namePositionNumberProject;
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
	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
	/**
	 * 获取：
	 */
	public String getApplyId() {
		return applyId;
	}
	/**
	 * 设置：
	 */
	public void setOptUid(String optUid) {
		this.optUid = optUid;
	}
	/**
	 * 获取：
	 */
	public String getOptUid() {
		return optUid;
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

	public int getApplyInfoId() {
		return applyInfoId;
	}

	public void setApplyInfoId(int applyInfoId) {
		this.applyInfoId = applyInfoId;
	}
}
