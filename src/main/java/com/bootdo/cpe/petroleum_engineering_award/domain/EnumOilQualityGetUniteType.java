package com.bootdo.cpe.petroleum_engineering_award.domain;

/**
 * 获奖类型
 *
 * @author houzb
 * @version 1.0
 * @date 2021-03-23 23:56
 *
 * Construction unit
 */
public enum EnumOilQualityGetUniteType {
    CONTRUBUTE_UNITE(1, "建设单位"),
    SURVEY_DESIGN_UNITE(2, "勘查及设计单位"),
    SUPERVISION_UNITE(3, "监理单位"),
    CONSTRUCTION_UNITE(4, "施工总承包单位"),
    ENGIINEERING_GENERAL_UNITE(5, "工程总承包单位"),

    ;
    private int typeId;
    private String desc;

    EnumOilQualityGetUniteType(int typeId, String desc) {
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
        EnumOilQualityGetUniteType[] types = EnumOilQualityGetUniteType.values();
        for(EnumOilQualityGetUniteType t:types) {
            if((t.getTypeId()+"").equals(id)) {
                return t.getDesc();
            }
        }
        return "";
    }
}
