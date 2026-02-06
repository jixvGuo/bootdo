package com.bootdo.cpe.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author houzb
 * @Description
 * @create 2020-09-29 1:46
 */
@Mapper
public interface ProjectCommonDao {

    public String getProjectStat(int proId);

    long getProCreateUid(int proId);

    /**
     * 更新项目成果编码
     * @param proId
     * @param resultCode
     * @return
     */
    public int updateProResultCode(@Param("proId") int proId, @Param("resultCode") String resultCode);

    public int removePros(String taskId);
    public int removeTask(String taskId);

    public int updateProGroupName(@Param("proId") int proId, @Param("groupName") String groupName);

}
