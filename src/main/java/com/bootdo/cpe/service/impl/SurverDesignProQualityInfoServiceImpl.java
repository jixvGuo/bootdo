package com.bootdo.cpe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.cpe.dao.SurverDesignProQualityInfoDao;
import com.bootdo.cpe.domain.SurverDesignProQualityInfoDO;
import com.bootdo.cpe.service.SurverDesignProQualityInfoService;



@Service
public class SurverDesignProQualityInfoServiceImpl implements SurverDesignProQualityInfoService {
	@Autowired
	private SurverDesignProQualityInfoDao surverDesignProQualityInfoDao;
	
	@Override
	public SurverDesignProQualityInfoDO get(Integer id){
		return surverDesignProQualityInfoDao.get(id);
	}
	
	@Override
	public List<SurverDesignProQualityInfoDO> list(Map<String, Object> map){
		return surverDesignProQualityInfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return surverDesignProQualityInfoDao.count(map);
	}
	
	@Override
	public int save(SurverDesignProQualityInfoDO surverDesignProQualityInfo){
		return surverDesignProQualityInfoDao.save(surverDesignProQualityInfo);
	}
	
	@Override
	public int update(SurverDesignProQualityInfoDO surverDesignProQualityInfo){
		return surverDesignProQualityInfoDao.update(surverDesignProQualityInfo);
	}
	
	@Override
	public int remove(Integer id){
		return surverDesignProQualityInfoDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return surverDesignProQualityInfoDao.batchRemove(ids);
	}
	
}
