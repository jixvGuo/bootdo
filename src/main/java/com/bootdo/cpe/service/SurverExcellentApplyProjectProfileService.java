package com.bootdo.cpe.service;

import com.bootdo.cpe.domain.SurverExcellentApplyProjectProfileDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-04-05 20:07:38
 */
public interface SurverExcellentApplyProjectProfileService {
	
	SurverExcellentApplyProjectProfileDO get(Integer id);
	
	List<SurverExcellentApplyProjectProfileDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SurverExcellentApplyProjectProfileDO surverExcellentApplyProjectProfile);
	
	int update(SurverExcellentApplyProjectProfileDO surverExcellentApplyProjectProfile);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
