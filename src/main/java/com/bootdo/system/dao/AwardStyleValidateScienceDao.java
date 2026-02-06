package com.bootdo.system.dao;

import com.bootdo.system.domain.AwardStyleValidateScienceDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 形式审查_团队
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-10-01 00:04:42
 */
@Mapper
public interface AwardStyleValidateScienceDao {

	AwardStyleValidateScienceDO get(Integer id);
	
	List<AwardStyleValidateScienceDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(AwardStyleValidateScienceDO awardStyleValidateScience);
	
	int update(AwardStyleValidateScienceDO awardStyleValidateScience);

	public int cleanLastValidate(int proId);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
