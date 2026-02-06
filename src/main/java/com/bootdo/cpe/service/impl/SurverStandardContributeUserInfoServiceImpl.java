package com.bootdo.cpe.service.impl;

import com.bootdo.cpe.dao.SurverStandardContributeUserInfoDao;
import com.bootdo.system.domain.SurverStandardContributeUserInfoDO;
import com.bootdo.system.service.SurverStandardContributeUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;




@Service
public class SurverStandardContributeUserInfoServiceImpl implements SurverStandardContributeUserInfoService {
	@Autowired
	private SurverStandardContributeUserInfoDao surverStandardContributeUserInfoDao;
	
	@Override
	public SurverStandardContributeUserInfoDO get(Integer id){
		return surverStandardContributeUserInfoDao.get(id);
	}
	
	@Override
	public List<SurverStandardContributeUserInfoDO> list(Map<String, Object> map){
		return surverStandardContributeUserInfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return surverStandardContributeUserInfoDao.count(map);
	}
	
	@Override
	public int save(SurverStandardContributeUserInfoDO surverStandardContributeUserInfo){
		return surverStandardContributeUserInfoDao.save(surverStandardContributeUserInfo);
	}
	
	@Override
	public int update(SurverStandardContributeUserInfoDO surverStandardContributeUserInfo){
		return surverStandardContributeUserInfoDao.update(surverStandardContributeUserInfo);
	}
	
	@Override
	public int remove(Integer id){
		return surverStandardContributeUserInfoDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return surverStandardContributeUserInfoDao.batchRemove(ids);
	}
	
}
