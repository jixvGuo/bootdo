package com.bootdo.system.domain;

import com.bootdo.cpe.petroleum_engineering_award.domain.ScienceBaseProjectInfoDO;
import com.bootdo.cpe.utils.EnumUtils;

import java.io.Serializable;
import java.util.Date;



/**
 * 先进团队评审网页
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-06-30 04:48:14
 */
public class EnterpriTeamInfoDO extends ScienceBaseProjectInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;
	//项目类别
	private String proType = "团队";
	private String proStatStr;
	//
	private Integer id;
	/**
	 * 显示的编号
	 */
	private int showNum;
	//项目编号
	private String proResultCode;
	//团队名称
	private String teamName;
	//研究方向
	private String researchDirection;
	//团队带头人
	private String leader;
	//主要支持单位
	private String mainSupportCompany;
	//团队成立时间
	private String teamBuildTime;
	// 申报单位
	private String applyCompany;
	//申报材料
	private String applyData;
	//密级
	private String securityLevel;
	//可否公示
	private String isPublic;
	//是否宣传
	private String isPropaganda;
	//学科分类名称
	private String subjectClassificationName;
	//所属国民经济行业
	private String industryNationalEconomy;
	//所属国家重点发展领域
	private String nationalKeyDevelopmentAreas;
	//
	private Integer optUid;
	//任务id
	private String taskId;
	//
	private Date created;

	private String teamStat;
	//团队简介
	private String teamDesc;
    //项目id
	private int proId;
	//审核日期
	private String validateDate;
	//申报企业的名称
	private String enterpriseName;
	//打分的日期
	private String scoreDate;
	/**
	 * 专业
	 */
	private String major;

	private String outUserNames;

	public String getProType() {
		return proType;
	}

	public String getProStatStr() {
		return proStatStr;
	}

	public void setProStatStr(String proStatStr) {
		this.proStatStr = proStatStr;
	}

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	public int getShowNum() {
		return showNum;
	}

	public void setShowNum(int showNum) {
		this.showNum = showNum;
	}

	public String getProResultCode() {
		return proResultCode;
	}

	public void setProResultCode(String proResultCode) {
		this.proResultCode = proResultCode;
	}

	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 设置：团队名称
	 */
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	/**
	 * 获取：团队名称
	 */
	public String getTeamName() {
		return teamName;
	}
	/**
	 * 设置：研究方向
	 */
	public void setResearchDirection(String researchDirection) {
		this.researchDirection = researchDirection;
	}
	/**
	 * 获取：研究方向
	 */
	public String getResearchDirection() {
		return researchDirection;
	}
	/**
	 * 设置：团队带头人
	 */
	public void setLeader(String leader) {
		this.leader = leader;
	}
	/**
	 * 获取：团队带头人
	 */
	public String getLeader() {
		return leader;
	}
	/**
	 * 设置：主要支持单位
	 */
	public void setMainSupportCompany(String mainSupportCompany) {
		this.mainSupportCompany = mainSupportCompany;
	}
	/**
	 * 获取：主要支持单位
	 */
	public String getMainSupportCompany() {
		return mainSupportCompany;
	}
	/**
	 * 设置：团队成立时间
	 */
	public void setTeamBuildTime(String teamBuildTime) {
		this.teamBuildTime = teamBuildTime;
	}
	/**
	 * 获取：团队成立时间
	 */
	public String getTeamBuildTime() {
		return teamBuildTime;
	}
	/**
	 * 设置： 申报单位
	 */
	public void setApplyCompany(String applyCompany) {
		this.applyCompany = applyCompany;
	}
	/**
	 * 获取： 申报单位
	 */
	public String getApplyCompany() {
		return applyCompany;
	}
	/**
	 * 设置：申报材料
	 */
	public void setApplyData(String applyData) {
		this.applyData = applyData;
	}
	/**
	 * 获取：申报材料
	 */
	public String getApplyData() {
		return applyData;
	}
	/**
	 * 设置：密级
	 */
	public void setSecurityLevel(String securityLevel) {
		this.securityLevel = securityLevel;
	}
	/**
	 * 获取：密级
	 */
	public String getSecurityLevel() {
		return securityLevel;
	}
	/**
	 * 设置：可否公示
	 */
	public void setIsPublic(String isPublic) {
		this.isPublic = isPublic;
	}
	/**
	 * 获取：可否公示
	 */
	public String getIsPublic() {
		return isPublic;
	}
	/**
	 * 设置：
	 */
	public void setIsPropaganda(String isPropaganda) {
		this.isPropaganda = isPropaganda;
	}
	/**
	 * 获取：
	 */
	public String getIsPropaganda() {
		return isPropaganda;
	}
	/**
	 * 设置：学科分类名称
	 */
	public void setSubjectClassificationName(String subjectClassificationName) {
		this.subjectClassificationName = subjectClassificationName;
	}
	/**
	 * 获取：学科分类名称
	 */
	public String getSubjectClassificationName() {
		return subjectClassificationName;
	}
	/**
	 * 设置：所属国民经济行业
	 */
	public void setIndustryNationalEconomy(String industryNationalEconomy) {
		this.industryNationalEconomy = industryNationalEconomy;
	}
	/**
	 * 获取：所属国民经济行业
	 */
	public String getIndustryNationalEconomy() {
		return industryNationalEconomy;
	}
	/**
	 * 设置：所属国家重点发展领域
	 */
	public void setNationalKeyDevelopmentAreas(String nationalKeyDevelopmentAreas) {
		this.nationalKeyDevelopmentAreas = nationalKeyDevelopmentAreas;
	}
	/**
	 * 获取：所属国家重点发展领域
	 */
	public String getNationalKeyDevelopmentAreas() {
		return nationalKeyDevelopmentAreas;
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
	 * 设置：任务id
	 */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	/**
	 * 获取：任务id
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

	public String getTeamStat() {
		return teamStat;
	}

	public void setTeamStat(String teamStat) {
		this.teamStat = teamStat;
	}

	/**
	 * 设置：团队简介
	 */
	public void setTeamDesc(String teamDesc) {
		this.teamDesc = teamDesc;
	}
	/**
	 * 获取：团队简介
	 */
	public String getTeamDesc() {
		return teamDesc;
	}

	public int getProId() {
		return proId;
	}

	public void setProId(int proId) {
		this.proId = proId;
	}

	public String getValidateDate() {
		return validateDate;
	}

	public void setValidateDate(String validateDate) {
		this.validateDate = validateDate;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getScoreDate() {
		return scoreDate;
	}

	public void setScoreDate(String scoreDate) {
		scoreDate = scoreDate.replaceAll("\\.0","");
		this.scoreDate = scoreDate;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getOutUserNames() {
		return outUserNames;
	}

	public void setOutUserNames(String outUserNames) {
		this.outUserNames = outUserNames;
	}
}
