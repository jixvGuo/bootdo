package com.bootdo.cpe.domain;

import com.bootdo.cpe.dto.QcBaseProjectInfoDO;

import java.io.Serializable;
import java.util.Date;



/**
 * 公奖法申请信息
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-07-27 08:03:07
 */
public class GfGroupApplyInfoDO extends QcBaseProjectInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//工法名称
	private String gfName;
	//主要完成单位
	private String gfUnit;
	//推荐的单位
	private String gfRecommendUnit;
	//通讯地址
	private String gfAddress;
	//邮编
	private String gfCode;
	//联系人
	private String gfContact;
	//联系电话
	private String gfPhone;
	//工法应用工程名称和时间
	private String gfApplyInfo;
	//工法关键技术名称、组织审定的单位和时间
	private String gfKeyInfo;
	//工法关键技术获科技成果奖励的情况
	private String gfKeyAward;
	//原工法名称、完成单位、石油工程建设工法批准文号及工法编号(重新申报项目填写此栏)
	private String gfOriginInfo;
	//工法内容简述
	private String gfDesc;
	//关键技术及保密点（如有专利权，请注名专利号）
	private String gfKeySecret;
	//技术水平和技术难度（与国内外同类技术水平比较）
	private String gfTechnologyLevel;
	//工法成熟、可靠性说明（当工法工程应用少于2项时填写）
	private String gfReliableDesc;
	//工法应用情况及应用前景
	private String gfApplyDesc;
	//经济效益和社会效益（包括节能和环保效益）
	private String gfBenefit;
	//
	private String proId;
	//
	private Date created;
	//
	private Date update;
	//
	private String taskId;
	//
	private String applyId;
	//
	private String optUid;

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
	 * 设置：工法名称
	 */
	public void setGfName(String gfName) {
		this.gfName = gfName;
	}
	/**
	 * 获取：工法名称
	 */
	public String getGfName() {
		return gfName;
	}
	/**
	 * 设置：主要完成单位
	 */
	public void setGfUnit(String gfUnit) {
		this.gfUnit = gfUnit;
	}
	/**
	 * 获取：主要完成单位
	 */
	public String getGfUnit() {
		return gfUnit;
	}
	/**
	 * 设置：推荐的单位
	 */
	public void setGfRecommendUnit(String gfRecommendUnit) {
		this.gfRecommendUnit = gfRecommendUnit;
	}
	/**
	 * 获取：推荐的单位
	 */
	public String getGfRecommendUnit() {
		return gfRecommendUnit;
	}
	/**
	 * 设置：通讯地址
	 */
	public void setGfAddress(String gfAddress) {
		this.gfAddress = gfAddress;
	}
	/**
	 * 获取：通讯地址
	 */
	public String getGfAddress() {
		return gfAddress;
	}
	/**
	 * 设置：邮编
	 */
	public void setGfCode(String gfCode) {
		this.gfCode = gfCode;
	}
	/**
	 * 获取：邮编
	 */
	public String getGfCode() {
		return gfCode;
	}
	/**
	 * 设置：联系人
	 */
	public void setGfContact(String gfContact) {
		this.gfContact = gfContact;
	}
	/**
	 * 获取：联系人
	 */
	public String getGfContact() {
		return gfContact;
	}
	/**
	 * 设置：联系电话
	 */
	public void setGfPhone(String gfPhone) {
		this.gfPhone = gfPhone;
	}
	/**
	 * 获取：联系电话
	 */
	public String getGfPhone() {
		return gfPhone;
	}
	/**
	 * 设置：工法应用工程名称和时间
	 */
	public void setGfApplyInfo(String gfApplyInfo) {
		this.gfApplyInfo = gfApplyInfo;
	}
	/**
	 * 获取：工法应用工程名称和时间
	 */
	public String getGfApplyInfo() {
		return gfApplyInfo;
	}
	/**
	 * 设置：工法关键技术名称、组织审定的单位和时间
	 */
	public void setGfKeyInfo(String gfKeyInfo) {
		this.gfKeyInfo = gfKeyInfo;
	}
	/**
	 * 获取：工法关键技术名称、组织审定的单位和时间
	 */
	public String getGfKeyInfo() {
		return gfKeyInfo;
	}
	/**
	 * 设置：工法关键技术获科技成果奖励的情况
	 */
	public void setGfKeyAward(String gfKeyAward) {
		this.gfKeyAward = gfKeyAward;
	}
	/**
	 * 获取：工法关键技术获科技成果奖励的情况
	 */
	public String getGfKeyAward() {
		return gfKeyAward;
	}
	/**
	 * 设置：原工法名称、完成单位、石油工程建设工法批准文号及工法编号(重新申报项目填写此栏)
	 */
	public void setGfOriginInfo(String gfOriginInfo) {
		this.gfOriginInfo = gfOriginInfo;
	}
	/**
	 * 获取：原工法名称、完成单位、石油工程建设工法批准文号及工法编号(重新申报项目填写此栏)
	 */
	public String getGfOriginInfo() {
		return gfOriginInfo;
	}
	/**
	 * 设置：工法内容简述
	 */
	public void setGfDesc(String gfDesc) {
		this.gfDesc = gfDesc;
	}
	/**
	 * 获取：工法内容简述
	 */
	public String getGfDesc() {
		return gfDesc;
	}
	/**
	 * 设置：关键技术及保密点（如有专利权，请注名专利号）
	 */
	public void setGfKeySecret(String gfKeySecret) {
		this.gfKeySecret = gfKeySecret;
	}
	/**
	 * 获取：关键技术及保密点（如有专利权，请注名专利号）
	 */
	public String getGfKeySecret() {
		return gfKeySecret;
	}
	/**
	 * 设置：技术水平和技术难度（与国内外同类技术水平比较）
	 */
	public void setGfTechnologyLevel(String gfTechnologyLevel) {
		this.gfTechnologyLevel = gfTechnologyLevel;
	}
	/**
	 * 获取：技术水平和技术难度（与国内外同类技术水平比较）
	 */
	public String getGfTechnologyLevel() {
		return gfTechnologyLevel;
	}
	/**
	 * 设置：工法成熟、可靠性说明（当工法工程应用少于2项时填写）
	 */
	public void setGfReliableDesc(String gfReliableDesc) {
		this.gfReliableDesc = gfReliableDesc;
	}
	/**
	 * 获取：工法成熟、可靠性说明（当工法工程应用少于2项时填写）
	 */
	public String getGfReliableDesc() {
		return gfReliableDesc;
	}
	/**
	 * 设置：工法应用情况及应用前景
	 */
	public void setGfApplyDesc(String gfApplyDesc) {
		this.gfApplyDesc = gfApplyDesc;
	}
	/**
	 * 获取：工法应用情况及应用前景
	 */
	public String getGfApplyDesc() {
		return gfApplyDesc;
	}
	/**
	 * 设置：经济效益和社会效益（包括节能和环保效益）
	 */
	public void setGfBenefit(String gfBenefit) {
		this.gfBenefit = gfBenefit;
	}
	/**
	 * 获取：经济效益和社会效益（包括节能和环保效益）
	 */
	public String getGfBenefit() {
		return gfBenefit;
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
	public void setUpdate(Date update) {
		this.update = update;
	}
	/**
	 * 获取：
	 */
	public Date getUpdate() {
		return update;
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
	public void setOptUid(String optUid) {
		this.optUid = optUid;
	}
	/**
	 * 获取：
	 */
	public String getOptUid() {
		return optUid;
	}
}
