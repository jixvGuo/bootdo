package com.bootdo.cpe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.cpe.dao.SurverBaseApplyTableInfoDao;
import com.bootdo.cpe.domain.SurverBaseApplyTableInfoDO;
import com.bootdo.cpe.service.SurverBaseApplyTableInfoService;



@Service
public class SurverBaseApplyTableInfoServiceImpl implements SurverBaseApplyTableInfoService {
	@Autowired
	private SurverBaseApplyTableInfoDao surverBaseApplyTableInfoDao;
	
	@Override
	public SurverBaseApplyTableInfoDO get(Integer id){
		return surverBaseApplyTableInfoDao.get(id);
	}
	
	@Override
	public List<SurverBaseApplyTableInfoDO> list(Map<String, Object> map){
		return surverBaseApplyTableInfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return surverBaseApplyTableInfoDao.count(map);
	}
	
	@Override
	public int save(SurverBaseApplyTableInfoDO surverBaseApplyTableInfo){
		return surverBaseApplyTableInfoDao.save(surverBaseApplyTableInfo);
	}
	
	@Override
	public int update(SurverBaseApplyTableInfoDO surverBaseApplyTableInfo){
		return surverBaseApplyTableInfoDao.update(surverBaseApplyTableInfo);
	}
	
	@Override
	public int remove(Integer id){
		return surverBaseApplyTableInfoDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return surverBaseApplyTableInfoDao.batchRemove(ids);
	}
	
}
