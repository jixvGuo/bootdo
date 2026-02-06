package com.bootdo.activiti.domain;

import com.bootdo.common.utils.StringUtils;
import com.bootdo.cpe.domain.EnumScienceScoreType;
import com.bootdo.cpe.utils.NumberUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 项目打分信息
 */
public class ProjectScoreInfo {
    private long id;
    private long proId;
    private String proName;
    private String proType;
    private Integer itemId;
    private double scienceScore;
    private double teamScore;
    private double personalScore;
    private long scoreUid;
    private String created;
    private String updatedDate;
    private String proCode;
    private String validateResult;


    public String getValidateResult() {
        return validateResult;
    }

    public void setValidateResult(String validateResult) {
        this.validateResult = validateResult;
    }

    public String getProCode() {
        return proCode;
    }

    public void setProCode(String proCode) {
        this.proCode = proCode;
    }

    //当前打分上传的参数
    private Map<String,Double> scoreDetailMap;
    private String scoreType;
    private String scoreUserName;
    private String major;
    private double score;
    //总分数
    private double totalScore;
    //意见说明
    private String opinionText;
    //意见等级
    private String opinionLevel;
    //是否提交了评分 0提交，1已提交
    private int scoreOver;

    private Object imageObj;

    public Object getImageObj() {
        return imageObj;
    }

    public void setImageObj(Object imageObj) {
        this.imageObj = imageObj;
    }

    private String chengguo;
    private String personName;
    private String teamName;
    private String enterpriseName;
    //签名地址
    private String signUrl;
    //分组
    private String proGroupName;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProId() {
        return proId;
    }

    public void setProId(long proId) {
        this.proId = proId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProType() {
        return proType;
    }

    public void setProType(String proType) {
        this.proType = proType;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public double getScienceScore() {
        return scienceScore;
    }

    public void setScienceScore(double scienceScore) {
        this.scienceScore = scienceScore;
    }

    public double getTeamScore() {
        return teamScore;
    }

    public void setTeamScore(double teamScore) {
        this.teamScore = teamScore;
    }

    public double getPersonalScore() {
        return personalScore;
    }

    public void setPersonalScore(double personalScore) {
        this.personalScore = personalScore;
    }

    public long getScoreUid() {
        return scoreUid;
    }

    public void setScoreUid(long scoreUid) {
        this.scoreUid = scoreUid;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Map<String, Double> getScoreDetailMap() {
        return scoreDetailMap;
    }

    public void setScoreDetailMap(Map<String, Double> scoreDetailMap) {
        this.scoreDetailMap = scoreDetailMap;
    }

    public String getScoreType() {
        return scoreType;
    }

    public void setScoreType(String scoreType) {
        this.scoreType = scoreType;
    }

    public String getScoreUserName() {
        return scoreUserName;
    }

    public void setScoreUserName(String scoreUserName) {
        this.scoreUserName = scoreUserName;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(double totalScore) {
        this.totalScore = totalScore;
    }

    public String getOpinionText() {
        return opinionText;
    }

    public void setOpinionText(String opinionText) {
        if(StringUtils.isNotBlank(opinionText) && opinionText.equals("0.0")) {
            opinionText = "";
        }
        this.opinionText = opinionText;
    }

    public String getOpinionLevel() {
        return opinionLevel;
    }

    public void setOpinionLevel(String opinionLevel) {
        if(StringUtils.isNotBlank(opinionLevel) && opinionLevel.equals("0.0")) {
            opinionLevel = "";
        }
        this.opinionLevel = opinionLevel;
    }

    public int getScoreOver() {
        return scoreOver;
    }

    public void setScoreOver(int scoreOver) {
        this.scoreOver = scoreOver;
    }

    public String getChengguo() {
        return chengguo;
    }

    public void setChengguo(String chengguo) {
        this.chengguo = chengguo;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getSignUrl() {
        return signUrl;
    }

    public void setSignUrl(String signUrl) {
        this.signUrl = signUrl;
    }

    public String getProGroupName() {
        return proGroupName;
    }

    public void setProGroupName(String proGroupName) {
        this.proGroupName = proGroupName;
    }

    public void handleParams(HttpServletRequest request) {
        this.scoreDetailMap = new HashMap<>();
        Enumeration<String> paramNames = request.getParameterNames();
        String scoreType = "";
        double total = 0;
        while (paramNames.hasMoreElements()) {
            String pName = paramNames.nextElement();
            String val = request.getParameter(pName);


            if("score_type".equals(pName)) {
                scoreType = val;
                continue;
            }
            if(!NumberUtils.isNumber(val) || "proId".equals(pName)) {
               continue;
            }
            double v = StringUtils.isNotBlank(val) ? Double.parseDouble(val.trim()) : 0;
            if("total".equals(pName) || "itemId".equals(pName) ||   "totalScore".equals(pName)) {
                continue;
            }
            total+=v;
            this.scoreDetailMap.put(pName,v);
        }
        this.scoreDetailMap.put("total", total);
        this.score = total;

        if(EnumScienceScoreType.SCIENCE.getScoreType().equals(scoreType)) {
            this.scienceScore = total;
        }else if(EnumScienceScoreType.TEAM.getScoreType().equals(scoreType)) {
            this.teamScore = total;
        }else if(EnumScienceScoreType.PERSONAL.getScoreType().equals(scoreType)) {
            this.personalScore = total;
        }
        this.scoreType = scoreType;
    }
}
