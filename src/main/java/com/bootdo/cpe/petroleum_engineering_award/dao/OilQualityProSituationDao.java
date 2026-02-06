package com.bootdo.cpe.petroleum_engineering_award.dao;

import com.bootdo.cpe.petroleum_engineering_award.domain.OilQualityProSituationDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 石油优质工程项目概况
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2021-03-14 21:38:26
 */
@Mapper
public interface OilQualityProSituationDao {

	OilQualityProSituationDO get(Integer id);
	
	List<OilQualityProSituationDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(OilQualityProSituationDO oilQualityProSituation);
	
	int update(OilQualityProSituationDO oilQualityProSituation);
	
	int remove(Integer id);

	int removeByProId(int proId);
	
	int batchRemove(Integer[] ids);
}
