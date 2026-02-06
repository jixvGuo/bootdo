package com.bootdo.cpe.petroleum_engineering_award.service;

import com.bootdo.cpe.petroleum_engineering_award.domain.OilProDesignAwardDO;

import java.util.List;
import java.util.Map;

/**
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2021-02-20 22:55:27
 */
public interface OilProDesignAwardService {

    OilProDesignAwardDO get(Integer id);

    List<OilProDesignAwardDO> getByProId(int proId);

    List<OilProDesignAwardDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(OilProDesignAwardDO oilProDesignAward);

    int update(OilProDesignAwardDO oilProDesignAward);

    int remove(Integer id);

    int batchRemove(Integer[] ids);

}
