package com.bootdo.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.system.dao.EnterpriTeamEnclosureListDao;
import com.bootdo.system.domain.EnterpriTeamEnclosureListDO;
import com.bootdo.system.service.EnterpriTeamEnclosureListService;



@Service
public class EnterpriTeamEnclosureListServiceImpl implements EnterpriTeamEnclosureListService {
	@Autowired
	private EnterpriTeamEnclosureListDao enterpriTeamEnclosureListDao;
	
	@Override
	public EnterpriTeamEnclosureListDO get(Integer id){
		return enterpriTeamEnclosureListDao.get(id);
	}
	
	@Override
	public List<EnterpriTeamEnclosureListDO> list(Map<String, Object> map){
		return enterpriTeamEnclosureListDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return enterpriTeamEnclosureListDao.count(map);
	}
	
	@Override
	public int save(EnterpriTeamEnclosureListDO enterpriTeamEnclosureList){
		return enterpriTeamEnclosureListDao.save(enterpriTeamEnclosureList);
	}
	
	@Override
	public int update(EnterpriTeamEnclosureListDO enterpriTeamEnclosureList){
		return enterpriTeamEnclosureListDao.update(enterpriTeamEnclosureList);
	}
	
	@Override
	public int remove(Integer id){
		return enterpriTeamEnclosureListDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return enterpriTeamEnclosureListDao.batchRemove(ids);
	}
	
}
