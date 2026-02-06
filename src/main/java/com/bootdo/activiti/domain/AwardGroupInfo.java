package com.bootdo.activiti.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 分组信息，用于专家领导审核的左侧菜单使用
 */
public class AwardGroupInfo {
    private String id;
    private String majorName;
    private String parentId;

    private String awardName;
    private int awardId;
    //增加前缀使得奖项id和专业id不至于重复
    private String awardIdStr;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName =majorName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getAwardName() {
        return awardName;
    }

    public void setAwardName(String awardName) {
        this.awardName = awardName;
    }

    public int getAwardId() {
        return awardId;
    }

    public void setAwardId(int awardId) {
        this.awardId = awardId;
        this.awardIdStr = "award_" + awardId;
        //奖项作为专业父级节点 仅用于显示使用
        this.parentId = this.awardIdStr;
    }

    public AwardGroupInfo getAwardInfo() {
        AwardGroupInfo awardInfo = new AwardGroupInfo();
        awardInfo.setMajorName(this.awardName);
        awardInfo.setParentId("0");
        awardInfo.setId(this.awardIdStr);
        return awardInfo;
    }

    public List<AwardGroupInfo> optMenus(){
        List<AwardGroupInfo> list = new ArrayList<>();
        AwardGroupInfo info = new AwardGroupInfo();
        info.setMajorName("科技奖打分统计表");
        info.setParentId(this.id);
        info.setId("score_static_science");
        list.add(info);
        AwardGroupInfo infoTeam = new AwardGroupInfo();
        infoTeam.setMajorName("团队奖打分统计表");
        infoTeam.setParentId(this.id);
        infoTeam.setId("score_static_team");
        list.add(infoTeam);
        AwardGroupInfo infoPersonal = new AwardGroupInfo();
        infoPersonal.setMajorName("个人奖打分统计表");
        infoPersonal.setParentId(this.id);
        infoPersonal.setId("score_static_personal");
        list.add(infoPersonal);

        AwardGroupInfo recommendTable = new AwardGroupInfo();
        recommendTable.setMajorName("专家组推荐意见表");
        recommendTable.setParentId(this.id);
        recommendTable.setId("recommend");
        list.add(recommendTable);

        AwardGroupInfo reviewTable = new AwardGroupInfo();
        reviewTable.setId("review");
        reviewTable.setParentId(this.id);
        reviewTable.setMajorName("专家评审意见表");
        list.add(reviewTable);

        return list;
    }
}
