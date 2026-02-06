package com.bootdo.cpe.domain;

/**
 * @author houzb
 * @Description
 * @create 2020-09-29 1:10
 */
public enum  EnumProjectType {
    BLANK("","不存在"),
    SCIENCE_PROGRESS("science_progress","科技奖进步"),
    SCIENCE_TEAM("science_team","科技奖团队"),
    SCIENCE_PERSONAL("science_personal","科技奖个人"),
    OIL_PRO_INSTALL("oil_install","石油安装工程优质奖"),
    OIL_PRO_QUALITY_GOLD("oil_quality_gold","石油优质工程金奖"),
    OIL_PRO_QUALITY("oil_quality","石油优质工程奖"),
    QC_PRO_GROUP("qc_group","QC奖小组类型"),
    SURVER_PRO("surver_pro","勘察设计奖类型"),
    GF_PRO("gf_pro","工法奖"),
    ;
    private String proType;
    private String desc;

    EnumProjectType(String proType,String desc){
        this.proType = proType;
        this.desc = desc;
    }

    public String getProType() {
        return proType;
    }

    public void setProType(String proType) {
        this.proType = proType;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static EnumProjectType getTypeEnum(String proType) {
        if(proType == null) {
            return null;
        }
        EnumProjectType[] subTypeEnumList = EnumProjectType.values();
        for(EnumProjectType subTypeEnum: subTypeEnumList) {
            String st = subTypeEnum.getProType();
            if(proType.equals(st)) {
                return subTypeEnum;
            }
        }
        return null;
    }
}
