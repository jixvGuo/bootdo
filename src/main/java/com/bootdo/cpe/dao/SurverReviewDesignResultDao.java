package com.bootdo.cpe.dao;

import com.bootdo.cpe.domain.SurverReviewDesignResultDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 设计类审查表格
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-04-13 07:07:29
 */
@Mapper
public interface SurverReviewDesignResultDao {

	SurverReviewDesignResultDO get(Integer id);
	
	List<SurverReviewDesignResultDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(SurverReviewDesignResultDO surverReviewDesignResult);
	
	int update(SurverReviewDesignResultDO surverReviewDesignResult);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
