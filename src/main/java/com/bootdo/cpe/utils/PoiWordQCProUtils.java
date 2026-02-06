package com.bootdo.cpe.utils;

import com.bootdo.cpe.domain.*;
import com.bootdo.cpe.petroleum_engineering_award.domain.OilAwardContributionUsersDO;
import com.bootdo.cpe.petroleum_engineering_award.domain.QCImageObj;
import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.data.*;
import com.deepoove.poi.data.style.Style;
import com.deepoove.poi.policy.ListRenderPolicy;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author houzb
 * @Description
 * @create 2020-09-07 16:37
 */
public class PoiWordQCProUtils {
    private static Logger logger = LoggerFactory.getLogger(PoiWordQCProUtils.class);
    public static void createWorld(String templatePath, String createWordPath, Map<String, Object> params) throws IOException {
        XWPFTemplate template = XWPFTemplate.compile(templatePath).render(params);
        FileOutputStream out = new FileOutputStream(createWordPath);
        template.write(out);
        out.flush();
        out.close();
    }

//    List<String> teamLandmarkTitle = new ArrayList<>();
//        teamLandmarkTitle.add("序号");
//        teamLandmarkTitle.add("成果名称");
//        teamLandmarkTitle.add("团队主要成员中的完成人/排名");
//        teamLandmarkTitle.add("研发起止日期");
//        teamLandmarkTitle.add("正式应用（公开发表）日期");
//        teamLandmarkTitle.add("依托计划名称和编号（不超过3项）");
//        teamLandmarkTitle.add("证明材料编号");
//
//    RowRenderData teamLandmarkTitleHead = Rows.of(getRowHederList(teamLandmarkTitle)).create();
//
//
//    List<RowRenderData> teamLandRows = new ArrayList<>();
//        teamLandRows.add(teamLandmarkTitleHead);
//        if (teamLandmarkList != null){
//        for (int a = 0; a < teamLandmarkList.size(); a++) {
//            EnterpriTeamAcheievementsDO award = teamLandmarkList.get(a);
//            int num = a + 1;
//            teamLandRows.add(Rows.of(
//                            num + "",
//                            award.getAchName(),
//                            award.getAchUsers(),
//                            award.getAchDateStart() + "~" + award.getAchDateEnd(),
//                            award.getAchUseDate(),
//                            award.getAchPlan(),
//                            award.getAchEvidenceCode()
//                    ).textColor("333333").textFontSize(10)
//                    .textFontFamily("宋体")
//                    .bgColor("FFFFFF").center().create());
//        }
//        datas.put("teamLandmarkTable",getTableRenderData(teamLandRows));
//
//    }

    /**
     * QC工程 / 优质工程
     * @param templatePath
     * @param createWordPath
     * @param params
     * @throws IOException
     */
    public static void createProQCWord(String templatePath, String createWordPath, QCDocBaseDataWord params) throws IOException {
        ListRenderPolicy policy = new ListRenderPolicy();
        Configure config = Configure.builder()
                .bind("getAwardInfos", policy)
                .build();


        try {

        XWPFTemplate template = XWPFTemplate.compile(templatePath, config).render(
                new HashMap<String, Object>() {{
                    put("params",params);
                }}
        );

        template.writeToFile(createWordPath);
        }catch (Exception ex) {
            logger.error("poi word url exception {}",ex.getMessage());
        }
    }


    public static Style getStyle(){
        Style style = new Style();
        style.setColor("333333");
        style.setFontFamily("宋体");
        style.setBold(true);
        style.setFontSize(10);
        return style;
    }



    public static TableRenderData getTableRenderData(List<RowRenderData> rowRenderDataList){
        RowRenderData[] rowArr = new RowRenderData[rowRenderDataList.size()];
        for(int i=0;i<rowArr.length;i++) {
            rowArr[i] = rowRenderDataList.get(i);
        }
        return Tables.create(rowArr);
    }




    public static void main(String[] args) throws IOException {
//        Map<String, Object> datasq = new HashMap<String, Object>();
//
//        GroupScoreItem groupScoreItem = new GroupScoreItem();
//        groupScoreItem.setChengguo("AAAA");
//        groupScoreItem.setTechnologyAdvancement("1");
//        groupScoreItem.setDegreeInnovation("1");
//        groupScoreItem.setLoreProperty("1");
//        groupScoreItem.setEconomicBenefit("1");
//        groupScoreItem.setSocialBenefit("1");
//        groupScoreItem.setPromotionProspect("1");
//        groupScoreItem.setPreparationExpression("1");
//        groupScoreItem.setTotal("1");
//
//
//        try {
//            datasq.put("urlHeader", Pictures.ofUrl("https://img1.doubanio.com/view/celebrity/raw/public/p1564038254.19.jpg", PictureType.PNG).size(117, 145).create());
//        }catch (Exception ex) {
//            logger.error("poi word url exception {}",ex.getMessage());
//        }
//
//        List<GroupScoreItem> datas = new ArrayList<>();
//
//        datas.add(groupScoreItem);
//        Configure config = Configure.newBuilder()
//                .bind("tableHis", new PoiWordExpertScoreTablePolicy())//获奖信息
//                 .build();
//
//        XWPFTemplate template = XWPFTemplate.compile("/Users/zhanggai/dev/group_expert_score.docx", config).render(
//                new HashMap<String, Object>() {{
//                    put("tableHis",datas );
//                }}
//        );
//        template.writeToFile("/Users/zhanggai/dev/group_expert_score1.docx");


//        List<Object> projectScoreInfoList =  new ArrayList<>();
//        ProjectScoreInfo projectScoreInfo = new ProjectScoreInfo() ;
//        projectScoreInfo.setChengguo("AAAA");
//        projectScoreInfo.setEnterpriseName("BBBBB");
//        projectScoreInfo.setMajor("CCCCC");
//        projectScoreInfo.setOpinionLevel("DDDDD");
//        projectScoreInfo.setCreated("12121212");
//        projectScoreInfo.setOpinionText("asdfadfasdfasdf");
//        projectScoreInfo.setImageObj(Pictures.ofUrl("https://img1.doubanio.com/view/celebrity/raw/public/p1564038254.19.jpg", PictureType.PNG).size(117, 145).create());
//        projectScoreInfoList.add(projectScoreInfo);
//
//
//        creatExpertAdvisePrint("/Users/zhanggai/dev/expert_advice.docx","/Users/zhanggai/dev/expert_advice11.docx",projectScoreInfoList);


//        String sheetName = "测试";
//        String  headContent = "油气集输及储运组";
//        String[] userName = {"李刚", "王强", "小王"};
//        File file = new File("/Users/zhanggai/dev/hssf.xls");
//        FileOutputStream output = new FileOutputStream(file);
//        HSSFWorkbook workbook = new HSSFWorkbook();
//        HSSFSheet sheet = workbook.createSheet(sheetName);
//        HSSFRow row = sheet.createRow(0);
//        HSSFRow row1 = sheet.createRow(1);
//        HSSFRow row2 = sheet.createRow(2);
//        HSSFCell cell = null;
//       int celCount  = userName.length + 6 ;
//        for (int i = 0; i < celCount; i++) {
//            cell = row.createCell(i);
//       }
//        cell.setCellValue(headContent);
//        CellRangeAddress region = new CellRangeAddress(0, 0, 0, celCount - 1 );
//        sheet.addMergedRegion(region);
//        row.getCell(0).setCellValue(headContent);
//        for (int i = 0; i < celCount; i++) {
//            row1.createCell(i);
//            row2.createCell(i);
//        }
//        row1.getCell(0).setCellValue("序号");
//        row1.getCell(1).setCellValue("项目名称");
//        row1.getCell(2).setCellValue("承建单位");
//        row1.getCell(3).setCellValue("专家组成员打分情况");
//        row1.getCell(celCount - 2).setCellValue("排序");
//        row1.getCell(celCount - 1).setCellValue("推荐意见");
//        for (int i = 0; i < celCount; i++) {
//            row2.createCell(i);
//        }
//        row2.getCell(0).setCellValue("序号");
//        row2.getCell(1).setCellValue("项目名称");
//        CellRangeAddress region1 = new CellRangeAddress(1, 2, 0, 0 );
//        sheet.addMergedRegion(region1);// 序号
//
//        CellRangeAddress region2 = new CellRangeAddress(1, 2, 1, 1 );
//        sheet.addMergedRegion(region2);// 项目名称
//
//
//
//        CellRangeAddress region3 = new CellRangeAddress(1, 1, 3, celCount -1 - 2 );
//        sheet.addMergedRegion(region3);//专家组成员打分情况
//
//        CellRangeAddress region4 = new CellRangeAddress(1, 2, celCount-1-1, celCount-1-1 );
//        sheet.addMergedRegion(region4);
//
//        CellRangeAddress region5 = new CellRangeAddress(1, 2, celCount-1, celCount-1 );
//        sheet.addMergedRegion(region5);
//        CellRangeAddress region6 = new CellRangeAddress(1, 2, 2, 2) ;
//        sheet.addMergedRegion(region6);
//
//        for (int a = 0 ; a < userName.length ; a++){
//            row2.getCell(a + 3 ).setCellValue(userName[a]);
//
//        }
//        row2.getCell(3+userName.length ).setCellValue("平均值");
//
//
//        for (int m = 0 ; m < 4 ; m++){
//            HSSFRow row3 = sheet.createRow(m+3);
//            for (int n =0 ;n < celCount ; n++){
//                row3.createCell(n);
//                row3.getCell(n).setCellValue("a + n");
//            }
//        }
////        List<AwardScoreDetailInfo> allData = new ArrayList<>();
////        AwardScoreDetailInfo awardScoreDetailInfo = new AwardScoreDetailInfo();
////        awardScoreDetailInfo.setScoreUid(121212121);
////        awardScoreDetailInfo.setScoreVal(121.0);
////        awardScoreDetailInfo.setScoreTxt("asdfasdf");
//        try {
//            workbook.write(output);
//            output.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }











}
