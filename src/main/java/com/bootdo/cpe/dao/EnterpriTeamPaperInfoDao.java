package com.bootdo.cpe.dao;

import com.bootdo.cpe.domain.EnterpriTeamPaperInfoDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 发表论文专著情况
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-09-18 08:05:28
 */
@Mapper
public interface EnterpriTeamPaperInfoDao {

    EnterpriTeamPaperInfoDO get(Integer id);

    List<EnterpriTeamPaperInfoDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(EnterpriTeamPaperInfoDO enterpriTeamPaperInfo);

    int update(EnterpriTeamPaperInfoDO enterpriTeamPaperInfo);

    int remove(Integer id);

    int batchRemove(Integer[] ids);
}
