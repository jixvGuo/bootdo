package com.bootdo.cpe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.cpe.dao.EnterpriTeamIntrductionDao;
import com.bootdo.cpe.domain.EnterpriTeamIntrductionDO;
import com.bootdo.cpe.service.EnterpriTeamIntrductionService;



@Service
public class EnterpriTeamIntrductionServiceImpl implements EnterpriTeamIntrductionService {
	@Autowired
	private EnterpriTeamIntrductionDao enterpriTeamIntrductionDao;
	
	@Override
	public EnterpriTeamIntrductionDO get(Integer id){
		return enterpriTeamIntrductionDao.get(id);
	}
	
	@Override
	public List<EnterpriTeamIntrductionDO> list(Map<String, Object> map){
		return enterpriTeamIntrductionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return enterpriTeamIntrductionDao.count(map);
	}
	
	@Override
	public int save(EnterpriTeamIntrductionDO enterpriTeamIntrduction){
		return enterpriTeamIntrductionDao.save(enterpriTeamIntrduction);
	}
	
	@Override
	public int update(EnterpriTeamIntrductionDO enterpriTeamIntrduction){
		return enterpriTeamIntrductionDao.update(enterpriTeamIntrduction);
	}
	
	@Override
	public int remove(Integer id){
		return enterpriTeamIntrductionDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return enterpriTeamIntrductionDao.batchRemove(ids);
	}
	
}
