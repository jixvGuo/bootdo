package com.bootdo.cpe.service;

import com.bootdo.cpe.domain.QcResultSolveScoreDO;

import java.util.List;
import java.util.Map;

/**
 * 问题解决型课题成果评价表
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-01-31 16:01:32
 */
public interface QcResultSolveScoreService {
	
	QcResultSolveScoreDO get(Integer id);
	
	List<QcResultSolveScoreDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(QcResultSolveScoreDO qcResultSolveScore);
	
	int update(QcResultSolveScoreDO qcResultSolveScore);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
