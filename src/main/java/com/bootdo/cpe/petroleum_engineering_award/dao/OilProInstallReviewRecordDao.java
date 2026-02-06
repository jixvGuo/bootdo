package com.bootdo.cpe.petroleum_engineering_award.dao;

import com.bootdo.cpe.petroleum_engineering_award.domain.OilProInstallReviewRecordDO;

import java.util.List;
import java.util.Map;

import com.bootdo.cpe.petroleum_engineering_award.domain.ReviewInstallProBaseInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 石油安装工程形式审查结果记录
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2021-03-18 23:28:53
 */
@Mapper
public interface OilProInstallReviewRecordDao {

	OilProInstallReviewRecordDO get(Integer id);
	
	List<OilProInstallReviewRecordDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(OilProInstallReviewRecordDO oilProInstallReviewRecord);
	
	int update(OilProInstallReviewRecordDO oilProInstallReviewRecord);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	List<ReviewInstallProBaseInfo> getReviewProBaseInfoByProId(int proId);
}
