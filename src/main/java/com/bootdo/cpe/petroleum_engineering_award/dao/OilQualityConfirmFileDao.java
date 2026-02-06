package com.bootdo.cpe.petroleum_engineering_award.dao;

import com.bootdo.cpe.petroleum_engineering_award.domain.OilQualityConfirmFileDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 优质工程证实性文件
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2021-03-14 22:54:14
 */
@Mapper
public interface OilQualityConfirmFileDao {

	OilQualityConfirmFileDO get(Integer id);
	
	List<OilQualityConfirmFileDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(OilQualityConfirmFileDO oilQualityConfirmFile);
	
	int update(OilQualityConfirmFileDO oilQualityConfirmFile);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
