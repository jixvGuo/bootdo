package com.bootdo.cpe.petroleum_engineering_award.service;

import com.bootdo.cpe.petroleum_engineering_award.domain.OilProApplyInfoDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2021-02-20 22:55:27
 */
public interface OilProApplyInfoService {
	
	OilProApplyInfoDO get(Integer id);

	/**
	 * 根据项目id获取最后录入的记录
	 * @param proId
	 * @return
	 */
	List<OilProApplyInfoDO> getByProId(int proId);

	List<OilProApplyInfoDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(OilProApplyInfoDO oilProApplyInfo);
	
	int update(OilProApplyInfoDO oilProApplyInfo);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
