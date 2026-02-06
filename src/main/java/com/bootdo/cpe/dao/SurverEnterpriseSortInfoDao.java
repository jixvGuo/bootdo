package com.bootdo.cpe.dao;

import com.bootdo.cpe.domain.SurverEnterpriseSortInfoDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 上传排序表
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-04-02 21:56:36
 */
@Mapper
public interface SurverEnterpriseSortInfoDao {

	SurverEnterpriseSortInfoDO get(Integer id);
	
	List<SurverEnterpriseSortInfoDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(SurverEnterpriseSortInfoDO surverEnterpriseSortInfo);
	
	int update(SurverEnterpriseSortInfoDO surverEnterpriseSortInfo);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
