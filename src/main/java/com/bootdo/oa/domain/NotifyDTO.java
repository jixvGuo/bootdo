package com.bootdo.oa.domain;

import com.bootdo.common.utils.StringUtils;
//import sun.swing.StringUIClientPropertyKey;

public class NotifyDTO extends NotifyDO {

    private static final long serialVersionUID = 1L;

    private String isRead;

    private String before;

    private String sender;
    /**
     * 注册的企业id
     */
    private int deptId;
    /**
     * 跳转的url地址
     */
    private String notifyUrl = "/oa/notify/selfNotify?id=";

    /**
     * 项目形式审查数据
     * 项目id和形式审查结果id已井号分隔
     */
    private String proReviewData;
    private int proId;
    private int reviewId;
    private String proType;

    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }

    public String getBefore() {
        return before;
    }

    public void setBefore(String before) {
        this.before = before;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
        if(deptId > 0) {
            this.notifyUrl = "/enterpriseManager/toDeptList?notifyId=NOTIFY_ID&id=";
        }
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getProReviewData() {
        return proReviewData;
    }

    public void setProReviewData(String proReviewData) {
        this.proReviewData = proReviewData;
        if(StringUtils.isNotBlank(proReviewData)) {
            String[] arr = proReviewData.split("#");
            this.proId = Integer.parseInt(arr[0]);
            this.reviewId = Integer.parseInt(arr[1]);
            this.proType = arr[2];
        }
    }

    public int getProId() {
        return proId;
    }

    public int getReviewId() {
        return reviewId;
    }

    public String getProType() {
        return proType;
    }

    @Override
    public String toString() {
        return "NotifyDTO{" +
                "isRead='" + isRead + '\'' +
                ", before='" + before + '\'' +
                ", sender='" + sender + '\'' +
                '}';
    }
}
