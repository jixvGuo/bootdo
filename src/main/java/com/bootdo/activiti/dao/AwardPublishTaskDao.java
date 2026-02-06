package com.bootdo.activiti.dao;

import com.bootdo.activiti.domain.MajorInfo;
import com.bootdo.activiti.domain.PublishAwardTaskDo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 审批流程测试表
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-11-25 13:28:58
 */
@Mapper
public interface AwardPublishTaskDao {

	PublishAwardTaskDo get(String id);
	PublishAwardTaskDo getByProId(String proId);
	PublishAwardTaskDo getAwardTaskByProcInsId(String procInsId);

	List<PublishAwardTaskDo> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(PublishAwardTaskDo publishTask);

	int update(PublishAwardTaskDo publishTask);

	int remove(String id);

	int batchRemove(String[] ids);
	//根据发布奖项的任务id获取专业信息
	List<MajorInfo> getAwardTaskMajorInfosById(String publishTaskId);

	/**
	 * 根据发布的奖项任务id获取对应的工作流的实例id
	 * @param publishTaskId
	 * @return
	 */
	int getPublishTaskActProcInsId(String publishTaskId);

	/**
	 * 根据发布的任务id获取下面的项目id列表
	 * @param publishTaskId
	 * @return
	 */
	List<String> getProIdsByPublishTaskId(String publishTaskId);

    String getLastTaskId();

	PublishAwardTaskDo getLastProTaskByAwardType(String awardType);
	PublishAwardTaskDo getProTaskByTaskId(String taskId);
	String getTaskIdByProId(int proId);

	/**
	 * 是否有菜单操作的权限
	 * @param uid
	 * @param menuIds
	 * @return
	 */
	int isMenuAuth(@Param("uid") long uid, @Param("menuIds") List<Integer> menuIds);
}
