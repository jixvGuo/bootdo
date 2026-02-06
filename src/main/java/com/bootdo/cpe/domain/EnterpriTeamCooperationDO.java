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
 * 团队合作情况汇总表
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-09-19 20:08:27
 */
public class EnterpriTeamCooperationDO implements Serializable {
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
    //类型
    private String type;
    //成果名称
    private String achievementsname;

    private String achievementsnameNo;
    private List<QCImageObj> achievementsnameImage = new ArrayList<>();

    public String getAchievementsnameNo() {
        return achievementsnameNo;
    }

    public void setAchievementsnameNo(String achievementsnameNo) {

        this.achievementsnameNo = this.achievementsname.replaceAll("\\<.*?>","");


    }

    //本团队合作者/排序
    private String cooperationpeople;

    private String cooperationpeopleNo;
    private List<QCImageObj> cooperationpeopleImage = new ArrayList<>();


    //备注
    private String remarks;
    private String remarksNo;
    private List<QCImageObj> remarksImage = new ArrayList<>();



    //创建日期
    private Date created;

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
     * 获取：类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置：类型
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取：成果名称
     */
    public String getAchievementsname() {

        if(this.achievementsname == null) {
            return "";
        }
        this.achievementsname = this.achievementsname.replaceAll("\\<.*?>","");


        return achievementsname;
    }




    public List<QCImageObj> getAchievementsnameImage() {
        return achievementsnameImage;
    }

    public void setAchievementsnameImage(List<QCImageObj> achievementsnameImage) {
        this.achievementsnameImage = achievementsnameImage;
    }

    /**
     * 设置：成果名称
     */
    public void setAchievementsname(String achievementsname) {
        this.achievementsname = achievementsname;

        this.achievementsnameNo = this.achievementsname.replaceAll("\\<.*?>","");
        BootdoConfig bootdoConfig = getBean("bootdoConfig");
        //this.achievementsnameImage = CpeCommonUtils.getImages(this.achievementsname,bootdoConfig.getImgUrlPre());



    }

    /**
     * 获取：本团队合作者/排序
     */
    public String getCooperationpeople() {
//        if(this.cooperationpeople == null) {
//            return "";
//        }
//        this.cooperationpeople = this.cooperationpeople.replaceAll("\\<.*?>","");
        return cooperationpeople;
    }

    /**
     * 设置：本团队合作者/排序
     */
    public void setCooperationpeople(String cooperationpeople) {
        this.cooperationpeople = cooperationpeople;
        this.cooperationpeopleNo = this.cooperationpeople.replaceAll("\\<.*?>","");
        BootdoConfig bootdoConfig = getBean("bootdoConfig");
        //this.cooperationpeopleImage = CpeCommonUtils.getImages(this.cooperationpeople,bootdoConfig.getImgUrlPre());


    }

    public String getCooperationpeopleNo() {
        return cooperationpeopleNo;
    }

    public void setCooperationpeopleNo(String cooperationpeopleNo) {
        this.cooperationpeopleNo = cooperationpeopleNo;
    }

    public List<QCImageObj> getCooperationpeopleImage() {
        return cooperationpeopleImage;
    }

    public void setCooperationpeopleImage(List<QCImageObj> cooperationpeopleImage) {
        this.cooperationpeopleImage = cooperationpeopleImage;
    }

    public String getRemarksNo() {
        return remarksNo;
    }

    public void setRemarksNo(String remarksNo) {
        this.remarksNo = remarksNo;
    }

    public List<QCImageObj> getRemarksImage() {
        return remarksImage;
    }

    public void setRemarksImage(List<QCImageObj> remarksImage) {
        this.remarksImage = remarksImage;
    }

    /**
     * 获取：备注
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置：备注
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
        this.remarksNo = this.remarks.replaceAll("\\<.*?>","");
        BootdoConfig bootdoConfig = getBean("bootdoConfig");
        //this.remarksImage = CpeCommonUtils.getImages(this.remarks,bootdoConfig.getImgUrlPre());
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
