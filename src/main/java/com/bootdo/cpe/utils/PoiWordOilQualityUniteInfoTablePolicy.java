package com.bootdo.cpe.utils;

import com.bootdo.cpe.domain.PetroleumEngineAwardDocTableData;
import com.bootdo.cpe.petroleum_engineering_award.domain.*;
import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.data.Rows;
import com.deepoove.poi.policy.DynamicTableRenderPolicy;
import com.deepoove.poi.policy.TableRenderPolicy;
import com.deepoove.poi.util.TableTools;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;

/**
 * 单位信息
 */
public class PoiWordOilQualityUniteInfoTablePolicy extends DynamicTableRenderPolicy {

    int awardStartRow = 32 + 3 + 4 ;

    @Override
    public void render(XWPFTable table, Object data) {
        if (null == data) return;
        PetroleumEngineAwardDocTableData awardDocTableData = (PetroleumEngineAwardDocTableData)data;


        List<OilAwardUnitInfoDO> awardPartakeUnitDOS = awardDocTableData.getOilAwardUnitInfoDOList();//获奖信息

        List<OilAwardGetInfoDO> list = awardDocTableData.getOilAwardGetInfoDOS();//获奖信息

        awardStartRow = awardStartRow + list.size() ;

        table.removeRow(awardStartRow);

        // 循环插入行 //获奖信息
        for (int i = 0; i < awardPartakeUnitDOS.size(); i++) {
            OilAwardUnitInfoDO award = awardPartakeUnitDOS.get(i);
            String awradType = EnumOilQualityGetUniteType.getDescById(award.getUnitType());
            for(int r=0;r<1;r++) {
                XWPFTableRow insertNewTableRow = table.insertNewTableRow(awardStartRow);
                for (int j = 0; j < 9; j++){
                    insertNewTableRow.createCell();
                }
            }

            // 合并单元格
            TableTools.mergeCellsHorizonal(table, awardStartRow, 1,2);
            TableTools.mergeCellsHorizonal(table, awardStartRow, 3,4);
            TableTools.mergeCellsHorizonal(table, awardStartRow, 5,6);

            // 单行渲染

            RowRenderData awardNameRow = Rows.of
                    (awradType, award.getFullUnitName(),
                            award.getMailingAddressPostalCode(),
                            award.getContactPersonTelNumber(),
                            award.getNamePositionNumberProject(),
                            "").textFontFamily("仿宋_GB2312").textFontSize(14).create();

            try {
                TableRenderPolicy.Helper.renderRow(table.getRow(awardStartRow), awardNameRow);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
