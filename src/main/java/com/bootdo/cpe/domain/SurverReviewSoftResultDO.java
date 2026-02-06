package com.bootdo.cpe.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 软件类审查表格
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-04-13 07:07:29
 */
public class SurverReviewSoftResultDO extends SurverReviewProBaseInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	private Integer id;
	//册数
	private String numCopies;
	//是否申报单位自行、合作及二次开发
	private String unitIsDevelopment;
	//是否局级一二等奖
	private String bureauIsFirst;
	//上级意见及盖章
	private String superiorIsOpionion;
	//鉴定证书或行业评测文件\软件著作权
	private String certIsAuth;
	//推广应用证明(两项以上工程使用个检验)
	private String popularizeIsUse;
	//申报材料存在问题
	private String applySourceQuestion;
	//问题反馈情况
	private String feedbackInfo;
	//项目联系人及电话
	private String proContact;
	//主要完成人
	private String mainUser;
	//使用说明书/用户手册
	private String manualIsUse;
	//鉴定证书
	private String certIsOk;
	//推广应用时间、范围、数量及经济效益证明
	private String popularizeIsCert;
	//局级一、二等奖证书或文件
	private String bureauIsCert;
	//查新报告(报一等)
	private String viewNewIsReport;
	//形式审查意见
	private String reviewResult;
	//
	private String remarks;
	//
	private Integer optUid;
	//
	private String taskId;
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
	 * 设置：册数
	 */
	public void setNumCopies(String numCopies) {
		this.numCopies = numCopies;
	}
	/**
	 * 获取：册数
	 */
	public String getNumCopies() {
		return numCopies;
	}
	/**
	 * 设置：是否申报单位自行、合作及二次开发
	 */
	public void setUnitIsDevelopment(String unitIsDevelopment) {
		this.unitIsDevelopment = unitIsDevelopment;
	}
	/**
	 * 获取：是否申报单位自行、合作及二次开发
	 */
	public String getUnitIsDevelopment() {
		return unitIsDevelopment;
	}
	/**
	 * 设置：是否局级一二等奖
	 */
	public void setBureauIsFirst(String bureauIsFirst) {
		this.bureauIsFirst = bureauIsFirst;
	}
	/**
	 * 获取：是否局级一二等奖
	 */
	public String getBureauIsFirst() {
		return bureauIsFirst;
	}
	/**
	 * 设置：上级意见及盖章
	 */
	public void setSuperiorIsOpionion(String superiorIsOpionion) {
		this.superiorIsOpionion = superiorIsOpionion;
	}
	/**
	 * 获取：上级意见及盖章
	 */
	public String getSuperiorIsOpionion() {
		return superiorIsOpionion;
	}
	/**
	 * 设置：鉴定证书或行业评测文件\软件著作权
	 */
	public void setCertIsAuth(String certIsAuth) {
		this.certIsAuth = certIsAuth;
	}
	/**
	 * 获取：鉴定证书或行业评测文件\软件著作权
	 */
	public String getCertIsAuth() {
		return certIsAuth;
	}
	/**
	 * 设置：推广应用证明(两项以上工程使用个检验)
	 */
	public void setPopularizeIsUse(String popularizeIsUse) {
		this.popularizeIsUse = popularizeIsUse;
	}
	/**
	 * 获取：推广应用证明(两项以上工程使用个检验)
	 */
	public String getPopularizeIsUse() {
		return popularizeIsUse;
	}
	/**
	 * 设置：申报材料存在问题
	 */
	public void setApplySourceQuestion(String applySourceQuestion) {
		this.applySourceQuestion = applySourceQuestion;
	}
	/**
	 * 获取：申报材料存在问题
	 */
	public String getApplySourceQuestion() {
		return applySourceQuestion;
	}
	/**
	 * 设置：问题反馈情况
	 */
	public void setFeedbackInfo(String feedbackInfo) {
		this.feedbackInfo = feedbackInfo;
	}
	/**
	 * 获取：问题反馈情况
	 */
	public String getFeedbackInfo() {
		return feedbackInfo;
	}
	/**
	 * 设置：项目联系人及电话
	 */
	public void setProContact(String proContact) {
		this.proContact = proContact;
	}
	/**
	 * 获取：项目联系人及电话
	 */
	public String getProContact() {
		return proContact;
	}
	/**
	 * 设置：主要完成人
	 */
	public void setMainUser(String mainUser) {
		this.mainUser = mainUser;
	}
	/**
	 * 获取：主要完成人
	 */
	public String getMainUser() {
		return mainUser;
	}
	/**
	 * 设置：使用说明书/用户手册
	 */
	public void setManualIsUse(String manualIsUse) {
		this.manualIsUse = manualIsUse;
	}
	/**
	 * 获取：使用说明书/用户手册
	 */
	public String getManualIsUse() {
		return manualIsUse;
	}
	/**
	 * 设置：鉴定证书
	 */
	public void setCertIsOk(String certIsOk) {
		this.certIsOk = certIsOk;
	}
	/**
	 * 获取：鉴定证书
	 */
	public String getCertIsOk() {
		return certIsOk;
	}
	/**
	 * 设置：推广应用时间、范围、数量及经济效益证明
	 */
	public void setPopularizeIsCert(String popularizeIsCert) {
		this.popularizeIsCert = popularizeIsCert;
	}
	/**
	 * 获取：推广应用时间、范围、数量及经济效益证明
	 */
	public String getPopularizeIsCert() {
		return popularizeIsCert;
	}
	/**
	 * 设置：局级一、二等奖证书或文件
	 */
	public void setBureauIsCert(String bureauIsCert) {
		this.bureauIsCert = bureauIsCert;
	}
	/**
	 * 获取：局级一、二等奖证书或文件
	 */
	public String getBureauIsCert() {
		return bureauIsCert;
	}
	/**
	 * 设置：查新报告(报一等)
	 */
	public void setViewNewIsReport(String viewNewIsReport) {
		this.viewNewIsReport = viewNewIsReport;
	}
	/**
	 * 获取：查新报告(报一等)
	 */
	public String getViewNewIsReport() {
		return viewNewIsReport;
	}
	/**
	 * 设置：形式审查意见
	 */
	public void setReviewResult(String reviewResult) {
		this.reviewResult = reviewResult;
	}
	/**
	 * 获取：形式审查意见
	 */
	public String getReviewResult() {
		return reviewResult;
	}
	/**
	 * 设置：
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * 获取：
	 */
	public String getRemarks() {
		return remarks;
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
