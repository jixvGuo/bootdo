package com.bootdo.cpe.dao;

import com.bootdo.cpe.domain.EnterpriTeamIntellectualPropertyDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 所获知识产权和技术标准情况
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-09-19 09:29:22
 */
@Mapper
public interface EnterpriTeamIntellectualPropertyDao {

    EnterpriTeamIntellectualPropertyDO get(Integer id);

    List<EnterpriTeamIntellectualPropertyDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(EnterpriTeamIntellectualPropertyDO enterpriTeamIntellectualProperty);

    int update(EnterpriTeamIntellectualPropertyDO enterpriTeamIntellectualProperty);

    int remove(Integer id);

    int batchRemove(Integer[] ids);
}
