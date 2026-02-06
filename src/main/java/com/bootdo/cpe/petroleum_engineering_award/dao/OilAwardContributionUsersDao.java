package com.bootdo.cpe.petroleum_engineering_award.dao;

import com.bootdo.cpe.petroleum_engineering_award.domain.OilAwardContributionUsersDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2021-02-20 22:55:26
 */
@Mapper
public interface OilAwardContributionUsersDao {

	OilAwardContributionUsersDO get(Integer id);
	
	List<OilAwardContributionUsersDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(OilAwardContributionUsersDO oilAwardContributionUsers);
	
	int update(OilAwardContributionUsersDO oilAwardContributionUsers);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
