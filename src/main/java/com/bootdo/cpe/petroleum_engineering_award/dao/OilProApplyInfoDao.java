package com.bootdo.cpe.petroleum_engineering_award.dao;

import com.bootdo.cpe.petroleum_engineering_award.domain.OilProApplyInfoDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2021-02-20 22:55:27
 */
@Mapper
public interface OilProApplyInfoDao {

	OilProApplyInfoDO get(Integer id);

	List<OilProApplyInfoDO> getByProId(int proId);
	
	List<OilProApplyInfoDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(OilProApplyInfoDO oilProApplyInfo);
	
	int update(OilProApplyInfoDO oilProApplyInfo);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
