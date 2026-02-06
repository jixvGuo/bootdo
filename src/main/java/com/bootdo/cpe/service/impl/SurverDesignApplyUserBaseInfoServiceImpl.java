package com.bootdo.cpe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.cpe.dao.SurverDesignApplyUserBaseInfoDao;
import com.bootdo.cpe.domain.SurverDesignApplyUserBaseInfoDO;
import com.bootdo.cpe.service.SurverDesignApplyUserBaseInfoService;



@Service
public class SurverDesignApplyUserBaseInfoServiceImpl implements SurverDesignApplyUserBaseInfoService {
	@Autowired
	private SurverDesignApplyUserBaseInfoDao surverDesignApplyUserBaseInfoDao;
	
	@Override
	public SurverDesignApplyUserBaseInfoDO get(Integer id){
		return surverDesignApplyUserBaseInfoDao.get(id);
	}
	
	@Override
	public List<SurverDesignApplyUserBaseInfoDO> list(Map<String, Object> map){
		return surverDesignApplyUserBaseInfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return surverDesignApplyUserBaseInfoDao.count(map);
	}
	
	@Override
	public int save(SurverDesignApplyUserBaseInfoDO surverDesignApplyUserBaseInfo){
		return surverDesignApplyUserBaseInfoDao.save(surverDesignApplyUserBaseInfo);
	}
	
	@Override
	public int update(SurverDesignApplyUserBaseInfoDO surverDesignApplyUserBaseInfo){
		return surverDesignApplyUserBaseInfoDao.update(surverDesignApplyUserBaseInfo);
	}
	
	@Override
	public int remove(Integer id){
		return surverDesignApplyUserBaseInfoDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return surverDesignApplyUserBaseInfoDao.batchRemove(ids);
	}
	
}
