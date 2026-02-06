package com.bootdo.system.service.impl;

import com.bootdo.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import com.bootdo.system.dao.EnterpriseChengguoOtherInfoDao;
import com.bootdo.system.domain.EnterpriseChengguoOtherInfoDO;
import com.bootdo.system.service.EnterpriseChengguoOtherInfoService;



@Service
public class EnterpriseChengguoOtherInfoServiceImpl implements EnterpriseChengguoOtherInfoService {
	@Autowired
	private EnterpriseChengguoOtherInfoDao enterpriseChengguoOtherInfoDao;
	
	@Override
	public EnterpriseChengguoOtherInfoDO get(Integer id){
		return enterpriseChengguoOtherInfoDao.get(id);
	}

	@Override
	public List<EnterpriseChengguoOtherInfoDO> getByProId(Integer id) {
		return enterpriseChengguoOtherInfoDao.getByProId(id);
	}

	@Override
	public List<EnterpriseChengguoOtherInfoDO> list(Map<String, Object> map){
		List<EnterpriseChengguoOtherInfoDO> otherInfoDOList = enterpriseChengguoOtherInfoDao.list(map);
		EnterpriseChengguoOtherInfoDO otherInfoDO = otherInfoDOList.size() > 0 ? otherInfoDOList.get(0) : null;
		if(otherInfoDO != null) {
			//由于保存记录时一个字段一条记录，将字段统一到一个对象里面
			List<EnterpriseChengguoOtherInfoDO> mainCompleteInfoList = new ArrayList<>();
			otherInfoDOList.stream().forEach(o->{
				String chengguoDesc = o.getChengguoDes();
				if(StringUtils.isNotBlank(chengguoDesc) && StringUtils.isBlank(otherInfoDO.getChengguoDes())) {
					otherInfoDO.setChengguoDes(chengguoDesc);
				}
				String mainTechnologicalInnovation = o.getMainTechnologicalInnovation();
				if(StringUtils.isNotBlank(mainTechnologicalInnovation) && StringUtils.isBlank(otherInfoDO.getMainTechnologicalInnovation())) {
					otherInfoDO.setMainTechnologicalInnovation(mainTechnologicalInnovation);
				}
				String chengguoStand = o.getChengguoStandard();
				if(StringUtils.isNotBlank(chengguoStand) && StringUtils.isBlank(otherInfoDO.getChengguoStandard())) {
					otherInfoDO.setChengguoStandard(chengguoStand);
				}
				String thirdOption = o.getThirdOpinion();
				if(StringUtils.isNotBlank(thirdOption) && StringUtils.isBlank(otherInfoDO.getThirdOpinion())) {
					otherInfoDO.setThirdOpinion(thirdOption);
				}

				if(StringUtils.isNotBlank(o.getMainEnterpriseCompanyName()) || StringUtils.isNotBlank(o.getMainEnterpriseCompleter())) {
					mainCompleteInfoList.add(o);
				}
			});
			List<EnterpriseChengguoOtherInfoDO> mainCompleteInfoListSorted = mainCompleteInfoList.stream().sorted(Comparator.comparing(EnterpriseChengguoOtherInfoDO::getMainEnterpriseCompleterSortInt)).collect(Collectors.toList());
			otherInfoDO.setMainCompleteInfoList(mainCompleteInfoListSorted);
		}
		return otherInfoDOList;
	}
	
	@Override
	public int count(Map<String, Object> map){
		return enterpriseChengguoOtherInfoDao.count(map);
	}
	
	@Override
	public int save(EnterpriseChengguoOtherInfoDO enterpriseChengguoOtherInfo){
		return enterpriseChengguoOtherInfoDao.save(enterpriseChengguoOtherInfo);
	}
	
	@Override
	public int update(EnterpriseChengguoOtherInfoDO enterpriseChengguoOtherInfo){
		return enterpriseChengguoOtherInfoDao.update(enterpriseChengguoOtherInfo);
	}
	
	@Override
	public int remove(Integer id){
		return enterpriseChengguoOtherInfoDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return enterpriseChengguoOtherInfoDao.batchRemove(ids);
	}
	
}
