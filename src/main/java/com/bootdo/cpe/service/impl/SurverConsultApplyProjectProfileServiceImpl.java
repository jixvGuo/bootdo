package com.bootdo.cpe.service.impl;

import com.bootdo.cpe.dao.SurverConsultApplyProjectProfileDao;
import com.bootdo.cpe.domain.SurverConsultApplyProjectProfileDO;
import com.bootdo.cpe.service.SurverConsultApplyProjectProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



@Service
public class SurverConsultApplyProjectProfileServiceImpl implements SurverConsultApplyProjectProfileService {
	@Autowired
	private SurverConsultApplyProjectProfileDao surverConsultApplyProjectProfileDao;

	@Override
	public SurverConsultApplyProjectProfileDO get(Integer id){
		return surverConsultApplyProjectProfileDao.get(id);
	}

	@Override
	public List<SurverConsultApplyProjectProfileDO> list(Map<String, Object> map){
		return surverConsultApplyProjectProfileDao.list(map);
	}

	@Override
	public int count(Map<String, Object> map){
		return surverConsultApplyProjectProfileDao.count(map);
	}

	@Override
	public int save(SurverConsultApplyProjectProfileDO surverConsultApplyProjectProfile){
		return surverConsultApplyProjectProfileDao.save(surverConsultApplyProjectProfile);
	}

	@Override
	public int update(SurverConsultApplyProjectProfileDO surverConsultApplyProjectProfile){
		return surverConsultApplyProjectProfileDao.update(surverConsultApplyProjectProfile);
	}

	@Override
	public int remove(Integer id){
		return surverConsultApplyProjectProfileDao.remove(id);
	}

	@Override
	public int batchRemove(Integer[] ids){
		return surverConsultApplyProjectProfileDao.batchRemove(ids);
	}

}
