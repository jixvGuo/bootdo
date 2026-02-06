package com.bootdo.common.config;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class Constant {
    public static Gson gson = new Gson();
    //演示系统账户
    public static String DEMO_ACCOUNT = "test";
    //自动去除表前缀
    public static String AUTO_REOMVE_PRE = "true";
    //停止计划任务
    public static String STATUS_RUNNING_STOP = "stop";
    //开启计划任务
    public static String STATUS_RUNNING_START = "start";
    //通知公告阅读状态-未读
    public static String OA_NOTIFY_READ_NO = "0";
    //通知公告阅读状态-已读
    public static int OA_NOTIFY_READ_YES = 1;
    //部门根节点id
    public static Long DEPT_ROOT_ID = 0l;
    //缓存方式
    public static String CACHE_TYPE_REDIS ="redis";

    public static String LOG_ERROR = "error";

    //协会领导
    public static long ROLE_ASSOCIATION_LEADER = 64L;
    public static long ROLE_ADMIN_ID = 1L;
    //科技奖协会联系人角色id
    public static long ROLE_SCIENCE_ASSOCIATION_ID = 60L;
    //QC奖协会联系人角色id
    public static long ROLE_QC_ASSOCIATION_ID = 70L;
    //勘察设计奖协会联系人角色ID
    public static long ROLE_SURVER_ASSOCIATION_ID = 74L;
    //工法奖联系人角色
    public static long ROLE_GONGFA_ASSOCIATION_ID = 78L;
    //科技奖线下评审用户角色，同协会联系人的角色
    public static long ROLE_SCIENCE_OFFLINE_VIEW_ID = 69L;
    public static long ROLE_QC_OFFLINE_VIEW_ID = 69L;
    //勘察奖线下角色id
    public static long ROLE_SURVER_OFFLINE_VIEW_ID = -69L;
    //科技奖企业用户角色id 企业用户（科技奖）
    public static long ROLE_ENTERPRISE_SCIENCE_ID = 61L;
    //科技奖企业用户角色id 企业用户（QC奖）
    public static long ROLE_ENTERPRISE_QC_ID = 71L;
    //勘察设计奖企业用户角色id
    public static long ROLE_ENTERPRISE_SURVER_ID = 73L;
    //勘察设计奖评审专家
    public static long ROLE_SURVER_SPECALIST_ID = 76L;
    //协会工作人员角色id 石油工程
    public static long ROLE_ASSOCIATION_OIL_PRO_ID = 66L;
    //企业用户（优质工程奖）
    public static long ROLE_ENTERPRISE_ENGINEER_ID = 67L;
    //工法企业用户角色id
    public static long ROLE_GONGFA_ENTERPRISE_ID = 79L;
    //科技奖专家角色id
    public static long ROLE_SPECIALIST_ID = 62L;
    //科技奖协会外聘人员
    public static long ROLE_SCIENCE_EXTERNAL_EMPLOYMENT_ID = 65L;
    //QC奖协会外聘人员
    public static long ROLE_QC_EXTERNAL_EMPLOYMENT_ID = 72L;
    //勘察奖协会外聘人员
    public static long ROLE_SURVER_EXTERNAL_EMPLOYMENT_ID = 75L;
    //优质工程外聘
    public static long ROLE_GOOD_PRO_EMPLOYMENT_ID = 82L;
    //工法外聘
    public static long ROLE_GONGFA_EMPLOYMENT_ID = 83L;
    /**
     * 1.将“申报任务管理”中按钮“项目列表”中的形式审查部分复制一份到左侧目录“企业形式检查”，
     * 并且取消“申报任务管理”中按钮“项目列表”中的形式检查按钮，改成“查看”，仅能查看
     * 2.在“企业形式检查”页面中显示的项目列表应该是被协会负责人分配好的项目
     */
    public static String PAGE_SOURCE_ENTERPRISE_VALIDATE_MENU = "enterprise_validate_menu";

    /**
     * 奖项类型科技进步奖
     */
    public static String AWARD_TYPE_SCIENCE_ID = "1";

    public static Map<String, Integer> CHINESE_NUMBER_MAP = new HashMap<>();
    static {
        CHINESE_NUMBER_MAP.put("一", 1);
        CHINESE_NUMBER_MAP.put("二", 2);
        CHINESE_NUMBER_MAP.put("三", 3);
        CHINESE_NUMBER_MAP.put("四", 4);
        CHINESE_NUMBER_MAP.put("五", 5);
        CHINESE_NUMBER_MAP.put("六", 6);
        CHINESE_NUMBER_MAP.put("七", 7);
        CHINESE_NUMBER_MAP.put("八", 8);
        CHINESE_NUMBER_MAP.put("九", 9);
        CHINESE_NUMBER_MAP.put("十", 10);
        CHINESE_NUMBER_MAP.put("十一", 11);
    }



}
