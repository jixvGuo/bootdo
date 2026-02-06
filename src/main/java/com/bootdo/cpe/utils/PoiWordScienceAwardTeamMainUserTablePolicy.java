package com.bootdo.cpe.utils;

import com.bootdo.cpe.domain.EnterpriTeamCooperationDO;
import com.bootdo.cpe.domain.EnterpriTeamLeaderInfoDO;
import com.bootdo.cpe.domain.EnterpriTeamMainUserPoiData;
import com.bootdo.cpe.domain.EnterpriTeamOtherMainDO;
import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.data.Rows;
import com.deepoove.poi.policy.DynamicTableRenderPolicy;
import com.deepoove.poi.policy.TableRenderPolicy;
import com.deepoove.poi.util.TableTools;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.bootdo.common.utils.PoiWordUtils.getTeamCooperationTypeRows;

/**
 * 科技奖团队主要人员其他人员动态表
 *
 * @author houzb
 * @version 1.0
 * @date 2021-07-26 23:46
 */
public class PoiWordScienceAwardTeamMainUserTablePolicy extends DynamicTableRenderPolicy {
    int awardStartRow = 10;

    @Override
    public void render(XWPFTable table, Object data) throws Exception {
        if (null == data) return;
        EnterpriTeamMainUserPoiData mainUserPoiData = (EnterpriTeamMainUserPoiData) data;

        List<EnterpriTeamLeaderInfoDO> mainUserList = mainUserPoiData.getTeamMainUers();
        List<RowRenderData> mainUserRowList = new ArrayList<>();
        RowRenderData headRow = Rows.of("带头人", "序号","姓名","性别","专业技术职务","","所在单位","","研究领域","团队工作时间（年）")
                .textFontSize(11)
                .textFontFamily("宋体")
                .bgColor("FFFFFF").center().create();
        mainUserRowList.add(headRow);
        if (mainUserList != null){//带头人
            int size = mainUserList.size();
            for (int a = 0; a < size; a++) {
                EnterpriTeamLeaderInfoDO award = mainUserList.get(a);
                int num = a+1;
                mainUserRowList.add(Rows.of(
                        "",
                        num + "",
                        award.getName(),
                        award.getGender() + "",
                        award.getTechnologytitle(),
                        "",
                        award.getWorkcompany(),
                        "",
                        award.getMajor(),
                        award.getGetWorkYear()).textFontFamily("宋体").textFontSize(11).create()
                );
            }
        }
        //标志性成果
        rendTypeData(table, mainUserRowList, awardStartRow);
        awardStartRow += mainUserRowList.size();

        List<EnterpriTeamOtherMainDO> oteamUers = mainUserPoiData.getTeamOtherUers();
        List<RowRenderData> otherUserRowList = new ArrayList<>();
        if (oteamUers != null){ //其他主要成员
            for (int a = 0; a < oteamUers.size(); a++) {
                EnterpriTeamOtherMainDO award = oteamUers.get(a);
                int num = a+1;
                otherUserRowList.add(Rows.of(
                        a == 0 ? "其他主要成员" : "",
                        num + "",
                        award.getName(),
                        award.getGender() + "",
                        award.getTechnologytitle(),
                        "",
                        award.getWorkcompany(),
                        "",
                        award.getMajor(),
                        award.getWorkYear()).textFontFamily("宋体").textFontSize(11).create()
                );
            }
        }

        //发表论文专著情况
        rendTypeData(table, otherUserRowList, awardStartRow);
    }

    private void rendTypeData(XWPFTable table, List<RowRenderData> dataRows, int awardStartRow) {
        int rowSize = dataRows.size();
        for(int r=0;r<rowSize;r++) {
            XWPFTableRow insertNewTableRow = table.insertNewTableRow(awardStartRow);
            for (int j = 0; j < 10; j++) {
                insertNewTableRow.createCell();
            }
        }
        if(rowSize > 1) {
            // 合并单元格
            TableTools.mergeCellsVertically(table, 0, awardStartRow, awardStartRow + rowSize - 1);
        }
        for (int i = 0; i < rowSize; i++) {
            int rowNum = awardStartRow + i;
            //渲染
            RowRenderData awardNameRow = dataRows.get(i);
            try {
                TableRenderPolicy.Helper.renderRow(table.getRow(rowNum), awardNameRow);
            } catch (Exception e) {
                e.printStackTrace();
            }
            TableTools.mergeCellsHorizonal(table,  rowNum, 6, 7);
            TableTools.mergeCellsHorizonal(table,  rowNum, 4, 5);
        }

    }
}
