package com.bootdo.cpe.service.impl;

import com.bootdo.cpe.dao.EnterpriTeamEvaluateOtherDao;
import com.bootdo.cpe.domain.EnterpriTeamEvaluateOtherDO;
import com.bootdo.cpe.service.EnterpriTeamEvaluateOtherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class EnterpriTeamEvaluateOtherServiceImpl implements EnterpriTeamEvaluateOtherService {
    @Autowired
    private EnterpriTeamEvaluateOtherDao enterpriTeamEvaluateOtherDao;

    @Override
    public EnterpriTeamEvaluateOtherDO get(Integer id) {
        return enterpriTeamEvaluateOtherDao.get(id);
    }

    @Override
    public List<EnterpriTeamEvaluateOtherDO> list(Map<String, Object> map) {
        return enterpriTeamEvaluateOtherDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return enterpriTeamEvaluateOtherDao.count(map);
    }

    @Override
    public int save(EnterpriTeamEvaluateOtherDO enterpriTeamEvaluateOther) {
        return enterpriTeamEvaluateOtherDao.save(enterpriTeamEvaluateOther);
    }

    @Override
    public int update(EnterpriTeamEvaluateOtherDO enterpriTeamEvaluateOther) {
        return enterpriTeamEvaluateOtherDao.update(enterpriTeamEvaluateOther);
    }

    @Override
    public int remove(Integer id) {
        return enterpriTeamEvaluateOtherDao.remove(id);
    }

    @Override
    public int batchRemove(Integer[] ids) {
        return enterpriTeamEvaluateOtherDao.batchRemove(ids);
    }

}
