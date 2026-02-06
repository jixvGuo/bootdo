package com.bootdo.system.service;

import com.bootdo.system.domain.EnterpriTeamUsersDO;

import java.util.List;
import java.util.Map;

/**
 * 人员构成
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-06-30 04:48:15
 */
public interface EnterpriTeamUsersService {
	
	EnterpriTeamUsersDO get(Integer id);
	
	List<EnterpriTeamUsersDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(EnterpriTeamUsersDO enterpriTeamUsers);
	
	int update(EnterpriTeamUsersDO enterpriTeamUsers);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
