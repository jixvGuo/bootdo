package com.bootdo.cpe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.cpe.dao.SurverEnterpriseSortInfoDao;
import com.bootdo.cpe.domain.SurverEnterpriseSortInfoDO;
import com.bootdo.cpe.service.SurverEnterpriseSortInfoService;



@Service
public class SurverEnterpriseSortInfoServiceImpl implements SurverEnterpriseSortInfoService {
	@Autowired
	private SurverEnterpriseSortInfoDao surverEnterpriseSortInfoDao;
	
	@Override
	public SurverEnterpriseSortInfoDO get(Integer id){
		return surverEnterpriseSortInfoDao.get(id);
	}
	
	@Override
	public List<SurverEnterpriseSortInfoDO> list(Map<String, Object> map){
		return surverEnterpriseSortInfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return surverEnterpriseSortInfoDao.count(map);
	}
	
	@Override
	public int save(SurverEnterpriseSortInfoDO surverEnterpriseSortInfo){
		return surverEnterpriseSortInfoDao.save(surverEnterpriseSortInfo);
	}
	
	@Override
	public int update(SurverEnterpriseSortInfoDO surverEnterpriseSortInfo){
		return surverEnterpriseSortInfoDao.update(surverEnterpriseSortInfo);
	}
	
	@Override
	public int remove(Integer id){
		return surverEnterpriseSortInfoDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return surverEnterpriseSortInfoDao.batchRemove(ids);
	}
	
}
