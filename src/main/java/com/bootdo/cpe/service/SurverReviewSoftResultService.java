package com.bootdo.cpe.service;

import com.bootdo.cpe.domain.SurverReviewSoftResultDO;

import java.util.List;
import java.util.Map;

/**
 * 软件类审查表格
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-04-13 07:07:29
 */
public interface SurverReviewSoftResultService {
	
	SurverReviewSoftResultDO get(Integer id);
	
	List<SurverReviewSoftResultDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SurverReviewSoftResultDO surverReviewSoftResult);
	
	int update(SurverReviewSoftResultDO surverReviewSoftResult);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
