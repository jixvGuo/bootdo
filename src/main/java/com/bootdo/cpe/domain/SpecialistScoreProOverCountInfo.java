package com.bootdo.cpe.domain;

/**
 * 专家打分结束的专家数量信息
 * @author houzb
 * @Description
 * @create 2020-10-10 1:48
 */
public class SpecialistScoreProOverCountInfo {
    //项目id
    private int proId;
    //项目类型
    private String proType;
    //专业组内的全部的专家数量
    private int totalSpecialistCount;
    //专业组内已经结束的专家数量
    private int overSpecialistCount;

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public String getProType() {
        return proType;
    }

    public void setProType(String proType) {
        this.proType = proType;
    }

    public int getTotalSpecialistCount() {
        return totalSpecialistCount;
    }

    public void setTotalSpecialistCount(int totalSpecialistCount) {
        this.totalSpecialistCount = totalSpecialistCount;
    }

    public int getOverSpecialistCount() {
        return overSpecialistCount;
    }

    public void setOverSpecialistCount(int overSpecialistCount) {
        this.overSpecialistCount = overSpecialistCount;
    }
}
