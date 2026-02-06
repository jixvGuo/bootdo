package com.bootdo.cpe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.cpe.dao.QcReportSolveInfoDao;
import com.bootdo.cpe.domain.QcReportSolveInfoDO;
import com.bootdo.cpe.service.QcReportSolveInfoService;



@Service
public class QcReportSolveInfoServiceImpl implements QcReportSolveInfoService {
	@Autowired
	private QcReportSolveInfoDao qcReportSolveInfoDao;
	
	@Override
	public QcReportSolveInfoDO get(Integer id){
		return qcReportSolveInfoDao.get(id);
	}
	
	@Override
	public List<QcReportSolveInfoDO> list(Map<String, Object> map){
		return qcReportSolveInfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return qcReportSolveInfoDao.count(map);
	}
	
	@Override
	public int save(QcReportSolveInfoDO qcReportSolveInfo){
		return qcReportSolveInfoDao.save(qcReportSolveInfo);
	}
	
	@Override
	public int update(QcReportSolveInfoDO qcReportSolveInfo){
		return qcReportSolveInfoDao.update(qcReportSolveInfo);
	}
	
	@Override
	public int remove(Integer id){
		return qcReportSolveInfoDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return qcReportSolveInfoDao.batchRemove(ids);
	}
	
}
