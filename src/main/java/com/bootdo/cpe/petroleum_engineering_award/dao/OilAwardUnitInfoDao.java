package com.bootdo.cpe.petroleum_engineering_award.dao;

import com.bootdo.cpe.petroleum_engineering_award.domain.OilAwardPartakeUnitDO;
import com.bootdo.cpe.petroleum_engineering_award.domain.OilAwardUnitInfoDO;

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
public interface OilAwardUnitInfoDao {

	OilAwardUnitInfoDO get(Integer id);
	
	List<OilAwardUnitInfoDO> list(Map<String, Object> map);
	List<OilAwardUnitInfoDO> getByProId(Integer id);

	int count(Map<String, Object> map);
	
	int save(OilAwardUnitInfoDO oilAwardUnitInfo);
	
	int update(OilAwardUnitInfoDO oilAwardUnitInfo);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
