package com.bootdo.cpe.domain;

/**
 * 专家各专业的账号信息
 * @author houzb
 * @Description
 * @create 2020-10-10 3:59
 */
public class SpecialistMajorAccountInfo {
    private String groupName;
    private String specialistAccounts;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getSpecialistAccounts() {
        return specialistAccounts;
    }

    public void setSpecialistAccounts(String specialistAccounts) {
        this.specialistAccounts = specialistAccounts;
    }
}
