package com.bootdo.cpe.domain;

import com.bootdo.common.config.Constant;

import java.util.List;

/**
 * 奖项类型枚举
 *
 * @author houzb
 * @version 1.0
 * @date 2021-03-23 7:38
 */
public enum EnumAwardType {
    SCIENCE(1,"科技进步奖", Constant.ROLE_ENTERPRISE_SCIENCE_ID),
    SURVER(2,"勘察设计奖", Constant.ROLE_ENTERPRISE_SURVER_ID),
    QC(3,"QC奖", Constant.ROLE_ENTERPRISE_QC_ID),
    QUALITY(4, "优质工程奖", Constant.ROLE_ENTERPRISE_ENGINEER_ID),
    GONGFA(5, "工法奖", Constant.ROLE_GONGFA_ENTERPRISE_ID);

    private int awrdType;
    private String desc;
    /**
     * 企业用户角色id
     */
    private long enterpriseRoleId;

    EnumAwardType(int awrdType, String desc,long enterpriseRoleId) {
        this.awrdType = awrdType;
        this.desc = desc;
        this.enterpriseRoleId = enterpriseRoleId;
    }

    public int getAwrdType() {
        return awrdType;
    }

    public void setAwrdType(int awrdType) {
        this.awrdType = awrdType;
    }

    public long getEnterpriseRoleId() {
        return enterpriseRoleId;
    }

    public void setEnterpriseRoleId(long enterpriseRoleId) {
        this.enterpriseRoleId = enterpriseRoleId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static int getAwardTypeIdByRoleList(List<Long> roleIdList) {
        EnumAwardType[] enumAwardTypes = EnumAwardType.values();
        for(EnumAwardType awardType:enumAwardTypes) {
            long enterRoleId = awardType.getEnterpriseRoleId();
            if(roleIdList.contains(enterRoleId)) {
                return awardType.getAwrdType();
            }
        }
        return 0;
    }
}
