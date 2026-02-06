package com.bootdo.cpe.domain;

/***
 * 专业组 评审打分表 每个项目的得到的分数
 */
public class GroupScoreItem {
    private String showNum;
    private String chengguo;//项目名称
    private String technologyAdvancement;//技术先进成功
    private String degreeInnovation;//创新程度
    private String difficultyComplex;//难易与复杂程度
    private String loreProperty;//知识产权
    private String economicBenefit;//经济效益
    private String socialBenefit;//社会效益
    private String promotionProspect;//推广应用前景
    private String preparationExpression;//材料准备情况、表达能力
    private String total;//总分数
    private String level;//等级



    private String technologyCreate;// 科技创新能力
    private String gainAwards; // 获奖成果
    private String scienceContribution;    // 	科技贡献
    private String intelliRight;   // 知识产权
    private String pubWork;  //发表论著


    public String getTechnologyCreate() {
        return technologyCreate;
    }

    public void setTechnologyCreate(String technologyCreate) {
        this.technologyCreate = technologyCreate;
    }

    public String getGainAwards() {
        return gainAwards;
    }

    public void setGainAwards(String gainAwards) {
        this.gainAwards = gainAwards;
    }

    public String getScienceContribution() {
        return scienceContribution;
    }

    public void setScienceContribution(String scienceContribution) {
        this.scienceContribution = scienceContribution;
    }

    public String getIntelliRight() {
        return intelliRight;
    }

    public void setIntelliRight(String intelliRight) {
        this.intelliRight = intelliRight;
    }

    public String getPubWork() {
        return pubWork;
    }

    public void setPubWork(String pubWork) {
        this.pubWork = pubWork;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    private String create;//时间
    private String sinImageUrl;//时间
    private Object imgObj;
    //项目分组名称
    private String groupName;


    public Object getImgObj() {
        return imgObj;
    }

    public void setImgObj(Object imgObj) {
        this.imgObj = imgObj;
    }

    public String getCreate() {
        return create;
    }

    public void setCreate(String create) {
        this.create = create;
    }

    public String getSinImageUrl() {
        return sinImageUrl;
    }

    public void setSinImageUrl(String sinImageUrl) {
        this.sinImageUrl = sinImageUrl;
    }

    public String getShowNum() {
        return showNum;
    }

    public void setShowNum(String showNum) {
        this.showNum = showNum;
    }

    public String getChengguo() {
        return chengguo;
    }

    public void setChengguo(String chengguo) {
        this.chengguo = chengguo;
    }

    public String getTechnologyAdvancement() {
        return technologyAdvancement;
    }

    public void setTechnologyAdvancement(String technologyAdvancement) {
        this.technologyAdvancement = technologyAdvancement;
    }

    public String getDegreeInnovation() {
        return degreeInnovation;
    }

    public void setDegreeInnovation(String degreeInnovation) {
        this.degreeInnovation = degreeInnovation;
    }

    public String getDifficultyComplex() {
        return difficultyComplex;
    }

    public void setDifficultyComplex(String difficultyComplex) {
        this.difficultyComplex = difficultyComplex;
    }

    public String getLoreProperty() {
        return loreProperty;
    }

    public void setLoreProperty(String loreProperty) {
        this.loreProperty = loreProperty;
    }

    public String getEconomicBenefit() {
        return economicBenefit;
    }

    public void setEconomicBenefit(String economicBenefit) {
        this.economicBenefit = economicBenefit;
    }

    public String getSocialBenefit() {
        return socialBenefit;
    }

    public void setSocialBenefit(String socialBenefit) {
        this.socialBenefit = socialBenefit;
    }

    public String getPromotionProspect() {
        return promotionProspect;
    }

    public void setPromotionProspect(String promotionProspect) {
        this.promotionProspect = promotionProspect;
    }

    public String getPreparationExpression() {
        return preparationExpression;
    }

    public void setPreparationExpression(String preparationExpression) {
        this.preparationExpression = preparationExpression;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

//    <td th:text="${proInfo.chengguo}"></td>
//                <td th:text="${scoreResultMap.get('technology_advancement')}"></td>
//                <td th:text="${scoreResultMap.get('degree_innovation')}"></td>
//                <td th:text="${scoreResultMap.get('difficulty_complex')}"></td>
//                <td th:text="${scoreResultMap.get('lore_property')}"></td>
//                <td th:text="${scoreResultMap.get('economic_benefit')}"></td>
//                <td th:text="${scoreResultMap.get('social_benefit')}"></td>
//                <td th:text="${scoreResultMap.get('promotion_prospect')}"></td>
//                <td th:text="${scoreResultMap.get('preparation_expression')}"></td>
//                <td th:text="${scoreResultMap.get('total')}"></td>


}
