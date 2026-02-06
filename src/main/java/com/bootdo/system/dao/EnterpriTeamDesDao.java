package com.bootdo.system.dao;

import com.bootdo.system.domain.EnterpriTeamBaseInfoDO;
import com.bootdo.system.domain.EnterpriTeamInfoDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


/***
 * 团队信息 简介
 */
@Mapper
public interface EnterpriTeamDesDao {

	EnterpriTeamBaseInfoDO get(Integer id);

	List<EnterpriTeamBaseInfoDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);
	
	int save(EnterpriTeamBaseInfoDO enterpriTeamInfo);
	
	int update(EnterpriTeamBaseInfoDO enterpriTeamInfo);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
