package com.bootdo.cpe.petroleum_engineering_award.service;

import com.bootdo.cpe.petroleum_engineering_award.domain.OilAwardGetInfoDO;
import com.bootdo.cpe.petroleum_engineering_award.domain.OilAwardPartakeUnitDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2021-02-20 22:55:27
 */
public interface OilAwardPartakeUnitService {
	
	OilAwardPartakeUnitDO get(Integer id);
	
	List<OilAwardPartakeUnitDO> list(Map<String, Object> map);
	/***
	 * 根据项目id 获取 //获奖信息
	 * @param proId
	 * @return
	 */
	List<OilAwardPartakeUnitDO> getByProId(int proId);

	int count(Map<String, Object> map);
	
	int save(OilAwardPartakeUnitDO oilAwardPartakeUnit);
	
	int update(OilAwardPartakeUnitDO oilAwardPartakeUnit);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
