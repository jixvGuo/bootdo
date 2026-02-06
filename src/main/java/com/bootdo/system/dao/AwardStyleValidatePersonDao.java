package com.bootdo.system.dao;

import com.bootdo.system.domain.AwardStyleValidatePersonDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Property;

/**
 * 形式审查_团队
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-10-01 00:23:36
 */
@Mapper
public interface AwardStyleValidatePersonDao {

	AwardStyleValidatePersonDO get(Integer id);
	
	List<AwardStyleValidatePersonDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(AwardStyleValidatePersonDO awardStyleValidatePerson);
	
	int update(AwardStyleValidatePersonDO awardStyleValidatePerson);

	public int cleanLastValidate(@Param("proId") int proId, @Param("personId") int personId);
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
