package com.bootdo.cpe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.cpe.dao.SurverDesiginContributeUserInfoDao;
import com.bootdo.cpe.domain.SurverDesiginContributeUserInfoDO;
import com.bootdo.cpe.service.SurverDesiginContributeUserInfoService;



@Service
public class SurverDesiginContributeUserInfoServiceImpl implements SurverDesiginContributeUserInfoService {
	@Autowired
	private SurverDesiginContributeUserInfoDao surverDesiginContributeUserInfoDao;
	
	@Override
	public SurverDesiginContributeUserInfoDO get(Integer id){
		return surverDesiginContributeUserInfoDao.get(id);
	}
	
	@Override
	public List<SurverDesiginContributeUserInfoDO> list(Map<String, Object> map){
		return surverDesiginContributeUserInfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return surverDesiginContributeUserInfoDao.count(map);
	}
	
	@Override
	public int save(SurverDesiginContributeUserInfoDO surverDesiginContributeUserInfo){
		return surverDesiginContributeUserInfoDao.save(surverDesiginContributeUserInfo);
	}
	
	@Override
	public int update(SurverDesiginContributeUserInfoDO surverDesiginContributeUserInfo){
		return surverDesiginContributeUserInfoDao.update(surverDesiginContributeUserInfo);
	}
	
	@Override
	public int remove(Integer id){
		return surverDesiginContributeUserInfoDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return surverDesiginContributeUserInfoDao.batchRemove(ids);
	}
	
}
