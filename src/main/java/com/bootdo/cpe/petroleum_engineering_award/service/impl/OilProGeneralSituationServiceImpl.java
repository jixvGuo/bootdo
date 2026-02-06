package com.bootdo.cpe.petroleum_engineering_award.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.cpe.petroleum_engineering_award.dao.OilProGeneralSituationDao;
import com.bootdo.cpe.petroleum_engineering_award.domain.OilProGeneralSituationDO;
import com.bootdo.cpe.petroleum_engineering_award.service.OilProGeneralSituationService;



@Service
public class OilProGeneralSituationServiceImpl implements OilProGeneralSituationService {
	@Autowired
	private OilProGeneralSituationDao oilProGeneralSituationDao;
	
	@Override
	public OilProGeneralSituationDO get(Integer id){
		return oilProGeneralSituationDao.get(id);
	}

	@Override
	public List<OilProGeneralSituationDO> getByProId(int proId) {
		return oilProGeneralSituationDao.getByProId(proId);
	}

	@Override
	public List<OilProGeneralSituationDO> list(Map<String, Object> map){
		return oilProGeneralSituationDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return oilProGeneralSituationDao.count(map);
	}
	
	@Override
	public int save(OilProGeneralSituationDO oilProGeneralSituation){
		return oilProGeneralSituationDao.save(oilProGeneralSituation);
	}
	
	@Override
	public int update(OilProGeneralSituationDO oilProGeneralSituation){
		return oilProGeneralSituationDao.update(oilProGeneralSituation);
	}
	
	@Override
	public int remove(Integer id){
		return oilProGeneralSituationDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return oilProGeneralSituationDao.batchRemove(ids);
	}
	
}
