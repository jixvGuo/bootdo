package com.bootdo.system.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 人员构成
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-06-08 00:58:54
 */
public class EnterpriTeamUsersDO implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	private Integer id;
	//总人数
	private String totalCount;
	//男人数
	private String manCount;
	//女人数
	private String womanCount;
	//65周岁以上
	private String ageBeyond65Count;
	// 50-65周岁
	private String age50To65Count;
	// 35-50周岁
	private String age35To50Count;
	// 35周岁以下
	private String ageUnder36Count;
	//正高级
	private String positionSenior;
	//副高级
	private String positionDeputySenior;
	//中级
	private String positionIntermediate;
	//博士研究生
	private String eduDoctoralCandidate;
	//硕士研究生
	private String eduPostgraduates;
	//本科
	private String eduUndergraduate;
	//
	private String taskId;
	//
	private Integer optUid;
	//
	private Date created;
	//项目id
	private Integer proId;
	//
	private String ageBeyond65CountNum;
	//
	private String age50To65CountNum;
	//
	private String age35To50CountNum;
	//
	private String ageUnder36CountNum;
	//
	private String positionSeniorNum;
	//
	private String positionDeputySeniorNum;
	//
	private String positionIntermediateNum;
	//
	private String eduDoctoralCandidateNum;
	//
	private String eduPostgraduatesNum;
	//
	private String eduUndergraduateNum;

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
	 * 设置：总人数
	 */
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}
	/**
	 * 获取：总人数
	 */
	public String getTotalCount() {
		return totalCount;
	}
	/**
	 * 设置：男人数
	 */
	public void setManCount(String manCount) {
		this.manCount = manCount;
	}
	/**
	 * 获取：男人数
	 */
	public String getManCount() {
		return manCount;
	}
	/**
	 * 设置：女人数
	 */
	public void setWomanCount(String womanCount) {
		this.womanCount = womanCount;
	}
	/**
	 * 获取：女人数
	 */
	public String getWomanCount() {
		return womanCount;
	}
	/**
	 * 设置：65周岁以上
	 */
	public void setAgeBeyond65Count(String ageBeyond65Count) {
		this.ageBeyond65Count = ageBeyond65Count;
	}
	/**
	 * 获取：65周岁以上
	 */
	public String getAgeBeyond65Count() {
		return ageBeyond65Count;
	}
	/**
	 * 设置： 50-65周岁
	 */
	public void setAge50To65Count(String age50To65Count) {
		this.age50To65Count = age50To65Count;
	}
	/**
	 * 获取： 50-65周岁
	 */
	public String getAge50To65Count() {
		return age50To65Count;
	}
	/**
	 * 设置： 35-50周岁
	 */
	public void setAge35To50Count(String age35To50Count) {
		this.age35To50Count = age35To50Count;
	}
	/**
	 * 获取： 35-50周岁
	 */
	public String getAge35To50Count() {
		return age35To50Count;
	}
	/**
	 * 设置： 35周岁以下
	 */
	public void setAgeUnder36Count(String ageUnder36Count) {
		this.ageUnder36Count = ageUnder36Count;
	}
	/**
	 * 获取： 35周岁以下
	 */
	public String getAgeUnder36Count() {
		return ageUnder36Count;
	}
	/**
	 * 设置：正高级
	 */
	public void setPositionSenior(String positionSenior) {
		this.positionSenior = positionSenior;
	}
	/**
	 * 获取：正高级
	 */
	public String getPositionSenior() {
		return positionSenior;
	}
	/**
	 * 设置：副高级
	 */
	public void setPositionDeputySenior(String positionDeputySenior) {
		this.positionDeputySenior = positionDeputySenior;
	}
	/**
	 * 获取：副高级
	 */
	public String getPositionDeputySenior() {
		return positionDeputySenior;
	}
	/**
	 * 设置：中级
	 */
	public void setPositionIntermediate(String positionIntermediate) {
		this.positionIntermediate = positionIntermediate;
	}
	/**
	 * 获取：中级
	 */
	public String getPositionIntermediate() {
		return positionIntermediate;
	}
	/**
	 * 设置：博士研究生
	 */
	public void setEduDoctoralCandidate(String eduDoctoralCandidate) {
		this.eduDoctoralCandidate = eduDoctoralCandidate;
	}
	/**
	 * 获取：博士研究生
	 */
	public String getEduDoctoralCandidate() {
		return eduDoctoralCandidate;
	}
	/**
	 * 设置：硕士研究生
	 */
	public void setEduPostgraduates(String eduPostgraduates) {
		this.eduPostgraduates = eduPostgraduates;
	}
	/**
	 * 获取：硕士研究生
	 */
	public String getEduPostgraduates() {
		return eduPostgraduates;
	}
	/**
	 * 设置：本科
	 */
	public void setEduUndergraduate(String eduUndergraduate) {
		this.eduUndergraduate = eduUndergraduate;
	}
	/**
	 * 获取：本科
	 */
	public String getEduUndergraduate() {
		return eduUndergraduate;
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
	 * 设置：
	 */
	public void setOptUid(Integer optUid) {
		this.optUid = optUid;
	}
	/**
	 * 获取：
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
	 * 设置：
	 */
	public void setAgeBeyond65CountNum(String ageBeyond65CountNum) {
		this.ageBeyond65CountNum = ageBeyond65CountNum;
	}
	/**
	 * 获取：
	 */
	public String getAgeBeyond65CountNum() {
		return ageBeyond65CountNum;
	}
	/**
	 * 设置：
	 */
	public void setAge50To65CountNum(String age50To65CountNum) {
		this.age50To65CountNum = age50To65CountNum;
	}
	/**
	 * 获取：
	 */
	public String getAge50To65CountNum() {
		return age50To65CountNum;
	}
	/**
	 * 设置：
	 */
	public void setAge35To50CountNum(String age35To50CountNum) {
		this.age35To50CountNum = age35To50CountNum;
	}
	/**
	 * 获取：
	 */
	public String getAge35To50CountNum() {
		return age35To50CountNum;
	}
	/**
	 * 设置：
	 */
	public void setAgeUnder36CountNum(String ageUnder36CountNum) {
		this.ageUnder36CountNum = ageUnder36CountNum;
	}
	/**
	 * 获取：
	 */
	public String getAgeUnder36CountNum() {
		return ageUnder36CountNum;
	}
	/**
	 * 设置：
	 */
	public void setPositionSeniorNum(String positionSeniorNum) {
		this.positionSeniorNum = positionSeniorNum;
	}
	/**
	 * 获取：
	 */
	public String getPositionSeniorNum() {
		return positionSeniorNum;
	}
	/**
	 * 设置：
	 */
	public void setPositionDeputySeniorNum(String positionDeputySeniorNum) {
		this.positionDeputySeniorNum = positionDeputySeniorNum;
	}
	/**
	 * 获取：
	 */
	public String getPositionDeputySeniorNum() {
		return positionDeputySeniorNum;
	}
	/**
	 * 设置：
	 */
	public void setPositionIntermediateNum(String positionIntermediateNum) {
		this.positionIntermediateNum = positionIntermediateNum;
	}
	/**
	 * 获取：
	 */
	public String getPositionIntermediateNum() {
		return positionIntermediateNum;
	}
	/**
	 * 设置：
	 */
	public void setEduDoctoralCandidateNum(String eduDoctoralCandidateNum) {
		this.eduDoctoralCandidateNum = eduDoctoralCandidateNum;
	}
	/**
	 * 获取：
	 */
	public String getEduDoctoralCandidateNum() {
		return eduDoctoralCandidateNum;
	}
	/**
	 * 设置：
	 */
	public void setEduPostgraduatesNum(String eduPostgraduatesNum) {
		this.eduPostgraduatesNum = eduPostgraduatesNum;
	}
	/**
	 * 获取：
	 */
	public String getEduPostgraduatesNum() {
		return eduPostgraduatesNum;
	}
	/**
	 * 设置：
	 */
	public void setEduUndergraduateNum(String eduUndergraduateNum) {
		this.eduUndergraduateNum = eduUndergraduateNum;
	}
	/**
	 * 获取：
	 */
	public String getEduUndergraduateNum() {
		return eduUndergraduateNum;
	}
}
