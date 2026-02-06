package com.bootdo.cpe.dao;

import com.bootdo.cpe.domain.SurverDesignApplyTableInfoDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 *  石油工程建设优秀设计奖项目申报表表格
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-03-27 10:11:09
 */
@Mapper
public interface SurverDesignApplyTableInfoDao {

	SurverDesignApplyTableInfoDO get(Integer id);
	
	List<SurverDesignApplyTableInfoDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(SurverDesignApplyTableInfoDO surverDesignApplyTableInfo);
	
	int update(SurverDesignApplyTableInfoDO surverDesignApplyTableInfo);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
