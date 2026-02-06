package com.bootdo.cpe.dao;

import com.bootdo.cpe.domain.GfGroupApplyInfoDO;

import java.util.List;
import java.util.Map;

import com.bootdo.cpe.dto.GfProDataDto;
import org.apache.ibatis.annotations.Mapper;

/**
 * 公奖法申请信息
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-07-27 08:03:07
 */
@Mapper
public interface GfGroupApplyInfoDao {

	GfGroupApplyInfoDO get(Integer id);
	
	List<GfProDataDto> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(GfGroupApplyInfoDO gfGroupApplyInfo);
	
	int update(GfGroupApplyInfoDO gfGroupApplyInfo);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
