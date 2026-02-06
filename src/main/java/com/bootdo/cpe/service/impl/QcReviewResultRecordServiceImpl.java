package com.bootdo.cpe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.cpe.dao.QcReviewResultRecordDao;
import com.bootdo.cpe.domain.QcReviewResultRecordDO;
import com.bootdo.cpe.service.QcReviewResultRecordService;



@Service
public class QcReviewResultRecordServiceImpl implements QcReviewResultRecordService {
	@Autowired
	private QcReviewResultRecordDao qcReviewResultRecordDao;
	
	@Override
	public QcReviewResultRecordDO get(Integer id){
		return qcReviewResultRecordDao.get(id);
	}
	
	@Override
	public List<QcReviewResultRecordDO> list(Map<String, Object> map){
		return qcReviewResultRecordDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return qcReviewResultRecordDao.count(map);
	}
	
	@Override
	public int save(QcReviewResultRecordDO qcReviewResultRecord){
		return qcReviewResultRecordDao.save(qcReviewResultRecord);
	}
	
	@Override
	public int update(QcReviewResultRecordDO qcReviewResultRecord){
		return qcReviewResultRecordDao.update(qcReviewResultRecord);
	}
	
	@Override
	public int remove(Integer id){
		return qcReviewResultRecordDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return qcReviewResultRecordDao.batchRemove(ids);
	}
	
}
