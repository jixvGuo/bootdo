package com.bootdo.system.service.impl;

import com.bootdo.cpe.dao.ScienceProcessDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.bootdo.system.dao.EnterpriTeamInfoDao;
import com.bootdo.system.domain.EnterpriTeamInfoDO;
import com.bootdo.system.service.EnterpriTeamInfoService;



@Service
public class EnterpriTeamInfoServiceImpl implements EnterpriTeamInfoService {
	@Autowired
	private EnterpriTeamInfoDao enterpriTeamInfoDao;
	@Autowired
	private ScienceProcessDao scienceProcessDao;
	
	@Override
	public EnterpriTeamInfoDO get(Integer id){
		return enterpriTeamInfoDao.get(id);
	}
	
	@Override
	public List<EnterpriTeamInfoDO> list(Map<String, Object> map){
		List<EnterpriTeamInfoDO> list = enterpriTeamInfoDao.list(map);
		Object offsetObj = map.get("offset");
		int startNum = offsetObj == null ? 1 : Integer.parseInt(offsetObj.toString()) + 1;
		AtomicInteger atomicInteger = new AtomicInteger(startNum);
		list.stream().forEach(p->{
			p.initApplyStat();
			if(p.getShowNum() == 0) {
				p.setShowNum(atomicInteger.getAndIncrement());
			}
		});
		return list;
	}
	
	@Override
	public int count(Map<String, Object> map){
		return enterpriTeamInfoDao.count(map);
	}
	
	@Override
	public int save(EnterpriTeamInfoDO enterpriTeamInfo){

		Map<String,Object> params = new HashMap<>();
		params.put("proType", "science_team");
		params.put("taskId", enterpriTeamInfo.getTaskId());
		Integer proMaxCode = scienceProcessDao.getMaxProCode(params);
		int proCode = proMaxCode == null ? 1 : (proMaxCode + 1);
		params.put("proId", enterpriTeamInfo.getProId());
		params.put("proCode", proCode);
		scienceProcessDao.updatePorCode(params);

		return enterpriTeamInfoDao.save(enterpriTeamInfo);
	}
	
	@Override
	public int update(EnterpriTeamInfoDO enterpriTeamInfo){
		return enterpriTeamInfoDao.update(enterpriTeamInfo);
	}

	@Override
	public int updateMajor(EnterpriTeamInfoDO enterpriTeamInfo) {
		return enterpriTeamInfoDao.updateMajor(enterpriTeamInfo);
	}

	@Override
	public int remove(Integer id){
		return enterpriTeamInfoDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return enterpriTeamInfoDao.batchRemove(ids);
	}
	
}
