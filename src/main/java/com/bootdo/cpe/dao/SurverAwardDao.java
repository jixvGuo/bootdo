package com.bootdo.cpe.dao;

import com.bootdo.common.domain.FileDO;
import com.bootdo.cpe.domain.ApplyEnterpriseInfo;
import com.bootdo.cpe.domain.SurverProjectInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author houzb
 * @version 1.0
 * @date 2022-03-28 22:38
 */
@Mapper
public interface SurverAwardDao {

    public List<SurverProjectInfo> listProInfo(Map<String, Object> params);

    public int countProInfo(Map<String, Object> params);

    public List<ApplyEnterpriseInfo> listEnterpriseInfo(Map<String, Object> params);

    public int countEnterpriseInfo(Map<String, Object> params);

    public List<FileDO> getUploadFileList(Map<String, Object> params);

}
