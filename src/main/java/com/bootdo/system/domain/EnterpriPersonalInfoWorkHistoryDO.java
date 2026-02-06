package com.bootdo.system.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 个人工作经历
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-06-30 04:48:14
 */
public class EnterpriPersonalInfoWorkHistoryDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//个人信息的id
	private Integer personalId;
	//工作起止时间
	private String workStartEndTime;
	//单位名称
	private String workCompany;
	//职务
	private String workPosition;
	//从事工作
	private String workContent;
	//
	private Date created;
	//项目id
	private int proId;

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
	 * 设置：个人信息的id
	 */
	public void setPersonalId(Integer personalId) {
		this.personalId = personalId;
	}
	/**
	 * 获取：个人信息的id
	 */
	public Integer getPersonalId() {
		return personalId;
	}
	/**
	 * 设置：工作起止时间
	 */
	public void setWorkStartEndTime(String workStartEndTime) {
		this.workStartEndTime = workStartEndTime;
	}
	/**
	 * 获取：工作起止时间
	 */
	public String getWorkStartEndTime() {
		return workStartEndTime;
	}
	/**
	 * 设置：单位名称
	 */
	public void setWorkCompany(String workCompany) {
		this.workCompany = workCompany;
	}
	/**
	 * 获取：单位名称
	 */
	public String getWorkCompany() {
		return workCompany;
	}
	/**
	 * 设置：职务
	 */
	public void setWorkPosition(String workPosition) {
		this.workPosition = workPosition;
	}
	/**
	 * 获取：职务
	 */
	public String getWorkPosition() {
		return workPosition;
	}
	/**
	 * 设置：从事工作
	 */
	public void setWorkContent(String workContent) {
		this.workContent = workContent;
	}
	/**
	 * 获取：从事工作
	 */
	public String getWorkContent() {
		return workContent;
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

	public int getProId() {
		return proId;
	}

	public void setProId(int proId) {
		this.proId = proId;
	}
}
