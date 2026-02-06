package com.bootdo.cpe.service.impl;

import com.bootdo.cpe.dto.GfProDataDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.cpe.dao.GfGroupApplyInfoDao;
import com.bootdo.cpe.domain.GfGroupApplyInfoDO;
import com.bootdo.cpe.service.GfGroupApplyInfoService;



@Service
public class GfGroupApplyInfoServiceImpl implements GfGroupApplyInfoService {
	@Autowired
	private GfGroupApplyInfoDao gfGroupApplyInfoDao;
	
	@Override
	public GfGroupApplyInfoDO get(Integer id){
		return gfGroupApplyInfoDao.get(id);
	}
	
	@Override
	public List<GfProDataDto> list(Map<String, Object> map){
		List<GfProDataDto> list = gfGroupApplyInfoDao.list(map);
		list.stream().forEach(gf->{
			gf.initApplyStat();
		});
		return list;
	}
	
	@Override
	public int count(Map<String, Object> map){
		return gfGroupApplyInfoDao.count(map);
	}
	
	@Override
	public int save(GfGroupApplyInfoDO gfGroupApplyInfo){
		return gfGroupApplyInfoDao.save(gfGroupApplyInfo);
	}
	
	@Override
	public int update(GfGroupApplyInfoDO gfGroupApplyInfo){
		return gfGroupApplyInfoDao.update(gfGroupApplyInfo);
	}
	
	@Override
	public int remove(Integer id){
		return gfGroupApplyInfoDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return gfGroupApplyInfoDao.batchRemove(ids);
	}
	
}
