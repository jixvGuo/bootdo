package com.bootdo.system.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 近十年内由申报人参与完成并取得授权的专利 
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-06-30 04:48:14
 */
public class EnterpriPersonalInfoTenYearsPatentDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private Integer personalId;
	//
	private String name;
	//
	private String type;
	//
	private String effect;
	//指标索引值:1、近十年内由申报人参与完成并取得授权的专利 ;2、近十年内由申报人参与完成并获批工法 ;3、近十年内由申报人参与完成并通过具有科技成果鉴定权机构鉴定的科技成果(不含QC) ;4、近十年内由申报人参与完成的成果所获科技类奖项（不含QC） ;5、近十年内由申报人参与完成并公开发表、发行的论文及专著 ;6. 近十年内申报人获得的科技类个人荣誉（称号）奖 
	private Integer indicatorIndex;
	//操作的用户id
	private Integer optUid;
	//
	private Date created;
	//任务id
	private String taskId;
	//申请的id编号
	private String applyId;
	//项目id
	private int proId;

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
	public void setPersonalId(Integer personalId) {
		this.personalId = personalId;
	}
	/**
	 * 获取：
	 */
	public Integer getPersonalId() {
		return personalId;
	}
	/**
	 * 设置：
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：
	 */
	public String getType() {
		return type;
	}
	/**
	 * 设置：
	 */
	public void setEffect(String effect) {
		this.effect = effect;
	}
	/**
	 * 获取：
	 */
	public String getEffect() {
		return effect;
	}
	/**
	 * 设置：指标索引值:1、近十年内由申报人参与完成并取得授权的专利 ;2、近十年内由申报人参与完成并获批工法 ;3、近十年内由申报人参与完成并通过具有科技成果鉴定权机构鉴定的科技成果(不含QC) ;4、近十年内由申报人参与完成的成果所获科技类奖项（不含QC） ;5、近十年内由申报人参与完成并公开发表、发行的论文及专著 ;6. 近十年内申报人获得的科技类个人荣誉（称号）奖 
	 */
	public void setIndicatorIndex(Integer indicatorIndex) {
		this.indicatorIndex = indicatorIndex;
	}
	/**
	 * 获取：指标索引值:1、近十年内由申报人参与完成并取得授权的专利 ;2、近十年内由申报人参与完成并获批工法 ;3、近十年内由申报人参与完成并通过具有科技成果鉴定权机构鉴定的科技成果(不含QC) ;4、近十年内由申报人参与完成的成果所获科技类奖项（不含QC） ;5、近十年内由申报人参与完成并公开发表、发行的论文及专著 ;6. 近十年内申报人获得的科技类个人荣誉（称号）奖 
	 */
	public Integer getIndicatorIndex() {
		return indicatorIndex;
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

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}

	public int getProId() {
		return proId;
	}

	public void setProId(int proId) {
		this.proId = proId;
	}
}
