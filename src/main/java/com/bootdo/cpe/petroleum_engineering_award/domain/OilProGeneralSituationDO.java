package com.bootdo.cpe.petroleum_engineering_award.domain;

import com.bootdo.common.utils.DateUtils;
import com.bootdo.common.utils.StringUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2021-02-20 22:55:27
 */
public class OilProGeneralSituationDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//记录id(id)
	private Integer id;
	//
	private String projectName;
	//建设地点
	private String buildLocation;
	//工程类别
	private String projectCategory;
	//工程规模
	private String projectScale;
	//开工时间
	private Date startTime;
	private String startTimeStr;
	//竣工时间
	private Date completionTime;
	private String completionTimeStr;
	//是否立项批复
	private Integer projectApproval;
	//是否立项批复字符串
	private String proApprovalStr;
	//是否有竣工验收报告
	private Integer completionAcceptanceReport;
	//是否有竣工验收报告
	private String completAcceptReportStr;
	//备案时间
	private Date recordTime;
	//备案时间
	private String recordTimeStr;
	//申报内容
	private String declarationContent;
	//工程投资（万元）
	private String projectInvestment;
	//工程决算（万元）
	private String projectFinalAccounts;
	//工程合同（万元）
	private String engineeringContract;
	//工程结算（万元）
	private String projectSettlement;
	//运行效果
	private String operationEffect;
	//设计是否先进合理
	private String designAdvancedReasonable;
	//设计是否先进合理
	private String designAdvanceIsStr;
	//有无证明
	private String hasAnyProof;
	//有无证明
	private String hasAnyProofStr;
	//是否通过安全评估
	private String passedSafetyAssessment;
	//是否通过安全评估
	private String passSafeIsStr;
	//是否通过环保评估
	private String hasEnvironmentalAssessment;
	//是否通过环保评估
	private String hasEnvAssessmentIsStr;
	//建设单位
	private String constructionUnit;
	//使用单位
	private String exploitingUnit;
	//设计单位
	private String designInstitute;
	//监理单位
	private String constructionControlUnit;
    //设计奖项
	private List<OilProDesignAwardDO> designAwardList;
	//工程奖项
	private List<OilProEngineeAwardDO> engineeAwardList;
	private OilProDesignAwardDO oilProDesignAwardDO = new OilProDesignAwardDO();
	private OilProEngineeAwardDO oilProEngineeAwardDO = new OilProEngineeAwardDO();
	//项目id
	private String proId;
	//任务id(task_id)
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
	 * 设置：记录id(id)
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：记录id(id)
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	/**
	 * 获取：
	 */
	public String getProjectName() {
		return projectName;
	}
	/**
	 * 设置：建设地点
	 */
	public void setBuildLocation(String buildLocation) {
		this.buildLocation = buildLocation;
	}
	/**
	 * 获取：建设地点
	 */
	public String getBuildLocation() {
		return buildLocation;
	}
	/**
	 * 设置：工程类别
	 */
	public void setProjectCategory(String projectCategory) {
		this.projectCategory = projectCategory;
	}
	/**
	 * 获取：工程类别
	 */
	public String getProjectCategory() {
		return projectCategory;
	}
	/**
	 * 设置：工程规模
	 */
	public void setProjectScale(String projectScale) {
		this.projectScale = projectScale;
	}
	/**
	 * 获取：工程规模
	 */
	public String getProjectScale() {
		return projectScale;
	}
	/**
	 * 设置：开工时间
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
		if(startTime != null) {
			this.startTimeStr = DateUtils.format(startTime, "yyyy-MM-dd");
		}
	}
	/**
	 * 获取：开工时间
	 */
	public Date getStartTime() {
		return startTime;
	}

	public String getStartTimeStr() {
		return startTimeStr;
	}

	public void setStartTimeStr(String startTimeStr) {
		this.startTimeStr = startTimeStr;
	}

	public String getCompletionTimeStr() {
		return completionTimeStr;
	}

	public void setCompletionTimeStr(String completionTimeStr) {
		this.completionTimeStr = completionTimeStr;
	}

	/**
	 * 设置：竣工时间
	 */
	public void setCompletionTime(Date completionTime) {
		this.completionTime = completionTime;
		if(completionTime != null) {
			this.completionTimeStr = DateUtils.format(completionTime, "yyyy-MM-dd");
		}
	}
	/**
	 * 获取：竣工时间
	 */
	public Date getCompletionTime() {
		return completionTime;
	}
	/**
	 * 设置：是否立项批复
	 */
	public void setProjectApproval(Integer projectApproval) {
		this.projectApproval = projectApproval;
		if(projectApproval != null && projectApproval == 1) {
			this.proApprovalStr = "是";
		}else {
			this.proApprovalStr = "否";
		}
	}
	/**
	 * 获取：是否立项批复
	 */
	public Integer getProjectApproval() {
		return projectApproval;
	}

	public String getProApprovalStr() {
		return proApprovalStr;
	}

	public void setProApprovalStr(String proApprovalStr) {
		this.proApprovalStr = proApprovalStr;
	}

	/**
	 * 设置：是否有竣工验收报告
	 */
	public void setCompletionAcceptanceReport(Integer completionAcceptanceReport) {
		this.completionAcceptanceReport = completionAcceptanceReport;
		if(completionAcceptanceReport != null && completionAcceptanceReport == 1) {
			this.completAcceptReportStr = "是";
		}else {
			this.completAcceptReportStr = "否";
		}
	}
	/**
	 * 获取：是否有竣工验收报告
	 */
	public Integer getCompletionAcceptanceReport() {
		return completionAcceptanceReport;
	}

	public String getCompletAcceptReportStr() {
		return completAcceptReportStr;
	}

	public void setCompletAcceptReportStr(String completAcceptReportStr) {
		this.completAcceptReportStr = completAcceptReportStr;
	}

	/**
	 * 设置：备案时间
	 */
	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
		if(recordTime != null) {
			this.recordTimeStr = DateUtils.format(recordTime, "yyyy-MM-dd");
		}
	}
	/**
	 * 获取：备案时间
	 */
	public Date getRecordTime() {
		return recordTime;
	}

	public String getRecordTimeStr() {
		return recordTimeStr;
	}

	public void setRecordTimeStr(String recordTimeStr) {
		this.recordTimeStr = recordTimeStr;
	}

	/**
	 * 设置：申报内容
	 */
	public void setDeclarationContent(String declarationContent) {
		this.declarationContent = declarationContent;
	}
	/**
	 * 获取：申报内容
	 */
	public String getDeclarationContent() {
		return declarationContent;
	}
	/**
	 * 设置：工程投资（万元）
	 */
	public void setProjectInvestment(String projectInvestment) {
		this.projectInvestment = projectInvestment;
	}
	/**
	 * 获取：工程投资（万元）
	 */
	public String getProjectInvestment() {
		return projectInvestment;
	}
	/**
	 * 设置：工程决算（万元）
	 */
	public void setProjectFinalAccounts(String projectFinalAccounts) {
		this.projectFinalAccounts = projectFinalAccounts;
	}
	/**
	 * 获取：工程决算（万元）
	 */
	public String getProjectFinalAccounts() {
		return projectFinalAccounts;
	}
	/**
	 * 设置：工程合同（万元）
	 */
	public void setEngineeringContract(String engineeringContract) {
		this.engineeringContract = engineeringContract;
	}
	/**
	 * 获取：工程合同（万元）
	 */
	public String getEngineeringContract() {
		return engineeringContract;
	}
	/**
	 * 设置：工程结算（万元）
	 */
	public void setProjectSettlement(String projectSettlement) {
		this.projectSettlement = projectSettlement;
	}
	/**
	 * 获取：工程结算（万元）
	 */
	public String getProjectSettlement() {
		return projectSettlement;
	}
	/**
	 * 设置：运行效果
	 */
	public void setOperationEffect(String operationEffect) {
		this.operationEffect = operationEffect;
	}
	/**
	 * 获取：运行效果
	 */
	public String getOperationEffect() {
		return operationEffect;
	}
	/**
	 * 设置：设计是否先进合理
	 */
	public void setDesignAdvancedReasonable(String designAdvancedReasonable) {
		this.designAdvancedReasonable = designAdvancedReasonable;
		if(designAdvancedReasonable != null && designAdvancedReasonable.equals("1")) {
			this.designAdvanceIsStr = "是";
		}else {
			this.designAdvanceIsStr = "否";
		}
	}
	/**
	 * 获取：设计是否先进合理
	 */
	public String getDesignAdvancedReasonable() {
		return designAdvancedReasonable;
	}

	public String getDesignAdvanceIsStr() {
		return designAdvanceIsStr;
	}

	public void setDesignAdvanceIsStr(String designAdvanceIsStr) {
		this.designAdvanceIsStr = designAdvanceIsStr;
	}

	/**
	 * 设置：有无证明
	 */
	public void setHasAnyProof(String hasAnyProof) {
		this.hasAnyProof = hasAnyProof;
		if(StringUtils.isNotBlank(hasAnyProof) && "1".equals(hasAnyProof)) {
			this.hasAnyProofStr = "是";
		}else {
			this.hasAnyProofStr = "否";
		}
	}
	/**
	 * 获取：有无证明
	 */
	public String getHasAnyProof() {
		return hasAnyProof;
	}

	public String getHasAnyProofStr() {
		return hasAnyProofStr;
	}

	public void setHasAnyProofStr(String hasAnyProofStr) {
		this.hasAnyProofStr = hasAnyProofStr;
	}

	/**
	 * 设置：是否通过安全评估
	 */
	public void setPassedSafetyAssessment(String passedSafetyAssessment) {
		this.passedSafetyAssessment = passedSafetyAssessment;
		if(StringUtils.isNotBlank(passedSafetyAssessment) && "1".equals(passedSafetyAssessment)) {
			this.passSafeIsStr = "是";
		}else {
			this.passSafeIsStr = "否";
		}
	}
	/**
	 * 获取：是否通过安全评估
	 */
	public String getPassedSafetyAssessment() {
		return passedSafetyAssessment;
	}

	public String getPassSafeIsStr() {
		return passSafeIsStr;
	}

	public void setPassSafeIsStr(String passSafeIsStr) {
		this.passSafeIsStr = passSafeIsStr;
	}

	/**
	 * 设置：是否通过环保评估
	 */
	public void setHasEnvironmentalAssessment(String hasEnvironmentalAssessment) {
		this.hasEnvironmentalAssessment = hasEnvironmentalAssessment;
		if(StringUtils.isNotBlank(hasEnvironmentalAssessment) && "1".equals(hasEnvironmentalAssessment)) {
			this.hasEnvAssessmentIsStr = "是";
		}else {
			this.hasEnvAssessmentIsStr = "否";
		}
	}
	/**
	 * 获取：是否通过环保评估
	 */
	public String getHasEnvironmentalAssessment() {
		return hasEnvironmentalAssessment;
	}

	public String getHasEnvAssessmentIsStr() {
		return hasEnvAssessmentIsStr;
	}

	public void setHasEnvAssessmentIsStr(String hasEnvAssessmentIsStr) {
		this.hasEnvAssessmentIsStr = hasEnvAssessmentIsStr;
	}

	/**
	 * 设置：建设单位
	 */
	public void setConstructionUnit(String constructionUnit) {
		this.constructionUnit = constructionUnit;
	}
	/**
	 * 获取：建设单位
	 */
	public String getConstructionUnit() {
		return constructionUnit;
	}
	/**
	 * 设置：使用单位
	 */
	public void setExploitingUnit(String exploitingUnit) {
		this.exploitingUnit = exploitingUnit;
	}
	/**
	 * 获取：使用单位
	 */
	public String getExploitingUnit() {
		return exploitingUnit;
	}
	/**
	 * 设置：设计单位
	 */
	public void setDesignInstitute(String designInstitute) {
		this.designInstitute = designInstitute;
	}
	/**
	 * 获取：设计单位
	 */
	public String getDesignInstitute() {
		return designInstitute;
	}
	/**
	 * 设置：监理单位
	 */
	public void setConstructionControlUnit(String constructionControlUnit) {
		this.constructionControlUnit = constructionControlUnit;
	}
	/**
	 * 获取：监理单位
	 */
	public String getConstructionControlUnit() {
		return constructionControlUnit;
	}

	public List<OilProDesignAwardDO> getDesignAwardList() {
		return designAwardList;
	}

	public void setDesignAwardList(List<OilProDesignAwardDO> designAwardList) {
		this.designAwardList = designAwardList;
		if(designAwardList != null && designAwardList.size() > 0) {
			this.oilProDesignAwardDO = designAwardList.get(0);
		}
	}

	public List<OilProEngineeAwardDO> getEngineeAwardList() {
		return engineeAwardList;
	}

	public void setEngineeAwardList(List<OilProEngineeAwardDO> engineeAwardList) {
		this.engineeAwardList = engineeAwardList;
		if(engineeAwardList != null && engineeAwardList.size() > 0) {
			this.oilProEngineeAwardDO = engineeAwardList.get(0);
		}
	}

	public OilProDesignAwardDO getOilProDesignAwardDO() {
		return oilProDesignAwardDO;
	}

	public void setOilProDesignAwardDO(OilProDesignAwardDO oilProDesignAwardDO) {
		this.oilProDesignAwardDO = oilProDesignAwardDO;
	}

	public OilProEngineeAwardDO getOilProEngineeAwardDO() {
		return oilProEngineeAwardDO;
	}

	public void setOilProEngineeAwardDO(OilProEngineeAwardDO oilProEngineeAwardDO) {
		this.oilProEngineeAwardDO = oilProEngineeAwardDO;
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
	 * 设置：任务id(task_id)
	 */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	/**
	 * 获取：任务id(task_id)
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
