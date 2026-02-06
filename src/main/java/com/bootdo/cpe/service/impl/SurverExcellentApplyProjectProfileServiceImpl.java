package com.bootdo.cpe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.cpe.dao.SurverExcellentApplyProjectProfileDao;
import com.bootdo.cpe.domain.SurverExcellentApplyProjectProfileDO;
import com.bootdo.cpe.service.SurverExcellentApplyProjectProfileService;



@Service
public class SurverExcellentApplyProjectProfileServiceImpl implements SurverExcellentApplyProjectProfileService {
	@Autowired
	private SurverExcellentApplyProjectProfileDao surverExcellentApplyProjectProfileDao;
	
	@Override
	public SurverExcellentApplyProjectProfileDO get(Integer id){
		return surverExcellentApplyProjectProfileDao.get(id);
	}
	
	@Override
	public List<SurverExcellentApplyProjectProfileDO> list(Map<String, Object> map){
		return surverExcellentApplyProjectProfileDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return surverExcellentApplyProjectProfileDao.count(map);
	}
	
	@Override
	public int save(SurverExcellentApplyProjectProfileDO surverExcellentApplyProjectProfile){
		return surverExcellentApplyProjectProfileDao.save(surverExcellentApplyProjectProfile);
	}
	
	@Override
	public int update(SurverExcellentApplyProjectProfileDO surverExcellentApplyProjectProfile){
		return surverExcellentApplyProjectProfileDao.update(surverExcellentApplyProjectProfile);
	}
	
	@Override
	public int remove(Integer id){
		return surverExcellentApplyProjectProfileDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return surverExcellentApplyProjectProfileDao.batchRemove(ids);
	}
	
}
