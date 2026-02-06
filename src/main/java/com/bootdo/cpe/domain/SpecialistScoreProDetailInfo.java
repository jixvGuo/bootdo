package com.bootdo.cpe.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author houzb
 * @Description
 * @create 2020-10-11 23:46
 */
public class SpecialistScoreProDetailInfo {
    private int proId;
    private String proName;
    private Map<String,Double> scoreDetailMap;
    private double totalScore;

    public SpecialistScoreProDetailInfo(List<SpecialistScoreDetailInfo> detailInfoList) {
        if(detailInfoList.size() > 0) {
            this.proName = detailInfoList.get(0).getProName();
            this.proId = detailInfoList.get(0).getProId();
            this.scoreDetailMap = new HashMap<>();
            detailInfoList.stream().forEach(d->{
                double score = d.getScoreVal();

                this.scoreDetailMap.put(d.getScoreKey(),score);
                System.out.println("AAAAA   " + d.getScoreKey());
                if(!"total".equalsIgnoreCase(d.getScoreKey())){
                    this.totalScore+=score;
                }

            });
        }
    }

    public int getProId() {
        return proId;
    }

    public void setProId(int proId) {
        this.proId = proId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public Map<String, Double> getScoreDetailMap() {
        return scoreDetailMap;
    }

    public void setScoreDetailMap(Map<String, Double> scoreDetailMap) {
        this.scoreDetailMap = scoreDetailMap;
    }

    public double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(double totalScore) {
        this.totalScore = totalScore;
    }
}
