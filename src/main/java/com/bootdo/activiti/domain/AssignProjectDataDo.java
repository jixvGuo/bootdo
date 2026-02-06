package com.bootdo.activiti.domain;

/**
 * 分派项目的数据内容
 */
public class AssignProjectDataDo {
    private int id;
    private long assignUid;
    private long uid;
    private long proId;
    private String created;
    private String proType;
    private String proName;

    public AssignProjectDataDo(long assignUid,long uid,long proId) {
        this.assignUid = assignUid;
        this.uid = uid;
        this.proId = proId;
    }
    public AssignProjectDataDo(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getAssignUid() {
        return assignUid;
    }

    public void setAssignUid(long assignUid) {
        this.assignUid = assignUid;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public long getProId() {
        return proId;
    }

    public void setProId(long proId) {
        this.proId = proId;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getProType() {
        return proType;
    }

    public void setProType(String proType) {
        this.proType = proType;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }
}
