package com.bootdo.cpe.service;

import com.bootdo.cpe.domain.EnterpriTeamEvaluateOtherDO;

import java.util.List;
import java.util.Map;

/**
 * 团队第三方评价
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-09-19 16:46:09
 */
public interface EnterpriTeamEvaluateOtherService {

    EnterpriTeamEvaluateOtherDO get(Integer id);

    List<EnterpriTeamEvaluateOtherDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(EnterpriTeamEvaluateOtherDO enterpriTeamEvaluateOther);

    int update(EnterpriTeamEvaluateOtherDO enterpriTeamEvaluateOther);

    int remove(Integer id);

    int batchRemove(Integer[] ids);
}
