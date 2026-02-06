package com.bootdo.cpe.petroleum_engineering_award.domain;

/**
 * 石油安装工程信息
 *
 * @author houzb
 * @version 1.0
 * @date 2021-03-12 7:10
 */
public class OilProQualityInfoDO extends OilProBaseInfoDO{
    /**
     * 申报信息id
     */
    private int applyInfoId;
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
     * 是否可以提交审核 0不可以，1可以
     * 默认放开，因为不能判定用户是否已经提交完，具体由用户自己决定是否提交
     */
    private int isSubCheck = 1;
    /**
     * 是否填写工程简介 0 没有 1已填写
     * TODO 暂未记录
     */
    private int isProDesc;
    /**
     * 是否填写贡献者名单
     */
    private int isContributionUser;
    /**
     * 是否证实性文件上传
     * TODO 暂未记录
     */
    private int isSourceUp;


    public int getApplyInfoId() {
        return applyInfoId;
    }

    public void setApplyInfoId(int applyInfoId) {
        this.applyInfoId = applyInfoId;
    }

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

    public int getIsSubCheck() {
        return isSubCheck;
    }

    public void setIsSubCheck(int isSubCheck) {
        this.isSubCheck = isSubCheck;
    }

    public int getIsProDesc() {
        return isProDesc;
    }

    public void setIsProDesc(int isProDesc) {
        this.isProDesc = isProDesc;
    }

    public int getIsContributionUser() {
        return isContributionUser;
    }

    public void setIsContributionUser(int isContributionUser) {
        this.isContributionUser = isContributionUser;
    }

    public int getIsSourceUp() {
        return isSourceUp;
    }

    public void setIsSourceUp(int isSourceUp) {
        this.isSourceUp = isSourceUp;
    }

}
