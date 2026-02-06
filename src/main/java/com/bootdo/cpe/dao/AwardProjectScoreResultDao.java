package com.bootdo.cpe.dao;

import com.bootdo.cpe.domain.AwardProjectScoreResultDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 项目的打分结果记录
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-10-10 02:22:28
 */
@Mapper
public interface AwardProjectScoreResultDao {

	AwardProjectScoreResultDO get(Integer id);
	
	List<AwardProjectScoreResultDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(AwardProjectScoreResultDO awardProjectScoreResult);
	
	int update(AwardProjectScoreResultDO awardProjectScoreResult);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	int batchAdd(List<AwardProjectScoreResultDO> list);
}
