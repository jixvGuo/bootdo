package com.bootdo.cpe.service.impl;

import com.bootdo.cpe.dao.EnterpriTeamCooperationDao;
import com.bootdo.cpe.domain.EnterpriTeamCooperationDO;
import com.bootdo.cpe.service.EnterpriTeamCooperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class EnterpriTeamCooperationServiceImpl implements EnterpriTeamCooperationService {
    @Autowired
    private EnterpriTeamCooperationDao enterpriTeamCooperationDao;

    @Override
    public EnterpriTeamCooperationDO get(Integer id) {
        return enterpriTeamCooperationDao.get(id);
    }

    @Override
    public List<EnterpriTeamCooperationDO> list(Map<String, Object> map) {
        return enterpriTeamCooperationDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return enterpriTeamCooperationDao.count(map);
    }

    @Override
    public int save(EnterpriTeamCooperationDO enterpriTeamCooperation) {
        return enterpriTeamCooperationDao.save(enterpriTeamCooperation);
    }

    @Override
    public int update(EnterpriTeamCooperationDO enterpriTeamCooperation) {
        return enterpriTeamCooperationDao.update(enterpriTeamCooperation);
    }

    @Override
    public int remove(Integer id) {
        return enterpriTeamCooperationDao.remove(id);
    }

    @Override
    public int batchRemove(Integer[] ids) {
        return enterpriTeamCooperationDao.batchRemove(ids);
    }

}
