package com.bootdo.cpe.dao;


import java.util.List;
import java.util.Map;

import com.bootdo.cpe.domain.ImportCheckExcelDataDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 导入的excel的
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-09-04 07:21:32
 */
@Mapper
public interface ImportCheckExcelDataDao {

	ImportCheckExcelDataDO get(Integer id);
	
	List<ImportCheckExcelDataDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ImportCheckExcelDataDO importCheckExcelData);
	int saveBatch(List<ImportCheckExcelDataDO> list);

	int update(ImportCheckExcelDataDO importCheckExcelData);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
