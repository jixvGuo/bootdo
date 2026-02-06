package com.bootdo.cpe.dao;

import com.bootdo.cpe.domain.EnterpriTeamEvaluateOtherDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 团队第三方评价
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-09-19 16:46:09
 */
@Mapper
public interface EnterpriTeamEvaluateOtherDao {

    EnterpriTeamEvaluateOtherDO get(Integer id);

    List<EnterpriTeamEvaluateOtherDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(EnterpriTeamEvaluateOtherDO enterpriTeamEvaluateOther);

    int update(EnterpriTeamEvaluateOtherDO enterpriTeamEvaluateOther);

    int remove(Integer id);

    int batchRemove(Integer[] ids);
}
