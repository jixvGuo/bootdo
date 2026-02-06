package com.bootdo.cpe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.cpe.dao.SurverReviewSurverResultDao;
import com.bootdo.cpe.domain.SurverReviewSurverResultDO;
import com.bootdo.cpe.service.SurverReviewSurverResultService;



@Service
public class SurverReviewSurverResultServiceImpl implements SurverReviewSurverResultService {
	@Autowired
	private SurverReviewSurverResultDao surverReviewSurverResultDao;
	
	@Override
	public SurverReviewSurverResultDO get(Integer id){
		return surverReviewSurverResultDao.get(id);
	}
	
	@Override
	public List<SurverReviewSurverResultDO> list(Map<String, Object> map){
		return surverReviewSurverResultDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return surverReviewSurverResultDao.count(map);
	}
	
	@Override
	public int save(SurverReviewSurverResultDO surverReviewSurverResult){
		return surverReviewSurverResultDao.save(surverReviewSurverResult);
	}
	
	@Override
	public int update(SurverReviewSurverResultDO surverReviewSurverResult){
		return surverReviewSurverResultDao.update(surverReviewSurverResult);
	}
	
	@Override
	public int remove(Integer id){
		return surverReviewSurverResultDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return surverReviewSurverResultDao.batchRemove(ids);
	}
	
}
