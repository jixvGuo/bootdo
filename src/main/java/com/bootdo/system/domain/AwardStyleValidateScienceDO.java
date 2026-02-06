package com.bootdo.system.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 形式审查_团队
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-10-01 00:04:42
 */
public class AwardStyleValidateScienceDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private int id;
	//申报单位
	private String applyCompany;
	//申报成果名称
	private String applyName;
	//是否有申报书 0没有，1有
	private int isApplyBook;
	//是否有研究报告
	private int isStudyReport;
	//附件-成果立项
	private int enclosureIsApproval;
	//附件-成果验收报告
	private int enclosureIsCheck;
	//附件-科技成果鉴定证书
	private String enclosureAppraisalBook;
	//附件-成果鉴定等级
	private String enclosureAppraisalLevel;
	//附件-知识产权证明数量
	private String enclosureKnowledgeCount;
	//附件-查新报告
	private int enclosureIsNew;
	//附件-其他证明
	private int enclosureIsOther;
	//附件-成果形成的国标、行标
	private int enclosureIsStandard;
	//附件-检测报告
	private String enclosureCheckBook;
	//附件-检测报告出具单位
	private String enclosureCheckCompany;
	//附件-成果应用证明
	private int enclosureIsApply;
	//附件-成果应用时间
	private String enclosureApplyTime;
	//附件-应用单位数量
	private String enclosureApplyCompanyCount;
	//附件-完成人名单排序表等
	private int enclosureIsTable;
	//审查存在问题
	private String validateQuestions;
	//主要完成人
	private String mainCompleteUsers;
	//审核结果显示
	private String validateResult;
	//审核的用户id
	private Long validateUid;
	//项目id
	private int proId;
	//是否为最新的一次审核0不是 1是，主要针对驳回的有记录可查
	private int isLastValidate;
	//完善后参评 提出的修改意见内容
	private String rejectReason = "无";
	//创建日期
	private Date created;
	//项目分组
	private String proGroupName;

	/**
	 * 设置：
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public int getId() {
		return id;
	}
	/**
	 * 设置：申报单位
	 */
	public void setApplyCompany(String applyCompany) {
		this.applyCompany = applyCompany;
	}
	/**
	 * 获取：申报单位
	 */
	public String getApplyCompany() {
		return applyCompany;
	}
	/**
	 * 设置：申报成果名称
	 */
	public void setApplyName(String applyName) {
		this.applyName = applyName;
	}
	/**
	 * 获取：申报成果名称
	 */
	public String getApplyName() {
		return applyName;
	}
	/**
	 * 设置：是否有申报书 0没有，1有
	 */
	public void setIsApplyBook(int isApplyBook) {
		this.isApplyBook = isApplyBook;
	}
	/**
	 * 获取：是否有申报书 0没有，1有
	 */
	public int getIsApplyBook() {
		return isApplyBook;
	}
	/**
	 * 设置：是否有研究报告
	 */
	public void setIsStudyReport(int isStudyReport) {
		this.isStudyReport = isStudyReport;
	}
	/**
	 * 获取：是否有研究报告
	 */
	public int getIsStudyReport() {
		return isStudyReport;
	}
	/**
	 * 设置：附件-成果立项
	 */
	public void setEnclosureIsApproval(int enclosureIsApproval) {
		this.enclosureIsApproval = enclosureIsApproval;
	}
	/**
	 * 获取：附件-成果立项
	 */
	public int getEnclosureIsApproval() {
		return enclosureIsApproval;
	}
	/**
	 * 设置：附件-成果验收报告
	 */
	public void setEnclosureIsCheck(int enclosureIsCheck) {
		this.enclosureIsCheck = enclosureIsCheck;
	}
	/**
	 * 获取：附件-成果验收报告
	 */
	public int getEnclosureIsCheck() {
		return enclosureIsCheck;
	}
	/**
	 * 设置：附件-科技成果鉴定证书
	 */
	public void setEnclosureAppraisalBook(String enclosureAppraisalBook) {
		this.enclosureAppraisalBook = enclosureAppraisalBook;
	}
	/**
	 * 获取：附件-科技成果鉴定证书
	 */
	public String getEnclosureAppraisalBook() {
		return enclosureAppraisalBook;
	}
	/**
	 * 设置：附件-成果鉴定等级
	 */
	public void setEnclosureAppraisalLevel(String enclosureAppraisalLevel) {
		this.enclosureAppraisalLevel = enclosureAppraisalLevel;
	}
	/**
	 * 获取：附件-成果鉴定等级
	 */
	public String getEnclosureAppraisalLevel() {
		return enclosureAppraisalLevel;
	}
	/**
	 * 设置：附件-知识产权证明数量
	 */
	public void setEnclosureKnowledgeCount(String enclosureKnowledgeCount) {
		this.enclosureKnowledgeCount = enclosureKnowledgeCount;
	}
	/**
	 * 获取：附件-知识产权证明数量
	 */
	public String getEnclosureKnowledgeCount() {
		return enclosureKnowledgeCount;
	}
	/**
	 * 设置：附件-查新报告
	 */
	public void setEnclosureIsNew(int enclosureIsNew) {
		this.enclosureIsNew = enclosureIsNew;
	}
	/**
	 * 获取：附件-查新报告
	 */
	public int getEnclosureIsNew() {
		return enclosureIsNew;
	}
	/**
	 * 设置：附件-其他证明
	 */
	public void setEnclosureIsOther(int enclosureIsOther) {
		this.enclosureIsOther = enclosureIsOther;
	}
	/**
	 * 获取：附件-其他证明
	 */
	public int getEnclosureIsOther() {
		return enclosureIsOther;
	}
	/**
	 * 设置：附件-成果形成的国标、行标
	 */
	public void setEnclosureIsStandard(int enclosureIsStandard) {
		this.enclosureIsStandard = enclosureIsStandard;
	}
	/**
	 * 获取：附件-成果形成的国标、行标
	 */
	public int getEnclosureIsStandard() {
		return enclosureIsStandard;
	}
	/**
	 * 设置：附件-检测报告
	 */
	public void setEnclosureCheckBook(String enclosureCheckBook) {
		this.enclosureCheckBook = enclosureCheckBook;
	}
	/**
	 * 获取：附件-检测报告
	 */
	public String getEnclosureCheckBook() {
		return enclosureCheckBook;
	}
	/**
	 * 设置：附件-检测报告出具单位
	 */
	public void setEnclosureCheckCompany(String enclosureCheckCompany) {
		this.enclosureCheckCompany = enclosureCheckCompany;
	}
	/**
	 * 获取：附件-检测报告出具单位
	 */
	public String getEnclosureCheckCompany() {
		return enclosureCheckCompany;
	}
	/**
	 * 设置：附件-成果应用证明
	 */
	public void setEnclosureIsApply(int enclosureIsApply) {
		this.enclosureIsApply = enclosureIsApply;
	}
	/**
	 * 获取：附件-成果应用证明
	 */
	public int getEnclosureIsApply() {
		return enclosureIsApply;
	}
	/**
	 * 设置：附件-成果应用时间
	 */
	public void setEnclosureApplyTime(String enclosureApplyTime) {
		this.enclosureApplyTime = enclosureApplyTime;
	}
	/**
	 * 获取：附件-成果应用时间
	 */
	public String getEnclosureApplyTime() {
		return enclosureApplyTime;
	}
	/**
	 * 设置：附件-应用单位数量
	 */
	public void setEnclosureApplyCompanyCount(String enclosureApplyCompanyCount) {
		this.enclosureApplyCompanyCount = enclosureApplyCompanyCount;
	}
	/**
	 * 获取：附件-应用单位数量
	 */
	public String getEnclosureApplyCompanyCount() {
		return enclosureApplyCompanyCount;
	}
	/**
	 * 设置：附件-完成人名单排序表等
	 */
	public void setEnclosureIsTable(int enclosureIsTable) {
		this.enclosureIsTable = enclosureIsTable;
	}
	/**
	 * 获取：附件-完成人名单排序表等
	 */
	public int getEnclosureIsTable() {
		return enclosureIsTable;
	}
	/**
	 * 设置：审查存在问题
	 */
	public void setValidateQuestions(String validateQuestions) {
		this.validateQuestions = validateQuestions;
	}
	/**
	 * 获取：审查存在问题
	 */
	public String getValidateQuestions() {
		return validateQuestions;
	}
	/**
	 * 设置：主要完成人
	 */
	public void setMainCompleteUsers(String mainCompleteUsers) {
		this.mainCompleteUsers = mainCompleteUsers;
	}
	/**
	 * 获取：主要完成人
	 */
	public String getMainCompleteUsers() {
		return mainCompleteUsers;
	}
	/**
	 * 设置：审核结果显示
	 */
	public void setValidateResult(String validateResult) {
		this.validateResult = validateResult;
	}
	/**
	 * 获取：审核结果显示
	 */
	public String getValidateResult() {
		return validateResult;
	}
	/**
	 * 设置：审核的用户id
	 */
	public void setValidateUid(Long validateUid) {
		this.validateUid = validateUid;
	}
	/**
	 * 获取：审核的用户id
	 */
	public Long getValidateUid() {
		return validateUid;
	}
	/**
	 * 设置：项目id
	 */
	public void setProId(int proId) {
		this.proId = proId;
	}
	/**
	 * 获取：项目id
	 */
	public int getProId() {
		return proId;
	}
	/**
	 * 设置：是否为最新的一次审核0不是 1是，主要针对驳回的有记录可查
	 */
	public void setIsLastValidate(int isLastValidate) {
		this.isLastValidate = isLastValidate;
	}
	/**
	 * 获取：是否为最新的一次审核0不是 1是，主要针对驳回的有记录可查
	 */
	public int getIsLastValidate() {
		return isLastValidate;
	}

	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	/**
	 * 设置：创建日期
	 */
	public void setCreated(Date created) {
		this.created = created;
	}
	/**
	 * 获取：创建日期
	 */
	public Date getCreated() {
		return created;
	}

	public String getProGroupName() {
		return proGroupName;
	}

	public void setProGroupName(String proGroupName) {
		this.proGroupName = proGroupName;
	}
}
