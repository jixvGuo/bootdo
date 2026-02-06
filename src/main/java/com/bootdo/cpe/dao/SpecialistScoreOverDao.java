package com.bootdo.cpe.dao;

import com.bootdo.cpe.domain.SpecialistScoreOverDO;

import java.util.List;
import java.util.Map;

import com.bootdo.cpe.domain.SpecialistScoreProOverCountInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 专家打分结束记录
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-10-10 01:08:19
 */
@Mapper
public interface SpecialistScoreOverDao {

	SpecialistScoreOverDO get(Integer id);
	
	List<SpecialistScoreOverDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SpecialistScoreOverDO specialistScoreOver);

	/**
	 * 项目打分结束
	 * @param scoreUid
	 * @param proType
	 * @return
	 */
	int scoreOverPro(@Param("scoreUid") long scoreUid, @Param("proType") String proType);

	/**
	 * 取消打分提交结果
	 * @param scoreUid
	 * @param proType
	 * @return
	 */
	int scoreCancelPro(@Param("scoreUid") long scoreUid, @Param("proType") String proType);

	/**
	 * 获取分数是否完成
	 * @param scoreUid
	 * @param proType
	 * @return
	 */
	List<Integer> getScoreIsOver(@Param("scoreUid") long scoreUid, @Param("proType") String proType);

	/**
	 * 获取已打分完结的项目列表信息
	 * @param params
	 * @return
	 */
	List<SpecialistScoreProOverCountInfo> getScoreOverSpecialistCountProList(Map<String,Object> params);
	
	int update(SpecialistScoreOverDO specialistScoreOver);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
