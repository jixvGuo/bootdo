package com.bootdo.cpe.domain;

import java.io.Serializable;
import java.util.Date;


/**
 * 发表论文专著情况
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-09-18 08:05:28
 */
public class EnterpriTeamPaperInfoDO implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;
    //
    private Integer proId;
    //
    private String taskId;
    //
    private String applyId;
    //
    private Long optUid;
    //论文专著名称
    private String papername;
    //影响因子
    private String influencefactor;
    //年卷页码（xx年xx卷xx页）
    private String yearnumber;
    //发表时间（xx年xx月xx日）
    private String publishtime;
    //通讯作者
    private String communicationauthor;
    //第一作者
    private String firstauthor;
    //国内作者（排序）
    private String domesticauthor;
    //SCI他引次数
    private String quotenumber;
    //他引总次数
    private String quotetotalnumber;
    //是否国内完成
    private String whetherdomesticcomplete;
    private String whetherdomesticcompleteStr;
    //证明材料编号
    private String materialcode;
    //创建日期
    private Date created;

    /**
     * 获取：
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置：
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取：
     */
    public Integer getProId() {
        return proId;
    }

    /**
     * 设置：
     */
    public void setProId(Integer proId) {
        this.proId = proId;
    }

    /**
     * 获取：
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * 设置：
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    /**
     * 获取：
     */
    public String getApplyId() {
        return applyId;
    }

    /**
     * 设置：
     */
    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    /**
     * 获取：
     */
    public Long getOptUid() {
        return optUid;
    }

    /**
     * 设置：
     */
    public void setOptUid(Long optUid) {
        this.optUid = optUid;
    }

    /**
     * 获取：论文专著名称
     */
    public String getPapername() {
        return papername;
    }

    /**
     * 设置：论文专著名称
     */
    public void setPapername(String papername) {
        this.papername = papername;
    }

    /**
     * 获取：影响因子
     */
    public String getInfluencefactor() {
        return influencefactor;
    }

    /**
     * 设置：影响因子
     */
    public void setInfluencefactor(String influencefactor) {
        this.influencefactor = influencefactor;
    }

    /**
     * 获取：年卷页码（xx年xx卷xx页）
     */
    public String getYearnumber() {
        return yearnumber;
    }

    /**
     * 设置：年卷页码（xx年xx卷xx页）
     */
    public void setYearnumber(String yearnumber) {
        this.yearnumber = yearnumber;
    }

    /**
     * 获取：发表时间（xx年xx月xx日）
     */
    public String getPublishtime() {
        return publishtime;
    }

    /**
     * 设置：发表时间（xx年xx月xx日）
     */
    public void setPublishtime(String publishtime) {
        this.publishtime = publishtime;
    }

    /**
     * 获取：通讯作者
     */
    public String getCommunicationauthor() {
        return communicationauthor;
    }

    /**
     * 设置：通讯作者
     */
    public void setCommunicationauthor(String communicationauthor) {
        this.communicationauthor = communicationauthor;
    }

    /**
     * 获取：第一作者
     */
    public String getFirstauthor() {
        return firstauthor;
    }

    /**
     * 设置：第一作者
     */
    public void setFirstauthor(String firstauthor) {
        this.firstauthor = firstauthor;
    }

    /**
     * 获取：国内作者（排序）
     */
    public String getDomesticauthor() {
        return domesticauthor;
    }

    /**
     * 设置：国内作者（排序）
     */
    public void setDomesticauthor(String domesticauthor) {
        this.domesticauthor = domesticauthor;
    }

    /**
     * 获取：SCI他引次数
     */
    public String getQuotenumber() {
        return quotenumber;
    }

    /**
     * 设置：SCI他引次数
     */
    public void setQuotenumber(String quotenumber) {
        this.quotenumber = quotenumber;
    }

    /**
     * 获取：他引总次数
     */
    public String getQuotetotalnumber() {
        return quotetotalnumber;
    }

    /**
     * 设置：他引总次数
     */
    public void setQuotetotalnumber(String quotetotalnumber) {
        this.quotetotalnumber = quotetotalnumber;
    }

    /**
     * 获取：是否国内完成
     */
    public String getWhetherdomesticcomplete() {
        return whetherdomesticcomplete;
    }

    /**
     * 设置：是否国内完成
     */
    public void setWhetherdomesticcomplete(String whetherdomesticcomplete) {
        this.whetherdomesticcomplete = whetherdomesticcomplete;
        if("1".equals(whetherdomesticcomplete)) {
            this.whetherdomesticcompleteStr = "是";
        }else {
            this.whetherdomesticcompleteStr = "否";
        }
    }

    public String getWhetherdomesticcompleteStr() {
        return whetherdomesticcompleteStr;
    }

    public void setWhetherdomesticcompleteStr(String whetherdomesticcompleteStr) {
        this.whetherdomesticcompleteStr = whetherdomesticcompleteStr;
    }

    /**
     * 获取：证明材料编号
     */
    public String getMaterialcode() {
        return materialcode;
    }

    /**
     * 设置：证明材料编号
     */
    public void setMaterialcode(String materialcode) {
        this.materialcode = materialcode;
    }

    /**
     * 获取：创建日期
     */
    public Date getCreated() {
        return created;
    }

    /**
     * 设置：创建日期
     */
    public void setCreated(Date created) {
        this.created = created;
    }
}
