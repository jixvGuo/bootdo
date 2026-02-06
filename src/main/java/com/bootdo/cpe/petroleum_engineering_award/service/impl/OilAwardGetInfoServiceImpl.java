package com.bootdo.cpe.petroleum_engineering_award.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.cpe.petroleum_engineering_award.dao.OilAwardGetInfoDao;
import com.bootdo.cpe.petroleum_engineering_award.domain.OilAwardGetInfoDO;
import com.bootdo.cpe.petroleum_engineering_award.service.OilAwardGetInfoService;



@Service
public class OilAwardGetInfoServiceImpl implements OilAwardGetInfoService {
	@Autowired
	private OilAwardGetInfoDao oilAwardGetInfoDao;
	
	@Override
	public OilAwardGetInfoDO get(Integer id){
		return oilAwardGetInfoDao.get(id);
	}
	
	@Override
	public List<OilAwardGetInfoDO> list(Map<String, Object> map){
		return oilAwardGetInfoDao.list(map);
	}

	@Override
	public List<OilAwardGetInfoDO> getByProId(int proId) {
		return oilAwardGetInfoDao.getByProId(proId);
	}

	@Override
	public int count(Map<String, Object> map){
		return oilAwardGetInfoDao.count(map);
	}
	
	@Override
	public int save(OilAwardGetInfoDO oilAwardGetInfo){
		return oilAwardGetInfoDao.save(oilAwardGetInfo);
	}
	
	@Override
	public int update(OilAwardGetInfoDO oilAwardGetInfo){
		return oilAwardGetInfoDao.update(oilAwardGetInfo);
	}
	
	@Override
	public int remove(Integer id){
		return oilAwardGetInfoDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return oilAwardGetInfoDao.batchRemove(ids);
	}
	
}
