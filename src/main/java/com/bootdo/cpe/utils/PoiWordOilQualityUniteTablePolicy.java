package com.bootdo.cpe.utils;

import com.bootdo.cpe.domain.PetroleumEngineAwardDocTableData;
import com.bootdo.cpe.petroleum_engineering_award.domain.*;
import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.data.Rows;
import com.deepoove.poi.policy.DynamicTableRenderPolicy;
import com.deepoove.poi.policy.TableRenderPolicy;
import com.deepoove.poi.util.TableTools;
import org.apache.poi.xwpf.usermodel.TableWidthType;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STVerticalJc;

import java.util.List;

/**
 * 优质工程奖word策略
 *  参建单位
 */
public class PoiWordOilQualityUniteTablePolicy extends DynamicTableRenderPolicy {

    int awardStartRow = 32 + 3 + 4 + 2;

    @Override
    public void render(XWPFTable table, Object data) throws Exception {
        if (null == data)
            return;
        PetroleumEngineAwardDocTableData awardDocTableData = (PetroleumEngineAwardDocTableData)data;
        List<OilAwardPartakeUnitDO> awardPartakeUnitDOS = awardDocTableData.getOilAwardPartakeUnitDOS();//获奖信息
        List<OilAwardGetInfoDO> list = awardDocTableData.getOilAwardGetInfoDOS();
        List<OilAwardUnitInfoDO> oilAwardUnitInfoDOS = awardDocTableData.getOilAwardUnitInfoDOList();

        awardStartRow = awardStartRow +   list.size()   + oilAwardUnitInfoDOS.size() ;


        table.removeRow(awardStartRow);


        for (int i = 0; i < awardPartakeUnitDOS.size(); i++) {
            OilAwardPartakeUnitDO award = awardPartakeUnitDOS.get(i);
            table.setWidthType(TableWidthType.DXA);
                XWPFTableRow insertNewTableRow = table.insertNewTableRow(awardStartRow);
                for (int j = 0; j < 9; j++){
                    insertNewTableRow.createCell();
                 }

            // 合并单元格
            TableTools.mergeCellsHorizonal(table, awardStartRow, 0,2);
            TableTools.mergeCellsHorizonal(table, awardStartRow, 2,3);
            TableTools.mergeCellsHorizonal(table, awardStartRow, 4,5);

            RowRenderData awardNameRow = Rows.of(
                    award.getUnitOutputValue(),
                    award.getMailingAddressPostalCode(),
                    award.getContactPersonTelNumber(),
                    award.getNamePositionNumberProject(),""
                    ). textFontFamily("仿宋_GB2312").textFontSize(14).
                    create();
            try {
                TableRenderPolicy.Helper.renderRow(table.getRow(awardStartRow), awardNameRow);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
