package com.bootdo.cpe.petroleum_engineering_award.service;

import com.bootdo.cpe.petroleum_engineering_award.domain.OilAwardGetInfoDO;
import com.bootdo.cpe.petroleum_engineering_award.domain.OilAwardUnitInfoDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2021-02-20 22:55:27
 */
public interface OilAwardUnitInfoService {
	
	OilAwardUnitInfoDO get(Integer id);
	
	List<OilAwardUnitInfoDO> list(Map<String, Object> map);
	/***
	 * 根据项目id 获取 // 单位信息表(ass_oil_award_unit_info):
	 * @param proId
	 * @return
	 */
	List<OilAwardUnitInfoDO> getByProId(int proId);

	int count(Map<String, Object> map);
	
	int save(OilAwardUnitInfoDO oilAwardUnitInfo);
	
	int update(OilAwardUnitInfoDO oilAwardUnitInfo);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
