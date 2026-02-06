package com.bootdo.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.system.dao.EnterpriPersonalInfoWorkHistoryDao;
import com.bootdo.system.domain.EnterpriPersonalInfoWorkHistoryDO;
import com.bootdo.system.service.EnterpriPersonalInfoWorkHistoryService;



@Service
public class EnterpriPersonalInfoWorkHistoryServiceImpl implements EnterpriPersonalInfoWorkHistoryService {
	@Autowired
	private EnterpriPersonalInfoWorkHistoryDao enterpriPersonalInfoWorkHistoryDao;
	
	@Override
	public EnterpriPersonalInfoWorkHistoryDO get(Integer id){
		return enterpriPersonalInfoWorkHistoryDao.get(id);
	}
	
	@Override
	public List<EnterpriPersonalInfoWorkHistoryDO> list(Map<String, Object> map){
		return enterpriPersonalInfoWorkHistoryDao.list(map);
	}

	@Override
	public int count(Map<String, Object> map){
		return enterpriPersonalInfoWorkHistoryDao.count(map);
	}
	
	@Override
	public int save(EnterpriPersonalInfoWorkHistoryDO enterpriPersonalInfoWorkHistory){
		return enterpriPersonalInfoWorkHistoryDao.save(enterpriPersonalInfoWorkHistory);
	}
	
	@Override
	public int update(EnterpriPersonalInfoWorkHistoryDO enterpriPersonalInfoWorkHistory){
		return enterpriPersonalInfoWorkHistoryDao.update(enterpriPersonalInfoWorkHistory);
	}
	
	@Override
	public int remove(Integer id){
		return enterpriPersonalInfoWorkHistoryDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return enterpriPersonalInfoWorkHistoryDao.batchRemove(ids);
	}
	
}
