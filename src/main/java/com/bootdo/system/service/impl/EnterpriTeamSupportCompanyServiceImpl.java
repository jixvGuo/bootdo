package com.bootdo.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.system.dao.EnterpriTeamSupportCompanyDao;
import com.bootdo.system.domain.EnterpriTeamSupportCompanyDO;
import com.bootdo.system.service.EnterpriTeamSupportCompanyService;



@Service
public class EnterpriTeamSupportCompanyServiceImpl implements EnterpriTeamSupportCompanyService {
	@Autowired
	private EnterpriTeamSupportCompanyDao enterpriTeamSupportCompanyDao;
	
	@Override
	public EnterpriTeamSupportCompanyDO get(Integer id){
		return enterpriTeamSupportCompanyDao.get(id);
	}
	
	@Override
	public List<EnterpriTeamSupportCompanyDO> list(Map<String, Object> map){
		return enterpriTeamSupportCompanyDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return enterpriTeamSupportCompanyDao.count(map);
	}
	
	@Override
	public int save(EnterpriTeamSupportCompanyDO enterpriTeamSupportCompany){
		return enterpriTeamSupportCompanyDao.save(enterpriTeamSupportCompany);
	}
	
	@Override
	public int update(EnterpriTeamSupportCompanyDO enterpriTeamSupportCompany){
		return enterpriTeamSupportCompanyDao.update(enterpriTeamSupportCompany);
	}
	
	@Override
	public int remove(Integer id){
		return enterpriTeamSupportCompanyDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return enterpriTeamSupportCompanyDao.batchRemove(ids);
	}
	
}
