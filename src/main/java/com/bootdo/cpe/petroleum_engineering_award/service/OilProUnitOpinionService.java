package com.bootdo.cpe.petroleum_engineering_award.service;

import com.bootdo.cpe.petroleum_engineering_award.domain.OilProUnitOpinionDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2021-02-20 22:55:27
 */
public interface OilProUnitOpinionService {
	
	OilProUnitOpinionDO get(String id);

    List<OilProUnitOpinionDO> getByProId(int proId);
	
	List<OilProUnitOpinionDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(OilProUnitOpinionDO oilProUnitOpinion);
	
	int update(OilProUnitOpinionDO oilProUnitOpinion);
	
	int remove(String id);
	
	int batchRemove(String[] ids);

}
