package com.bootdo.cpe.dao;

import com.bootdo.cpe.domain.ExpertGroupDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/***
 * 专业组
 */
public interface ExpertGroupDao {

    // 得到专业信息
    ExpertGroupDO get(Integer id);

    List<ExpertGroupDO> list(Map<String, Object> map);

    /**
     * 获取签名地址
     * @param params
     * @return
     */
    String getSignUrl(Map<String,Object> params);

    int count(Map<String, Object> map);

    int save(ExpertGroupDO expertGroupDO);

    int update(ExpertGroupDO expertGroupDO);

    int updateByLoginAccount(ExpertGroupDO expertGroupDO);

    int updateExpertSignId(@Param("signId") long signId, @Param("taskId") String taskId, @Param("expertUid") long expertUid);

    int remove(Integer id);

    public int removeByLoginAccount(String loginAccount);

    public int delByLoginAccount(String loginAccount);
}
