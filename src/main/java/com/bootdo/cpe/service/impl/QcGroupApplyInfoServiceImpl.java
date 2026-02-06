package com.bootdo.cpe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.cpe.dao.QcGroupApplyInfoDao;
import com.bootdo.cpe.domain.QcGroupApplyInfoDO;
import com.bootdo.cpe.service.QcGroupApplyInfoService;



@Service
public class QcGroupApplyInfoServiceImpl implements QcGroupApplyInfoService {
	@Autowired
	private QcGroupApplyInfoDao qcGroupApplyInfoDao;
	
	@Override
	public QcGroupApplyInfoDO get(Integer id){
		return qcGroupApplyInfoDao.get(id);
	}
	
	@Override
	public List<QcGroupApplyInfoDO> list(Map<String, Object> map){
		return qcGroupApplyInfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return qcGroupApplyInfoDao.count(map);
	}
	
	@Override
	public int save(QcGroupApplyInfoDO qcGroupApplyInfo){
		return qcGroupApplyInfoDao.save(qcGroupApplyInfo);
	}
	
	@Override
	public int update(QcGroupApplyInfoDO qcGroupApplyInfo){
		return qcGroupApplyInfoDao.update(qcGroupApplyInfo);
	}
	
	@Override
	public int remove(Integer id){
		return qcGroupApplyInfoDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return qcGroupApplyInfoDao.batchRemove(ids);
	}
	
}
