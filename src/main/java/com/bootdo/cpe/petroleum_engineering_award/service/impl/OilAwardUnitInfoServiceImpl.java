package com.bootdo.cpe.petroleum_engineering_award.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.cpe.petroleum_engineering_award.dao.OilAwardUnitInfoDao;
import com.bootdo.cpe.petroleum_engineering_award.domain.OilAwardUnitInfoDO;
import com.bootdo.cpe.petroleum_engineering_award.service.OilAwardUnitInfoService;



@Service
public class OilAwardUnitInfoServiceImpl implements OilAwardUnitInfoService {
	@Autowired
	private OilAwardUnitInfoDao oilAwardUnitInfoDao;
	
	@Override
	public OilAwardUnitInfoDO get(Integer id){
		return oilAwardUnitInfoDao.get(id);
	}
	
	@Override
	public List<OilAwardUnitInfoDO> list(Map<String, Object> map){
		return oilAwardUnitInfoDao.list(map);
	}

	@Override
	public List<OilAwardUnitInfoDO> getByProId(int proId) {
		return oilAwardUnitInfoDao.getByProId(proId);
	}

	@Override
	public int count(Map<String, Object> map){
		return oilAwardUnitInfoDao.count(map);
	}
	
	@Override
	public int save(OilAwardUnitInfoDO oilAwardUnitInfo){
		return oilAwardUnitInfoDao.save(oilAwardUnitInfo);
	}
	
	@Override
	public int update(OilAwardUnitInfoDO oilAwardUnitInfo){
		return oilAwardUnitInfoDao.update(oilAwardUnitInfo);
	}
	
	@Override
	public int remove(Integer id){
		return oilAwardUnitInfoDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return oilAwardUnitInfoDao.batchRemove(ids);
	}
	
}
