package com.bootdo.cpe.service;

import com.bootdo.cpe.domain.GfGroupApplyInfoDO;
import com.bootdo.cpe.dto.GfProDataDto;

import java.util.List;
import java.util.Map;

/**
 * 公奖法申请信息
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-07-27 08:03:07
 */
public interface GfGroupApplyInfoService {
	
	GfGroupApplyInfoDO get(Integer id);
	
	List<GfProDataDto> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(GfGroupApplyInfoDO gfGroupApplyInfo);
	
	int update(GfGroupApplyInfoDO gfGroupApplyInfo);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
