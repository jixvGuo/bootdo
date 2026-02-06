package com.bootdo.cpe.petroleum_engineering_award.domain;

import com.bootdo.common.utils.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;



/**
 * 优质工程新增对象
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2021-02-20 22:55:26
 */
public class OilAwardApplyInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;

	private ArrayList<String> data = new ArrayList<>() ;


	//
	private Integer id;
	//单位全称
	private String fullNameUnit;
	//单位资质
	private String unitQualification;
	//姓名
	private String name;
	//手机
	private String mobilePhone;
	//座机
	private String telPhone;
	//通信地址
	private String postAddress;
	//邮编

	private String postCode;
	//电子邮箱
	private String eMailAdress;
	//
	private String name1;
	//
	private String mobilePhone1;
	//
	private String telPhone1;
	//电子邮箱
	private String postAddress1;
	//电子邮箱
	private String emailAdress1;
	//邮编
	private String postCodeOne;
	//我单位在该工程建设中是(承建单位性质)
	private String unitConstructionProject;
	private String unitConstructionProjectVal;
	//建设单位
	private String constructionUnit;
	//建设单位
	private String constructionUnitValue;

	public String getConstructionUnitValue() {
		String tt = "建设单位";
		 if (unitConstructionProject.equalsIgnoreCase("constructionUnit")){
			 tt = "建设单位";
		 }else if (unitConstructionProject.equalsIgnoreCase("engineeringGeneralContractor")){
			 tt = "工程总承包单位";
		 }else if (unitConstructionProject.equalsIgnoreCase("constructionGeneralContractor")){
			 tt = "施工总承包单位";
		 }else if (unitConstructionProject.equalsIgnoreCase("participatingUnit")){
			 tt = "参建单位";
		 }else if (unitConstructionProject.equalsIgnoreCase("other")){
			 tt = "其他";
		 }
		return  tt ;
	}

	public OilAwardApplyInfoDO() {
	    this.data.add("");
		this.data.add("");
		this.data.add("");
		this.data.add("");
		this.data.add("");

	}

	public void setConstructionUnitValue(String constructionUnitValue) {
		this.constructionUnitValue = constructionUnitValue;
	}

	//工程总承包单位
	private String engineeringGeneralContractor;
	//施工总承包单位
	private String constructionGeneralContractor;
	//参建单位
	private String participatingUnit;
	//其他
	private String other;
	//工程名称
	private String projectName;
	//工程类别
	private String engineeringCategory;
	private String engineeringCategoryStr;
	//资金来源
	private String sourcesFunds;
	private String sourcesFundsStr;
	//建设地点
	private String constructionSite;
	//建设性质
	private String constructionNature;
	private String constructionNatureStr;
	//竣工决算
	private String completionAccounts;
	//开工时间
	private String startTime;
	//竣工时间
	private String completionTime;
	//设计概算
	private String designBudget;
	//工程立项方式
	private String projectEstablishmentMethod;
	private String projectEstablishmentMethodStr;
	//工程立项批复单位
	private String projectApprovalUnit;
	//工程立项批复时间
	private String projectApprovalTime;
	//用地性质
	private String natureLandUse;
	//用地面积
	private String landArea;
	//工程性质
	private String engineeringProperties;
	//建设规模
	private String constructionScale;
	//地类（用途）
	private String landType;
	//使用权类型
	private String typesUseRight;
	private String typesUseRightStr;
	//
	private String approvedReback;
	//
	private String userUnite;
	//批准单位
	private String approvedUnite;
	//
	private String approvalTime;
	//环评批复单位
	private String eiaApprovalUnit;
	//批复时间
	private String eiaApprovalTime;
	//竣工验收单位
	private String completionAcceptanceUnit;
	//评定单位
	private String assessmentUnit;
	//评定结论
	private String evaluationConclusion;
	//备案机关
	private String filingAuthority;
	//备案时间
	private String recordTime;
	//审计单位
	private String auditUnit;
	//审计时间
	private String auditTime;
	//专项验收
	private String specialAcceptance;
	private String specialAcceptanceStr;
	// 竣工验收单位
	private String completionAcceptanceUnit1;
	//竣工验收时间
	private String completionAcceptanceTime;
	//农民工工资是否结算完成 1 完成 2 未完成
	private Integer workersWagesSettled;
	private String workersWagesSettledStr;
	//是否发生过安全事故 1 发生 0 未发生
	private Integer safetyAccident;
	private String safetyAccidentStr;
	// 是否发生过质量事故 1 发生 0 未发生
	private Integer qualityAccident = 0;
	private String qualityAccidentStr;
	//其他证明 专利、技术、工法、科技成果、“四新”技术应用、QC等）
	private String otherProof;
	//其他说明的事项
	private String otherMattersSpecified;
	//工程质量监督部门意见
	private String engineeringQualitySupervision;
	//工程安全监督部门意见
	private String engineeringSafetySupervisionDepartment;
	//工程使用单位意见
	private String opinionsProjectUsers;
	//申报单位上级主管部门意见
	private String competentDepartmentDeclarationUnit;
	//建设单位意见
	private String opinionsConstructionUnit;
	//推荐单位意见
	private String recommendationUnitComments;
	//工程规模或主要技术指标
	private String postCode1;
	//奖项类型 金奖gold 普通奖项 normal
	private String awardType;
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

	private int  addType;


	public int getAddType() {
		return addType;
	}

	public void setAddType(int addType) {
		this.addType = addType;
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
	/**
	 * 设置：单位全称
	 */
	public void setFullNameUnit(String fullNameUnit) {
		this.fullNameUnit = fullNameUnit;
	}
	/**
	 * 获取：单位全称
	 */
	public String getFullNameUnit() {
		return fullNameUnit;
	}
	/**
	 * 设置：单位资质
	 */
	public void setUnitQualification(String unitQualification) {
		this.unitQualification = unitQualification;
	}
	/**
	 * 获取：单位资质
	 */
	public String getUnitQualification() {
		return unitQualification;
	}
	/**
	 * 设置：姓名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：姓名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：手机
	 */
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	/**
	 * 获取：手机
	 */
	public String getMobilePhone() {
		return mobilePhone;
	}
	/**
	 * 设置：座机
	 */
	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}
	/**
	 * 获取：座机
	 */
	public String getTelPhone() {
		return telPhone;
	}
	/**
	 * 设置：通信地址
	 */
	public void setPostAddress(String postAddress) {
		this.postAddress = postAddress;
	}
	/**
	 * 获取：通信地址
	 */
	public String getPostAddress() {
		return postAddress;
	}
	/**
	 * 设置：邮编

	 */
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	/**
	 * 获取：邮编

	 */
	public String getPostCode() {
		return postCode;
	}
	/**
	 * 设置：电子邮箱
	 */
	public void setEMailAdress(String eMailAdress) {
		this.eMailAdress = eMailAdress;
	}
	/**
	 * 获取：电子邮箱
	 */
	public String getEMailAdress() {
		return eMailAdress;
	}
	/**
	 * 设置：
	 */
	public void setName1(String name1) {
		this.name1 = name1;
	}
	/**
	 * 获取：
	 */
	public String getName1() {
		return name1;
	}
	/**
	 * 设置：
	 */
	public void setMobilePhone1(String mobilePhone1) {
		this.mobilePhone1 = mobilePhone1;
	}
	/**
	 * 获取：
	 */
	public String getMobilePhone1() {
		return mobilePhone1;
	}
	/**
	 * 设置：
	 */
	public void setTelPhone1(String telPhone1) {
		this.telPhone1 = telPhone1;
	}
	/**
	 * 获取：
	 */
	public String getTelPhone1() {
		return telPhone1;
	}
	/**
	 * 设置：电子邮箱
	 */
	public void setPostAddress1(String postAddress1) {
		this.postAddress1 = postAddress1;
	}
	/**
	 * 获取：电子邮箱
	 */
	public String getPostAddress1() {
		return postAddress1;
	}
	/**
	 * 设置：电子邮箱
	 */
	public void setEmailAdress1(String emailAdress1) {
		this.emailAdress1 = emailAdress1;
	}
	/**
	 * 获取：电子邮箱
	 */
	public String getEmailAdress1() {
		return emailAdress1;
	}

	public String getPostCodeOne() {
		return postCodeOne;
	}

	public void setPostCodeOne(String postCodeOne) {
		this.postCodeOne = postCodeOne;
	}

	/**
	 * 设置：我单位在该工程建设中是(承建单位性质)
	 */
	public void setUnitConstructionProject(String unitConstructionProject) {
		this.unitConstructionProject = unitConstructionProject;
		this.unitConstructionProjectVal = unitConstructionProject;
	}
	/**
	 * 获取：我单位在该工程建设中是(承建单位性质)
	 */
	public String getUnitConstructionProject() {
		return unitConstructionProject;
	}

	public String getUnitConstructionProjectVal() {
		return unitConstructionProjectVal;
	}

	public void setUnitConstructionProjectVal(String unitConstructionProjectVal) {
		this.unitConstructionProjectVal = unitConstructionProjectVal;
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
	 * 设置：工程总承包单位
	 */
	public void setEngineeringGeneralContractor(String engineeringGeneralContractor) {
		this.engineeringGeneralContractor = engineeringGeneralContractor;
	}
	/**
	 * 获取：工程总承包单位
	 */
	public String getEngineeringGeneralContractor() {
		return engineeringGeneralContractor;
	}
	/**
	 * 设置：施工总承包单位
	 */
	public void setConstructionGeneralContractor(String constructionGeneralContractor) {
		this.constructionGeneralContractor = constructionGeneralContractor;
	}
	/**
	 * 获取：施工总承包单位
	 */
	public String getConstructionGeneralContractor() {
		return constructionGeneralContractor;
	}
	/**
	 * 设置：参建单位
	 */
	public void setParticipatingUnit(String participatingUnit) {
		this.participatingUnit = participatingUnit;
	}
	/**
	 * 获取：参建单位
	 */
	public String getParticipatingUnit() {
		return participatingUnit;
	}
	/**
	 * 设置：其他
	 */
	public void setOther(String other) {
		this.other = other;
	}
	/**
	 * 获取：其他
	 */
	public String getOther() {
		return other;
	}
	/**
	 * 设置：工程名称
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	/**
	 * 获取：工程名称
	 */
	public String getProjectName() {
		return projectName;
	}
	/**
	 * 设置：工程类别
	 */
	public void setEngineeringCategory(String engineeringCategory) {
		this.engineeringCategory = engineeringCategory;
		String str = "";
		if(StringUtils.isNotBlank(engineeringCategory)) {
			if(engineeringCategory.contains("1")) {
				str += "工业,";
			}
			if(engineeringCategory.contains("2")) {
				str += "交通,";
			}
			if(engineeringCategory.contains("3")) {
				str += "水利,";
			}
			if(engineeringCategory.contains("4")) {
				str += "市政,";
			}
			if(engineeringCategory.contains("5")) {
				str += "房屋建筑,";
			}
			this.engineeringCategoryStr = str.substring(0, str.length() - 1);
		}
	}
	/**
	 * 获取：工程类别
	 */
	public String getEngineeringCategory() {
		return engineeringCategory;
	}

	public String getEngineeringCategoryStr() {
		return engineeringCategoryStr;
	}

	public void setEngineeringCategoryStr(String engineeringCategoryStr) {
		this.engineeringCategoryStr = engineeringCategoryStr;
	}

	/**
	 * 设置：资金来源
	 */
	public void setSourcesFunds(String sourcesFunds) {
		this.sourcesFunds = sourcesFunds;
		if(StringUtils.isNotBlank(sourcesFunds)) {
			String str = "";
			if(sourcesFunds.contains("1")) {
				str += "自筹,";
			}
			if(sourcesFunds.contains("2")) {
				str += "财政拨款,";
			}
			if(sourcesFunds.contains("3")) {
				str += "其他,";
			}
			this.sourcesFundsStr = str.substring(0, str.length() - 1);
		}
	}
	/**
	 * 获取：资金来源
	 */
	public String getSourcesFunds() {
		return sourcesFunds;
	}

	public String getSourcesFundsStr() {
		return sourcesFundsStr;
	}

	public void setSourcesFundsStr(String sourcesFundsStr) {
		this.sourcesFundsStr = sourcesFundsStr;
	}

	/**
	 * 设置：建设地点
	 */
	public void setConstructionSite(String constructionSite) {
		this.constructionSite = constructionSite;
	}
	/**
	 * 获取：建设地点
	 */
	public String getConstructionSite() {
		return constructionSite;
	}
	/**
	 * 设置：建设性质
	 */
	public void setConstructionNature(String constructionNature) {
		this.constructionNature = constructionNature;
		if(StringUtils.isNotBlank(constructionNature)) {
			String str = "";
			if(constructionNature.contains("1")) {
				str += "新建,";
			}
			if(constructionNature.contains("2")) {
				str += "扩建,";
			}
			if(constructionNature.contains("3")) {
				str += "技改,";
			}
			this.constructionNatureStr = str.substring(0, str.length() - 1);
		}
	}
	/**
	 * 获取：建设性质
	 */
	public String getConstructionNature() {
		return constructionNature;
	}

	public String getConstructionNatureStr() {
		return constructionNatureStr;
	}

	public void setConstructionNatureStr(String constructionNatureStr) {
		this.constructionNatureStr = constructionNatureStr;
	}

	/**
	 * 设置：竣工决算
	 */
	public void setCompletionAccounts(String completionAccounts) {
		this.completionAccounts = completionAccounts;
	}
	/**
	 * 获取：竣工决算
	 */
	public String getCompletionAccounts() {
		return completionAccounts;
	}
	/**
	 * 设置：开工时间
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	/**
	 * 获取：开工时间
	 */
	public String getStartTime() {
		return startTime;
	}
	/**
	 * 设置：竣工时间
	 */
	public void setCompletionTime(String completionTime) {
		this.completionTime = completionTime;
	}
	/**
	 * 获取：竣工时间
	 */
	public String getCompletionTime() {
		return completionTime;
	}
	/**
	 * 设置：设计概算
	 */
	public void setDesignBudget(String designBudget) {
		this.designBudget = designBudget;
	}
	/**
	 * 获取：设计概算
	 */
	public String getDesignBudget() {
		return designBudget;
	}
	/**
	 * 设置：工程立项方式
	 */
	public void setProjectEstablishmentMethod(String projectEstablishmentMethod) {
		this.projectEstablishmentMethod = projectEstablishmentMethod;
		if(StringUtils.isNotBlank(projectEstablishmentMethod)) {
			String str = "";
			if(projectEstablishmentMethod.contains("1")) {
				str += "审批,";
			}
			if(projectEstablishmentMethod.contains("2")) {
				str += "核准,";
			}
			if(projectEstablishmentMethod.contains("3")) {
				str += "备案,";
			}
			this.projectEstablishmentMethodStr = str.substring(0, str.length() - 1);
		}
	}
	/**
	 * 获取：工程立项方式
	 */
	public String getProjectEstablishmentMethod() {
		return projectEstablishmentMethod;
	}

	public String getProjectEstablishmentMethodStr() {
		return projectEstablishmentMethodStr;
	}

	public void setProjectEstablishmentMethodStr(String projectEstablishmentMethodStr) {
		this.projectEstablishmentMethodStr = projectEstablishmentMethodStr;
	}

	/**
	 * 设置：工程立项批复单位
	 */
	public void setProjectApprovalUnit(String projectApprovalUnit) {
		this.projectApprovalUnit = projectApprovalUnit;
	}
	/**
	 * 获取：工程立项批复单位
	 */
	public String getProjectApprovalUnit() {
		return projectApprovalUnit;
	}
	/**
	 * 设置：工程立项批复时间
	 */
	public void setProjectApprovalTime(String projectApprovalTime) {
		this.projectApprovalTime = projectApprovalTime;
	}
	/**
	 * 获取：工程立项批复时间
	 */
	public String getProjectApprovalTime() {
		return projectApprovalTime;
	}
	/**
	 * 设置：用地性质
	 */
	public void setNatureLandUse(String natureLandUse) {
		this.natureLandUse = natureLandUse;
	}
	/**
	 * 获取：用地性质
	 */
	public String getNatureLandUse() {
		return natureLandUse;
	}
	/**
	 * 设置：用地面积
	 */
	public void setLandArea(String landArea) {
		this.landArea = landArea;
	}
	/**
	 * 获取：用地面积
	 */
	public String getLandArea() {
		return landArea;
	}
	/**
	 * 设置：工程性质
	 */
	public void setEngineeringProperties(String engineeringProperties) {
		this.engineeringProperties = engineeringProperties;
	}
	/**
	 * 获取：工程性质
	 */
	public String getEngineeringProperties() {
		return engineeringProperties;
	}
	/**
	 * 设置：建设规模
	 */
	public void setConstructionScale(String constructionScale) {
		this.constructionScale = constructionScale;
	}
	/**
	 * 获取：建设规模
	 */
	public String getConstructionScale() {
		return constructionScale;
	}
	/**
	 * 设置：地类（用途）
	 */
	public void setLandType(String landType) {
		this.landType = landType;
	}
	/**
	 * 获取：地类（用途）
	 */
	public String getLandType() {
		return landType;
	}
	/**
	 * 设置：使用权类型
	 */
	public void setTypesUseRight(String typesUseRight) {
		this.typesUseRight = typesUseRight;
		if(StringUtils.isNotBlank(typesUseRight)) {
			String str = "";
			if(typesUseRight.contains("1")) {
				str += "划拨,";
			}
			if(typesUseRight.contains("2")) {
				str += "出让,";
			}
			this.typesUseRightStr = str.substring(0, str.length() - 1);
		}
	}
	/**
	 * 获取：使用权类型
	 */
	public String getTypesUseRight() {
		return typesUseRight;
	}

	public String getTypesUseRightStr() {
		return typesUseRightStr;
	}

	public void setTypesUseRightStr(String typesUseRightStr) {
		this.typesUseRightStr = typesUseRightStr;
	}

	/**
	 * 设置：
	 */
	public void setApprovedReback(String approvedReback) {
		this.approvedReback = approvedReback;
	}
	/**
	 * 获取：
	 */
	public String getApprovedReback() {
		return approvedReback;
	}
	/**
	 * 设置：
	 */
	public void setUserUnite(String userUnite) {
		this.userUnite = userUnite;
	}
	/**
	 * 获取：
	 */
	public String getUserUnite() {
		return userUnite;
	}
	/**
	 * 设置：批准单位
	 */
	public void setApprovedUnite(String approvedUnite) {
		this.approvedUnite = approvedUnite;
	}
	/**
	 * 获取：批准单位
	 */
	public String getApprovedUnite() {
		return approvedUnite;
	}
	/**
	 * 设置：
	 */
	public void setApprovalTime(String approvalTime) {
		this.approvalTime = approvalTime;
	}
	/**
	 * 获取：
	 */
	public String getApprovalTime() {
		return approvalTime;
	}
	/**
	 * 设置：环评批复单位
	 */
	public void setEiaApprovalUnit(String eiaApprovalUnit) {
		this.eiaApprovalUnit = eiaApprovalUnit;
	}
	/**
	 * 获取：环评批复单位
	 */
	public String getEiaApprovalUnit() {
		return eiaApprovalUnit;
	}
	/**
	 * 设置：批复时间
	 */
	public void setEiaApprovalTime(String eiaApprovalTime) {
		this.eiaApprovalTime = eiaApprovalTime;
	}
	/**
	 * 获取：批复时间
	 */
	public String getEiaApprovalTime() {
		return eiaApprovalTime;
	}
	/**
	 * 设置：竣工验收单位
	 */
	public void setCompletionAcceptanceUnit(String completionAcceptanceUnit) {
		this.completionAcceptanceUnit = completionAcceptanceUnit;
	}
	/**
	 * 获取：竣工验收单位
	 */
	public String getCompletionAcceptanceUnit() {
		return completionAcceptanceUnit;
	}
	/**
	 * 设置：评定单位
	 */
	public void setAssessmentUnit(String assessmentUnit) {
		this.assessmentUnit = assessmentUnit;
	}
	/**
	 * 获取：评定单位
	 */
	public String getAssessmentUnit() {
		return assessmentUnit;
	}
	/**
	 * 设置：评定结论
	 */
	public void setEvaluationConclusion(String evaluationConclusion) {
		this.evaluationConclusion = evaluationConclusion;
	}
	/**
	 * 获取：评定结论
	 */
	public String getEvaluationConclusion() {
		return evaluationConclusion;
	}
	/**
	 * 设置：备案机关
	 */
	public void setFilingAuthority(String filingAuthority) {
		this.filingAuthority = filingAuthority;
	}
	/**
	 * 获取：备案机关
	 */
	public String getFilingAuthority() {
		return filingAuthority;
	}
	/**
	 * 设置：备案时间
	 */
	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}
	/**
	 * 获取：备案时间
	 */
	public String getRecordTime() {
		return recordTime;
	}
	/**
	 * 设置：审计单位
	 */
	public void setAuditUnit(String auditUnit) {
		this.auditUnit = auditUnit;
	}
	/**
	 * 获取：审计单位
	 */
	public String getAuditUnit() {
		return auditUnit;
	}
	/**
	 * 设置：审计时间
	 */
	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}
	/**
	 * 获取：审计时间
	 */
	public String getAuditTime() {
		return auditTime;
	}
	/**
	 * 设置：专项验收
	 */
	public void setSpecialAcceptance(String specialAcceptance) {
		this.specialAcceptance = specialAcceptance;
		if(StringUtils.isNotBlank(specialAcceptance)) {
			String str = "";
			if(specialAcceptance.contains("1")) {
				str += "规划,";
			}
			if(specialAcceptance.contains("2")) {
				str += "节能,";
			}
			if(specialAcceptance.contains("3")) {
				str += "环保,";
			}
			if(specialAcceptance.contains("4")) {
				str += "水土保持,";
			}
			if(specialAcceptance.contains("5")) {
				str += "消防,";
			}
			if(specialAcceptance.contains("6")) {
				str += "安全,";
			}
			if(specialAcceptance.contains("7")) {
				str += "职业卫生,";
			}
			if(specialAcceptance.contains("8")) {
				str += "档案,";
			}
			this.specialAcceptanceStr = str.substring(0, str.length() - 1);
		}
	}
	/**
	 * 获取：专项验收
	 */
	public String getSpecialAcceptance() {
		return specialAcceptance;
	}

	public String getSpecialAcceptanceStr() {
		return specialAcceptanceStr;
	}

	public void setSpecialAcceptanceStr(String specialAcceptanceStr) {
		this.specialAcceptanceStr = specialAcceptanceStr;
	}

	/**
	 * 设置： 竣工验收单位
	 */
	public void setCompletionAcceptanceUnit1(String completionAcceptanceUnit1) {
		this.completionAcceptanceUnit1 = completionAcceptanceUnit1;
	}
	/**
	 * 获取： 竣工验收单位
	 */
	public String getCompletionAcceptanceUnit1() {
		return completionAcceptanceUnit1;
	}
	/**
	 * 设置：竣工验收时间
	 */
	public void setCompletionAcceptanceTime(String completionAcceptanceTime) {
		this.completionAcceptanceTime = completionAcceptanceTime;
	}
	/**
	 * 获取：竣工验收时间
	 */
	public String getCompletionAcceptanceTime() {
		return completionAcceptanceTime;
	}
	/**
	 * 设置：农民工工资是否结算完成 1 完成 2 未完成
	 */
	public void setWorkersWagesSettled(Integer workersWagesSettled) {
		if(workersWagesSettled == 1) {
			this.workersWagesSettledStr = "完成";
		}else if(workersWagesSettled == 2) {
			this.workersWagesSettledStr = "未完成";
		}
		this.workersWagesSettled = workersWagesSettled;
	}
	/**
	 * 获取：农民工工资是否结算完成 1 完成 2 未完成
	 */
	public Integer getWorkersWagesSettled() {
		return workersWagesSettled;
	}

	public String getWorkersWagesSettledStr() {
		return workersWagesSettledStr;
	}

	public void setWorkersWagesSettledStr(String workersWagesSettledStr) {
		this.workersWagesSettledStr = workersWagesSettledStr;
	}

	/**
	 * 设置：是否发生过安全事故 1 发生 0 未发生
	 */
	public void setSafetyAccident(Integer safetyAccident) {
		this.safetyAccident = safetyAccident;
		if(safetyAccident == 1) {
			this.safetyAccidentStr = "发生";
		}else if(safetyAccident == 2) {
			this.safetyAccidentStr = "未发生";
		}
	}
	/**
	 * 获取：是否发生过安全事故 1 发生 0 未发生
	 */
	public Integer getSafetyAccident() {
		return safetyAccident;
	}

	public String getSafetyAccidentStr() {
		return safetyAccidentStr;
	}

	public void setSafetyAccidentStr(String safetyAccidentStr) {
		this.safetyAccidentStr = safetyAccidentStr;
	}

	/**
	 * 设置： 是否发生过质量事故 1 发生 0 未发生
	 */
	public void setQualityAccident(Integer qualityAccident) {
		this.qualityAccident = qualityAccident;
		if(qualityAccident == 1) {
			this.qualityAccidentStr = "发生";
		}else if(qualityAccident == 0) {
			this.qualityAccidentStr = "未发生";
		}
	}
	/**
	 * 获取： 是否发生过质量事故 1 发生 0 未发生
	 */
	public Integer getQualityAccident() {
		return qualityAccident;
	}

	public String getQualityAccidentStr() {
		return qualityAccidentStr;
	}

	public void setQualityAccidentStr(String qualityAccidentStr) {
		this.qualityAccidentStr = qualityAccidentStr;
	}

	/**
	 * 设置：其他证明 专利、技术、工法、科技成果、“四新”技术应用、QC等）
	 */
	public void setOtherProof(String otherProof) {
		this.otherProof = otherProof;
	}
	/**
	 * 获取：其他证明 专利、技术、工法、科技成果、“四新”技术应用、QC等）
	 */
	public String getOtherProof() {
		return otherProof;
	}
	/**
	 * 设置：其他说明的事项
	 */
	public void setOtherMattersSpecified(String otherMattersSpecified) {
		this.otherMattersSpecified = otherMattersSpecified;
	}
	/**
	 * 获取：其他说明的事项
	 */
	public String getOtherMattersSpecified() {
		return otherMattersSpecified;
	}
	/**
	 * 设置：工程质量监督部门意见
	 */
	public void setEngineeringQualitySupervision(String engineeringQualitySupervision) {
		this.engineeringQualitySupervision = engineeringQualitySupervision;
	}
	/**
	 * 获取：工程质量监督部门意见
	 */
	public String getEngineeringQualitySupervision() {
		return engineeringQualitySupervision;
	}
	/**
	 * 设置：工程安全监督部门意见
	 */
	public void setEngineeringSafetySupervisionDepartment(String engineeringSafetySupervisionDepartment) {
		this.engineeringSafetySupervisionDepartment = engineeringSafetySupervisionDepartment;
	}
	/**
	 * 获取：工程安全监督部门意见
	 */
	public String getEngineeringSafetySupervisionDepartment() {
		return engineeringSafetySupervisionDepartment;
	}
	/**
	 * 设置：工程使用单位意见
	 */
	public void setOpinionsProjectUsers(String opinionsProjectUsers) {
		this.opinionsProjectUsers = opinionsProjectUsers;
	}
	/**
	 * 获取：工程使用单位意见
	 */
	public String getOpinionsProjectUsers() {
		return opinionsProjectUsers;
	}
	/**
	 * 设置：申报单位上级主管部门意见
	 */
	public void setCompetentDepartmentDeclarationUnit(String competentDepartmentDeclarationUnit) {
		this.competentDepartmentDeclarationUnit = competentDepartmentDeclarationUnit;
	}
	/**
	 * 获取：申报单位上级主管部门意见
	 */
	public String getCompetentDepartmentDeclarationUnit() {
		return competentDepartmentDeclarationUnit;
	}
	/**
	 * 设置：建设单位意见
	 */
	public void setOpinionsConstructionUnit(String opinionsConstructionUnit) {
		this.opinionsConstructionUnit = opinionsConstructionUnit;
	}

	/**
	 * 获取：建设单位意见
	 */
	public String getOpinionsConstructionUnit() {
		return opinionsConstructionUnit;
	}

	/**
	 * 设置：推荐单位意见
	 */
	public void setRecommendationUnitComments(String recommendationUnitComments) {
		this.recommendationUnitComments = recommendationUnitComments;
	}
	/**
	 * 获取：推荐单位意见
	 */
	public String getRecommendationUnitComments() {
		return recommendationUnitComments;
	}
	/**
	 * 设置：工程规模或主要技术指标
	 */
	public void setPostCode1(String postCode1) {
		this.postCode1 = postCode1;
	}
	/**
	 * 获取：工程规模或主要技术指标
	 */
	public String getPostCode1() {
		return postCode1;
	}

	public String geteMailAdress() {
		return eMailAdress;
	}

	public void seteMailAdress(String eMailAdress) {
		this.eMailAdress = eMailAdress;
	}

	public String getAwardType() {
		return awardType;
	}

	public void setAwardType(String awardType) {
		this.awardType = awardType;
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
}
