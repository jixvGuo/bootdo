package com.bootdo.system.service;

import com.bootdo.system.domain.AwardStyleValidateTeamDO;

import java.util.List;
import java.util.Map;

/**
 * 形式审查_团队
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-10-01 00:13:43
 */
public interface AwardStyleValidateTeamService {
	
	AwardStyleValidateTeamDO get(Integer id);
	
	List<AwardStyleValidateTeamDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(AwardStyleValidateTeamDO awardStyleValidateTeam);
	
	int update(AwardStyleValidateTeamDO awardStyleValidateTeam);

	/**
	 * 清空最后一次校验标记
	 * @param proId
	 * @return
	 */
	int cleanLastValidate(int proId,int teamId);

	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
