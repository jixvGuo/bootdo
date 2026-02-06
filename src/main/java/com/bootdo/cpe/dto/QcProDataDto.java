package com.bootdo.cpe.dto;

import com.bootdo.cpe.domain.QcGroupApplyInfoDO;

/**
 * QC奖项目数据信息
 *
 * @author houzb
 * @version 1.0
 * @date 2022-02-05 10:34
 */
public class QcProDataDto extends QcGroupApplyInfoDO {
    /**
     * 填报的账号
     */
    private String optAccount;
    /**
     * 项目编号
     */
    private String proCode;

    /**
     * 项目类别
     */
    private String proType = "QC";

    /**
     * 单位名称
     */
    private String companyName;

    public String getOptAccount() {
        return optAccount;
    }

    public void setOptAccount(String optAccount) {
        this.optAccount = optAccount;
    }

    public String getProCode() {
        return proCode;
    }

    public void setProCode(String proCode) {
        this.proCode = proCode;
    }

    public String getProType() {
        return proType;
    }

    public void setProType(String proType) {
        this.proType = proType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

}
