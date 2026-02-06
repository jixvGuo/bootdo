package com.bootdo.cpe.dao;

import com.bootdo.cpe.domain.EnterpriTeamProjectInfoDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 团队承担项目情况
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-09-19 15:42:06
 */
@Mapper
public interface EnterpriTeamProjectInfoDao {

    EnterpriTeamProjectInfoDO get(Integer id);

    List<EnterpriTeamProjectInfoDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(EnterpriTeamProjectInfoDO enterpriTeamProjectInfo);

    int update(EnterpriTeamProjectInfoDO enterpriTeamProjectInfo);

    int remove(Integer id);

    int batchRemove(Integer[] ids);
}
