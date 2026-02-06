package com.bootdo.cpe.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 申报人基本信息
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-03-27 10:11:10
 */
public class SurverDesignApplyUserBaseInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//类别
	private String userType;
	//排名
	private String sortNum;
	//项目名称
	private String proName;
	//主要贡献者名单
	private String contributeUserNames;
	//
	private String recommendLevel;
	//操作用户
	private Integer optUid;
	//项目id
	private Integer proId;
	//任务id
	private String taskId;
	//创建日期
	private Date created;
	//更新日期
	private Date updated;
	//是否删除 0未删除，1已删除
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
	 * 设置：类别
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}
	/**
	 * 获取：类别
	 */
	public String getUserType() {
		return userType;
	}
	/**
	 * 设置：排名
	 */
	public void setSortNum(String sortNum) {
		this.sortNum = sortNum;
	}
	/**
	 * 获取：排名
	 */
	public String getSortNum() {
		return sortNum;
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
	 * 设置：主要贡献者名单
	 */
	public void setContributeUserNames(String contributeUserNames) {
		this.contributeUserNames = contributeUserNames;
	}
	/**
	 * 获取：主要贡献者名单
	 */
	public String getContributeUserNames() {
		return contributeUserNames;
	}
	/**
	 * 设置：
	 */
	public void setRecommendLevel(String recommendLevel) {
		this.recommendLevel = recommendLevel;
	}
	/**
	 * 获取：
	 */
	public String getRecommendLevel() {
		return recommendLevel;
	}
	/**
	 * 设置：操作用户
	 */
	public void setOptUid(Integer optUid) {
		this.optUid = optUid;
	}
	/**
	 * 获取：操作用户
	 */
	public Integer getOptUid() {
		return optUid;
	}
	/**
	 * 设置：项目id
	 */
	public void setProId(Integer proId) {
		this.proId = proId;
	}
	/**
	 * 获取：项目id
	 */
	public Integer getProId() {
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
	 * 设置：是否删除 0未删除，1已删除
	 */
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
	/**
	 * 获取：是否删除 0未删除，1已删除
	 */
	public Integer getDeleted() {
		return deleted;
	}
}
