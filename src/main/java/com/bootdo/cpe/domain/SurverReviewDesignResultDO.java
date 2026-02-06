package com.bootdo.cpe.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 设计类审查表格
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-04-13 07:07:29
 */
public class SurverReviewDesignResultDO extends SurverReviewProBaseInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	private Integer id;
	//
	private String numCopies;
	//是否申报单位承担
	private String applyUnitIsBear;
	//是否局级一二等奖
	private String bureauIsFirstAward;
	//上级意见及盖章
	private String superiorIsOpionion;
	//建设单位证明的投运时间
	private String useTimeIsCert;
	//引进国外技术或中外合作设计，在国内建设且由申报单位进行初设
	private String technologyIsStart;
	//是否有批准立项文件或初设批复(分期、分单项或单位工程申报）
	private String fileIsApproval;
	//投资是否超概算
	private String investmentIsBeyond;
	//设计资质
	private String designIsCert;
	//单位三年无事故安全证明
	private String unitIsThreeSafe;
	//人员名单数
	private String userList;
	//地方政府审批意见
	private String localGovIsOpinion;
	//建设方评价意见
	private String buildIsOpinion;
	//合同
	private String contractIsCert;
	//上级或建设方证明
	private String superiorIsCert;
	//地方政府证明
	private String localGovIsCert;
	//查新报告(报一等奖)
	private String viewNewIsReport;
	//技术审定文件
	private String techFileIsCheck;
	//工程项目简单介绍
	private String proIsDesc;
	//申报理由
	private String applyIsReason;
	//主要经济指标对比情况
	private String mainEconomicIsOk;
	//主要技术成果对比情况
	private String mainTechnologyIsOk;
	//采用新技术的名称及来源
	private String newTechnologyIsOk;
	//总平面图
	private String generalLayoutIsOk;
	//主要工艺流程图
	private String workmanshipIsOk;
	//设备表
	private String equTableIsOk;
	//新工艺的系统图
	private String workmanshipSysIsOk;
	//总平面图（平、立、剖面）-普通建筑
	private String normalGeneralLayoutIsOk;
	//总平面图（平、立、剖面）-高层及大跨度
	private String highGeneralLayoutIsOk;
	//结构主要图纸-高层及大跨度
	private String structuralPicIsOk;
	//设备主要图纸-高层及大跨度
	private String equPicIsOk;
	//勘察报告-高层及大跨度
	private String surverIsReport;
	//各专业应用计算机软件表
	private String softListIsOk;
	//交竣工证明
	private String completeIsCert;
	//经济
	private String economicIsOk;
	//社会
	private String socialIsOk;
	//环境
	private String envIsOk;
	//安全
	private String safeIsOk;
	//工业项目主要原料、材料消耗定额与生产实际值对比表
	private String rawMaterialIsOk;
	//产品质量设计值与生产实际值对比表
	private String qualityIsOk;
	//公用工程消耗定额设计值与生产实际值对比表
	private String expendIsOk;
	//工业项目废渣、废液、废气排放量及排放指标设计值与生产实际值对比表
	private String residueIsOk;
	//需要时提供项目和新技术有关音像制品
	private String audioIsNeed;
	//申报材料存在问题
	private String applySourceQuestion;
	//问题回馈情况
	private String feedbackInfo;
	//项目联系人及电话
	private String proContact;
	//主要完成人
	private String mainUser;
	//
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
	 * 设置：
	 */
	public void setNumCopies(String numCopies) {
		this.numCopies = numCopies;
	}
	/**
	 * 获取：
	 */
	public String getNumCopies() {
		return numCopies;
	}
	/**
	 * 设置：是否申报单位承担
	 */
	public void setApplyUnitIsBear(String applyUnitIsBear) {
		this.applyUnitIsBear = applyUnitIsBear;
	}
	/**
	 * 获取：是否申报单位承担
	 */
	public String getApplyUnitIsBear() {
		return applyUnitIsBear;
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
	 * 设置：建设单位证明的投运时间
	 */
	public void setUseTimeIsCert(String useTimeIsCert) {
		this.useTimeIsCert = useTimeIsCert;
	}
	/**
	 * 获取：建设单位证明的投运时间
	 */
	public String getUseTimeIsCert() {
		return useTimeIsCert;
	}
	/**
	 * 设置：引进国外技术或中外合作设计，在国内建设且由申报单位进行初设
	 */
	public void setTechnologyIsStart(String technologyIsStart) {
		this.technologyIsStart = technologyIsStart;
	}
	/**
	 * 获取：引进国外技术或中外合作设计，在国内建设且由申报单位进行初设
	 */
	public String getTechnologyIsStart() {
		return technologyIsStart;
	}
	/**
	 * 设置：是否有批准立项文件或初设批复(分期、分单项或单位工程申报）
	 */
	public void setFileIsApproval(String fileIsApproval) {
		this.fileIsApproval = fileIsApproval;
	}
	/**
	 * 获取：是否有批准立项文件或初设批复(分期、分单项或单位工程申报）
	 */
	public String getFileIsApproval() {
		return fileIsApproval;
	}
	/**
	 * 设置：投资是否超概算
	 */
	public void setInvestmentIsBeyond(String investmentIsBeyond) {
		this.investmentIsBeyond = investmentIsBeyond;
	}
	/**
	 * 获取：投资是否超概算
	 */
	public String getInvestmentIsBeyond() {
		return investmentIsBeyond;
	}
	/**
	 * 设置：设计资质
	 */
	public void setDesignIsCert(String designIsCert) {
		this.designIsCert = designIsCert;
	}
	/**
	 * 获取：设计资质
	 */
	public String getDesignIsCert() {
		return designIsCert;
	}
	/**
	 * 设置：单位三年无事故安全证明
	 */
	public void setUnitIsThreeSafe(String unitIsThreeSafe) {
		this.unitIsThreeSafe = unitIsThreeSafe;
	}
	/**
	 * 获取：单位三年无事故安全证明
	 */
	public String getUnitIsThreeSafe() {
		return unitIsThreeSafe;
	}
	/**
	 * 设置：人员名单数
	 */
	public void setUserList(String userList) {
		this.userList = userList;
	}
	/**
	 * 获取：人员名单数
	 */
	public String getUserList() {
		return userList;
	}
	/**
	 * 设置：地方政府审批意见
	 */
	public void setLocalGovIsOpinion(String localGovIsOpinion) {
		this.localGovIsOpinion = localGovIsOpinion;
	}
	/**
	 * 获取：地方政府审批意见
	 */
	public String getLocalGovIsOpinion() {
		return localGovIsOpinion;
	}
	/**
	 * 设置：建设方评价意见
	 */
	public void setBuildIsOpinion(String buildIsOpinion) {
		this.buildIsOpinion = buildIsOpinion;
	}
	/**
	 * 获取：建设方评价意见
	 */
	public String getBuildIsOpinion() {
		return buildIsOpinion;
	}
	/**
	 * 设置：合同
	 */
	public void setContractIsCert(String contractIsCert) {
		this.contractIsCert = contractIsCert;
	}
	/**
	 * 获取：合同
	 */
	public String getContractIsCert() {
		return contractIsCert;
	}
	/**
	 * 设置：上级或建设方证明
	 */
	public void setSuperiorIsCert(String superiorIsCert) {
		this.superiorIsCert = superiorIsCert;
	}
	/**
	 * 获取：上级或建设方证明
	 */
	public String getSuperiorIsCert() {
		return superiorIsCert;
	}
	/**
	 * 设置：地方政府证明
	 */
	public void setLocalGovIsCert(String localGovIsCert) {
		this.localGovIsCert = localGovIsCert;
	}
	/**
	 * 获取：地方政府证明
	 */
	public String getLocalGovIsCert() {
		return localGovIsCert;
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
	 * 设置：技术审定文件
	 */
	public void setTechFileIsCheck(String techFileIsCheck) {
		this.techFileIsCheck = techFileIsCheck;
	}
	/**
	 * 获取：技术审定文件
	 */
	public String getTechFileIsCheck() {
		return techFileIsCheck;
	}
	/**
	 * 设置：工程项目简单介绍
	 */
	public void setProIsDesc(String proIsDesc) {
		this.proIsDesc = proIsDesc;
	}
	/**
	 * 获取：工程项目简单介绍
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
	public void setMainEconomicIsOk(String mainEconomicIsOk) {
		this.mainEconomicIsOk = mainEconomicIsOk;
	}
	/**
	 * 获取：主要经济指标对比情况
	 */
	public String getMainEconomicIsOk() {
		return mainEconomicIsOk;
	}
	/**
	 * 设置：主要技术成果对比情况
	 */
	public void setMainTechnologyIsOk(String mainTechnologyIsOk) {
		this.mainTechnologyIsOk = mainTechnologyIsOk;
	}
	/**
	 * 获取：主要技术成果对比情况
	 */
	public String getMainTechnologyIsOk() {
		return mainTechnologyIsOk;
	}
	/**
	 * 设置：采用新技术的名称及来源
	 */
	public void setNewTechnologyIsOk(String newTechnologyIsOk) {
		this.newTechnologyIsOk = newTechnologyIsOk;
	}
	/**
	 * 获取：采用新技术的名称及来源
	 */
	public String getNewTechnologyIsOk() {
		return newTechnologyIsOk;
	}
	/**
	 * 设置：总平面图
	 */
	public void setGeneralLayoutIsOk(String generalLayoutIsOk) {
		this.generalLayoutIsOk = generalLayoutIsOk;
	}
	/**
	 * 获取：总平面图
	 */
	public String getGeneralLayoutIsOk() {
		return generalLayoutIsOk;
	}
	/**
	 * 设置：主要工艺流程图
	 */
	public void setWorkmanshipIsOk(String workmanshipIsOk) {
		this.workmanshipIsOk = workmanshipIsOk;
	}
	/**
	 * 获取：主要工艺流程图
	 */
	public String getWorkmanshipIsOk() {
		return workmanshipIsOk;
	}
	/**
	 * 设置：设备表
	 */
	public void setEquTableIsOk(String equTableIsOk) {
		this.equTableIsOk = equTableIsOk;
	}
	/**
	 * 获取：设备表
	 */
	public String getEquTableIsOk() {
		return equTableIsOk;
	}
	/**
	 * 设置：新工艺的系统图
	 */
	public void setWorkmanshipSysIsOk(String workmanshipSysIsOk) {
		this.workmanshipSysIsOk = workmanshipSysIsOk;
	}
	/**
	 * 获取：新工艺的系统图
	 */
	public String getWorkmanshipSysIsOk() {
		return workmanshipSysIsOk;
	}
	/**
	 * 设置：总平面图（平、立、剖面）-普通建筑
	 */
	public void setNormalGeneralLayoutIsOk(String normalGeneralLayoutIsOk) {
		this.normalGeneralLayoutIsOk = normalGeneralLayoutIsOk;
	}
	/**
	 * 获取：总平面图（平、立、剖面）-普通建筑
	 */
	public String getNormalGeneralLayoutIsOk() {
		return normalGeneralLayoutIsOk;
	}
	/**
	 * 设置：总平面图（平、立、剖面）-高层及大跨度
	 */
	public void setHighGeneralLayoutIsOk(String highGeneralLayoutIsOk) {
		this.highGeneralLayoutIsOk = highGeneralLayoutIsOk;
	}
	/**
	 * 获取：总平面图（平、立、剖面）-高层及大跨度
	 */
	public String getHighGeneralLayoutIsOk() {
		return highGeneralLayoutIsOk;
	}
	/**
	 * 设置：结构主要图纸-高层及大跨度
	 */
	public void setStructuralPicIsOk(String structuralPicIsOk) {
		this.structuralPicIsOk = structuralPicIsOk;
	}
	/**
	 * 获取：结构主要图纸-高层及大跨度
	 */
	public String getStructuralPicIsOk() {
		return structuralPicIsOk;
	}
	/**
	 * 设置：设备主要图纸-高层及大跨度
	 */
	public void setEquPicIsOk(String equPicIsOk) {
		this.equPicIsOk = equPicIsOk;
	}
	/**
	 * 获取：设备主要图纸-高层及大跨度
	 */
	public String getEquPicIsOk() {
		return equPicIsOk;
	}
	/**
	 * 设置：勘察报告-高层及大跨度
	 */
	public void setSurverIsReport(String surverIsReport) {
		this.surverIsReport = surverIsReport;
	}
	/**
	 * 获取：勘察报告-高层及大跨度
	 */
	public String getSurverIsReport() {
		return surverIsReport;
	}
	/**
	 * 设置：各专业应用计算机软件表
	 */
	public void setSoftListIsOk(String softListIsOk) {
		this.softListIsOk = softListIsOk;
	}
	/**
	 * 获取：各专业应用计算机软件表
	 */
	public String getSoftListIsOk() {
		return softListIsOk;
	}
	/**
	 * 设置：交竣工证明
	 */
	public void setCompleteIsCert(String completeIsCert) {
		this.completeIsCert = completeIsCert;
	}
	/**
	 * 获取：交竣工证明
	 */
	public String getCompleteIsCert() {
		return completeIsCert;
	}
	/**
	 * 设置：经济
	 */
	public void setEconomicIsOk(String economicIsOk) {
		this.economicIsOk = economicIsOk;
	}
	/**
	 * 获取：经济
	 */
	public String getEconomicIsOk() {
		return economicIsOk;
	}
	/**
	 * 设置：社会
	 */
	public void setSocialIsOk(String socialIsOk) {
		this.socialIsOk = socialIsOk;
	}
	/**
	 * 获取：社会
	 */
	public String getSocialIsOk() {
		return socialIsOk;
	}
	/**
	 * 设置：环境
	 */
	public void setEnvIsOk(String envIsOk) {
		this.envIsOk = envIsOk;
	}
	/**
	 * 获取：环境
	 */
	public String getEnvIsOk() {
		return envIsOk;
	}
	/**
	 * 设置：安全
	 */
	public void setSafeIsOk(String safeIsOk) {
		this.safeIsOk = safeIsOk;
	}
	/**
	 * 获取：安全
	 */
	public String getSafeIsOk() {
		return safeIsOk;
	}
	/**
	 * 设置：工业项目主要原料、材料消耗定额与生产实际值对比表
	 */
	public void setRawMaterialIsOk(String rawMaterialIsOk) {
		this.rawMaterialIsOk = rawMaterialIsOk;
	}
	/**
	 * 获取：工业项目主要原料、材料消耗定额与生产实际值对比表
	 */
	public String getRawMaterialIsOk() {
		return rawMaterialIsOk;
	}
	/**
	 * 设置：产品质量设计值与生产实际值对比表
	 */
	public void setQualityIsOk(String qualityIsOk) {
		this.qualityIsOk = qualityIsOk;
	}
	/**
	 * 获取：产品质量设计值与生产实际值对比表
	 */
	public String getQualityIsOk() {
		return qualityIsOk;
	}
	/**
	 * 设置：公用工程消耗定额设计值与生产实际值对比表
	 */
	public void setExpendIsOk(String expendIsOk) {
		this.expendIsOk = expendIsOk;
	}
	/**
	 * 获取：公用工程消耗定额设计值与生产实际值对比表
	 */
	public String getExpendIsOk() {
		return expendIsOk;
	}
	/**
	 * 设置：工业项目废渣、废液、废气排放量及排放指标设计值与生产实际值对比表
	 */
	public void setResidueIsOk(String residueIsOk) {
		this.residueIsOk = residueIsOk;
	}
	/**
	 * 获取：工业项目废渣、废液、废气排放量及排放指标设计值与生产实际值对比表
	 */
	public String getResidueIsOk() {
		return residueIsOk;
	}
	/**
	 * 设置：需要时提供项目和新技术有关音像制品
	 */
	public void setAudioIsNeed(String audioIsNeed) {
		this.audioIsNeed = audioIsNeed;
	}
	/**
	 * 获取：需要时提供项目和新技术有关音像制品
	 */
	public String getAudioIsNeed() {
		return audioIsNeed;
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
	 * 设置：问题回馈情况
	 */
	public void setFeedbackInfo(String feedbackInfo) {
		this.feedbackInfo = feedbackInfo;
	}
	/**
	 * 获取：问题回馈情况
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
	 * 设置：
	 */
	public void setReviewResult(String reviewResult) {
		this.reviewResult = reviewResult;
	}
	/**
	 * 获取：
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
