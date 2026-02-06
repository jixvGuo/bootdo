package com.bootdo.cpe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.cpe.dao.SurverDesiginMajorMaterialsUseInfoDao;
import com.bootdo.cpe.domain.SurverDesiginMajorMaterialsUseInfoDO;
import com.bootdo.cpe.service.SurverDesiginMajorMaterialsUseInfoService;



@Service
public class SurverDesiginMajorMaterialsUseInfoServiceImpl implements SurverDesiginMajorMaterialsUseInfoService {
	@Autowired
	private SurverDesiginMajorMaterialsUseInfoDao surverDesiginMajorMaterialsUseInfoDao;
	
	@Override
	public SurverDesiginMajorMaterialsUseInfoDO get(Integer id){
		return surverDesiginMajorMaterialsUseInfoDao.get(id);
	}
	
	@Override
	public List<SurverDesiginMajorMaterialsUseInfoDO> list(Map<String, Object> map){
		return surverDesiginMajorMaterialsUseInfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return surverDesiginMajorMaterialsUseInfoDao.count(map);
	}
	
	@Override
	public int save(SurverDesiginMajorMaterialsUseInfoDO surverDesiginMajorMaterialsUseInfo){
		return surverDesiginMajorMaterialsUseInfoDao.save(surverDesiginMajorMaterialsUseInfo);
	}
	
	@Override
	public int update(SurverDesiginMajorMaterialsUseInfoDO surverDesiginMajorMaterialsUseInfo){
		return surverDesiginMajorMaterialsUseInfoDao.update(surverDesiginMajorMaterialsUseInfo);
	}
	
	@Override
	public int remove(Integer id){
		return surverDesiginMajorMaterialsUseInfoDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return surverDesiginMajorMaterialsUseInfoDao.batchRemove(ids);
	}
	
}
