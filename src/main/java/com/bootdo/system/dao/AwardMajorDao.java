package com.bootdo.system.dao;

import com.bootdo.system.domain.AwardMajorDo;

import java.util.List;
import java.util.Map;

public interface AwardMajorDao {
    AwardMajorDo get(Long id);

    List<AwardMajorDo> list(Map<String,Object> map);

    int count(Map<String,Object> map);

    int save(AwardMajorDo awardMajor);

    int update(AwardMajorDo awardMajor);

    int remove(Long id);

    int batchRemove(Long[] ids);

    List<Long> listMajorIdByAwardId(Long awardId);

    int removeByAwardId(long awardId);

    int removeByMajorId(int majorId);

    int batchSave(List<AwardMajorDo> list);
}
