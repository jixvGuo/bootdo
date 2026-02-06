package com.bootdo.cpe.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 公奖法主要完成人
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-07-27 08:03:07
 */
public class GfMainCompleteInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private String username;
	//职务
	private String duties;
	//职称
	private String technicalTitle;
	//工作单位
	private String unit;
	//
	private String proId;
	//
	private String taskId;
	//
	private String applyId;
	//
	private Date created;
	//
	private Date updated;

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
	 * 设置：
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * 获取：
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * 设置：职务
	 */
	public void setDuties(String duties) {
		this.duties = duties;
	}
	/**
	 * 获取：职务
	 */
	public String getDuties() {
		return duties;
	}
	/**
	 * 设置：职称
	 */
	public void setTechnicalTitle(String technicalTitle) {
		this.technicalTitle = technicalTitle;
	}
	/**
	 * 获取：职称
	 */
	public String getTechnicalTitle() {
		return technicalTitle;
	}
	/**
	 * 设置：工作单位
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}
	/**
	 * 获取：工作单位
	 */
	public String getUnit() {
		return unit;
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
}
