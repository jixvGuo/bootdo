package com.bootdo.cpe.domain;

/**
 * 专家打分汇总表信息
 * @author houzb
 * @Description
 * @create 2020-10-11 23:25
 */
public class SpecialistScoreDetailInfo {

    private int proId;
    private int itemId;
    //名称
    private String proName;
    private String scoreKey;
    private double scoreVal;
    private String signature;
    //打分日期
    private String created;

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getScoreKey() {
        return scoreKey;
    }

    public void setScoreKey(String scoreKey) {
        this.scoreKey = scoreKey;
    }

    public double getScoreVal() {
        return scoreVal;
    }

    public void setScoreVal(double scoreVal) {
        this.scoreVal = scoreVal;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}
