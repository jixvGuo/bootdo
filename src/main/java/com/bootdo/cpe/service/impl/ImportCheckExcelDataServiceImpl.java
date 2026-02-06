package com.bootdo.cpe.service.impl;

import com.bootdo.cpe.dao.ImportCheckExcelDataDao;
import com.bootdo.cpe.domain.ImportCheckExcelDataDO;
import com.bootdo.cpe.service.ImportCheckExcelDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;




@Service
public class ImportCheckExcelDataServiceImpl implements ImportCheckExcelDataService {
	@Autowired
	private ImportCheckExcelDataDao importCheckExcelDataDao;
	
	@Override
	public ImportCheckExcelDataDO get(Integer id){
		return importCheckExcelDataDao.get(id);
	}
	
	@Override
	public List<ImportCheckExcelDataDO> list(Map<String, Object> map){
		return importCheckExcelDataDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return importCheckExcelDataDao.count(map);
	}
	
	@Override
	public int save(ImportCheckExcelDataDO importCheckExcelData){
		return importCheckExcelDataDao.save(importCheckExcelData);
	}

	@Override
	public int saveBatch(List<ImportCheckExcelDataDO> list) {
		return importCheckExcelDataDao.saveBatch(list);
	}

	@Override
	public int update(ImportCheckExcelDataDO importCheckExcelData){
		return importCheckExcelDataDao.update(importCheckExcelData);
	}
	
	@Override
	public int remove(Integer id){
		return importCheckExcelDataDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return importCheckExcelDataDao.batchRemove(ids);
	}
	
}
