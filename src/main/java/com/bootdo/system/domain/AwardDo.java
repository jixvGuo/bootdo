package com.bootdo.system.domain;

import com.bootdo.activiti.domain.AwardUpDocDo;

import java.util.List;

public class AwardDo {
    private Long id;
    private String awardName;
    private String major;
    private String code;
    private String awardSign;
    private String remark;
    private List<Long> menuIds;
    private List<Long> majorIds;
    private String flg = "false";
    private AwardUpDocDo awardUpDocDo;
    private String proId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAwardName() {
        return awardName;
    }

    public void setAwardName(String awardName) {
        this.awardName = awardName;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAwardSign() {
        return awardSign;
    }

    public void setAwardSign(String awardSign) {
        this.awardSign = awardSign;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<Long> getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(List<Long> menuIds) {
        this.menuIds = menuIds;
    }

    public List<Long> getMajorIds() {
        return majorIds;
    }

    public void setMajorIds(List<Long> majorIds) {
        this.majorIds = majorIds;
    }

    public String getFlg() {
        return flg;
    }

    public void setFlg(String flg) {
        this.flg = flg;
    }

    public AwardUpDocDo getAwardUpDocDo() {
        return awardUpDocDo;
    }

    public void setAwardUpDocDo(AwardUpDocDo awardUpDocDo) {
        this.awardUpDocDo = awardUpDocDo;
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }
}
