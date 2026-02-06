package com.bootdo.cpe.dao;

import com.bootdo.cpe.dto.QcProDataDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * QC奖项db
 *
 * @author houzb
 * @version 1.0
 * @date 2022-02-06 8:59
 */
@Mapper
public interface QcAwardDao {

    public List<QcProDataDto> getProDataList(Map<String, Object> params);

    public int countProData(Map<String, Object> params);

    public int updateProStat(Map<String,Object> params);

}
