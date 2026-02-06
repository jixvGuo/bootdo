package com.bootdo.cpe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.cpe.dao.QcSurveyStatisticInfoDao;
import com.bootdo.cpe.domain.QcSurveyStatisticInfoDO;
import com.bootdo.cpe.service.QcSurveyStatisticInfoService;



@Service
public class QcSurveyStatisticInfoServiceImpl implements QcSurveyStatisticInfoService {
	@Autowired
	private QcSurveyStatisticInfoDao qcSurveyStatisticInfoDao;
	
	@Override
	public QcSurveyStatisticInfoDO get(Integer id){
		return qcSurveyStatisticInfoDao.get(id);
	}
	
	@Override
	public List<QcSurveyStatisticInfoDO> list(Map<String, Object> map){
		return qcSurveyStatisticInfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return qcSurveyStatisticInfoDao.count(map);
	}
	
	@Override
	public int save(QcSurveyStatisticInfoDO qcSurveyStatisticInfo){
		return qcSurveyStatisticInfoDao.save(qcSurveyStatisticInfo);
	}
	
	@Override
	public int update(QcSurveyStatisticInfoDO qcSurveyStatisticInfo){
		return qcSurveyStatisticInfoDao.update(qcSurveyStatisticInfo);
	}
	
	@Override
	public int remove(Integer id){
		return qcSurveyStatisticInfoDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return qcSurveyStatisticInfoDao.batchRemove(ids);
	}
	
}
