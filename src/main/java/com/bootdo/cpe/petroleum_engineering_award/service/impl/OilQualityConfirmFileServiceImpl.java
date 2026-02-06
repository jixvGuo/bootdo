package com.bootdo.cpe.petroleum_engineering_award.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.cpe.petroleum_engineering_award.dao.OilQualityConfirmFileDao;
import com.bootdo.cpe.petroleum_engineering_award.domain.OilQualityConfirmFileDO;
import com.bootdo.cpe.petroleum_engineering_award.service.OilQualityConfirmFileService;



@Service
public class OilQualityConfirmFileServiceImpl implements OilQualityConfirmFileService {
	@Autowired
	private OilQualityConfirmFileDao oilQualityConfirmFileDao;
	
	@Override
	public OilQualityConfirmFileDO get(Integer id){
		return oilQualityConfirmFileDao.get(id);
	}
	
	@Override
	public List<OilQualityConfirmFileDO> list(Map<String, Object> map){
		return oilQualityConfirmFileDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return oilQualityConfirmFileDao.count(map);
	}
	
	@Override
	public int save(OilQualityConfirmFileDO oilQualityConfirmFile){
		return oilQualityConfirmFileDao.save(oilQualityConfirmFile);
	}
	
	@Override
	public int update(OilQualityConfirmFileDO oilQualityConfirmFile){
		return oilQualityConfirmFileDao.update(oilQualityConfirmFile);
	}
	
	@Override
	public int remove(Integer id){
		return oilQualityConfirmFileDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return oilQualityConfirmFileDao.batchRemove(ids);
	}
	
}
