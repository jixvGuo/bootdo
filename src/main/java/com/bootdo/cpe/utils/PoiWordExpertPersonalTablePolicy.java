package com.bootdo.cpe.utils;

import com.bootdo.cpe.domain.GroupScoreItem;
import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.data.Rows;
import com.deepoove.poi.policy.DynamicTableRenderPolicy;
import com.deepoove.poi.policy.TableRenderPolicy;
import org.apache.poi.xwpf.usermodel.*;

import java.util.List;

public class PoiWordExpertPersonalTablePolicy extends DynamicTableRenderPolicy {


    int awardStartRow = 3;

    @Override
    public void render(XWPFTable table, Object data) throws Exception {

        if (null == data) return;
        List<GroupScoreItem>  situationDO = ( List<GroupScoreItem> ) data;
        table.removeRow(awardStartRow);
        for (int i = 0; i < situationDO.size(); i++) {
            GroupScoreItem award = situationDO.get(i);
                XWPFTableRow insertNewTableRow = table.insertNewTableRow(awardStartRow);
                for (int j = 0; j < 8; j++) {
                    XWPFTableCell cell = insertNewTableRow.createCell();
                    XWPFParagraph paragraph = cell.getParagraphs().get(0); // 获取段落对象
                    paragraph.setAlignment(ParagraphAlignment.CENTER); // 居中对齐
                    XWPFRun run = paragraph.createRun();
                    run.setFontFamily("宋体"); // 设置字体为宋体
                    run.setFontSize(11); // 设置字号为11号
                }

            RowRenderData awardUnitRow = Rows.of(
                    award.getShowNum(),
                    award.getChengguo(),
                    award.getTechnologyCreate(),
                    award.getGainAwards(),
                    award.getScienceContribution(),
                    award.getIntelliRight(),
                    award.getPubWork(),
                    award.getTotal()
                     ).textFontFamily("Arial Unicode MS").textFontSize(12).create();

            TableRenderPolicy.Helper.renderRow(table.getRow(awardStartRow), awardUnitRow);


        }


    }
}
