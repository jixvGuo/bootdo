package com.bootdo.cpe.utils;

import com.bootdo.cpe.petroleum_engineering_award.domain.*;
import com.deepoove.poi.data.CellRenderData;
import com.deepoove.poi.data.Cells;
import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.data.Rows;
import com.deepoove.poi.data.style.CellStyle;
import com.deepoove.poi.policy.DynamicTableRenderPolicy;
import com.deepoove.poi.policy.TableRenderPolicy;
import com.deepoove.poi.util.TableTools;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;

/**
 * 安装工程表格详细
 *
 * @author houzb
 * @version 1.0
 * @date 2021-03-25 23:22
 */
public class PoiWordOilInstallAwardDetailTablePolicy  extends DynamicTableRenderPolicy {
    int awardStartRow = 11;
    @Override
    public void render(XWPFTable table, Object data) throws Exception {
        if (null == data) return;
        OilProGeneralSituationDO situationDO = (OilProGeneralSituationDO) data;
        table.removeRow(awardStartRow);
        //设计奖项
        List<OilProDesignAwardDO> designAwardList = situationDO.getDesignAwardList();
        //工程奖项
        List<OilProEngineeAwardDO> engineeAwardList = situationDO.getEngineeAwardList();

        for (int i = 0; i < engineeAwardList.size(); i++) {
            OilProEngineeAwardDO award = engineeAwardList.get(i);
            for(int r=0;r<2;r++) {
                XWPFTableRow insertNewTableRow = table.insertNewTableRow(awardStartRow);
                for (int j = 0; j < 6; j++){
                    insertNewTableRow.createCell();
                }
            }

            // 合并单元格
            TableTools.mergeCellsVertically(table, 0, awardStartRow, awardStartRow + 1);
            TableTools.mergeCellsHorizonal(table, awardStartRow, 2, 3);
            TableTools.mergeCellsHorizonal(table, awardStartRow +1, 2, 5);
            // 单行渲染          ;
            RowRenderData awardNameRow = Rows.of("工程获奖", "获奖名称", award.getEngineeNameAward(),"获奖时间", award.getAwardTime()).textFontFamily("Arial Unicode MS").textFontSize(12).create();
            RowRenderData awardUnitRow = Rows.of("", "颁奖单位", award.getEngineeAwardingUnit()).textFontFamily("Arial Unicode MS").textFontSize(12).create();
            try {
                TableRenderPolicy.Helper.renderRow(table.getRow(awardStartRow), awardNameRow);
                TableRenderPolicy.Helper.renderRow(table.getRow(awardStartRow +1), awardUnitRow);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < designAwardList.size(); i++) {
            OilProDesignAwardDO award = designAwardList.get(i);
            for(int r=0;r<2;r++) {
                XWPFTableRow insertNewTableRow = table.insertNewTableRow(awardStartRow);
                for (int j = 0; j < 6; j++){
                    insertNewTableRow.createCell();
                }
            }
            // 合并单元格
            TableTools.mergeCellsVertically(table, 0, awardStartRow, awardStartRow + 1);
            TableTools.mergeCellsHorizonal(table, awardStartRow, 2, 3);
            TableTools.mergeCellsHorizonal(table, awardStartRow +1, 2, 5);
            // 单行渲染
            RowRenderData awardNameRow = Rows.of("设计获奖", "获奖名称", award.getNameAward(),"获奖时间",
                    award.getAwardTimeStr()).textFontFamily("Arial Unicode MS").textFontSize(12).create();
            RowRenderData awardUnitRow = Rows.of("", "颁奖单位", award.getAwardingUnit()).textFontFamily("Arial Unicode MS").textFontSize(12).create();
            try {
                TableRenderPolicy.Helper.renderRow(table.getRow(awardStartRow), awardNameRow);
                TableRenderPolicy.Helper.renderRow(table.getRow(awardStartRow +1), awardUnitRow);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
