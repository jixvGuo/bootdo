package com.bootdo.cpe.domain;

import com.bootdo.system.domain.EnterpriTeamInfoDO;
import com.bootdo.system.domain.EnterpriTeamUsersDO;

/**
 * @author houzb
 * @Description
 * @create 2020-09-20 9:06
 */
public class DocTeamApplyWord extends DocBaseDataWord {
    private EnterpriTeamInfoDO teamInfo;
    private EnterpriTeamUsersDO teamUsersDO;//人员构成
    //团队简介信息
    private EnterpriTeamIntrductionDO teamIntrductionDO;

    public EnterpriTeamInfoDO getTeamInfo() {
        return teamInfo;
    }

    public void setTeamInfo(EnterpriTeamInfoDO teamInfo) {
        this.teamInfo = teamInfo;
    }

    public EnterpriTeamUsersDO getTeamUsersDO() {
        return teamUsersDO;
    }

    public void setTeamUsersDO(EnterpriTeamUsersDO teamUsersDO) {
        this.teamUsersDO = teamUsersDO;
    }

    public EnterpriTeamIntrductionDO getTeamIntrductionDO() {
        return teamIntrductionDO;
    }

    public void setTeamIntrductionDO(EnterpriTeamIntrductionDO teamIntrductionDO) {
        this.teamIntrductionDO = teamIntrductionDO;
    }
}
