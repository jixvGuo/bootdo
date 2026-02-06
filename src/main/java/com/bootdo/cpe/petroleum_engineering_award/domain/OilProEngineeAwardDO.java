package com.bootdo.cpe.petroleum_engineering_award.domain;

import com.bootdo.common.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;



/**
 * 工程获奖
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2021-02-25 06:35:24
 */
public class OilProEngineeAwardDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//工程概况id
	private int engineeDescId;
	//获奖名称
	private String engineeNameAward = "";
	//获奖时间
	private Date engineeAwardWinningTime;
	//获奖时间
	private String awardTime = "";
	//颁奖单位
	private String engineeAwardingUnit = "";
	//项目id
	private String proId;
	//任务id
	private String taskId;
	//申报id
	private String applyId;
	//申报用户id
	private String optUid;
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

	public int getEngineeDescId() {
		return engineeDescId;
	}

	public void setEngineeDescId(int engineeDescId) {
		this.engineeDescId = engineeDescId;
	}

	/**
	 * 设置：获奖名称
	 */
	public void setEngineeNameAward(String engineeNameAward) {
		this.engineeNameAward = engineeNameAward;
	}
	/**
	 * 获取：获奖名称
	 */
	public String getEngineeNameAward() {
		return engineeNameAward;
	}
	/**
	 * 设置：获奖时间
	 */
	public void setEngineeAwardWinningTime(Date engineeAwardWinningTime) {
		this.engineeAwardWinningTime = engineeAwardWinningTime;
		if(engineeAwardWinningTime != null) {
			this.awardTime = DateUtils.format(engineeAwardWinningTime, "yyyy-MM-dd");
		}
	}
	/**
	 * 获取：获奖时间
	 */
	public Date getEngineeAwardWinningTime() {
		return engineeAwardWinningTime;
	}

	public String getAwardTime() {
		return awardTime;
	}

	public void setAwardTime(String awardTime) {
		this.awardTime = awardTime;
	}

	/**
	 * 设置：颁奖单位
	 */
	public void setEngineeAwardingUnit(String engineeAwardingUnit) {
		this.engineeAwardingUnit = engineeAwardingUnit;
	}
	/**
	 * 获取：颁奖单位
	 */
	public String getEngineeAwardingUnit() {
		return engineeAwardingUnit;
	}
	/**
	 * 设置：项目id
	 */
	public void setProId(String proId) {
		this.proId = proId;
	}
	/**
	 * 获取：项目id
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
