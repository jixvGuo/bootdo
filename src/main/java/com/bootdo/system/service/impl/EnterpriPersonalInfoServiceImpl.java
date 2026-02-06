package com.bootdo.system.service.impl;

import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.cpe.dao.ScienceProcessDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.bootdo.system.dao.EnterpriPersonalInfoDao;
import com.bootdo.system.domain.EnterpriPersonalInfoDO;
import com.bootdo.system.service.EnterpriPersonalInfoService;



@Service
public class EnterpriPersonalInfoServiceImpl implements EnterpriPersonalInfoService {
	@Autowired
	private EnterpriPersonalInfoDao enterpriPersonalInfoDao;
	@Autowired
	private ScienceProcessDao scienceProcessDao;
	@Autowired
	private BootdoConfig bootdoConfig;
	
	@Override
	public EnterpriPersonalInfoDO get(Integer id){
		return enterpriPersonalInfoDao.get(id);
	}
	
	@Override
	public List<EnterpriPersonalInfoDO> list(Map<String, Object> map){
		List<EnterpriPersonalInfoDO> list = enterpriPersonalInfoDao.list(map);
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
		return enterpriPersonalInfoDao.count(map);
	}
	
	@Override
	public int save(EnterpriPersonalInfoDO enterpriPersonalInfo)  {
		Map<String,Object> params = new HashMap<>();
		params.put("proType", "science_personal");
		params.put("taskId", enterpriPersonalInfo.getTaskId());
		Integer proMaxCode = scienceProcessDao.getMaxProCode(params);
		int proCode = proMaxCode == null ? 1 : (proMaxCode + 1);
		params.put("proId", enterpriPersonalInfo.getProId());
		params.put("proCode", proCode);
		scienceProcessDao.updatePorCode(params);
		String photoUrl = enterpriPersonalInfo.getPhoto();
		if(StringUtils.isBlank(photoUrl)) {
			enterpriPersonalInfo.setPhoto(null);
		}else if(!photoUrl.startsWith("http")) {
            photoUrl = bootdoConfig.getImgUrlPre() + photoUrl;
			try {
				photoUrl = URLEncoder.encode(photoUrl, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}
			enterpriPersonalInfo.setPhoto(photoUrl);
		}

		return enterpriPersonalInfoDao.save(enterpriPersonalInfo);
	}
	
	@Override
	public int update(EnterpriPersonalInfoDO enterpriPersonalInfo){
		String photoUrl = enterpriPersonalInfo.getPhoto();
		if(StringUtils.isBlank(photoUrl)) {
			enterpriPersonalInfo.setPhoto(null);
		}else if(!photoUrl.startsWith("http")) {
            photoUrl = bootdoConfig.getImgUrlPre() + photoUrl;
			try {
				photoUrl = URLEncoder.encode(photoUrl, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}
			enterpriPersonalInfo.setPhoto(photoUrl);
		}
		return enterpriPersonalInfoDao.update(enterpriPersonalInfo);
	}

	@Override
	public int updateMajor(EnterpriPersonalInfoDO enterpriPersonalInfo) {
		return enterpriPersonalInfoDao.updateMajor(enterpriPersonalInfo);
	}

	@Override
	public int remove(Integer id){
		return enterpriPersonalInfoDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return enterpriPersonalInfoDao.batchRemove(ids);
	}
	
}
