package com.bootdo.system.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;



/**
 * 企业科技进步奖知识产权信息
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-09-08 22:47:30
 */
public class EnterpriseScienceAwardKnowledgeInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//项目id
	private Integer proId;
	//知识产权类别
	private String kType;
	//知识产权名称
	private String kName;
	//国家（地区）
	private String kArea;
	//授权号
	private String kAuthCode;
	//授权日期
	private String kAuthDate;
	//证书编号
	private String kBookCode;
	//权利人
	private String kRightUser;
	//发明人
	private String kCreateUser;
	//专利有效状态
	private String kEffectiveStat;
	//填写的用户id
	private Long uid;
	//填写日期
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
	 * 设置：知识产权类别
	 */
    @JsonProperty(value = "kType")
	public void setKType(String kType) {
		this.kType = kType;
	}
	/**
	 * 获取：知识产权类别
	 */
    @JsonProperty(value = "kType")
	public String getKType() {
		return kType;
	}
	/**
	 * 设置：知识产权名称
	 */
    @JsonProperty(value = "kName")
	public void setKName(String kName) {
		this.kName = kName;
	}
	/**
	 * 获取：知识产权名称
	 */
	@JsonProperty(value = "kName")
	public String getKName() {
		return kName;
	}
	/**
	 * 设置：国家（地区）
	 */
	@JsonProperty(value = "kArea")
	public void setKArea(String kArea) {
		this.kArea = kArea;
	}
	/**
	 * 获取：国家（地区）
	 */
	@JsonProperty(value = "kArea")
	public String getKArea() {
		return kArea;
	}
	/**
	 * 设置：授权号
	 */
	@JsonProperty(value = "kAuthCode")
	public void setKAuthCode(String kAuthCode) {
		this.kAuthCode = kAuthCode;
	}
	/**
	 * 获取：授权号
	 */
	@JsonProperty(value = "kAuthCode")
	public String getKAuthCode() {
		return kAuthCode;
	}
	/**
	 * 设置：授权日期
	 */
	@JsonProperty(value = "kAuthDate")
	public void setKAuthDate(String kAuthDate) {
		this.kAuthDate = kAuthDate;
	}
	/**
	 * 获取：授权日期
	 */
	@JsonProperty(value = "kAuthDate")
	public String getKAuthDate() {
		return kAuthDate;
	}
	/**
	 * 设置：证书编号
	 */
	@JsonProperty(value = "kBookCode")
	public void setKBookCode(String kBookCode) {
		this.kBookCode = kBookCode;
	}
	/**
	 * 获取：证书编号
	 */
	@JsonProperty(value = "kBookCode")
	public String getKBookCode() {
		return kBookCode;
	}
	/**
	 * 设置：权利人
	 */
	@JsonProperty(value = "kRightUser")
	public void setKRightUser(String kRightUser) {
		this.kRightUser = kRightUser;
	}
	/**
	 * 获取：权利人
	 */
	@JsonProperty(value = "kRightUser")
	public String getKRightUser() {
		return kRightUser;
	}
	/**
	 * 设置：发明人
	 */
	@JsonProperty(value = "kCreateUser")
	public void setKCreateUser(String kCreateUser) {
		this.kCreateUser = kCreateUser;
	}
	/**
	 * 获取：发明人
	 */
	@JsonProperty(value = "kCreateUser")
	public String getKCreateUser() {
		return kCreateUser;
	}
	/**
	 * 设置：专利有效状态
	 */
	@JsonProperty(value = "kEffectiveStat")
	public void setKEffectiveStat(String kEffectiveStat) {
		this.kEffectiveStat = kEffectiveStat;
	}
	/**
	 * 获取：专利有效状态
	 */
	@JsonProperty(value = "kEffectiveStat")
	public String getKEffectiveStat() {
		return kEffectiveStat;
	}
	/**
	 * 设置：填写的用户id
	 */
	public void setUid(Long uid) {
		this.uid = uid;
	}
	/**
	 * 获取：填写的用户id
	 */
	public Long getUid() {
		return uid;
	}
	/**
	 * 设置：填写日期
	 */
	public void setCreated(Date created) {
		this.created = created;
	}
	/**
	 * 获取：填写日期
	 */
	public Date getCreated() {
		return created;
	}
}
