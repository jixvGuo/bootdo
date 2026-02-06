package com.bootdo.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.system.dao.EnterpriPersonalInfoTenYearsPatentDao;
import com.bootdo.system.domain.EnterpriPersonalInfoTenYearsPatentDO;
import com.bootdo.system.service.EnterpriPersonalInfoTenYearsPatentService;



@Service
public class EnterpriPersonalInfoTenYearsPatentServiceImpl implements EnterpriPersonalInfoTenYearsPatentService {
	@Autowired
	private EnterpriPersonalInfoTenYearsPatentDao enterpriPersonalInfoTenYearsPatentDao;
	
	@Override
	public EnterpriPersonalInfoTenYearsPatentDO get(Integer id){
		return enterpriPersonalInfoTenYearsPatentDao.get(id);
	}
	
	@Override
	public List<EnterpriPersonalInfoTenYearsPatentDO> list(Map<String, Object> map){
		return enterpriPersonalInfoTenYearsPatentDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return enterpriPersonalInfoTenYearsPatentDao.count(map);
	}
	
	@Override
	public int save(EnterpriPersonalInfoTenYearsPatentDO enterpriPersonalInfoTenYearsPatent){
		return enterpriPersonalInfoTenYearsPatentDao.save(enterpriPersonalInfoTenYearsPatent);
	}
	
	@Override
	public int update(EnterpriPersonalInfoTenYearsPatentDO enterpriPersonalInfoTenYearsPatent){
		return enterpriPersonalInfoTenYearsPatentDao.update(enterpriPersonalInfoTenYearsPatent);
	}
	
	@Override
	public int remove(Integer id){
		return enterpriPersonalInfoTenYearsPatentDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return enterpriPersonalInfoTenYearsPatentDao.batchRemove(ids);
	}
	
}
