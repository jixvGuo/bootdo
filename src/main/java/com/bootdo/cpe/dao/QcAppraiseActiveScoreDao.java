package com.bootdo.cpe.dao;

import com.bootdo.cpe.domain.QcAppraiseActiveScoreDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 质量管理小组活动现场评价表
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-02-16 01:15:51
 */
@Mapper
public interface QcAppraiseActiveScoreDao {

	QcAppraiseActiveScoreDO get(Integer id);
	
	List<QcAppraiseActiveScoreDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(QcAppraiseActiveScoreDO qcAppraiseActiveScore);
	
	int update(QcAppraiseActiveScoreDO qcAppraiseActiveScore);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
