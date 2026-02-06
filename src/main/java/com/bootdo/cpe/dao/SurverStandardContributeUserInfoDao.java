package com.bootdo.cpe.dao;

import com.bootdo.system.domain.SurverStandardContributeUserInfoDO;

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
public interface SurverStandardContributeUserInfoDao {

	SurverStandardContributeUserInfoDO get(Integer id);
	
	List<SurverStandardContributeUserInfoDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(SurverStandardContributeUserInfoDO surverStandardContributeUserInfo);
	
	int update(SurverStandardContributeUserInfoDO surverStandardContributeUserInfo);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
