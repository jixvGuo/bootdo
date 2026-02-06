package com.bootdo.cpe.petroleum_engineering_award.dao;

import com.bootdo.cpe.petroleum_engineering_award.domain.OilProQualityGoldReviewRecordDO;

import java.util.List;
import java.util.Map;

import com.bootdo.cpe.petroleum_engineering_award.domain.ReviewQualityProBaseInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 石油优质工程形式审查结果记录
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2021-03-20 09:01:55
 */
@Mapper
public interface OilProQualityGoldReviewRecordDao {

	OilProQualityGoldReviewRecordDO get(Integer id);
	
	List<OilProQualityGoldReviewRecordDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(OilProQualityGoldReviewRecordDO oilProQualityGoldReviewRecord);
	
	int update(OilProQualityGoldReviewRecordDO oilProQualityGoldReviewRecord);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

}
