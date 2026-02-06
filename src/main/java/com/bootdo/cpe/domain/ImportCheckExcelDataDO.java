package com.bootdo.cpe.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 导入的excel的
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-09-05 06:52:54
 */
public class ImportCheckExcelDataDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private String excelTabName;
	//奖项类型
	private String awardType;
	//序号
	private String excelNum;
	//申报账号
	private String applyAccount;
	//成果名称
	private String resultName;
	//申报企业
	private String applyEnterprise;
	//审查状态
	private String checkStat;
	//审查问题说明
	private String checkOpinion;
	//专业
	private String major;
	//系统对应的校验结果值
	private String validateResult;
	//备注
	private String memo;
	//项目分组名称
	private String proGroupName;
	//操作用户id
	private Integer optUid;
	//
	private Integer proId;
	//任务id
	private String taskId;
	//创建日期
	private Date created;
	//更新日期
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
	 * 设置：
	 */
	public void setExcelTabName(String excelTabName) {
		this.excelTabName = excelTabName;
	}
	/**
	 * 获取：
	 */
	public String getExcelTabName() {
		return excelTabName;
	}
	/**
	 * 设置：奖项类型
	 */
	public void setAwardType(String awardType) {
		this.awardType = awardType;
	}
	/**
	 * 获取：奖项类型
	 */
	public String getAwardType() {
		return awardType;
	}
	/**
	 * 设置：序号
	 */
	public void setExcelNum(String excelNum) {
		this.excelNum = excelNum;
	}
	/**
	 * 获取：序号
	 */
	public String getExcelNum() {
		return excelNum;
	}
	/**
	 * 设置：申报账号
	 */
	public void setApplyAccount(String applyAccount) {
		this.applyAccount = applyAccount;
	}
	/**
	 * 获取：申报账号
	 */
	public String getApplyAccount() {
		return applyAccount;
	}
	/**
	 * 设置：成果名称
	 */
	public void setResultName(String resultName) {
		this.resultName = resultName;
	}
	/**
	 * 获取：成果名称
	 */
	public String getResultName() {
		return resultName;
	}
	/**
	 * 设置：申报企业
	 */
	public void setApplyEnterprise(String applyEnterprise) {
		this.applyEnterprise = applyEnterprise;
	}
	/**
	 * 获取：申报企业
	 */
	public String getApplyEnterprise() {
		return applyEnterprise;
	}
	/**
	 * 设置：审查状态
	 */
	public void setCheckStat(String checkStat) {
		this.checkStat = checkStat;
	}
	/**
	 * 获取：审查状态
	 */
	public String getCheckStat() {
		return checkStat;
	}
	/**
	 * 设置：审查问题说明
	 */
	public void setCheckOpinion(String checkOpinion) {
		this.checkOpinion = checkOpinion;
	}
	/**
	 * 获取：审查问题说明
	 */
	public String getCheckOpinion() {
		return checkOpinion;
	}
	/**
	 * 设置：专业
	 */
	public void setMajor(String major) {
		this.major = major;
	}
	/**
	 * 获取：专业
	 */
	public String getMajor() {
		return major;
	}
	/**
	 * 设置：系统对应的校验结果值
	 */
	public void setValidateResult(String validateResult) {
		this.validateResult = validateResult;
	}
	/**
	 * 获取：系统对应的校验结果值
	 */
	public String getValidateResult() {
		return validateResult;
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

	public String getProGroupName() {
		return proGroupName;
	}

	public void setProGroupName(String proGroupName) {
		this.proGroupName = proGroupName;
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
	 * 设置：
	 */
	public void setProId(Integer proId) {
		this.proId = proId;
	}
	/**
	 * 获取：
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
}
