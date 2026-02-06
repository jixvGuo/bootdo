package com.bootdo.system.domain;

import java.io.Serializable;
import java.util.Date;


/***
 * 团队基本情况说明
  */
public class EnterpriTeamBaseInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id = 0;

	//
	private String taskId;
	//
	private Integer optUid;
	//
	private Date created;

	private Integer proId;

	private String teamDes;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public Integer getOptUid() {
		return optUid;
	}

	public void setOptUid(Integer optUid) {
		this.optUid = optUid;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Integer getProId() {
		return proId;
	}

	public void setProId(Integer proId) {
		this.proId = proId;
	}

	public String getTeamDes() {
		return teamDes;
	}

	public void setTeamDes(String teamDes) {
		this.teamDes = teamDes;
	}
}
