package com.bootdo.system.service;

import com.bootdo.system.domain.AwardStyleValidatePersonDO;

import java.util.List;
import java.util.Map;

/**
 * 形式审查_团队
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-10-01 00:23:36
 */
public interface AwardStyleValidatePersonService {
	
	AwardStyleValidatePersonDO get(Integer id);
	
	List<AwardStyleValidatePersonDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(AwardStyleValidatePersonDO awardStyleValidatePerson);
	
	int update(AwardStyleValidatePersonDO awardStyleValidatePerson);
	int cleanLastValidate(int proId,int personId);
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
