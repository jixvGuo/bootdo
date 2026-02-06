package com.bootdo.cpe.service.impl;

import com.bootdo.cpe.dao.EnterpriTeamGetTechAwardDao;
import com.bootdo.cpe.domain.EnterpriTeamGetTechAwardDO;
import com.bootdo.cpe.service.EnterpriTeamGetTechAwardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class EnterpriTeamGetTechAwardServiceImpl implements EnterpriTeamGetTechAwardService {
    @Autowired
    private EnterpriTeamGetTechAwardDao enterpriTeamGetTechAwardDao;

    @Override
    public EnterpriTeamGetTechAwardDO get(Integer id) {
        return enterpriTeamGetTechAwardDao.get(id);
    }

    @Override
    public List<EnterpriTeamGetTechAwardDO> list(Map<String, Object> map) {
        return enterpriTeamGetTechAwardDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return enterpriTeamGetTechAwardDao.count(map);
    }

    @Override
    public int save(EnterpriTeamGetTechAwardDO enterpriTeamGetTechAward) {
        return enterpriTeamGetTechAwardDao.save(enterpriTeamGetTechAward);
    }

    @Override
    public int update(EnterpriTeamGetTechAwardDO enterpriTeamGetTechAward) {
        return enterpriTeamGetTechAwardDao.update(enterpriTeamGetTechAward);
    }

    @Override
    public int remove(Integer id) {
        return enterpriTeamGetTechAwardDao.remove(id);
    }

    @Override
    public int batchRemove(Integer[] ids) {
        return enterpriTeamGetTechAwardDao.batchRemove(ids);
    }

}
