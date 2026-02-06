package com.bootdo.cpe.domain.science_process;

/**
 * 已分派给外聘人员的信息
 *
 * @author houzb
 * @version 1.0
 * @date 2021-04-19 1:58
 */
public class ScienceAssignUserInfo {
    /**
     * 外聘人员用户id
     */
    private int uid;
    /**
     * 分派的项目id，多个逗号分隔
     */
    private String proIds;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getProIds() {
        return proIds;
    }

    public void setProIds(String proIds) {
        this.proIds = proIds;
    }
}
