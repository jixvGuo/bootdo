package com.bootdo.system.dao;

import com.bootdo.system.domain.EnterpriPersonalInfoScienceKpiDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 申报人科技创新业绩指标
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-07-01 01:13:32
 */
@Mapper
public interface EnterpriPersonalInfoScienceKpiDao {

	EnterpriPersonalInfoScienceKpiDO get(Integer id);
	
	List<EnterpriPersonalInfoScienceKpiDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(EnterpriPersonalInfoScienceKpiDO enterpriPersonalInfoScienceKpi);
	
	int update(EnterpriPersonalInfoScienceKpiDO enterpriPersonalInfoScienceKpi);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
