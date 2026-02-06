package com.bootdo.oa.service;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.cpe.domain.AssDeptNotifyRecord;
import com.bootdo.cpe.domain.AssNotifyProReviewRecord;
import com.bootdo.oa.domain.NotifyDO;

import java.util.List;
import java.util.Map;

/**
 * 通知通告
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-05 17:11:16
 */
public interface NotifyService {

	NotifyDO get(Long id);

	List<NotifyDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(NotifyDO notify);

	/**
	 * 记录注册企业发送的消息
	 * @param assDeptNotifyRecord
	 * @return
	 */
	int saveDeptNotify(AssDeptNotifyRecord assDeptNotifyRecord);

	/**
	 * 记录项目形式审查记录
	 * @param assNotifyProReviewRecord
	 * @return
	 */
	int saveProReviewNotify(AssNotifyProReviewRecord assNotifyProReviewRecord);
	int saveProReviewNotifyShip(long notifyId,int proId,int reviewId, String proType);

	int update(NotifyDO notify);

	int remove(Long id);

	int batchRemove(Long[] ids);

//	Map<String, Object> message(Long userId);

	PageUtils selfList(Map<String, Object> map);
}
