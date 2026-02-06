package com.bootdo.cpe.service;


import com.bootdo.cpe.domain.ImportCheckExcelDataDO;

import java.util.List;
import java.util.Map;

/**
 * 导入的excel的
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-09-04 07:21:32
 */
public interface ImportCheckExcelDataService {
	
	ImportCheckExcelDataDO get(Integer id);
	
	List<ImportCheckExcelDataDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ImportCheckExcelDataDO importCheckExcelData);
	int saveBatch(List<ImportCheckExcelDataDO> list);

	int update(ImportCheckExcelDataDO importCheckExcelData);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
