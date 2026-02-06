package com.bootdo.cpe.domain;

import org.apache.commons.lang.StringUtils;

import java.io.Serializable;


/***
 * 专家组管理
  */
public class ExpertGroupDO implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;
    // 提交ID
    private String proId;
    // 任务ID
    private String taskId;
    //
    private  String userId;
    //类型
    private  Integer  isGroupLeader;
    private  String groupName;
    /**
     * 工作单位
      */
    private String company;
    /**
     * 银行账号
     */
    private String bankAccount;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 登录的账号
     */
    private String loginAccount;
    /**
     * 专家名称
     */
    private String expertName;
    /**
     * 专家签名
     */
    private String  expertSignUrl;

    /**
     * 项目类型
     */
    private String proType;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        if(StringUtils.isNotBlank(groupName)) {
            groupName = groupName.trim();
        }
        this.groupName = groupName;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getIsGroupLeader() {
        return isGroupLeader;
    }

    public void setIsGroupLeader(Integer isGroupLeader) {
        this.isGroupLeader = isGroupLeader;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        if(StringUtils.isNotBlank(company)) {
            company = company.trim();
        }
        this.company = company;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        if(StringUtils.isNotBlank(bankAccount)) {
            bankAccount = bankAccount.trim();
        }
        this.bankAccount = bankAccount;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if(StringUtils.isNotBlank(phone)) {
            phone = phone.trim();
        }
        this.phone = phone;
    }

    public String getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(String loginAccount) {
        if(StringUtils.isNotBlank(loginAccount)) {
            loginAccount = loginAccount.trim();
        }
        this.loginAccount = loginAccount;
    }

    public String getExpertName() {
        return expertName;
    }

    public void setExpertName(String expertName) {
        if(StringUtils.isNotBlank(expertName)) {
            expertName = expertName.trim();
        }
        this.expertName = expertName;
    }

    public String getExpertSignUrl() {
        return expertSignUrl;
    }

    public void setExpertSignUrl(String expertSignUrl) {
        if(StringUtils.isNotBlank(expertSignUrl)) {
            expertSignUrl = expertSignUrl.trim();
        }
        this.expertSignUrl = expertSignUrl;
    }

    public String getProType() {
        return proType;
    }

    public void setProType(String proType) {
        this.proType = proType;
    }
}
