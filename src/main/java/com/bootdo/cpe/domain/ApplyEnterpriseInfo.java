package com.bootdo.cpe.domain;

/**
 * @author houzb
 * @version 1.0
 * @date 2022-04-01 21:44
 */
public class ApplyEnterpriseInfo {

    private int num;
    /**
     * 企业id
     */
    private int id;
    /**
     * 企业名称
     */
    private String enterpriseName;

    /**
     * 上传的文件地址
     */
    private String fileUrl;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
}
