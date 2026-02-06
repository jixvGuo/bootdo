package com.bootdo.system.service;

import com.bootdo.system.domain.EnterpriPersonalInfoDO;

import java.util.List;
import java.util.Map;

/**
 * 企业报奖个人信息
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-06-30 04:48:14
 */
public interface EnterpriPersonalInfoService {
	
	EnterpriPersonalInfoDO get(Integer id);
	
	List<EnterpriPersonalInfoDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(EnterpriPersonalInfoDO enterpriPersonalInfo);
	
	int update(EnterpriPersonalInfoDO enterpriPersonalInfo);

	int updateMajor(EnterpriPersonalInfoDO enterpriPersonalInfo);

	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
