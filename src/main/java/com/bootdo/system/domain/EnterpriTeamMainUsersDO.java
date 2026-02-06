package com.bootdo.system.domain;

import com.bootdo.common.config.BootdoConfig;
import com.bootdo.cpe.petroleum_engineering_award.domain.QCImageObj;
import com.bootdo.cpe.utils.CpeCommonUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.bootdo.common.utils.SpringContextHolder.getBean;


/**
 * 团队主要成员
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-07-05 08:21:09
 */
public class EnterpriTeamMainUsersDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id = 0;
	//姓名
	private String muUsername;
	//性别
	private String muGender;
	//序号
	private String muNum;
	//出生年月
	private String muBirthday;
	//出生地
	private String muBirthAddr;
	//民族
	private String muNation;
	//身份证号
	private String muUserIden;
	//是否归国人员
	private String muIsReturnee;
	//归国时间
	private String muReturneeTime;
	//技术职称
	private String muTechnicalTitle;
	//最高学历
	private String muHighestEducation;
	//最高学位
	private String muHighestDegree;
	//毕业学校
	private String muGraduateSchool;
	//毕业时间
	private String muGraduateTime;
	//所学专业
	private String muMajor;
	//电子邮箱
	private String muEmail;
	//办公电话
	private String muWorkPhone;
	//移动电话
	private String muMobilePhone;
	//通讯地址
	private String muConcatAddress;
	//邮政编码
	private String muPostcode;
	//工作单位
	private String muWorkCompany;
	//行政职务
	private String muWorkPosition;
	//二级单位
	private String muWorkSecondCompany;
	//党派
	private String muParties;
	//参加本团队的起止时间 
	private String muParticipatingTime;
	//主要学习经历 
	private String muStudyHistory;
	private String muStudyHistoryNo;
	private List<QCImageObj> muStudyHistoryImage = new ArrayList<>();


	//科研工作经历
	private String muScienceWorkHistory;
	private String muScienceWorkHistoryNo;
	private List<QCImageObj> muScienceWorkHistoryImage = new ArrayList<>();

	//
	private String muMajorAcademicPartTime;
	//代表性成果
	private String muRepresentativeAchievements;
	//代表性论文和著作获奖励情况 
	private String muAwardsRepresentativePapers;
	//对团队发展的贡献
	private String muContribution;
	//创建日期
	private Date created;
	//操作用户id
	private Integer optUid;
	//发布的任务id
	private String taskId;
	//项目id
	private Integer proId;
	//研究领域
	private String researchField;

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
	 * 设置：姓名
	 */
	public void setMuUsername(String muUsername) {
		this.muUsername = muUsername;
	}
	/**
	 * 获取：姓名
	 */
	public String getMuUsername() {
		return muUsername;
	}
	/**
	 * 设置：性别
	 */
	public void setMuGender(String muGender) {
		this.muGender = muGender;
	}
	/**
	 * 获取：性别
	 */
	public String getMuGender() {
		return muGender;
	}
	/**
	 * 设置：序号
	 */
	public void setMuNum(String muNum) {
		this.muNum = muNum;
	}
	/**
	 * 获取：序号
	 */
	public String getMuNum() {
		return muNum;
	}
	/**
	 * 设置：出生年月
	 */
	public void setMuBirthday(String muBirthday) {
		this.muBirthday = muBirthday;
	}
	/**
	 * 获取：出生年月
	 */
	public String getMuBirthday() {
		return muBirthday;
	}
	/**
	 * 设置：出生地
	 */
	public void setMuBirthAddr(String muBirthAddr) {
		this.muBirthAddr = muBirthAddr;
	}
	/**
	 * 获取：出生地
	 */
	public String getMuBirthAddr() {
		return muBirthAddr;
	}
	/**
	 * 设置：民族
	 */
	public void setMuNation(String muNation) {
		this.muNation = muNation;
	}
	/**
	 * 获取：民族
	 */
	public String getMuNation() {
		return muNation;
	}
	/**
	 * 设置：身份证号
	 */
	public void setMuUserIden(String muUserIden) {
		this.muUserIden = muUserIden;
	}
	/**
	 * 获取：身份证号
	 */
	public String getMuUserIden() {
		return muUserIden;
	}
	/**
	 * 设置：是否归国人员
	 */
	public void setMuIsReturnee(String muIsReturnee) {
		this.muIsReturnee = muIsReturnee;
	}
	/**
	 * 获取：是否归国人员
	 */
	public String getMuIsReturnee() {
		return muIsReturnee;
	}
	/**
	 * 设置：归国时间
	 */
	public void setMuReturneeTime(String muReturneeTime) {
		this.muReturneeTime = muReturneeTime;
	}
	/**
	 * 获取：归国时间
	 */
	public String getMuReturneeTime() {
		return muReturneeTime;
	}
	/**
	 * 设置：技术职称
	 */
	public void setMuTechnicalTitle(String muTechnicalTitle) {
		this.muTechnicalTitle = muTechnicalTitle;
	}
	/**
	 * 获取：技术职称
	 */
	public String getMuTechnicalTitle() {
		return muTechnicalTitle;
	}
	/**
	 * 设置：最高学历
	 */
	public void setMuHighestEducation(String muHighestEducation) {
		this.muHighestEducation = muHighestEducation;
	}
	/**
	 * 获取：最高学历
	 */
	public String getMuHighestEducation() {
		return muHighestEducation;
	}
	/**
	 * 设置：最高学位
	 */
	public void setMuHighestDegree(String muHighestDegree) {
		this.muHighestDegree = muHighestDegree;
	}
	/**
	 * 获取：最高学位
	 */
	public String getMuHighestDegree() {
		return muHighestDegree;
	}
	/**
	 * 设置：毕业学校
	 */
	public void setMuGraduateSchool(String muGraduateSchool) {
		this.muGraduateSchool = muGraduateSchool;
	}
	/**
	 * 获取：毕业学校
	 */
	public String getMuGraduateSchool() {
		return muGraduateSchool;
	}
	/**
	 * 设置：毕业时间
	 */
	public void setMuGraduateTime(String muGraduateTime) {
		this.muGraduateTime = muGraduateTime;
	}
	/**
	 * 获取：毕业时间
	 */
	public String getMuGraduateTime() {
		return muGraduateTime;
	}
	/**
	 * 设置：所学专业
	 */
	public void setMuMajor(String muMajor) {
		this.muMajor = muMajor;
	}
	/**
	 * 获取：所学专业
	 */
	public String getMuMajor() {
		return muMajor;
	}
	/**
	 * 设置：电子邮箱
	 */
	public void setMuEmail(String muEmail) {
		this.muEmail = muEmail;
	}
	/**
	 * 获取：电子邮箱
	 */
	public String getMuEmail() {
		return muEmail;
	}
	/**
	 * 设置：办公电话
	 */
	public void setMuWorkPhone(String muWorkPhone) {
		this.muWorkPhone = muWorkPhone;
	}
	/**
	 * 获取：办公电话
	 */
	public String getMuWorkPhone() {
		return muWorkPhone;
	}
	/**
	 * 设置：移动电话
	 */
	public void setMuMobilePhone(String muMobilePhone) {
		this.muMobilePhone = muMobilePhone;
	}
	/**
	 * 获取：移动电话
	 */
	public String getMuMobilePhone() {
		return muMobilePhone;
	}
	/**
	 * 设置：通讯地址
	 */
	public void setMuConcatAddress(String muConcatAddress) {
		this.muConcatAddress = muConcatAddress;
	}
	/**
	 * 获取：通讯地址
	 */
	public String getMuConcatAddress() {
		return muConcatAddress;
	}
	/**
	 * 设置：邮政编码
	 */
	public void setMuPostcode(String muPostcode) {
		this.muPostcode = muPostcode;
	}
	/**
	 * 获取：邮政编码
	 */
	public String getMuPostcode() {
		return muPostcode;
	}
	/**
	 * 设置：工作单位
	 */
	public void setMuWorkCompany(String muWorkCompany) {
		this.muWorkCompany = muWorkCompany;
	}
	/**
	 * 获取：工作单位
	 */
	public String getMuWorkCompany() {
		return muWorkCompany;
	}
	/**
	 * 设置：行政职务
	 */
	public void setMuWorkPosition(String muWorkPosition) {
		this.muWorkPosition = muWorkPosition;
	}
	/**
	 * 获取：行政职务
	 */
	public String getMuWorkPosition() {
		return muWorkPosition;
	}
	/**
	 * 设置：二级单位
	 */
	public void setMuWorkSecondCompany(String muWorkSecondCompany) {
		this.muWorkSecondCompany = muWorkSecondCompany;
	}
	/**
	 * 获取：二级单位
	 */
	public String getMuWorkSecondCompany() {
		return muWorkSecondCompany;
	}
	/**
	 * 设置：党派
	 */
	public void setMuParties(String muParties) {
		this.muParties = muParties;
	}
	/**
	 * 获取：党派
	 */
	public String getMuParties() {
		return muParties;
	}
	/**
	 * 设置：参加本团队的起止时间 
	 */
	public void setMuParticipatingTime(String muParticipatingTime) {
		this.muParticipatingTime = muParticipatingTime;
	}
	/**
	 * 获取：参加本团队的起止时间 
	 */
	public String getMuParticipatingTime() {
		return muParticipatingTime;
	}
	/**
	 * 设置：主要学习经历 
	 */
	public void setMuStudyHistory(String muStudyHistory) {
		this.muStudyHistory = muStudyHistory;
		this.muStudyHistoryNo = this.muStudyHistory.replaceAll("\\<.*?>","");
		BootdoConfig bootdoConfig = getBean("bootdoConfig");
		//this.muStudyHistoryImage = CpeCommonUtils.getImages(this.muStudyHistory,bootdoConfig.getImgUrlPre());
	}


	/**
	 * 获取：主要学习经历 
	 */
	public String getMuStudyHistory() {
		return muStudyHistory;
	}
	/**
	 * 设置：科研工作经历
	 */
	public void setMuScienceWorkHistory(String muScienceWorkHistory) {
		this.muScienceWorkHistory = muScienceWorkHistory;

		this.muScienceWorkHistoryNo = this.muScienceWorkHistory.replaceAll("\\<.*?>","");
		BootdoConfig bootdoConfig = getBean("bootdoConfig");
		//this.muScienceWorkHistoryImage = CpeCommonUtils.getImages(this.muScienceWorkHistory,bootdoConfig.getImgUrlPre());



	}
	/**
	 * 获取：科研工作经历
	 */
	public String getMuScienceWorkHistory() {
		return muScienceWorkHistory;
	}
	/**
	 * 设置：
	 */
	public void setMuMajorAcademicPartTime(String muMajorAcademicPartTime) {
		this.muMajorAcademicPartTime = muMajorAcademicPartTime;
	}
	/**
	 * 获取：
	 */
	public String getMuMajorAcademicPartTime() {
		return muMajorAcademicPartTime;
	}
	/**
	 * 设置：代表性成果
	 */
	public void setMuRepresentativeAchievements(String muRepresentativeAchievements) {
		this.muRepresentativeAchievements = muRepresentativeAchievements;
	}
	/**
	 * 获取：代表性成果
	 */
	public String getMuRepresentativeAchievements() {
		return muRepresentativeAchievements;
	}
	/**
	 * 设置：代表性论文和著作获奖励情况 
	 */
	public void setMuAwardsRepresentativePapers(String muAwardsRepresentativePapers) {
		this.muAwardsRepresentativePapers = muAwardsRepresentativePapers;
	}
	/**
	 * 获取：代表性论文和著作获奖励情况 
	 */
	public String getMuAwardsRepresentativePapers() {
		return muAwardsRepresentativePapers;
	}
	/**
	 * 设置：对团队发展的贡献
	 */
	public void setMuContribution(String muContribution) {
		this.muContribution = muContribution;
	}
	/**
	 * 获取：对团队发展的贡献
	 */
	public String getMuContribution() {
		return muContribution;
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
	 * 设置：发布的任务id
	 */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	/**
	 * 获取：发布的任务id
	 */
	public String getTaskId() {
		return taskId;
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

	public String getResearchField() {
		return researchField;
	}

	public void setResearchField(String researchField) {
		this.researchField = researchField;
	}
}
