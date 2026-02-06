package com.bootdo.cpe.domain;

import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.cpe.petroleum_engineering_award.domain.QCImageObj;
import com.bootdo.cpe.utils.CpeCommonUtils;
import com.bootdo.cpe.utils.DateUtil;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.bootdo.common.utils.SpringContextHolder.getBean;


/**
 * 主要成员情况带头人
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-09-19 21:13:08
 */
public class EnterpriTeamLeaderInfoDO implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;
    //
    private Integer proId;
    //
    private String taskId;
    //
    private String applyId;
    //
    private Long optUid;
    //姓名
    private String name;
    //性别
    private String gender;
    //序号
    private String serialnumber;
    private int serialnumberInt;
    //出生年月
    private Date birthtime;
    //出生地
    private String birthaddress;
    //民族
    private String nation;
    //身份证号
    private String identitynumber;
    //是否归国人员
    private String whetherreturntochina;
    //归国时间
    private Date returnhometime;
    //技术职称
    private String technologytitle;
    //最高学历
    private String education;
    //最高学位
    private String academicdegree;
    //毕业学校
    private String graduateschool;
    //毕业时间
    private Date graduationtime;

    private String getWorkYear;

    //所学专业
    private String major;
    //电子邮箱
    private String mailbox;
    //办公电话
    private String telephone;
    //移动电话
    private String mobilephone;
    //通讯地址
    private String contactaddress;
    //邮政编码
    private String postcode;
    //工作单位
    private String workcompany;
    //行政职务
    private String administrationpost;
    //二级单位
    private String secondlevelcompany;
    //党派
    private String parties;
    //参加本团队的开始时间
    private Date attendstarttime;

    //参加本团队的结束时间
    private Date attendendtime;
    //主要学习经历
    private String educationundergo;
    private String educationundergoNo;
    private List<QCImageObj> educationundoImage = new ArrayList<>();

    public String getEducationundergoNo() {
        return educationundergoNo;
    }

    public void setEducationundergoNo(String educationundergoNo) {
        this.educationundergoNo = educationundergoNo;
    }

    public List<QCImageObj> getEducationundoImage() {
        return educationundoImage;
    }

    public void setEducationundoImage(List<QCImageObj> educationundoImage) {
        this.educationundoImage = educationundoImage;


    }

    //科研工作经历
    private String workundergo;
    private String workundergoNo;
    private List<QCImageObj> workundergoImage = new ArrayList<>();


    //主要学术兼职
    private String learningconcurrent;
    private String learningconcurrentNo;
    private List<QCImageObj> learningconcurrentImage = new ArrayList<>();


    //代表性成果
    private String deputyfruit;

    private String deputyfruitNo;
    private List<QCImageObj> deputyfruitNoImage = new ArrayList<>();

    //代表性论文和著作获奖励情况
    private String rewardsituation;

    private String rewardsituationNo;
    private List<QCImageObj> rewardsituationImage = new ArrayList<>();

    //对团队发展的贡献
    private String contribution;
    private String contributionNo;
    private List<QCImageObj> contributionImage = new ArrayList<>();


    //创建日期
    private Date created;

    public String getGetWorkYear() {
        if (attendendtime == null || attendstarttime == null) {
            return "";
        }
        int days = DateUtil.differentDays(attendendtime, attendstarttime);

        double f = (float) days / (float) 365;
        BigDecimal b = new BigDecimal(f);
        double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return f1 + "";
    }

    public void setGetWorkYear(String getWorkYear) {
        this.getWorkYear = getWorkYear;
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
    public void setId(Integer id) {
        this.id = id;
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
    public void setProId(Integer proId) {
        this.proId = proId;
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
    public void setTaskId(String taskId) {
        this.taskId = taskId;
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
    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    /**
     * 获取：
     */
    public Long getOptUid() {
        return optUid;
    }

    /**
     * 设置：
     */
    public void setOptUid(Long optUid) {
        this.optUid = optUid;
    }

    /**
     * 获取：姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置：姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：性别
     */
    public String getGender() {
        return gender;
    }

    /**
     * 设置：性别
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * 获取：序号
     */
    public String getSerialnumber() {
       return this.serialnumber;
    }

    public String getSerialnumberStr() {
        return this.serialnumber;
    }

    /**
     * 设置：序号
     */
    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber;
        if(StringUtils.isNotBlank(this.serialnumber) && StringUtils.isNumeric(this.serialnumber.trim())) {
            this.serialnumberInt = Integer.parseInt(this.serialnumber.trim());
        }
    }


    public int getSerialnumberInt() {
        return serialnumberInt;
    }

    public void setSerialnumberInt(int serialnumberInt) {
        this.serialnumberInt = serialnumberInt;
    }

    /**
     * 获取：出生年月
     */
    public Date getBirthtime() {
        return birthtime;
    }

    /**
     * 设置：出生年月
     */
    public void setBirthtime(Date birthtime) {
        this.birthtime = birthtime;
    }

    /**
     * 获取：出生地
     */
    public String getBirthaddress() {
        return birthaddress;
    }

    /**
     * 设置：出生地
     */
    public void setBirthaddress(String birthaddress) {
        this.birthaddress = birthaddress;
    }

    /**
     * 获取：民族
     */
    public String getNation() {
        return nation;
    }

    /**
     * 设置：民族
     */
    public void setNation(String nation) {
        this.nation = nation;
    }

    /**
     * 获取：身份证号
     */
    public String getIdentitynumber() {
        return identitynumber;
    }

    /**
     * 设置：身份证号
     */
    public void setIdentitynumber(String identitynumber) {
        this.identitynumber = identitynumber;
    }

    /**
     * 获取：是否归国人员
     */
    public String getWhetherreturntochina() {
        return whetherreturntochina;
    }

    /**
     * 设置：是否归国人员
     */
    public void setWhetherreturntochina(String whetherreturntochina) {
        this.whetherreturntochina = whetherreturntochina;
    }

    /**
     * 获取：归国时间
     */
    public Date getReturnhometime() {
        return returnhometime;
    }

    /**
     * 设置：归国时间
     */
    public void setReturnhometime(Date returnhometime) {
        this.returnhometime = returnhometime;
    }

    /**
     * 获取：技术职称
     */
    public String getTechnologytitle() {
        return technologytitle;
    }

    /**
     * 设置：技术职称
     */
    public void setTechnologytitle(String technologytitle) {
        this.technologytitle = technologytitle;
    }

    /**
     * 获取：最高学历
     */
    public String getEducation() {
        return education;
    }

    /**
     * 设置：最高学历
     */
    public void setEducation(String education) {
        this.education = education;
    }

    /**
     * 获取：最高学位
     */
    public String getAcademicdegree() {
        return academicdegree;
    }

    /**
     * 设置：最高学位
     */
    public void setAcademicdegree(String academicdegree) {
        this.academicdegree = academicdegree;
    }

    /**
     * 获取：毕业学校
     */
    public String getGraduateschool() {
        return graduateschool;
    }

    /**
     * 设置：毕业学校
     */
    public void setGraduateschool(String graduateschool) {
        this.graduateschool = graduateschool;
    }

    /**
     * 获取：毕业时间
     */
    public Date getGraduationtime() {
        return graduationtime;
    }

    /**
     * 设置：毕业时间
     */
    public void setGraduationtime(Date graduationtime) {
        this.graduationtime = graduationtime;
    }

    /**
     * 获取：所学专业
     */
    public String getMajor() {
        return major;
    }

    /**
     * 设置：所学专业
     */
    public void setMajor(String major) {
        this.major = major;
    }

    /**
     * 获取：电子邮箱
     */
    public String getMailbox() {
        return mailbox;
    }

    /**
     * 设置：电子邮箱
     */
    public void setMailbox(String mailbox) {
        this.mailbox = mailbox;
    }

    /**
     * 获取：办公电话
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * 设置：办公电话
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * 获取：移动电话
     */
    public String getMobilephone() {
        return mobilephone;
    }

    /**
     * 设置：移动电话
     */
    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    /**
     * 获取：通讯地址
     */
    public String getContactaddress() {
        return contactaddress;
    }

    /**
     * 设置：通讯地址
     */
    public void setContactaddress(String contactaddress) {
        this.contactaddress = contactaddress;
    }

    /**
     * 获取：邮政编码
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * 设置：邮政编码
     */
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    /**
     * 获取：工作单位
     */
    public String getWorkcompany() {
        return workcompany;
    }

    /**
     * 设置：工作单位
     */
    public void setWorkcompany(String workcompany) {
        this.workcompany = workcompany;
    }

    /**
     * 获取：行政职务
     */
    public String getAdministrationpost() {
        return administrationpost;
    }

    /**
     * 设置：行政职务
     */
    public void setAdministrationpost(String administrationpost) {
        this.administrationpost = administrationpost;
    }

    /**
     * 获取：二级单位
     */
    public String getSecondlevelcompany() {
        return secondlevelcompany;
    }

    /**
     * 设置：二级单位
     */
    public void setSecondlevelcompany(String secondlevelcompany) {
        this.secondlevelcompany = secondlevelcompany;
    }

    /**
     * 获取：党派
     */
    public String getParties() {
        return parties;
    }

    /**
     * 设置：党派
     */
    public void setParties(String parties) {
        this.parties = parties;
    }

    /**
     * 获取：参加本团队的开始时间
     */
    public Date getAttendstarttime() {
        return attendstarttime;
    }

    /**
     * 设置：参加本团队的开始时间
     */
    public void setAttendstarttime(Date attendstarttime) {
        this.attendstarttime = attendstarttime;
    }

    /**
     * 获取：参加本团队的结束时间
     */
    public Date getAttendendtime() {
        return attendendtime;
    }

    /**
     * 设置：参加本团队的结束时间
     */
    public void setAttendendtime(Date attendendtime) {
        this.attendendtime = attendendtime;
    }

    /**
     * 获取：主要学习经历
     */
    public String getEducationundergo() {
        return educationundergo;
    }

    /**
     * 设置：主要学习经历
     */
    public void setEducationundergo(String educationundergo) {
        this.educationundergo = educationundergo;
        this.educationundergoNo = this.educationundergo.replaceAll("\\<.*?>","");
        BootdoConfig bootdoConfig = getBean("bootdoConfig");
        //this.educationundoImage = CpeCommonUtils.getImages(this.educationundergo,bootdoConfig.getImgUrlPre());

    }

    /**
     * 获取：科研工作经历
     */
    public String getWorkundergo() {
        return workundergo;
    }

    /**
     * 设置：科研工作经历
     */
    public void setWorkundergo(String workundergo) {
        this.workundergo = workundergo;

        this.workundergoNo = this.workundergo.replaceAll("\\<.*?>","");
        BootdoConfig bootdoConfig = getBean("bootdoConfig");
       // this.workundergoImage = CpeCommonUtils.getImages(this.workundergo,bootdoConfig.getImgUrlPre());




    }

    /**
     * 获取：主要学术兼职
     */
    public String getLearningconcurrent() {
        return learningconcurrent;
    }

    /**
     * 设置：主要学术兼职
     */
    public void setLearningconcurrent(String learningconcurrent) {
        this.learningconcurrent = learningconcurrent;

        this.learningconcurrentNo = this.learningconcurrent.replaceAll("\\<.*?>","");
        BootdoConfig bootdoConfig = getBean("bootdoConfig");
        //this.learningconcurrentImage = CpeCommonUtils.getImages(this.learningconcurrent,bootdoConfig.getImgUrlPre());



    }

    /**
     * 获取：代表性成果
     */
    public String getDeputyfruit() {
        return deputyfruit;
    }

    /**
     * 设置：代表性成果
     */
    public void setDeputyfruit(String deputyfruit) {
        this.deputyfruit = deputyfruit;

        this.deputyfruitNo = this.deputyfruit.replaceAll("\\<.*?>","");
        BootdoConfig bootdoConfig = getBean("bootdoConfig");
        //this.deputyfruitNoImage = CpeCommonUtils.getImages(this.deputyfruit,bootdoConfig.getImgUrlPre());




    }

    /**
     * 获取：代表性论文和著作获奖励情况
     */
    public String getRewardsituation() {
        return rewardsituation;
    }

    /**
     * 设置：代表性论文和著作获奖励情况
     */
    public void setRewardsituation(String rewardsituation) {
        this.rewardsituation = rewardsituation;

        this.rewardsituationNo = this.rewardsituation.replaceAll("\\<.*?>","");
        BootdoConfig bootdoConfig = getBean("bootdoConfig");
        //this.rewardsituationImage = CpeCommonUtils.getImages(this.rewardsituation,bootdoConfig.getImgUrlPre());



    }

    /**
     * 获取：对团队发展的贡献
     */
    public String getContribution() {
        return contribution;
    }

    public String getWorkundergoNo() {
        return workundergoNo;
    }

    public void setWorkundergoNo(String workundergoNo) {
        this.workundergoNo = workundergoNo;
    }

    public List<QCImageObj> getWorkundergoImage() {
        return workundergoImage;
    }

    public void setWorkundergoImage(List<QCImageObj> workundergoImage) {
        this.workundergoImage = workundergoImage;
    }

    public String getLearningconcurrentNo() {
        return learningconcurrentNo;
    }

    public void setLearningconcurrentNo(String learningconcurrentNo) {
        this.learningconcurrentNo = learningconcurrentNo;
    }

    public List<QCImageObj> getLearningconcurrentImage() {
        return learningconcurrentImage;
    }

    public void setLearningconcurrentImage(List<QCImageObj> learningconcurrentImage) {
        this.learningconcurrentImage = learningconcurrentImage;
    }

    public String getDeputyfruitNo() {
        return deputyfruitNo;
    }

    public void setDeputyfruitNo(String deputyfruitNo) {
        this.deputyfruitNo = deputyfruitNo;
    }

    public List<QCImageObj> getDeputyfruitNoImage() {
        return deputyfruitNoImage;
    }

    public void setDeputyfruitNoImage(List<QCImageObj> deputyfruitNoImage) {
        this.deputyfruitNoImage = deputyfruitNoImage;
    }

    public String getRewardsituationNo() {
        return rewardsituationNo;
    }

    public void setRewardsituationNo(String rewardsituationNo) {
        this.rewardsituationNo = rewardsituationNo;
    }

    public List<QCImageObj> getRewardsituationImage() {
        return rewardsituationImage;
    }

    public void setRewardsituationImage(List<QCImageObj> rewardsituationImage) {
        this.rewardsituationImage = rewardsituationImage;
    }

    public String getContributionNo() {
        return contributionNo;
    }

    public void setContributionNo(String contributionNo) {
        this.contributionNo = contributionNo;
    }

    public List<QCImageObj> getContributionImage() {
        return contributionImage;
    }

    public void setContributionImage(List<QCImageObj> contributionImage) {
        this.contributionImage = contributionImage;
    }

    /**
     * 设置：对团队发展的贡献
     */
    public void setContribution(String contribution) {
        this.contribution = contribution;

        this.contributionNo = this.contribution.replaceAll("\\<.*?>","");
        BootdoConfig bootdoConfig = getBean("bootdoConfig");
        //this.contributionImage = CpeCommonUtils.getImages(this.contribution,bootdoConfig.getImgUrlPre());




    }

    /**
     * 获取：创建日期
     */
    public Date getCreated() {
        return created;
    }

    /**
     * 设置：创建日期
     */
    public void setCreated(Date created) {
        this.created = created;
    }
}
