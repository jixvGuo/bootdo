package com.bootdo.system.domain;

import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.cpe.petroleum_engineering_award.domain.QCImageObj;
import com.bootdo.cpe.utils.CpeCommonUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.bootdo.common.utils.SpringContextHolder.getBean;


/**
 * 企业成果申报其他资料信息
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-06-27 13:37:22
 */
public class EnterpriseChengguoOtherInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private String proId;
	//主要科技创新
	private String mainTechnologicalInnovation;

	private String mainTechnologicalInnovationNo;

	private List<QCImageObj> mainTechnologicalInnovationImage = new ArrayList<>();



	//验收类文件-文件填写
	private String checkFileInfo;
	//验收类文件-验收报告
	private String checkReport;
	//验收类文件-验收意见
	private String checkOpinion;
	//验收类文件-各种验收结论
	private String checkConclusion;
	//验收类文件-其他证明材料
	private String checkEvidence;
	//第三方资料
	private String thirdData;
	//特殊成果资料
	private String specialData;
	//知识产权类别
	private String intellectualPropertyType;
	//知识产权名称
	private String intellectualPropertyName;
	//知识产权-国家(地区)
	private String intellectualPropertyCountry;
	//知识产权-授权号
	private String intellectualPropertyAuthNum;
	//知识产权-授权日期
	private String intellectualPropertyAuthDate;
	//知识产权-证书编号
	private String intellectualPropertyCertificateNum;
	//知识产权-权利人
	private String intellectualPropertyRightUser;
	//知识产权-发明人
	private String intellectualPropertyInventor;
	//知识产权-专利有效状态
	private String intellectualPropertyEffectiveStat;
	//成果标准
	private String chengguoStandard;
	//成果简介
	private String chengguoStandardNo;

	private List<QCImageObj> chengguoStandardImage = new ArrayList<>();


	//第三方评价意见及证书
	private String thirdOpinion;
	private String thirdOpinionNo;

	private List<QCImageObj> thirdOpinionImage = new ArrayList<>();


	public String getMainTechnologicalInnovationNo() {
		return mainTechnologicalInnovationNo;
	}

	public void setMainTechnologicalInnovationNo(String mainTechnologicalInnovationNo) {
		this.mainTechnologicalInnovationNo = mainTechnologicalInnovationNo;
	}

	public List<QCImageObj> getMainTechnologicalInnovationImage() {
		return mainTechnologicalInnovationImage;
	}

	public void setMainTechnologicalInnovationImage(List<QCImageObj> mainTechnologicalInnovationImage) {
		this.mainTechnologicalInnovationImage = mainTechnologicalInnovationImage;
	}

	public String getChengguoStandardNo() {
		return chengguoStandardNo;
	}

	public void setChengguoStandardNo(String chengguoStandardNo) {
		this.chengguoStandardNo = chengguoStandardNo;
	}

	public List<QCImageObj> getChengguoStandardImage() {
		return chengguoStandardImage;
	}

	public void setChengguoStandardImage(List<QCImageObj> chengguoStandardImage) {
		this.chengguoStandardImage = chengguoStandardImage;
	}

	public String getThirdOpinionNo() {
		return thirdOpinionNo;
	}

	public void setThirdOpinionNo(String thirdOpinionNo) {
		this.thirdOpinionNo = thirdOpinionNo;
	}

	public List<QCImageObj> getThirdOpinionImage() {
		return thirdOpinionImage;
	}

	public void setThirdOpinionImage(List<QCImageObj> thirdOpinionImage) {
		this.thirdOpinionImage = thirdOpinionImage;
	}

	public String getChengguoDesNo() {
		return chengguoDesNo;
	}

	public void setChengguoDesNo(String chengguoDesNo) {
		this.chengguoDesNo = chengguoDesNo;
	}

	public List<QCImageObj> getChengguoDesImage() {
		return chengguoDesImage;
	}

	public void setChengguoDesImage(List<QCImageObj> chengguoDesImage) {
		this.chengguoDesImage = chengguoDesImage;
	}

	//主要完成单位及主要完成人情况-单位名称
	private String mainEnterpriseCompanyName;
	//主要完成单位及主要完成人情况-主要完成人姓名
	private String mainEnterpriseCompleter;
	//主要完成单位及主要完成人情况-性别
	private String mainEnterpriseCompleterGender;
	//主要完成单位及主要完成人情况-排名
	private String mainEnterpriseCompleterSort;
	private int mainEnterpriseCompleterSortInt;
	//主要完成单位及主要完成人情况-出生年月
	private String mainEnterpriseCompleterBirth;
	//主要完成单位及主要完成人情况-身份证号
	private String mainEnterpriseCompleterId;
	//主要完成单位及主要完成人情况-民族
	private String mainEnterpriseCompleterNation;
	//主要完成单位及主要完成人情况-国籍
	private String mainEnterpriseCompleterCountry;
	//主要完成单位及主要完成人情况-学历
	private String mainEnterpriseCompleterEducation;
	//主要完成单位及主要完成人情况-行政职务
	private String mainEnterpriseCompleterAdministrativePost;
	//技术职称
	private String mainEnterpriseCompleterTechnicalTitle;
	//主要完成单位及主要完成人情况-从事的专业
	private String mainEnterpriseCompleterMajorWork;
	//主要完成单位及主要完成人情况-所学的专业
	private String mainEnterpriseCompleterMajorStudy;
	//主要完成单位及主要完成人情况-办公电话
	private String mainEnterpriseCompleterWorkTelphone;
	//主要完成单位及主要完成人情况-手机
	private String mainEnterpriseCompleterWorkMobile;
	//主要完成单位及主要完成人情况-电子邮箱
	private String mainEnterpriseCompleterWorkEmail;
	//通讯地址
	private String mainEnterpriseCompleterWorkAddress;
	//邮编
	private String mainEnterpriseCompleterWorkAddrCode;
	//主要完成单位及主要完成人情况-参加本项目的起止时间
	private String mainEnterpriseCompleterPartakeTime;
	//主要完成单位及主要完成人情况-主要完成人对本项目的学术贡献 
	private String mainEnterpriseCompleterAcademicContribution;
	//主要完成单位及主要完成人情况-主要完成单位对本项目科技创新和推广应用情况的贡献 
	private String mainEnterpriseContribution;
	//已获奖项
	private String mainEnterpriseCompleterAwards;
	//成果基本情况-申报书
	private String chengguoBaseDeclaration;
	//创建日期
	private Date created;
    //成果简介
	private String chengguoDes;

	//成果简介
	private String chengguoDesNo;

	private List<QCImageObj> chengguoDesImage = new ArrayList<>();



	/**
	 * 主要完成单位及主要完成人情况
	 * 由于表设计问题，目前先使用本对象进行存储
	 */
	private List<EnterpriseChengguoOtherInfoDO> mainCompleteInfoList;

	public List<EnterpriseChengguoOtherInfoDO> getMainCompleteInfoList() {
		return mainCompleteInfoList;
	}

	public void setMainCompleteInfoList(List<EnterpriseChengguoOtherInfoDO> mainCompleteInfoList) {
		this.mainCompleteInfoList = mainCompleteInfoList;
	}

	public String getChengguoDes() {
		return chengguoDes;
	}

	public void setChengguoDes(String chengguoDes) {
		this.chengguoDes = chengguoDes;
		this.chengguoDesNo = this.chengguoDes.replaceAll("\\<.*?>","");
		BootdoConfig bootdoConfig = getBean("bootdoConfig");
		//this.chengguoDesImage = CpeCommonUtils.getImages(this.chengguoDes,bootdoConfig.getImgUrlPre());

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
	 * 设置：
	 */
	public void setProId(String proId) {
		this.proId = proId;
	}
	/**
	 * 获取：
	 */
	public String getProId() {
		return proId;
	}
	/**
	 * 设置：主要科技创新
	 */
	public void setMainTechnologicalInnovation(String mainTechnologicalInnovation) {
		this.mainTechnologicalInnovation = mainTechnologicalInnovation;
		this.mainTechnologicalInnovationNo = this.mainTechnologicalInnovation.replaceAll("\\<.*?>","");
		BootdoConfig bootdoConfig = getBean("bootdoConfig");
		//this.mainTechnologicalInnovationImage = CpeCommonUtils.getImages(this.mainTechnologicalInnovation,bootdoConfig.getImgUrlPre());

	}
	/**
	 * 获取：主要科技创新
	 */
	public String getMainTechnologicalInnovation() {
		return mainTechnologicalInnovation;
	}
	/**
	 * 设置：验收类文件-文件填写
	 */
	public void setCheckFileInfo(String checkFileInfo) {
		this.checkFileInfo = checkFileInfo;
	}
	/**
	 * 获取：验收类文件-文件填写
	 */
	public String getCheckFileInfo() {
		return checkFileInfo;
	}
	/**
	 * 设置：验收类文件-验收报告
	 */
	public void setCheckReport(String checkReport) {
		this.checkReport = checkReport;
	}
	/**
	 * 获取：验收类文件-验收报告
	 */
	public String getCheckReport() {
		return checkReport;
	}
	/**
	 * 设置：验收类文件-验收意见
	 */
	public void setCheckOpinion(String checkOpinion) {
		this.checkOpinion = checkOpinion;
	}
	/**
	 * 获取：验收类文件-验收意见
	 */
	public String getCheckOpinion() {
		return checkOpinion;
	}
	/**
	 * 设置：验收类文件-各种验收结论
	 */
	public void setCheckConclusion(String checkConclusion) {
		this.checkConclusion = checkConclusion;
	}
	/**
	 * 获取：验收类文件-各种验收结论
	 */
	public String getCheckConclusion() {
		return checkConclusion;
	}
	/**
	 * 设置：验收类文件-其他证明材料
	 */
	public void setCheckEvidence(String checkEvidence) {
		this.checkEvidence = checkEvidence;
	}
	/**
	 * 获取：验收类文件-其他证明材料
	 */
	public String getCheckEvidence() {
		return checkEvidence;
	}
	/**
	 * 设置：第三方资料
	 */
	public void setThirdData(String thirdData) {
		this.thirdData = thirdData;
	}
	/**
	 * 获取：第三方资料
	 */
	public String getThirdData() {
		return thirdData;
	}
	/**
	 * 设置：特殊成果资料
	 */
	public void setSpecialData(String specialData) {
		this.specialData = specialData;
	}
	/**
	 * 获取：特殊成果资料
	 */
	public String getSpecialData() {
		return specialData;
	}
	/**
	 * 设置：知识产权类别
	 */
	public void setIntellectualPropertyType(String intellectualPropertyType) {
		this.intellectualPropertyType = intellectualPropertyType;
	}
	/**
	 * 获取：知识产权类别
	 */
	public String getIntellectualPropertyType() {
		return intellectualPropertyType;
	}
	/**
	 * 设置：知识产权名称
	 */
	public void setIntellectualPropertyName(String intellectualPropertyName) {
		this.intellectualPropertyName = intellectualPropertyName;
	}
	/**
	 * 获取：知识产权名称
	 */
	public String getIntellectualPropertyName() {
		return intellectualPropertyName;
	}
	/**
	 * 设置：知识产权-国家(地区)
	 */
	public void setIntellectualPropertyCountry(String intellectualPropertyCountry) {
		this.intellectualPropertyCountry = intellectualPropertyCountry;
	}
	/**
	 * 获取：知识产权-国家(地区)
	 */
	public String getIntellectualPropertyCountry() {
		return intellectualPropertyCountry;
	}
	/**
	 * 设置：知识产权-授权号
	 */
	public void setIntellectualPropertyAuthNum(String intellectualPropertyAuthNum) {
		this.intellectualPropertyAuthNum = intellectualPropertyAuthNum;
	}
	/**
	 * 获取：知识产权-授权号
	 */
	public String getIntellectualPropertyAuthNum() {
		return intellectualPropertyAuthNum;
	}
	/**
	 * 设置：知识产权-授权日期
	 */
	public void setIntellectualPropertyAuthDate(String intellectualPropertyAuthDate) {
		this.intellectualPropertyAuthDate = intellectualPropertyAuthDate;
	}
	/**
	 * 获取：知识产权-授权日期
	 */
	public String getIntellectualPropertyAuthDate() {
		return intellectualPropertyAuthDate;
	}
	/**
	 * 设置：知识产权-证书编号
	 */
	public void setIntellectualPropertyCertificateNum(String intellectualPropertyCertificateNum) {
		this.intellectualPropertyCertificateNum = intellectualPropertyCertificateNum;
	}
	/**
	 * 获取：知识产权-证书编号
	 */
	public String getIntellectualPropertyCertificateNum() {
		return intellectualPropertyCertificateNum;
	}
	/**
	 * 设置：知识产权-权利人
	 */
	public void setIntellectualPropertyRightUser(String intellectualPropertyRightUser) {
		this.intellectualPropertyRightUser = intellectualPropertyRightUser;
	}
	/**
	 * 获取：知识产权-权利人
	 */
	public String getIntellectualPropertyRightUser() {
		return intellectualPropertyRightUser;
	}
	/**
	 * 设置：知识产权-发明人
	 */
	public void setIntellectualPropertyInventor(String intellectualPropertyInventor) {
		this.intellectualPropertyInventor = intellectualPropertyInventor;
	}
	/**
	 * 获取：知识产权-发明人
	 */
	public String getIntellectualPropertyInventor() {
		return intellectualPropertyInventor;
	}
	/**
	 * 设置：知识产权-专利有效状态
	 */
	public void setIntellectualPropertyEffectiveStat(String intellectualPropertyEffectiveStat) {
		this.intellectualPropertyEffectiveStat = intellectualPropertyEffectiveStat;
	}
	/**
	 * 获取：知识产权-专利有效状态
	 */
	public String getIntellectualPropertyEffectiveStat() {
		return intellectualPropertyEffectiveStat;
	}
	/**
	 * 设置：成果标准
	 */
	public void setChengguoStandard(String chengguoStandard) {
		this.chengguoStandard = chengguoStandard;

		BootdoConfig bootdoConfig = getBean("bootdoConfig");
		this.chengguoStandardImage = CpeCommonUtils.getImages(this.chengguoStandard,bootdoConfig.getImgUrlPre());

		//this.chengguoStandardNo = this.chengguoStandard.replaceAll("\\<.*?>","");

	}
	/**
	 * 获取：成果标准
	 */
	public String getChengguoStandard() {
		return chengguoStandard;
	}
	/**
	 * 设置：第三方评价意见及证书
	 */
	public void setThirdOpinion(String thirdOpinion) {
		this.thirdOpinion = thirdOpinion;

		BootdoConfig bootdoConfig = getBean("bootdoConfig");
		this.thirdOpinionImage = CpeCommonUtils.getImages(this.thirdOpinion,bootdoConfig.getImgUrlPre());

		//this.thirdOpinionNo = this.thirdOpinion.replaceAll("\\<.*?>","");

	}
	/**
	 * 获取：第三方评价意见及证书
	 */
	public String getThirdOpinion() {
		return thirdOpinion;
	}
	/**
	 * 设置：主要完成单位及主要完成人情况-单位名称
	 */
	public void setMainEnterpriseCompanyName(String mainEnterpriseCompanyName) {
		this.mainEnterpriseCompanyName = mainEnterpriseCompanyName;
	}
	/**
	 * 获取：主要完成单位及主要完成人情况-单位名称
	 */
	public String getMainEnterpriseCompanyName() {
		return mainEnterpriseCompanyName;
	}
	/**
	 * 设置：主要完成单位及主要完成人情况-主要完成人姓名
	 */
	public void setMainEnterpriseCompleter(String mainEnterpriseCompleter) {
		this.mainEnterpriseCompleter = mainEnterpriseCompleter;
	}
	/**
	 * 获取：主要完成单位及主要完成人情况-主要完成人姓名
	 */
	public String getMainEnterpriseCompleter() {
		return mainEnterpriseCompleter;
	}
	/**
	 * 设置：主要完成单位及主要完成人情况-性别
	 */
	public void setMainEnterpriseCompleterGender(String mainEnterpriseCompleterGender) {
		this.mainEnterpriseCompleterGender = mainEnterpriseCompleterGender;
	}
	/**
	 * 获取：主要完成单位及主要完成人情况-性别
	 */
	public String getMainEnterpriseCompleterGender() {
		return mainEnterpriseCompleterGender;
	}
	/**
	 * 设置：主要完成单位及主要完成人情况-排名
	 */
	public void setMainEnterpriseCompleterSort(String mainEnterpriseCompleterSort) {
		this.mainEnterpriseCompleterSort = mainEnterpriseCompleterSort;
		if(StringUtils.isNotBlank(mainEnterpriseCompleterSort) && StringUtils.isNumeric(mainEnterpriseCompleterSort.trim())) {
			this.mainEnterpriseCompleterSortInt = Integer.parseInt(mainEnterpriseCompleterSort.trim());
		}
	}
	/**
	 * 获取：主要完成单位及主要完成人情况-排名
	 */
	public String getMainEnterpriseCompleterSort() {
		return this.mainEnterpriseCompleterSort;
	}

	public int getMainEnterpriseCompleterSortInt() {
		return mainEnterpriseCompleterSortInt;
	}

	public void setMainEnterpriseCompleterSortInt(int mainEnterpriseCompleterSortInt) {
		this.mainEnterpriseCompleterSortInt = mainEnterpriseCompleterSortInt;
	}

	/**
	 * 设置：主要完成单位及主要完成人情况-出生年月
	 */
	public void setMainEnterpriseCompleterBirth(String mainEnterpriseCompleterBirth) {
		this.mainEnterpriseCompleterBirth = mainEnterpriseCompleterBirth;
	}
	/**
	 * 获取：主要完成单位及主要完成人情况-出生年月
	 */
	public String getMainEnterpriseCompleterBirth() {
		return mainEnterpriseCompleterBirth;
	}
	/**
	 * 设置：主要完成单位及主要完成人情况-身份证号
	 */
	public void setMainEnterpriseCompleterId(String mainEnterpriseCompleterId) {
		this.mainEnterpriseCompleterId = mainEnterpriseCompleterId;
	}
	/**
	 * 获取：主要完成单位及主要完成人情况-身份证号
	 */
	public String getMainEnterpriseCompleterId() {
		return mainEnterpriseCompleterId;
	}
	/**
	 * 设置：主要完成单位及主要完成人情况-民族
	 */
	public void setMainEnterpriseCompleterNation(String mainEnterpriseCompleterNation) {
		this.mainEnterpriseCompleterNation = mainEnterpriseCompleterNation;
	}
	/**
	 * 获取：主要完成单位及主要完成人情况-民族
	 */
	public String getMainEnterpriseCompleterNation() {
		return mainEnterpriseCompleterNation;
	}
	/**
	 * 设置：主要完成单位及主要完成人情况-国籍
	 */
	public void setMainEnterpriseCompleterCountry(String mainEnterpriseCompleterCountry) {
		this.mainEnterpriseCompleterCountry = mainEnterpriseCompleterCountry;
	}
	/**
	 * 获取：主要完成单位及主要完成人情况-国籍
	 */
	public String getMainEnterpriseCompleterCountry() {
		return mainEnterpriseCompleterCountry;
	}
	/**
	 * 设置：主要完成单位及主要完成人情况-学历
	 */
	public void setMainEnterpriseCompleterEducation(String mainEnterpriseCompleterEducation) {
		this.mainEnterpriseCompleterEducation = mainEnterpriseCompleterEducation;
	}
	/**
	 * 获取：主要完成单位及主要完成人情况-学历
	 */
	public String getMainEnterpriseCompleterEducation() {
		return mainEnterpriseCompleterEducation;
	}
	/**
	 * 设置：主要完成单位及主要完成人情况-行政职务
	 */
	public void setMainEnterpriseCompleterAdministrativePost(String mainEnterpriseCompleterAdministrativePost) {
		this.mainEnterpriseCompleterAdministrativePost = mainEnterpriseCompleterAdministrativePost;
	}
	/**
	 * 获取：主要完成单位及主要完成人情况-行政职务
	 */
	public String getMainEnterpriseCompleterAdministrativePost() {
		return mainEnterpriseCompleterAdministrativePost;
	}
	/**
	 * 设置：技术职称
	 */
	public void setMainEnterpriseCompleterTechnicalTitle(String mainEnterpriseCompleterTechnicalTitle) {
		this.mainEnterpriseCompleterTechnicalTitle = mainEnterpriseCompleterTechnicalTitle;
	}
	/**
	 * 获取：技术职称
	 */
	public String getMainEnterpriseCompleterTechnicalTitle() {
		return mainEnterpriseCompleterTechnicalTitle;
	}
	/**
	 * 设置：主要完成单位及主要完成人情况-从事的专业
	 */
	public void setMainEnterpriseCompleterMajorWork(String mainEnterpriseCompleterMajorWork) {
		this.mainEnterpriseCompleterMajorWork = mainEnterpriseCompleterMajorWork;
	}
	/**
	 * 获取：主要完成单位及主要完成人情况-从事的专业
	 */
	public String getMainEnterpriseCompleterMajorWork() {
		return mainEnterpriseCompleterMajorWork;
	}
	/**
	 * 设置：主要完成单位及主要完成人情况-所学的专业
	 */
	public void setMainEnterpriseCompleterMajorStudy(String mainEnterpriseCompleterMajorStudy) {
		this.mainEnterpriseCompleterMajorStudy = mainEnterpriseCompleterMajorStudy;
	}
	/**
	 * 获取：主要完成单位及主要完成人情况-所学的专业
	 */
	public String getMainEnterpriseCompleterMajorStudy() {
		return mainEnterpriseCompleterMajorStudy;
	}
	/**
	 * 设置：主要完成单位及主要完成人情况-办公电话
	 */
	public void setMainEnterpriseCompleterWorkTelphone(String mainEnterpriseCompleterWorkTelphone) {
		this.mainEnterpriseCompleterWorkTelphone = mainEnterpriseCompleterWorkTelphone;
	}
	/**
	 * 获取：主要完成单位及主要完成人情况-办公电话
	 */
	public String getMainEnterpriseCompleterWorkTelphone() {
		return mainEnterpriseCompleterWorkTelphone;
	}
	/**
	 * 设置：主要完成单位及主要完成人情况-手机
	 */
	public void setMainEnterpriseCompleterWorkMobile(String mainEnterpriseCompleterWorkMobile) {
		this.mainEnterpriseCompleterWorkMobile = mainEnterpriseCompleterWorkMobile;
	}
	/**
	 * 获取：主要完成单位及主要完成人情况-手机
	 */
	public String getMainEnterpriseCompleterWorkMobile() {
		return mainEnterpriseCompleterWorkMobile;
	}
	/**
	 * 设置：主要完成单位及主要完成人情况-电子邮箱
	 */
	public void setMainEnterpriseCompleterWorkEmail(String mainEnterpriseCompleterWorkEmail) {
		this.mainEnterpriseCompleterWorkEmail = mainEnterpriseCompleterWorkEmail;
	}
	/**
	 * 获取：主要完成单位及主要完成人情况-电子邮箱
	 */
	public String getMainEnterpriseCompleterWorkEmail() {
		return mainEnterpriseCompleterWorkEmail;
	}
	/**
	 * 设置：通讯地址
	 */
	public void setMainEnterpriseCompleterWorkAddress(String mainEnterpriseCompleterWorkAddress) {
		this.mainEnterpriseCompleterWorkAddress = mainEnterpriseCompleterWorkAddress;
	}
	/**
	 * 获取：通讯地址
	 */
	public String getMainEnterpriseCompleterWorkAddress() {
		return mainEnterpriseCompleterWorkAddress;
	}
	/**
	 * 设置：邮编
	 */
	public void setMainEnterpriseCompleterWorkAddrCode(String mainEnterpriseCompleterWorkAddrCode) {
		this.mainEnterpriseCompleterWorkAddrCode = mainEnterpriseCompleterWorkAddrCode;
	}
	/**
	 * 获取：邮编
	 */
	public String getMainEnterpriseCompleterWorkAddrCode() {
		return mainEnterpriseCompleterWorkAddrCode;
	}
	/**
	 * 设置：主要完成单位及主要完成人情况-参加本项目的起止时间
	 */
	public void setMainEnterpriseCompleterPartakeTime(String mainEnterpriseCompleterPartakeTime) {
		this.mainEnterpriseCompleterPartakeTime = mainEnterpriseCompleterPartakeTime;
	}
	/**
	 * 获取：主要完成单位及主要完成人情况-参加本项目的起止时间
	 */
	public String getMainEnterpriseCompleterPartakeTime() {
		return mainEnterpriseCompleterPartakeTime;
	}
	/**
	 * 设置：主要完成单位及主要完成人情况-主要完成人对本项目的学术贡献 
	 */
	public void setMainEnterpriseCompleterAcademicContribution(String mainEnterpriseCompleterAcademicContribution) {
		this.mainEnterpriseCompleterAcademicContribution = mainEnterpriseCompleterAcademicContribution;
	}
	/**
	 * 获取：主要完成单位及主要完成人情况-主要完成人对本项目的学术贡献 
	 */
	public String getMainEnterpriseCompleterAcademicContribution() {
		return mainEnterpriseCompleterAcademicContribution;
	}
	/**
	 * 设置：主要完成单位及主要完成人情况-主要完成单位对本项目科技创新和推广应用情况的贡献 
	 */
	public void setMainEnterpriseContribution(String mainEnterpriseContribution) {
		this.mainEnterpriseContribution = mainEnterpriseContribution;
	}
	/**
	 * 获取：主要完成单位及主要完成人情况-主要完成单位对本项目科技创新和推广应用情况的贡献 
	 */
	public String getMainEnterpriseContribution() {
		return mainEnterpriseContribution;
	}
	/**
	 * 设置：已获奖项
	 */
	public void setMainEnterpriseCompleterAwards(String mainEnterpriseCompleterAwards) {
		this.mainEnterpriseCompleterAwards = mainEnterpriseCompleterAwards;
	}
	/**
	 * 获取：已获奖项
	 */
	public String getMainEnterpriseCompleterAwards() {
		return mainEnterpriseCompleterAwards;
	}
	/**
	 * 设置：成果基本情况-申报书
	 */
	public void setChengguoBaseDeclaration(String chengguoBaseDeclaration) {
		this.chengguoBaseDeclaration = chengguoBaseDeclaration;
	}
	/**
	 * 获取：成果基本情况-申报书
	 */
	public String getChengguoBaseDeclaration() {
		return chengguoBaseDeclaration;
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
}
