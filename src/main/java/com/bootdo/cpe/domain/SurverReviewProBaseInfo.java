package com.bootdo.cpe.domain;

/**
 * 勘察审查的项目基本信息
 *
 * @author houzb
 * @version 1.0
 * @date 2022-04-13 7:20
 */
public class SurverReviewProBaseInfo {
    /**
     * 项目编号
     */
    private String proCode;
    /**
     * 项目id
     */
    private Integer proId;
    /**
     * 项目编号
     */
    private String proNum;
    /**
     * 项目类型
     */
    private String proType;
    /**
     * 项目子类型
     */
    private String proSubType;
    /**
     * 子类型名称
     */
    private String proSubTypeStr;
    /**
     * 项目名称
     */
    private String proName;
    /**
     * 申请单位
     */
    private String applyCompany;
    /**
     * 专业
     */
    private String major;

    public String getProCode() {
        return proCode;
    }

    public void setProCode(String proCode) {
        this.proCode = proCode;
    }

    public Integer getProId() {
        return proId;
    }

    public void setProId(Integer proId) {
        this.proId = proId;
    }

    public String getProNum() {
        return proNum;
    }

    public void setProNum(String proNum) {
        this.proNum = proNum;
    }

    public String getProType() {
        return proType;
    }

    public void setProType(String proType) {
        this.proType = proType;
    }

    public String getProSubType() {
        return proSubType;
    }

    public void setProSubType(String proSubType) {
        this.proSubType = proSubType;
    }

    public String getProSubTypeStr() {
        return proSubTypeStr;
    }

    public void setProSubTypeStr(String proSubTypeStr) {
        this.proSubTypeStr = proSubTypeStr;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getApplyCompany() {
        return applyCompany;
    }

    public void setApplyCompany(String applyCompany) {
        this.applyCompany = applyCompany;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}
