package com.bootdo.cpe.service.impl;

import com.bootdo.cpe.dao.SurverExcellentApplyMainInfoDao;
import com.bootdo.cpe.domain.SurverExcellentApplyMainInfoDO;
import com.bootdo.cpe.service.SurverExcellentApplyMainInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



@Service
public class SurverExcellentApplyMainInfoServiceImpl implements SurverExcellentApplyMainInfoService {
	@Autowired
	private SurverExcellentApplyMainInfoDao surverExcellentApplyMainInfoDao;
	
	@Override
	public SurverExcellentApplyMainInfoDO get(Integer id){
		return surverExcellentApplyMainInfoDao.get(id);
	}
	
	@Override
	public List<SurverExcellentApplyMainInfoDO> list(Map<String, Object> map){
		return surverExcellentApplyMainInfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return surverExcellentApplyMainInfoDao.count(map);
	}
	
	@Override
	public int save(SurverExcellentApplyMainInfoDO surverExcellentApplyMainInfo){
		return surverExcellentApplyMainInfoDao.save(surverExcellentApplyMainInfo);
	}
	
	@Override
	public int update(SurverExcellentApplyMainInfoDO surverExcellentApplyMainInfo){
		return surverExcellentApplyMainInfoDao.update(surverExcellentApplyMainInfo);
	}
	
	@Override
	public int remove(Integer id){
		return surverExcellentApplyMainInfoDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return surverExcellentApplyMainInfoDao.batchRemove(ids);
	}
	
}
