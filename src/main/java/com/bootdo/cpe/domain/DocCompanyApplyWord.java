package com.bootdo.cpe.domain;

import com.bootdo.system.domain.EnterpriseChengguoBaseInfoDO;
import com.bootdo.system.domain.EnterpriseChengguoOtherInfoDO;

/***
 * 企业信息申报
 */
public class DocCompanyApplyWord extends DocBaseDataWord {
    private EnterpriseChengguoBaseInfoDO pInfo;

    private EnterpriseChengguoOtherInfoDO oInfo;

    public EnterpriseChengguoOtherInfoDO getoInfo() {
        return oInfo;
    }

    public void setoInfo(EnterpriseChengguoOtherInfoDO oInfo) {
        this.oInfo = oInfo;
    }

    public EnterpriseChengguoBaseInfoDO getpInfo() {
        return pInfo;
    }

    public void setpInfo(EnterpriseChengguoBaseInfoDO pInfo) {
        this.pInfo = pInfo;
    }
}
