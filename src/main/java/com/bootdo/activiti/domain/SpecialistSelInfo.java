package com.bootdo.activiti.domain;

import org.apache.commons.lang.StringUtils;

public class SpecialistSelInfo {
    private long id;
    //协会工作人员id
    private long selUid;
    //选择的专家
    private String uids;
    //发布的奖项任务id
    private String publishAwardId;
    //创建时间
    private String created;
    //专业id
    private long majorId;
    /**
     * 专业名称
     */
    private String major;
    //专业组长用户id
    private long specialistLeaderUid;
    //修改时间
    private String updateTime;
    //是否选择结束
    private boolean isComplete;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSelUid() {
        return selUid;
    }

    public void setSelUid(long selUid) {
        this.selUid = selUid;
    }

    public String getUids() {
        return uids;
    }

    public void setUids(String uids) {
        this.uids = uids;
    }

    public String getPublishAwardId() {
        return publishAwardId;
    }

    public void setPublishAwardId(String publishAwardId) {
        this.publishAwardId = publishAwardId;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public long getMajorId() {
        return majorId;
    }

    public void setMajorId(long majorId) {
        this.majorId = majorId;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public long getSpecialistLeaderUid() {
        return specialistLeaderUid;
    }

    public void setSpecialistLeaderUid(long specialistLeaderUid) {
        this.specialistLeaderUid = specialistLeaderUid;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setIsComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public boolean isCouldSave() {
        if(this.majorId == 0) {
           return false;
        }
        if(StringUtils.isBlank(this.uids)) {
            return false;
        }
        if(this.specialistLeaderUid == 0) {
            return false;
        }
        return true;
    }
}
