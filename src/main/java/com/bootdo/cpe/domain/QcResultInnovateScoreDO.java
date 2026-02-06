package com.bootdo.cpe.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 创新型课题成果评价表
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-02-06 22:48:27
 */
public class QcResultInnovateScoreDO implements Serializable {
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
	//选题得分
	private BigDecimal selSocre;
	//提出方案并确定最佳方案
	private BigDecimal bestPlanScore;
	//原因分析得分
	private BigDecimal reasonScore;
	//对策与实施得分
	private BigDecimal strategyExecuteScore;
	//效果得分
	private BigDecimal effectScore;
	//成果报告得分
	private BigDecimal reportScore;
	//特点得分
	private BigDecimal characteristicScore;
	//总体评价
	private String appraiseSum;
	//评价组长签字
	private String appraiseSign;
	//总体评价
	private String sumRecommend = "";
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
	 * 设置：选题得分
	 */
	public void setSelSocre(BigDecimal selSocre) {
		this.selSocre = selSocre;
	}
	/**
	 * 获取：选题得分
	 */
	public BigDecimal getSelSocre() {
		return selSocre;
	}
	/**
	 * 设置：提出方案并确定最佳方案
	 */
	public void setBestPlanScore(BigDecimal bestPlanScore) {
		this.bestPlanScore = bestPlanScore;
	}
	/**
	 * 获取：提出方案并确定最佳方案
	 */
	public BigDecimal getBestPlanScore() {
		return bestPlanScore;
	}
	/**
	 * 设置：原因分析得分
	 */
	public void setReasonScore(BigDecimal reasonScore) {
		this.reasonScore = reasonScore;
	}
	/**
	 * 获取：原因分析得分
	 */
	public BigDecimal getReasonScore() {
		return reasonScore;
	}
	/**
	 * 设置：对策与实施得分
	 */
	public void setStrategyExecuteScore(BigDecimal strategyExecuteScore) {
		this.strategyExecuteScore = strategyExecuteScore;
	}
	/**
	 * 获取：对策与实施得分
	 */
	public BigDecimal getStrategyExecuteScore() {
		return strategyExecuteScore;
	}
	/**
	 * 设置：效果得分
	 */
	public void setEffectScore(BigDecimal effectScore) {
		this.effectScore = effectScore;
	}
	/**
	 * 获取：效果得分
	 */
	public BigDecimal getEffectScore() {
		return effectScore;
	}
	/**
	 * 设置：成果报告得分
	 */
	public void setReportScore(BigDecimal reportScore) {
		this.reportScore = reportScore;
	}
	/**
	 * 获取：成果报告得分
	 */
	public BigDecimal getReportScore() {
		return reportScore;
	}
	/**
	 * 设置：特点得分
	 */
	public void setCharacteristicScore(BigDecimal characteristicScore) {
		this.characteristicScore = characteristicScore;
	}
	/**
	 * 获取：特点得分
	 */
	public BigDecimal getCharacteristicScore() {
		return characteristicScore;
	}
	/**
	 * 设置：总体评价
	 */
	public void setAppraiseSum(String appraiseSum) {
		this.appraiseSum = appraiseSum;
	}
	/**
	 * 获取：总体评价
	 */
	public String getAppraiseSum() {
		return appraiseSum;
	}
	/**
	 * 设置：评价组长签字
	 */
	public void setAppraiseSign(String appraiseSign) {
		this.appraiseSign = appraiseSign;
	}
	/**
	 * 获取：评价组长签字
	 */
	public String getAppraiseSign() {
		return appraiseSign;
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
