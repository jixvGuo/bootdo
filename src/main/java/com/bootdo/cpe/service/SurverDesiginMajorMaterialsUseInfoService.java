package com.bootdo.cpe.service;

import com.bootdo.cpe.domain.SurverDesiginMajorMaterialsUseInfoDO;

import java.util.List;
import java.util.Map;

/**
 * 主要原材料消耗定额对比
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-03-27 10:11:09
 */
public interface SurverDesiginMajorMaterialsUseInfoService {
	
	SurverDesiginMajorMaterialsUseInfoDO get(Integer id);
	
	List<SurverDesiginMajorMaterialsUseInfoDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SurverDesiginMajorMaterialsUseInfoDO surverDesiginMajorMaterialsUseInfo);
	
	int update(SurverDesiginMajorMaterialsUseInfoDO surverDesiginMajorMaterialsUseInfo);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
