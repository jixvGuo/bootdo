package com.bootdo.cpe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.cpe.dao.GfMainCompleteInfoDao;
import com.bootdo.cpe.domain.GfMainCompleteInfoDO;
import com.bootdo.cpe.service.GfMainCompleteInfoService;



@Service
public class GfMainCompleteInfoServiceImpl implements GfMainCompleteInfoService {
	@Autowired
	private GfMainCompleteInfoDao gfMainCompleteInfoDao;
	
	@Override
	public GfMainCompleteInfoDO get(Integer id){
		return gfMainCompleteInfoDao.get(id);
	}
	
	@Override
	public List<GfMainCompleteInfoDO> list(Map<String, Object> map){
		return gfMainCompleteInfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return gfMainCompleteInfoDao.count(map);
	}
	
	@Override
	public int save(GfMainCompleteInfoDO gfMainCompleteInfo){
		return gfMainCompleteInfoDao.save(gfMainCompleteInfo);
	}
	
	@Override
	public int update(GfMainCompleteInfoDO gfMainCompleteInfo){
		return gfMainCompleteInfoDao.update(gfMainCompleteInfo);
	}
	
	@Override
	public int remove(Integer id){
		return gfMainCompleteInfoDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return gfMainCompleteInfoDao.batchRemove(ids);
	}
	
}
