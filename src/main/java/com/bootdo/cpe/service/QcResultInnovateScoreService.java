package com.bootdo.cpe.service;

import com.bootdo.cpe.domain.QcResultInnovateScoreDO;

import java.util.List;
import java.util.Map;

/**
 * 创新型课题成果评价表
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-02-06 22:48:27
 */
public interface QcResultInnovateScoreService {
	
	QcResultInnovateScoreDO get(Integer id);
	
	List<QcResultInnovateScoreDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(QcResultInnovateScoreDO qcResultInnovateScore);
	
	int update(QcResultInnovateScoreDO qcResultInnovateScore);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
