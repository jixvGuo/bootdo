package com.bootdo.cpe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.cpe.dao.SurverDesignApplyProjectProfileDao;
import com.bootdo.cpe.domain.SurverDesignApplyProjectProfileDO;
import com.bootdo.cpe.service.SurverDesignApplyProjectProfileService;



@Service
public class SurverDesignApplyProjectProfileServiceImpl implements SurverDesignApplyProjectProfileService {
	@Autowired
	private SurverDesignApplyProjectProfileDao surverDesignApplyProjectProfileDao;
	
	@Override
	public SurverDesignApplyProjectProfileDO get(Integer id){
		return surverDesignApplyProjectProfileDao.get(id);
	}
	
	@Override
	public List<SurverDesignApplyProjectProfileDO> list(Map<String, Object> map){
		return surverDesignApplyProjectProfileDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return surverDesignApplyProjectProfileDao.count(map);
	}
	
	@Override
	public int save(SurverDesignApplyProjectProfileDO surverDesignApplyProjectProfile){
		return surverDesignApplyProjectProfileDao.save(surverDesignApplyProjectProfile);
	}
	
	@Override
	public int update(SurverDesignApplyProjectProfileDO surverDesignApplyProjectProfile){
		return surverDesignApplyProjectProfileDao.update(surverDesignApplyProjectProfile);
	}
	
	@Override
	public int remove(Integer id){
		return surverDesignApplyProjectProfileDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return surverDesignApplyProjectProfileDao.batchRemove(ids);
	}
	
}
