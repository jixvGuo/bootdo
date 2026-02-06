package com.bootdo.cpe.service;


import com.bootdo.cpe.domain.SurverExcellentApplyMainInfoDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-03-27 21:44:17
 */
public interface SurverExcellentApplyMainInfoService {
	
	SurverExcellentApplyMainInfoDO get(Integer id);
	
	List<SurverExcellentApplyMainInfoDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SurverExcellentApplyMainInfoDO surverExcellentApplyMainInfo);
	
	int update(SurverExcellentApplyMainInfoDO surverExcellentApplyMainInfo);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
