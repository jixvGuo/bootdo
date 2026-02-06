package com.bootdo.system.service;

import com.bootdo.system.domain.EnterpriTeamInfoDO;

import java.util.List;
import java.util.Map;

/**
 * 先进团队评审网页
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-06-30 04:48:14
 */
public interface EnterpriTeamInfoService {
	
	EnterpriTeamInfoDO get(Integer id);
	
	List<EnterpriTeamInfoDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(EnterpriTeamInfoDO enterpriTeamInfo);
	
	int update(EnterpriTeamInfoDO enterpriTeamInfo);

	int updateMajor(EnterpriTeamInfoDO enterpriTeamInfo);

	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
