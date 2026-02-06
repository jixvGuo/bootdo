package com.bootdo.cpe.service;


import com.bootdo.common.domain.FileDO;
import com.bootdo.cpe.domain.ApplyEnterpriseInfo;
import com.bootdo.cpe.domain.SurverProjectInfo;

import java.util.List;
import java.util.Map;

/**
 * 勘察奖服务
 *
 * @author houzb
 * @version 1.0
 * @date 2022-03-28 22:31
 */
public interface SurverAwardService {

    public List<SurverProjectInfo> listProInfo(Map<String, Object> params);
    public int countProInfo(Map<String, Object> params);

    public List<ApplyEnterpriseInfo> listEnterpriseInfo(Map<String, Object> params);
    public int countEnterpriseInfo(Map<String, Object> params);

    /**
     * 获取文件信息
     * @param params
     * @return
     */
    public List<FileDO> getUploadFileList(Map<String, Object> params);

}
