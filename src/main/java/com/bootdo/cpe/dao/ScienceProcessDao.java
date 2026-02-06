package com.bootdo.cpe.dao;

import com.bootdo.cpe.domain.TechnologyProjectInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author houzb
 * @version 1.0
 * @date 2021-08-18 23:55
 */
@Mapper
public interface ScienceProcessDao {

    public List<String> getUploadFileUrlList(int proId);

    public Integer getMaxProCode(Map<String,Object> params);

    public String getTaskIdByProId(int proId);

    public int updatePorCode(Map<String,Object> params);

    public List<TechnologyProjectInfo> listProInfo(Map<String, Object> params);

    public int countProInfo(Map<String, Object> params);
}
