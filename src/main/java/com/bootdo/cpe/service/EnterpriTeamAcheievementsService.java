package com.bootdo.cpe.service;

import com.bootdo.cpe.domain.EnterpriTeamAcheievementsDO;

import java.util.List;
import java.util.Map;

/**
 * 团队标志性成果
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-09-17 22:54:16
 */
public interface EnterpriTeamAcheievementsService {

    EnterpriTeamAcheievementsDO get(Integer id);

    List<EnterpriTeamAcheievementsDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(EnterpriTeamAcheievementsDO enterpriTeamAcheievements);

    int update(EnterpriTeamAcheievementsDO enterpriTeamAcheievements);

    int remove(Integer id);

    int batchRemove(Integer[] ids);
}
