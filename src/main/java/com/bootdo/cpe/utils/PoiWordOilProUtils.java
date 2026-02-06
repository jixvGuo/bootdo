package com.bootdo.cpe.utils;

import com.bootdo.activiti.domain.ProjectScoreInfo;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.cpe.domain.*;
import com.bootdo.cpe.petroleum_engineering_award.domain.OilAwardContributionUsersDO;
import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.data.*;
import com.deepoove.poi.data.style.Style;
import com.deepoove.poi.policy.ListRenderPolicy;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
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
public class PoiWordOilProUtils {
    private static Logger logger = LoggerFactory.getLogger(PoiWordOilProUtils.class);
    public static void createWorld(String templatePath, String createWordPath, Map<String, Object> params) throws IOException {
        XWPFTemplate template = XWPFTemplate.compile(templatePath).render(params);
        FileOutputStream out = new FileOutputStream(createWordPath);
        template.write(out);
        out.flush();
        out.close();
    }

    /**
     * @param templatePath
     * @param createWordPath
     * @param params 安装工程
     * @throws IOException
     */
    public static void createOilProWord(String templatePath, String createWordPath, PetroleumEngineDocBaseDataWord params) throws IOException {
        ListRenderPolicy policy = new ListRenderPolicy();
        Configure config = Configure.builder()
                .bind("opinionList", policy).bind("designList", policy)
                .bind("engineeList" ,policy)
                .bind("awardTable", new PoiWordOilInstallAwardDetailTablePolicy())
                .build();
        XWPFTemplate template = XWPFTemplate.compile(templatePath, config).render(
                new HashMap<String, Object>() {{
                    put("params",params);
                    put("opinionList",params.getOpinionDOList());
                    put("designAward", params.getSituationDO().getOilProDesignAwardDO());
                    put("engineeAward", params.getSituationDO().getOilProEngineeAwardDO());
                    put("awardTable", params.getSituationDO());
                }}
        );
        template.writeToFile(createWordPath);

    }


    /**
     * 石油工程金奖申请 / 优质工程
     * @param templatePath
     * @param createWordPath
     * @param params
     * @throws IOException
     */
    public static void createQualityOilProWord(String templatePath, String createWordPath, PetroleumEngineAwardDocBaseDataWord params) throws IOException {
        ListRenderPolicy policy = new ListRenderPolicy();
        Configure config = Configure.builder()
                .bind("getAwardInfos", policy)
                .bind("awardTable", new PoiWordOilQualityDetailTablePolicy())//获奖信息
                .bind("awardUniteInfos", new PoiWordOilQualityUniteInfoTablePolicy())// 获奖单位信息
                .bind("awardParkTakeUnite" ,new PoiWordOilQualityUniteTablePolicy())// 参与单位信息
                .build();

        XWPFTemplate template = XWPFTemplate.compile(templatePath, config).render(
                new HashMap<String, Object>() {{
                    put("params",params);
                    put("awardTable", params.getTableData());
                    put("awardUniteInfos", params.getTableData());
                    put("awardParkTakeUnite", params.getTableData());
                }}
        );
        template.writeToFile(createWordPath);

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

    /**
     * @param templatePath
     * @param createWordPath
     * @param params
     * @throws IOException
     */
    public static void createOilContributeProWord(String templatePath, String createWordPath, PetroleumEngineContributeDocBaseDataWord params) throws IOException {


        Map<String, Object> datas = new HashMap<String, Object>();


        ListRenderPolicy policy = new ListRenderPolicy();
        Configure config = Configure.builder()
                .bind("opinionList", policy).build();

        List<RowRenderData> rowRenderDataList = new ArrayList<>();
        RowRenderData headRow = Rows.of("序号", "承建（参建）单位","姓名", "职务", "工程建设中的作用")
                .textColor("333333").textFontSize(10)
                .textFontFamily("宋体").textBold()
                .bgColor("FFFFFF").center().create();
        rowRenderDataList.add(headRow);

        for (int a = 0; a < params.getOpinionDOList().size(); a++) {
            int serialNumber = a + 1;
            OilAwardContributionUsersDO award = params.getOpinionDOList().get(a);
            rowRenderDataList.add(Rows.of(
                    serialNumber +"",
                    award.getAffectProjectConstruction(),
                    award.getName(),
                    award.getPost(),
                    award.getContractorsParticipants()
                     ).create());

        }
        RowRenderData[] rowArr = new RowRenderData[rowRenderDataList.size()];
        for(int i=0;i<rowArr.length;i++) {
            rowArr[i] = rowRenderDataList.get(i);
        }
        datas.put("knowledgeTable", Tables.create(rowArr));
        datas.put("projectName", params.getProjectName());
        datas.put("useName", params.getUseName());

        XWPFTemplate template = XWPFTemplate.compile(templatePath).render(datas);
        FileOutputStream out = new FileOutputStream(createWordPath);
        template.write(out);
        out.flush();
        out.close();


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




    /***
     *
     * @param templatePath
     * @param createWordPath
     * @throws IOException
     */
    public static void creatExpertPrint(String templatePath, String createWordPath,
                                        List<GroupScoreItem>  scores,String imgPath, String imgName,String createTime, String groupName)throws IOException {

        HashMap<String,Object> params = new HashMap<>();

        params.put("create",createTime);
        params.put("groupName", groupName);
        if(StringUtils.isNotBlank(imgName)) {
            imgPath = imgPath + "/" + URLEncoder.encode(imgName, "utf-8");
            params.put("imgObj", Pictures.ofUrl(imgPath, PictureType.PNG).size(75, 40).create());
        }
        Configure config = Configure.builder()
                .bind("tableHis", new PoiWordExpertScoreTablePolicy())//获奖信息
                .build();
        XWPFTemplate template = XWPFTemplate.compile(templatePath, config).render(
                new HashMap<String, Object>() {{
                    put("tableHis",scores);
                    put("params",params);
                }}
        );
        template.writeToFile(createWordPath);
    }

    /***
     *
     * @param templatePath
     * @param createWordPath
     * @throws IOException
     */
    public static void creatExpertPersonalPrint(String templatePath, String createWordPath,
                                        List<GroupScoreItem>  scores,String imgPath, String imgName,String createTime, String groupName)throws IOException {

        HashMap<String,Object> params = new HashMap<>();

        params.put("create",createTime);
        params.put("groupName", groupName);
        if(StringUtils.isNotBlank(imgName)) {
            imgPath = imgPath + "/" + URLEncoder.encode(imgName, "utf-8");
            params.put("imgObj", Pictures.ofUrl(imgPath, PictureType.PNG).size(75, 40).create());
        }
        Configure config = Configure.builder()
                .bind("tableHis", new PoiWordExpertPersonalTablePolicy())//获奖信息
                .build();
        XWPFTemplate template = XWPFTemplate.compile(templatePath, config).render(
                new HashMap<String, Object>() {{
                    put("tableHis",scores);
                    put("params",params);
                }}
        );
        template.writeToFile(createWordPath);
    }




    /***
     * List<Object> obj
     * 专家评审意见表
     * @param templatePath
     * @param createWordPath
     * @throws IOException
     */
    public static void creatExpertAdvisePrint(String templatePath, String createWordPath,List<Object> obj
                                               )throws IOException {
        ListRenderPolicy policy = new ListRenderPolicy();
        Configure config = Configure.builder()
                .bind("opinionList", policy)
                .build();
        XWPFTemplate template = XWPFTemplate.compile(templatePath, config).render(
                new HashMap<String, Object>() {{
                    put("opinionList",obj);
                }}
        );
        template.writeToFile(createWordPath);
    }


    /****
     * 科技奖打分统计 Scoring statistics
     * @param sheetName sheet 的名称
     * @param headContent 表格的头部信息
     * @param scoreUserNameList 专家的名字
     * @param response
     * @throws IOException
     */
    public  static void downScoreStatistics(String sheetName, String headContent, List<String>   scoreUserNameList,
                                            List<ProjectScoreTotalInfo> projectScoreTotalInfos,
                                  HttpServletResponse response) throws IOException {

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(sheetName +"科技奖");
        HSSFRow row = sheet.createRow(0);
        HSSFRow row1 = sheet.createRow(1);
        HSSFRow row2 = sheet.createRow(2);
        HSSFCell cell = null;
        int celCount  = scoreUserNameList.size() + 7 ;
        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        for (int i = 0; i < celCount; i++) {
            cell = row.createCell(i);
            cell .setCellStyle(style);
        }
        cell.setCellValue(headContent);
        CellRangeAddress region = new CellRangeAddress(0, 0, 0, celCount - 1 );
        sheet.addMergedRegion(region);
        row.getCell(0).setCellValue(headContent);
        for (int i = 0; i < celCount; i++) {
            row1.createCell(i);
            row2.createCell(i);
        }
        row1.getCell(0).setCellValue("序号");
        row1.getCell(1).setCellValue("项目编号");
        row1.getCell(2).setCellValue("项目名称");
        row1.getCell(3).setCellValue("承建单位");
        row1.getCell(4).setCellValue("专家组成员打分情况");
        row1.getCell(celCount - 2).setCellValue("排序");
        row1.getCell(celCount - 1).setCellValue("推荐意见");
        for (int i = 0; i < celCount; i++) {
            row2.createCell(i);
        }
        row2.getCell(0).setCellValue("序号");
        row2.getCell(1).setCellValue("项目编号");
        row2.getCell(2).setCellValue("项目名称");
        row2.getCell(3).setCellValue("承建单位");

        CellRangeAddress region1 = new CellRangeAddress(1, 2, 0, 0 );
        sheet.addMergedRegion(region1);// 序号


        CellRangeAddress region2 = new CellRangeAddress(1, 2, 1, 1 );
        sheet.addMergedRegion(region2);// 项目编号



        CellRangeAddress region3 = new CellRangeAddress(1, 2, 2, 2 );
        sheet.addMergedRegion(region3);// 项目名称

        CellRangeAddress region7 = new CellRangeAddress(1, 2, 3, 3 );
        sheet.addMergedRegion(region7);// 城建单位




        CellRangeAddress region4 = new CellRangeAddress(1, 1, 4, celCount -1 - 2 );
        sheet.addMergedRegion(region4);//专家组成员打分情况

        CellRangeAddress region5 = new CellRangeAddress(1, 2, celCount-1-1, celCount-1-1 );
        sheet.addMergedRegion(region5);

        CellRangeAddress region6 = new CellRangeAddress(1, 2, celCount-1, celCount-1 );
        sheet.addMergedRegion(region6);



        for (int a = 0 ; a < scoreUserNameList.size() ; a++){
            row2.getCell(a + 4 ).setCellValue(scoreUserNameList.get(a));
        }
        row2.getCell(4+scoreUserNameList.size() ).setCellValue("平均值");

        int dataSize = projectScoreTotalInfos.size();
        for (int m = 0 ; m < dataSize ; m++){
            ProjectScoreTotalInfo info =   projectScoreTotalInfos.get(m);
            HSSFRow row3 = sheet.createRow(m+3);
            for (int n =0 ;n < celCount ; n++){
                row3.createCell(n).setCellStyle(style);
                if(n == 0 ){
                    row3.getCell(n).setCellValue(m+1);
                }  else if(n == 1){
                    row3.getCell(n).setCellValue(info.getProCode());
                } else if(n == 2){
                    row3.getCell(n).setCellValue(info.getName());
                }else if(n == 3){
                    row3.getCell(n).setCellValue(info.getEnterpriseName());
                }else if(n == celCount-1){// 推荐意见
//                    row3.getCell(n).setCellValue(info.getEnterpriseName());
                }else if(n == celCount-2){// 排序
                    row3.getCell(n).setCellValue(m+1);
                }else if(n == celCount-3){// 排序
                   row3.getCell(n) .setCellValue(info.getAvgScore());
                }
            }

            for(int a = 0 ;a < scoreUserNameList.size() ; a++){
                String scoreUserName = scoreUserNameList.get(a);
                Double score = info.getScoreMap().get(scoreUserName);
                row3.getCell(a+4).setCellValue(score == null ? 0 : score);
            }
        }
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=expert_scores.xls");


        workbook.write(response.getOutputStream());
        response.flushBuffer();
    }





    /****
     * 科技奖打分统计 Scoring statistics
     * @param sheetName sheet 的名称
     * @param headContent 表格的头部信息
     * @param scoreUserNameList 专家的名字
     * @param response
     * @throws IOException
     */
    public  static void downScoreStatisticsPersonal(String sheetName,String headContent, List<String>   scoreUserNameList,
                                            List<ProjectScoreTotalInfo> projectScoreTotalInfos,
                                            HttpServletResponse response) throws IOException {

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(sheetName +"科技奖");
        HSSFRow row = sheet.createRow(0);
        HSSFRow row1 = sheet.createRow(1);
        HSSFRow row2 = sheet.createRow(2);
        HSSFCell cell = null;
        int celCount  = scoreUserNameList.size() + 7 ;
        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);

        for (int i = 0; i < celCount; i++) {
            cell = row.createCell(i);
            cell .setCellStyle(style);
        }
        cell.setCellValue(headContent);
        CellRangeAddress region = new CellRangeAddress(0, 0, 0, celCount - 1 );
        sheet.addMergedRegion(region);
        row.getCell(0).setCellValue(headContent);
        for (int i = 0; i < celCount; i++) {
            row1.createCell(i);
            row2.createCell(i);
        }
        row1.getCell(0).setCellValue("序号");
        row1.getCell(1).setCellValue("项目编号");
        row1.getCell(2).setCellValue("项目名称");
        row1.getCell(3).setCellValue("承建单位");
        row1.getCell(4).setCellValue("专家组成员打分情况");
        row1.getCell(celCount - 2).setCellValue("排序");
        row1.getCell(celCount - 1).setCellValue("推荐意见");
        for (int i = 0; i < celCount; i++) {
            row2.createCell(i);
        }
        row2.getCell(0).setCellValue("序号");
        row2.getCell(1).setCellValue("项目编号");
        row2.getCell(2).setCellValue("项目名称");
        row2.getCell(3).setCellValue("承建单位");



        CellRangeAddress region1 = new CellRangeAddress(1, 2, 0, 0 );
        sheet.addMergedRegion(region1);// 序号


        CellRangeAddress region2 = new CellRangeAddress(1, 2, 1, 1 );
        sheet.addMergedRegion(region2);// 项目名称



        CellRangeAddress region3 = new CellRangeAddress(1, 2, 2, 2 );
        sheet.addMergedRegion(region3);// 项目名称




        CellRangeAddress region7 = new CellRangeAddress(1, 2, 3, 3 );
        sheet.addMergedRegion(region7);// 城建单位





        CellRangeAddress region4 = new CellRangeAddress(1, 1, 4, celCount -1 - 2 );
        sheet.addMergedRegion(region4);//专家组成员打分情况

        CellRangeAddress region5 = new CellRangeAddress(1, 2, celCount-1-1, celCount-1-1 );
        sheet.addMergedRegion(region5);

        CellRangeAddress region6 = new CellRangeAddress(1, 2, celCount-1, celCount-1 );
        sheet.addMergedRegion(region6);



        for (int a = 0 ; a < scoreUserNameList.size() ; a++){
            row2.getCell(a + 4 ).setCellValue(scoreUserNameList.get(a));
        }
        row2.getCell(4+scoreUserNameList.size() ).setCellValue("平均值");


        int dataSize = projectScoreTotalInfos.size();
        for (int m = 0 ; m < dataSize ; m++){
            ProjectScoreTotalInfo info =   projectScoreTotalInfos.get(m);
            HSSFRow row3 = sheet.createRow(m+3);
            for (int n =0 ;n < celCount ; n++){
                row3.createCell(n).setCellStyle(style);
                if(n == 0 ){
                    row3.getCell(n).setCellValue(m+1);
                }  else if(n == 1){
                    row3.getCell(n).setCellValue(info.getProCode());
                } else if(n == 2){
                    row3.getCell(n).setCellValue(info.getName());
                }else if(n == 3){
                    row3.getCell(n).setCellValue(info.getEnterpriseName());
                }else if(n == celCount-1){// 推荐意见
//                    row3.getCell(n).setCellValue(info.getEnterpriseName());
                }else if(n == celCount-2){// 排序
                    row3.getCell(n).setCellValue(m+1);
                }else if(n == celCount-3){// 排序
                    row3.getCell(n) .setCellValue(info.getAvgScore());
                }
            }

            for(int a = 0 ;a < scoreUserNameList.size() ; a++){
                String scoreUserName = scoreUserNameList.get(a);
                Double score = info.getScoreMap().get(scoreUserName);
                row3.getCell(a+4).setCellValue(score == null ? 0 : score);
            }
        }
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=expert_scores.xls");


        workbook.write(response.getOutputStream());
        response.flushBuffer();
    }



}
