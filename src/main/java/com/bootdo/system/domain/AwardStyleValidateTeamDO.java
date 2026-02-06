package com.bootdo.system.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 形式审查_团队
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-10-01 00:13:43
 */
public class AwardStyleValidateTeamDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private int id;
	//申报单位
	private String applyCompany;
	//申报成果名称
	private String applyName;
	//三年以上稳定合作 0没有，1有
	private int isThreeYears;
	//技术专家（具有高级职称，必要条件）
	private String enclosureAppraisalBook;
	//科技奖励
	private int isScienceAward;
	//知识产权证书
	private int isKnowledgeBook;
	//论文、论著证明
	private int isPaper;
	//科技成果应用证明
	private int isApply;
	//营业执照
	private int isBusinessLicense;
	//质量、安全环保证明
	private int isQuality;
	//审核结果显示
	private String validateResult;
	//审核的用户id
	private Long validateUid;
	//项目id
	private int proId;
	//是否为最新的一次审核0不是 1是，主要针对驳回的有记录可查
	private int isLastValidate;
	//完善后参评 提出的修改意见内容
	private String rejectReason;
	//创建日期
	private Date created;
	//团队id
	private int teamId;
	//团队的流程节点状态，同项目的状态节点
	private String teamStat;
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
	 * 设置：三年以上稳定合作 0没有，1有
	 */
	public void setIsThreeYears(int isThreeYears) {
		this.isThreeYears = isThreeYears;
	}
	/**
	 * 获取：三年以上稳定合作 0没有，1有
	 */
	public int getIsThreeYears() {
		return isThreeYears;
	}
	/**
	 * 设置：技术专家（具有高级职称，必要条件）
	 */
	public void setEnclosureAppraisalBook(String enclosureAppraisalBook) {
		this.enclosureAppraisalBook = enclosureAppraisalBook;
	}
	/**
	 * 获取：技术专家（具有高级职称，必要条件）
	 */
	public String getEnclosureAppraisalBook() {
		return enclosureAppraisalBook;
	}
	/**
	 * 设置：科技奖励
	 */
	public void setIsScienceAward(int isScienceAward) {
		this.isScienceAward = isScienceAward;
	}
	/**
	 * 获取：科技奖励
	 */
	public int getIsScienceAward() {
		return isScienceAward;
	}
	/**
	 * 设置：知识产权证书
	 */
	public void setIsKnowledgeBook(int isKnowledgeBook) {
		this.isKnowledgeBook = isKnowledgeBook;
	}
	/**
	 * 获取：知识产权证书
	 */
	public int getIsKnowledgeBook() {
		return isKnowledgeBook;
	}
	/**
	 * 设置：论文、论著证明
	 */
	public void setIsPaper(int isPaper) {
		this.isPaper = isPaper;
	}
	/**
	 * 获取：论文、论著证明
	 */
	public int getIsPaper() {
		return isPaper;
	}
	/**
	 * 设置：科技成果应用证明
	 */
	public void setIsApply(int isApply) {
		this.isApply = isApply;
	}
	/**
	 * 获取：科技成果应用证明
	 */
	public int getIsApply() {
		return isApply;
	}
	/**
	 * 设置：营业执照
	 */
	public void setIsBusinessLicense(int isBusinessLicense) {
		this.isBusinessLicense = isBusinessLicense;
	}
	/**
	 * 获取：营业执照
	 */
	public int getIsBusinessLicense() {
		return isBusinessLicense;
	}
	/**
	 * 设置：质量、安全环保证明
	 */
	public void setIsQuality(int isQuality) {
		this.isQuality = isQuality;
	}
	/**
	 * 获取：质量、安全环保证明
	 */
	public int getIsQuality() {
		return isQuality;
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

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public String getTeamStat() {
		return teamStat;
	}

	public void setTeamStat(String teamStat) {
		this.teamStat = teamStat;
	}

	public String getProGroupName() {
		return proGroupName;
	}

	public void setProGroupName(String proGroupName) {
		this.proGroupName = proGroupName;
	}
}
