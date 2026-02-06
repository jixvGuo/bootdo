package com.bootdo.cpe.domain;

import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.Date;


/**
 * 所获知识产权和技术标准情况
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-09-19 09:29:22
 */
public class EnterpriTeamIntellectualPropertyDO implements Serializable {
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
    //知识产权类别
    private String propertytype;
    //授权项目名称
    private String propertyname;
    //国(区)别
    private String country;
    //授权号
    private String authorizationnumber;
    //授权日期
    private String authorizationtime;
    //证书编号
    private String certificatecode;
    //权利人
    private String obligee;
    //发明人
    private String inventor;
    //所对应标志性成果
    private String keynotegain;
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
     * 获取：知识产权类别
     */
    public String getPropertytype() {
        return propertytype;
    }

    /**
     * 设置：知识产权类别
     */
    public void setPropertytype(String propertytype) {
        this.propertytype = propertytype;
    }

    /**
     * 获取：授权项目名称
     */
    public String getPropertyname() {
        return propertyname;
    }

    /**
     * 设置：授权项目名称
     */
    public void setPropertyname(String propertyname) {
        this.propertyname = propertyname;
    }

    /**
     * 获取：国(区)别
     */
    public String getCountry() {
        return country;
    }

    /**
     * 设置：国(区)别
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * 获取：授权号
     */
    public String getAuthorizationnumber() {
        return authorizationnumber;
    }

    /**
     * 设置：授权号
     */
    public void setAuthorizationnumber(String authorizationnumber) {
        this.authorizationnumber = authorizationnumber;
    }

    /**
     * 获取：授权日期
     */
    public String getAuthorizationtime() {
        return authorizationtime;
    }

    /**
     * 设置：授权日期
     */
    public void setAuthorizationtime(String authorizationtime) {
        if(StringUtils.isNotBlank(authorizationtime)) {
            String[] arr = authorizationtime.split(" ");
            authorizationtime = arr[0];
        }
        this.authorizationtime = authorizationtime;
    }

    /**
     * 获取：证书编号
     */
    public String getCertificatecode() {
        return certificatecode;
    }

    /**
     * 设置：证书编号
     */
    public void setCertificatecode(String certificatecode) {
        this.certificatecode = certificatecode;
    }

    /**
     * 获取：权利人
     */
    public String getObligee() {
        return obligee;
    }

    /**
     * 设置：权利人
     */
    public void setObligee(String obligee) {
        this.obligee = obligee;
    }

    /**
     * 获取：发明人
     */
    public String getInventor() {
        return inventor;
    }

    /**
     * 设置：发明人
     */
    public void setInventor(String inventor) {
        this.inventor = inventor;
    }

    /**
     * 获取：所对应标志性成果
     */
    public String getKeynotegain() {
        return keynotegain;
    }

    /**
     * 设置：所对应标志性成果
     */
    public void setKeynotegain(String keynotegain) {
        this.keynotegain = keynotegain;
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
