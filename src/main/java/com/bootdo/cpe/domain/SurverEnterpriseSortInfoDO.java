package com.bootdo.cpe.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 上传排序表
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-04-02 21:56:36
 */
public class SurverEnterpriseSortInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private String taskId;
	//操作用户id
	private Integer optUid;
	//部门id
	private Integer departmentId;
	//文件id
	private Integer fileId;
	//存储地址
	private String storePath;
	//创建日期
	private Date created;
	//更新日期
	private Date updated;
	//是否删除0未删除,1已删除
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
	 * 设置：部门id
	 */
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	/**
	 * 获取：部门id
	 */
	public Integer getDepartmentId() {
		return departmentId;
	}
	/**
	 * 设置：文件id
	 */
	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}
	/**
	 * 获取：文件id
	 */
	public Integer getFileId() {
		return fileId;
	}
	/**
	 * 设置：存储地址
	 */
	public void setStorePath(String storePath) {
		this.storePath = storePath;
	}
	/**
	 * 获取：存储地址
	 */
	public String getStorePath() {
		return storePath;
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
	 * 设置：是否删除0未删除,1已删除
	 */
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
	/**
	 * 获取：是否删除0未删除,1已删除
	 */
	public Integer getDeleted() {
		return deleted;
	}
}
