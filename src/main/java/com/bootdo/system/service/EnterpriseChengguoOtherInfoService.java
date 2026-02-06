package com.bootdo.system.service;

import com.bootdo.system.domain.EnterpriseChengguoOtherInfoDO;

import java.util.List;
import java.util.Map;

/**
 * 企业成果申报其他资料信息
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-06-27 13:37:22
 */
public interface EnterpriseChengguoOtherInfoService {
	
	EnterpriseChengguoOtherInfoDO get(Integer id);

	List<EnterpriseChengguoOtherInfoDO> getByProId(Integer id);



	List<EnterpriseChengguoOtherInfoDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(EnterpriseChengguoOtherInfoDO enterpriseChengguoOtherInfo);
	
	int update(EnterpriseChengguoOtherInfoDO enterpriseChengguoOtherInfo);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
