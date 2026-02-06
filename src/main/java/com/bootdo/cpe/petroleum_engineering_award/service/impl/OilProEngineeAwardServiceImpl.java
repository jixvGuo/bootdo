package com.bootdo.cpe.petroleum_engineering_award.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.cpe.petroleum_engineering_award.dao.OilProEngineeAwardDao;
import com.bootdo.cpe.petroleum_engineering_award.domain.OilProEngineeAwardDO;
import com.bootdo.cpe.petroleum_engineering_award.service.OilProEngineeAwardService;



@Service
public class OilProEngineeAwardServiceImpl implements OilProEngineeAwardService {
	@Autowired
	private OilProEngineeAwardDao oilProEngineeAwardDao;
	
	@Override
	public OilProEngineeAwardDO get(Integer id){
		return oilProEngineeAwardDao.get(id);
	}

	@Override
	public List<OilProEngineeAwardDO> getByProId(int proId) {
		return oilProEngineeAwardDao.getByProId(proId);
	}

	@Override
	public List<OilProEngineeAwardDO> list(Map<String, Object> map){
		return oilProEngineeAwardDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return oilProEngineeAwardDao.count(map);
	}
	
	@Override
	public int save(OilProEngineeAwardDO oilProEngineeAward){
		return oilProEngineeAwardDao.save(oilProEngineeAward);
	}
	
	@Override
	public int update(OilProEngineeAwardDO oilProEngineeAward){
		return oilProEngineeAwardDao.update(oilProEngineeAward);
	}
	
	@Override
	public int remove(Integer id){
		return oilProEngineeAwardDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return oilProEngineeAwardDao.batchRemove(ids);
	}
	
}
