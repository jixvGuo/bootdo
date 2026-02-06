package com.bootdo.cpe.service;

import com.bootdo.cpe.domain.SurverDesignApplyProjectProfileDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-04-05 20:07:38
 */
public interface SurverDesignApplyProjectProfileService {
	
	SurverDesignApplyProjectProfileDO get(Integer id);
	
	List<SurverDesignApplyProjectProfileDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SurverDesignApplyProjectProfileDO surverDesignApplyProjectProfile);
	
	int update(SurverDesignApplyProjectProfileDO surverDesignApplyProjectProfile);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
