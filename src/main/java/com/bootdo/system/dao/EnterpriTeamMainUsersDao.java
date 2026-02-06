package com.bootdo.system.dao;

import com.bootdo.system.domain.EnterpriTeamMainUsersDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 团队主要成员
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-07-05 08:21:09
 */
@Mapper
public interface EnterpriTeamMainUsersDao {

	EnterpriTeamMainUsersDO get(Integer id);
	
	List<EnterpriTeamMainUsersDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(EnterpriTeamMainUsersDO enterpriTeamMainUsers);
	
	int update(EnterpriTeamMainUsersDO enterpriTeamMainUsers);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
