package com.bootdo.cpe.domain;

import com.bootdo.common.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;


/**
 * 团队承担项目情况
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-09-19 15:42:06
 */
public class EnterpriTeamProjectInfoDO implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id = 0;
    //
    private Integer proId;
    //
    private String taskId;
    //
    private String applyId;
    //
    private Long optUid;
    //项目名称
    private String proname;
    //研究经费(万元)
    private String funds;
    //项目来源
    private String prosource;
    //项目编号
    private String procode;
    //研发开始时间
    private Date starttime;
    //研发结束时间
    private Date endtime;
    //状态
    private String state;
    //负责人
    private String responsibilityname;
    //本团队主要成员中参与人/排序
    private String mainmember;
    //证明材料编号/已验收
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
     * 获取：项目名称
     */
    public String getProname() {
        return proname;
    }

    /**
     * 设置：项目名称
     */
    public void setProname(String proname) {
        this.proname = proname;
    }

    /**
     * 获取：研究经费(万元)
     */
    public String getFunds() {
        return funds;
    }

    /**
     * 设置：研究经费(万元)
     */
    public void setFunds(String funds) {
        this.funds = funds;
    }

    /**
     * 获取：项目来源
     */
    public String getProsource() {

//        this.prosource = this.prosource.replaceAll("\\<.*?>","");
        return prosource;
    }

    /**
     * 设置：项目来源
     */
    public void setProsource(String prosource) {
        this.prosource = prosource;
    }

    /**
     * 获取：项目编号
     */
    public String getProcode() {
        return procode;
    }

    /**
     * 设置：项目编号
     */
    public void setProcode(String procode) {
        this.procode = procode;
    }

    /**
     * 获取：研发开始时间
     */
    public String getStarttime() {
        if (starttime != null){
           return DateUtils.format(starttime,DateUtils.DATE_PATTERN);
        }
        return "";
    }

    /**
     * 设置：研发开始时间
     */
    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    /**
     * 获取：研发结束时间
     */
    public String getEndtime() {
        if (endtime != null){
            return  DateUtils.format(endtime,DateUtils.DATE_PATTERN);
        }
        return "";
    }

    /**
     * 设置：研发结束时间
     */
    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    /**
     * 获取：状态
     */
    public String getState() {
        return state;
    }

    /**
     * 设置：状态
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * 获取：负责人
     */
    public String getResponsibilityname() {
        return responsibilityname;
    }

    /**
     * 设置：负责人
     */
    public void setResponsibilityname(String responsibilityname) {
        this.responsibilityname = responsibilityname;
    }

    /**
     * 获取：本团队主要成员中参与人/排序
     */
    public String getMainmember() {
        return mainmember;
    }

    /**
     * 设置：本团队主要成员中参与人/排序
     */
    public void setMainmember(String mainmember) {
        this.mainmember = mainmember;
    }

    /**
     * 获取：证明材料编号/已验收
     */
    public String getMaterialcode() {
        return materialcode;
    }

    /**
     * 设置：证明材料编号/已验收
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
