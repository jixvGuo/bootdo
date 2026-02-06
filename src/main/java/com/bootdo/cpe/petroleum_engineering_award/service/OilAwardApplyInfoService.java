package com.bootdo.cpe.petroleum_engineering_award.service;

import com.bootdo.cpe.petroleum_engineering_award.domain.OilAwardApplyInfoDO;
import com.bootdo.cpe.petroleum_engineering_award.domain.OilQualityProBaseInfo;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2021-02-20 22:55:26
 */
public interface OilAwardApplyInfoService {
	
	OilAwardApplyInfoDO get(Integer id);
	
	List<OilAwardApplyInfoDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(OilAwardApplyInfoDO oilAwardApplyInfo);
	
	int update(OilAwardApplyInfoDO oilAwardApplyInfo);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	OilQualityProBaseInfo getQualityProBaseInfo(int proId);
}
