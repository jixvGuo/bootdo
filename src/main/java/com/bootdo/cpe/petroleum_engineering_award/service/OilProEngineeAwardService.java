package com.bootdo.cpe.petroleum_engineering_award.service;

import com.bootdo.cpe.petroleum_engineering_award.domain.OilProEngineeAwardDO;

import java.util.List;
import java.util.Map;

/**
 * 工程获奖
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2021-02-25 06:35:24
 */
public interface OilProEngineeAwardService {

    OilProEngineeAwardDO get(Integer id);

    List<OilProEngineeAwardDO> getByProId(int proId);

    List<OilProEngineeAwardDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(OilProEngineeAwardDO oilProEngineeAward);

    int update(OilProEngineeAwardDO oilProEngineeAward);

    int remove(Integer id);

    int batchRemove(Integer[] ids);

}
