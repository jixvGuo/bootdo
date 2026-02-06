package com.bootdo.cpe.service;

import com.bootdo.system.domain.SurverSoftContributeUserInfoDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-03-27 21:44:17
 */
public interface SurverSoftContributeUserInfoService {
	
	SurverSoftContributeUserInfoDO get(Integer id);
	
	List<SurverSoftContributeUserInfoDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SurverSoftContributeUserInfoDO surverSoftContributeUserInfo);
	
	int update(SurverSoftContributeUserInfoDO surverSoftContributeUserInfo);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
