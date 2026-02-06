package com.bootdo.cpe.dao;

import com.bootdo.cpe.domain.AwardEnterpriseProjectDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 企业创建的项目
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2022-03-28 23:16:05
 */
@Mapper
public interface AwardEnterpriseProjectCommonDao {

	AwardEnterpriseProjectDO get(Integer id);

	List<AwardEnterpriseProjectDO> list(Map<String,Object> map);

	int count(Map<String,Object> map);

	int save(AwardEnterpriseProjectDO awardEnterpriseProject);

	int update(AwardEnterpriseProjectDO awardEnterpriseProject);

	int remove(Integer id);

	int batchRemove(Integer[] ids);
}
