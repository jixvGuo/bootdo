package com.bootdo.cpe.petroleum_engineering_award.domain;

/**
 * 石油安装工程信息
 *
 * @author houzb
 * @version 1.0
 * @date 2021-03-12 7:10
 */
public class OilProInstallBaseInfoDO extends OilProBaseInfoDO {
    private int proId;
    /**
     * 项目名称
     */
    private String proName;
    /**
     * 单位名称
     */
    private String proUnitName;
    /**
     * 申报日期
     */
    private String created;
    /**
     * 申报的企业id
     */
    private int companyId;
    /**
     * 申报的工程概况id
     */
    private int situationId;
    /**
     * 是否存在单位意见
     */
    private int unitOptionCount;

    /**
     * 是否可以提交审核 0不可以，1可以
     * 默认放开，因为不能判定用户是否已经提交完，具体由用户自己决定是否提交
     */
    private int isSubCheck = 1;


    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProUnitName() {
        return proUnitName;
    }

    public void setProUnitName(String proUnitName) {
        this.proUnitName = proUnitName;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getSituationId() {
        return situationId;
    }

    public void setSituationId(int situationId) {
        this.situationId = situationId;
    }

    public int getUnitOptionCount() {
        return unitOptionCount;
    }

    public void setUnitOptionCount(int unitOptionCount) {
        this.unitOptionCount = unitOptionCount;
    }

    public int getIsSubCheck() {
        return isSubCheck;
    }

    public void setIsSubCheck(int isSubCheck) {
        this.isSubCheck = isSubCheck;
    }

}
