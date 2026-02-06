package com.bootdo.cpe.utils;

import com.bootdo.common.utils.StringUtils;
import com.bootdo.cpe.domain.EnumApplyEnterpriseProStat;
import com.bootdo.cpe.domain.EnumApplyTaskStat;
import com.bootdo.cpe.domain.EnumProjectType;
import com.bootdo.cpe.domain.EnumScienceScoreType;

/**
 * @author houzb
 * @Description
 * @create 2020-09-29 2:16
 */
public class EnumUtils {

     public static String getProTypeByScoreType(String scoreType) {
         if(EnumScienceScoreType.SCIENCE.getScoreType().equals(scoreType)) {
             return EnumProjectType.SCIENCE_PROGRESS.getProType();
         }
         if(EnumScienceScoreType.TEAM.getScoreType().equals(scoreType)) {
             return EnumProjectType.SCIENCE_TEAM.getProType();
         }
         if(EnumScienceScoreType.PERSONAL.getScoreType().equals(scoreType)) {
             return EnumProjectType.SCIENCE_PERSONAL.getProType();
         }
         return null;
     }

     public static String getProjectStatShowStrByStat(String stat) {
        EnumApplyEnterpriseProStat[] proStats = EnumApplyEnterpriseProStat.values();
        for(EnumApplyEnterpriseProStat ps:proStats) {
            if(ps.getStat().equals(stat)) {
                return ps.getStatShowStr();
            }
        }
        return "";
    }

    public static String getProjectTypeShowStrByType(String proType) {
        EnumProjectType[] proStats = EnumProjectType.values();
        for(EnumProjectType ps:proStats) {
            if(ps.getProType().equals(proType)) {
                return ps.getDesc();
            }
        }
        return "";
    }

    public static String getTaskStatShowStrByStat(String stat) {
        EnumApplyTaskStat[] proStats = EnumApplyTaskStat.values();
        for(EnumApplyTaskStat ps:proStats) {
            if(ps.getStat().equals(stat)) {
                return ps.getStatShowStr();
            }
        }
        return "";
    }

    /**
     * 项目是否可以编辑
     * @param proStat
     * @return true可以编辑 false不可以编辑
     */
    public static boolean proIsCouldEdit(String proStat) {
         return  StringUtils.isBlank(proStat) || EnumApplyEnterpriseProStat.REJECT.getStat().equals(proStat) ;
    }
}
