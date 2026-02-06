package com.bootdo.cpe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.cpe.dao.QcResultInnovateScoreDao;
import com.bootdo.cpe.domain.QcResultInnovateScoreDO;
import com.bootdo.cpe.service.QcResultInnovateScoreService;



@Service
public class QcResultInnovateScoreServiceImpl implements QcResultInnovateScoreService {
	@Autowired
	private QcResultInnovateScoreDao qcResultInnovateScoreDao;
	
	@Override
	public QcResultInnovateScoreDO get(Integer id){
		return qcResultInnovateScoreDao.get(id);
	}
	
	@Override
	public List<QcResultInnovateScoreDO> list(Map<String, Object> map){
		return qcResultInnovateScoreDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return qcResultInnovateScoreDao.count(map);
	}
	
	@Override
	public int save(QcResultInnovateScoreDO qcResultInnovateScore){
		return qcResultInnovateScoreDao.save(qcResultInnovateScore);
	}
	
	@Override
	public int update(QcResultInnovateScoreDO qcResultInnovateScore){
		return qcResultInnovateScoreDao.update(qcResultInnovateScore);
	}
	
	@Override
	public int remove(Integer id){
		return qcResultInnovateScoreDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return qcResultInnovateScoreDao.batchRemove(ids);
	}
	
}
