package com.bootdo.cpe.service;

import com.bootdo.cpe.dto.QcProDataDto;

import java.util.List;
import java.util.Map;

/**
 * QC奖处理服务
 *
 * @author houzb
 * @version 1.0
 * @date 2022-02-05 10:33
 */
public interface QcAwardService {

    /**
     * 获取项目列表
     * @param map
     * @return
     */
    public List<QcProDataDto> listProInfo(Map<String, Object> map);

    /**
     * 获取项目的数量
     * @param map
     * @return
     */
    public int countProInfo(Map<String, Object> map);

    /**
     * 更新项目的成果编码
     * @param proId
     * @param resultCode
     * @return
     */
    public int updateProResultCode(int proId, String resultCode);

    /**
     * 更新项目状态
     * @param params
     * @return
     */
    int updateProStat(Map<String,Object> params);

    /**
     * 更新项目状态为审核
     * @param proId
     * @return
     */
    public int updateProCheck(int proId);

    public int updateProApply(int proId);

}
