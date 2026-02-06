package com.bootdo.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.system.dao.AwardStyleValidateTeamDao;
import com.bootdo.system.domain.AwardStyleValidateTeamDO;
import com.bootdo.system.service.AwardStyleValidateTeamService;



@Service
public class AwardStyleValidateTeamServiceImpl implements AwardStyleValidateTeamService {
	@Autowired
	private AwardStyleValidateTeamDao awardStyleValidateTeamDao;
	
	@Override
	public AwardStyleValidateTeamDO get(Integer id){
		return awardStyleValidateTeamDao.get(id);
	}
	
	@Override
	public List<AwardStyleValidateTeamDO> list(Map<String, Object> map){
		return awardStyleValidateTeamDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return awardStyleValidateTeamDao.count(map);
	}
	
	@Override
	public int save(AwardStyleValidateTeamDO awardStyleValidateTeam){
		return awardStyleValidateTeamDao.save(awardStyleValidateTeam);
	}
	
	@Override
	public int update(AwardStyleValidateTeamDO awardStyleValidateTeam){
		return awardStyleValidateTeamDao.update(awardStyleValidateTeam);
	}

	/**
	 * 清空最后一次校验标记
	 *
	 * @param proId
	 * @return
	 */
	@Override
	public int cleanLastValidate(int proId,int teamId) {
		return awardStyleValidateTeamDao.cleanLastValidate(proId,teamId);
	}

	@Override
	public int remove(Integer id){
		return awardStyleValidateTeamDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return awardStyleValidateTeamDao.batchRemove(ids);
	}
	
}
