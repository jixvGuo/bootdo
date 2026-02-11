package com.bootdo.common.utils;

import org.apache.commons.lang.RandomStringUtils;

import java.util.List;

import static com.bootdo.common.config.Constant.*;


public class CommonUtils {

    public static String getApplyIdStr(String type,Long uid) {
        String applyId = type + (uid == null ? 0 : uid) + "_" + System.currentTimeMillis() + RandomStringUtils.randomAlphanumeric(10);
        return applyId;
    }
    public static String getApplyTeamId(Long uid) {
        return getApplyIdStr("T",uid);
    }
    public static String getApplyPersonalId(Long uid) {
        return getApplyIdStr("P",uid);
    }
    public static String getApplyScienceId(Long uid) {
        return getApplyIdStr("S",uid);
    }

    public static String replaceHtmlTagP(String str) {
        if(StringUtils.isBlank(str)) {
            return "";
        }
        str = str.replaceAll("<p>","");
        str = str.replaceAll("</p>","");
        return str;
    }

    public static boolean isViewProCode(List<Long> roleIdList) {
        if(roleIdList.contains(ROLE_ASSOCIATION_LEADER)) {
            return true;
        }
        if(roleIdList.contains(ROLE_SCIENCE_ASSOCIATION_ID)) {
            return true;
        }
        if(roleIdList.contains(ROLE_QC_ASSOCIATION_ID)) {
            return true;
        }
        if(roleIdList.contains(ROLE_SURVER_ASSOCIATION_ID)) {
            return true;
        }
        if(roleIdList.contains(ROLE_GONGFA_ASSOCIATION_ID)) {
            return true;
        }
        if(roleIdList.contains(ROLE_SCIENCE_EXTERNAL_EMPLOYMENT_ID)) {
            return true;
        }
        if(roleIdList.contains(ROLE_QC_EXTERNAL_EMPLOYMENT_ID)) {
            return true;
        }
        if(roleIdList.contains(ROLE_SURVER_EXTERNAL_EMPLOYMENT_ID)) {
            return true;
        }
        /*新增QC企业人员可以看到proCode add by sys@20260209*/
        if(roleIdList.contains(ROLE_ENTERPRISE_QC_ID)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(String.format("你好%s,%S","http://www.baidu.com?A", "http://www.baidu.com"));
    }
}
