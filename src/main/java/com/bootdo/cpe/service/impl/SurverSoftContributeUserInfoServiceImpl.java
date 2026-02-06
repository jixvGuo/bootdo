package com.bootdo.cpe.service.impl;

import com.bootdo.cpe.dao.SurverSoftContributeUserInfoDao;
import com.bootdo.cpe.service.SurverSoftContributeUserInfoService;
import com.bootdo.system.domain.SurverSoftContributeUserInfoDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;




@Service
public class SurverSoftContributeUserInfoServiceImpl implements SurverSoftContributeUserInfoService {
	@Autowired
	private SurverSoftContributeUserInfoDao surverSoftContributeUserInfoDao;
	
	@Override
	public SurverSoftContributeUserInfoDO get(Integer id){
		return surverSoftContributeUserInfoDao.get(id);
	}
	
	@Override
	public List<SurverSoftContributeUserInfoDO> list(Map<String, Object> map){
		return surverSoftContributeUserInfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return surverSoftContributeUserInfoDao.count(map);
	}
	
	@Override
	public int save(SurverSoftContributeUserInfoDO surverSoftContributeUserInfo){
		return surverSoftContributeUserInfoDao.save(surverSoftContributeUserInfo);
	}
	
	@Override
	public int update(SurverSoftContributeUserInfoDO surverSoftContributeUserInfo){
		return surverSoftContributeUserInfoDao.update(surverSoftContributeUserInfo);
	}
	
	@Override
	public int remove(Integer id){
		return surverSoftContributeUserInfoDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return surverSoftContributeUserInfoDao.batchRemove(ids);
	}
	
}
