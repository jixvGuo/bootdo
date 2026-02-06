package com.bootdo.system.dao;

import com.bootdo.system.domain.EnterpriTeamUsersDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 人员构成
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-06-30 04:48:15
 */
@Mapper
public interface EnterpriTeamUsersDao {

	EnterpriTeamUsersDO get(Integer id);
	
	List<EnterpriTeamUsersDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(EnterpriTeamUsersDO enterpriTeamUsers);
	
	int update(EnterpriTeamUsersDO enterpriTeamUsers);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
