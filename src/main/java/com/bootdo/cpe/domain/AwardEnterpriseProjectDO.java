package com.bootdo.cpe.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 企业创建的项目
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-03-28 23:16:05
 */
public class AwardEnterpriseProjectDO implements Serializable {
	private static final long serialVersionUID = 1L;

	//编号
	private Integer id;
	//
	private String chengguo;
	//
	private Long majorId;
	//
	private String tuandui;
	//
	private String person;
	//
	private String proDesc;
	//
	private String publishTaskId;
	//先进个人姓名
	private String advancedIndividual;
	//创建项目的用户id
	private Long createUid;
	//
	private Date created;
	//企业用户自己填写的专业信息
	private String major;
	//流程实例ID
	private String procInsId;
	//修改时间
	private Date updateDate;
	//协会工作人员审核结果,yes通过，no不通过，over_validate不参评
	private String associationReviewRst;
	//形式审查驳回原因
	private String associationRejectContent;
	//运行中的的工作流的任务id
	private String actRunTaskId;
	//
	private String rstScienceValiFile;
	//
	private String rstPersonalValiFile;
	//
	private String rstTuanduiValiFile;
	//
	private String scienceLevel;
	//
	private String scienceKnowledgeCount;
	//
	private String scienceVliEnterprise;
	//
	private String scienceApplyTime;
	//
	private String scienceApplyEnterpriseCount;
	//
	private String tuanduiCooperate;
	//
	private String tuanduiSpecialist;
	//
	private String personalIsEmploy;
	//
	private String personalIsWorkEthics;
	//科技进步奖平均分
	private BigDecimal scienceAvgScore;
	//团队平均分
	private BigDecimal teamAvgScore;
	//个人平均分
	private BigDecimal personalAvgScore;
	//专家组长审核意见
	private String specialistLeaderComment;
	//项目的状态信息:"to_validate","待审核中，申请尚未结束"
/*"validate","审核中,申请结束，进入审核中"
"score","参评，企业项目可以进入打分节点"
"reject","完善后参评，企业形式审查被驳回，需企业进行二次提交审核"
"no_score","不评，不对该申请项目进行评分"
"defer_score","缓评,此次不对此申请项目进行评分"
"result","专家打分结束，算出平均分，及生成相关模板数据"*/
	private String proStat;
	//项目的类型:science_progress 科技进步,science_team科技团队,science_personal科技个人,oil_install 石油安装工程优质奖,oil_quality_gold 石油优质工程金奖,oil_quality_gold 石油优质工程奖,qc_group QC奖小组
	private String proType;
	//专业组ID（专业组管理时更新数据）
	private String teamGroupId;
	//项目编码，科技奖、团队、个人中每一个项目要给一个编码，作为唯一对应编码，编码按照申报时间排序，第一个申报的是01，第二个是02，最后一个是71
	private Integer proCode;
	//项目成果编号，由协会联系人进行设置
	private String proResultCode;
	//项目子类型
	private String proSubType;

	/**
	 * 设置：编号
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：编号
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setChengguo(String chengguo) {
		this.chengguo = chengguo;
	}
	/**
	 * 获取：
	 */
	public String getChengguo() {
		return chengguo;
	}
	/**
	 * 设置：
	 */
	public void setMajorId(Long majorId) {
		this.majorId = majorId;
	}
	/**
	 * 获取：
	 */
	public Long getMajorId() {
		return majorId;
	}
	/**
	 * 设置：
	 */
	public void setTuandui(String tuandui) {
		this.tuandui = tuandui;
	}
	/**
	 * 获取：
	 */
	public String getTuandui() {
		return tuandui;
	}
	/**
	 * 设置：
	 */
	public void setPerson(String person) {
		this.person = person;
	}
	/**
	 * 获取：
	 */
	public String getPerson() {
		return person;
	}
	/**
	 * 设置：
	 */
	public void setProDesc(String proDesc) {
		this.proDesc = proDesc;
	}
	/**
	 * 获取：
	 */
	public String getProDesc() {
		return proDesc;
	}
	/**
	 * 设置：
	 */
	public void setPublishTaskId(String publishTaskId) {
		this.publishTaskId = publishTaskId;
	}
	/**
	 * 获取：
	 */
	public String getPublishTaskId() {
		return publishTaskId;
	}
	/**
	 * 设置：先进个人姓名
	 */
	public void setAdvancedIndividual(String advancedIndividual) {
		this.advancedIndividual = advancedIndividual;
	}
	/**
	 * 获取：先进个人姓名
	 */
	public String getAdvancedIndividual() {
		return advancedIndividual;
	}
	/**
	 * 设置：创建项目的用户id
	 */
	public void setCreateUid(Long createUid) {
		this.createUid = createUid;
	}
	/**
	 * 获取：创建项目的用户id
	 */
	public Long getCreateUid() {
		return createUid;
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
	 * 设置：企业用户自己填写的专业信息
	 */
	public void setMajor(String major) {
		this.major = major;
	}
	/**
	 * 获取：企业用户自己填写的专业信息
	 */
	public String getMajor() {
		return major;
	}
	/**
	 * 设置：流程实例ID
	 */
	public void setProcInsId(String procInsId) {
		this.procInsId = procInsId;
	}
	/**
	 * 获取：流程实例ID
	 */
	public String getProcInsId() {
		return procInsId;
	}
	/**
	 * 设置：修改时间
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	/**
	 * 获取：修改时间
	 */
	public Date getUpdateDate() {
		return updateDate;
	}
	/**
	 * 设置：协会工作人员审核结果,yes通过，no不通过，over_validate不参评
	 */
	public void setAssociationReviewRst(String associationReviewRst) {
		this.associationReviewRst = associationReviewRst;
	}
	/**
	 * 获取：协会工作人员审核结果,yes通过，no不通过，over_validate不参评
	 */
	public String getAssociationReviewRst() {
		return associationReviewRst;
	}
	/**
	 * 设置：形式审查驳回原因
	 */
	public void setAssociationRejectContent(String associationRejectContent) {
		this.associationRejectContent = associationRejectContent;
	}
	/**
	 * 获取：形式审查驳回原因
	 */
	public String getAssociationRejectContent() {
		return associationRejectContent;
	}
	/**
	 * 设置：运行中的的工作流的任务id
	 */
	public void setActRunTaskId(String actRunTaskId) {
		this.actRunTaskId = actRunTaskId;
	}
	/**
	 * 获取：运行中的的工作流的任务id
	 */
	public String getActRunTaskId() {
		return actRunTaskId;
	}
	/**
	 * 设置：
	 */
	public void setRstScienceValiFile(String rstScienceValiFile) {
		this.rstScienceValiFile = rstScienceValiFile;
	}
	/**
	 * 获取：
	 */
	public String getRstScienceValiFile() {
		return rstScienceValiFile;
	}
	/**
	 * 设置：
	 */
	public void setRstPersonalValiFile(String rstPersonalValiFile) {
		this.rstPersonalValiFile = rstPersonalValiFile;
	}
	/**
	 * 获取：
	 */
	public String getRstPersonalValiFile() {
		return rstPersonalValiFile;
	}
	/**
	 * 设置：
	 */
	public void setRstTuanduiValiFile(String rstTuanduiValiFile) {
		this.rstTuanduiValiFile = rstTuanduiValiFile;
	}
	/**
	 * 获取：
	 */
	public String getRstTuanduiValiFile() {
		return rstTuanduiValiFile;
	}
	/**
	 * 设置：
	 */
	public void setScienceLevel(String scienceLevel) {
		this.scienceLevel = scienceLevel;
	}
	/**
	 * 获取：
	 */
	public String getScienceLevel() {
		return scienceLevel;
	}
	/**
	 * 设置：
	 */
	public void setScienceKnowledgeCount(String scienceKnowledgeCount) {
		this.scienceKnowledgeCount = scienceKnowledgeCount;
	}
	/**
	 * 获取：
	 */
	public String getScienceKnowledgeCount() {
		return scienceKnowledgeCount;
	}
	/**
	 * 设置：
	 */
	public void setScienceVliEnterprise(String scienceVliEnterprise) {
		this.scienceVliEnterprise = scienceVliEnterprise;
	}
	/**
	 * 获取：
	 */
	public String getScienceVliEnterprise() {
		return scienceVliEnterprise;
	}
	/**
	 * 设置：
	 */
	public void setScienceApplyTime(String scienceApplyTime) {
		this.scienceApplyTime = scienceApplyTime;
	}
	/**
	 * 获取：
	 */
	public String getScienceApplyTime() {
		return scienceApplyTime;
	}
	/**
	 * 设置：
	 */
	public void setScienceApplyEnterpriseCount(String scienceApplyEnterpriseCount) {
		this.scienceApplyEnterpriseCount = scienceApplyEnterpriseCount;
	}
	/**
	 * 获取：
	 */
	public String getScienceApplyEnterpriseCount() {
		return scienceApplyEnterpriseCount;
	}
	/**
	 * 设置：
	 */
	public void setTuanduiCooperate(String tuanduiCooperate) {
		this.tuanduiCooperate = tuanduiCooperate;
	}
	/**
	 * 获取：
	 */
	public String getTuanduiCooperate() {
		return tuanduiCooperate;
	}
	/**
	 * 设置：
	 */
	public void setTuanduiSpecialist(String tuanduiSpecialist) {
		this.tuanduiSpecialist = tuanduiSpecialist;
	}
	/**
	 * 获取：
	 */
	public String getTuanduiSpecialist() {
		return tuanduiSpecialist;
	}
	/**
	 * 设置：
	 */
	public void setPersonalIsEmploy(String personalIsEmploy) {
		this.personalIsEmploy = personalIsEmploy;
	}
	/**
	 * 获取：
	 */
	public String getPersonalIsEmploy() {
		return personalIsEmploy;
	}
	/**
	 * 设置：
	 */
	public void setPersonalIsWorkEthics(String personalIsWorkEthics) {
		this.personalIsWorkEthics = personalIsWorkEthics;
	}
	/**
	 * 获取：
	 */
	public String getPersonalIsWorkEthics() {
		return personalIsWorkEthics;
	}
	/**
	 * 设置：科技进步奖平均分
	 */
	public void setScienceAvgScore(BigDecimal scienceAvgScore) {
		this.scienceAvgScore = scienceAvgScore;
	}
	/**
	 * 获取：科技进步奖平均分
	 */
	public BigDecimal getScienceAvgScore() {
		return scienceAvgScore;
	}
	/**
	 * 设置：团队平均分
	 */
	public void setTeamAvgScore(BigDecimal teamAvgScore) {
		this.teamAvgScore = teamAvgScore;
	}
	/**
	 * 获取：团队平均分
	 */
	public BigDecimal getTeamAvgScore() {
		return teamAvgScore;
	}
	/**
	 * 设置：个人平均分
	 */
	public void setPersonalAvgScore(BigDecimal personalAvgScore) {
		this.personalAvgScore = personalAvgScore;
	}
	/**
	 * 获取：个人平均分
	 */
	public BigDecimal getPersonalAvgScore() {
		return personalAvgScore;
	}
	/**
	 * 设置：专家组长审核意见
	 */
	public void setSpecialistLeaderComment(String specialistLeaderComment) {
		this.specialistLeaderComment = specialistLeaderComment;
	}
	/**
	 * 获取：专家组长审核意见
	 */
	public String getSpecialistLeaderComment() {
		return specialistLeaderComment;
	}
	/**
	 * 设置：项目的状态信息:"to_validate","待审核中，申请尚未结束"
"validate","审核中,申请结束，进入审核中"
"score","参评，企业项目可以进入打分节点"
"reject","完善后参评，企业形式审查被驳回，需企业进行二次提交审核"
"no_score","不评，不对该申请项目进行评分"
"defer_score","缓评,此次不对此申请项目进行评分"
"result","专家打分结束，算出平均分，及生成相关模板数据"
	 */
	public void setProStat(String proStat) {
		this.proStat = proStat;
	}
	/**
	 * 获取：项目的状态信息:"to_validate","待审核中，申请尚未结束"
"validate","审核中,申请结束，进入审核中"
"score","参评，企业项目可以进入打分节点"
"reject","完善后参评，企业形式审查被驳回，需企业进行二次提交审核"
"no_score","不评，不对该申请项目进行评分"
"defer_score","缓评,此次不对此申请项目进行评分"
"result","专家打分结束，算出平均分，及生成相关模板数据"
	 */
	public String getProStat() {
		return proStat;
	}
	/**
	 * 设置：项目的类型:science_progress 科技进步,science_team科技团队,science_personal科技个人,oil_install 石油安装工程优质奖,oil_quality_gold 石油优质工程金奖,oil_quality_gold 石油优质工程奖,qc_group QC奖小组
	 */
	public void setProType(String proType) {
		this.proType = proType;
	}
	/**
	 * 获取：项目的类型:science_progress 科技进步,science_team科技团队,science_personal科技个人,oil_install 石油安装工程优质奖,oil_quality_gold 石油优质工程金奖,oil_quality_gold 石油优质工程奖,qc_group QC奖小组
	 */
	public String getProType() {
		return proType;
	}
	/**
	 * 设置：专业组ID（专业组管理时更新数据）
	 */
	public void setTeamGroupId(String teamGroupId) {
		this.teamGroupId = teamGroupId;
	}
	/**
	 * 获取：专业组ID（专业组管理时更新数据）
	 */
	public String getTeamGroupId() {
		return teamGroupId;
	}
	/**
	 * 设置：项目编码，科技奖、团队、个人中每一个项目要给一个编码，作为唯一对应编码，编码按照申报时间排序，第一个申报的是01，第二个是02，最后一个是71
	 */
	public void setProCode(Integer proCode) {
		this.proCode = proCode;
	}
	/**
	 * 获取：项目编码，科技奖、团队、个人中每一个项目要给一个编码，作为唯一对应编码，编码按照申报时间排序，第一个申报的是01，第二个是02，最后一个是71
	 */
	public Integer getProCode() {
		return proCode;
	}
	/**
	 * 设置：项目成果编号，由协会联系人进行设置
	 */
	public void setProResultCode(String proResultCode) {
		this.proResultCode = proResultCode;
	}
	/**
	 * 获取：项目成果编号，由协会联系人进行设置
	 */
	public String getProResultCode() {
		return proResultCode;
	}
	/**
	 * 设置：项目子类型
	 */
	public void setProSubType(String proSubType) {
		this.proSubType = proSubType;
	}
	/**
	 * 获取：项目子类型
	 */
	public String getProSubType() {
		return proSubType;
	}
}
