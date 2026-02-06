package com.bootdo.cpe.dao;

import com.bootdo.cpe.domain.SurverConsultApplyTableInfoDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-03-29 23:15:37
 */
@Mapper
public interface SurverConsultApplyTableInfoDao {

	SurverConsultApplyTableInfoDO get(Integer id);
	
	List<SurverConsultApplyTableInfoDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(SurverConsultApplyTableInfoDO surverConsultApplyTableInfo);
	
	int update(SurverConsultApplyTableInfoDO surverConsultApplyTableInfo);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
