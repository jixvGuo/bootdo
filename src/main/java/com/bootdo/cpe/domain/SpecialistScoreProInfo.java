package com.bootdo.cpe.domain;

public class SpecialistScoreProInfo {
    private int showNum;
    private String proResultCode;
    //个人为姓名，团队为团队名
    private String proName;
    private String proType;
    private String proTypeStr;
    private String enterpriseName;
    private int proId;
    private String taskId;
    //团队或个人的id
    private int itemId;

    public int getShowNum() {
        return showNum;
    }

    public void setShowNum(int showNum) {
        this.showNum = showNum;
    }

    public String getProResultCode() {
        return proResultCode;
    }

    public void setProResultCode(String proResultCode) {
        this.proResultCode = proResultCode;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProType() {
        return proType;
    }

    public void setProType(String proType) {
        this.proType = proType;
        EnumProjectType subTypeEnum = EnumProjectType.getTypeEnum(this.proType);
        this.proTypeStr = subTypeEnum != null ? subTypeEnum.getDesc() : "暂无";
    }

    public String getProTypeStr() {
        return proTypeStr;
    }

    public void setProTypeStr(String proTypeStr) {
        this.proTypeStr = proTypeStr;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
}
