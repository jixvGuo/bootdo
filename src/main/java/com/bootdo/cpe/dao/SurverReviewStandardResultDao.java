package com.bootdo.cpe.dao;

import com.bootdo.cpe.domain.SurverReviewStandardResultDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 标准设计类审查表格
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-04-13 07:07:29
 */
@Mapper
public interface SurverReviewStandardResultDao {

	SurverReviewStandardResultDO get(Integer id);
	
	List<SurverReviewStandardResultDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(SurverReviewStandardResultDO surverReviewStandardResult);
	
	int update(SurverReviewStandardResultDO surverReviewStandardResult);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
