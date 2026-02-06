package com.bootdo.cpe.dao;

import com.bootdo.cpe.domain.SurverReviewSurverResultDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 勘察类审查表格
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-04-13 07:07:29
 */
@Mapper
public interface SurverReviewSurverResultDao {

	SurverReviewSurverResultDO get(Integer id);
	
	List<SurverReviewSurverResultDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(SurverReviewSurverResultDO surverReviewSurverResult);
	
	int update(SurverReviewSurverResultDO surverReviewSurverResult);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
