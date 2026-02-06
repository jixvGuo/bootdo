package com.bootdo.cpe.utils;

import com.bootdo.cpe.domain.*;
import com.bootdo.cpe.petroleum_engineering_award.domain.OilProUnitOpinionDO;
import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.data.*;
import com.deepoove.poi.data.style.Style;
import com.deepoove.poi.data.style.TableStyle;
import com.deepoove.poi.policy.ListRenderPolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bootdo.common.utils.PoiWordUtils.getTableRenderData;

/**
 * 勘察设计奖 咨询类 打印的打印模块
 */
public class PoiWordSurveyConsultProUtils {
    private static Logger logger = LoggerFactory.getLogger(PoiWordSurveyConsultProUtils.class);
    public static void createWorld(String templatePath, String createWordPath, Map<String, Object> params) throws IOException {
        XWPFTemplate template = XWPFTemplate.compile(templatePath).render(params);
        FileOutputStream out = new FileOutputStream(createWordPath);
        template.write(out);
        out.flush();
        out.close();
    }


    /**
     * 创建咨询类
     * @param templatePath
     * @param createWordPath
     * @param params
     * @throws IOException
     */
    public static void createDesignWord(String templatePath, String createWordPath, SurveyDesignConsultDataWord params) throws IOException {
        ListRenderPolicy policy = new ListRenderPolicy();
        Configure config = Configure.builder()
                .bind("getAwardInfos", policy)
                .build();
        try {
            TableRenderData mainContribute = getMainContribute(params);//在本项目中做出贡献的主要人员情况表
            TableRenderData opinionList = getOpinionTable(params);//在本项目中做出贡献的主要人员情况表


            XWPFTemplate template = XWPFTemplate.compile(templatePath, config).render(
                new HashMap<String, Object>() {{
                    put("params",params);
                    put("mainContribute",mainContribute);
                    put("opinionList",opinionList);
                }}
        );

        template.writeToFile(createWordPath);
        }catch (Exception ex) {
            logger.error("poi word url exception {}",ex.getMessage());
        }
    }


    /***
     * 在本项目中做出贡献的主要人员情况表
     * @param allData
     * @return
     */
    private static TableRenderData   getMainContribute(SurveyDesignConsultDataWord allData){
        List<String> listKowlegesStr = new ArrayList<>();
        listKowlegesStr.add("序号");
        listKowlegesStr.add("姓名");
        listKowlegesStr.add("性别");
        listKowlegesStr.add("年龄");
        listKowlegesStr.add("职务或职称");
        listKowlegesStr.add("工作单位");
        listKowlegesStr.add("参加起止时间");
        listKowlegesStr.add("在本项目中担任的主要工作职责");


        RowRenderData header = Rows.of(getRowHederList(listKowlegesStr)).textFontSize(14).bgColor("FFFFFF").center().create();
        List<RowRenderData> knowledgeRows = new ArrayList<>();
        knowledgeRows.add(header);
        List<SurverDesiginContributeUserInfoDO>  listMaterialsUseInfoDO = allData.getUserInfo();
        for (int a = 0; a < listMaterialsUseInfoDO.size(); a++) {
            SurverDesiginContributeUserInfoDO award = listMaterialsUseInfoDO.get(a);
            knowledgeRows.add(Rows.of(
                    award.getId()+"",
                    award.getUsername(),
                    award.getGender(),
                    award.getAge()+"",
                    award.getJob(),
                    award.getWorkPlace(),award.getStartTime(),award.getWorkDesc()).textFontSize(12).bgColor("FFFFFF").create());
        }
        return  getTableRenderData(knowledgeRows);
    }

    public static TableRenderData getOpinionTable(SurveyDesignConsultDataWord params){
        List<RowRenderData> rowList = new ArrayList<>();
        TableStyle tableStyle = new TableStyle();
        Style rowStyle = new Style();
        rowStyle.setFontSize(18);
        List<String> listKowlegesStr = new ArrayList<>();
        listKowlegesStr.add("项目编号");
        listKowlegesStr.add("申报单位");
        listKowlegesStr.add("奖项类别");
        listKowlegesStr.add("项目名称");
        listKowlegesStr.add("推荐等级");
        listKowlegesStr.add("初评专业组");


        for(SurverConsultApplyProjectProfileDO opinionDO:params.getProjectProfileDOList()) {
            RowRenderData header = Rows.of(getRowHederList(listKowlegesStr)).textFontSize(14).bgColor("FFFFFF").center().create();

            rowList.add(header);
            rowList.add(Rows.of(
                    opinionDO.getProjectId()+"",
                    opinionDO.getReportingUnit(),
                    opinionDO.getAwardCategory(),
                    opinionDO.getProjectName()+"",
                    opinionDO.getRecommendedGrade(),
                    opinionDO.getPreliminaryEvaluationGroup()).textFontSize(12).bgColor("FFFFFF").create());

            RowRenderData  row0 =   Rows.of(
                     "项目简介",opinionDO.getProjectDescription(),"","","",""
                    ).textFontSize(12).bgColor("FFFFFF").create() ;

            rowList.add(row0);

        }
        return getTableRenderData(rowList);
    }



    /***
     * 项目简介
     * @param allData
     * @return
     */
    private static TableRenderData   get(SurveyDesignConsultDataWord allData){
        List<String> listKowlegesStr = new ArrayList<>();
        listKowlegesStr.add("序号");
        listKowlegesStr.add("姓名");
        listKowlegesStr.add("性别");
        listKowlegesStr.add("年龄");
        listKowlegesStr.add("职务或职称");
        listKowlegesStr.add("工作单位");
        listKowlegesStr.add("参加起止时间");
        listKowlegesStr.add("在本项目中担任的主要工作职责");


        RowRenderData header = Rows.of(getRowHederList(listKowlegesStr)).textFontSize(14).bgColor("FFFFFF").center().create();
        List<RowRenderData> knowledgeRows = new ArrayList<>();
        knowledgeRows.add(header);
        List<SurverDesiginContributeUserInfoDO>  listMaterialsUseInfoDO = allData.getUserInfo();
        for (int a = 0; a < listMaterialsUseInfoDO.size(); a++) {
            SurverDesiginContributeUserInfoDO award = listMaterialsUseInfoDO.get(a);
            knowledgeRows.add(Rows.of(
                    award.getId()+"",
                    award.getUsername(),
                    award.getGender(),
                    award.getAge()+"",
                    award.getJob(),
                    award.getWorkPlace(),award.getStartTime(),award.getWorkDesc()).textFontSize(12).bgColor("FFFFFF").create());
        }
        return  getTableRenderData(knowledgeRows);
    }

    /***
     * 表头数据
     * @param title
     * @return
     */
    private static TextRenderData[] getRowHederList(List<String> title) {
        int count = title.size();
        TextRenderData[] headList = new TextRenderData[count];

        for (int a = 0; a < count; a++) {
            headList[a] = new TextRenderData(title.get(a), getHeadStyle());
        }

        return headList;
    }
    public static Style getHeadStyle(){
        Style style = new Style();
        style.setColor("333333");
        style.setFontFamily("宋体");
        style.setBold(true);
        style.setFontSize(10);
        return style;
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
