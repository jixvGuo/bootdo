package com.bootdo.system.dao;

import com.bootdo.system.domain.UserAwardDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 用户与角色对应关系
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-03 11:08:59
 */
@Mapper
public interface UserAwardDao {

	UserAwardDO get(Long id);

	List<UserAwardDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(UserAwardDO userAward);

	int update(UserAwardDO userAward);

	int remove(Long id);

	int batchRemove(Long[] ids);

	List<Long> listAwardId(Long userId);

	int removeByUserId(Long userId);

	int removeByAwardId(Long awardId);

	int batchSave(List<UserAwardDO> list);

	int batchRemoveByUserId(Long[] ids);
}
