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
 * 支持单位情况
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-07-05 08:21:09
 */
public class EnterpriTeamSupportCompanyDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//单位名称
	private String comName;
	//序号
	private String comNum;
	//所在地
	private String comAddress;
	//是否主要支持单位 
	private String comIsMainSupport;
	//单位性质
	private String comNature;
	//
	private String comEmail;
	//传真 
	private String comFox;
	//法定代表人
	private String comLegalPerson;
	//单位电话
	private String comTelphone;
	//移动电话
	private String comMobilePhone;
	//联系人
	private String comConcat;
	//联系人-单位电话
	private String comConcatTelphone;
	//联系人-移动电话
	private String comConcatMobilePhone;
	//通讯地址
	private String comConcatAddress;
	//邮政编码
	private String comPostcode;
	//对团队发展的贡献
	private String comContribution;

	private String comContributionNo;
	private List<QCImageObj> comContributionImage = new ArrayList<>();

	//创建日期
	private Date created;
	//操作的用户id
	private Long optUid;
	//发布的任务id
	private String taskId;
	//项目id
	private Integer proId;

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
	 * 设置：单位名称
	 */
	public void setComName(String comName) {
		this.comName = comName;
	}
	/**
	 * 获取：单位名称
	 */
	public String getComName() {
		return comName;
	}
	/**
	 * 设置：序号
	 */
	public void setComNum(String comNum) {
		this.comNum = comNum;
	}
	/**
	 * 获取：序号
	 */
	public String getComNum() {
		return comNum;
	}
	/**
	 * 设置：所在地
	 */
	public void setComAddress(String comAddress) {
		this.comAddress = comAddress;
	}
	/**
	 * 获取：所在地
	 */
	public String getComAddress() {
		return comAddress;
	}
	/**
	 * 设置：是否主要支持单位 
	 */
	public void setComIsMainSupport(String comIsMainSupport) {
		this.comIsMainSupport = comIsMainSupport;
	}
	/**
	 * 获取：是否主要支持单位 
	 */
	public String getComIsMainSupport() {
		return comIsMainSupport;
	}
	/**
	 * 设置：单位性质
	 */
	public void setComNature(String comNature) {
		this.comNature = comNature;
	}
	/**
	 * 获取：单位性质
	 */
	public String getComNature() {
		return comNature;
	}
	/**
	 * 设置：
	 */
	public void setComEmail(String comEmail) {
		this.comEmail = comEmail;
	}
	/**
	 * 获取：
	 */
	public String getComEmail() {
		return comEmail;
	}
	/**
	 * 设置：传真 
	 */
	public void setComFox(String comFox) {
		this.comFox = comFox;
	}
	/**
	 * 获取：传真 
	 */
	public String getComFox() {
		return comFox;
	}
	/**
	 * 设置：法定代表人
	 */
	public void setComLegalPerson(String comLegalPerson) {
		this.comLegalPerson = comLegalPerson;
	}
	/**
	 * 获取：法定代表人
	 */
	public String getComLegalPerson() {
		return comLegalPerson;
	}
	/**
	 * 设置：单位电话
	 */
	public void setComTelphone(String comTelphone) {
		this.comTelphone = comTelphone;
	}
	/**
	 * 获取：单位电话
	 */
	public String getComTelphone() {
		return comTelphone;
	}
	/**
	 * 设置：移动电话
	 */
	public void setComMobilePhone(String comMobilePhone) {
		this.comMobilePhone = comMobilePhone;
	}
	/**
	 * 获取：移动电话
	 */
	public String getComMobilePhone() {
		return comMobilePhone;
	}
	/**
	 * 设置：联系人
	 */
	public void setComConcat(String comConcat) {
		this.comConcat = comConcat;
	}
	/**
	 * 获取：联系人
	 */
	public String getComConcat() {
		return comConcat;
	}
	/**
	 * 设置：联系人-单位电话
	 */
	public void setComConcatTelphone(String comConcatTelphone) {
		this.comConcatTelphone = comConcatTelphone;
	}
	/**
	 * 获取：联系人-单位电话
	 */
	public String getComConcatTelphone() {
		return comConcatTelphone;
	}
	/**
	 * 设置：联系人-移动电话
	 */
	public void setComConcatMobilePhone(String comConcatMobilePhone) {
		this.comConcatMobilePhone = comConcatMobilePhone;
	}
	/**
	 * 获取：联系人-移动电话
	 */
	public String getComConcatMobilePhone() {
		return comConcatMobilePhone;
	}
	/**
	 * 设置：通讯地址
	 */
	public void setComConcatAddress(String comConcatAddress) {
		this.comConcatAddress = comConcatAddress;
	}
	/**
	 * 获取：通讯地址
	 */
	public String getComConcatAddress() {
		return comConcatAddress;
	}
	/**
	 * 设置：邮政编码
	 */
	public void setComPostcode(String comPostcode) {
		this.comPostcode = comPostcode;
	}
	/**
	 * 获取：邮政编码
	 */
	public String getComPostcode() {
		return comPostcode;
	}

	public String getComContributionNo() {
		return comContributionNo;
	}

	public void setComContributionNo(String comContributionNo) {
		this.comContributionNo = comContributionNo;
	}

	public List<QCImageObj> getComContributionImage() {
		return comContributionImage;
	}

	public void setComContributionImage(List<QCImageObj> comContributionImage) {
		this.comContributionImage = comContributionImage;
	}

	/**
	 * 设置：对团队发展的贡献
	 */
	public void setComContribution(String comContribution) {
		this.comContribution = comContribution;
		this.comContributionNo = this.comContribution.replaceAll("\\<.*?>","");
		BootdoConfig bootdoConfig = getBean("bootdoConfig");
		//this.comContributionImage = CpeCommonUtils.getImages(this.comContribution,bootdoConfig.getImgUrlPre());



	}
	/**
	 * 获取：对团队发展的贡献
	 */
	public String getComContribution() {
		return comContribution;
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
	 * 获取：操作的用户id
	 */
	public Long getOptUid() {
		return optUid;
	}

	/**
	 * 设置：操作的用户id
	 */
	public void setOptUid(Long optUid) {
		this.optUid = optUid;
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
}
