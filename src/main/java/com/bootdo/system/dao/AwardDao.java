package com.bootdo.system.dao;

import com.bootdo.system.domain.AwardDo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 角色
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-02 20:24:47
 */
@Mapper
public interface AwardDao {

	AwardDo get(Long awardId);

	List<AwardDo> list(Map<String, Object> map);

	int count(Map<String, Object> map);
	
	int save(AwardDo award);
	
	int update(AwardDo award);
	
	int remove(Long awardId);

	int batchRemove(Long[] awardIds);
}
