package com.bootdo.cpe.domain;

import com.bootdo.common.utils.DateUtils;

import java.io.Serializable;
import java.util.Date;


/**
 * 团队曾获科技奖励情况
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-09-19 17:23:35
 */
public class EnterpriTeamGetTechAwardDO implements Serializable {
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
    //获奖对象
    private String awardspeople;
    //获奖时间
    private Date awardstime;
    //奖项名称
    private String prizename;
    //奖项等级
    private String prizelevel;
    //授权部门(单位)
    private String empowerdepa;
    //团队主要成员完成人/排名
    private String mainfulfilhumname;
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
     * 获取：获奖对象
     */
    public String getAwardspeople() {
        return awardspeople;
    }

    /**
     * 设置：获奖对象
     */
    public void setAwardspeople(String awardspeople) {
        this.awardspeople = awardspeople;
    }

    /**
     * 获取：获奖时间
     */
    public String getAwardstime() {
        if (awardstime != null) {
            return DateUtils.format(awardstime, DateUtils.DATE_PATTERN);
        }
        return "";
    }

    /**
     * 设置：获奖时间
     */
    public void setAwardstime(Date awardstime) {
        this.awardstime = awardstime;
    }

    /**
     * 获取：奖项名称
     */
    public String getPrizename() {
        return prizename;
    }

    /**
     * 设置：奖项名称
     */
    public void setPrizename(String prizename) {
        this.prizename = prizename;
    }

    /**
     * 获取：奖项等级
     */
    public String getPrizelevel() {
        return prizelevel;
    }

    /**
     * 设置：奖项等级
     */
    public void setPrizelevel(String prizelevel) {
        this.prizelevel = prizelevel;
    }

    /**
     * 获取：授权部门(单位)
     */
    public String getEmpowerdepa() {
        return empowerdepa;
    }

    /**
     * 设置：授权部门(单位)
     */
    public void setEmpowerdepa(String empowerdepa) {
        this.empowerdepa = empowerdepa;
    }

    /**
     * 获取：团队主要成员完成人/排名
     */
    public String getMainfulfilhumname() {
        return mainfulfilhumname;
    }

    /**
     * 设置：团队主要成员完成人/排名
     */
    public void setMainfulfilhumname(String mainfulfilhumname) {
        this.mainfulfilhumname = mainfulfilhumname;
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
