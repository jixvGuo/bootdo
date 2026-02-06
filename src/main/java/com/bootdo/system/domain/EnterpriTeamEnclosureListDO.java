package com.bootdo.system.domain;

import com.bootdo.common.utils.StringUtils;

import java.io.Serializable;
import java.util.Date;



/**
 * 附件列表
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-07-05 08:21:09
 */
public class EnterpriTeamEnclosureListDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//附件编号
	private String encNum;
	//附件名称
	private String encName;
	//备注
	private String encMemo;
	//操作的用户id
	private Integer optUid;
	//任务编号
	private String taskId;
	//项目编号
	private Integer proId;
	//
	private Date created;
	//存储的附件的id
	private Long fileId;
	//附件的访问地址
	private String fileUrl;
	//附件的名称
	private String fileName;
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
	 * 设置：附件编号
	 */
	public void setEncNum(String encNum) {
		this.encNum = encNum;
	}
	/**
	 * 获取：附件编号
	 */
	public String getEncNum() {
		return encNum;
	}
	/**
	 * 设置：附件名称
	 */
	public void setEncName(String encName) {
		this.encName = encName;
	}
	/**
	 * 获取：附件名称
	 */
	public String getEncName() {
		return encName;
	}
	/**
	 * 设置：备注
	 */
	public void setEncMemo(String encMemo) {
		this.encMemo = encMemo;
	}
	/**
	 * 获取：备注
	 */
	public String getEncMemo() {
		return encMemo;
	}
	/**
	 * 设置：操作的用户id
	 */
	public void setOptUid(Integer optUid) {
		this.optUid = optUid;
	}
	/**
	 * 获取：操作的用户id
	 */
	public Integer getOptUid() {
		return optUid;
	}
	/**
	 * 设置：任务编号
	 */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	/**
	 * 获取：任务编号
	 */
	public String getTaskId() {
		return taskId;
	}
	/**
	 * 设置：项目编号
	 */
	public void setProId(Integer proId) {
		this.proId = proId;
	}
	/**
	 * 获取：项目编号
	 */
	public Integer getProId() {
		return proId;
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

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
		if (StringUtils.isNotBlank(fileUrl)) {
			String[] arr = fileUrl.split("/");
			this.fileName = arr[arr.length - 1];
		}
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
