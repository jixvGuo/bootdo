package com.bootdo.system.dao;

import com.bootdo.system.domain.EnterpriTeamEnclosureListDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 附件列表
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-07-05 08:21:09
 */
@Mapper
public interface EnterpriTeamEnclosureListDao {

	EnterpriTeamEnclosureListDO get(Integer id);
	
	List<EnterpriTeamEnclosureListDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(EnterpriTeamEnclosureListDO enterpriTeamEnclosureList);
	
	int update(EnterpriTeamEnclosureListDO enterpriTeamEnclosureList);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
