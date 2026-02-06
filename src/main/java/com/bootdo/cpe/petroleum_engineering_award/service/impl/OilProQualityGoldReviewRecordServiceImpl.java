package com.bootdo.cpe.petroleum_engineering_award.service.impl;

import com.bootdo.cpe.petroleum_engineering_award.domain.ReviewQualityProBaseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.cpe.petroleum_engineering_award.dao.OilProQualityGoldReviewRecordDao;
import com.bootdo.cpe.petroleum_engineering_award.domain.OilProQualityGoldReviewRecordDO;
import com.bootdo.cpe.petroleum_engineering_award.service.OilProQualityGoldReviewRecordService;



@Service
public class OilProQualityGoldReviewRecordServiceImpl implements OilProQualityGoldReviewRecordService {
	@Autowired
	private OilProQualityGoldReviewRecordDao oilProQualityGoldReviewRecordDao;
	
	@Override
	public OilProQualityGoldReviewRecordDO get(Integer id){
		return oilProQualityGoldReviewRecordDao.get(id);
	}
	
	@Override
	public List<OilProQualityGoldReviewRecordDO> list(Map<String, Object> map){
		return oilProQualityGoldReviewRecordDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return oilProQualityGoldReviewRecordDao.count(map);
	}
	
	@Override
	public int save(OilProQualityGoldReviewRecordDO oilProQualityGoldReviewRecord){
		return oilProQualityGoldReviewRecordDao.save(oilProQualityGoldReviewRecord);
	}
	
	@Override
	public int update(OilProQualityGoldReviewRecordDO oilProQualityGoldReviewRecord){
		return oilProQualityGoldReviewRecordDao.update(oilProQualityGoldReviewRecord);
	}
	
	@Override
	public int remove(Integer id){
		return oilProQualityGoldReviewRecordDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return oilProQualityGoldReviewRecordDao.batchRemove(ids);
	}

}
