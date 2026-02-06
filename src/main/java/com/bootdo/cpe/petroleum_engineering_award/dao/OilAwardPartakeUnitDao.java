package com.bootdo.cpe.petroleum_engineering_award.dao;

import com.bootdo.cpe.petroleum_engineering_award.domain.OilAwardPartakeUnitDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2021-02-20 22:55:27
 */
@Mapper
public interface OilAwardPartakeUnitDao {

	OilAwardPartakeUnitDO get(Integer id);
	
	List<OilAwardPartakeUnitDO> list(Map<String, Object> map);

	List<OilAwardPartakeUnitDO> getByProId(Integer id);


	int count(Map<String, Object> map);
	
	int save(OilAwardPartakeUnitDO oilAwardPartakeUnit);
	
	int update(OilAwardPartakeUnitDO oilAwardPartakeUnit);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
