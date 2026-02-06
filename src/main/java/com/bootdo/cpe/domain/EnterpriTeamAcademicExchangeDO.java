package com.bootdo.cpe.domain;

import com.bootdo.common.utils.StringUtils;

import java.io.Serializable;
import java.util.Date;


/**
 * 团队学术交流
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-09-19 01:31:08
 */
public class EnterpriTeamAcademicExchangeDO implements Serializable {
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
    //会议名称
    private String meetingname;
    //时间
    private String meetingtime;
    //地点
    private String meetingplace;
    //主办单位
    private String hostcompany;
    //报告人
    private String reportname;
    //报告题目
    private String reporttitle;
    //证明材料编号
    private String materialcode;
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
     * 获取：会议名称
     */
    public String getMeetingname() {
        return meetingname;
    }

    /**
     * 设置：会议名称
     */
    public void setMeetingname(String meetingname) {
        this.meetingname = meetingname;
    }

    /**
     * 获取：时间
     */
    public String getMeetingtime() {
        return meetingtime;
    }

    /**
     * 设置：时间
     */
    public void setMeetingtime(String meetingtime) {
        if(StringUtils.isNotBlank(meetingtime)) {
            String[] arr = meetingtime.split(" ");
            meetingtime = arr[0];
        }
        this.meetingtime = meetingtime;
    }

    /**
     * 获取：地点
     */
    public String getMeetingplace() {
        return meetingplace;
    }

    /**
     * 设置：地点
     */
    public void setMeetingplace(String meetingplace) {
        this.meetingplace = meetingplace;
    }

    /**
     * 获取：主办单位
     */
    public String getHostcompany() {
        return hostcompany;
    }

    /**
     * 设置：主办单位
     */
    public void setHostcompany(String hostcompany) {
        this.hostcompany = hostcompany;
    }

    /**
     * 获取：报告人
     */
    public String getReportname() {
        return reportname;
    }

    /**
     * 设置：报告人
     */
    public void setReportname(String reportname) {
        this.reportname = reportname;
    }

    /**
     * 获取：报告题目
     */
    public String getReporttitle() {
        return reporttitle;
    }

    /**
     * 设置：报告题目
     */
    public void setReporttitle(String reporttitle) {
        this.reporttitle = reporttitle;
    }

    /**
     * 获取：证明材料编号
     */
    public String getMaterialcode() {
        return materialcode;
    }

    /**
     * 设置：证明材料编号
     */
    public void setMaterialcode(String materialcode) {
        this.materialcode = materialcode;
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
