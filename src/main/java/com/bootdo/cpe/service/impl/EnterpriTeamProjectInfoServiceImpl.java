package com.bootdo.cpe.service.impl;

import com.bootdo.cpe.dao.EnterpriTeamProjectInfoDao;
import com.bootdo.cpe.domain.EnterpriTeamProjectInfoDO;
import com.bootdo.cpe.service.EnterpriTeamProjectInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class EnterpriTeamProjectInfoServiceImpl implements EnterpriTeamProjectInfoService {
    @Autowired
    private EnterpriTeamProjectInfoDao enterpriTeamProjectInfoDao;

    @Override
    public EnterpriTeamProjectInfoDO get(Integer id) {
        return enterpriTeamProjectInfoDao.get(id);
    }

    @Override
    public List<EnterpriTeamProjectInfoDO> list(Map<String, Object> map) {
        return enterpriTeamProjectInfoDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return enterpriTeamProjectInfoDao.count(map);
    }

    @Override
    public int save(EnterpriTeamProjectInfoDO enterpriTeamProjectInfo) {
        return enterpriTeamProjectInfoDao.save(enterpriTeamProjectInfo);
    }

    @Override
    public int update(EnterpriTeamProjectInfoDO enterpriTeamProjectInfo) {
        return enterpriTeamProjectInfoDao.update(enterpriTeamProjectInfo);
    }

    @Override
    public int remove(Integer id) {
        return enterpriTeamProjectInfoDao.remove(id);
    }

    @Override
    public int batchRemove(Integer[] ids) {
        return enterpriTeamProjectInfoDao.batchRemove(ids);
    }

}
