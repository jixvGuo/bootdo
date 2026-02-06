package com.bootdo.cpe.service;

import com.bootdo.cpe.domain.SpecialistScoreOverDO;

import java.util.List;
import java.util.Map;

/**
 * 专家打分结束记录
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-10-10 01:08:19
 */
public interface SpecialistScoreOverService {
	
	SpecialistScoreOverDO get(Integer id);
	
	List<SpecialistScoreOverDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SpecialistScoreOverDO specialistScoreOver);
	
	int update(SpecialistScoreOverDO specialistScoreOver);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
