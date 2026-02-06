package com.bootdo.cpe.dao;

import com.bootdo.cpe.domain.SurverSoftApplyTableInfoDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 *  石油工程建设优秀勘察设计计算机软件奖申报表
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-03-29 20:51:20
 */
@Mapper
public interface SurverSoftApplyTableInfoDao {

	SurverSoftApplyTableInfoDO get(Integer id);
	
	List<SurverSoftApplyTableInfoDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(SurverSoftApplyTableInfoDO surverSoftApplyTableInfo);
	
	int update(SurverSoftApplyTableInfoDO surverSoftApplyTableInfo);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
