package com.bootdo.cpe.dao;

import com.bootdo.cpe.domain.AwardAssignProjectDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 分派的项目信息
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-03-28 23:13:51
 */
@Mapper
public interface AwardAssignProjectDao {

	AwardAssignProjectDO get(Long id);
	
	List<AwardAssignProjectDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(AwardAssignProjectDO awardAssignProject);
	
	int update(AwardAssignProjectDO awardAssignProject);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
