package com.bootdo.cpe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.cpe.dao.SurverStandardApplyProjectProfileDao;
import com.bootdo.cpe.domain.SurverStandardApplyProjectProfileDO;
import com.bootdo.cpe.service.SurverStandardApplyProjectProfileService;



@Service
public class SurverStandardApplyProjectProfileServiceImpl implements SurverStandardApplyProjectProfileService {
	@Autowired
	private SurverStandardApplyProjectProfileDao surverStandardApplyProjectProfileDao;
	
	@Override
	public SurverStandardApplyProjectProfileDO get(Integer id){
		return surverStandardApplyProjectProfileDao.get(id);
	}
	
	@Override
	public List<SurverStandardApplyProjectProfileDO> list(Map<String, Object> map){
		return surverStandardApplyProjectProfileDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return surverStandardApplyProjectProfileDao.count(map);
	}
	
	@Override
	public int save(SurverStandardApplyProjectProfileDO surverStandardApplyProjectProfile){
		return surverStandardApplyProjectProfileDao.save(surverStandardApplyProjectProfile);
	}
	
	@Override
	public int update(SurverStandardApplyProjectProfileDO surverStandardApplyProjectProfile){
		return surverStandardApplyProjectProfileDao.update(surverStandardApplyProjectProfile);
	}
	
	@Override
	public int remove(Integer id){
		return surverStandardApplyProjectProfileDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return surverStandardApplyProjectProfileDao.batchRemove(ids);
	}
	
}
