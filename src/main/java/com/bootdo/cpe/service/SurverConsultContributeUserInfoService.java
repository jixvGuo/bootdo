package com.bootdo.system.service;

import com.bootdo.system.domain.SurverConsultContributeUserInfoDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-03-27 21:44:17
 */
public interface SurverConsultContributeUserInfoService {
	
	SurverConsultContributeUserInfoDO get(Integer id);
	
	List<SurverConsultContributeUserInfoDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SurverConsultContributeUserInfoDO surverConsultContributeUserInfo);
	
	int update(SurverConsultContributeUserInfoDO surverConsultContributeUserInfo);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
