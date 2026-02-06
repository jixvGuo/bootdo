package com.bootdo.cpe.petroleum_engineering_award.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 *  获奖信息表
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2021-02-20 22:55:26
 */
public class OilAwardGetInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//获奖类型
	private String typesAwards;
	//
	private String assOilAwardGetInfocol;
	//获奖名称
	private String nameAward;
	//颁发单位

	private String issuingUnit;
	//颁发时间
	private String timeIssue;
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
	 * 设置：获奖类型
	 */
	public void setTypesAwards(String typesAwards) {
		this.typesAwards = typesAwards;
	}
	/**
	 * 获取：获奖类型
	 */
	public String getTypesAwards() {
		return typesAwards;
	}
	/**
	 * 设置：
	 */
	public void setAssOilAwardGetInfocol(String assOilAwardGetInfocol) {
		this.assOilAwardGetInfocol = assOilAwardGetInfocol;
	}
	/**
	 * 获取：
	 */
	public String getAssOilAwardGetInfocol() {
		return assOilAwardGetInfocol;
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
	 * 设置：颁发单位

	 */
	public void setIssuingUnit(String issuingUnit) {
		this.issuingUnit = issuingUnit;
	}
	/**
	 * 获取：颁发单位

	 */
	public String getIssuingUnit() {
		return issuingUnit;
	}
	/**
	 * 设置：颁发时间
	 */
	public void setTimeIssue(String timeIssue) {
		this.timeIssue = timeIssue;
	}
	/**
	 * 获取：颁发时间
	 */
	public String getTimeIssue() {
		return timeIssue;
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
