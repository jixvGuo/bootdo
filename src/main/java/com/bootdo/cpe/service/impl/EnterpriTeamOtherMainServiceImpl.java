package com.bootdo.cpe.service.impl;

import com.bootdo.cpe.dao.EnterpriTeamOtherMainDao;
import com.bootdo.cpe.domain.EnterpriTeamLeaderInfoDO;
import com.bootdo.cpe.domain.EnterpriTeamOtherMainDO;
import com.bootdo.cpe.service.EnterpriTeamOtherMainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class EnterpriTeamOtherMainServiceImpl implements EnterpriTeamOtherMainService {
    @Autowired
    private EnterpriTeamOtherMainDao enterpriTeamOtherMainDao;

    @Override
    public EnterpriTeamOtherMainDO get(Integer id) {
        return enterpriTeamOtherMainDao.get(id);
    }

    @Override
    public List<EnterpriTeamOtherMainDO> list(Map<String, Object> map) {
        List<EnterpriTeamOtherMainDO> list = enterpriTeamOtherMainDao.list(map);
        List<EnterpriTeamOtherMainDO> otherMainDOList = list.stream().sorted(Comparator.comparing(EnterpriTeamOtherMainDO::getSerialnumberInt)).collect(Collectors.toList());
        return otherMainDOList;
    }

    @Override
    public int count(Map<String, Object> map) {
        return enterpriTeamOtherMainDao.count(map);
    }

    @Override
    public int save(EnterpriTeamOtherMainDO enterpriTeamOtherMain) {
        return enterpriTeamOtherMainDao.save(enterpriTeamOtherMain);
    }

    @Override
    public int update(EnterpriTeamOtherMainDO enterpriTeamOtherMain) {
        return enterpriTeamOtherMainDao.update(enterpriTeamOtherMain);
    }

    @Override
    public int remove(Integer id) {
        return enterpriTeamOtherMainDao.remove(id);
    }

    @Override
    public int batchRemove(Integer[] ids) {
        return enterpriTeamOtherMainDao.batchRemove(ids);
    }

}
