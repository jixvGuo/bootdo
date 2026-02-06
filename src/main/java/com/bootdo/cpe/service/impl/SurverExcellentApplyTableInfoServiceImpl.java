package com.bootdo.cpe.service.impl;

import com.bootdo.cpe.dao.SurverExcellentApplyTableInfoDao;
import com.bootdo.cpe.service.SurverExcellentApplyTableInfoService;
import com.bootdo.system.domain.SurverExcellentApplyTableInfoDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;




@Service
public class SurverExcellentApplyTableInfoServiceImpl implements SurverExcellentApplyTableInfoService {
	@Autowired
	private SurverExcellentApplyTableInfoDao surverExcellentApplyTableInfoDao;
	
	@Override
	public SurverExcellentApplyTableInfoDO get(Integer id){
		return surverExcellentApplyTableInfoDao.get(id);
	}
	
	@Override
	public List<SurverExcellentApplyTableInfoDO> list(Map<String, Object> map){
		return surverExcellentApplyTableInfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return surverExcellentApplyTableInfoDao.count(map);
	}
	
	@Override
	public int save(SurverExcellentApplyTableInfoDO surverExcellentApplyTableInfo){
		return surverExcellentApplyTableInfoDao.save(surverExcellentApplyTableInfo);
	}
	
	@Override
	public int update(SurverExcellentApplyTableInfoDO surverExcellentApplyTableInfo){
		return surverExcellentApplyTableInfoDao.update(surverExcellentApplyTableInfo);
	}
	
	@Override
	public int remove(Integer id){
		return surverExcellentApplyTableInfoDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return surverExcellentApplyTableInfoDao.batchRemove(ids);
	}
	
}
