package com.bootdo.activiti.domain;

public class AwardScoreDetailInfo {
    private int id;
    private int proId;
    private String scoreType;
    private long scoreUid;
    private String scoreKey;
    private double scoreVal;
    private String scoreTxt;
    private String created;
    /**
     * 专业
     */
    private String major;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public String getScoreType() {
        return scoreType;
    }

    public void setScoreType(String scoreType) {
        this.scoreType = scoreType;
    }

    public long getScoreUid() {
        return scoreUid;
    }

    public void setScoreUid(long scoreUid) {
        this.scoreUid = scoreUid;
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

    public String getScoreTxt() {
        return scoreTxt;
    }

    public void setScoreTxt(String scoreTxt) {
        this.scoreTxt = scoreTxt;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}
