package com.bootdo.cpe.domain.science_process;

/**
 * 分派外聘数量信息
 *
 * @author houzb
 * @version 1.0
 * @date 2021-04-19 1:24
 */
public class ScienceAssignCountInfo {
    /**
     * 提交审核的数量
     */
    private int validateCount;
    /**
     * 已分派的数量
     */
    private int assignCount;

    public int getValidateCount() {
        return validateCount;
    }

    public void setValidateCount(int validateCount) {
        this.validateCount = validateCount;
    }

    public int getAssignCount() {
        return assignCount;
    }

    public void setAssignCount(int assignCount) {
        this.assignCount = assignCount;
    }
}
