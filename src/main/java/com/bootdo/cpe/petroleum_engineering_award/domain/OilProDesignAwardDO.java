package com.bootdo.cpe.petroleum_engineering_award.domain;

import com.bootdo.common.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;



/**
 *
 *OilProDesignAwardDO
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2021-02-20 22:55:27
 */
public class OilProDesignAwardDO implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	private Integer id;
	//工程概况id
	private int engineeDescId;
	//获奖名称
	private String nameAward = "";
	//获奖时间
	private Date awardWinningTime;
	//获奖时间
	private String awardTimeStr = "";
	//颁奖单位
	private String awardingUnit = "";
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
	 * 奖项类型 desgin设计奖，enginee工程奖
	 */
	private String awardType;

	public String getAwardType() {
		return awardType;
	}

	public void setAwardType(String awardType) {
		this.awardType = awardType;
	}

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
	public void setNameAward(String nameAward) {
		this.nameAward = nameAward;
	}

	/**
	 * 获取：获奖名称
	 */
	public String getNameAward() {
		return nameAward;
	}

	/**
	 * 设置：获奖时间
	 */
	public void setAwardWinningTime(Date awardWinningTime) {
		this.awardWinningTime = awardWinningTime;
		if(awardWinningTime != null) {
			this.awardTimeStr = DateUtils.format(awardWinningTime, "yyyy-MM-dd");
		}
	}
	/**
	 * 获取：获奖时间
	 */
	public Date getAwardWinningTime() {
		return awardWinningTime;
	}

	public String getAwardTimeStr() {
		return awardTimeStr;
	}

	public void setAwardTimeStr(String awardTimeStr) {
		this.awardTimeStr = awardTimeStr;
	}

	/**
	 * 设置：颁奖单位
	 */
	public void setAwardingUnit(String awardingUnit) {
		this.awardingUnit = awardingUnit;
	}
	/**
	 * 获取：颁奖单位
	 */
	public String getAwardingUnit() {
		return awardingUnit;
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