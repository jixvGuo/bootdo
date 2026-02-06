package com.bootdo.cpe.utils;

import com.bootdo.cpe.domain.PetroleumEngineAwardDocTableData;
import com.bootdo.cpe.petroleum_engineering_award.domain.EnumOilQualityGetAwardType;
import com.bootdo.cpe.petroleum_engineering_award.domain.OilAwardGetInfoDO;
import com.bootdo.cpe.petroleum_engineering_award.domain.OilAwardPartakeUnitDO;
import com.bootdo.cpe.petroleum_engineering_award.domain.OilAwardUnitInfoDO;
import com.deepoove.poi.data.CellRenderData;
import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.data.Rows;
import com.deepoove.poi.data.TextRenderData;
import com.deepoove.poi.data.style.CellStyle;
import com.deepoove.poi.data.style.RowStyle;
import com.deepoove.poi.policy.DynamicTableRenderPolicy;
import com.deepoove.poi.policy.TableRenderPolicy;
import com.deepoove.poi.util.TableTools;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;

/**
 * 优质工程奖word策略
 *
 * @author houzb
 * @version 1.0
 * @date 2021-03-24 0:04
 */
public class PoiWordOilQualityDetailTablePolicy extends DynamicTableRenderPolicy {

    int awardStartRow = 32;

    @Override
    public void render(XWPFTable table, Object data) {
        if (null == data) return;
        PetroleumEngineAwardDocTableData awardDocTableData = (PetroleumEngineAwardDocTableData)data;

        List<OilAwardGetInfoDO> list = awardDocTableData.getOilAwardGetInfoDOS();//获奖信息

        table.removeRow(awardStartRow );
        // 循环插入行 //获奖信息
        for (int i = 0; i < list.size(); i++) {
            OilAwardGetInfoDO award = list.get(i);
            String awradType = EnumOilQualityGetAwardType.getDescById(award.getTypesAwards());
            for(int r=0;r<3;r++) {
                XWPFTableRow insertNewTableRow = table.insertNewTableRow(awardStartRow);
                for (int j = 0; j < 9; j++){
                    insertNewTableRow.createCell();
                }
            }
            // 合并单元格
            TableTools.mergeCellsHorizonal(table, awardStartRow, 0,3);
            TableTools.mergeCellsHorizonal(table, awardStartRow, 1,5);
            TableTools.mergeCellsHorizonal(table, awardStartRow + 1, 0,3);
            TableTools.mergeCellsHorizonal(table, awardStartRow + 1, 1,5);
            TableTools.mergeCellsHorizonal(table, awardStartRow + 2, 0,3);
            TableTools.mergeCellsHorizonal(table, awardStartRow + 2, 1,5);
            TableTools.mergeCellsVertically(table, 0, awardStartRow, awardStartRow +2);
            // 单行渲染
            RowRenderData awardNameRow = Rows.of(awradType, "获奖名称："+award.getNameAward()).textFontFamily("仿宋_GB2312").textFontSize(14).create();
            RowRenderData awardUnitRow = Rows.of("", "颁发单位："+award.getIssuingUnit()).textFontFamily("仿宋_GB2312").textFontSize(14).create();
            RowRenderData awardTimeRow = Rows.of("","颁发时间："+award.getTimeIssue()).textFontFamily("仿宋_GB2312").textFontSize(14).create();
            try {
                TableRenderPolicy.Helper.renderRow(table.getRow(awardStartRow), awardNameRow);
                TableRenderPolicy.Helper.renderRow(table.getRow(awardStartRow +1), awardUnitRow);
                TableRenderPolicy.Helper.renderRow(table.getRow(awardStartRow +2), awardTimeRow);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
