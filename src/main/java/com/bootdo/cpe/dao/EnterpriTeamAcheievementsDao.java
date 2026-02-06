package com.bootdo.cpe.dao;

import com.bootdo.cpe.domain.EnterpriTeamAcheievementsDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 团队标志性成果
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-09-17 22:54:16
 */
@Mapper
public interface EnterpriTeamAcheievementsDao {

    EnterpriTeamAcheievementsDO get(Integer id);

    List<EnterpriTeamAcheievementsDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(EnterpriTeamAcheievementsDO enterpriTeamAcheievements);

    int update(EnterpriTeamAcheievementsDO enterpriTeamAcheievements);

    int remove(Integer id);

    int batchRemove(Integer[] ids);
}
