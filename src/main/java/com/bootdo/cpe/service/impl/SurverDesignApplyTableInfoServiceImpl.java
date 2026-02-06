package com.bootdo.cpe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.cpe.dao.SurverDesignApplyTableInfoDao;
import com.bootdo.cpe.domain.SurverDesignApplyTableInfoDO;
import com.bootdo.cpe.service.SurverDesignApplyTableInfoService;



@Service
public class SurverDesignApplyTableInfoServiceImpl implements SurverDesignApplyTableInfoService {
	@Autowired
	private SurverDesignApplyTableInfoDao surverDesignApplyTableInfoDao;
	
	@Override
	public SurverDesignApplyTableInfoDO get(Integer id){
		return surverDesignApplyTableInfoDao.get(id);
	}
	
	@Override
	public List<SurverDesignApplyTableInfoDO> list(Map<String, Object> map){
		return surverDesignApplyTableInfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return surverDesignApplyTableInfoDao.count(map);
	}
	
	@Override
	public int save(SurverDesignApplyTableInfoDO surverDesignApplyTableInfo){
		return surverDesignApplyTableInfoDao.save(surverDesignApplyTableInfo);
	}
	
	@Override
	public int update(SurverDesignApplyTableInfoDO surverDesignApplyTableInfo){
		return surverDesignApplyTableInfoDao.update(surverDesignApplyTableInfo);
	}
	
	@Override
	public int remove(Integer id){
		return surverDesignApplyTableInfoDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return surverDesignApplyTableInfoDao.batchRemove(ids);
	}
	
}
