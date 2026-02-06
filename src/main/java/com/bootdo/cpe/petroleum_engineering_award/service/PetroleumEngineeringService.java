package com.bootdo.cpe.petroleum_engineering_award.service;

import com.bootdo.cpe.petroleum_engineering_award.domain.OilProInstallBaseInfoDO;
import com.bootdo.cpe.petroleum_engineering_award.domain.OilProInstallReviewRecordDO;
import com.bootdo.cpe.petroleum_engineering_award.domain.OilProQualityInfoDO;
import com.bootdo.cpe.petroleum_engineering_award.domain.OilProQualityReviewRecordDO;

import java.util.List;
import java.util.Map;

/**
 * 石油工程业务操作
 *
 * @author houzb
 * @version 1.0
 * @date 2021-03-12 7:20
 */
public interface PetroleumEngineeringService {

    List<OilProInstallBaseInfoDO> listInstallPro(Map<String, Object> map);
    List<OilProQualityInfoDO> listQualityPro(Map<String,Object> map);

    int installProCount(Map<String, Object> map);
    int qualityProCount(Map<String, Object> map);

    int removeInstallPro(int proId);
    int removeQualityPro(int proId);

    int removeApplyInfoByProId(int proId);

    int removeDesignAwardByProId(int proId);

    int removeEngineeAwardByProId(int proId);

    int removeGeneralSituationByProId(int proId);

    int removeUnitOpinionByProId(int proId);

    int removeQualityApplyInfo(int proId);
    int removeQualityContributionUser(int proId);


    int updateProStat(Map<String,Object> params);

    int updateProCheck(int proId);
    //参评
    int updateProPartakeAward(int proId);
    //完善后参评
    int updateProReject(int proId);
    //缓评
    int updateProDelayedAward(int proId);
    //不评
    int updateProNoAwards(int proId);
    //申请中
    int updateProApply(int proId);

    int removeQualityConfirmFile(long fileId);
    int removeQualityDescFile(long fileId);
}
