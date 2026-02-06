package com.bootdo.cpe.service;

import com.bootdo.cpe.domain.EnterpriTeamOtherMainDO;

import java.util.List;
import java.util.Map;

/**
 * 主要成员情况-其他主要成员
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-09-19 23:28:34
 */
public interface EnterpriTeamOtherMainService {

    EnterpriTeamOtherMainDO get(Integer id);

    List<EnterpriTeamOtherMainDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(EnterpriTeamOtherMainDO enterpriTeamOtherMain);

    int update(EnterpriTeamOtherMainDO enterpriTeamOtherMain);

    int remove(Integer id);

    int batchRemove(Integer[] ids);
}
