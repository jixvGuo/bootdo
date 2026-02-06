package com.bootdo.cpe.service;

import com.bootdo.cpe.domain.TechnologyProjectInfo;

import java.util.List;
import java.util.Map;

/**
 * 科技进步奖服务
 *
 * @author houzb
 * @version 1.0
 * @date 2021-08-18 23:52
 */
public interface ScienceProcessService {

    public List<String> getUploadFileUrlList(int proId);

    public List<TechnologyProjectInfo> listProInfo(Map<String, Object> params);
    public int countProInfo(Map<String, Object> params);

}
