package com.bootdo.cpe.service.impl;

import com.bootdo.cpe.dao.EnterpriTeamIntellectualPropertyDao;
import com.bootdo.cpe.domain.EnterpriTeamIntellectualPropertyDO;
import com.bootdo.cpe.service.EnterpriTeamIntellectualPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class EnterpriTeamIntellectualPropertyServiceImpl implements EnterpriTeamIntellectualPropertyService {
    @Autowired
    private EnterpriTeamIntellectualPropertyDao enterpriTeamIntellectualPropertyDao;

    @Override
    public EnterpriTeamIntellectualPropertyDO get(Integer id) {
        return enterpriTeamIntellectualPropertyDao.get(id);
    }

    @Override
    public List<EnterpriTeamIntellectualPropertyDO> list(Map<String, Object> map) {
        return enterpriTeamIntellectualPropertyDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return enterpriTeamIntellectualPropertyDao.count(map);
    }

    @Override
    public int save(EnterpriTeamIntellectualPropertyDO enterpriTeamIntellectualProperty) {
        return enterpriTeamIntellectualPropertyDao.save(enterpriTeamIntellectualProperty);
    }

    @Override
    public int update(EnterpriTeamIntellectualPropertyDO enterpriTeamIntellectualProperty) {
        return enterpriTeamIntellectualPropertyDao.update(enterpriTeamIntellectualProperty);
    }

    @Override
    public int remove(Integer id) {
        return enterpriTeamIntellectualPropertyDao.remove(id);
    }

    @Override
    public int batchRemove(Integer[] ids) {
        return enterpriTeamIntellectualPropertyDao.batchRemove(ids);
    }

}
