package com.bootdo.cpe.petroleum_engineering_award.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.cpe.petroleum_engineering_award.dao.OilQualityProSituationDao;
import com.bootdo.cpe.petroleum_engineering_award.domain.OilQualityProSituationDO;
import com.bootdo.cpe.petroleum_engineering_award.service.OilQualityProSituationService;



@Service
public class OilQualityProSituationServiceImpl implements OilQualityProSituationService {
	@Autowired
	private OilQualityProSituationDao oilQualityProSituationDao;
	
	@Override
	public OilQualityProSituationDO get(Integer id){
		return oilQualityProSituationDao.get(id);
	}
	
	@Override
	public List<OilQualityProSituationDO> list(Map<String, Object> map){
		return oilQualityProSituationDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return oilQualityProSituationDao.count(map);
	}
	
	@Override
	public int save(OilQualityProSituationDO oilQualityProSituation){
		return oilQualityProSituationDao.save(oilQualityProSituation);
	}
	
	@Override
	public int update(OilQualityProSituationDO oilQualityProSituation){
		return oilQualityProSituationDao.update(oilQualityProSituation);
	}
	
	@Override
	public int remove(Integer id){
		return oilQualityProSituationDao.remove(id);
	}

	/**
	 * 移除项目下的概况文件
	 *
	 * @param proId
	 * @return
	 */
	@Override
	public int removeByProId(int proId) {
		return oilQualityProSituationDao.removeByProId(proId);
	}

	@Override
	public int batchRemove(Integer[] ids){
		return oilQualityProSituationDao.batchRemove(ids);
	}
	
}
