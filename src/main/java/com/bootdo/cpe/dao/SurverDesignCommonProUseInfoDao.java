package com.bootdo.cpe.dao;

import com.bootdo.cpe.domain.SurverDesignCommonProUseInfoDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 主要公用工程消耗定额对比
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-03-27 10:11:10
 */
@Mapper
public interface SurverDesignCommonProUseInfoDao {

	SurverDesignCommonProUseInfoDO get(Integer id);
	
	List<SurverDesignCommonProUseInfoDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(SurverDesignCommonProUseInfoDO surverDesignCommonProUseInfo);
	
	int update(SurverDesignCommonProUseInfoDO surverDesignCommonProUseInfo);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
