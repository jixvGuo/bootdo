package com.bootdo.cpe.service;

import com.bootdo.cpe.domain.EnterpriTeamLeaderInfoDO;

import java.util.List;
import java.util.Map;

/**
 * 主要成员情况带头人
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-09-19 21:13:08
 */
public interface EnterpriTeamLeaderInfoService {

    EnterpriTeamLeaderInfoDO get(Integer id);

    List<EnterpriTeamLeaderInfoDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(EnterpriTeamLeaderInfoDO enterpriTeamLeaderInfo);

    int update(EnterpriTeamLeaderInfoDO enterpriTeamLeaderInfo);

    int remove(Integer id);

    int batchRemove(Integer[] ids);
}
