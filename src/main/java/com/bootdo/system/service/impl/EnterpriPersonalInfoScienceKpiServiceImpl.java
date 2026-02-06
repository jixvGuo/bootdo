package com.bootdo.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.system.dao.EnterpriPersonalInfoScienceKpiDao;
import com.bootdo.system.domain.EnterpriPersonalInfoScienceKpiDO;
import com.bootdo.system.service.EnterpriPersonalInfoScienceKpiService;



@Service
public class EnterpriPersonalInfoScienceKpiServiceImpl implements EnterpriPersonalInfoScienceKpiService {
	@Autowired
	private EnterpriPersonalInfoScienceKpiDao enterpriPersonalInfoScienceKpiDao;
	
	@Override
	public EnterpriPersonalInfoScienceKpiDO get(Integer id){
		return enterpriPersonalInfoScienceKpiDao.get(id);
	}
	
	@Override
	public List<EnterpriPersonalInfoScienceKpiDO> list(Map<String, Object> map){
		return enterpriPersonalInfoScienceKpiDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return enterpriPersonalInfoScienceKpiDao.count(map);
	}
	
	@Override
	public int save(EnterpriPersonalInfoScienceKpiDO enterpriPersonalInfoScienceKpi){
		return enterpriPersonalInfoScienceKpiDao.save(enterpriPersonalInfoScienceKpi);
	}
	
	@Override
	public int update(EnterpriPersonalInfoScienceKpiDO enterpriPersonalInfoScienceKpi){
		return enterpriPersonalInfoScienceKpiDao.update(enterpriPersonalInfoScienceKpi);
	}
	
	@Override
	public int remove(Integer id){
		return enterpriPersonalInfoScienceKpiDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return enterpriPersonalInfoScienceKpiDao.batchRemove(ids);
	}
	
}
