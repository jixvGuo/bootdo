package com.bootdo.cpe.petroleum_engineering_award.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 石油安装工程形式审查结果记录
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2021-03-18 23:28:53
 */
public class OilProInstallReviewRecordDO implements Serializable {
	private static final long serialVersionUID = 1L;
	//申报单位
	private String applyUnit;
	//申报成果名
	private String applyAwardName;
	//
	private Integer id;
	//是否有工程简介 1有 0没有
	private Integer proDescIs;
	//《石油安装工程优质奖申报表》是否有，1有 0没有
	private Integer applyTabelIs;
	//证实性材料是否有：1有 0没有
	private Integer confirmFileIs;
	//先进安装技术水平介绍是否有1有0没有
	private Integer techniqueDescIs;
	//申报资料封皮(封面)是否有 1有，0没有
	private Integer applyCoverIs;
	//承诺书是否有1有0没有
	private Integer commitmentIs;
	//目录是否存在1存在0没有
	private Integer catalogIs;
	//主申报单位（非建设单位申报时）法人营业执照，资质证书
	private Integer busiLicenseIs;
	//工程立项核准或批复文件（批复页）
	private Integer approvalFileIs;
	//工程建设安评、环评批复文件扫描件
	private Integer buildFileIs;
	//工程承包合同（首页、承包范围页、合同额页、盖章页）
	private Integer contractIs;
	//获得局级工程质量奖证书、文件或达到局级质量奖水平证明扫描件
	private Integer certificateIs;
	//获得局级优秀设计奖证书、文件或设计先进合理性证明扫描
	private Integer proveFileIs;
	//建设单位（局级及以上）组织的竣工联合验收资料
	private Integer checkFileId;
	//建设单位出具的工程建设和生产运行期间未发生一般及以上安全、质量、环保事故证明
	private Integer envProveIs;
	//申报表内签署意见各栏应写明对工程的具体评价意见
	private Integer opinionIs;
	//申报资料中的文件、证明、印章等必须准确、真实、清晰
	private Integer stampIs;
	//突出贡献者名单
	private Integer contributorIs;
	//申报表、承诺书和建设单位出具的工程建设和生产运行期间未发生一般及以上安全、质量、环保事故证明需要提供纸质版原件1份。
	private Integer envPagerIs;
	//
	private String reviewResult;
	//
	private String opinionDesc;
	//
	private Date created;
	//
	private Date updated;
	//形式审查用户id
	private Long optUid;
	//项目id
	private Integer proId;
	//
	private String taskId;
	//
	private String applyId;

	public String getApplyUnit() {
		return applyUnit;
	}

	public void setApplyUnit(String applyUnit) {
		this.applyUnit = applyUnit;
	}

	public String getApplyAwardName() {
		return applyAwardName;
	}

	public void setApplyAwardName(String applyAwardName) {
		this.applyAwardName = applyAwardName;
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
	 * 设置：是否有工程简介 1有 0没有
	 */
	public void setProDescIs(Integer proDescIs) {
		this.proDescIs = proDescIs;
	}
	/**
	 * 获取：是否有工程简介 1有 0没有
	 */
	public Integer getProDescIs() {
		return proDescIs;
	}
	/**
	 * 设置：《石油安装工程优质奖申报表》是否有，1有 0没有
	 */
	public void setApplyTabelIs(Integer applyTabelIs) {
		this.applyTabelIs = applyTabelIs;
	}
	/**
	 * 获取：《石油安装工程优质奖申报表》是否有，1有 0没有
	 */
	public Integer getApplyTabelIs() {
		return applyTabelIs;
	}
	/**
	 * 设置：证实性材料是否有：1有 0没有
	 */
	public void setConfirmFileIs(Integer confirmFileIs) {
		this.confirmFileIs = confirmFileIs;
	}
	/**
	 * 获取：证实性材料是否有：1有 0没有
	 */
	public Integer getConfirmFileIs() {
		return confirmFileIs;
	}
	/**
	 * 设置：先进安装技术水平介绍是否有1有0没有
	 */
	public void setTechniqueDescIs(Integer techniqueDescIs) {
		this.techniqueDescIs = techniqueDescIs;
	}
	/**
	 * 获取：先进安装技术水平介绍是否有1有0没有
	 */
	public Integer getTechniqueDescIs() {
		return techniqueDescIs;
	}
	/**
	 * 设置：申报资料封皮(封面)是否有 1有，0没有
	 */
	public void setApplyCoverIs(Integer applyCoverIs) {
		this.applyCoverIs = applyCoverIs;
	}
	/**
	 * 获取：申报资料封皮(封面)是否有 1有，0没有
	 */
	public Integer getApplyCoverIs() {
		return applyCoverIs;
	}
	/**
	 * 设置：承诺书是否有1有0没有
	 */
	public void setCommitmentIs(Integer commitmentIs) {
		this.commitmentIs = commitmentIs;
	}
	/**
	 * 获取：承诺书是否有1有0没有
	 */
	public Integer getCommitmentIs() {
		return commitmentIs;
	}
	/**
	 * 设置：目录是否存在1存在0没有
	 */
	public void setCatalogIs(Integer catalogIs) {
		this.catalogIs = catalogIs;
	}
	/**
	 * 获取：目录是否存在1存在0没有
	 */
	public Integer getCatalogIs() {
		return catalogIs;
	}
	/**
	 * 设置：主申报单位（非建设单位申报时）法人营业执照，资质证书
	 */
	public void setBusiLicenseIs(Integer busiLicenseIs) {
		this.busiLicenseIs = busiLicenseIs;
	}
	/**
	 * 获取：主申报单位（非建设单位申报时）法人营业执照，资质证书
	 */
	public Integer getBusiLicenseIs() {
		return busiLicenseIs;
	}
	/**
	 * 设置：工程立项核准或批复文件（批复页）
	 */
	public void setApprovalFileIs(Integer approvalFileIs) {
		this.approvalFileIs = approvalFileIs;
	}
	/**
	 * 获取：工程立项核准或批复文件（批复页）
	 */
	public Integer getApprovalFileIs() {
		return approvalFileIs;
	}
	/**
	 * 设置：工程建设安评、环评批复文件扫描件
	 */
	public void setBuildFileIs(Integer buildFileIs) {
		this.buildFileIs = buildFileIs;
	}
	/**
	 * 获取：工程建设安评、环评批复文件扫描件
	 */
	public Integer getBuildFileIs() {
		return buildFileIs;
	}
	/**
	 * 设置：工程承包合同（首页、承包范围页、合同额页、盖章页）
	 */
	public void setContractIs(Integer contractIs) {
		this.contractIs = contractIs;
	}
	/**
	 * 获取：工程承包合同（首页、承包范围页、合同额页、盖章页）
	 */
	public Integer getContractIs() {
		return contractIs;
	}
	/**
	 * 设置：获得局级工程质量奖证书、文件或达到局级质量奖水平证明扫描件
	 */
	public void setCertificateIs(Integer certificateIs) {
		this.certificateIs = certificateIs;
	}
	/**
	 * 获取：获得局级工程质量奖证书、文件或达到局级质量奖水平证明扫描件
	 */
	public Integer getCertificateIs() {
		return certificateIs;
	}
	/**
	 * 设置：获得局级优秀设计奖证书、文件或设计先进合理性证明扫描
	 */
	public void setProveFileIs(Integer proveFileIs) {
		this.proveFileIs = proveFileIs;
	}
	/**
	 * 获取：获得局级优秀设计奖证书、文件或设计先进合理性证明扫描
	 */
	public Integer getProveFileIs() {
		return proveFileIs;
	}
	/**
	 * 设置：建设单位（局级及以上）组织的竣工联合验收资料
	 */
	public void setCheckFileId(Integer checkFileId) {
		this.checkFileId = checkFileId;
	}
	/**
	 * 获取：建设单位（局级及以上）组织的竣工联合验收资料
	 */
	public Integer getCheckFileId() {
		return checkFileId;
	}
	/**
	 * 设置：建设单位出具的工程建设和生产运行期间未发生一般及以上安全、质量、环保事故证明
	 */
	public void setEnvProveIs(Integer envProveIs) {
		this.envProveIs = envProveIs;
	}
	/**
	 * 获取：建设单位出具的工程建设和生产运行期间未发生一般及以上安全、质量、环保事故证明
	 */
	public Integer getEnvProveIs() {
		return envProveIs;
	}
	/**
	 * 设置：申报表内签署意见各栏应写明对工程的具体评价意见
	 */
	public void setOpinionIs(Integer opinionIs) {
		this.opinionIs = opinionIs;
	}
	/**
	 * 获取：申报表内签署意见各栏应写明对工程的具体评价意见
	 */
	public Integer getOpinionIs() {
		return opinionIs;
	}
	/**
	 * 设置：申报资料中的文件、证明、印章等必须准确、真实、清晰
	 */
	public void setStampIs(Integer stampIs) {
		this.stampIs = stampIs;
	}
	/**
	 * 获取：申报资料中的文件、证明、印章等必须准确、真实、清晰
	 */
	public Integer getStampIs() {
		return stampIs;
	}
	/**
	 * 设置：突出贡献者名单
	 */
	public void setContributorIs(Integer contributorIs) {
		this.contributorIs = contributorIs;
	}
	/**
	 * 获取：突出贡献者名单
	 */
	public Integer getContributorIs() {
		return contributorIs;
	}
	/**
	 * 设置：申报表、承诺书和建设单位出具的工程建设和生产运行期间未发生一般及以上安全、质量、环保事故证明需要提供纸质版原件1份。
	 */
	public void setEnvPagerIs(Integer envPagerIs) {
		this.envPagerIs = envPagerIs;
	}
	/**
	 * 获取：申报表、承诺书和建设单位出具的工程建设和生产运行期间未发生一般及以上安全、质量、环保事故证明需要提供纸质版原件1份。
	 */
	public Integer getEnvPagerIs() {
		return envPagerIs;
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
	public void setOpinionDesc(String opinionDesc) {
		this.opinionDesc = opinionDesc;
	}
	/**
	 * 获取：
	 */
	public String getOpinionDesc() {
		return opinionDesc;
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
	 * 设置：形式审查用户id
	 */
	public void setOptUid(Long optUid) {
		this.optUid = optUid;
	}
	/**
	 * 获取：形式审查用户id
	 */
	public Long getOptUid() {
		return optUid;
	}
	/**
	 * 设置：项目id
	 */
	public void setProId(Integer proId) {
		this.proId = proId;
	}
	/**
	 * 获取：项目id
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
}
