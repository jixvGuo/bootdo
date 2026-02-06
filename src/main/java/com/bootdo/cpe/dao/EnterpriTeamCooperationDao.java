package com.bootdo.cpe.dao;

import com.bootdo.cpe.domain.EnterpriTeamCooperationDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 团队合作情况汇总表
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-09-19 20:08:27
 */
@Mapper
public interface EnterpriTeamCooperationDao {

    EnterpriTeamCooperationDO get(Integer id);

    List<EnterpriTeamCooperationDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(EnterpriTeamCooperationDO enterpriTeamCooperation);

    int update(EnterpriTeamCooperationDO enterpriTeamCooperation);

    int remove(Integer id);

    int batchRemove(Integer[] ids);
}
