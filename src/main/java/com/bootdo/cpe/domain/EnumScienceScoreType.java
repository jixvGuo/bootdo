package com.bootdo.cpe.domain;

/**
 * @author houzb
 * @Description
 * @create 2020-10-09 21:35
 */
public enum EnumScienceScoreType {
    TEAM("team_score","团队打分"),
    SCIENCE("science_score","科技进步打分"),
    PERSONAL("personal_score","个人打分");

    private String scoreType;
    private String desc;
    EnumScienceScoreType(String scoreType,String desc) {
        this.scoreType = scoreType;
        this.desc = desc;
    }

    public String getScoreType() {
        return scoreType;
    }

    public void setScoreType(String scoreType) {
        this.scoreType = scoreType;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
