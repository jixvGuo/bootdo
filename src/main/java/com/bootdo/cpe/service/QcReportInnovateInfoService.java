package com.bootdo.cpe.service;

import com.bootdo.cpe.domain.QcReportInnovateInfoDO;

import java.util.List;
import java.util.Map;

/**
 * 石油工程建设优秀质量管理小组报告单（创新型课题）
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-01-31 16:01:32
 */
public interface QcReportInnovateInfoService {
	
	QcReportInnovateInfoDO get(Integer id);
	
	List<QcReportInnovateInfoDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(QcReportInnovateInfoDO qcReportInnovateInfo);
	
	int update(QcReportInnovateInfoDO qcReportInnovateInfo);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
