package com.bootdo.cpe.service;

import com.bootdo.cpe.domain.SurverDesiginContributeUserInfoDO;

import java.util.List;
import java.util.Map;

/**
 * 在本项目中做出贡献的主要人员情况
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-03-27 10:11:09
 */
public interface SurverDesiginContributeUserInfoService {
	
	SurverDesiginContributeUserInfoDO get(Integer id);
	
	List<SurverDesiginContributeUserInfoDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SurverDesiginContributeUserInfoDO surverDesiginContributeUserInfo);
	
	int update(SurverDesiginContributeUserInfoDO surverDesiginContributeUserInfo);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
