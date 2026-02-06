package com.bootdo.cpe.service.impl;

import com.bootdo.common.domain.FileDO;
import com.bootdo.cpe.dao.SurverAwardDao;
import com.bootdo.cpe.domain.ApplyEnterpriseInfo;
import com.bootdo.cpe.domain.SurverProjectInfo;
import com.bootdo.cpe.service.SurverAwardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author houzb
 * @version 1.0
 * @date 2022-03-28 22:36
 */
@Service
public class SurverAwardServiceImpl implements SurverAwardService {
    @Autowired
    private SurverAwardDao surverAwardDao;

    @Override
    public List<SurverProjectInfo> listProInfo(Map<String, Object> params) {
        List<SurverProjectInfo> list = surverAwardDao.listProInfo(params);
        AtomicInteger num = new AtomicInteger(1);
        list.stream().forEach(pro->{
            pro.initApplyStat();
            pro.setId(num.getAndIncrement());
        });
        return list;
    }

    @Override
    public int countProInfo(Map<String, Object> params) {
        return surverAwardDao.countProInfo(params);
    }

    @Override
    public List<ApplyEnterpriseInfo> listEnterpriseInfo(Map<String, Object> params) {
        return surverAwardDao.listEnterpriseInfo(params);
    }

    @Override
    public int countEnterpriseInfo(Map<String, Object> params) {
        return surverAwardDao.countEnterpriseInfo(params);
    }

    /**
     * 获取文件信息
     *
     * @param params
     * @return
     */
    @Override
    public List<FileDO> getUploadFileList(Map<String, Object> params) {
        return surverAwardDao.getUploadFileList(params);
    }
}
