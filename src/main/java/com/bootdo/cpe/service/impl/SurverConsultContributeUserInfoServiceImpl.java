package com.bootdo.cpe.service.impl;

import com.bootdo.cpe.dao.SurverConsultContributeUserInfoDao;
import com.bootdo.system.domain.SurverConsultContributeUserInfoDO;
import com.bootdo.system.service.SurverConsultContributeUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;




@Service
public class SurverConsultContributeUserInfoServiceImpl implements SurverConsultContributeUserInfoService {
	@Autowired
	private SurverConsultContributeUserInfoDao surverConsultContributeUserInfoDao;
	
	@Override
	public SurverConsultContributeUserInfoDO get(Integer id){
		return surverConsultContributeUserInfoDao.get(id);
	}
	
	@Override
	public List<SurverConsultContributeUserInfoDO> list(Map<String, Object> map){
		return surverConsultContributeUserInfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return surverConsultContributeUserInfoDao.count(map);
	}
	
	@Override
	public int save(SurverConsultContributeUserInfoDO surverConsultContributeUserInfo){
		return surverConsultContributeUserInfoDao.save(surverConsultContributeUserInfo);
	}
	
	@Override
	public int update(SurverConsultContributeUserInfoDO surverConsultContributeUserInfo){
		return surverConsultContributeUserInfoDao.update(surverConsultContributeUserInfo);
	}
	
	@Override
	public int remove(Integer id){
		return surverConsultContributeUserInfoDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return surverConsultContributeUserInfoDao.batchRemove(ids);
	}
	
}
