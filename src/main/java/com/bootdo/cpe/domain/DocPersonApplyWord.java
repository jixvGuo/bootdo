package com.bootdo.cpe.domain;

import com.bootdo.system.domain.EnterpriPersonalInfoDO;

/***
 *个人信息打印
 */
public class DocPersonApplyWord extends DocBaseDataWord {
    private EnterpriPersonalInfoDO pInfo;

    public EnterpriPersonalInfoDO getpInfo() {
        return pInfo;
    }

    public void setpInfo(EnterpriPersonalInfoDO pInfo) {
        this.pInfo = pInfo;
    }
 }
