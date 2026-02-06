package com.bootdo.cpe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.cpe.dao.SurverSoftApplyTableInfoDao;
import com.bootdo.cpe.domain.SurverSoftApplyTableInfoDO;
import com.bootdo.cpe.service.SurverSoftApplyTableInfoService;



@Service
public class SurverSoftApplyTableInfoServiceImpl implements SurverSoftApplyTableInfoService {
	@Autowired
	private SurverSoftApplyTableInfoDao surverSoftApplyTableInfoDao;
	
	@Override
	public SurverSoftApplyTableInfoDO get(Integer id){
		return surverSoftApplyTableInfoDao.get(id);
	}
	
	@Override
	public List<SurverSoftApplyTableInfoDO> list(Map<String, Object> map){
		return surverSoftApplyTableInfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return surverSoftApplyTableInfoDao.count(map);
	}
	
	@Override
	public int save(SurverSoftApplyTableInfoDO surverSoftApplyTableInfo){
		return surverSoftApplyTableInfoDao.save(surverSoftApplyTableInfo);
	}
	
	@Override
	public int update(SurverSoftApplyTableInfoDO surverSoftApplyTableInfo){
		return surverSoftApplyTableInfoDao.update(surverSoftApplyTableInfo);
	}
	
	@Override
	public int remove(Integer id){
		return surverSoftApplyTableInfoDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return surverSoftApplyTableInfoDao.batchRemove(ids);
	}
	
}
