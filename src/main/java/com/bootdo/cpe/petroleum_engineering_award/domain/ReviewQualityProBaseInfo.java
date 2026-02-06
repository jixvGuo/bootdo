package com.bootdo.cpe.petroleum_engineering_award.domain;

/**
 * 审核需要的安装工程基本信息
 *
 * @author houzb
 * @version 1.0
 * @date 2021-03-19 21:47
 */
public class ReviewQualityProBaseInfo {
    /**
     * 项目名称
     */
    private String projectName;
    /**
     * 单位名称
     */
    private String unitName;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
}
