package com.bootdo.activiti.dao;

import com.bootdo.activiti.domain.AwardUpDocDo;
import com.bootdo.activiti.domain.PublishAwardTaskDo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 审批流程测试表
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-11-25 13:28:58
 */
@Mapper
public interface AwardFlowDao {

	AwardUpDocDo get(String id);
	AwardUpDocDo getByProId(String proId);
	/**
     * 根据工作流的实例id获取发布的奖项的id
     * @param procInsId
     * @return
     */
    String getPublishAwardIdByProcInsId(String procInsId);

	List<AwardUpDocDo> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(AwardUpDocDo salary);
	
	int update(AwardUpDocDo salary);
	
	int remove(String id);

	int batchRemove(String[] ids);

}
