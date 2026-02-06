package com.bootdo.system.domain;

import com.bootdo.common.utils.StringUtils;
import com.bootdo.cpe.petroleum_engineering_award.domain.OilProStatEnum;

import java.io.Serializable;
import java.util.Date;



/**
 * 形式审查_团队
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-10-01 00:23:36
 */
public class AwardStyleValidatePersonDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//申报单位
	private String applyCompany = "";
	//姓名
	private String applyName = "";
	//是否受雇申报企业 0没有，1有
	private Integer isEmploymentRelationship = 0 ;
	//是否有违反职业道德行为 0没有，1有
	private Integer isMorality = 0;
	//知识产权证明 0没有，1有
	private Integer isKnowledgeRight = 0 ;
	//科技奖证明 0没有，1有
	private Integer isScience = 0;
	//论著 0没有，1有
	private Integer isPaper = 0 ;
	//标准（工法） 0没有，1有
	private Integer isStandard = 0;
	//其它科技荣誉 0没有，1有
	private Integer isOtherHonour = 0;
	//审核结果显示
	private String validateResult = "";
	private String validateResultShow = "";
	//审核的用户id
	private Long validateUid;
	//项目id
	private Integer proId;
	//是否为最新的一次审核0不是 1是，主要针对驳回的有记录可查
	private Integer isLastValidate = 0;
	//完善后参评 提出的修改意见内容
	private String rejectReason = "";
	//创建日期
	private Date created;
	//申报的用户记录id
	private int personId;
	//个人流程节点状态值，同项目的状态值
	private String personStat;
	//项目分组
	private String proGroupName;

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
	 * 设置：姓名
	 */
	public void setApplyName(String applyName) {
		this.applyName = applyName;
	}
	/**
	 * 获取：姓名
	 */
	public String getApplyName() {
		return applyName;
	}
	/**
	 * 设置：是否受雇申报企业 0没有，1有
	 */
	public void setIsEmploymentRelationship(Integer isEmploymentRelationship) {
		this.isEmploymentRelationship = isEmploymentRelationship;
	}
	/**
	 * 获取：是否受雇申报企业 0没有，1有
	 */
	public Integer getIsEmploymentRelationship() {
		return isEmploymentRelationship;
	}
	/**
	 * 设置：是否有违反职业道德行为 0没有，1有
	 */
	public void setIsMorality(Integer isMorality) {
		this.isMorality = isMorality;
	}
	/**
	 * 获取：是否有违反职业道德行为 0没有，1有
	 */
	public Integer getIsMorality() {
		return isMorality;
	}
	/**
	 * 设置：知识产权证明 0没有，1有
	 */
	public void setIsKnowledgeRight(Integer isKnowledgeRight) {
		this.isKnowledgeRight = isKnowledgeRight;
	}
	/**
	 * 获取：知识产权证明 0没有，1有
	 */
	public Integer getIsKnowledgeRight() {
		return isKnowledgeRight;
	}
	/**
	 * 设置：科技奖证明 0没有，1有
	 */
	public void setIsScience(Integer isScience) {
		this.isScience = isScience;
	}
	/**
	 * 获取：科技奖证明 0没有，1有
	 */
	public Integer getIsScience() {
		return isScience;
	}
	/**
	 * 设置：论著 0没有，1有
	 */
	public void setIsPaper(Integer isPaper) {
		this.isPaper = isPaper;
	}
	/**
	 * 获取：论著 0没有，1有
	 */
	public Integer getIsPaper() {
		return isPaper;
	}
	/**
	 * 设置：标准（工法） 0没有，1有
	 */
	public void setIsStandard(Integer isStandard) {
		this.isStandard = isStandard;
	}
	/**
	 * 获取：标准（工法） 0没有，1有
	 */
	public Integer getIsStandard() {
		return isStandard;
	}
	/**
	 * 设置：其它科技荣誉 0没有，1有
	 */
	public void setIsOtherHonour(Integer isOtherHonour) {
		this.isOtherHonour = isOtherHonour;
	}
	/**
	 * 获取：其它科技荣誉 0没有，1有
	 */
	public Integer getIsOtherHonour() {
		return isOtherHonour;
	}
	/**
	 * 设置：审核结果显示
	 */
	public void setValidateResult(String validateResult) {
		this.validateResult = validateResult;
		if(StringUtils.isNotBlank(validateResult)) {
			OilProStatEnum[] statEnumsArr = OilProStatEnum.values();
			for (OilProStatEnum statEnum : statEnumsArr) {
				if (validateResult.equals(statEnum.getProStat())) {
					this.validateResultShow = statEnum.getStatDesc();
				}
			}
		}
	}
	/**
	 * 获取：审核结果显示
	 */
	public String getValidateResult() {
		return validateResult;
	}

	public String getValidateResultShow() {
		return validateResultShow;
	}

	public void setValidateResultShow(String validateResultShow) {
		this.validateResultShow = validateResultShow;
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
	 * 设置：是否为最新的一次审核0不是 1是，主要针对驳回的有记录可查
	 */
	public void setIsLastValidate(Integer isLastValidate) {
		this.isLastValidate = isLastValidate;
	}
	/**
	 * 获取：是否为最新的一次审核0不是 1是，主要针对驳回的有记录可查
	 */
	public Integer getIsLastValidate() {
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

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public String getPersonStat() {
		return personStat;
	}

	public void setPersonStat(String personStat) {
		this.personStat = personStat;
	}

	public String getProGroupName() {
		return proGroupName;
	}

	public void setProGroupName(String proGroupName) {
		this.proGroupName = proGroupName;
	}
}
