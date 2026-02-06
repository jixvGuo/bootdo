package com.bootdo.cpe.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 主要公用工程消耗定额对比
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-03-27 10:11:10
 */
public class SurverDesignCommonProUseInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//项目
	private String proName;
	//计量单位
	private String calUnit;
	//设计值
	private String setVal;
	//实际值
	private String realVal;
	//国内先进水平
	private String innerAdvancedLevel;
	//国际先进水平
	private String outAdvancedLevel;
	//备注
	private String memo;
	//操作用户id
	private Integer optUid;
	//项目id
	private Integer proId;
	//任务id
	private String taskId;
	//创建日期
	private Date created;
	//更新日期
	private Date updated;
	//是否删除 0未删除，1已删除
	private Integer deleted;

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
	 * 设置：项目
	 */
	public void setProName(String proName) {
		this.proName = proName;
	}
	/**
	 * 获取：项目
	 */
	public String getProName() {
		return proName;
	}
	/**
	 * 设置：计量单位
	 */
	public void setCalUnit(String calUnit) {
		this.calUnit = calUnit;
	}
	/**
	 * 获取：计量单位
	 */
	public String getCalUnit() {
		return calUnit;
	}
	/**
	 * 设置：设计值
	 */
	public void setSetVal(String setVal) {
		this.setVal = setVal;
	}
	/**
	 * 获取：设计值
	 */
	public String getSetVal() {
		return setVal;
	}
	/**
	 * 设置：实际值
	 */
	public void setRealVal(String realVal) {
		this.realVal = realVal;
	}
	/**
	 * 获取：实际值
	 */
	public String getRealVal() {
		return realVal;
	}
	/**
	 * 设置：国内先进水平
	 */
	public void setInnerAdvancedLevel(String innerAdvancedLevel) {
		this.innerAdvancedLevel = innerAdvancedLevel;
	}
	/**
	 * 获取：国内先进水平
	 */
	public String getInnerAdvancedLevel() {
		return innerAdvancedLevel;
	}
	/**
	 * 设置：国际先进水平
	 */
	public void setOutAdvancedLevel(String outAdvancedLevel) {
		this.outAdvancedLevel = outAdvancedLevel;
	}
	/**
	 * 获取：国际先进水平
	 */
	public String getOutAdvancedLevel() {
		return outAdvancedLevel;
	}
	/**
	 * 设置：备注
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}
	/**
	 * 获取：备注
	 */
	public String getMemo() {
		return memo;
	}
	/**
	 * 设置：操作用户id
	 */
	public void setOptUid(Integer optUid) {
		this.optUid = optUid;
	}
	/**
	 * 获取：操作用户id
	 */
	public Integer getOptUid() {
		return optUid;
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
	/**
	 * 设置：更新日期
	 */
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	/**
	 * 获取：更新日期
	 */
	public Date getUpdated() {
		return updated;
	}
	/**
	 * 设置：是否删除 0未删除，1已删除
	 */
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
	/**
	 * 获取：是否删除 0未删除，1已删除
	 */
	public Integer getDeleted() {
		return deleted;
	}
}
