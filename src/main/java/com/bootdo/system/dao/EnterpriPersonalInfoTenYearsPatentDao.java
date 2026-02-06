package com.bootdo.system.dao;

import com.bootdo.system.domain.EnterpriPersonalInfoTenYearsPatentDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 近十年内由申报人参与完成并取得授权的专利 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-06-30 04:48:14
 */
@Mapper
public interface EnterpriPersonalInfoTenYearsPatentDao {

	EnterpriPersonalInfoTenYearsPatentDO get(Integer id);
	
	List<EnterpriPersonalInfoTenYearsPatentDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(EnterpriPersonalInfoTenYearsPatentDO enterpriPersonalInfoTenYearsPatent);
	
	int update(EnterpriPersonalInfoTenYearsPatentDO enterpriPersonalInfoTenYearsPatent);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
