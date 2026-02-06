package com.bootdo.cpe.petroleum_engineering_award.service.impl;

import com.bootdo.cpe.petroleum_engineering_award.domain.ReviewInstallProBaseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.cpe.petroleum_engineering_award.dao.OilProInstallReviewRecordDao;
import com.bootdo.cpe.petroleum_engineering_award.domain.OilProInstallReviewRecordDO;
import com.bootdo.cpe.petroleum_engineering_award.service.OilProInstallReviewRecordService;



@Service
public class OilProInstallReviewRecordServiceImpl implements OilProInstallReviewRecordService {
	@Autowired
	private OilProInstallReviewRecordDao oilProInstallReviewRecordDao;
	
	@Override
	public OilProInstallReviewRecordDO get(Integer id){
		return oilProInstallReviewRecordDao.get(id);
	}
	
	@Override
	public List<OilProInstallReviewRecordDO> list(Map<String, Object> map){
		return oilProInstallReviewRecordDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return oilProInstallReviewRecordDao.count(map);
	}
	
	@Override
	public int save(OilProInstallReviewRecordDO oilProInstallReviewRecord){
		return oilProInstallReviewRecordDao.save(oilProInstallReviewRecord);
	}
	
	@Override
	public int update(OilProInstallReviewRecordDO oilProInstallReviewRecord){
		return oilProInstallReviewRecordDao.update(oilProInstallReviewRecord);
	}
	
	@Override
	public int remove(Integer id){
		return oilProInstallReviewRecordDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return oilProInstallReviewRecordDao.batchRemove(ids);
	}

	@Override
	public ReviewInstallProBaseInfo getReviewProBaseInfoByProId(int proId) {
		List<ReviewInstallProBaseInfo> list = oilProInstallReviewRecordDao.getReviewProBaseInfoByProId(proId);
		return list.size() > 0 ? list.get(0) : null;
	}
}
