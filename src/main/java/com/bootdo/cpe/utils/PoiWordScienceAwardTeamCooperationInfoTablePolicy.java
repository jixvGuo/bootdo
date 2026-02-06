package com.bootdo.cpe.utils;

import com.bootdo.common.utils.PoiWordUtils;
import com.bootdo.cpe.domain.EnterpriTeamCooperationDO;
import com.bootdo.cpe.petroleum_engineering_award.domain.OilProEngineeAwardDO;
import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.data.Rows;
import com.deepoove.poi.policy.DynamicTableRenderPolicy;
import com.deepoove.poi.policy.TableRenderPolicy;
import com.deepoove.poi.util.TableTools;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;
import java.util.Map;

import static com.bootdo.common.utils.PoiWordUtils.getTeamCooperationTypeRows;

/**
 * 科技奖团队合作情况汇总表
 *
 * @author houzb
 * @version 1.0
 * @date 2021-07-12 23:46
 */
public class PoiWordScienceAwardTeamCooperationInfoTablePolicy extends DynamicTableRenderPolicy {
    int awardStartRow = 1;

    @Override
    public void render(XWPFTable table, Object data) throws Exception {
        if (null == data) return;
        Map<String, List<EnterpriTeamCooperationDO>> cooperationMap = (Map<String, List<EnterpriTeamCooperationDO>>) data;

        //标志性成果
        String type = "标志性成果";
        List<RowRenderData> markTypeRows = getTeamCooperationTypeRows(type, cooperationMap.get(type));
        rendTypeData(table, markTypeRows, awardStartRow);
        awardStartRow += markTypeRows.size();

        //发表论文专著情况
        String typePager = "发表论文专著情况";
        List<RowRenderData> pagerTypeRows = getTeamCooperationTypeRows(typePager, cooperationMap.get(typePager));
        rendTypeData(table, pagerTypeRows, awardStartRow);
        awardStartRow += pagerTypeRows.size();

        //所获知识产权和技术标准情况
        String knowledgeType = "所获知识产权和技术标准情况";
        List<RowRenderData> knowledgeTypeRows = getTeamCooperationTypeRows(knowledgeType, cooperationMap.get(knowledgeType));
        rendTypeData(table, knowledgeTypeRows, awardStartRow);
        awardStartRow += knowledgeTypeRows.size();

        //团队承担项目及科研经费情况
        String studyType = "团队承担项目及科研经费情况";
        List<RowRenderData> studyTypeRows = getTeamCooperationTypeRows(studyType, cooperationMap.get(studyType));
        rendTypeData(table, studyTypeRows, awardStartRow);
        awardStartRow += studyTypeRows.size();

        //团队曾获科技奖励情况
        String scienceType = "团队曾获科技奖励情况";
        List<RowRenderData> scienceTypeRows = getTeamCooperationTypeRows(scienceType, cooperationMap.get(scienceType));
        rendTypeData(table, scienceTypeRows, awardStartRow);
        awardStartRow += scienceTypeRows.size();

    }

    private void rendTypeData(XWPFTable table, List<RowRenderData> dataRows, int awardStartRow) {
        int rowSize = dataRows.size();
        for(int r=0;r<rowSize;r++) {
            XWPFTableRow insertNewTableRow = table.insertNewTableRow(awardStartRow);
            for (int j = 0; j < 4; j++) {
                insertNewTableRow.createCell();
            }
        }
        if(rowSize > 1) {
            // 合并单元格
            TableTools.mergeCellsVertically(table, 0, awardStartRow, awardStartRow + rowSize - 1);
        }
        for (int i = 0; i < rowSize; i++) {
            //渲染
            RowRenderData awardNameRow = dataRows.get(i);
            try {
                TableRenderPolicy.Helper.renderRow(table.getRow(awardStartRow + i), awardNameRow);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
