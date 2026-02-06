package com.bootdo.cpe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.cpe.dao.AwardProjectScoreResultDao;
import com.bootdo.cpe.domain.AwardProjectScoreResultDO;
import com.bootdo.cpe.service.AwardProjectScoreResultService;



@Service
public class AwardProjectScoreResultServiceImpl implements AwardProjectScoreResultService {
	@Autowired
	private AwardProjectScoreResultDao awardProjectScoreResultDao;
	
	@Override
	public AwardProjectScoreResultDO get(Integer id){
		return awardProjectScoreResultDao.get(id);
	}
	
	@Override
	public List<AwardProjectScoreResultDO> list(Map<String, Object> map){
		return awardProjectScoreResultDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return awardProjectScoreResultDao.count(map);
	}
	
	@Override
	public int save(AwardProjectScoreResultDO awardProjectScoreResult){
		return awardProjectScoreResultDao.save(awardProjectScoreResult);
	}
	
	@Override
	public int update(AwardProjectScoreResultDO awardProjectScoreResult){
		return awardProjectScoreResultDao.update(awardProjectScoreResult);
	}
	
	@Override
	public int remove(Integer id){
		return awardProjectScoreResultDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return awardProjectScoreResultDao.batchRemove(ids);
	}
	
}
