package com.bootdo.cpe.petroleum_engineering_award.domain;

import io.swagger.models.auth.In;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2021-02-20 22:55:27
 */
public class OilProUnitOpinionDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//建设单位（使用单位）意见：
	private String constructionOpinions = "";
	//设计单位意见：
	private String designUnitOpinions = "";
	//监理单位意见：
	private String supervisionOpinions = "";
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
	 * 设置：建设单位（使用单位）意见：
	 */
	public void setConstructionOpinions(String constructionOpinions) {
		this.constructionOpinions = constructionOpinions;
	}
	/**
	 * 获取：建设单位（使用单位）意见：
	 */
	public String getConstructionOpinions() {
		return constructionOpinions;
	}
	/**
	 * 设置：设计单位意见：
	 */
	public void setDesignUnitOpinions(String designUnitOpinions) {
		this.designUnitOpinions = designUnitOpinions;
	}
	/**
	 * 获取：设计单位意见：
	 */
	public String getDesignUnitOpinions() {
		return designUnitOpinions;
	}
	/**
	 * 设置：监理单位意见：
	 */
	public void setSupervisionOpinions(String supervisionOpinions) {
		this.supervisionOpinions = supervisionOpinions;
	}
	/**
	 * 获取：监理单位意见：
	 */
	public String getSupervisionOpinions() {
		return supervisionOpinions;
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
