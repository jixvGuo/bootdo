package com.bootdo.cpe.petroleum_engineering_award.service;

import com.bootdo.cpe.petroleum_engineering_award.domain.OilAwardContributionUsersDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2021-02-20 22:55:26
 */
public interface OilAwardContributionUsersService {
	
	OilAwardContributionUsersDO get(Integer id);
	
	List<OilAwardContributionUsersDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(OilAwardContributionUsersDO oilAwardContributionUsers);
	
	int update(OilAwardContributionUsersDO oilAwardContributionUsers);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
