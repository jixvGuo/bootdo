package com.bootdo.cpe.dao;

import com.bootdo.cpe.domain.EnterpriTeamAcademicExchangeDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 团队学术交流
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-09-19 01:31:08
 */
@Mapper
public interface EnterpriTeamAcademicExchangeDao {

    EnterpriTeamAcademicExchangeDO get(Integer id);

    List<EnterpriTeamAcademicExchangeDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(EnterpriTeamAcademicExchangeDO enterpriTeamAcademicExchange);

    int update(EnterpriTeamAcademicExchangeDO enterpriTeamAcademicExchange);

    int remove(Integer id);

    int batchRemove(Integer[] ids);
}
