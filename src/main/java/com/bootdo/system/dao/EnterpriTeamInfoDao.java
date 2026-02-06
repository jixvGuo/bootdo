package com.bootdo.system.dao;

import com.bootdo.system.domain.EnterpriTeamInfoDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 先进团队评审网页
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-06-30 04:48:14
 */
@Mapper
public interface EnterpriTeamInfoDao {

	EnterpriTeamInfoDO get(Integer id);
	
	List<EnterpriTeamInfoDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(EnterpriTeamInfoDO enterpriTeamInfo);
	
	int update(EnterpriTeamInfoDO enterpriTeamInfo);

	int updateMajor(EnterpriTeamInfoDO enterpriTeamInfo);

	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
