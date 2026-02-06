package com.bootdo.cpe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.cpe.dao.AwardEnterpriseProjectCommonDao;
import com.bootdo.cpe.domain.AwardEnterpriseProjectDO;
import com.bootdo.cpe.service.AwardEnterpriseProjectCommonService;



@Service
public class AwardEnterpriseProjectCommonServiceImpl implements AwardEnterpriseProjectCommonService {
	@Autowired
	private AwardEnterpriseProjectCommonDao awardEnterpriseProjectCommonDao;

	@Override
	public AwardEnterpriseProjectDO get(Integer id){
		return awardEnterpriseProjectCommonDao.get(id);
	}

	@Override
	public List<AwardEnterpriseProjectDO> list(Map<String, Object> map){
		return awardEnterpriseProjectCommonDao.list(map);
	}

	@Override
	public int count(Map<String, Object> map){
		return awardEnterpriseProjectCommonDao.count(map);
	}

	@Override
	public int save(AwardEnterpriseProjectDO awardEnterpriseProject){
		return awardEnterpriseProjectCommonDao.save(awardEnterpriseProject);
	}

	@Override
	public int update(AwardEnterpriseProjectDO awardEnterpriseProject){
		return awardEnterpriseProjectCommonDao.update(awardEnterpriseProject);
	}

	@Override
	public int remove(Integer id){
		return awardEnterpriseProjectCommonDao.remove(id);
	}

	@Override
	public int batchRemove(Integer[] ids){
		return awardEnterpriseProjectCommonDao.batchRemove(ids);
	}

}
