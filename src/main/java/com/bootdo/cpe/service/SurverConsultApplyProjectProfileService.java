package com.bootdo.cpe.service;


import com.bootdo.cpe.domain.SurverConsultApplyProjectProfileDO;

import java.util.List;
import java.util.Map;

/**
 *
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-03-27 21:44:17
 */
public interface SurverConsultApplyProjectProfileService {

	SurverConsultApplyProjectProfileDO get(Integer id);

	List<SurverConsultApplyProjectProfileDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(SurverConsultApplyProjectProfileDO surverConsultApplyProjectProfile);

	int update(SurverConsultApplyProjectProfileDO surverConsultApplyProjectProfile);

	int remove(Integer id);

	int batchRemove(Integer[] ids);
}
