package com.bootdo.cpe.petroleum_engineering_award.service;

import com.bootdo.cpe.petroleum_engineering_award.domain.OilAwardGetInfoDO;
import com.bootdo.cpe.petroleum_engineering_award.domain.OilProApplyInfoDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2021-02-20 22:55:26
 */
public interface OilAwardGetInfoService {
	
	OilAwardGetInfoDO get(Integer id);
	
	List<OilAwardGetInfoDO> list(Map<String, Object> map);


	/***
	 * 根据项目id 获取 //获奖信息
	 * @param proId
	 * @return
	 */
	List<OilAwardGetInfoDO> getByProId(int proId);

	int count(Map<String, Object> map);
	
	int save(OilAwardGetInfoDO oilAwardGetInfo);
	
	int update(OilAwardGetInfoDO oilAwardGetInfo);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
