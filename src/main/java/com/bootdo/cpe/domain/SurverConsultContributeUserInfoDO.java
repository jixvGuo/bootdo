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
public class SurverConsultContributeUserInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//	优秀咨询奖
	private Integer id;
	//姓名
	private String username;
	//性别
	private String gender;
	//年龄
	private Integer age;
	//职务或职称
	private String job;
	//工作单位
	private String workPlace;
	//参加本项目的起止日期
	private String startTime;
	//在本项目中担任的主要工作职责
	private String workDesc;
	//
	private String optUid;
	//
	private String proId;
	//
	private String taskId;
	//
	private Date created;
	//
	private Date updated;
	//
	private String deleted;

	/**
	 * 设置：	优秀咨询奖
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：	优秀咨询奖
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：姓名
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * 获取：姓名
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * 设置：性别
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * 获取：性别
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * 设置：年龄
	 */
	public void setAge(Integer age) {
		this.age = age;
	}
	/**
	 * 获取：年龄
	 */
	public Integer getAge() {
		return age;
	}
	/**
	 * 设置：职务或职称
	 */
	public void setJob(String job) {
		this.job = job;
	}
	/**
	 * 获取：职务或职称
	 */
	public String getJob() {
		return job;
	}
	/**
	 * 设置：工作单位
	 */
	public void setWorkPlace(String workPlace) {
		this.workPlace = workPlace;
	}
	/**
	 * 获取：工作单位
	 */
	public String getWorkPlace() {
		return workPlace;
	}
	/**
	 * 设置：参加本项目的起止日期
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	/**
	 * 获取：参加本项目的起止日期
	 */
	public String getStartTime() {
		return startTime;
	}
	/**
	 * 设置：在本项目中担任的主要工作职责
	 */
	public void setWorkDesc(String workDesc) {
		this.workDesc = workDesc;
	}
	/**
	 * 获取：在本项目中担任的主要工作职责
	 */
	public String getWorkDesc() {
		return workDesc;
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
