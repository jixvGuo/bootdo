package com.bootdo.cpe.petroleum_engineering_award.dao;

import com.bootdo.cpe.petroleum_engineering_award.domain.OilAwardGetInfoDO;

import java.util.List;
import java.util.Map;

import com.bootdo.cpe.petroleum_engineering_award.domain.OilProApplyInfoDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2021-02-20 22:55:26
 */
@Mapper
public interface OilAwardGetInfoDao {

	OilAwardGetInfoDO get(Integer id);
	
	List<OilAwardGetInfoDO> list(Map<String, Object> map);

	List<OilAwardGetInfoDO> getByProId(int proId);

	int count(Map<String, Object> map);
	
	int save(OilAwardGetInfoDO oilAwardGetInfo);
	
	int update(OilAwardGetInfoDO oilAwardGetInfo);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
