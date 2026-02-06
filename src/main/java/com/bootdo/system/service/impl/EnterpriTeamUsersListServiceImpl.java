package com.bootdo.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.system.dao.EnterpriTeamUsersListDao;
import com.bootdo.system.domain.EnterpriTeamUsersListDO;
import com.bootdo.system.service.EnterpriTeamUsersListService;



@Service
public class EnterpriTeamUsersListServiceImpl implements EnterpriTeamUsersListService {
	@Autowired
	private EnterpriTeamUsersListDao enterpriTeamUsersListDao;
	
	@Override
	public EnterpriTeamUsersListDO get(Integer id){
		return enterpriTeamUsersListDao.get(id);
	}
	
	@Override
	public List<EnterpriTeamUsersListDO> list(Map<String, Object> map){
		return enterpriTeamUsersListDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return enterpriTeamUsersListDao.count(map);
	}
	
	@Override
	public int save(EnterpriTeamUsersListDO enterpriTeamUsersList){
		return enterpriTeamUsersListDao.save(enterpriTeamUsersList);
	}
	
	@Override
	public int update(EnterpriTeamUsersListDO enterpriTeamUsersList){
		return enterpriTeamUsersListDao.update(enterpriTeamUsersList);
	}
	
	@Override
	public int remove(Integer id){
		return enterpriTeamUsersListDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return enterpriTeamUsersListDao.batchRemove(ids);
	}
	
}
