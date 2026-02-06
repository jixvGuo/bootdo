package com.bootdo.cpe.service;

import com.bootdo.cpe.domain.SurverReviewConsultResultDO;

import java.util.List;
import java.util.Map;

/**
 * 咨询类形式审查模板
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-04-13 07:07:29
 */
public interface SurverReviewConsultResultService {
	
	SurverReviewConsultResultDO get(Integer id);
	
	List<SurverReviewConsultResultDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SurverReviewConsultResultDO surverReviewConsultResult);
	
	int update(SurverReviewConsultResultDO surverReviewConsultResult);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
