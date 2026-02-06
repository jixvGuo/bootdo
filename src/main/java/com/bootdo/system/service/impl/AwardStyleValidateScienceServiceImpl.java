package com.bootdo.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.system.dao.AwardStyleValidateScienceDao;
import com.bootdo.system.domain.AwardStyleValidateScienceDO;
import com.bootdo.system.service.AwardStyleValidateScienceService;



@Service
public class AwardStyleValidateScienceServiceImpl implements AwardStyleValidateScienceService {
	@Autowired
	private AwardStyleValidateScienceDao awardStyleValidateScienceDao;
	
	@Override
	public AwardStyleValidateScienceDO get(Integer id){
		return awardStyleValidateScienceDao.get(id);
	}
	
	@Override
	public List<AwardStyleValidateScienceDO> list(Map<String, Object> map){
		return awardStyleValidateScienceDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return awardStyleValidateScienceDao.count(map);
	}
	
	@Override
	public int save(AwardStyleValidateScienceDO awardStyleValidateScience){
		return awardStyleValidateScienceDao.save(awardStyleValidateScience);
	}
	
	@Override
	public int update(AwardStyleValidateScienceDO awardStyleValidateScience){
		return awardStyleValidateScienceDao.update(awardStyleValidateScience);
	}

	/**
	 * 清空之前默认的审核标记
	 *
	 * @param proId
	 * @return
	 */
	@Override
	public int cleanLastValidate(int proId) {
		return awardStyleValidateScienceDao.cleanLastValidate(proId);
	}

	@Override
	public int remove(Integer id){
		return awardStyleValidateScienceDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return awardStyleValidateScienceDao.batchRemove(ids);
	}
	
}
