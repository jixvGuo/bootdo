package com.bootdo.cpe.domain;

/**
 * @author houzb
 * @Description 申报的企业项目的状态信息
 * @create 2020-09-26 22:03
 */
public enum EnumApplyEnterpriseProStat {
    TO_VALIDATE("to_validate", "待审核中，申请尚未结束","专家审核"),
    VALIDATE("validate", "审核中,申请结束，进入审核中","审核中"),
    SCORE("score", "参评，企业项目可以进入打分节点","参评"),
    REJECT("reject", "完善后参评，企业形式审查被驳回，需企业进行二次提交审核","完善后参评"),
    NO_SCORE("no_score", "不评，不对该申请项目进行评分","不评"),
    DEFER_SCORE("defer_score", "缓评,此次不对此申请项目进行评分","缓评"),
    RESULT("result", "专家打分结束，算出平均分，及生成相关模板数据","已评分"),
    TO_ASSIGN_EXPRETS("to_assign_experts", "去分派专家","待分派专家"),
    ;
    private String stat;
    //备注说明
    private String desc;
    //展示的文字说明
    private String statShowStr;

    EnumApplyEnterpriseProStat(String stat, String desc,String statShowStr) {
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
