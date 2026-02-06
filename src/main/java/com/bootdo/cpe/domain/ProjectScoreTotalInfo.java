package com.bootdo.cpe.domain;

import com.bootdo.activiti.domain.ProjectScoreInfo;
import com.bootdo.cpe.utils.NumberUtils;

import java.util.*;

/**
 * @author houzb
 * @Description
 * @create 2020-10-11 1:29
 */
public class ProjectScoreTotalInfo {
    //项目id
    private long proId;
    private String name;
    private String enterpriseName;
    private double avgScore;
    private String proCode;
    private String checkRes;

    public String getProCode() {
        return proCode;
    }

    public String getCheckRes() {

        return checkRes;
    }

    public void setCheckRes(String checkRes) {

        this.checkRes = checkRes;
    }

    public void setProCode(String proCode) {
        this.proCode = proCode;
    }

    private Map<String,Double> scoreMap;
    private Map<String,Double> scoreUidMap;
    private List<Double> scoreList;

    public void addScoreInfo(ProjectScoreInfo scoreInfo){


        if(this.scoreMap == null) {
            this.scoreMap = new HashMap<>();
        }
        if(this.scoreUidMap == null) {
            this.scoreUidMap = new HashMap<>();
        }
        this.scoreMap.put(scoreInfo.getScoreUserName(),scoreInfo.getTotalScore());
        this.scoreUidMap.put(scoreInfo.getScoreUid() + "", scoreInfo.getTotalScore());
    }

    public void initScoreList(Set<String> specialistList) {
        this.scoreList = new ArrayList<>();
        for(String s:specialistList) {
            Double score = this.scoreMap.get(s);
            this.scoreList.add(score == null ? 0 : score);
        }
        List<Double> scoreTmpList = new ArrayList<>();
        scoreTmpList.addAll(this.scoreList);
        this.avgScore = NumberUtils.getAvg(scoreTmpList);
    }

    public long getProId() {
        return proId;
    }

    public void setProId(long proId) {
        this.proId = proId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public double getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(double avgScore) {
        this.avgScore = avgScore;
    }

    public Map<String, Double> getScoreMap() {
        return scoreMap;
    }

    public void setScoreMap(Map<String, Double> scoreMap) {
        this.scoreMap = scoreMap;
    }

    public List<Double> getScoreList() {
        return scoreList;
    }

    public void setScoreList(List<Double> scoreList) {
        this.scoreList = scoreList;
    }

    public Map<String, Double> getScoreUidMap() {
        return scoreUidMap;
    }

    public void setScoreUidMap(Map<String, Double> scoreUidMap) {
        this.scoreUidMap = scoreUidMap;
    }
}
