package com.bootdo.system.service;

import com.bootdo.system.domain.EnterpriTeamUsersListDO;

import java.util.List;
import java.util.Map;

/**
 * 团队人员构成-主要成员
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-06-30 04:48:15
 */
public interface EnterpriTeamUsersListService {
	
	EnterpriTeamUsersListDO get(Integer id);
	
	List<EnterpriTeamUsersListDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(EnterpriTeamUsersListDO enterpriTeamUsersList);
	
	int update(EnterpriTeamUsersListDO enterpriTeamUsersList);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
