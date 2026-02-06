package com.bootdo.cpe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.cpe.dao.SurverSoftApplyProjectProfileDao;
import com.bootdo.cpe.domain.SurverSoftApplyProjectProfileDO;
import com.bootdo.cpe.service.SurverSoftApplyProjectProfileService;



@Service
public class SurverSoftApplyProjectProfileServiceImpl implements SurverSoftApplyProjectProfileService {
	@Autowired
	private SurverSoftApplyProjectProfileDao surverSoftApplyProjectProfileDao;
	
	@Override
	public SurverSoftApplyProjectProfileDO get(Integer id){
		return surverSoftApplyProjectProfileDao.get(id);
	}
	
	@Override
	public List<SurverSoftApplyProjectProfileDO> list(Map<String, Object> map){
		return surverSoftApplyProjectProfileDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return surverSoftApplyProjectProfileDao.count(map);
	}
	
	@Override
	public int save(SurverSoftApplyProjectProfileDO surverSoftApplyProjectProfile){
		return surverSoftApplyProjectProfileDao.save(surverSoftApplyProjectProfile);
	}
	
	@Override
	public int update(SurverSoftApplyProjectProfileDO surverSoftApplyProjectProfile){
		return surverSoftApplyProjectProfileDao.update(surverSoftApplyProjectProfile);
	}
	
	@Override
	public int remove(Integer id){
		return surverSoftApplyProjectProfileDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return surverSoftApplyProjectProfileDao.batchRemove(ids);
	}
	
}
