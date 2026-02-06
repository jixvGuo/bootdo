package com.bootdo.cpe.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 项目的打分结果记录
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-10-10 02:22:28
 */
public class AwardProjectScoreResultDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private int id;
	//项目id
	private int proId;
	//子项id，团队个人
	private int itemId;
	//分数
	private BigDecimal scoreResult;
	//出结果的时间
	private Date created;

	/**
	 * 设置：
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public int getId() {
		return id;
	}
	/**
	 * 设置：项目id
	 */
	public void setProId(int proId) {
		this.proId = proId;
	}
	/**
	 * 获取：项目id
	 */
	public int getProId() {
		return proId;
	}
	/**
	 * 设置：子项id，团队个人
	 */
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	/**
	 * 获取：子项id，团队个人
	 */
	public int getItemId() {
		return itemId;
	}
	/**
	 * 设置：分数
	 */
	public void setScoreResult(BigDecimal scoreResult) {
		this.scoreResult = scoreResult;
	}
	/**
	 * 获取：分数
	 */
	public BigDecimal getScoreResult() {
		return scoreResult;
	}
	/**
	 * 设置：出结果的时间
	 */
	public void setCreated(Date created) {
		this.created = created;
	}
	/**
	 * 获取：出结果的时间
	 */
	public Date getCreated() {
		return created;
	}
}
