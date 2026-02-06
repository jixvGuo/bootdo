package com.bootdo.cpe.service.impl;

import com.bootdo.cpe.dao.EnterpriTeamLeaderInfoDao;
import com.bootdo.cpe.domain.EnterpriTeamLeaderInfoDO;
import com.bootdo.cpe.service.EnterpriTeamLeaderInfoService;
import com.bootdo.system.domain.EnterpriseChengguoOtherInfoDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class EnterpriTeamLeaderInfoServiceImpl implements EnterpriTeamLeaderInfoService {
    @Autowired
    private EnterpriTeamLeaderInfoDao enterpriTeamLeaderInfoDao;

    @Override
    public EnterpriTeamLeaderInfoDO get(Integer id) {
        return enterpriTeamLeaderInfoDao.get(id);
    }

    @Override
    public List<EnterpriTeamLeaderInfoDO> list(Map<String, Object> map) {
        List<EnterpriTeamLeaderInfoDO> list = enterpriTeamLeaderInfoDao.list(map);
        List<EnterpriTeamLeaderInfoDO> mainUserList = list.stream().sorted(Comparator.comparing(EnterpriTeamLeaderInfoDO::getSerialnumberInt)).collect(Collectors.toList());
        return mainUserList;
    }

    @Override
    public int count(Map<String, Object> map) {
        return enterpriTeamLeaderInfoDao.count(map);
    }

    @Override
    public int save(EnterpriTeamLeaderInfoDO enterpriTeamLeaderInfo) {
        return enterpriTeamLeaderInfoDao.save(enterpriTeamLeaderInfo);
    }

    @Override
    public int update(EnterpriTeamLeaderInfoDO enterpriTeamLeaderInfo) {
        return enterpriTeamLeaderInfoDao.update(enterpriTeamLeaderInfo);
    }

    @Override
    public int remove(Integer id) {
        return enterpriTeamLeaderInfoDao.remove(id);
    }

    @Override
    public int batchRemove(Integer[] ids) {
        return enterpriTeamLeaderInfoDao.batchRemove(ids);
    }

}
