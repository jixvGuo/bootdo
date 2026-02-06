package com.bootdo.cpe.domain;

import java.util.List;

/**
 * 科技奖团队主要成员数据
 *
 * @author houzb
 * @version 1.0
 * @date 2021-07-26 23:44
 */
public class EnterpriTeamMainUserPoiData {
    List<EnterpriTeamLeaderInfoDO> teamMainUers;
    List<EnterpriTeamOtherMainDO> teamOtherUers;

    public EnterpriTeamMainUserPoiData(List<EnterpriTeamLeaderInfoDO> teamMainUers, List<EnterpriTeamOtherMainDO> oteamUers) {
        this.teamMainUers = teamMainUers;
        this.teamOtherUers = oteamUers;
    }

    public List<EnterpriTeamLeaderInfoDO> getTeamMainUers() {
        return teamMainUers;
    }

    public void setTeamMainUers(List<EnterpriTeamLeaderInfoDO> teamMainUers) {
        this.teamMainUers = teamMainUers;
    }

    public List<EnterpriTeamOtherMainDO> getTeamOtherUers() {
        return teamOtherUers;
    }

    public void setTeamOtherUers(List<EnterpriTeamOtherMainDO> teamOtherUers) {
        this.teamOtherUers = teamOtherUers;
    }
}
