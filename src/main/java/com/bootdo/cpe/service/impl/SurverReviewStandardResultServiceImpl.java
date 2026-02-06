package com.bootdo.cpe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.cpe.dao.SurverReviewStandardResultDao;
import com.bootdo.cpe.domain.SurverReviewStandardResultDO;
import com.bootdo.cpe.service.SurverReviewStandardResultService;



@Service
public class SurverReviewStandardResultServiceImpl implements SurverReviewStandardResultService {
	@Autowired
	private SurverReviewStandardResultDao surverReviewStandardResultDao;
	
	@Override
	public SurverReviewStandardResultDO get(Integer id){
		return surverReviewStandardResultDao.get(id);
	}
	
	@Override
	public List<SurverReviewStandardResultDO> list(Map<String, Object> map){
		return surverReviewStandardResultDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return surverReviewStandardResultDao.count(map);
	}
	
	@Override
	public int save(SurverReviewStandardResultDO surverReviewStandardResult){
		return surverReviewStandardResultDao.save(surverReviewStandardResult);
	}
	
	@Override
	public int update(SurverReviewStandardResultDO surverReviewStandardResult){
		return surverReviewStandardResultDao.update(surverReviewStandardResult);
	}
	
	@Override
	public int remove(Integer id){
		return surverReviewStandardResultDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return surverReviewStandardResultDao.batchRemove(ids);
	}
	
}
