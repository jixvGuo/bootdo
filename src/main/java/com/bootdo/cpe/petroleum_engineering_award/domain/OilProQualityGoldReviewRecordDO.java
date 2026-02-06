package com.bootdo.cpe.petroleum_engineering_award.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 石油优质工程形式审查结果记录
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2021-03-20 09:01:55
 */
public class OilProQualityGoldReviewRecordDO implements Serializable {
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
	//影像资料是否有：1有 0没有
	private Integer videoFileIs;
	//工程创新成果总结是否有1有0没有
	private Integer innovateSumIs;
	//证实性材料封皮是否有 1有，0没有
	private Integer confirmCoverIs;
	//承诺书是否有1有0没有
	private Integer commitmentIs;
	//目录是否存在1存在0没有
	private Integer catalogIs;
	//主申报单位（非建设单位申报时）资质证书
	private Integer busiLicenseIs;
	//工程初步设计批复文件（批复页））
	private Integer approvalFileIs;
	//工程立项核准或批复文件（批复页）
	private Integer buildFileIs;
	//工程报建批复文件，主要包括：环评批复文件（批复页）、安评批复文件（批复页）、职业卫生批复文件（批复页），建设工程规划许可证，建设用地规划许可证，土地使用证，施工许可证或开工报告
	private Integer contractIs;
	//工程质量监督单位的工程质量评定文件
	private Integer certificateIs;
	//工程专项竣工验收文件，包括：规划、节能、环保、水土保持、消防、安全、职业卫生、档案验收文件等
	private Integer completedFileIs;
	//工程竣工验收及备案文件
	private Integer checkFileIs;
	//工程竣工决算书或审计报告
	private Integer auditReporIs;
	//无安全质量事故、无拖欠农民工工资证明文件
	private Integer noQualityIs;
	//局级优质工程奖证书
	private Integer bureauLevelEnginIs;
	//局级优秀设计奖证书
	private Integer bureauLevelDesignIs;
	//主申报单位（非建设单位申报时）与建设单位签订的承包合同
	private Integer signContractIs;
	//其他说明工程质量的材料（局级级以上QC活动成果、工法等）
	private Integer otherQualityIs;
	//境外证实性材料封皮是否有 1有，0没有
	private Integer outConfirmCoverIs;
	//境外承诺书是否有1有0没有
	private Integer outCommitmentIs;
	//境外目录是否存在1存在0没有
	private Integer outCatalogIs;
	//境外主申报单位（非建设单位申报时）资质证书
	private Integer outBusiLicenseIs;
	//境外对外承包工程经营资格证书
	private Integer outContractLicenseIs;
	//境外工程立项文件。其中，由国内投资（含对外援建工程）且执行国内相关标准的，应提供政府批复文件（批复页），完全由国外业主投资的项目，提供业主批复文件（批复页）
	private Integer outBuildFileIs;
	//境外工程施工承包商务合同和技术协议（主要页及签字盖章页）。其中，执行境外工程建设标准的项目需提供与国内标准比较的对标说明
	private Integer outBuildStandIs;
	//境外工程竣工验收资料，以及分部工程、单位工程验收报告
	private Integer outCheckFileIs;
	//境外工程使用单位的评价意见
	private Integer outUseOpinionIs;
	//境外中方驻外大使馆经济商务参赞处对工程质量和使用情况的书面意见
	private Integer outBookOpinionIs;
	//境外局级优质工程奖证书或局级主管部门出具工程质量评价证明。另外，获得工程所在国质量奖的，需提供参赞处对质量奖级别的鉴定说明
	private Integer outBureauLevelEnginIs;
	//境外局级优秀设计奖证书或局级主管部门出具设计质量评价证明
	private Integer outBureauLevelDesignIs;
	//境外工程项目无安全、质量事故证明。此证明由主申报单位上级行政主管部门出具。
	private Integer outNoQualityIs;
	//其他质量、安全、科技、节能、环保等相关资料
	private Integer outOtherQualityIs;
	//突出贡献者名单
	private Integer outContributorIs;
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
	 * 设置：影像资料是否有：1有 0没有
	 */
	public void setVideoFileIs(Integer videoFileIs) {
		this.videoFileIs = videoFileIs;
	}
	/**
	 * 获取：影像资料是否有：1有 0没有
	 */
	public Integer getVideoFileIs() {
		return videoFileIs;
	}
	/**
	 * 设置：工程创新成果总结是否有1有0没有
	 */
	public void setInnovateSumIs(Integer innovateSumIs) {
		this.innovateSumIs = innovateSumIs;
	}
	/**
	 * 获取：工程创新成果总结是否有1有0没有
	 */
	public Integer getInnovateSumIs() {
		return innovateSumIs;
	}
	/**
	 * 设置：证实性材料封皮是否有 1有，0没有
	 */
	public void setConfirmCoverIs(Integer confirmCoverIs) {
		this.confirmCoverIs = confirmCoverIs;
	}
	/**
	 * 获取：证实性材料封皮是否有 1有，0没有
	 */
	public Integer getConfirmCoverIs() {
		return confirmCoverIs;
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
	 * 设置：主申报单位（非建设单位申报时）资质证书
	 */
	public void setBusiLicenseIs(Integer busiLicenseIs) {
		this.busiLicenseIs = busiLicenseIs;
	}
	/**
	 * 获取：主申报单位（非建设单位申报时）资质证书
	 */
	public Integer getBusiLicenseIs() {
		return busiLicenseIs;
	}
	/**
	 * 设置：工程初步设计批复文件（批复页））
	 */
	public void setApprovalFileIs(Integer approvalFileIs) {
		this.approvalFileIs = approvalFileIs;
	}
	/**
	 * 获取：工程初步设计批复文件（批复页））
	 */
	public Integer getApprovalFileIs() {
		return approvalFileIs;
	}
	/**
	 * 设置：工程立项核准或批复文件（批复页）
	 */
	public void setBuildFileIs(Integer buildFileIs) {
		this.buildFileIs = buildFileIs;
	}
	/**
	 * 获取：工程立项核准或批复文件（批复页）
	 */
	public Integer getBuildFileIs() {
		return buildFileIs;
	}
	/**
	 * 设置：工程报建批复文件，主要包括：环评批复文件（批复页）、安评批复文件（批复页）、职业卫生批复文件（批复页），建设工程规划许可证，建设用地规划许可证，土地使用证，施工许可证或开工报告
	 */
	public void setContractIs(Integer contractIs) {
		this.contractIs = contractIs;
	}
	/**
	 * 获取：工程报建批复文件，主要包括：环评批复文件（批复页）、安评批复文件（批复页）、职业卫生批复文件（批复页），建设工程规划许可证，建设用地规划许可证，土地使用证，施工许可证或开工报告
	 */
	public Integer getContractIs() {
		return contractIs;
	}
	/**
	 * 设置：工程质量监督单位的工程质量评定文件
	 */
	public void setCertificateIs(Integer certificateIs) {
		this.certificateIs = certificateIs;
	}
	/**
	 * 获取：工程质量监督单位的工程质量评定文件
	 */
	public Integer getCertificateIs() {
		return certificateIs;
	}
	/**
	 * 设置：工程专项竣工验收文件，包括：规划、节能、环保、水土保持、消防、安全、职业卫生、档案验收文件等
	 */
	public void setCompletedFileIs(Integer completedFileIs) {
		this.completedFileIs = completedFileIs;
	}
	/**
	 * 获取：工程专项竣工验收文件，包括：规划、节能、环保、水土保持、消防、安全、职业卫生、档案验收文件等
	 */
	public Integer getCompletedFileIs() {
		return completedFileIs;
	}
	/**
	 * 设置：工程竣工验收及备案文件
	 */
	public void setCheckFileIs(Integer checkFileIs) {
		this.checkFileIs = checkFileIs;
	}
	/**
	 * 获取：工程竣工验收及备案文件
	 */
	public Integer getCheckFileIs() {
		return checkFileIs;
	}
	/**
	 * 设置：工程竣工决算书或审计报告
	 */
	public void setAuditReporIs(Integer auditReporIs) {
		this.auditReporIs = auditReporIs;
	}
	/**
	 * 获取：工程竣工决算书或审计报告
	 */
	public Integer getAuditReporIs() {
		return auditReporIs;
	}
	/**
	 * 设置：无安全质量事故、无拖欠农民工工资证明文件
	 */
	public void setNoQualityIs(Integer noQualityIs) {
		this.noQualityIs = noQualityIs;
	}
	/**
	 * 获取：无安全质量事故、无拖欠农民工工资证明文件
	 */
	public Integer getNoQualityIs() {
		return noQualityIs;
	}
	/**
	 * 设置：局级优质工程奖证书
	 */
	public void setBureauLevelEnginIs(Integer bureauLevelEnginIs) {
		this.bureauLevelEnginIs = bureauLevelEnginIs;
	}
	/**
	 * 获取：局级优质工程奖证书
	 */
	public Integer getBureauLevelEnginIs() {
		return bureauLevelEnginIs;
	}
	/**
	 * 设置：局级优秀设计奖证书
	 */
	public void setBureauLevelDesignIs(Integer bureauLevelDesignIs) {
		this.bureauLevelDesignIs = bureauLevelDesignIs;
	}
	/**
	 * 获取：局级优秀设计奖证书
	 */
	public Integer getBureauLevelDesignIs() {
		return bureauLevelDesignIs;
	}
	/**
	 * 设置：主申报单位（非建设单位申报时）与建设单位签订的承包合同
	 */
	public void setSignContractIs(Integer signContractIs) {
		this.signContractIs = signContractIs;
	}
	/**
	 * 获取：主申报单位（非建设单位申报时）与建设单位签订的承包合同
	 */
	public Integer getSignContractIs() {
		return signContractIs;
	}
	/**
	 * 设置：其他说明工程质量的材料（局级级以上QC活动成果、工法等）
	 */
	public void setOtherQualityIs(Integer otherQualityIs) {
		this.otherQualityIs = otherQualityIs;
	}
	/**
	 * 获取：其他说明工程质量的材料（局级级以上QC活动成果、工法等）
	 */
	public Integer getOtherQualityIs() {
		return otherQualityIs;
	}
	/**
	 * 设置：境外证实性材料封皮是否有 1有，0没有
	 */
	public void setOutConfirmCoverIs(Integer outConfirmCoverIs) {
		this.outConfirmCoverIs = outConfirmCoverIs;
	}
	/**
	 * 获取：境外证实性材料封皮是否有 1有，0没有
	 */
	public Integer getOutConfirmCoverIs() {
		return outConfirmCoverIs;
	}
	/**
	 * 设置：境外承诺书是否有1有0没有
	 */
	public void setOutCommitmentIs(Integer outCommitmentIs) {
		this.outCommitmentIs = outCommitmentIs;
	}
	/**
	 * 获取：境外承诺书是否有1有0没有
	 */
	public Integer getOutCommitmentIs() {
		return outCommitmentIs;
	}
	/**
	 * 设置：境外目录是否存在1存在0没有
	 */
	public void setOutCatalogIs(Integer outCatalogIs) {
		this.outCatalogIs = outCatalogIs;
	}
	/**
	 * 获取：境外目录是否存在1存在0没有
	 */
	public Integer getOutCatalogIs() {
		return outCatalogIs;
	}
	/**
	 * 设置：境外主申报单位（非建设单位申报时）资质证书
	 */
	public void setOutBusiLicenseIs(Integer outBusiLicenseIs) {
		this.outBusiLicenseIs = outBusiLicenseIs;
	}
	/**
	 * 获取：境外主申报单位（非建设单位申报时）资质证书
	 */
	public Integer getOutBusiLicenseIs() {
		return outBusiLicenseIs;
	}
	/**
	 * 设置：境外对外承包工程经营资格证书
	 */
	public void setOutContractLicenseIs(Integer outContractLicenseIs) {
		this.outContractLicenseIs = outContractLicenseIs;
	}
	/**
	 * 获取：境外对外承包工程经营资格证书
	 */
	public Integer getOutContractLicenseIs() {
		return outContractLicenseIs;
	}
	/**
	 * 设置：境外工程立项文件。其中，由国内投资（含对外援建工程）且执行国内相关标准的，应提供政府批复文件（批复页），完全由国外业主投资的项目，提供业主批复文件（批复页）
	 */
	public void setOutBuildFileIs(Integer outBuildFileIs) {
		this.outBuildFileIs = outBuildFileIs;
	}
	/**
	 * 获取：境外工程立项文件。其中，由国内投资（含对外援建工程）且执行国内相关标准的，应提供政府批复文件（批复页），完全由国外业主投资的项目，提供业主批复文件（批复页）
	 */
	public Integer getOutBuildFileIs() {
		return outBuildFileIs;
	}
	/**
	 * 设置：境外工程施工承包商务合同和技术协议（主要页及签字盖章页）。其中，执行境外工程建设标准的项目需提供与国内标准比较的对标说明
	 */
	public void setOutBuildStandIs(Integer outBuildStandIs) {
		this.outBuildStandIs = outBuildStandIs;
	}
	/**
	 * 获取：境外工程施工承包商务合同和技术协议（主要页及签字盖章页）。其中，执行境外工程建设标准的项目需提供与国内标准比较的对标说明
	 */
	public Integer getOutBuildStandIs() {
		return outBuildStandIs;
	}
	/**
	 * 设置：境外工程竣工验收资料，以及分部工程、单位工程验收报告
	 */
	public void setOutCheckFileIs(Integer outCheckFileIs) {
		this.outCheckFileIs = outCheckFileIs;
	}
	/**
	 * 获取：境外工程竣工验收资料，以及分部工程、单位工程验收报告
	 */
	public Integer getOutCheckFileIs() {
		return outCheckFileIs;
	}
	/**
	 * 设置：境外工程使用单位的评价意见
	 */
	public void setOutUseOpinionIs(Integer outUseOpinionIs) {
		this.outUseOpinionIs = outUseOpinionIs;
	}
	/**
	 * 获取：境外工程使用单位的评价意见
	 */
	public Integer getOutUseOpinionIs() {
		return outUseOpinionIs;
	}
	/**
	 * 设置：境外中方驻外大使馆经济商务参赞处对工程质量和使用情况的书面意见
	 */
	public void setOutBookOpinionIs(Integer outBookOpinionIs) {
		this.outBookOpinionIs = outBookOpinionIs;
	}
	/**
	 * 获取：境外中方驻外大使馆经济商务参赞处对工程质量和使用情况的书面意见
	 */
	public Integer getOutBookOpinionIs() {
		return outBookOpinionIs;
	}
	/**
	 * 设置：境外局级优质工程奖证书或局级主管部门出具工程质量评价证明。另外，获得工程所在国质量奖的，需提供参赞处对质量奖级别的鉴定说明
	 */
	public void setOutBureauLevelEnginIs(Integer outBureauLevelEnginIs) {
		this.outBureauLevelEnginIs = outBureauLevelEnginIs;
	}
	/**
	 * 获取：境外局级优质工程奖证书或局级主管部门出具工程质量评价证明。另外，获得工程所在国质量奖的，需提供参赞处对质量奖级别的鉴定说明
	 */
	public Integer getOutBureauLevelEnginIs() {
		return outBureauLevelEnginIs;
	}
	/**
	 * 设置：境外局级优秀设计奖证书或局级主管部门出具设计质量评价证明
	 */
	public void setOutBureauLevelDesignIs(Integer outBureauLevelDesignIs) {
		this.outBureauLevelDesignIs = outBureauLevelDesignIs;
	}
	/**
	 * 获取：境外局级优秀设计奖证书或局级主管部门出具设计质量评价证明
	 */
	public Integer getOutBureauLevelDesignIs() {
		return outBureauLevelDesignIs;
	}
	/**
	 * 设置：境外工程项目无安全、质量事故证明。此证明由主申报单位上级行政主管部门出具。
	 */
	public void setOutNoQualityIs(Integer outNoQualityIs) {
		this.outNoQualityIs = outNoQualityIs;
	}
	/**
	 * 获取：境外工程项目无安全、质量事故证明。此证明由主申报单位上级行政主管部门出具。
	 */
	public Integer getOutNoQualityIs() {
		return outNoQualityIs;
	}
	/**
	 * 设置：其他质量、安全、科技、节能、环保等相关资料
	 */
	public void setOutOtherQualityIs(Integer outOtherQualityIs) {
		this.outOtherQualityIs = outOtherQualityIs;
	}
	/**
	 * 获取：其他质量、安全、科技、节能、环保等相关资料
	 */
	public Integer getOutOtherQualityIs() {
		return outOtherQualityIs;
	}
	/**
	 * 设置：突出贡献者名单
	 */
	public void setOutContributorIs(Integer outContributorIs) {
		this.outContributorIs = outContributorIs;
	}
	/**
	 * 获取：突出贡献者名单
	 */
	public Integer getOutContributorIs() {
		return outContributorIs;
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
