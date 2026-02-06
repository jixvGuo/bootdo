package com.bootdo.system.dao;

import com.bootdo.system.domain.EnterpriseChengguoBaseInfoDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 成果基本情况
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-06-23 05:48:20
 */
@Mapper
public interface EnterpriseChengguoBaseInfoDao {

	EnterpriseChengguoBaseInfoDO get(Integer id);

	EnterpriseChengguoBaseInfoDO getByProId(Integer id);


	List<EnterpriseChengguoBaseInfoDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(EnterpriseChengguoBaseInfoDO enterpriseChengguoBaseInfo);
	
	int update(EnterpriseChengguoBaseInfoDO enterpriseChengguoBaseInfo);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

    void addBaseInfo(EnterpriseChengguoBaseInfoDO baseInfoDO);
}
