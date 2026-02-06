package com.bootdo.system.service.impl;

import com.bootdo.cpe.dao.ScienceProcessDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.system.dao.EnterpriseChengguoBaseInfoDao;
import com.bootdo.system.domain.EnterpriseChengguoBaseInfoDO;
import com.bootdo.system.service.EnterpriseChengguoBaseInfoService;



@Service
public class EnterpriseChengguoBaseInfoServiceImpl implements EnterpriseChengguoBaseInfoService {
	@Autowired
	private EnterpriseChengguoBaseInfoDao enterpriseChengguoBaseInfoDao;
	@Autowired
	private ScienceProcessDao scienceProcessDao;
	
	@Override
	public EnterpriseChengguoBaseInfoDO get(Integer id){
		return enterpriseChengguoBaseInfoDao.get(id);
	}

	@Override
	public EnterpriseChengguoBaseInfoDO getByProId(Integer id) {
		return enterpriseChengguoBaseInfoDao.getByProId(id);
	}

	@Override
	public List<EnterpriseChengguoBaseInfoDO> list(Map<String, Object> map){
		return enterpriseChengguoBaseInfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return enterpriseChengguoBaseInfoDao.count(map);
	}
	
	@Override
	public int save(EnterpriseChengguoBaseInfoDO enterpriseChengguoBaseInfo){
		Map<String,Object> params = new HashMap<>();
		params.put("proType", "science_progress");
		int proId = Integer.parseInt(enterpriseChengguoBaseInfo.getProId());
		String taskId = scienceProcessDao.getTaskIdByProId(proId);
		params.put("taskId", taskId);
		Integer proMaxCode = scienceProcessDao.getMaxProCode(params);
		int proCode = proMaxCode == null ? 1 : (proMaxCode + 1);
		params.put("proId", proId);
		params.put("proCode", proCode);
        scienceProcessDao.updatePorCode(params);

		return enterpriseChengguoBaseInfoDao.save(enterpriseChengguoBaseInfo);
	}
	
	@Override
	public int update(EnterpriseChengguoBaseInfoDO enterpriseChengguoBaseInfo){
		return enterpriseChengguoBaseInfoDao.update(enterpriseChengguoBaseInfo);
	}

	@Override
	public int remove(Integer id){
		return enterpriseChengguoBaseInfoDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return enterpriseChengguoBaseInfoDao.batchRemove(ids);
	}

	@Override
	public void addBaseInfo(EnterpriseChengguoBaseInfoDO baseInfoDO) {
		Map<String,Object> params = new HashMap<>();
		params.put("proType", "science_progress");
		int proId = Integer.parseInt(baseInfoDO.getProId());
		String taskId = scienceProcessDao.getTaskIdByProId(proId);
		params.put("taskId", taskId);
		Integer proMaxCode = scienceProcessDao.getMaxProCode(params);
		int proCode = proMaxCode == null ? 1 : (proMaxCode + 1);
		params.put("proId", proId);
		params.put("proCode", proCode);
		scienceProcessDao.updatePorCode(params);

		enterpriseChengguoBaseInfoDao.addBaseInfo(baseInfoDO);
	}
}
