package com.bootdo.cpe.service;

import com.bootdo.cpe.domain.SurverReviewDesignResultDO;

import java.util.List;
import java.util.Map;

/**
 * 设计类审查表格
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-04-13 07:07:29
 */
public interface SurverReviewDesignResultService {
	
	SurverReviewDesignResultDO get(Integer id);
	
	List<SurverReviewDesignResultDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SurverReviewDesignResultDO surverReviewDesignResult);
	
	int update(SurverReviewDesignResultDO surverReviewDesignResult);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
