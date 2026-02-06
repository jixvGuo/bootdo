package com.bootdo.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.system.dao.AwardStyleValidatePersonDao;
import com.bootdo.system.domain.AwardStyleValidatePersonDO;
import com.bootdo.system.service.AwardStyleValidatePersonService;



@Service
public class AwardStyleValidatePersonServiceImpl implements AwardStyleValidatePersonService {
	@Autowired
	private AwardStyleValidatePersonDao awardStyleValidatePersonDao;
	
	@Override
	public AwardStyleValidatePersonDO get(Integer id){
		return awardStyleValidatePersonDao.get(id);
	}
	
	@Override
	public List<AwardStyleValidatePersonDO> list(Map<String, Object> map){
		return awardStyleValidatePersonDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return awardStyleValidatePersonDao.count(map);
	}
	
	@Override
	public int save(AwardStyleValidatePersonDO awardStyleValidatePerson){
		return awardStyleValidatePersonDao.save(awardStyleValidatePerson);
	}
	
	@Override
	public int update(AwardStyleValidatePersonDO awardStyleValidatePerson){
		return awardStyleValidatePersonDao.update(awardStyleValidatePerson);
	}

	@Override
	public int cleanLastValidate(int proId, int personId) {
		return awardStyleValidatePersonDao.cleanLastValidate(proId,personId);
	}

	@Override
	public int remove(Integer id){
		return awardStyleValidatePersonDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return awardStyleValidatePersonDao.batchRemove(ids);
	}
	
}
