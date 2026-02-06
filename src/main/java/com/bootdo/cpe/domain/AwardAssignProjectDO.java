package com.bootdo.cpe.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 分派的项目信息
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-03-28 23:13:51
 */
public class AwardAssignProjectDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//分派的用户id
	private Long assignUid;
	//被分派的用户id
	private Long uid;
	//分派的项目id
	private Long proId;
	//分派的时间
	private Date created;

	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：分派的用户id
	 */
	public void setAssignUid(Long assignUid) {
		this.assignUid = assignUid;
	}
	/**
	 * 获取：分派的用户id
	 */
	public Long getAssignUid() {
		return assignUid;
	}
	/**
	 * 设置：被分派的用户id
	 */
	public void setUid(Long uid) {
		this.uid = uid;
	}
	/**
	 * 获取：被分派的用户id
	 */
	public Long getUid() {
		return uid;
	}
	/**
	 * 设置：分派的项目id
	 */
	public void setProId(Long proId) {
		this.proId = proId;
	}
	/**
	 * 获取：分派的项目id
	 */
	public Long getProId() {
		return proId;
	}
	/**
	 * 设置：分派的时间
	 */
	public void setCreated(Date created) {
		this.created = created;
	}
	/**
	 * 获取：分派的时间
	 */
	public Date getCreated() {
		return created;
	}
}
