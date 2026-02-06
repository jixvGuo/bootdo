package com.bootdo.cpe.dao;

import com.bootdo.cpe.domain.SurverExcellentApplyProjectProfileDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-04-05 20:07:38
 */
@Mapper
public interface SurverExcellentApplyProjectProfileDao {

	SurverExcellentApplyProjectProfileDO get(Integer id);
	
	List<SurverExcellentApplyProjectProfileDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(SurverExcellentApplyProjectProfileDO surverExcellentApplyProjectProfile);
	
	int update(SurverExcellentApplyProjectProfileDO surverExcellentApplyProjectProfile);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
