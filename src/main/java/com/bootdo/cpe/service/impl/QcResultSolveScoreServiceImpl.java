package com.bootdo.cpe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.cpe.dao.QcResultSolveScoreDao;
import com.bootdo.cpe.domain.QcResultSolveScoreDO;
import com.bootdo.cpe.service.QcResultSolveScoreService;



@Service
public class QcResultSolveScoreServiceImpl implements QcResultSolveScoreService {
	@Autowired
	private QcResultSolveScoreDao qcResultSolveScoreDao;
	
	@Override
	public QcResultSolveScoreDO get(Integer id){
		return qcResultSolveScoreDao.get(id);
	}
	
	@Override
	public List<QcResultSolveScoreDO> list(Map<String, Object> map){
		return qcResultSolveScoreDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return qcResultSolveScoreDao.count(map);
	}
	
	@Override
	public int save(QcResultSolveScoreDO qcResultSolveScore){
		return qcResultSolveScoreDao.save(qcResultSolveScore);
	}
	
	@Override
	public int update(QcResultSolveScoreDO qcResultSolveScore){
		return qcResultSolveScoreDao.update(qcResultSolveScore);
	}
	
	@Override
	public int remove(Integer id){
		return qcResultSolveScoreDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return qcResultSolveScoreDao.batchRemove(ids);
	}
	
}
