package com.bootdo.cpe.service.impl;

import com.bootdo.cpe.dao.EnterpriTeamPaperInfoDao;
import com.bootdo.cpe.domain.EnterpriTeamPaperInfoDO;
import com.bootdo.cpe.service.EnterpriTeamPaperInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class EnterpriTeamPaperInfoServiceImpl implements EnterpriTeamPaperInfoService {
    @Autowired
    private EnterpriTeamPaperInfoDao enterpriTeamPaperInfoDao;

    @Override
    public EnterpriTeamPaperInfoDO get(Integer id) {
        return enterpriTeamPaperInfoDao.get(id);
    }

    @Override
    public List<EnterpriTeamPaperInfoDO> list(Map<String, Object> map) {
        return enterpriTeamPaperInfoDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return enterpriTeamPaperInfoDao.count(map);
    }

    @Override
    public int save(EnterpriTeamPaperInfoDO enterpriTeamPaperInfo) {
        return enterpriTeamPaperInfoDao.save(enterpriTeamPaperInfo);
    }

    @Override
    public int update(EnterpriTeamPaperInfoDO enterpriTeamPaperInfo) {
        return enterpriTeamPaperInfoDao.update(enterpriTeamPaperInfo);
    }

    @Override
    public int remove(Integer id) {
        return enterpriTeamPaperInfoDao.remove(id);
    }

    @Override
    public int batchRemove(Integer[] ids) {
        return enterpriTeamPaperInfoDao.batchRemove(ids);
    }

}
