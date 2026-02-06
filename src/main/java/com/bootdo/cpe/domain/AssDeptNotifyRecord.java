package com.bootdo.cpe.domain;

/**
 * 创建企业消息通知记录
 *
 * @author houzb
 * @version 1.0
 * @date 2021-03-09 23:14
 */
public class AssDeptNotifyRecord {
    private int id;
    private int deptId;
    private long notifyId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public long getNotifyId() {
        return notifyId;
    }

    public void setNotifyId(long notifyId) {
        this.notifyId = notifyId;
    }
}
