package com.bootdo.cpe.domain;

/**
 * 项目形式审查通知记录
 *
 * @author houzb
 * @version 1.0
 * @date 2021-03-22 0:32
 */
public class AssNotifyProReviewRecord {
    private int proId;
    private int reviewId;
    private long notifyId;
    private String proType;

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public long getNotifyId() {
        return notifyId;
    }

    public void setNotifyId(long notifyId) {
        this.notifyId = notifyId;
    }

    public String getProType() {
        return proType;
    }

    public void setProType(String proType) {
        this.proType = proType;
    }
}
