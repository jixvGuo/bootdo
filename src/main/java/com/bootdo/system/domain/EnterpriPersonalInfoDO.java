package com.bootdo.system.domain;

import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.utils.DateUtils;
import com.bootdo.cpe.petroleum_engineering_award.domain.QCImageObj;
import com.bootdo.cpe.petroleum_engineering_award.domain.ScienceBaseProjectInfoDO;
import com.bootdo.cpe.utils.CpeCommonUtils;
import com.bootdo.cpe.utils.EnumUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.bootdo.common.utils.SpringContextHolder.getBean;


/**
 * 企业报奖个人信息
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-06-30 04:48:14
 */
public class EnterpriPersonalInfoDO extends ScienceBaseProjectInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String proType = "个人";
	private String proStatStr;
	//
	private Integer id;
	/**
	 * 显示的编号
	 */
	private int showNum;
	//项目编号
	private String proResultCode;
	//申报人姓名
	private String userName;
	//性别
	private String gender;
	//出生年月
	private String birthday;
	//照片
	private String photo;
	//民族
	private String nation;
	//从事专业
	private String workMajor;
	//技术职称
	private String technicalTitle;
	//身份证
	private String userIden;
	//技术职务
	private String technicalPosition;
	//最高学历
	private String highestEducation;
	//毕业学校
	private String graduateSchool;
	//所学专业
	private String major;
	//毕业时间
	private String graduationTime;
	//是否享受政府津贴
	private String isEnjoyGovernmentSubsidies;
	//姓名
	private String contactsName;
	//手机
	private String contactsPhone;
	//通讯地址
	private String contactsAddress;
	// 电话 
	private String contactsTelphone;
	// 传真 
	private String contactsFax;
	//
	private String contactsPostcode;
	//个人综述
	private String personalSummary;
	private String personalSummaryNo;
	//任务来源
	private List<QCImageObj> personalSummaryImage = new ArrayList<>();

	public void setProType(String proType) {
		this.proType = proType;
	}

	public String getPersonalSummaryNo() {
		return personalSummaryNo;
	}

	public void setPersonalSummaryNo(String personalSummaryNo) {
		this.personalSummaryNo = personalSummaryNo;
	}

	public List<QCImageObj> getPersonalSummaryImage() {
		return personalSummaryImage;
	}

	public void setPersonalSummaryImage(List<QCImageObj> personalSummaryImage) {
		this.personalSummaryImage = personalSummaryImage;
	}

	//填报的账号用户id
	private Long optUid;
	//
	private Date created;

	/**
	 * 获取：创建日期
	 */
	public String getCreated() {

		if (created != null){
			return DateUtils.format(created,DateUtils.DATE_PATTERN);
		}
		return "";
	}

	//任务id
	private String taskId;
	//申请的id编号
	private String applyId;
	//单位意见
	private String companyOpinion;


	private String companyOpinionNo;
	//任务来源
	private List<QCImageObj> companyOpinionImage = new ArrayList<>();

	public String getCompanyOpinionNo() {
		return companyOpinionNo;
	}

	public void setCompanyOpinionNo(String companyOpinionNo) {
		this.companyOpinionNo = companyOpinionNo;
	}

	public List<QCImageObj> getCompanyOpinionImage() {
		return companyOpinionImage;
	}

	public void setCompanyOpinionImage(List<QCImageObj> companyOpinionImage) {
		this.companyOpinionImage = companyOpinionImage;
	}

	//项目id
	private int proId;
	//个人记录的id
	private int itemId;
	//申报的企业
	private String enterpriseName;
	//审核的状态
	private String personStat;
	private String personStatStr;
    //打分日期
	private String scoreDate;


	public String getProType() {
		return proType;
	}

	public String getProStatStr() {
		return proStatStr;
	}

	public void setProStatStr(String proStatStr) {
		this.proStatStr = proStatStr;
	}

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

	public int getShowNum() {
		return showNum;
	}

	public void setShowNum(int showNum) {
		this.showNum = showNum;
	}

	public String getProResultCode() {
		return proResultCode;
	}

	public void setProResultCode(String proResultCode) {
		this.proResultCode = proResultCode;
	}

	/**
	 * 设置：申报人姓名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 获取：申报人姓名
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * 设置：性别
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * 获取：性别
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * 设置：出生年月
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	/**
	 * 获取：出生年月
	 */
	public String getBirthday() {
		return birthday;
	}
	/**
	 * 设置：照片
	 */
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	/**
	 * 获取：照片
	 */
	public String getPhoto() {
		return photo;
	}
	/**
	 * 设置：民族
	 */
	public void setNation(String nation) {
		this.nation = nation;
	}
	/**
	 * 获取：民族
	 */
	public String getNation() {
		return nation;
	}
	/**
	 * 设置：从事专业
	 */
	public void setWorkMajor(String workMajor) {
		this.workMajor = workMajor;
	}
	/**
	 * 获取：从事专业
	 */
	public String getWorkMajor() {
		return workMajor;
	}
	/**
	 * 设置：技术职称
	 */
	public void setTechnicalTitle(String technicalTitle) {
		this.technicalTitle = technicalTitle;
	}
	/**
	 * 获取：技术职称
	 */
	public String getTechnicalTitle() {
		return technicalTitle;
	}
	/**
	 * 设置：身份证
	 */
	public void setUserIden(String userIden) {
		this.userIden = userIden;
	}
	/**
	 * 获取：身份证
	 */
	public String getUserIden() {
		return userIden;
	}
	/**
	 * 设置：技术职务
	 */
	public void setTechnicalPosition(String technicalPosition) {
		this.technicalPosition = technicalPosition;
	}
	/**
	 * 获取：技术职务
	 */
	public String getTechnicalPosition() {
		return technicalPosition;
	}
	/**
	 * 设置：最高学历
	 */
	public void setHighestEducation(String highestEducation) {
		this.highestEducation = highestEducation;
	}
	/**
	 * 获取：最高学历
	 */
	public String getHighestEducation() {
		return highestEducation;
	}
	/**
	 * 设置：毕业学校
	 */
	public void setGraduateSchool(String graduateSchool) {
		this.graduateSchool = graduateSchool;
	}
	/**
	 * 获取：毕业学校
	 */
	public String getGraduateSchool() {
		return graduateSchool;
	}
	/**
	 * 设置：所学专业
	 */
	public void setMajor(String major) {
		this.major = major;
	}
	/**
	 * 获取：所学专业
	 */
	public String getMajor() {
		return major;
	}
	/**
	 * 设置：毕业时间
	 */
	public void setGraduationTime(String graduationTime) {
		this.graduationTime = graduationTime;
	}
	/**
	 * 获取：毕业时间
	 */
	public String getGraduationTime() {
		return graduationTime;
	}
	/**
	 * 设置：是否享受政府津贴
	 */
	public void setIsEnjoyGovernmentSubsidies(String isEnjoyGovernmentSubsidies) {
		this.isEnjoyGovernmentSubsidies = isEnjoyGovernmentSubsidies;
	}
	/**
	 * 获取：是否享受政府津贴
	 */
	public String getIsEnjoyGovernmentSubsidies() {
		return isEnjoyGovernmentSubsidies;
	}
	/**
	 * 设置：姓名
	 */
	public void setContactsName(String contactsName) {
		this.contactsName = contactsName;
	}
	/**
	 * 获取：姓名
	 */
	public String getContactsName() {
		return contactsName;
	}
	/**
	 * 设置：手机
	 */
	public void setContactsPhone(String contactsPhone) {
		this.contactsPhone = contactsPhone;
	}
	/**
	 * 获取：手机
	 */
	public String getContactsPhone() {
		return contactsPhone;
	}
	/**
	 * 设置：通讯地址
	 */
	public void setContactsAddress(String contactsAddress) {
		this.contactsAddress = contactsAddress;
	}
	/**
	 * 获取：通讯地址
	 */
	public String getContactsAddress() {
		return contactsAddress;
	}
	/**
	 * 设置： 电话 
	 */
	public void setContactsTelphone(String contactsTelphone) {
		this.contactsTelphone = contactsTelphone;
	}
	/**
	 * 获取： 电话 
	 */
	public String getContactsTelphone() {
		return contactsTelphone;
	}
	/**
	 * 设置： 传真 
	 */
	public void setContactsFax(String contactsFax) {
		this.contactsFax = contactsFax;
	}
	/**
	 * 获取： 传真 
	 */
	public String getContactsFax() {
		return contactsFax;
	}
	/**
	 * 设置：
	 */
	public void setContactsPostcode(String contactsPostcode) {
		this.contactsPostcode = contactsPostcode;
	}
	/**
	 * 获取：
	 */
	public String getContactsPostcode() {
		return contactsPostcode;
	}
	/**
	 * 设置：个人综述
	 */
	public void setPersonalSummary(String personalSummary) {
		this.personalSummary = personalSummary;
		this.personalSummaryNo = this.personalSummary.replaceAll("\\<.*?>","");
		BootdoConfig bootdoConfig = getBean("bootdoConfig");
		//this.personalSummaryImage = CpeCommonUtils.getImages(this.personalSummary,bootdoConfig.getImgUrlPre());
	}
	/**
	 * 获取：个人综述
	 */
	public String getPersonalSummary() {
		return personalSummary;
	}
	/**
	 * 设置：填报的账号用户id
	 */
	public void setOptUid(Long optUid) {
		this.optUid = optUid;
	}
	/**
	 * 获取：填报的账号用户id
	 */
	public Long getOptUid() {
		return optUid;
	}
	/**
	 * 设置：
	 */
	public void setCreated(Date created) {
		this.created = created;
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

	public String getCompanyOpinion() {
		return companyOpinion;
	}

	public void setCompanyOpinion(String companyOpinion) {
		this.companyOpinion = companyOpinion;
		this.companyOpinionNo = this.companyOpinion.replaceAll("\\<.*?>","");
		BootdoConfig bootdoConfig = getBean("bootdoConfig");
		//this.companyOpinionImage = CpeCommonUtils.getImages(this.companyOpinion,bootdoConfig.getImgUrlPre());


	}

	public int getProId() {
		return proId;
	}

	public void setProId(int proId) {
		this.proId = proId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getPersonStat() {
		return personStat;
	}

	public void setPersonStat(String personStat) {
		this.personStat = personStat;
		this.personStatStr = EnumUtils.getProjectStatShowStrByStat(personStat);
	}

	public String getPersonStatStr() {
		return personStatStr;
	}

	public void setPersonStatStr(String personStatStr) {
		this.personStatStr = personStatStr;
	}

	public String getScoreDate() {
		return scoreDate;
	}

	public void setScoreDate(String scoreDate) {
        scoreDate = scoreDate.replaceAll("\\.0","");
		this.scoreDate = scoreDate;
	}
}
