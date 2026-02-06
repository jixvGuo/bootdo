package com.bootdo.cpe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.cpe.dao.QcAppraiseActiveScoreDao;
import com.bootdo.cpe.domain.QcAppraiseActiveScoreDO;
import com.bootdo.cpe.service.QcAppraiseActiveScoreService;



@Service
public class QcAppraiseActiveScoreServiceImpl implements QcAppraiseActiveScoreService {
	@Autowired
	private QcAppraiseActiveScoreDao qcAppraiseActiveScoreDao;
	
	@Override
	public QcAppraiseActiveScoreDO get(Integer id){
		return qcAppraiseActiveScoreDao.get(id);
	}
	
	@Override
	public List<QcAppraiseActiveScoreDO> list(Map<String, Object> map){
		return qcAppraiseActiveScoreDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return qcAppraiseActiveScoreDao.count(map);
	}
	
	@Override
	public int save(QcAppraiseActiveScoreDO qcAppraiseActiveScore){
		return qcAppraiseActiveScoreDao.save(qcAppraiseActiveScore);
	}
	
	@Override
	public int update(QcAppraiseActiveScoreDO qcAppraiseActiveScore){
		return qcAppraiseActiveScoreDao.update(qcAppraiseActiveScore);
	}
	
	@Override
	public int remove(Integer id){
		return qcAppraiseActiveScoreDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return qcAppraiseActiveScoreDao.batchRemove(ids);
	}
	
}
