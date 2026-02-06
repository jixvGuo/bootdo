package com.bootdo.cpe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.cpe.dao.SurverDesignCommonProUseInfoDao;
import com.bootdo.cpe.domain.SurverDesignCommonProUseInfoDO;
import com.bootdo.cpe.service.SurverDesignCommonProUseInfoService;



@Service
public class SurverDesignCommonProUseInfoServiceImpl implements SurverDesignCommonProUseInfoService {
	@Autowired
	private SurverDesignCommonProUseInfoDao surverDesignCommonProUseInfoDao;
	
	@Override
	public SurverDesignCommonProUseInfoDO get(Integer id){
		return surverDesignCommonProUseInfoDao.get(id);
	}
	
	@Override
	public List<SurverDesignCommonProUseInfoDO> list(Map<String, Object> map){
		return surverDesignCommonProUseInfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return surverDesignCommonProUseInfoDao.count(map);
	}
	
	@Override
	public int save(SurverDesignCommonProUseInfoDO surverDesignCommonProUseInfo){
		return surverDesignCommonProUseInfoDao.save(surverDesignCommonProUseInfo);
	}
	
	@Override
	public int update(SurverDesignCommonProUseInfoDO surverDesignCommonProUseInfo){
		return surverDesignCommonProUseInfoDao.update(surverDesignCommonProUseInfo);
	}
	
	@Override
	public int remove(Integer id){
		return surverDesignCommonProUseInfoDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return surverDesignCommonProUseInfoDao.batchRemove(ids);
	}
	
}
