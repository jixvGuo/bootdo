package com.bootdo.cpe.petroleum_engineering_award.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.cpe.petroleum_engineering_award.dao.OilProApplyInfoDao;
import com.bootdo.cpe.petroleum_engineering_award.domain.OilProApplyInfoDO;
import com.bootdo.cpe.petroleum_engineering_award.service.OilProApplyInfoService;



@Service
public class OilProApplyInfoServiceImpl implements OilProApplyInfoService {
	@Autowired
	private OilProApplyInfoDao oilProApplyInfoDao;
	
	@Override
	public OilProApplyInfoDO get(Integer id){
		return oilProApplyInfoDao.get(id);
	}

	/**
	 * 根据项目id获取最后录入的记录
	 *
	 * @param proId
	 * @return
	 */
	@Override
	public List<OilProApplyInfoDO> getByProId(int proId) {
		return oilProApplyInfoDao.getByProId(proId);
	}

	@Override
	public List<OilProApplyInfoDO> list(Map<String, Object> map){
		return oilProApplyInfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return oilProApplyInfoDao.count(map);
	}
	
	@Override
	public int save(OilProApplyInfoDO oilProApplyInfo){
		return oilProApplyInfoDao.save(oilProApplyInfo);
	}
	
	@Override
	public int update(OilProApplyInfoDO oilProApplyInfo){
		return oilProApplyInfoDao.update(oilProApplyInfo);
	}
	
	@Override
	public int remove(Integer id){
		return oilProApplyInfoDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return oilProApplyInfoDao.batchRemove(ids);
	}
	
}
