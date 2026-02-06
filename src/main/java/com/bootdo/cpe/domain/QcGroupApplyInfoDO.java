package com.bootdo.cpe.domain;

import com.bootdo.common.config.BootdoConfig;
import com.bootdo.cpe.dto.QcBaseProjectInfoDO;
import com.bootdo.cpe.petroleum_engineering_award.domain.QCImageObj;
import com.bootdo.cpe.utils.CpeCommonUtils;
import com.bootdo.cpe.utils.DateUtil;
import com.deepoove.poi.data.PictureType;
import com.deepoove.poi.data.Pictures;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import static com.bootdo.common.utils.SpringContextHolder.getBean;


/**
 * 石油工程建设优秀质量管理小组申报表
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-02-16 01:08:57
 */
public class QcGroupApplyInfoDO extends QcBaseProjectInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;


	private static final Pattern IMAGE_TAG_PATTERN = Pattern.compile("<(img|IMG)(.*?)>");
	private static Pattern IMAGE_SRC_PATTERN = Pattern.compile("(src|SRC)=\"(.*?)\"");
	private static Pattern IMAGE__SRC_PATTERN = Pattern.compile("(_src|_SRC)=\"(.*?)\"");


	//
	private Integer id;
	//
	private Integer optUid;
	//
	private Integer proId;
	//
	private String taskId;
	//
	private String applyId;
	//小组名称
	private String groupName;
	//课题名称
	private String topicName;
	//课题类型:创新型，问题解决型
	private String topicType;
	//联系人
	private String contacts;
	//联系电话
	private String phone;
	//专业范围:管理，管理类，监理，监理、管理，勘察设计，施工
	private String professionalScope;
	//小组简介
	private String groupDesc;
	//小组简介 没有标签
	private String groupDescNO;

	private List<QCImageObj> groupDescImg = new ArrayList<>();

	public String getGroupDescNO() {
		return groupDescNO;
	}

	public void setGroupDescNO(String groupDescNO) {
		this.groupDescNO = groupDescNO;

	}

	//选择课题理由
	private String selTopicReason;
	//选择课题理由
	private String selTopicReasonNo;

	//活动概况
	private String actDesc;

	//活动概况
	private String actDescNo;

	//取得的主要成果
	private String mainResult;
	//取得的主要成果
	private String mainResultNo;

	//
	private String recommendAction;

	//
	private String recommendActionNo;

	public String getSelTopicReasonNo() {
		return selTopicReasonNo;
	}

	public void setSelTopicReasonNo(String selTopicReasonNo) {
		this.selTopicReasonNo = selTopicReasonNo;

	}

	public String getActDescNo() {
		return actDescNo;
	}

	public void setActDescNo(String actDescNo) {
		this.actDescNo = actDescNo;
	}

	public String getMainResultNo() {
		return mainResultNo;
	}

	public void setMainResultNo(String mainResultNo) {
		this.mainResultNo = mainResultNo;
	}

	public String getRecommendActionNo() {
		return recommendActionNo;
	}

	public void setRecommendActionNo(String recommendActionNo) {
		this.recommendActionNo = recommendActionNo;
	}

	//创建日期
	private Date created;
	//更新日期
	private Date updated;
	//是否删除0未删除，1已删除
	private Integer deleted;
	//小组单位名称
	private String unitName;

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
	 * 设置：小组名称
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	/**
	 * 获取：小组名称
	 */
	public String getGroupName() {
		return groupName;
	}
	/**
	 * 设置：课题名称
	 */
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	/**
	 * 获取：课题名称
	 */
	public String getTopicName() {
		return topicName;
	}
	/**
	 * 设置：课题类型:创新型，问题解决型
	 */
	public void setTopicType(String topicType) {
		this.topicType = topicType;
	}
	/**
	 * 获取：课题类型:创新型，问题解决型
	 */
	public String getTopicType() {
		return topicType;
	}
	/**
	 * 设置：联系人
	 */
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	/**
	 * 获取：联系人
	 */
	public String getContacts() {
		return contacts;
	}
	/**
	 * 设置：联系电话
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取：联系电话
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * 设置：专业范围:管理，管理类，监理，监理、管理，勘察设计，施工
	 */
	public void setProfessionalScope(String professionalScope) {
		this.professionalScope = professionalScope;
	}
	/**
	 * 获取：专业范围:管理，管理类，监理，监理、管理，勘察设计，施工
	 */
	public String getProfessionalScope() {
		return professionalScope;
	}
	/**
	 * 设置：小组简介
	 */
	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
		this.groupDescNO = this.groupDesc.replaceAll("\\<.*?>","");
		BootdoConfig bootdoConfig = getBean("bootdoConfig");
		//this.groupDescImg = CpeCommonUtils.getImages(this.groupDesc,bootdoConfig.getImgUrlPre());
	}

	public List<QCImageObj> getGroupDescImg() {
		return groupDescImg;
	}

	public void setGroupDescImg(List<QCImageObj> groupDescImg) {
		this.groupDescImg = groupDescImg;
	}

	public List<QCImageObj> getMatchImg() {
		return groupDescImg;
	}

	public void setMatchImg(List<QCImageObj> matchImg) {
		this.groupDescImg = matchImg;
	}

	/**
	 * 获取：小组简介
	 */
	public String getGroupDesc() {
		return groupDesc;
	}
	/**
	 * 设置：选择课题理由
	 */
	public void setSelTopicReason(String selTopicReason) {
		this.selTopicReason = selTopicReason;
		selTopicReasonNo = this. selTopicReason.replaceAll("\\<.*?>","");
	}
	/**
	 * 获取：选择课题理由
	 */
	public String getSelTopicReason() {
		return selTopicReason;
	}
	/**
	 * 设置：活动概况
	 */
	public void setActDesc(String actDesc) {
		this.actDesc = actDesc;
		this.actDescNo = this.actDesc.replaceAll("\\<.*?>","");
	}
	/**
	 * 获取：活动概况
	 */
	public String getActDesc() {
		return actDesc;
	}
	/**
	 * 设置：取得的主要成果
	 */
	public void setMainResult(String mainResult) {
		this.mainResult = mainResult;
		this.mainResultNo = this.mainResult.replaceAll("\\<.*?>","");
	}
	/**
	 * 获取：取得的主要成果
	 */
	public String getMainResult() {
		return mainResult;
	}
	/**
	 * 设置：
	 */
	public void setRecommendAction(String recommendAction) {
		this.recommendAction = recommendAction;
		this.recommendActionNo = this.recommendAction.replaceAll("\\<.*?>","");
	}
	/**
	 * 获取：
	 */
	public String getRecommendAction() {
		return recommendAction;
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
	/**
	 * 设置：是否删除0未删除，1已删除
	 */
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
	/**
	 * 获取：是否删除0未删除，1已删除
	 */
	public Integer getDeleted() {
		return deleted;
	}
	/**
	 * 设置：小组单位名称
	 */
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	/**
	 * 获取：小组单位名称
	 */
	public String getUnitName() {
		return unitName;
	}
}
