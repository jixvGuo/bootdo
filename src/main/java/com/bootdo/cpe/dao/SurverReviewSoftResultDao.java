package com.bootdo.cpe.dao;

import com.bootdo.cpe.domain.SurverReviewSoftResultDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 软件类审查表格
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-04-13 07:07:29
 */
@Mapper
public interface SurverReviewSoftResultDao {

	SurverReviewSoftResultDO get(Integer id);
	
	List<SurverReviewSoftResultDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(SurverReviewSoftResultDO surverReviewSoftResult);
	
	int update(SurverReviewSoftResultDO surverReviewSoftResult);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
