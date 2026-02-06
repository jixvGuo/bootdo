package com.bootdo.cpe.petroleum_engineering_award.service;

import com.bootdo.cpe.petroleum_engineering_award.domain.OilProInstallReviewRecordDO;
import com.bootdo.cpe.petroleum_engineering_award.domain.ReviewInstallProBaseInfo;

import java.util.List;
import java.util.Map;

/**
 * 石油安装工程形式审查结果记录
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2021-03-18 23:28:53
 */
public interface OilProInstallReviewRecordService {
	
	OilProInstallReviewRecordDO get(Integer id);
	
	List<OilProInstallReviewRecordDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(OilProInstallReviewRecordDO oilProInstallReviewRecord);
	
	int update(OilProInstallReviewRecordDO oilProInstallReviewRecord);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	ReviewInstallProBaseInfo getReviewProBaseInfoByProId(int proId);
}
