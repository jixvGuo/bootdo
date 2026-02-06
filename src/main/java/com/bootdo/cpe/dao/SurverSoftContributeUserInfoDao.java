package com.bootdo.cpe.dao;

import com.bootdo.system.domain.SurverSoftContributeUserInfoDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-03-27 21:44:17
 */
@Mapper
public interface SurverSoftContributeUserInfoDao {

	SurverSoftContributeUserInfoDO get(Integer id);
	
	List<SurverSoftContributeUserInfoDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(SurverSoftContributeUserInfoDO surverSoftContributeUserInfo);
	
	int update(SurverSoftContributeUserInfoDO surverSoftContributeUserInfo);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
