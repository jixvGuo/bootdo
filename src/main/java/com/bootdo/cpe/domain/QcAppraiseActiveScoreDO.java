package com.bootdo.cpe.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 质量管理小组活动现场评价表
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-02-16 01:15:51
 */
public class QcAppraiseActiveScoreDO implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	private Integer id;
	//
	private Integer optUid;
	//
	private Integer proId;
	//
	private String taskId;
	//
	private String applyId;
	//质量管理小组活动的组织得分
	private BigDecimal organizationScore;
	//活动情况与活动记录得分
	private BigDecimal activeDescScore;
	//活动真实性与有效性得分
	private BigDecimal activeRealScore;
	//成果的维持与巩固得分
	private BigDecimal resultMaintainScore;
	//质量管理小组教育得分
	private BigDecimal groupEducationScore;
	//总分
	private BigDecimal totalScore;

	private String totalScoreStr = "";


	//总体评价
	private String sumRecommend = "" ;
	//
	private Date created;
	//
	private Date updated;
	//
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
	 * 设置：质量管理小组活动的组织得分
	 */
	public void setOrganizationScore(BigDecimal organizationScore) {
		this.organizationScore = organizationScore;
	}
	/**
	 * 获取：质量管理小组活动的组织得分
	 */
	public BigDecimal getOrganizationScore() {
		return organizationScore;
	}
	/**
	 * 设置：活动情况与活动记录得分
	 */
	public void setActiveDescScore(BigDecimal activeDescScore) {
		this.activeDescScore = activeDescScore;
	}
	/**
	 * 获取：活动情况与活动记录得分
	 */
	public BigDecimal getActiveDescScore() {
		return activeDescScore;
	}
	/**
	 * 设置：活动真实性与有效性得分
	 */
	public void setActiveRealScore(BigDecimal activeRealScore) {
		this.activeRealScore = activeRealScore;
	}
	/**
	 * 获取：活动真实性与有效性得分
	 */
	public BigDecimal getActiveRealScore() {
		return activeRealScore;
	}
	/**
	 * 设置：成果的维持与巩固得分
	 */
	public void setResultMaintainScore(BigDecimal resultMaintainScore) {
		this.resultMaintainScore = resultMaintainScore;
	}
	/**
	 * 获取：成果的维持与巩固得分
	 */
	public BigDecimal getResultMaintainScore() {
		return resultMaintainScore;
	}
	/**
	 * 设置：质量管理小组教育得分
	 */
	public void setGroupEducationScore(BigDecimal groupEducationScore) {
		this.groupEducationScore = groupEducationScore;
	}
	/**
	 * 获取：质量管理小组教育得分
	 */
	public BigDecimal getGroupEducationScore() {
		return groupEducationScore;
	}
	/**
	 * 设置：总分
	 */
	public void setTotalScore(BigDecimal totalScore) {
		this.totalScore = totalScore;
		totalScoreStr = this.totalScore + "" ;
	}
	/**
	 * 获取：总分
	 */
	public BigDecimal getTotalScore() {
		return totalScore;
	}
	/**
	 * 设置：总体评价
	 */
	public void setSumRecommend(String sumRecommend) {
		this.sumRecommend = sumRecommend;

	}
	/**
	 * 获取：总体评价
	 */
	public String getSumRecommend() {
		return sumRecommend;
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
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
	/**
	 * 获取：
	 */
	public Integer getDeleted() {
		return deleted;
	}
}
