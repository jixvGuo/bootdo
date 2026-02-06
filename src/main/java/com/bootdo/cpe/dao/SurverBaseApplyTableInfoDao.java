package com.bootdo.cpe.dao;

import com.bootdo.cpe.domain.SurverBaseApplyTableInfoDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-03-30 23:24:20
 */
@Mapper
public interface SurverBaseApplyTableInfoDao {

	SurverBaseApplyTableInfoDO get(Integer id);
	
	List<SurverBaseApplyTableInfoDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(SurverBaseApplyTableInfoDO surverBaseApplyTableInfo);
	
	int update(SurverBaseApplyTableInfoDO surverBaseApplyTableInfo);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
