package com.bootdo.cpe.service;

import com.bootdo.cpe.domain.EnterpriTeamIntellectualPropertyDO;

import java.util.List;
import java.util.Map;

/**
 * 所获知识产权和技术标准情况
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-09-19 09:29:22
 */
public interface EnterpriTeamIntellectualPropertyService {

    EnterpriTeamIntellectualPropertyDO get(Integer id);

    List<EnterpriTeamIntellectualPropertyDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(EnterpriTeamIntellectualPropertyDO enterpriTeamIntellectualProperty);

    int update(EnterpriTeamIntellectualPropertyDO enterpriTeamIntellectualProperty);

    int remove(Integer id);

    int batchRemove(Integer[] ids);
}
