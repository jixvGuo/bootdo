package com.bootdo.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.system.dao.EnterpriseScienceAwardKnowledgeInfoDao;
import com.bootdo.system.domain.EnterpriseScienceAwardKnowledgeInfoDO;
import com.bootdo.system.service.EnterpriseScienceAwardKnowledgeInfoService;



@Service
public class EnterpriseScienceAwardKnowledgeInfoServiceImpl implements EnterpriseScienceAwardKnowledgeInfoService {
	@Autowired
	private EnterpriseScienceAwardKnowledgeInfoDao enterpriseScienceAwardKnowledgeInfoDao;
	
	@Override
	public EnterpriseScienceAwardKnowledgeInfoDO get(Integer id){
		return enterpriseScienceAwardKnowledgeInfoDao.get(id);
	}
	
	@Override
	public List<EnterpriseScienceAwardKnowledgeInfoDO> list(Map<String, Object> map){
		return enterpriseScienceAwardKnowledgeInfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return enterpriseScienceAwardKnowledgeInfoDao.count(map);
	}
	
	@Override
	public int save(EnterpriseScienceAwardKnowledgeInfoDO enterpriseScienceAwardKnowledgeInfo){
		return enterpriseScienceAwardKnowledgeInfoDao.save(enterpriseScienceAwardKnowledgeInfo);
	}
	
	@Override
	public int update(EnterpriseScienceAwardKnowledgeInfoDO enterpriseScienceAwardKnowledgeInfo){
		return enterpriseScienceAwardKnowledgeInfoDao.update(enterpriseScienceAwardKnowledgeInfo);
	}
	
	@Override
	public int remove(Integer id){
		return enterpriseScienceAwardKnowledgeInfoDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return enterpriseScienceAwardKnowledgeInfoDao.batchRemove(ids);
	}
	
}
