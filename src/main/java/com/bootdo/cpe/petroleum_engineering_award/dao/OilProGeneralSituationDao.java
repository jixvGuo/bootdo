package com.bootdo.cpe.petroleum_engineering_award.dao;

import com.bootdo.cpe.petroleum_engineering_award.domain.OilProGeneralSituationDO;

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
public interface OilProGeneralSituationDao {

	OilProGeneralSituationDO get(Integer id);

	List<OilProGeneralSituationDO> getByProId(int proId);
	
	List<OilProGeneralSituationDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(OilProGeneralSituationDO oilProGeneralSituation);
	
	int update(OilProGeneralSituationDO oilProGeneralSituation);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
