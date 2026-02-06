package com.bootdo.cpe.service.impl;

import com.bootdo.cpe.dao.EnterpriTeamAcheievementsDao;
import com.bootdo.cpe.domain.EnterpriTeamAcheievementsDO;
import com.bootdo.cpe.service.EnterpriTeamAcheievementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class EnterpriTeamAcheievementsServiceImpl implements EnterpriTeamAcheievementsService {
    @Autowired
    private EnterpriTeamAcheievementsDao enterpriTeamAcheievementsDao;

    @Override
    public EnterpriTeamAcheievementsDO get(Integer id) {
        return enterpriTeamAcheievementsDao.get(id);
    }

    @Override
    public List<EnterpriTeamAcheievementsDO> list(Map<String, Object> map) {
        return enterpriTeamAcheievementsDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return enterpriTeamAcheievementsDao.count(map);
    }

    @Override
    public int save(EnterpriTeamAcheievementsDO enterpriTeamAcheievements) {
        return enterpriTeamAcheievementsDao.save(enterpriTeamAcheievements);
    }

    @Override
    public int update(EnterpriTeamAcheievementsDO enterpriTeamAcheievements) {
        return enterpriTeamAcheievementsDao.update(enterpriTeamAcheievements);
    }

    @Override
    public int remove(Integer id) {
        return enterpriTeamAcheievementsDao.remove(id);
    }

    @Override
    public int batchRemove(Integer[] ids) {
        return enterpriTeamAcheievementsDao.batchRemove(ids);
    }

}
