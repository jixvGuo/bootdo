package com.bootdo.cpe.domain;

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
 * 团队标志性成果
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-09-17 22:54:16
 */
public class EnterpriTeamAcheievementsDO implements Serializable {
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
    //成果名称
    private String achName;
    //团队主要成员中的完成人/排名
    private String achUsers;
    private String achUsersNo;
    private List<QCImageObj> achUsersNoImage = new ArrayList<>();

    public String getAchUsersNo() {
        return achUsersNo;
    }

    public void setAchUsersNo(String achUsersNo) {
        this.achUsersNo = achUsersNo;
    }

    public List<QCImageObj> getAchUsersNoImage() {
        return achUsersNoImage;
    }

    public void setAchUsersNoImage(List<QCImageObj> achUsersNoImage) {
        this.achUsersNoImage = achUsersNoImage;
    }

    //研发开始日期
    private String achDateStart;
    //研发结束日期
    private String achDateEnd;
    //正式应用(公开发表)日期
    private String achUseDate;
    //依托计划名称和编号(不超过3项)
    private String achPlan;
    private String achPlanNo;
    private List<QCImageObj> achPlanNoImage = new ArrayList<>();


    //证明材料编号
    private String achEvidenceCode;
    //记录生成的日期
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
     * 获取：成果名称
     */
    public String getAchName() {
        return achName;
    }

    /**
     * 设置：成果名称
     */
    public void setAchName(String achName) {
        this.achName = achName;
    }

    /**
     * 获取：团队主要成员中的完成人/排名
     */
    public String getAchUsers() {
        return achUsers;
    }

    /**
     * 设置：团队主要成员中的完成人/排名
     */
    public void setAchUsers(String achUsers) {
        this.achUsers = achUsers;

        this.achUsersNo = this.achUsers.replaceAll("\\<.*?>","");
        BootdoConfig bootdoConfig = getBean("bootdoConfig");
        //this.achUsersNoImage = CpeCommonUtils.getImages(this.achUsers,bootdoConfig.getImgUrlPre());



    }

    /**
     * 获取：研发开始日期
     */
    public String getAchDateStart() {
        return achDateStart;
    }

    /**
     * 设置：研发开始日期
     */
    public void setAchDateStart(String achDateStart) {
        if(StringUtils.isNotBlank(achDateStart)) {
            String[] arr = achDateStart.split(" ");
            achDateStart = arr[0];
        }
        this.achDateStart = achDateStart;
    }

    /**
     * 获取：研发结束日期
     */
    public String getAchDateEnd() {
        return achDateEnd;
    }

    /**
     * 设置：研发结束日期
     */
    public void setAchDateEnd(String achDateEnd) {
        if(StringUtils.isNotBlank(achDateEnd)) {
            String[] arr = achDateEnd.split(" ");
            achDateEnd = arr[0];
        }
        this.achDateEnd = achDateEnd;
    }

    /**
     * 获取：正式应用(公开发表)日期
     */
    public String getAchUseDate() {
        return achUseDate;
    }

    /**
     * 设置：正式应用(公开发表)日期
     */
    public void setAchUseDate(String achUseDate) {
        if(StringUtils.isNotBlank(achUseDate)) {
            String[] arr = achUseDate.split(" ");
            achUseDate = arr[0];
        }
        this.achUseDate = achUseDate;
    }

    /**
     * 获取：依托计划名称和编号(不超过3项)
     */
    public String getAchPlan() {
        return achPlan;
    }

    /**
     * 设置：依托计划名称和编号(不超过3项)
     */
    public void setAchPlan(String achPlan) {
        this.achPlan = achPlan;
        this.achPlanNo = this.achPlan.replaceAll("\\<.*?>","");
        BootdoConfig bootdoConfig = getBean("bootdoConfig");
        //this.achPlanNoImage = CpeCommonUtils.getImages(this.achPlan,bootdoConfig.getImgUrlPre());
    }

    public String getAchPlanNo() {
        return achPlanNo;
    }

    public void setAchPlanNo(String achPlanNo) {
        this.achPlanNo = achPlanNo;
    }

    public List<QCImageObj> getAchPlanNoImage() {
        return achPlanNoImage;
    }

    public void setAchPlanNoImage(List<QCImageObj> achPlanNoImage) {
        this.achPlanNoImage = achPlanNoImage;
    }

    /**
     * 获取：证明材料编号
     */
    public String getAchEvidenceCode() {
        return achEvidenceCode;
    }

    /**
     * 设置：证明材料编号
     */
    public void setAchEvidenceCode(String achEvidenceCode) {
        this.achEvidenceCode = achEvidenceCode;
    }

    /**
     * 获取：记录生成的日期
     */
    public Date getCreated() {
        return created;
    }

    /**
     * 设置：记录生成的日期
     */
    public void setCreated(Date created) {
        this.created = created;
    }
}
