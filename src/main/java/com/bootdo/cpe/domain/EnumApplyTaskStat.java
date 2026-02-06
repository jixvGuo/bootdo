package com.bootdo.cpe.domain;

/**
 * @author houzb
 * @Description 创建的申报
 * 任务的状态枚举
 * @create 2020-09-26 21:57
 */
public enum EnumApplyTaskStat {
    APPLY("apply", "申请中，企业可进行提交申请","申请中"),
    ASSIGN_VALIDATE("assign_validate", "停止申请，协会工作人员进行项目分派给外聘人员进行形式审查","停止申请"),
    VALIDATE("validate", "分派完成，外聘人员对企业申报信息进行形式审核","形式审查"),
    SCORE("score", "形式审查结束，专家对企业申报的各项进行打分","打分"),
    RESULT("result", "打分结束，将专家打分进行汇总，并生成模板,整个流程结束","结束");
    private String stat;
    private String desc;
    //展示的文字说明
    private String statShowStr;


    EnumApplyTaskStat(String stat, String desc,String statShowStr) {
        this.stat = stat;
        this.desc = desc;
        this.statShowStr = statShowStr;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getStatShowStr() {
        return statShowStr;
    }

    public void setStatShowStr(String statShowStr) {
        this.statShowStr = statShowStr;
    }
}
