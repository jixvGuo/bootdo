package com.bootdo.cpe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.cpe.dao.SurverReviewSoftResultDao;
import com.bootdo.cpe.domain.SurverReviewSoftResultDO;
import com.bootdo.cpe.service.SurverReviewSoftResultService;



@Service
public class SurverReviewSoftResultServiceImpl implements SurverReviewSoftResultService {
	@Autowired
	private SurverReviewSoftResultDao surverReviewSoftResultDao;
	
	@Override
	public SurverReviewSoftResultDO get(Integer id){
		return surverReviewSoftResultDao.get(id);
	}
	
	@Override
	public List<SurverReviewSoftResultDO> list(Map<String, Object> map){
		return surverReviewSoftResultDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return surverReviewSoftResultDao.count(map);
	}
	
	@Override
	public int save(SurverReviewSoftResultDO surverReviewSoftResult){
		return surverReviewSoftResultDao.save(surverReviewSoftResult);
	}
	
	@Override
	public int update(SurverReviewSoftResultDO surverReviewSoftResult){
		return surverReviewSoftResultDao.update(surverReviewSoftResult);
	}
	
	@Override
	public int remove(Integer id){
		return surverReviewSoftResultDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return surverReviewSoftResultDao.batchRemove(ids);
	}
	
}
