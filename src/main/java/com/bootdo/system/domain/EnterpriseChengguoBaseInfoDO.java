package com.bootdo.system.domain;

import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.utils.DateUtils;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.cpe.petroleum_engineering_award.domain.QCImageObj;
import com.bootdo.cpe.utils.CpeCommonUtils;
import com.bootdo.cpe.utils.EnumUtils;
import com.bootdo.system.config.ConstantCommonData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.bootdo.common.utils.JSONUtils.gson;
import static com.bootdo.common.utils.SpringContextHolder.getBean;


/**
 * 成果基本情况
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-06-23 05:48:20
 */
public class EnterpriseChengguoBaseInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String proType = "科技奖";
	//
	private Integer id;
	//项目id(成果id)
	private String proId;
	//成果名称
	private String chengguoName;
	//申报单位
	private String applyEnterprise;
	private String applyEnterpriseExt;

	private List<String> applyEnterpriseList;
	//主要完成单位
	private String masterCompleteEnterprise;
	private List<String> masterEnterpriseList;
	//主要完成人员
	private String mainCompleteUsers;

	//主要完成人员
	private String mainCompleteUsersNo;

	//成果所属专业
	private String major;
	//成果所属学科分类
	private String subjectType;
	//联系人
	private String concatUser;
	//职位
	private String concatWorkPosition;
	//联系电话
	private String concatTel;
	//手机
	private String concatPhone;
	//部门
	private String concatDepartment;
	//传真
	private String concatFax;
	//邮编
	private String concatCode;
	//地址
	private String concatAddr;
	//邮箱
	private String concatEmail;
	//任务来源
	private String taskSource;
	//任务来源
	private String taskSourceNo;
	//任务来源
	private List<QCImageObj> taskSourceImage = new ArrayList<>();

	//具体计划、基金的名称及编号
	private String planDetalNameCode;
	private String planDetalNameCodeNo;
	private List<QCImageObj> planDetalNameCodeImage = new ArrayList<>();
	//项目编号
	private String proResultCode;

	public String getTaskSourceNo() {
		return taskSourceNo;
	}

	public void setTaskSourceNo(String taskSourceNo) {
		this.taskSourceNo = taskSourceNo;
	}

	public List<QCImageObj> getTaskSourceImage() {
		return taskSourceImage;
	}

	public void setTaskSourceImage(List<QCImageObj> taskSourceImage) {
		this.taskSourceImage = taskSourceImage;
	}

	public String getPlanDetalNameCodeNo() {
		return planDetalNameCodeNo;
	}

	public void setPlanDetalNameCodeNo(String planDetalNameCodeNo) {
		this.planDetalNameCodeNo = planDetalNameCodeNo;
	}

	public List<QCImageObj> getPlanDetalNameCodeImage() {
		return planDetalNameCodeImage;
	}

	public void setPlanDetalNameCodeImage(List<QCImageObj> planDetalNameCodeImage) {
		this.planDetalNameCodeImage = planDetalNameCodeImage;
	}

	//项目起止时间开始:
	private String startTime;
	//项目起止时间：完成
	private String endTime;
	//成果评价
	private String comment;
	//评价结论
	private String commentReult;
	//（鉴定）部门
	private String reiewDepartment;
	//项目密级
	private String securityLevel;
	//保密期限
	private String securityTimeLen;
	//定密日期
	private String sureSecurityDate;
	//定密审查单位
	private String sureSecurityDepartment;
	//本成果曾获何部门何种奖励，奖励等级及奖金
	private String departmentAwardInfo;
	private String departmentAwardInfoNo;
	private List<QCImageObj> departmentAwardInfoImage = new ArrayList<>();

	//申报单位意见
	private String enterpriseOpinion;
	private String enterpriseOpinionNo;
	private List<QCImageObj> enterpriseOpinionImage = new ArrayList<>();

	public String getEnterpriseOpinionNo() {
		return enterpriseOpinionNo;
	}

	public void setEnterpriseOpinionNo(String enterpriseOpinionNo) {
		this.enterpriseOpinionNo = enterpriseOpinionNo;
	}

	public List<QCImageObj> getEnterpriseOpinionImage() {
		return enterpriseOpinionImage;
	}

	public void setEnterpriseOpinionImage(List<QCImageObj> enterpriseOpinionImage) {
		this.enterpriseOpinionImage = enterpriseOpinionImage;
	}

	//申报书内容
	private String applyBookContent;
	//创建日期
	private Date created;
	//申报的企业
	private String enterpriseName;
	//打分的日期
	private String scoreDate;
	//项目的流程状态
	private String proStat;
	private String proStatStr;
	//项目编号
	private int proCode;

	private String chengguoDes;

	public EnterpriseChengguoBaseInfoDO(){
		this.applyEnterpriseList = new ArrayList<>();
		this.applyEnterpriseList.add("申报单位一(盖章)");
		this.masterEnterpriseList = new ArrayList<>();
		this.masterEnterpriseList.add("申报单位");
	}

	public String getChengguoDes() {
		return chengguoDes;
	}

	public void setChengguoDes(String chengguoDes) {
		this.chengguoDes = chengguoDes;
	}

	public String getProType() {
		return proType;
	}

	public void setApplyEnterpriseExt(String applyEnterpriseExt) {
		this.applyEnterpriseExt = applyEnterpriseExt;
	}

	public String getApplyEnterpriseExt() {

		if(StringUtils.isNotBlank(applyEnterpriseExt)) {
			if(applyEnterpriseExt.startsWith("[") && applyEnterpriseExt.endsWith("]")) {
                List<String> list = gson.fromJson(applyEnterpriseExt, List.class);
                if(list.size() == 0) {
                	return "";
				}
                StringBuilder eStr = new StringBuilder();
                for(String s:list) {
                	eStr.append(s).append(",");
				}
                return eStr.substring(0, eStr.length() - 1);
			}else {
				return "";
			}
		}else {

			return applyEnterpriseExt ;
		}
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
	/**
	 * 设置：项目id(成果id)
	 */
	public void setProId(String proId) {
		this.proId = proId;
	}
	/**
	 * 获取：项目id(成果id)
	 */
	public String getProId() {
		return proId;
	}
	/**
	 * 设置：成果名称
	 */
	public void setChengguoName(String chengguoName) {
		this.chengguoName = chengguoName;
	}
	/**
	 * 获取：成果名称
	 */
	public String getChengguoName() {
		return chengguoName;
	}
	/**
	 * 设置：申报单位
	 */
	public void setApplyEnterprise(String applyEnterprise) {
		this.applyEnterprise = applyEnterprise;

		setApplyEnterpriseExt(applyEnterprise);

		if(StringUtils.isNotBlank(applyEnterprise)) {
			if(applyEnterprise.startsWith("[") && applyEnterprise.endsWith("]")) {
				this.applyEnterpriseList = gson.fromJson(applyEnterprise, List.class);
			}else {
				this.applyEnterpriseList = new ArrayList<>();
				this.applyEnterpriseList.add(applyEnterprise);
			}
		}
	}

	public List<String> getApplyEnterpriseList() {
		return applyEnterpriseList;
	}

	public void setApplyEnterpriseList(List<String> applyEnterpriseList) {
		this.applyEnterpriseList = applyEnterpriseList;
		if(applyEnterpriseList != null) {
			this.applyEnterprise = gson.toJson(applyEnterpriseList);
		}
	}

	/**
	 * 获取：申报单位
	 */
	public String getApplyEnterprise() {
		return applyEnterprise;
	}
	/**
	 * 设置：主要完成单位
	 */
	public void setMasterCompleteEnterprise(String masterCompleteEnterprise) {
		this.masterCompleteEnterprise = masterCompleteEnterprise;
		if(StringUtils.isNotBlank(masterCompleteEnterprise)) {
			this.masterEnterpriseList = gson.fromJson(masterCompleteEnterprise, List.class);
		}
	}
	/**
	 * 获取：主要完成单位
	 */
	public String getMasterCompleteEnterprise() {
		return masterCompleteEnterprise;
	}

	public List<String> getMasterEnterpriseList() {
		return masterEnterpriseList;
	}

	public void setMasterEnterpriseList(List<String> masterEnterpriseList) {
		this.masterEnterpriseList = masterEnterpriseList;
		if(masterEnterpriseList != null) {
			this.masterCompleteEnterprise = gson.toJson(masterEnterpriseList);
		}
	}

	public void setProType(String proType) {
		this.proType = proType;
	}

	/**
	 * 设置：主要完成人员
	 */
	public void setMainCompleteUsers(String mainCompleteUsers) {
		this.mainCompleteUsers = mainCompleteUsers;

		this.mainCompleteUsersNo = this.mainCompleteUsers.replaceAll("\\<.*?>","");
		BootdoConfig bootdoConfig = getBean("bootdoConfig");
		//this.mainCompleteUsersImge = CpeCommonUtils.getImages(this.mainCompleteUsers,bootdoConfig.getImgUrlPre());

	}

	public String getMainCompleteUsersNo() {
		return mainCompleteUsersNo;
	}

	public void setMainCompleteUsersNo(String mainCompleteUsersNo) {
		this.mainCompleteUsersNo = mainCompleteUsersNo;
	}

	public List<QCImageObj> getMainCompleteUsersImge() {
		return mainCompleteUsersImge;
	}

	public void setMainCompleteUsersImge(List<QCImageObj> mainCompleteUsersImge) {
		this.mainCompleteUsersImge = mainCompleteUsersImge;
	}

	/**
	 * 获取：主要完成人员
	 */
	public String getMainCompleteUsers() {
		return mainCompleteUsers;
	}

	private List<QCImageObj> mainCompleteUsersImge = new ArrayList<>();




	/**
	 * 设置：成果所属专业
	 */
	public void setMajor(String major) {
		this.major = major;
	}
	/**
	 * 获取：成果所属专业
	 */
	public String getMajor() {
		return major;
	}
	/**
	 * 设置：成果所属学科分类
	 */
	public void setSubjectType(String subjectType) {
		this.subjectType = subjectType;
	}
	/**
	 * 获取：成果所属学科分类
	 */
	public String getSubjectType() {
		return subjectType;
	}
	/**
	 * 设置：联系人
	 */
	public void setConcatUser(String concatUser) {
		this.concatUser = concatUser;
	}
	/**
	 * 获取：联系人
	 */
	public String getConcatUser() {
		return concatUser;
	}
	/**
	 * 设置：职位
	 */
	public void setConcatWorkPosition(String concatWorkPosition) {
		this.concatWorkPosition = concatWorkPosition;
	}
	/**
	 * 获取：职位
	 */
	public String getConcatWorkPosition() {
		return concatWorkPosition;
	}
	/**
	 * 设置：联系电话
	 */
	public void setConcatTel(String concatTel) {
		this.concatTel = concatTel;
	}
	/**
	 * 获取：联系电话
	 */
	public String getConcatTel() {
		return concatTel;
	}
	/**
	 * 设置：手机
	 */
	public void setConcatPhone(String concatPhone) {
		this.concatPhone = concatPhone;
	}
	/**
	 * 获取：手机
	 */
	public String getConcatPhone() {
		return concatPhone;
	}
	/**
	 * 设置：部门
	 */
	public void setConcatDepartment(String concatDepartment) {
		this.concatDepartment = concatDepartment;
	}
	/**
	 * 获取：部门
	 */
	public String getConcatDepartment() {
		return concatDepartment;
	}
	/**
	 * 设置：传真
	 */
	public void setConcatFax(String concatFax) {
		this.concatFax = concatFax;
	}
	/**
	 * 获取：传真
	 */
	public String getConcatFax() {
		return concatFax;
	}
	/**
	 * 设置：邮编
	 */
	public void setConcatCode(String concatCode) {
		this.concatCode = concatCode;
	}
	/**
	 * 获取：邮编
	 */
	public String getConcatCode() {
		return concatCode;
	}
	/**
	 * 设置：地址
	 */
	public void setConcatAddr(String concatAddr) {
		this.concatAddr = concatAddr;
	}
	/**
	 * 获取：地址
	 */
	public String getConcatAddr() {
		return concatAddr;
	}
	/**
	 * 设置：邮箱
	 */
	public void setConcatEmail(String concatEmail) {
		this.concatEmail = concatEmail;
	}
	/**
	 * 获取：邮箱
	 */
	public String getConcatEmail() {
		return concatEmail;
	}
	/**
	 * 设置：任务来源
	 */
	public void setTaskSource(String taskSource) {
		this.taskSource = taskSource;
		this.taskSourceNo = this.taskSource.replaceAll("\\<.*?>","");
		BootdoConfig bootdoConfig = getBean("bootdoConfig");
		//this.taskSourceImage = CpeCommonUtils.getImages(this.taskSource,bootdoConfig.getImgUrlPre());
	}
	/**
	 * 获取：任务来源
	 */
	public String getTaskSource() {
		return taskSource;
	}
	/**
	 * 设置：具体计划、基金的名称及编号
	 */
	public void setPlanDetalNameCode(String planDetalNameCode) {
		this.planDetalNameCode = planDetalNameCode;
		this.planDetalNameCodeNo = this.planDetalNameCode.replaceAll("\\<.*?>","");
		BootdoConfig bootdoConfig = getBean("bootdoConfig");
		//this.planDetalNameCodeImage = CpeCommonUtils.getImages(this.planDetalNameCode,bootdoConfig.getImgUrlPre());


	}
	/**
	 * 获取：具体计划、基金的名称及编号
	 */
	public String getPlanDetalNameCode() {
		return planDetalNameCode;
	}
	/**
	 * 设置：项目起止时间开始:
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	/**
	 * 获取：项目起止时间开始:
	 */
	public String getStartTime() {
		return startTime;
	}
	/**
	 * 设置：项目起止时间：完成
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	/**
	 * 获取：项目起止时间：完成
	 */
	public String getEndTime() {
		return endTime;
	}
	/**
	 * 设置：成果评价
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	/**
	 * 获取：成果评价
	 */
	public String getComment() {
		return comment;
	}
	/**
	 * 设置：评价结论
	 */
	public void setCommentReult(String commentReult) {
		this.commentReult = commentReult;
	}
	/**
	 * 获取：评价结论
	 */
	public String getCommentReult() {
		return commentReult;
	}
	/**
	 * 设置：（鉴定）部门
	 */
	public void setReiewDepartment(String reiewDepartment) {
		this.reiewDepartment = reiewDepartment;
	}
	/**
	 * 获取：（鉴定）部门
	 */
	public String getReiewDepartment() {
		return reiewDepartment;
	}
	/**
	 * 设置：项目密级
	 */
	public void setSecurityLevel(String securityLevel) {
		this.securityLevel = securityLevel;
	}
	/**
	 * 获取：项目密级
	 */
	public String getSecurityLevel() {
		return securityLevel;
	}
	/**
	 * 设置：保密期限
	 */
	public void setSecurityTimeLen(String securityTimeLen) {
		this.securityTimeLen = securityTimeLen;
	}
	/**
	 * 获取：保密期限
	 */
	public String getSecurityTimeLen() {
		return securityTimeLen;
	}
	/**
	 * 设置：定密日期
	 */
	public void setSureSecurityDate(String sureSecurityDate) {
		this.sureSecurityDate = sureSecurityDate;
	}
	/**
	 * 获取：定密日期
	 */
	public String getSureSecurityDate() {
		return sureSecurityDate;
	}
	/**
	 * 设置：定密审查单位
	 */
	public void setSureSecurityDepartment(String sureSecurityDepartment) {
		this.sureSecurityDepartment = sureSecurityDepartment;
	}
	/**
	 * 获取：定密审查单位
	 */
	public String getSureSecurityDepartment() {
		return sureSecurityDepartment;
	}
	/**
	 * 设置：本成果曾获何部门何种奖励，奖励等级及奖金
	 */
	public void setDepartmentAwardInfo(String departmentAwardInfo) {
		this.departmentAwardInfo = departmentAwardInfo;
		this.departmentAwardInfoNo = this.departmentAwardInfo.replaceAll("\\<.*?>","");
		BootdoConfig bootdoConfig = getBean("bootdoConfig");
		//this.departmentAwardInfoImage = CpeCommonUtils.getImages(this.departmentAwardInfo,bootdoConfig.getImgUrlPre());
	}

	public String getDepartmentAwardInfoNo() {
		return departmentAwardInfoNo;
	}

	public void setDepartmentAwardInfoNo(String departmentAwardInfoNo) {
		this.departmentAwardInfoNo = departmentAwardInfoNo;
	}

	public List<QCImageObj> getDepartmentAwardInfoImage() {
		return departmentAwardInfoImage;
	}

	public void setDepartmentAwardInfoImage(List<QCImageObj> departmentAwardInfoImage) {
		this.departmentAwardInfoImage = departmentAwardInfoImage;
	}

	/**
	 * 获取：本成果曾获何部门何种奖励，奖励等级及奖金
	 */
	public String getDepartmentAwardInfo() {
		return departmentAwardInfo;
	}
	/**
	 * 设置：申报单位意见
	 */
	public void setEnterpriseOpinion(String enterpriseOpinion) {
		this.enterpriseOpinion = enterpriseOpinion;
		this.enterpriseOpinionNo = this.enterpriseOpinion.replaceAll("\\<.*?>","");
		BootdoConfig bootdoConfig = getBean("bootdoConfig");
		//this.enterpriseOpinionImage = CpeCommonUtils.getImages(this.enterpriseOpinion,bootdoConfig.getImgUrlPre());
	}

	/**
	 * 获取：申报单位意见
	 */
	public String getEnterpriseOpinion() {
		return enterpriseOpinion;
	}
	/**
	 * 设置：申报书内容
	 */
	public void setApplyBookContent(String applyBookContent) {
		this.applyBookContent = applyBookContent;
	}
	/**
	 * 获取：申报书内容
	 */
	public String getApplyBookContent() {
		return applyBookContent;
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
	public String getCreated() {

		if (created != null){
			return DateUtils.format(created,DateUtils.DATE_PATTERN);
		}
		return "";
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getScoreDate() {
		return scoreDate;
	}

	public void setScoreDate(String scoreDate) {
		scoreDate = scoreDate.replaceAll("\\.0","");
		this.scoreDate = scoreDate;
	}

	public String getProStat() {
		return proStat;
	}

	public void setProStat(String proStat) {
		this.proStat = proStat;
		this.proStatStr = EnumUtils.getProjectStatShowStrByStat(proStat);
	}

	public String getProStatStr() {
		return proStatStr;
	}

	public void setProStatStr(String proStatStr) {
		this.proStatStr = proStatStr;
	}

	public int getProCode() {
		return proCode;
	}

	public void setProCode(int proCode) {
		this.proCode = proCode;
	}

	public String getProResultCode() {
		return proResultCode;
	}

	public void setProResultCode(String proResultCode) {
		this.proResultCode = proResultCode;
	}
}
