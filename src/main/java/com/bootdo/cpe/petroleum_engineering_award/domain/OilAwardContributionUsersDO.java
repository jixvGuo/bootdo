package com.bootdo.cpe.petroleum_engineering_award.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2021-02-20 22:55:26
 */
public class OilAwardContributionUsersDO implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	private Integer id;
	//承建（参建）单位
	private String contractorsParticipants;
	//姓名
	private String name;
	//职务

	private int type ;
	private String post;
	//工程建设中的作用
	private String affectProjectConstruction;
	//             affect_project_construction
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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
	 * 设置：承建（参建）单位
	 */
	public void setContractorsParticipants(String contractorsParticipants) {
		this.contractorsParticipants = contractorsParticipants;
	}
	/**
	 * 获取：承建（参建）单位
	 */
	public String getContractorsParticipants() {
		return contractorsParticipants;
	}
	/**
	 * 设置：姓名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：姓名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：职务

	 */
	public void setPost(String post) {
		this.post = post;
	}
	/**
	 * 获取：职务

	 */
	public String getPost() {
		return post;
	}
	/**
	 * 设置：工程建设中的作用
	 */
	public void setAffectProjectConstruction(String affectProjectConstruction) {
		this.affectProjectConstruction = affectProjectConstruction;
	}
	/**
	 * 获取：工程建设中的作用
	 */
	public String getAffectProjectConstruction() {
		return affectProjectConstruction;
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
