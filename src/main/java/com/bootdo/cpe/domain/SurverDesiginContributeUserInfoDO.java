package com.bootdo.cpe.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 在本项目中做出贡献的主要人员情况
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-03-27 10:11:09
 */
public class SurverDesiginContributeUserInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
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
	//参加本项目的起止时间
	private String startTime;
	//
	private String workDesc;
	//
	private Integer optUid;
	//
	private Integer proId;
	//
	private String taskId;
	//创建日期
	private Date created;
	//更新日期
	private Date updated;
	//是否删除
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
	 * 设置：参加本项目的起止时间
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	/**
	 * 获取：参加本项目的起止时间
	 */
	public String getStartTime() {
		return startTime;
	}
	/**
	 * 设置：
	 */
	public void setWorkDesc(String workDesc) {
		this.workDesc = workDesc;
	}
	/**
	 * 获取：
	 */
	public String getWorkDesc() {
		return workDesc;
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
	 * 设置：创建日期
	 */
	public void setCreated(Date created) {
		this.created = created;
	}
	/**
	 * 获取：创建日期
	 */
	public Date getCreated() {
		return created;
	}
	/**
	 * 设置：更新日期
	 */
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	/**
	 * 获取：更新日期
	 */
	public Date getUpdated() {
		return updated;
	}
	/**
	 * 设置：是否删除
	 */
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
	/**
	 * 获取：是否删除
	 */
	public Integer getDeleted() {
		return deleted;
	}
}
