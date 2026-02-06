package com.bootdo.cpe.domain;

import com.bootdo.cpe.petroleum_engineering_award.domain.*;
import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.data.TableRenderData;
import com.deepoove.poi.data.TextRenderData;
import com.deepoove.poi.data.style.Style;
import com.deepoove.poi.data.style.TableStyle;

import java.util.ArrayList;
import java.util.List;

import static com.bootdo.common.utils.PoiWordUtils.getTableRenderData;

/**
 *
 */
public class PetroleumEngineAwardDocBaseDataWord extends DocBaseDataWord{
    private String projectName;
    private String useName;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getUseName() {
        return useName;
    }

    public void setUseName(String useName) {
        this.useName = useName;
    }

    private OilAwardApplyInfoDO oilAwardApplyInfoDO;//申报信息表(ass_oil_award_apply_info):


    private List<OilAwardGetInfoDO> oilAwardGetInfoDOS;//获奖信息

    private List<OilAwardUnitInfoDO> oilAwardUnitInfoDOList;//单位信息

    private List<OilAwardPartakeUnitDO> oilAwardPartakeUnitDOS;//参见单位表


    private List<OilAwardContributionUsersDO> contributionUsersDOS;//贡献者名单


    private PetroleumEngineAwardDocTableData tableData;

    public PetroleumEngineAwardDocTableData getTableData() {
        return tableData;
    }

    public void setTableData(PetroleumEngineAwardDocTableData tableData) {
        this.tableData = tableData;
    }

    public List<OilAwardPartakeUnitDO> getOilAwardPartakeUnitDOS() {
        return oilAwardPartakeUnitDOS;
    }

    public void setOilAwardPartakeUnitDOS(List<OilAwardPartakeUnitDO> oilAwardPartakeUnitDOS) {
        this.oilAwardPartakeUnitDOS = oilAwardPartakeUnitDOS;
    }

    public List<OilAwardContributionUsersDO> getContributionUsersDOS() {
        return contributionUsersDOS;
    }

    public void setContributionUsersDOS(List<OilAwardContributionUsersDO> contributionUsersDOS) {
        this.contributionUsersDOS = contributionUsersDOS;
    }

    public List<OilAwardUnitInfoDO> getOilAwardUnitInfoDOList() {
        return oilAwardUnitInfoDOList;
    }

    public void setOilAwardUnitInfoDOList(List<OilAwardUnitInfoDO> oilAwardUnitInfoDOList) {
        this.oilAwardUnitInfoDOList = oilAwardUnitInfoDOList;
    }

    public OilAwardApplyInfoDO getOilAwardApplyInfoDO() {
        return oilAwardApplyInfoDO;
    }

    public void setOilAwardApplyInfoDO(OilAwardApplyInfoDO oilAwardApplyInfoDO) {
        this.oilAwardApplyInfoDO = oilAwardApplyInfoDO;
    }


    public List<OilAwardGetInfoDO> getOilAwardGetInfoDOS() {
        return oilAwardGetInfoDOS;
    }

    public void setOilAwardGetInfoDOS(List<OilAwardGetInfoDO> oilAwardGetInfoDOS) {
        this.oilAwardGetInfoDOS = oilAwardGetInfoDOS;
    }

    public TableRenderData getOpinionTable(){
        List<RowRenderData> rowList = new ArrayList<>();
        TableStyle tableStyle = new TableStyle();
        Style rowStyle = new Style();
        rowStyle.setFontSize(18);
        for(OilAwardUnitInfoDO opinionDO:this.oilAwardUnitInfoDOList) {
//            TextRenderData constructionOpinionsStr = new TextRenderData("建设单位（使用单位）意见：" + opinionDO.getConstructionOpinions() +"\n \n  " +
//                    "            年  月  日（章）");
//            constructionOpinionsStr.setStyle(rowStyle);
//            RowRenderData row0 = RowRenderData.build(constructionOpinionsStr);
//            rowList.add(row0);
//            TextRenderData designUnitOpinionsStr = new TextRenderData("设计单位意见：" + opinionDO.getDesignUnitOpinions());
//            designUnitOpinionsStr.setStyle(rowStyle);
//            RowRenderData row1 = RowRenderData.build(designUnitOpinionsStr);
//            rowList.add(row1);
//            TextRenderData supervisionOpinionsStr = new TextRenderData("监理单位意见:" + opinionDO.getSupervisionOpinions());
//            supervisionOpinionsStr.setStyle(rowStyle);
//            RowRenderData row2 = RowRenderData.build(supervisionOpinionsStr);
//            rowList.add(row2);
        }
        return getTableRenderData(rowList);
    }
}
