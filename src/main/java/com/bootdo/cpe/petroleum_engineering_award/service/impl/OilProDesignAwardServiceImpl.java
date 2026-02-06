package com.bootdo.cpe.petroleum_engineering_award.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.cpe.petroleum_engineering_award.dao.OilProDesignAwardDao;
import com.bootdo.cpe.petroleum_engineering_award.domain.OilProDesignAwardDO;
import com.bootdo.cpe.petroleum_engineering_award.service.OilProDesignAwardService;



@Service
public class OilProDesignAwardServiceImpl implements OilProDesignAwardService {
	@Autowired
	private OilProDesignAwardDao oilProDesignAwardDao;
	
	@Override
	public OilProDesignAwardDO get(Integer id){
		return oilProDesignAwardDao.get(id);
	}

	@Override
	public List<OilProDesignAwardDO> getByProId(int proId) {
		return oilProDesignAwardDao.getByProId(proId);
	}

	@Override
	public List<OilProDesignAwardDO> list(Map<String, Object> map){
		return oilProDesignAwardDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return oilProDesignAwardDao.count(map);
	}
	
	@Override
	public int save(OilProDesignAwardDO oilProDesignAward){
		return oilProDesignAwardDao.save(oilProDesignAward);
	}
	
	@Override
	public int update(OilProDesignAwardDO oilProDesignAward){
		return oilProDesignAwardDao.update(oilProDesignAward);
	}
	
	@Override
	public int remove(Integer id){
		return oilProDesignAwardDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return oilProDesignAwardDao.batchRemove(ids);
	}
	
}
