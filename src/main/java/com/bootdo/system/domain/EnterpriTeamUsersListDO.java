package com.bootdo.system.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 团队人员构成-主要成员
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-06-30 04:48:15
 */
public class EnterpriTeamUsersListDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//用户信息记录id
	private Integer teamUsersRecordId;
	//序号
	private String number;
	//姓名
	private String userName;
	//性别 1女 2 男
	private int gender;
	//
	private String age;
	//专业技术职务
	private String technicalPosition;
	//所在单位
	private String workCompany;
	//研究领域
	private String researchField;
	// 团队工作时间（年） 
	private String teamWorkTimeLen;
	//用户类型：带头人, 其他主要成员 
	private String userType;
	//
	private Long optUid;
	//
	private String taskId;
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
	 * 设置：用户信息记录id
	 */
	public void setTeamUsersRecordId(Integer teamUsersRecordId) {
		this.teamUsersRecordId = teamUsersRecordId;
	}
	/**
	 * 获取：用户信息记录id
	 */
	public Integer getTeamUsersRecordId() {
		return teamUsersRecordId;
	}
	/**
	 * 设置：序号
	 */
	public void setNumber(String number) {
		this.number = number;
	}
	/**
	 * 获取：序号
	 */
	public String getNumber() {
		return number;
	}
	/**
	 * 设置：姓名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 获取：姓名
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * 设置：
	 */
	public void setAge(String age) {
		this.age = age;
	}
	/**
	 * 获取：
	 */
	public String getAge() {
		return age;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	/**
	 * 设置：专业技术职务
	 */
	public void setTechnicalPosition(String technicalPosition) {
		this.technicalPosition = technicalPosition;
	}
	/**
	 * 获取：专业技术职务
	 */
	public String getTechnicalPosition() {
		return technicalPosition;
	}
	/**
	 * 设置：所在单位
	 */
	public void setWorkCompany(String workCompany) {
		this.workCompany = workCompany;
	}
	/**
	 * 获取：所在单位
	 */
	public String getWorkCompany() {
		return workCompany;
	}
	/**
	 * 设置：研究领域
	 */
	public void setResearchField(String researchField) {
		this.researchField = researchField;
	}
	/**
	 * 获取：研究领域
	 */
	public String getResearchField() {
		return researchField;
	}
	/**
	 * 设置： 团队工作时间（年） 
	 */
	public void setTeamWorkTimeLen(String teamWorkTimeLen) {
		this.teamWorkTimeLen = teamWorkTimeLen;
	}
	/**
	 * 获取： 团队工作时间（年） 
	 */
	public String getTeamWorkTimeLen() {
		return teamWorkTimeLen;
	}
	/**
	 * 设置：用户类型：带头人, 其他主要成员 
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}
	/**
	 * 获取：用户类型：带头人, 其他主要成员 
	 */
	public String getUserType() {
		return userType;
	}
	/**
	 * 设置：
	 */
	public void setOptUid(Long optUid) {
		this.optUid = optUid;
	}
	/**
	 * 获取：
	 */
	public Long getOptUid() {
		return optUid;
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

	public int getProId() {
		return proId;
	}

	public void setProId(int proId) {
		this.proId = proId;
	}
}
