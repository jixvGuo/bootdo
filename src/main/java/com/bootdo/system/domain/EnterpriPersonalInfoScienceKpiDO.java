package com.bootdo.system.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 申报人科技创新业绩指标
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-07-01 01:13:32
 */
public class EnterpriPersonalInfoScienceKpiDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//专利名称
	private String patentName;
	//类型（发明、实用新型、外观设计） 
	private String patentType;
	//所起作用（主持完成排名1~2、主要参加完成排名3~5、排名6及以后） 
	private String patentEffect;
	//工法名称
	private String methodName;
	//审批机构
	private String methodApprova;
	//所起作用（主持完成排名1~2、主要参加完成排名3~5、排名6及以后） 
	private String methodEffect;
	//成果名称
	private String resultName;
	//鉴定结论（国际领先、国际先进、国内领先、国内先进） 
	private String resultAppraisal;
	//所起作用（主持完成排名1~2、主要参加完成排名3~5、排名6及以后） 
	private String resultEffect;
	//成果名称及获奖等级
	private String awardName;
	//奖项名称及发奖机构
	private String awardOrganization;
	//所起作用（主持完成排名1~2、主要参加完成排名3~5、排名6及以后） 
	private String awardEffect;
	//名称及发表（发行）时间 
	private String paperName;
	//类别（著作、期刊、会议论文） 
	private String paperType;
	//所起作用（主持完成排名1~2、主要参加完成排名3~5、排名6及以后） 
	private String paperEffect;
	//荣誉名称
	private String honorName;
	//发奖机构
	private String honorOrganization;
	//获奖年份
	private String honorTime;
	//任务id
	private String taskId;
	//申请id
	private String applyId;
	//申请操作用户id
	private Long applyOptUid;
	//
	private Date created;
	//项目id
	private int proId;

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
	 * 设置：专利名称
	 */
	public void setPatentName(String patentName) {
		this.patentName = patentName;
	}
	/**
	 * 获取：专利名称
	 */
	public String getPatentName() {
		return patentName;
	}
	/**
	 * 设置：类型（发明、实用新型、外观设计） 
	 */
	public void setPatentType(String patentType) {
		this.patentType = patentType;
	}
	/**
	 * 获取：类型（发明、实用新型、外观设计） 
	 */
	public String getPatentType() {
		return patentType;
	}
	/**
	 * 设置：所起作用（主持完成排名1~2、主要参加完成排名3~5、排名6及以后） 
	 */
	public void setPatentEffect(String patentEffect) {
		this.patentEffect = patentEffect;
	}
	/**
	 * 获取：所起作用（主持完成排名1~2、主要参加完成排名3~5、排名6及以后） 
	 */
	public String getPatentEffect() {
		return patentEffect;
	}
	/**
	 * 设置：工法名称
	 */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	/**
	 * 获取：工法名称
	 */
	public String getMethodName() {
		return methodName;
	}
	/**
	 * 设置：审批机构
	 */
	public void setMethodApprova(String methodApprova) {
		this.methodApprova = methodApprova;
	}
	/**
	 * 获取：审批机构
	 */
	public String getMethodApprova() {
		return methodApprova;
	}
	/**
	 * 设置：所起作用（主持完成排名1~2、主要参加完成排名3~5、排名6及以后） 
	 */
	public void setMethodEffect(String methodEffect) {
		this.methodEffect = methodEffect;
	}
	/**
	 * 获取：所起作用（主持完成排名1~2、主要参加完成排名3~5、排名6及以后） 
	 */
	public String getMethodEffect() {
		return methodEffect;
	}
	/**
	 * 设置：成果名称
	 */
	public void setResultName(String resultName) {
		this.resultName = resultName;
	}
	/**
	 * 获取：成果名称
	 */
	public String getResultName() {
		return resultName;
	}
	/**
	 * 设置：鉴定结论（国际领先、国际先进、国内领先、国内先进） 
	 */
	public void setResultAppraisal(String resultAppraisal) {
		this.resultAppraisal = resultAppraisal;
	}
	/**
	 * 获取：鉴定结论（国际领先、国际先进、国内领先、国内先进） 
	 */
	public String getResultAppraisal() {
		return resultAppraisal;
	}
	/**
	 * 设置：所起作用（主持完成排名1~2、主要参加完成排名3~5、排名6及以后） 
	 */
	public void setResultEffect(String resultEffect) {
		this.resultEffect = resultEffect;
	}
	/**
	 * 获取：所起作用（主持完成排名1~2、主要参加完成排名3~5、排名6及以后） 
	 */
	public String getResultEffect() {
		return resultEffect;
	}
	/**
	 * 设置：成果名称及获奖等级
	 */
	public void setAwardName(String awardName) {
		this.awardName = awardName;
	}
	/**
	 * 获取：成果名称及获奖等级
	 */
	public String getAwardName() {
		return awardName;
	}
	/**
	 * 设置：奖项名称及发奖机构
	 */
	public void setAwardOrganization(String awardOrganization) {
		this.awardOrganization = awardOrganization;
	}
	/**
	 * 获取：奖项名称及发奖机构
	 */
	public String getAwardOrganization() {
		return awardOrganization;
	}
	/**
	 * 设置：所起作用（主持完成排名1~2、主要参加完成排名3~5、排名6及以后） 
	 */
	public void setAwardEffect(String awardEffect) {
		this.awardEffect = awardEffect;
	}
	/**
	 * 获取：所起作用（主持完成排名1~2、主要参加完成排名3~5、排名6及以后） 
	 */
	public String getAwardEffect() {
		return awardEffect;
	}
	/**
	 * 设置：名称及发表（发行）时间 
	 */
	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}
	/**
	 * 获取：名称及发表（发行）时间 
	 */
	public String getPaperName() {
		return paperName;
	}
	/**
	 * 设置：类别（著作、期刊、会议论文） 
	 */
	public void setPaperType(String paperType) {
		this.paperType = paperType;
	}
	/**
	 * 获取：类别（著作、期刊、会议论文） 
	 */
	public String getPaperType() {
		return paperType;
	}
	/**
	 * 设置：所起作用（主持完成排名1~2、主要参加完成排名3~5、排名6及以后） 
	 */
	public void setPaperEffect(String paperEffect) {
		this.paperEffect = paperEffect;
	}
	/**
	 * 获取：所起作用（主持完成排名1~2、主要参加完成排名3~5、排名6及以后） 
	 */
	public String getPaperEffect() {
		return paperEffect;
	}
	/**
	 * 设置：荣誉名称
	 */
	public void setHonorName(String honorName) {
		this.honorName = honorName;
	}
	/**
	 * 获取：荣誉名称
	 */
	public String getHonorName() {
		return honorName;
	}
	/**
	 * 设置：发奖机构
	 */
	public void setHonorOrganization(String honorOrganization) {
		this.honorOrganization = honorOrganization;
	}
	/**
	 * 获取：发奖机构
	 */
	public String getHonorOrganization() {
		return honorOrganization;
	}
	/**
	 * 设置：获奖年份
	 */
	public void setHonorTime(String honorTime) {
		this.honorTime = honorTime;
	}
	/**
	 * 获取：获奖年份
	 */
	public String getHonorTime() {
		return honorTime;
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
	 * 设置：申请id
	 */
	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
	/**
	 * 获取：申请id
	 */
	public String getApplyId() {
		return applyId;
	}
	/**
	 * 设置：申请操作用户id
	 */
	public void setApplyOptUid(Long applyOptUid) {
		this.applyOptUid = applyOptUid;
	}
	/**
	 * 获取：申请操作用户id
	 */
	public Long getApplyOptUid() {
		return applyOptUid;
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

	public int getProId() {
		return proId;
	}

	public void setProId(int proId) {
		this.proId = proId;
	}
}
