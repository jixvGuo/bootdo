package com.bootdo.cpe.service;

import com.bootdo.cpe.domain.SurverStandardApplyProjectProfileDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-04-05 20:07:39
 */
public interface SurverStandardApplyProjectProfileService {
	
	SurverStandardApplyProjectProfileDO get(Integer id);
	
	List<SurverStandardApplyProjectProfileDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SurverStandardApplyProjectProfileDO surverStandardApplyProjectProfile);
	
	int update(SurverStandardApplyProjectProfileDO surverStandardApplyProjectProfile);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
