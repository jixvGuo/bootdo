package com.bootdo.cpe.domain;

import com.bootdo.cpe.petroleum_engineering_award.domain.OilAwardContributionUsersDO;
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
public class PetroleumEngineContributeDocBaseDataWord extends DocBaseDataWord{
    private String projectName;
    private String useName;

    private List<OilAwardContributionUsersDO> opinionDOList;

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

    public List<OilAwardContributionUsersDO> getOpinionDOList() {
        return opinionDOList;
    }

    public void setOpinionDOList(List<OilAwardContributionUsersDO> opinionDOList) {
        this.opinionDOList = opinionDOList;
    }

    public TableRenderData getOpinionTable(){
        List<RowRenderData> rowList = new ArrayList<>();
        TableStyle tableStyle = new TableStyle();
        Style rowStyle = new Style();
        rowStyle.setFontSize(18);
        for(OilAwardContributionUsersDO opinionDO:this.opinionDOList) {
            TextRenderData constructionOpinionsStr = new TextRenderData("建设单位（使用单位）意见：" +
                    "            年  月  日（章）");
            constructionOpinionsStr.setStyle(rowStyle);
            RowRenderData row0 = Rows.of(constructionOpinionsStr).create();
            rowList.add(row0);
        }
        return getTableRenderData(rowList);
    }
}
