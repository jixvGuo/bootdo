package com.bootdo.cpe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.cpe.dao.AwardAssignProjectDao;
import com.bootdo.cpe.domain.AwardAssignProjectDO;
import com.bootdo.cpe.service.AwardAssignProjectService;



@Service
public class AwardAssignProjectServiceImpl implements AwardAssignProjectService {
	@Autowired
	private AwardAssignProjectDao awardAssignProjectDao;
	
	@Override
	public AwardAssignProjectDO get(Long id){
		return awardAssignProjectDao.get(id);
	}
	
	@Override
	public List<AwardAssignProjectDO> list(Map<String, Object> map){
		return awardAssignProjectDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return awardAssignProjectDao.count(map);
	}
	
	@Override
	public int save(AwardAssignProjectDO awardAssignProject){
		return awardAssignProjectDao.save(awardAssignProject);
	}
	
	@Override
	public int update(AwardAssignProjectDO awardAssignProject){
		return awardAssignProjectDao.update(awardAssignProject);
	}
	
	@Override
	public int remove(Long id){
		return awardAssignProjectDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return awardAssignProjectDao.batchRemove(ids);
	}
	
}
