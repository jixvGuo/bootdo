package com.bootdo.cpe.petroleum_engineering_award.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.cpe.petroleum_engineering_award.dao.OilProUnitOpinionDao;
import com.bootdo.cpe.petroleum_engineering_award.domain.OilProUnitOpinionDO;
import com.bootdo.cpe.petroleum_engineering_award.service.OilProUnitOpinionService;



@Service
public class OilProUnitOpinionServiceImpl implements OilProUnitOpinionService {
	@Autowired
	private OilProUnitOpinionDao oilProUnitOpinionDao;
	
	@Override
	public OilProUnitOpinionDO get(String id){
		return oilProUnitOpinionDao.get(id);
	}

	@Override
	public List<OilProUnitOpinionDO> getByProId(int proId) {
		return oilProUnitOpinionDao.getByProId(proId);
	}

	@Override
	public List<OilProUnitOpinionDO> list(Map<String, Object> map){
		return oilProUnitOpinionDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return oilProUnitOpinionDao.count(map);
	}
	
	@Override
	public int save(OilProUnitOpinionDO oilProUnitOpinion){
		return oilProUnitOpinionDao.save(oilProUnitOpinion);
	}
	
	@Override
	public int update(OilProUnitOpinionDO oilProUnitOpinion){
		return oilProUnitOpinionDao.update(oilProUnitOpinion);
	}
	
	@Override
	public int remove(String id){
		return oilProUnitOpinionDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return oilProUnitOpinionDao.batchRemove(ids);
	}
	
}
