package com.bootdo.cpe.service;

import com.bootdo.cpe.domain.QcGroupApplyInfoDO;

import java.util.List;
import java.util.Map;

/**
 * 石油工程建设优秀质量管理小组申报表
 * 
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-02-16 01:08:57
 */
public interface QcGroupApplyInfoService {
	
	QcGroupApplyInfoDO get(Integer id);
	
	List<QcGroupApplyInfoDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(QcGroupApplyInfoDO qcGroupApplyInfo);
	
	int update(QcGroupApplyInfoDO qcGroupApplyInfo);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
