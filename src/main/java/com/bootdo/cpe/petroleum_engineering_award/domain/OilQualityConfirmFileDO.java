package com.bootdo.cpe.petroleum_engineering_award.domain;

import com.bootdo.common.utils.StringUtils;

import java.io.Serializable;
import java.util.Date;



/**
 * 优质工程证实性文件
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2021-03-14 22:54:14
 */
public class OilQualityConfirmFileDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//文件id
	private Long fileId;
	//文件地址
	private String url;
	private String fileName;
	//文件类型
	private String fileType;
	//操作用户id
	private Long optUid;
	//项目id
	private Integer proId;
	//任务id
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
	 * 设置：文件id
	 */
	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}
	/**
	 * 获取：文件id
	 */
	public Long getFileId() {
		return fileId;
	}
	/**
	 * 设置：文件地址
	 */
	public void setUrl(String url) {
		this.url = url;
		if(StringUtils.isNotBlank(url)) {
			String[] arr = url.split("/");
			this.fileName = arr[arr.length - 1];
		}
	}
	/**
	 * 获取：文件地址
	 */
	public String getUrl() {
		return url;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * 设置：文件类型
	 */
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	/**
	 * 获取：文件类型
	 */
	public String getFileType() {
		return fileType;
	}
	/**
	 * 设置：操作用户id
	 */
	public void setOptUid(Long optUid) {
		this.optUid = optUid;
	}
	/**
	 * 获取：操作用户id
	 */
	public Long getOptUid() {
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
