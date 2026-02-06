package com.bootdo.cpe.service;

import com.bootdo.cpe.domain.EnterpriTeamProjectInfoDO;
import com.bootdo.cpe.domain.ExpertGroupDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/***
 * 专业组管理
 */
public interface ExpertGroupService {
    // 得到专业信息
    ExpertGroupDO get(Integer id);

    List<ExpertGroupDO> list(Map<String, Object> map);

    String getSignUrl(Map<String,Object> params);

    int count(Map<String, Object> map);

    int save(ExpertGroupDO expertGroupDO);

    int update(ExpertGroupDO expertGroupDO);

    int updateExpertSignId(long signId, String taskId, long expertUid);

    int remove(Integer id);
    int removeByLoginAccount(String loginAccount);

    /**
     * 逻辑删除专家账号
     * @param loginAccount
     * @return
     */
    int delByLoginAccount(String loginAccount);

}
