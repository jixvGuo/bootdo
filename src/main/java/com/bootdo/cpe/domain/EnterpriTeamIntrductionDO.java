package com.bootdo.cpe.domain;

import com.bootdo.common.config.BootdoConfig;
import com.bootdo.cpe.petroleum_engineering_award.domain.QCImageObj;
import com.bootdo.cpe.utils.CpeCommonUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.bootdo.common.utils.SpringContextHolder.getBean;


/**
 * 团队简介信息
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-10-15 23:37:19
 */
public class EnterpriTeamIntrductionDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//id
	private Integer id;
	//项目id就是团队的id
	private Integer proId;
	//
	private String taskId;
	//
	private String applyId;
	//
	private String optUid;
	//
	private Date create;
	//团队的成果简介
	private String teamDes;
	private String teamDesNo;
	private List<QCImageObj> teamDesImage = new ArrayList<>();

	//团队建设情况
	private String teamBuildInfo;

	private String teamBuildInfoNo;
	private List<QCImageObj> teamBuildInfoImage = new ArrayList<>();

	public String getTeamDesNo() {
		return teamDesNo;
	}

	public void setTeamDesNo(String teamDesNo) {
		this.teamDesNo = teamDesNo;
	}

	public List<QCImageObj> getTeamDesImage() {
		return teamDesImage;
	}

	public void setTeamDesImage(List<QCImageObj> teamDesImage) {
		this.teamDesImage = teamDesImage;
	}

	public String getTeamBuildInfoNo() {
		return teamBuildInfoNo;
	}

	public void setTeamBuildInfoNo(String teamBuildInfoNo) {
		this.teamBuildInfoNo = teamBuildInfoNo;
	}

	public List<QCImageObj> getTeamBuildInfoImage() {
		return teamBuildInfoImage;
	}

	public void setTeamBuildInfoImage(List<QCImageObj> teamBuildInfoImage) {
		this.teamBuildInfoImage = teamBuildInfoImage;
	}

	public String getTeamInnovateNo() {
		return teamInnovateNo;
	}

	public void setTeamInnovateNo(String teamInnovateNo) {
		this.teamInnovateNo = teamInnovateNo;
	}

	public List<QCImageObj> getTeamInnovateImage() {
		return teamInnovateImage;
	}

	public void setTeamInnovateImage(List<QCImageObj> teamInnovateImage) {
		this.teamInnovateImage = teamInnovateImage;
	}

	public String getTeamSocialNo() {
		return teamSocialNo;
	}

	public void setTeamSocialNo(String teamSocialNo) {
		this.teamSocialNo = teamSocialNo;
	}

	public List<QCImageObj> getTeamSocialImage() {
		return teamSocialImage;
	}

	public void setTeamSocialImage(List<QCImageObj> teamSocialImage) {
		this.teamSocialImage = teamSocialImage;
	}

	public String getTeamDevelopmentNo() {
		return teamDevelopmentNo;
	}

	public void setTeamDevelopmentNo(String teamDevelopmentNo) {
		this.teamDevelopmentNo = teamDevelopmentNo;
	}

	public List<QCImageObj> getTeamDevelopmentImage() {
		return teamDevelopmentImage;
	}

	public void setTeamDevelopmentImage(List<QCImageObj> teamDevelopmentImage) {
		this.teamDevelopmentImage = teamDevelopmentImage;
	}

	//创新能力与水平
	private String teamInnovate;
	private String teamInnovateNo;
	private List<QCImageObj> teamInnovateImage = new ArrayList<>();

	//社会贡献与学术影响
	private String teamSocial;
	private String teamSocialNo;
	private List<QCImageObj> teamSocialImage = new ArrayList<>();

	//持续发展与服务能力
	private String teamDevelopment;
	private String teamDevelopmentNo;
	private List<QCImageObj> teamDevelopmentImage = new ArrayList<>();



	/**
	 * 设置：id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：项目id就是团队的id
	 */
	public void setProId(Integer proId) {
		this.proId = proId;
	}
	/**
	 * 获取：项目id就是团队的id
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
	/**
	 * 设置：
	 */
	public void setCreate(Date create) {
		this.create = create;
	}
	/**
	 * 获取：
	 */
	public Date getCreate() {
		return create;
	}
	/**
	 * 设置：团队的成果简介
	 */
	public void setTeamDes(String teamDes) {
		this.teamDes = teamDes;

		this.teamDesNo = this.teamDes.replaceAll("\\<.*?>","");
		BootdoConfig bootdoConfig = getBean("bootdoConfig");
		//this.teamDesImage = CpeCommonUtils.getImages(this.teamDes,bootdoConfig.getImgUrlPre());



	}
	/**
	 * 获取：团队的成果简介
	 */
	public String getTeamDes() {
		return teamDes;
	}

	public String getTeamBuildInfo() {
		return teamBuildInfo;
	}

	public void setTeamBuildInfo(String teamBuildInfo) {
		this.teamBuildInfo = teamBuildInfo;

		this.teamBuildInfoNo = this.teamBuildInfo.replaceAll("\\<.*?>","");
		BootdoConfig bootdoConfig = getBean("bootdoConfig");
		//this.teamBuildInfoImage = CpeCommonUtils.getImages(this.teamBuildInfo,bootdoConfig.getImgUrlPre());


	}

	public String getTeamInnovate() {
		return teamInnovate;
	}

	public void setTeamInnovate(String teamInnovate) {
		this.teamInnovate = teamInnovate;
		this.teamInnovateNo = this.teamInnovate.replaceAll("\\<.*?>","");
		BootdoConfig bootdoConfig = getBean("bootdoConfig");
		//this.teamInnovateImage = CpeCommonUtils.getImages(this.teamInnovate,bootdoConfig.getImgUrlPre());
	}

	public String getTeamSocial() {
		return teamSocial;
	}

	public void setTeamSocial(String teamSocial) {
		this.teamSocial = teamSocial;

		this.teamSocialNo = this.teamSocial.replaceAll("\\<.*?>","");
		BootdoConfig bootdoConfig = getBean("bootdoConfig");
		//this.teamSocialImage = CpeCommonUtils.getImages(this.teamSocial,bootdoConfig.getImgUrlPre());


	}

	public String getTeamDevelopment() {
		return teamDevelopment;
	}

	public void setTeamDevelopment(String teamDevelopment) {
		this.teamDevelopment = teamDevelopment;

		this.teamDevelopmentNo = this.teamDevelopment.replaceAll("\\<.*?>","");
		BootdoConfig bootdoConfig = getBean("bootdoConfig");
		this.teamDevelopmentImage = CpeCommonUtils.getImages(this.teamDevelopment,bootdoConfig.getImgUrlPre());



	}
}
