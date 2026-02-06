package com.bootdo.cpe.petroleum_engineering_award.service.impl;

import com.bootdo.cpe.petroleum_engineering_award.domain.ReviewInstallProBaseInfo;
import com.bootdo.cpe.petroleum_engineering_award.domain.ReviewQualityProBaseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.cpe.petroleum_engineering_award.dao.OilProQualityReviewRecordDao;
import com.bootdo.cpe.petroleum_engineering_award.domain.OilProQualityReviewRecordDO;
import com.bootdo.cpe.petroleum_engineering_award.service.OilProQualityReviewRecordService;



@Service
public class OilProQualityReviewRecordServiceImpl implements OilProQualityReviewRecordService {
	@Autowired
	private OilProQualityReviewRecordDao oilProQualityReviewRecordDao;
	
	@Override
	public OilProQualityReviewRecordDO get(Integer id){
		return oilProQualityReviewRecordDao.get(id);
	}
	
	@Override
	public List<OilProQualityReviewRecordDO> list(Map<String, Object> map){
		return oilProQualityReviewRecordDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return oilProQualityReviewRecordDao.count(map);
	}
	
	@Override
	public int save(OilProQualityReviewRecordDO oilProQualityReviewRecord){
		return oilProQualityReviewRecordDao.save(oilProQualityReviewRecord);
	}
	
	@Override
	public int update(OilProQualityReviewRecordDO oilProQualityReviewRecord){
		return oilProQualityReviewRecordDao.update(oilProQualityReviewRecord);
	}
	
	@Override
	public int remove(Integer id){
		return oilProQualityReviewRecordDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return oilProQualityReviewRecordDao.batchRemove(ids);
	}

	@Override
	public ReviewQualityProBaseInfo getReviewProBaseInfoByProId(int proId) {
		List<ReviewQualityProBaseInfo> list = oilProQualityReviewRecordDao.getReviewProBaseInfoByProId(proId);
		return list.size() > 0 ? list.get(0) : null;
	}
}
