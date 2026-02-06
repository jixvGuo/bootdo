package com.bootdo.system.dao;

import com.bootdo.system.domain.EnterpriPersonalInfoWorkHistoryDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 个人工作经历
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-06-30 04:48:14
 */
@Mapper
public interface EnterpriPersonalInfoWorkHistoryDao {

	EnterpriPersonalInfoWorkHistoryDO get(Integer id);
	
	List<EnterpriPersonalInfoWorkHistoryDO> list(Map<String,Object> map);

	List<EnterpriPersonalInfoWorkHistoryDO> listByPersonId(Map<String, Object> map);


	int count(Map<String,Object> map);
	
	int save(EnterpriPersonalInfoWorkHistoryDO enterpriPersonalInfoWorkHistory);
	
	int update(EnterpriPersonalInfoWorkHistoryDO enterpriPersonalInfoWorkHistory);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
