package com.bootdo.cpe.service;

import com.bootdo.cpe.domain.EnterpriTeamGetTechAwardDO;

import java.util.List;
import java.util.Map;

/**
 * 团队曾获科技奖励情况
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-09-19 17:23:35
 */
public interface EnterpriTeamGetTechAwardService {

    EnterpriTeamGetTechAwardDO get(Integer id);

    List<EnterpriTeamGetTechAwardDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(EnterpriTeamGetTechAwardDO enterpriTeamGetTechAward);

    int update(EnterpriTeamGetTechAwardDO enterpriTeamGetTechAward);

    int remove(Integer id);

    int batchRemove(Integer[] ids);
}
