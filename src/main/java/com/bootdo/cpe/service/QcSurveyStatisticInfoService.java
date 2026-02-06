package com.bootdo.cpe.service;

import com.bootdo.cpe.domain.QcSurveyStatisticInfoDO;

import java.util.List;
import java.util.Map;

/**
 * 质量管理小组概况统计表
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-01-31 16:01:32
 */
public interface QcSurveyStatisticInfoService {
	
	QcSurveyStatisticInfoDO get(Integer id);
	
	List<QcSurveyStatisticInfoDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(QcSurveyStatisticInfoDO qcSurveyStatisticInfo);
	
	int update(QcSurveyStatisticInfoDO qcSurveyStatisticInfo);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
