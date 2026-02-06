package com.bootdo.cpe.dao;


import java.util.List;
import java.util.Map;

import com.bootdo.cpe.domain.SurverStandardApplyTableInfoDO;
import org.apache.ibatis.annotations.Mapper;

/**
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-03-27 21:44:17
 */
@Mapper
public interface SurverStandardApplyTableInfoDao {

	SurverStandardApplyTableInfoDO get(Integer id);

	List<SurverStandardApplyTableInfoDO> list(Map<String,Object> map);

	int count(Map<String,Object> map);

	int save(SurverStandardApplyTableInfoDO surverStandardApplyTableInfo);

	int update(SurverStandardApplyTableInfoDO surverStandardApplyTableInfo);

	int remove(Integer id);

	int batchRemove(Integer[] ids);
}
