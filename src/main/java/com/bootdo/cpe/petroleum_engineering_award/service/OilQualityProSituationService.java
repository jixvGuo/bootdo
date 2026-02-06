package com.bootdo.cpe.petroleum_engineering_award.service;

import com.bootdo.cpe.petroleum_engineering_award.domain.OilQualityProSituationDO;

import java.util.List;
import java.util.Map;

/**
 * 石油优质工程项目概况
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2021-03-14 21:38:26
 */
public interface OilQualityProSituationService {
	
	OilQualityProSituationDO get(Integer id);
	
	List<OilQualityProSituationDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(OilQualityProSituationDO oilQualityProSituation);
	
	int update(OilQualityProSituationDO oilQualityProSituation);
	
	int remove(Integer id);

	/**
	 * 移除项目下的概况文件
	 * @param proId
	 * @return
	 */
	int removeByProId(int proId);
	
	int batchRemove(Integer[] ids);
}
