package com.bootdo.cpe.petroleum_engineering_award.dao;

import com.bootdo.cpe.petroleum_engineering_award.domain.OilAwardApplyInfoDO;

import java.util.List;
import java.util.Map;

import com.bootdo.cpe.petroleum_engineering_award.domain.OilQualityProBaseInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2021-02-20 22:55:26
 */
@Mapper
public interface OilAwardApplyInfoDao {

	OilAwardApplyInfoDO get(Integer id);
	
	List<OilAwardApplyInfoDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(OilAwardApplyInfoDO oilAwardApplyInfo);
	
	int update(OilAwardApplyInfoDO oilAwardApplyInfo);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	List<OilQualityProBaseInfo> getQualityProBaseInfo(int proId);

}
