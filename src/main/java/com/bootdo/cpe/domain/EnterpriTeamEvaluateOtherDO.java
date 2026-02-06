package com.bootdo.cpe.domain;

import java.io.Serializable;
import java.util.Date;


/**
 * 团队第三方评价
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-09-19 16:46:09
 */
public class EnterpriTeamEvaluateOtherDO implements Serializable {
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
    //评价方
    private String activeevaluate;
    //被评价方
    private String passiveevaluate;
    //评价要点
    private String evaluatemainpoints;
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
     * 获取：评价方
     */
    public String getActiveevaluate() {
        return activeevaluate;
    }

    /**
     * 设置：评价方
     */
    public void setActiveevaluate(String activeevaluate) {
        this.activeevaluate = activeevaluate;
    }

    /**
     * 获取：被评价方
     */
    public String getPassiveevaluate() {
        return passiveevaluate;
    }

    /**
     * 设置：被评价方
     */
    public void setPassiveevaluate(String passiveevaluate) {
        this.passiveevaluate = passiveevaluate;
    }

    /**
     * 获取：评价要点
     */
    public String getEvaluatemainpoints() {

//        if(this.evaluatemainpoints == null) {
//            return "";
//        }
//        this.evaluatemainpoints = this.evaluatemainpoints.replaceAll("\\<.*?>","");

        return evaluatemainpoints;
    }

    /**
     * 设置：评价要点
     */
    public void setEvaluatemainpoints(String evaluatemainpoints) {
        this.evaluatemainpoints = evaluatemainpoints;
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
