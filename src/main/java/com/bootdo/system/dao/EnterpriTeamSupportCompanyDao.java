package com.bootdo.system.dao;

import com.bootdo.system.domain.EnterpriTeamSupportCompanyDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 支持单位情况
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-07-05 08:21:09
 */
@Mapper
public interface EnterpriTeamSupportCompanyDao {

	EnterpriTeamSupportCompanyDO get(Integer id);
	
	List<EnterpriTeamSupportCompanyDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(EnterpriTeamSupportCompanyDO enterpriTeamSupportCompany);
	
	int update(EnterpriTeamSupportCompanyDO enterpriTeamSupportCompany);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
