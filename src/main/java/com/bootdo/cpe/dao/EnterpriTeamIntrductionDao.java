package com.bootdo.cpe.dao;

import com.bootdo.cpe.domain.EnterpriTeamIntrductionDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 团队简介信息
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-10-15 23:37:19
 */
@Mapper
public interface EnterpriTeamIntrductionDao {

	EnterpriTeamIntrductionDO get(Integer id);
	
	List<EnterpriTeamIntrductionDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(EnterpriTeamIntrductionDO enterpriTeamIntrduction);
	
	int update(EnterpriTeamIntrductionDO enterpriTeamIntrduction);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
