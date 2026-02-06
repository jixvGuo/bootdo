package com.bootdo.cpe.domain;

import com.bootdo.cpe.petroleum_engineering_award.domain.OilProApplyInfoDO;
import com.bootdo.cpe.petroleum_engineering_award.domain.OilProGeneralSituationDO;
import com.bootdo.cpe.petroleum_engineering_award.domain.OilProUnitOpinionDO;
import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.data.Rows;
import com.deepoove.poi.data.TableRenderData;
import com.deepoove.poi.data.TextRenderData;
import com.deepoove.poi.data.style.Style;
import com.deepoove.poi.data.style.TableStyle;

import java.util.ArrayList;
import java.util.List;

import static com.bootdo.common.utils.PoiWordUtils.getTableRenderData;

/**
 * @author houzb
 * @Description
 * @create 2021-03-07 16:13
 */
public class PetroleumEngineDocBaseDataWord extends DocBaseDataWord{

    private OilProApplyInfoDO proApplyInfoDO;
    private OilProGeneralSituationDO situationDO;
    private List<OilProUnitOpinionDO> opinionDOList;

    public OilProApplyInfoDO getProApplyInfoDO() {
        return proApplyInfoDO;
    }

    public void setProApplyInfoDO(OilProApplyInfoDO proApplyInfoDO) {
        this.proApplyInfoDO = proApplyInfoDO;
    }

    public OilProGeneralSituationDO getSituationDO() {
        return situationDO;
    }

    public void setSituationDO(OilProGeneralSituationDO situationDO) {
        this.situationDO = situationDO;
    }

    public List<OilProUnitOpinionDO> getOpinionDOList() {
        return opinionDOList;
    }

    public void setOpinionDOList(List<OilProUnitOpinionDO> opinionDOList) {
        this.opinionDOList = opinionDOList;
    }

    public TableRenderData getOpinionTable(){
        List<RowRenderData> rowList = new ArrayList<>();
        TableStyle tableStyle = new TableStyle();
        Style rowStyle = new Style();
        rowStyle.setFontSize(18);
        for(OilProUnitOpinionDO opinionDO:this.opinionDOList) {
            TextRenderData constructionOpinionsStr = new TextRenderData("建设单位（使用单位）意见：" + opinionDO.getConstructionOpinions() +"\n \n  " +
                    "            年  月  日（章）");
            constructionOpinionsStr.setStyle(rowStyle);
            RowRenderData row0 = Rows.of(constructionOpinionsStr).create();
            rowList.add(row0);
            TextRenderData designUnitOpinionsStr = new TextRenderData("设计单位意见：" + opinionDO.getDesignUnitOpinions());
            designUnitOpinionsStr.setStyle(rowStyle);
            RowRenderData row1 = Rows.of(designUnitOpinionsStr).create();
            rowList.add(row1);
            TextRenderData supervisionOpinionsStr = new TextRenderData("监理单位意见:" + opinionDO.getSupervisionOpinions());
            supervisionOpinionsStr.setStyle(rowStyle);
            RowRenderData row2 = Rows.of(supervisionOpinionsStr).create();
            rowList.add(row2);
        }
        return getTableRenderData(rowList);
    }
}
