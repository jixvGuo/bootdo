package com.bootdo.cpe.service;

import com.bootdo.cpe.domain.EnterpriTeamCooperationDO;

import java.util.List;
import java.util.Map;

/**
 * 团队合作情况汇总表
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-09-19 20:08:27
 */
public interface EnterpriTeamCooperationService {

    EnterpriTeamCooperationDO get(Integer id);

    List<EnterpriTeamCooperationDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(EnterpriTeamCooperationDO enterpriTeamCooperation);

    int update(EnterpriTeamCooperationDO enterpriTeamCooperation);

    int remove(Integer id);

    int batchRemove(Integer[] ids);
}
