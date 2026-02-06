package com.bootdo.cpe.petroleum_engineering_award.dao;

import com.bootdo.cpe.petroleum_engineering_award.domain.OilProInstallBaseInfoDO;
import com.bootdo.cpe.petroleum_engineering_award.domain.OilProQualityInfoDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 石油工程奖数据操作
 *
 * @author houzb
 * @version 1.0
 * @date 2021-03-12 7:09
 */
@Mapper
public interface PetroleumEngineeringDao {

    List<OilProInstallBaseInfoDO> listInstallPro(Map<String, Object> map);

    List<OilProQualityInfoDO> listQualityPro(Map<String, Object> map);

    int countInstallPro(Map<String, Object> map);

    int qualityProCount(Map<String, Object> map);

    int removeApplyInfoByProId(int proId);

    int removeDesignAwardByProId(int proId);

    int removeEngineeAwardByProId(int proId);

    int removeGeneralSituationByProId(int proId);

    int removeUnitOpinionByProId(int proId);

    int removeQualityApplyInfo(int proId);

    int removeQualityContributionUser(int proId);

    /**
     * 更新项目状态
     * @param params
     * @return
     */
    int updateProStat(Map<String,Object> params);

    int removeQualityConfirmFile(long fileId);

    int removeQualityDescFile(long fileId);

}
