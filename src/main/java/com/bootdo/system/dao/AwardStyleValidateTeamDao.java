package com.bootdo.system.dao;

import com.bootdo.system.domain.AwardStyleValidateTeamDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 形式审查_团队
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-10-01 00:13:43
 */
@Mapper
public interface AwardStyleValidateTeamDao {

	AwardStyleValidateTeamDO get(Integer id);
	
	List<AwardStyleValidateTeamDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(AwardStyleValidateTeamDO awardStyleValidateTeam);
	
	int update(AwardStyleValidateTeamDO awardStyleValidateTeam);

	public int cleanLastValidate(@Param("proId") int proId, @Param("teamId") int teamId);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
