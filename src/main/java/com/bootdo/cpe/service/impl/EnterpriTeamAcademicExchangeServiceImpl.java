package com.bootdo.cpe.service.impl;

import com.bootdo.cpe.dao.EnterpriTeamAcademicExchangeDao;
import com.bootdo.cpe.domain.EnterpriTeamAcademicExchangeDO;
import com.bootdo.cpe.service.EnterpriTeamAcademicExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class EnterpriTeamAcademicExchangeServiceImpl implements EnterpriTeamAcademicExchangeService {
    @Autowired
    private EnterpriTeamAcademicExchangeDao enterpriTeamAcademicExchangeDao;

    @Override
    public EnterpriTeamAcademicExchangeDO get(Integer id) {
        return enterpriTeamAcademicExchangeDao.get(id);
    }

    @Override
    public List<EnterpriTeamAcademicExchangeDO> list(Map<String, Object> map) {
        map.put("sort", "id");
        map.put("order", "asc");
        List<EnterpriTeamAcademicExchangeDO> list = enterpriTeamAcademicExchangeDao.list(map);
        return list;
    }

    @Override
    public int count(Map<String, Object> map) {
        return enterpriTeamAcademicExchangeDao.count(map);
    }

    @Override
    public int save(EnterpriTeamAcademicExchangeDO enterpriTeamAcademicExchange) {
        return enterpriTeamAcademicExchangeDao.save(enterpriTeamAcademicExchange);
    }

    @Override
    public int update(EnterpriTeamAcademicExchangeDO enterpriTeamAcademicExchange) {
        return enterpriTeamAcademicExchangeDao.update(enterpriTeamAcademicExchange);
    }

    @Override
    public int remove(Integer id) {
        return enterpriTeamAcademicExchangeDao.remove(id);
    }

    @Override
    public int batchRemove(Integer[] ids) {
        return enterpriTeamAcademicExchangeDao.batchRemove(ids);
    }

}
