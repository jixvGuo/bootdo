package com.bootdo.cpe.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * QC形式审查模板
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-02-20 21:57:29
 */
public class QcReviewResultRecordDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//备注
	private String remarks;
	//附件1、2和3是否有盖章
	private String annexIsStamped;
	//会员单位或其上级主管部门组织的评价，其评价人员中至少有1名获得中国质量协会或相关机构颁发的质量小组活动推进中级以上资格证书
	private String userRecommendIsMiddleCredentials;
	//各会员单位，应重视质量管理小组活动推进人员队伍建设，至少培养2名以上具有质量管理小组活动推进中级以上资格的人员。
	private String userBuildIsMiddleCredentials;
	//应为会员单位或其上级主管部门组织评价出的优秀QC小组。
	private String recommendIsBestGroup;
	//属于近2年的活动成果(封面)
	private String actIsCover;
	//审查结论
	private String reviewResult;
	//意见说明
	private String opinionDesc;
	//
	private Integer optUid;
	//
	private Integer proId;
	//
	private String taskId;
	//
	private String applyId;
	//
	private Date created;
	//
	private Date updated;

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
	 * 设置：备注
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * 获取：备注
	 */
	public String getRemarks() {
		return remarks;
	}
	/**
	 * 设置：附件1、2和3是否有盖章
	 */
	public void setAnnexIsStamped(String annexIsStamped) {
		this.annexIsStamped = annexIsStamped;
	}
	/**
	 * 获取：附件1、2和3是否有盖章
	 */
	public String getAnnexIsStamped() {
		return annexIsStamped;
	}
	/**
	 * 设置：会员单位或其上级主管部门组织的评价，其评价人员中至少有1名获得中国质量协会或相关机构颁发的质量小组活动推进中级以上资格证书
	 */
	public void setUserRecommendIsMiddleCredentials(String userRecommendIsMiddleCredentials) {
		this.userRecommendIsMiddleCredentials = userRecommendIsMiddleCredentials;
	}
	/**
	 * 获取：会员单位或其上级主管部门组织的评价，其评价人员中至少有1名获得中国质量协会或相关机构颁发的质量小组活动推进中级以上资格证书
	 */
	public String getUserRecommendIsMiddleCredentials() {
		return userRecommendIsMiddleCredentials;
	}
	/**
	 * 设置：各会员单位，应重视质量管理小组活动推进人员队伍建设，至少培养2名以上具有质量管理小组活动推进中级以上资格的人员。
	 */
	public void setUserBuildIsMiddleCredentials(String userBuildIsMiddleCredentials) {
		this.userBuildIsMiddleCredentials = userBuildIsMiddleCredentials;
	}
	/**
	 * 获取：各会员单位，应重视质量管理小组活动推进人员队伍建设，至少培养2名以上具有质量管理小组活动推进中级以上资格的人员。
	 */
	public String getUserBuildIsMiddleCredentials() {
		return userBuildIsMiddleCredentials;
	}
	/**
	 * 设置：应为会员单位或其上级主管部门组织评价出的优秀QC小组。
	 */
	public void setRecommendIsBestGroup(String recommendIsBestGroup) {
		this.recommendIsBestGroup = recommendIsBestGroup;
	}
	/**
	 * 获取：应为会员单位或其上级主管部门组织评价出的优秀QC小组。
	 */
	public String getRecommendIsBestGroup() {
		return recommendIsBestGroup;
	}
	/**
	 * 设置：属于近2年的活动成果(封面)
	 */
	public void setActIsCover(String actIsCover) {
		this.actIsCover = actIsCover;
	}
	/**
	 * 获取：属于近2年的活动成果(封面)
	 */
	public String getActIsCover() {
		return actIsCover;
	}
	/**
	 * 设置：审查结论
	 */
	public void setReviewResult(String reviewResult) {
		this.reviewResult = reviewResult;
	}
	/**
	 * 获取：审查结论
	 */
	public String getReviewResult() {
		return reviewResult;
	}
	/**
	 * 设置：意见说明
	 */
	public void setOpinionDesc(String opinionDesc) {
		this.opinionDesc = opinionDesc;
	}
	/**
	 * 获取：意见说明
	 */
	public String getOpinionDesc() {
		return opinionDesc;
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
	 * 设置：
	 */
	public void setProId(Integer proId) {
		this.proId = proId;
	}
	/**
	 * 获取：
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
