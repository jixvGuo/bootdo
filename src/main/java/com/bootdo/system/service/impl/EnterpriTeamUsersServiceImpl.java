package com.bootdo.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.system.dao.EnterpriTeamUsersDao;
import com.bootdo.system.domain.EnterpriTeamUsersDO;
import com.bootdo.system.service.EnterpriTeamUsersService;



@Service
public class EnterpriTeamUsersServiceImpl implements EnterpriTeamUsersService {
	@Autowired
	private EnterpriTeamUsersDao enterpriTeamUsersDao;
	
	@Override
	public EnterpriTeamUsersDO get(Integer id){
		return enterpriTeamUsersDao.get(id);
	}
	
	@Override
	public List<EnterpriTeamUsersDO> list(Map<String, Object> map){
		return enterpriTeamUsersDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return enterpriTeamUsersDao.count(map);
	}
	
	@Override
	public int save(EnterpriTeamUsersDO enterpriTeamUsers){
		return enterpriTeamUsersDao.save(enterpriTeamUsers);
	}
	
	@Override
	public int update(EnterpriTeamUsersDO enterpriTeamUsers){
		return enterpriTeamUsersDao.update(enterpriTeamUsers);
	}
	
	@Override
	public int remove(Integer id){
		return enterpriTeamUsersDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return enterpriTeamUsersDao.batchRemove(ids);
	}
	
}
