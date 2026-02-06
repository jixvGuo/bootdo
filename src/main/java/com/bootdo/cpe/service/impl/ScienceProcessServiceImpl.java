package com.bootdo.cpe.service.impl;

import com.bootdo.cpe.dao.ScienceProcessDao;
import com.bootdo.cpe.domain.SurverProjectInfo;
import com.bootdo.cpe.domain.TechnologyProjectInfo;
import com.bootdo.cpe.service.ScienceProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author houzb
 * @version 1.0
 * @date 2021-08-18 23:54
 */
@Service
public class ScienceProcessServiceImpl implements ScienceProcessService {

    @Autowired
    private ScienceProcessDao scienceProcessDao;

    @Override
    public List<String> getUploadFileUrlList(int proId) {
        return scienceProcessDao.getUploadFileUrlList(proId);
    }

    @Override
    public List<TechnologyProjectInfo> listProInfo(Map<String, Object> params) {
        List<TechnologyProjectInfo> list = scienceProcessDao.listProInfo(params);
        AtomicInteger num = new AtomicInteger(1);
        list.stream().forEach(pro->{
            pro.initApplyStat();
            pro.setId(num.getAndIncrement());
        });
        return list;
    }

    @Override
    public int countProInfo(Map<String, Object> params) {
        return scienceProcessDao.countProInfo(params);
    }
}
