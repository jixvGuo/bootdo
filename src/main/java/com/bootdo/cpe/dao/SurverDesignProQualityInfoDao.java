package com.bootdo.cpe.dao;

import com.bootdo.cpe.domain.SurverDesignProQualityInfoDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 产品质量指标对比
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-03-27 10:11:10
 */
@Mapper
public interface SurverDesignProQualityInfoDao {

	SurverDesignProQualityInfoDO get(Integer id);
	
	List<SurverDesignProQualityInfoDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(SurverDesignProQualityInfoDO surverDesignProQualityInfo);
	
	int update(SurverDesignProQualityInfoDO surverDesignProQualityInfo);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
