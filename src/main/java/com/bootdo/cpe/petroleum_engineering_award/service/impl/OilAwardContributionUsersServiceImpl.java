package com.bootdo.cpe.petroleum_engineering_award.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.cpe.petroleum_engineering_award.dao.OilAwardContributionUsersDao;
import com.bootdo.cpe.petroleum_engineering_award.domain.OilAwardContributionUsersDO;
import com.bootdo.cpe.petroleum_engineering_award.service.OilAwardContributionUsersService;



@Service
public class OilAwardContributionUsersServiceImpl implements OilAwardContributionUsersService {
	@Autowired
	private OilAwardContributionUsersDao oilAwardContributionUsersDao;
	
	@Override
	public OilAwardContributionUsersDO get(Integer id){
		return oilAwardContributionUsersDao.get(id);
	}
	
	@Override
	public List<OilAwardContributionUsersDO> list(Map<String, Object> map){
		return oilAwardContributionUsersDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return oilAwardContributionUsersDao.count(map);
	}
	
	@Override
	public int save(OilAwardContributionUsersDO oilAwardContributionUsers){
		return oilAwardContributionUsersDao.save(oilAwardContributionUsers);
	}
	
	@Override
	public int update(OilAwardContributionUsersDO oilAwardContributionUsers){
		return oilAwardContributionUsersDao.update(oilAwardContributionUsers);
	}
	
	@Override
	public int remove(Integer id){
		return oilAwardContributionUsersDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return oilAwardContributionUsersDao.batchRemove(ids);
	}
	
}
