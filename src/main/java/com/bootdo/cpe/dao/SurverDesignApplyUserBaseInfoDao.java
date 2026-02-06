package com.bootdo.cpe.dao;

import com.bootdo.cpe.domain.SurverDesignApplyUserBaseInfoDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 申报人基本信息
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-03-27 10:11:10
 */
@Mapper
public interface SurverDesignApplyUserBaseInfoDao {

	SurverDesignApplyUserBaseInfoDO get(Integer id);
	
	List<SurverDesignApplyUserBaseInfoDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(SurverDesignApplyUserBaseInfoDO surverDesignApplyUserBaseInfo);
	
	int update(SurverDesignApplyUserBaseInfoDO surverDesignApplyUserBaseInfo);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
