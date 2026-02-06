package com.bootdo.cpe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.cpe.dao.QcReportInnovateInfoDao;
import com.bootdo.cpe.domain.QcReportInnovateInfoDO;
import com.bootdo.cpe.service.QcReportInnovateInfoService;



@Service
public class QcReportInnovateInfoServiceImpl implements QcReportInnovateInfoService {
	@Autowired
	private QcReportInnovateInfoDao qcReportInnovateInfoDao;
	
	@Override
	public QcReportInnovateInfoDO get(Integer id){
		return qcReportInnovateInfoDao.get(id);
	}
	
	@Override
	public List<QcReportInnovateInfoDO> list(Map<String, Object> map){
		return qcReportInnovateInfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return qcReportInnovateInfoDao.count(map);
	}
	
	@Override
	public int save(QcReportInnovateInfoDO qcReportInnovateInfo){
		return qcReportInnovateInfoDao.save(qcReportInnovateInfo);
	}
	
	@Override
	public int update(QcReportInnovateInfoDO qcReportInnovateInfo){
		return qcReportInnovateInfoDao.update(qcReportInnovateInfo);
	}
	
	@Override
	public int remove(Integer id){
		return qcReportInnovateInfoDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return qcReportInnovateInfoDao.batchRemove(ids);
	}
	
}
