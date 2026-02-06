package com.bootdo.cpe.service;

import com.bootdo.system.domain.SurverExcellentApplyTableInfoDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-03-27 21:44:17
 */
public interface SurverExcellentApplyTableInfoService {
	
	SurverExcellentApplyTableInfoDO get(Integer id);
	
	List<SurverExcellentApplyTableInfoDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SurverExcellentApplyTableInfoDO surverExcellentApplyTableInfo);
	
	int update(SurverExcellentApplyTableInfoDO surverExcellentApplyTableInfo);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
