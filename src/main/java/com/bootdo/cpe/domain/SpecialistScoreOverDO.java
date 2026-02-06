package com.bootdo.cpe.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 专家打分结束记录
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-10-10 01:08:19
 */
public class SpecialistScoreOverDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//打分专家的用户id
	private Long scoreUid;
	//项目id
	private Integer proId;
	//创建日期
	private Date created;

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
	 * 设置：打分专家的用户id
	 */
	public void setScoreUid(Long scoreUid) {
		this.scoreUid = scoreUid;
	}
	/**
	 * 获取：打分专家的用户id
	 */
	public Long getScoreUid() {
		return scoreUid;
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
}
