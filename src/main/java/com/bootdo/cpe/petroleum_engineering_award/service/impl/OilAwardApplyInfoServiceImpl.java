package com.bootdo.cpe.petroleum_engineering_award.service.impl;

import com.bootdo.cpe.petroleum_engineering_award.domain.OilQualityProBaseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.cpe.petroleum_engineering_award.dao.OilAwardApplyInfoDao;
import com.bootdo.cpe.petroleum_engineering_award.domain.OilAwardApplyInfoDO;
import com.bootdo.cpe.petroleum_engineering_award.service.OilAwardApplyInfoService;



@Service
public class OilAwardApplyInfoServiceImpl implements OilAwardApplyInfoService {
	@Autowired
	private OilAwardApplyInfoDao oilAwardApplyInfoDao;
	
	@Override
	public OilAwardApplyInfoDO get(Integer id){
		return oilAwardApplyInfoDao.get(id);
	}
	
	@Override
	public List<OilAwardApplyInfoDO> list(Map<String, Object> map){
		return oilAwardApplyInfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return oilAwardApplyInfoDao.count(map);
	}
	
	@Override
	public int save(OilAwardApplyInfoDO oilAwardApplyInfo){
		return oilAwardApplyInfoDao.save(oilAwardApplyInfo);
	}
	
	@Override
	public int update(OilAwardApplyInfoDO oilAwardApplyInfo){
		return oilAwardApplyInfoDao.update(oilAwardApplyInfo);
	}
	
	@Override
	public int remove(Integer id){
		return oilAwardApplyInfoDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return oilAwardApplyInfoDao.batchRemove(ids);
	}

	@Override
	public OilQualityProBaseInfo getQualityProBaseInfo(int proId) {
		List<OilQualityProBaseInfo> list = oilAwardApplyInfoDao.getQualityProBaseInfo(proId);
		return list.size() > 0 ? list.get(0) : null;
	}
}
