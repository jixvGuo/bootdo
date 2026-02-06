package com.bootdo.cpe.service;

/**
 * @author houzb
 * @Description
 * @create 2020-09-29 1:43
 */
public interface ProjectCommonService {

    /**
     * 获取项目当前的状态值
     * @param proId
     * @return
     */
    public String getProjectStat(int proId);

    /**
     * 是否显示提交审核按钮
     * @param proId
     * @return
     */
    public boolean isViewApplyValidateBtn(int proId);

    /**
     * 是否显示提交审核按钮
     * @param proStat 项目当前的状态
     * @return
     */
    public boolean isViewApplyValidateBtnByStat(String proStat);

    /**
     * 根据项目id获取创建者用户id
     * @param proId
     * @return
     */
    public long getProCreateUid(int proId);

    /**
     * 逻辑删除全部的项目信息
     * @param taskId
     * @return
     */
    public int removePros(String taskId);
    public int removeTask(String taskId);

    /**
     * 更新项目分组
     * @param proId
     * @param groupName
     * @return
     */
    public int updateProGroupName(int proId, String groupName);

}
