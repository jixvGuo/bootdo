package com.bootdo.cpe.petroleum_engineering_award.service.impl;

import com.bootdo.cpe.petroleum_engineering_award.dao.PetroleumEngineeringDao;
import com.bootdo.cpe.petroleum_engineering_award.domain.OilProInstallBaseInfoDO;
import com.bootdo.cpe.petroleum_engineering_award.domain.OilProQualityInfoDO;
import com.bootdo.cpe.petroleum_engineering_award.domain.OilProStatEnum;
import com.bootdo.cpe.petroleum_engineering_award.service.PetroleumEngineeringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 石油工程业务实际处理
 *
 * @author houzb
 * @version 1.0
 * @date 2021-03-12 7:21
 */
@Service
public class PetroleumEngineeringServiceImpl  implements PetroleumEngineeringService {
    @Autowired
    private PetroleumEngineeringDao petroleumEngineeringDao;


    @Override
    public List<OilProInstallBaseInfoDO> listInstallPro(Map<String, Object> map) {
        //是否为协会人员
        Object isAssociationUserObj = map.get("isAssociationUser");
        boolean isAssociationUser = isAssociationUserObj != null ? Boolean.parseBoolean(isAssociationUserObj.toString()) : false;
        List<OilProInstallBaseInfoDO> list = petroleumEngineeringDao.listInstallPro(map);
        list.stream().forEach(p->{
            p.initApplyStat();
        });
        return list;
    }

    @Override
    public List<OilProQualityInfoDO> listQualityPro(Map<String, Object> map) {
        List<OilProQualityInfoDO> list = petroleumEngineeringDao.listQualityPro(map);
        list.stream().forEach(p->{
            p.initApplyStat();
        });
        return list;
    }

    @Override
    public int installProCount(Map<String, Object> map) {
        return petroleumEngineeringDao.countInstallPro(map);
    }

    @Override
    public int qualityProCount(Map<String, Object> map) {
        return petroleumEngineeringDao.qualityProCount(map);
    }

    @Override
    public int removeInstallPro(int proId) {
        removeApplyInfoByProId(proId);

        removeDesignAwardByProId(proId);

        removeEngineeAwardByProId(proId);

        removeGeneralSituationByProId(proId);

        removeUnitOpinionByProId(proId);
        return 1;
    }

    @Override
    public int removeQualityPro(int proId) {
        removeQualityApplyInfo(proId);

        removeQualityContributionUser(proId);

        return 1;
    }

    @Override
    public int removeApplyInfoByProId(int proId) {
        return petroleumEngineeringDao.removeApplyInfoByProId(proId);
    }

    @Override
    public int removeDesignAwardByProId(int proId) {
        return petroleumEngineeringDao.removeDesignAwardByProId(proId);
    }

    @Override
    public int removeEngineeAwardByProId(int proId) {
        return petroleumEngineeringDao.removeEngineeAwardByProId(proId);
    }

    @Override
    public int removeGeneralSituationByProId(int proId) {
        return petroleumEngineeringDao.removeGeneralSituationByProId(proId);
    }

    @Override
    public int removeUnitOpinionByProId(int proId) {
        return petroleumEngineeringDao.removeUnitOpinionByProId(proId);
    }

    @Override
    public int removeQualityApplyInfo(int proId) {
        return petroleumEngineeringDao.removeQualityApplyInfo(proId);
    }

    @Override
    public int removeQualityContributionUser(int proId) {
        return petroleumEngineeringDao.removeQualityContributionUser(proId);
    }

    @Override
    public int updateProStat(Map<String, Object> params) {
        Object reviewResultObj = params.get("reviewResult");
        if(reviewResultObj != null) {
            //传递了审核结果，则根据审核结果获取项目状态结果
            String reviewRst = reviewResultObj.toString();
            String proStat = "";
            if(OilProStatEnum.PARTAKE_AWARD.getStatDesc().equals(reviewRst)) {
                proStat = OilProStatEnum.PARTAKE_AWARD.getProStat();
            }else if(OilProStatEnum.REJECT.getStatDesc().equals(reviewRst)) {
                proStat = OilProStatEnum.REJECT.getProStat();
            }else if(OilProStatEnum.DELAYED_AWARD.getStatDesc().equals(reviewRst)) {
                proStat = OilProStatEnum.DELAYED_AWARD.getProStat();
            }else if(OilProStatEnum.NO_AWARD.getStatDesc().equals(reviewRst)) {
                proStat = OilProStatEnum.NO_AWARD.getProStat();
            }
            params.put("proStat", proStat);
        }
        Object proStatObj = params.get("proStat");
        if(proStatObj != null) {
            return petroleumEngineeringDao.updateProStat(params);
        }
        return 0;
    }

    @Override
    public int updateProCheck(int proId) {
        Map<String,Object> params = new HashMap<>();
        params.put("proId", proId);
        params.put("proStat", "check");
        return updateProStat(params);
    }

    @Override
    public int updateProPartakeAward(int proId) {
        Map<String,Object> params = new HashMap<>();
        params.put("proId", proId);
        params.put("proStat", "partake_award");
        return updateProStat(params);
    }

    @Override
    public int updateProReject(int proId) {
        Map<String,Object> params = new HashMap<>();
        params.put("proId", proId);
        params.put("proStat", "reject");
        return updateProStat(params);
    }

    @Override
    public int updateProDelayedAward(int proId) {
        Map<String,Object> params = new HashMap<>();
        params.put("proId", proId);
        params.put("proStat", "delayed_award");
        return updateProStat(params);
    }

    @Override
    public int updateProNoAwards(int proId) {
        Map<String,Object> params = new HashMap<>();
        params.put("proId", proId);
        params.put("proStat", "no_awards");
        return updateProStat(params);
    }

    @Override
    public int updateProApply(int proId) {
        Map<String,Object> params = new HashMap<>();
        params.put("proId", proId);
        params.put("proStat", "");
        return updateProStat(params);
    }

    @Override
    public int removeQualityConfirmFile(long fileId) {
        return petroleumEngineeringDao.removeQualityConfirmFile(fileId);
    }

    @Override
    public int removeQualityDescFile(long fileId) {
        return petroleumEngineeringDao.removeQualityDescFile(fileId);
    }
}
