package com.bootdo.cpe.domain.science_process;

/**
 * 分派外聘人员项目信息
 *
 * @author houzb
 * @version 1.0
 * @date 2021-04-19 2:06
 */
public class ScienceAssignExternalProInfo {

    private int id;
    /**
     * 序列号
     */
    private int serNum;
    private String proCode;
    private String taskName;
    private String major;
    //项目创建者所属企业
    private String enterpriseName;
    /**
     * 外聘人员用户id
     */
    private long extUserId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSerNum() {
        return serNum;
    }

    public void setSerNum(int serNum) {
        this.serNum = serNum;
    }

    public String getProCode() {
        return proCode;
    }

    public void setProCode(String proCode) {
        this.proCode = proCode;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public long getExtUserId() {
        return extUserId;
    }

    public void setExtUserId(long extUserId) {
        this.extUserId = extUserId;
    }
}
