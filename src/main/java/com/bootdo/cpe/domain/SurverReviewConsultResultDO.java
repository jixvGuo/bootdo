package com.bootdo.cpe.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 咨询类形式审查模板
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-04-13 07:07:29
 */
public class SurverReviewConsultResultDO extends SurverReviewProBaseInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	private Integer id;
	//册数
	private String numCopies;
	//
	private String type;
	//是否是完整项目
	private String proIsComplete;
	//审查程序是否完善
	private String checkIsOk;
	//是否运行一年以上
	private String runIsOneYear;
	//申报单位是否有咨询资质
	private String unitIsConsultCert;
	//申报单位是否第一完成单位
	private String unitIsFirstComplete;
	//是否局级一二等奖
	private String bureauIsFirstAward;
	//上级意见及盖章
	private String superiorIsOpionion;
	//申报成果是否经同行庄家评审或鉴定，经有关单位采纳
	private String applyRstIsAccept;
	//成果报告书
	private String resultIsReport;
	//评价或鉴定书
	private String evaluateIsCert;
	//交(竣)工验收证明
	private String completeIsCert;
	//局一、二等奖证书或文件
	private String bureauIsAwardCert;
	//单位三年无事故安全证明
	private String unitIsThreeYearSafe;
	//查新报告(报一等奖)
	private String viewNewIsReport;
	//合同
	private String contractIsOk;
	//上级主管部门或建设单位评价意见
	private String superiorUnitIsEvaluate;
	//评估、评审意见
	private String reviewIsEvaluate;
	//上级审批意见
	private String superiorReviewIsEvaluate;
	//建设单位验收及评价意见
	private String buildUnitIsEvaluate;
	//建成投产项目使用效果（经济、社会效益）证明
	private String useEffectIsCert;
	//专家评审、鉴定意见
	private String specilistIsExpertOpinion;
	//成果采纳证明
	private String resultIsAcceptCert;
	//委托方成果质量评价
	private String resultIsQualityEval;
	//专家评审、鉴定意见(后评价报告)
	private String specilistIsEvaluate;
	//发表证明
	private String publishIsCert;
	//高度评价证明
	private String highEvaluateIsCert;
	//项目简单介绍
	private String proIsDesc;
	//申报理由
	private String applyIsReason;
	//主要经济指标对比情况
	private String economicIsContrast;
	//主要技术成果对比
	private String technologyIsContrast;
	//采用新理论、新方法、新技术名称及来源
	private String newMethodIsOk;
	//规划图纸
	private String planIsDrawing;
	//照片
	private String photoIsOk;
	//需要时VCD
	private String needIsVcd;
	//申报材料存在问题
	private String applySourceQuestion;
	//问题反馈情况
	private String feedbackInfo;
	//项目联系人及电话
	private String proContact;
	//主要完成人
	private String mainUser;
	//形式审查意见
	private String reviewResult;
	//备注
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
	 * 设置：
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：是否是完整项目
	 */
	public void setProIsComplete(String proIsComplete) {
		this.proIsComplete = proIsComplete;
	}
	/**
	 * 获取：是否是完整项目
	 */
	public String getProIsComplete() {
		return proIsComplete;
	}
	/**
	 * 设置：审查程序是否完善
	 */
	public void setCheckIsOk(String checkIsOk) {
		this.checkIsOk = checkIsOk;
	}
	/**
	 * 获取：审查程序是否完善
	 */
	public String getCheckIsOk() {
		return checkIsOk;
	}
	/**
	 * 设置：是否运行一年以上
	 */
	public void setRunIsOneYear(String runIsOneYear) {
		this.runIsOneYear = runIsOneYear;
	}
	/**
	 * 获取：是否运行一年以上
	 */
	public String getRunIsOneYear() {
		return runIsOneYear;
	}
	/**
	 * 设置：申报单位是否有咨询资质
	 */
	public void setUnitIsConsultCert(String unitIsConsultCert) {
		this.unitIsConsultCert = unitIsConsultCert;
	}
	/**
	 * 获取：申报单位是否有咨询资质
	 */
	public String getUnitIsConsultCert() {
		return unitIsConsultCert;
	}
	/**
	 * 设置：申报单位是否第一完成单位
	 */
	public void setUnitIsFirstComplete(String unitIsFirstComplete) {
		this.unitIsFirstComplete = unitIsFirstComplete;
	}
	/**
	 * 获取：申报单位是否第一完成单位
	 */
	public String getUnitIsFirstComplete() {
		return unitIsFirstComplete;
	}
	/**
	 * 设置：是否局级一二等奖
	 */
	public void setBureauIsFirstAward(String bureauIsFirstAward) {
		this.bureauIsFirstAward = bureauIsFirstAward;
	}
	/**
	 * 获取：是否局级一二等奖
	 */
	public String getBureauIsFirstAward() {
		return bureauIsFirstAward;
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
	 * 设置：申报成果是否经同行庄家评审或鉴定，经有关单位采纳
	 */
	public void setApplyRstIsAccept(String applyRstIsAccept) {
		this.applyRstIsAccept = applyRstIsAccept;
	}
	/**
	 * 获取：申报成果是否经同行庄家评审或鉴定，经有关单位采纳
	 */
	public String getApplyRstIsAccept() {
		return applyRstIsAccept;
	}
	/**
	 * 设置：成果报告书
	 */
	public void setResultIsReport(String resultIsReport) {
		this.resultIsReport = resultIsReport;
	}
	/**
	 * 获取：成果报告书
	 */
	public String getResultIsReport() {
		return resultIsReport;
	}
	/**
	 * 设置：评价或鉴定书
	 */
	public void setEvaluateIsCert(String evaluateIsCert) {
		this.evaluateIsCert = evaluateIsCert;
	}
	/**
	 * 获取：评价或鉴定书
	 */
	public String getEvaluateIsCert() {
		return evaluateIsCert;
	}
	/**
	 * 设置：交(竣)工验收证明
	 */
	public void setCompleteIsCert(String completeIsCert) {
		this.completeIsCert = completeIsCert;
	}
	/**
	 * 获取：交(竣)工验收证明
	 */
	public String getCompleteIsCert() {
		return completeIsCert;
	}
	/**
	 * 设置：局一、二等奖证书或文件
	 */
	public void setBureauIsAwardCert(String bureauIsAwardCert) {
		this.bureauIsAwardCert = bureauIsAwardCert;
	}
	/**
	 * 获取：局一、二等奖证书或文件
	 */
	public String getBureauIsAwardCert() {
		return bureauIsAwardCert;
	}
	/**
	 * 设置：单位三年无事故安全证明
	 */
	public void setUnitIsThreeYearSafe(String unitIsThreeYearSafe) {
		this.unitIsThreeYearSafe = unitIsThreeYearSafe;
	}
	/**
	 * 获取：单位三年无事故安全证明
	 */
	public String getUnitIsThreeYearSafe() {
		return unitIsThreeYearSafe;
	}
	/**
	 * 设置：查新报告(报一等奖)
	 */
	public void setViewNewIsReport(String viewNewIsReport) {
		this.viewNewIsReport = viewNewIsReport;
	}
	/**
	 * 获取：查新报告(报一等奖)
	 */
	public String getViewNewIsReport() {
		return viewNewIsReport;
	}
	/**
	 * 设置：合同
	 */
	public void setContractIsOk(String contractIsOk) {
		this.contractIsOk = contractIsOk;
	}
	/**
	 * 获取：合同
	 */
	public String getContractIsOk() {
		return contractIsOk;
	}
	/**
	 * 设置：上级主管部门或建设单位评价意见
	 */
	public void setSuperiorUnitIsEvaluate(String superiorUnitIsEvaluate) {
		this.superiorUnitIsEvaluate = superiorUnitIsEvaluate;
	}
	/**
	 * 获取：上级主管部门或建设单位评价意见
	 */
	public String getSuperiorUnitIsEvaluate() {
		return superiorUnitIsEvaluate;
	}
	/**
	 * 设置：评估、评审意见
	 */
	public void setReviewIsEvaluate(String reviewIsEvaluate) {
		this.reviewIsEvaluate = reviewIsEvaluate;
	}
	/**
	 * 获取：评估、评审意见
	 */
	public String getReviewIsEvaluate() {
		return reviewIsEvaluate;
	}
	/**
	 * 设置：上级审批意见
	 */
	public void setSuperiorReviewIsEvaluate(String superiorReviewIsEvaluate) {
		this.superiorReviewIsEvaluate = superiorReviewIsEvaluate;
	}
	/**
	 * 获取：上级审批意见
	 */
	public String getSuperiorReviewIsEvaluate() {
		return superiorReviewIsEvaluate;
	}
	/**
	 * 设置：建设单位验收及评价意见
	 */
	public void setBuildUnitIsEvaluate(String buildUnitIsEvaluate) {
		this.buildUnitIsEvaluate = buildUnitIsEvaluate;
	}
	/**
	 * 获取：建设单位验收及评价意见
	 */
	public String getBuildUnitIsEvaluate() {
		return buildUnitIsEvaluate;
	}

	public String getUseEffectIsCert() {
		return useEffectIsCert;
	}

	public void setUseEffectIsCert(String useEffectIsCert) {
		this.useEffectIsCert = useEffectIsCert;
	}

	public String getSpecilistIsExpertOpinion() {
		return specilistIsExpertOpinion;
	}

	public void setSpecilistIsExpertOpinion(String specilistIsExpertOpinion) {
		this.specilistIsExpertOpinion = specilistIsExpertOpinion;
	}

	/**
	 * 设置：成果采纳证明
	 */
	public void setResultIsAcceptCert(String resultIsAcceptCert) {
		this.resultIsAcceptCert = resultIsAcceptCert;
	}
	/**
	 * 获取：成果采纳证明
	 */
	public String getResultIsAcceptCert() {
		return resultIsAcceptCert;
	}
	/**
	 * 设置：委托方成果质量评价
	 */
	public void setResultIsQualityEval(String resultIsQualityEval) {
		this.resultIsQualityEval = resultIsQualityEval;
	}
	/**
	 * 获取：委托方成果质量评价
	 */
	public String getResultIsQualityEval() {
		return resultIsQualityEval;
	}
	/**
	 * 设置：专家评审、鉴定意见(后评价报告)
	 */
	public void setSpecilistIsEvaluate(String specilistIsEvaluate) {
		this.specilistIsEvaluate = specilistIsEvaluate;
	}
	/**
	 * 获取：专家评审、鉴定意见(后评价报告)
	 */
	public String getSpecilistIsEvaluate() {
		return specilistIsEvaluate;
	}
	/**
	 * 设置：发表证明
	 */
	public void setPublishIsCert(String publishIsCert) {
		this.publishIsCert = publishIsCert;
	}
	/**
	 * 获取：发表证明
	 */
	public String getPublishIsCert() {
		return publishIsCert;
	}
	/**
	 * 设置：高度评价证明
	 */
	public void setHighEvaluateIsCert(String highEvaluateIsCert) {
		this.highEvaluateIsCert = highEvaluateIsCert;
	}
	/**
	 * 获取：高度评价证明
	 */
	public String getHighEvaluateIsCert() {
		return highEvaluateIsCert;
	}
	/**
	 * 设置：项目简单介绍
	 */
	public void setProIsDesc(String proIsDesc) {
		this.proIsDesc = proIsDesc;
	}
	/**
	 * 获取：项目简单介绍
	 */
	public String getProIsDesc() {
		return proIsDesc;
	}
	/**
	 * 设置：申报理由
	 */
	public void setApplyIsReason(String applyIsReason) {
		this.applyIsReason = applyIsReason;
	}
	/**
	 * 获取：申报理由
	 */
	public String getApplyIsReason() {
		return applyIsReason;
	}
	/**
	 * 设置：主要经济指标对比情况
	 */
	public void setEconomicIsContrast(String economicIsContrast) {
		this.economicIsContrast = economicIsContrast;
	}
	/**
	 * 获取：主要经济指标对比情况
	 */
	public String getEconomicIsContrast() {
		return economicIsContrast;
	}
	/**
	 * 设置：主要技术成果对比
	 */
	public void setTechnologyIsContrast(String technologyIsContrast) {
		this.technologyIsContrast = technologyIsContrast;
	}
	/**
	 * 获取：主要技术成果对比
	 */
	public String getTechnologyIsContrast() {
		return technologyIsContrast;
	}
	/**
	 * 设置：采用新理论、新方法、新技术名称及来源
	 */
	public void setNewMethodIsOk(String newMethodIsOk) {
		this.newMethodIsOk = newMethodIsOk;
	}
	/**
	 * 获取：采用新理论、新方法、新技术名称及来源
	 */
	public String getNewMethodIsOk() {
		return newMethodIsOk;
	}
	/**
	 * 设置：规划图纸
	 */
	public void setPlanIsDrawing(String planIsDrawing) {
		this.planIsDrawing = planIsDrawing;
	}
	/**
	 * 获取：规划图纸
	 */
	public String getPlanIsDrawing() {
		return planIsDrawing;
	}
	/**
	 * 设置：照片
	 */
	public void setPhotoIsOk(String photoIsOk) {
		this.photoIsOk = photoIsOk;
	}
	/**
	 * 获取：照片
	 */
	public String getPhotoIsOk() {
		return photoIsOk;
	}
	/**
	 * 设置：需要时VCD
	 */
	public void setNeedIsVcd(String needIsVcd) {
		this.needIsVcd = needIsVcd;
	}
	/**
	 * 获取：需要时VCD
	 */
	public String getNeedIsVcd() {
		return needIsVcd;
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
	 * 设置：备注
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * 获取：备注
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
