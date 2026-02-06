package com.bootdo.cpe.petroleum_engineering_award.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.cpe.petroleum_engineering_award.dao.OilAwardPartakeUnitDao;
import com.bootdo.cpe.petroleum_engineering_award.domain.OilAwardPartakeUnitDO;
import com.bootdo.cpe.petroleum_engineering_award.service.OilAwardPartakeUnitService;



@Service
public class OilAwardPartakeUnitServiceImpl implements OilAwardPartakeUnitService {
	@Autowired
	private OilAwardPartakeUnitDao oilAwardPartakeUnitDao;
	
	@Override
	public OilAwardPartakeUnitDO get(Integer id){
		return oilAwardPartakeUnitDao.get(id);
	}
	
	@Override
	public List<OilAwardPartakeUnitDO> list(Map<String, Object> map){
		return oilAwardPartakeUnitDao.list(map);
	}

	@Override
	public List<OilAwardPartakeUnitDO> getByProId(int proId) {
		return oilAwardPartakeUnitDao.getByProId(proId);
	}

	@Override
	public int count(Map<String, Object> map){
		return oilAwardPartakeUnitDao.count(map);
	}
	
	@Override
	public int save(OilAwardPartakeUnitDO oilAwardPartakeUnit){
		return oilAwardPartakeUnitDao.save(oilAwardPartakeUnit);
	}
	
	@Override
	public int update(OilAwardPartakeUnitDO oilAwardPartakeUnit){
		return oilAwardPartakeUnitDao.update(oilAwardPartakeUnit);
	}
	
	@Override
	public int remove(Integer id){
		return oilAwardPartakeUnitDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return oilAwardPartakeUnitDao.batchRemove(ids);
	}
	
}
