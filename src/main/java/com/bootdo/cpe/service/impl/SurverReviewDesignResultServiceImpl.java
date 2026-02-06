package com.bootdo.cpe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.cpe.dao.SurverReviewDesignResultDao;
import com.bootdo.cpe.domain.SurverReviewDesignResultDO;
import com.bootdo.cpe.service.SurverReviewDesignResultService;



@Service
public class SurverReviewDesignResultServiceImpl implements SurverReviewDesignResultService {
	@Autowired
	private SurverReviewDesignResultDao surverReviewDesignResultDao;
	
	@Override
	public SurverReviewDesignResultDO get(Integer id){
		return surverReviewDesignResultDao.get(id);
	}
	
	@Override
	public List<SurverReviewDesignResultDO> list(Map<String, Object> map){
		return surverReviewDesignResultDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return surverReviewDesignResultDao.count(map);
	}
	
	@Override
	public int save(SurverReviewDesignResultDO surverReviewDesignResult){
		return surverReviewDesignResultDao.save(surverReviewDesignResult);
	}
	
	@Override
	public int update(SurverReviewDesignResultDO surverReviewDesignResult){
		return surverReviewDesignResultDao.update(surverReviewDesignResult);
	}
	
	@Override
	public int remove(Integer id){
		return surverReviewDesignResultDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return surverReviewDesignResultDao.batchRemove(ids);
	}
	
}
