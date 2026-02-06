package com.bootdo.cpe.service;

import com.bootdo.cpe.domain.GfMainCompleteInfoDO;

import java.util.List;
import java.util.Map;

/**
 * 公奖法主要完成人
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-07-27 08:03:07
 */
public interface GfMainCompleteInfoService {
	
	GfMainCompleteInfoDO get(Integer id);
	
	List<GfMainCompleteInfoDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(GfMainCompleteInfoDO gfMainCompleteInfo);
	
	int update(GfMainCompleteInfoDO gfMainCompleteInfo);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
