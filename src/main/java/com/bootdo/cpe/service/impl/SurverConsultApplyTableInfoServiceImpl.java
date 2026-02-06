package com.bootdo.cpe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.cpe.dao.SurverConsultApplyTableInfoDao;
import com.bootdo.cpe.domain.SurverConsultApplyTableInfoDO;
import com.bootdo.cpe.service.SurverConsultApplyTableInfoService;



@Service
public class SurverConsultApplyTableInfoServiceImpl implements SurverConsultApplyTableInfoService {
	@Autowired
	private SurverConsultApplyTableInfoDao surverConsultApplyTableInfoDao;
	
	@Override
	public SurverConsultApplyTableInfoDO get(Integer id){
		return surverConsultApplyTableInfoDao.get(id);
	}
	
	@Override
	public List<SurverConsultApplyTableInfoDO> list(Map<String, Object> map){
		return surverConsultApplyTableInfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return surverConsultApplyTableInfoDao.count(map);
	}
	
	@Override
	public int save(SurverConsultApplyTableInfoDO surverConsultApplyTableInfo){
		return surverConsultApplyTableInfoDao.save(surverConsultApplyTableInfo);
	}
	
	@Override
	public int update(SurverConsultApplyTableInfoDO surverConsultApplyTableInfo){
		return surverConsultApplyTableInfoDao.update(surverConsultApplyTableInfo);
	}
	
	@Override
	public int remove(Integer id){
		return surverConsultApplyTableInfoDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return surverConsultApplyTableInfoDao.batchRemove(ids);
	}
	
}
