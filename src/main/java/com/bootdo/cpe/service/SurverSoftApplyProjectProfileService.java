package com.bootdo.cpe.service;

import com.bootdo.cpe.domain.SurverSoftApplyProjectProfileDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-04-05 20:07:39
 */
public interface SurverSoftApplyProjectProfileService {
	
	SurverSoftApplyProjectProfileDO get(Integer id);
	
	List<SurverSoftApplyProjectProfileDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SurverSoftApplyProjectProfileDO surverSoftApplyProjectProfile);
	
	int update(SurverSoftApplyProjectProfileDO surverSoftApplyProjectProfile);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
