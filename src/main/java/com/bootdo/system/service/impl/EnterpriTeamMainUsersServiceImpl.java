package com.bootdo.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.system.dao.EnterpriTeamMainUsersDao;
import com.bootdo.system.domain.EnterpriTeamMainUsersDO;
import com.bootdo.system.service.EnterpriTeamMainUsersService;



@Service
public class EnterpriTeamMainUsersServiceImpl implements EnterpriTeamMainUsersService {
	@Autowired
	private EnterpriTeamMainUsersDao enterpriTeamMainUsersDao;
	
	@Override
	public EnterpriTeamMainUsersDO get(Integer id){
		return enterpriTeamMainUsersDao.get(id);
	}
	
	@Override
	public List<EnterpriTeamMainUsersDO> list(Map<String, Object> map){
		return enterpriTeamMainUsersDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return enterpriTeamMainUsersDao.count(map);
	}
	
	@Override
	public int save(EnterpriTeamMainUsersDO enterpriTeamMainUsers){
		return enterpriTeamMainUsersDao.save(enterpriTeamMainUsers);
	}
	
	@Override
	public int update(EnterpriTeamMainUsersDO enterpriTeamMainUsers){
		return enterpriTeamMainUsersDao.update(enterpriTeamMainUsers);
	}
	
	@Override
	public int remove(Integer id){
		return enterpriTeamMainUsersDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return enterpriTeamMainUsersDao.batchRemove(ids);
	}
	
}
