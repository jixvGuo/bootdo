package com.bootdo.cpe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.cpe.dao.SpecialistScoreOverDao;
import com.bootdo.cpe.domain.SpecialistScoreOverDO;
import com.bootdo.cpe.service.SpecialistScoreOverService;



@Service
public class SpecialistScoreOverServiceImpl implements SpecialistScoreOverService {
	@Autowired
	private SpecialistScoreOverDao specialistScoreOverDao;
	
	@Override
	public SpecialistScoreOverDO get(Integer id){
		return specialistScoreOverDao.get(id);
	}
	
	@Override
	public List<SpecialistScoreOverDO> list(Map<String, Object> map){
		return specialistScoreOverDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return specialistScoreOverDao.count(map);
	}
	
	@Override
	public int save(SpecialistScoreOverDO specialistScoreOver){
		return specialistScoreOverDao.save(specialistScoreOver);
	}
	
	@Override
	public int update(SpecialistScoreOverDO specialistScoreOver){
		return specialistScoreOverDao.update(specialistScoreOver);
	}
	
	@Override
	public int remove(Integer id){
		return specialistScoreOverDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return specialistScoreOverDao.batchRemove(ids);
	}
	
}
