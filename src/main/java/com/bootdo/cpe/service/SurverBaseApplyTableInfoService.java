package com.bootdo.cpe.service;

import com.bootdo.cpe.domain.SurverBaseApplyTableInfoDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-03-30 23:24:20
 */
public interface SurverBaseApplyTableInfoService {
	
	SurverBaseApplyTableInfoDO get(Integer id);
	
	List<SurverBaseApplyTableInfoDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SurverBaseApplyTableInfoDO surverBaseApplyTableInfo);
	
	int update(SurverBaseApplyTableInfoDO surverBaseApplyTableInfo);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
