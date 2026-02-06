package com.bootdo.cpe.service;

import com.bootdo.cpe.domain.SurverDesignWasteDischargeInfoDO;

import java.util.List;
import java.util.Map;

/**
 * 废水_液__废气_废渣排放量及排放指标对比
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-03-27 10:11:10
 */
public interface SurverDesignWasteDischargeInfoService {
	
	SurverDesignWasteDischargeInfoDO get(Integer id);
	
	List<SurverDesignWasteDischargeInfoDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SurverDesignWasteDischargeInfoDO surverDesignWasteDischargeInfo);
	
	int update(SurverDesignWasteDischargeInfoDO surverDesignWasteDischargeInfo);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
