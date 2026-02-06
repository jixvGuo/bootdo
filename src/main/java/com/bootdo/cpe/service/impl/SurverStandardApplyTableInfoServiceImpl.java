package com.bootdo.cpe.service.impl;

import com.bootdo.cpe.dao.SurverStandardApplyTableInfoDao;
import com.bootdo.cpe.domain.SurverStandardApplyTableInfoDO;
import com.bootdo.cpe.service.SurverStandardApplyTableInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;




@Service
public class SurverStandardApplyTableInfoServiceImpl implements SurverStandardApplyTableInfoService {
	@Autowired
	private SurverStandardApplyTableInfoDao surverStandardApplyTableInfoDao;

	@Override
	public SurverStandardApplyTableInfoDO get(Integer id){
		return surverStandardApplyTableInfoDao.get(id);
	}

	@Override
	public List<SurverStandardApplyTableInfoDO> list(Map<String, Object> map){
		return surverStandardApplyTableInfoDao.list(map);
	}

	@Override
	public int count(Map<String, Object> map){
		return surverStandardApplyTableInfoDao.count(map);
	}

	@Override
	public int save(SurverStandardApplyTableInfoDO surverStandardApplyTableInfo){
		return surverStandardApplyTableInfoDao.save(surverStandardApplyTableInfo);
	}

	@Override
	public int update(SurverStandardApplyTableInfoDO surverStandardApplyTableInfo){
		return surverStandardApplyTableInfoDao.update(surverStandardApplyTableInfo);
	}

	@Override
	public int remove(Integer id){
		return surverStandardApplyTableInfoDao.remove(id);
	}

	@Override
	public int batchRemove(Integer[] ids){
		return surverStandardApplyTableInfoDao.batchRemove(ids);
	}

}
