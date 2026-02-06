package com.bootdo.cpe.domain;

import com.bootdo.cpe.dto.QcBaseProjectInfoDO;
import com.bootdo.cpe.utils.AwardSurverSubTypeEnum;

/**
 * 勘察奖项目信息
 *
 * @author houzb
 * @version 1.0
 * @date 2022-03-28 22:32
 */
public class TechnologyProjectInfo extends QcBaseProjectInfoDO {
    private int id;
    //项目详情id
    private int proDetailId;
    private String proCode;
    private String proCodeNum;
    /**
     * 项目id
     */
    private int proId;
    /**
     * 项目编号
     */
    private String proNum;
    /**
     * 项目类型
     */
    private String proType;
    /**
     * 项目类型字符串
     */
    private String proTypeStr;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProDetailId() {
        return proDetailId;
    }

    public void setProDetailId(int proDetailId) {
        this.proDetailId = proDetailId;
    }

    public String getProCode() {
        return proCode;
    }

    public void setProCode(String proCode) {
        this.proCode = proCode;
    }

    public String getProCodeNum() {
        return proCodeNum;
    }

    public void setProCodeNum(String proCodeNum) {
        this.proCodeNum = proCodeNum;
    }

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
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
        EnumProjectType subTypeEnum = EnumProjectType.getTypeEnum(this.proType);
        this.proTypeStr = subTypeEnum != null ? subTypeEnum.getDesc() : "暂无";
    }

    public String getProTypeStr() {
        return proTypeStr;
    }

    public void setProTypeStr(String proTypeStr) {
        this.proTypeStr = proTypeStr;
    }

    public String getProSubType() {
        return proSubType;
    }

    public void setProSubType(String proSubType) {
        this.proSubType = proSubType;
        EnumProjectType subTypeEnum = EnumProjectType.getTypeEnum(this.proType);
        this.proSubTypeStr = subTypeEnum != null ? subTypeEnum.getDesc() : "暂无";
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
