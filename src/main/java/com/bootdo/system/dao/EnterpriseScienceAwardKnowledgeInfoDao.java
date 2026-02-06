package com.bootdo.system.dao;

import com.bootdo.system.domain.EnterpriseScienceAwardKnowledgeInfoDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 企业科技进步奖知识产权信息
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-09-08 22:47:30
 */
@Mapper
public interface EnterpriseScienceAwardKnowledgeInfoDao {

	EnterpriseScienceAwardKnowledgeInfoDO get(Integer id);
	
	List<EnterpriseScienceAwardKnowledgeInfoDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(EnterpriseScienceAwardKnowledgeInfoDO enterpriseScienceAwardKnowledgeInfo);
	
	int update(EnterpriseScienceAwardKnowledgeInfoDO enterpriseScienceAwardKnowledgeInfo);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
