package com.bootdo.cpe.petroleum_engineering_award.domain;

/**
 * 获奖类型
 *
 * @author houzb
 * @version 1.0
 * @date 2021-03-23 23:56
 */
public enum EnumOilQualityGetAwardType {
    BUREAU_PRO(1, "局级优质工程奖"),
    BUREAU_DESIGN(2, "局级（含）以上优秀设计奖"),
    BUREAU_SCIENCE(3, "局级（含）以上科技进步奖"),
    ;
    private int typeId;
    private String desc;

    EnumOilQualityGetAwardType(int typeId, String desc) {
        this.typeId = typeId;
        this.desc = desc;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static String getDescById(String id) {
        EnumOilQualityGetAwardType[] types = EnumOilQualityGetAwardType.values();
        for(EnumOilQualityGetAwardType t:types) {
            if((t.getTypeId()+"").equals(id)) {
                return t.getDesc();
            }
        }
        return "";
    }
}
