package com.bootdo.cpe.service;

import com.bootdo.cpe.domain.QcReviewResultRecordDO;

import java.util.List;
import java.util.Map;

/**
 * QC形式审查模板
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-02-20 21:57:29
 */
public interface QcReviewResultRecordService {
	
	QcReviewResultRecordDO get(Integer id);
	
	List<QcReviewResultRecordDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(QcReviewResultRecordDO qcReviewResultRecord);
	
	int update(QcReviewResultRecordDO qcReviewResultRecord);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
