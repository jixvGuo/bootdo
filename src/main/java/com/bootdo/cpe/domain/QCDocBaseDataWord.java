package com.bootdo.cpe.domain;

import com.bootdo.cpe.petroleum_engineering_award.domain.*;
import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.data.TableRenderData;
import com.deepoove.poi.data.style.Style;
import com.deepoove.poi.data.style.TableStyle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bootdo.common.utils.PoiWordUtils.getTableRenderData;

/**
 * QC 项目
 */
public class QCDocBaseDataWord extends DocBaseDataWord{
    private String applyUnite;
    private String applyCOde;

    public String getApplyUnite() {
        return applyUnite;
    }

    public void setApplyUnite(String applyUnite) {
        this.applyUnite = applyUnite;
    }

    public String getApplyCOde() {
        return applyCOde;
    }

    public void setApplyCOde(String applyCOde) {
        this.applyCOde = applyCOde;
    }

    private  QcGroupApplyInfoDO applyInfoDO; //  优质申报表
    private  QcReportSolveInfoDO reportSolveInfoDO; //  问题解决型
    private  QcReportInnovateInfoDO reportInnovateInfoDO; //   // 创新型课题
    private  QcAppraiseActiveScoreDO activeScoreDO;  // 活动现场评价
    private  QcResultSolveScoreDO resultSolveScoreDO; //解决成果评价
    private  QcResultInnovateScoreDO resultInnovateScoreDO; // 创新型成果评价
    private  QcSurveyStatisticInfoDO surveyStatisticInfoDO; // 质量管理小组概况统计表


    public QcGroupApplyInfoDO getApplyInfoDO() {
        return applyInfoDO;
    }

    public void setApplyInfoDO(QcGroupApplyInfoDO applyInfoDO) {
        this.applyInfoDO = applyInfoDO;
    }

    public QcReportSolveInfoDO getReportSolveInfoDO() {
        return reportSolveInfoDO;
    }

    public void setReportSolveInfoDO(QcReportSolveInfoDO reportSolveInfoDO) {
        this.reportSolveInfoDO = reportSolveInfoDO;
    }

    public QcReportInnovateInfoDO getReportInnovateInfoDO() {
        return reportInnovateInfoDO;
    }

    public void setReportInnovateInfoDO(QcReportInnovateInfoDO reportInnovateInfoDO) {
        this.reportInnovateInfoDO = reportInnovateInfoDO;
    }

    public QcAppraiseActiveScoreDO getActiveScoreDO() {
        return activeScoreDO;
    }

    public void setActiveScoreDO(QcAppraiseActiveScoreDO activeScoreDO) {
        this.activeScoreDO = activeScoreDO;
    }

    public QcResultSolveScoreDO getResultSolveScoreDO() {
        return resultSolveScoreDO;
    }

    public void setResultSolveScoreDO(QcResultSolveScoreDO resultSolveScoreDO) {
        this.resultSolveScoreDO = resultSolveScoreDO;
    }

    public QcResultInnovateScoreDO getResultInnovateScoreDO() {
        return resultInnovateScoreDO;
    }

    public void setResultInnovateScoreDO(QcResultInnovateScoreDO resultInnovateScoreDO) {
        this.resultInnovateScoreDO = resultInnovateScoreDO;
    }

    public QcSurveyStatisticInfoDO getSurveyStatisticInfoDO() {
        return surveyStatisticInfoDO;
    }

    public void setSurveyStatisticInfoDO(QcSurveyStatisticInfoDO surveyStatisticInfoDO) {
        this.surveyStatisticInfoDO = surveyStatisticInfoDO;
    }
}
