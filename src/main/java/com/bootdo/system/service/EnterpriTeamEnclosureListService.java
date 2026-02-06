package com.bootdo.system.service;

import com.bootdo.system.domain.EnterpriTeamEnclosureListDO;

import java.util.List;
import java.util.Map;

/**
 * 附件列表
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-07-05 08:21:09
 */
public interface EnterpriTeamEnclosureListService {
	
	EnterpriTeamEnclosureListDO get(Integer id);
	
	List<EnterpriTeamEnclosureListDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(EnterpriTeamEnclosureListDO enterpriTeamEnclosureList);
	
	int update(EnterpriTeamEnclosureListDO enterpriTeamEnclosureList);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
