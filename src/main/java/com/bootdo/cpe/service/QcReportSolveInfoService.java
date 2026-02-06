package com.bootdo.cpe.service;

import com.bootdo.cpe.domain.QcReportSolveInfoDO;

import java.util.List;
import java.util.Map;

/**
 * 石油工程建设优秀质量管理小组报告单（问题解决型课题）
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-02-06 21:58:24
 */
public interface QcReportSolveInfoService {
	
	QcReportSolveInfoDO get(Integer id);
	
	List<QcReportSolveInfoDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(QcReportSolveInfoDO qcReportSolveInfo);
	
	int update(QcReportSolveInfoDO qcReportSolveInfo);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
