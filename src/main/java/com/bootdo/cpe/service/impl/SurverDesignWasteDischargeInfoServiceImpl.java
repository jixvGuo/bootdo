package com.bootdo.cpe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.cpe.dao.SurverDesignWasteDischargeInfoDao;
import com.bootdo.cpe.domain.SurverDesignWasteDischargeInfoDO;
import com.bootdo.cpe.service.SurverDesignWasteDischargeInfoService;



@Service
public class SurverDesignWasteDischargeInfoServiceImpl implements SurverDesignWasteDischargeInfoService {
	@Autowired
	private SurverDesignWasteDischargeInfoDao surverDesignWasteDischargeInfoDao;
	
	@Override
	public SurverDesignWasteDischargeInfoDO get(Integer id){
		return surverDesignWasteDischargeInfoDao.get(id);
	}
	
	@Override
	public List<SurverDesignWasteDischargeInfoDO> list(Map<String, Object> map){
		return surverDesignWasteDischargeInfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return surverDesignWasteDischargeInfoDao.count(map);
	}
	
	@Override
	public int save(SurverDesignWasteDischargeInfoDO surverDesignWasteDischargeInfo){
		return surverDesignWasteDischargeInfoDao.save(surverDesignWasteDischargeInfo);
	}
	
	@Override
	public int update(SurverDesignWasteDischargeInfoDO surverDesignWasteDischargeInfo){
		return surverDesignWasteDischargeInfoDao.update(surverDesignWasteDischargeInfo);
	}
	
	@Override
	public int remove(Integer id){
		return surverDesignWasteDischargeInfoDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return surverDesignWasteDischargeInfoDao.batchRemove(ids);
	}
	
}
