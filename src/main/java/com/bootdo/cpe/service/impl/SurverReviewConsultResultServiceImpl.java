package com.bootdo.cpe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.cpe.dao.SurverReviewConsultResultDao;
import com.bootdo.cpe.domain.SurverReviewConsultResultDO;
import com.bootdo.cpe.service.SurverReviewConsultResultService;



@Service
public class SurverReviewConsultResultServiceImpl implements SurverReviewConsultResultService {
	@Autowired
	private SurverReviewConsultResultDao surverReviewConsultResultDao;
	
	@Override
	public SurverReviewConsultResultDO get(Integer id){
		return surverReviewConsultResultDao.get(id);
	}
	
	@Override
	public List<SurverReviewConsultResultDO> list(Map<String, Object> map){
		return surverReviewConsultResultDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return surverReviewConsultResultDao.count(map);
	}
	
	@Override
	public int save(SurverReviewConsultResultDO surverReviewConsultResult){
		return surverReviewConsultResultDao.save(surverReviewConsultResult);
	}
	
	@Override
	public int update(SurverReviewConsultResultDO surverReviewConsultResult){
		return surverReviewConsultResultDao.update(surverReviewConsultResult);
	}
	
	@Override
	public int remove(Integer id){
		return surverReviewConsultResultDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return surverReviewConsultResultDao.batchRemove(ids);
	}
	
}
