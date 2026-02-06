package com.bootdo.cpe.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 质量管理小组概况统计表
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-01-31 16:01:32
 */
public class QcSurveyStatisticInfoDO implements Serializable {
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
	//推行全面质量管理企业数
	private String enterpriseCount;
	//企业职工总数
	private String enterWorkerCount;
	//历年来登记注册QC小组累计数量
	private String regQcCount;
	//本年度登记注册QC小组
	private String thisYearRegCount;
	//QC小组当年取得成果数量
	private String thisYearResultCount;
	//本年度开展活动的QC小组数量
	private String thisYearActCount;
	//本年度开展活动的QC小组直接效益
	private String thisYearIncome;
	//本年度QC小组成果率=取得成果数/注册小组数×100%
	private String thisYearResultRate;
	//本年度QC小组普及率=参加QC小组人数/职工总数×100%
	private String thisYearWorkerRate;
	//参加QC小组培训
	private String trainCount;
	//本年度QC小组成员提合理化建议数量
	private String thisYearProposeCount;
	//具有中国质协QC小组活动推进者资格
	private String qualificationCount;
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
	 * 设置：推行全面质量管理企业数
	 */
	public void setEnterpriseCount(String enterpriseCount) {
		this.enterpriseCount = enterpriseCount;
	}
	/**
	 * 获取：推行全面质量管理企业数
	 */
	public String getEnterpriseCount() {
		return enterpriseCount;
	}
	/**
	 * 设置：企业职工总数
	 */
	public void setEnterWorkerCount(String enterWorkerCount) {
		this.enterWorkerCount = enterWorkerCount;
	}
	/**
	 * 获取：企业职工总数
	 */
	public String getEnterWorkerCount() {
		return enterWorkerCount;
	}
	/**
	 * 设置：历年来登记注册QC小组累计数量
	 */
	public void setRegQcCount(String regQcCount) {
		this.regQcCount = regQcCount;
	}
	/**
	 * 获取：历年来登记注册QC小组累计数量
	 */
	public String getRegQcCount() {
		return regQcCount;
	}
	/**
	 * 设置：本年度登记注册QC小组
	 */
	public void setThisYearRegCount(String thisYearRegCount) {
		this.thisYearRegCount = thisYearRegCount;
	}
	/**
	 * 获取：本年度登记注册QC小组
	 */
	public String getThisYearRegCount() {
		return thisYearRegCount;
	}
	/**
	 * 设置：QC小组当年取得成果数量
	 */
	public void setThisYearResultCount(String thisYearResultCount) {
		this.thisYearResultCount = thisYearResultCount;
	}
	/**
	 * 获取：QC小组当年取得成果数量
	 */
	public String getThisYearResultCount() {
		return thisYearResultCount;
	}
	/**
	 * 设置：本年度开展活动的QC小组数量
	 */
	public void setThisYearActCount(String thisYearActCount) {
		this.thisYearActCount = thisYearActCount;
	}
	/**
	 * 获取：本年度开展活动的QC小组数量
	 */
	public String getThisYearActCount() {
		return thisYearActCount;
	}
	/**
	 * 设置：本年度开展活动的QC小组直接效益
	 */
	public void setThisYearIncome(String thisYearIncome) {
		this.thisYearIncome = thisYearIncome;
	}
	/**
	 * 获取：本年度开展活动的QC小组直接效益
	 */
	public String getThisYearIncome() {
		return thisYearIncome;
	}
	/**
	 * 设置：本年度QC小组成果率=取得成果数/注册小组数×100%
	 */
	public void setThisYearResultRate(String thisYearResultRate) {
		this.thisYearResultRate = thisYearResultRate;
	}
	/**
	 * 获取：本年度QC小组成果率=取得成果数/注册小组数×100%
	 */
	public String getThisYearResultRate() {
		return thisYearResultRate;
	}
	/**
	 * 设置：本年度QC小组普及率=参加QC小组人数/职工总数×100%
	 */
	public void setThisYearWorkerRate(String thisYearWorkerRate) {
		this.thisYearWorkerRate = thisYearWorkerRate;
	}
	/**
	 * 获取：本年度QC小组普及率=参加QC小组人数/职工总数×100%
	 */
	public String getThisYearWorkerRate() {
		return thisYearWorkerRate;
	}
	/**
	 * 设置：参加QC小组培训
	 */
	public void setTrainCount(String trainCount) {
		this.trainCount = trainCount;
	}
	/**
	 * 获取：参加QC小组培训
	 */
	public String getTrainCount() {
		return trainCount;
	}
	/**
	 * 设置：本年度QC小组成员提合理化建议数量
	 */
	public void setThisYearProposeCount(String thisYearProposeCount) {
		this.thisYearProposeCount = thisYearProposeCount;
	}
	/**
	 * 获取：本年度QC小组成员提合理化建议数量
	 */
	public String getThisYearProposeCount() {
		return thisYearProposeCount;
	}
	/**
	 * 设置：具有中国质协QC小组活动推进者资格
	 */
	public void setQualificationCount(String qualificationCount) {
		this.qualificationCount = qualificationCount;
	}
	/**
	 * 获取：具有中国质协QC小组活动推进者资格
	 */
	public String getQualificationCount() {
		return qualificationCount;
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
