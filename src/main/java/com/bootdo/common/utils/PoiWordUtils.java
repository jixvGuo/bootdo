package com.bootdo.common.utils;

import com.bootdo.activiti.domain.AwardScoreDetailInfo;
import com.bootdo.activiti.domain.EnterpriseProjectInfoDo;
import com.bootdo.activiti.domain.PublishAwardTaskDo;
import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.config.Constant;
import com.bootdo.cpe.domain.*;
import com.bootdo.cpe.dto.QcProDataDto;
import com.bootdo.cpe.petroleum_engineering_award.domain.ScienceBaseProjectInfoDO;
import com.bootdo.cpe.utils.DateUtil;
import com.bootdo.cpe.utils.PoiWordScienceAwardTeamCooperationInfoTablePolicy;
import com.bootdo.cpe.utils.PoiWordScienceAwardTeamMainUserTablePolicy;
import com.bootdo.system.domain.*;
import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.data.*;
import com.deepoove.poi.data.style.Style;
import com.deepoove.poi.data.style.TableStyle;
import com.deepoove.poi.policy.ListRenderPolicy;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.ddr.poi.html.HtmlRenderPolicy;
import org.openxmlformats.schemas.officeDocument.x2006.math.STJc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

import static com.bootdo.common.utils.SpringContextHolder.getBean;

/**
 * @author houzb
 * @Description
 * @create 2020-09-07 16:37
 */
public class PoiWordUtils {

    private static Logger logger = LoggerFactory.getLogger(PoiWordUtils.class);

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
     * @param params
     * @throws IOException
     */
    public static void createWord(String templatePath, String createWordPath, DocBaseDataWord params) throws IOException {
        XWPFTemplate template = XWPFTemplate.compile(templatePath).render(new HashMap<String, Object>() {{
            put("params", params);


        }});
        FileOutputStream out = new FileOutputStream(createWordPath);
        template.write(out);
        out.flush();
        out.close();
    }


    /****
     *
     * @param templatePath 路径
     * @param createWordPath 创建文件路径
     * @param params 页面基本参数
     * @param teamMainUers 主要成员 带头人
     * @param oteamUers   其他
     * @param teamLandmarkList 团队标志性成果
     * @param teamPaperInfos 发表论文专著情况
     * @param academicExchangeDOList 学术交流情况
     * @param intellectualPropertyList 所获知识产权和技术标准情况
     * @param teamUndertakesList 团队承担项目情况
     * @param thirdList    第三方评价表
     * @param teamEverList 团队曾获科技奖励情况
     * @param cooperationDOList 团队合作汇总表
     * @param teamSupport 支持单位情况
     * @throws IOException
     */
    public static void createTeamWord(String templatePath,
                                      String createWordPath,
                                      DocBaseDataWord params,
                                      List<EnterpriTeamLeaderInfoDO> teamMainUers,
                                      List<EnterpriTeamOtherMainDO> oteamUers,
                                      List<EnterpriTeamAcheievementsDO> teamLandmarkList,
                                      List<EnterpriTeamPaperInfoDO> teamPaperInfos,
                                      List<EnterpriTeamAcademicExchangeDO> academicExchangeDOList,
                                      List<EnterpriTeamIntellectualPropertyDO> intellectualPropertyList,
                                      List<EnterpriTeamProjectInfoDO> teamUndertakesList,
                                      List<EnterpriTeamEvaluateOtherDO> thirdList,
                                      List<EnterpriTeamGetTechAwardDO> teamEverList,
                                      List<EnterpriTeamCooperationDO> cooperationDOList,
                                      List<EnterpriTeamSupportCompanyDO> teamSupport) throws IOException {

        Map<String, Object> datas = new HashMap<String, Object>();
        // 基础数据
        datas.put("params", params);
        //  人员构成
        // 成员结构
        HtmlRenderPolicy htmlRenderPolicy = new HtmlRenderPolicy();
        ListRenderPolicy policy = new ListRenderPolicy();

        DocTeamApplyWord docTeamApplyWord = (DocTeamApplyWord) params;
        EnterpriTeamInfoDO teamInfoDO = docTeamApplyWord.getTeamInfo();
        if(teamInfoDO != null) {
            Style style = new Style();
            style.setColor("333333");
            style.setFontFamily("宋体");
            style.setBold(true);
            style.setFontSize(16);

            datas.put("teamName", new TextRenderData( teamInfoDO.getTeamName(), style));
            datas.put("enterpriseName", new TextRenderData( teamInfoDO.getEnterpriseName(), style));
        }
        EnterpriTeamUsersDO teamUsersDO = docTeamApplyWord.getTeamUsersDO();
        if (teamUsersDO != null) {
            datas.put("total", getTextRenderData(teamUsersDO.getTotalCount()));
            datas.put("man", getTextRenderData(teamUsersDO.getManCount()));
            datas.put("woman", getTextRenderData(teamUsersDO.getWomanCount()));
            datas.put("r65", getTextRenderData(teamUsersDO.getAgeBeyond65Count()));
            datas.put("p65", getTextRenderData(teamUsersDO.getAgeBeyond65CountNum()));

            datas.put("b50", getTextRenderData(teamUsersDO.getAge50To65Count()));
            datas.put("p50", getTextRenderData(teamUsersDO.getAge50To65CountNum()));

            datas.put("u35", getTextRenderData(teamUsersDO.getAge35To50Count()));
            datas.put("p35", getTextRenderData(teamUsersDO.getAge35To50CountNum()));

            datas.put("b35", getTextRenderData(teamUsersDO.getAgeUnder36Count()));
            datas.put("pb35", getTextRenderData(teamUsersDO.getAgeUnder36CountNum()));

            datas.put("senior", getTextRenderData(teamUsersDO.getPositionSenior()));
            datas.put("psenior", getTextRenderData(teamUsersDO.getPositionSeniorNum()));

            datas.put("dsenior", getTextRenderData(teamUsersDO.getPositionDeputySenior()));
            datas.put("pdsenior", getTextRenderData(teamUsersDO.getPositionDeputySeniorNum()));

            datas.put("inMedia", getTextRenderData(teamUsersDO.getPositionIntermediate()));
            datas.put("pinMedia", getTextRenderData(teamUsersDO.getPositionIntermediateNum()));

            datas.put("docCan", getTextRenderData(teamUsersDO.getEduDoctoralCandidate()));
            datas.put("pdocCan", getTextRenderData(teamUsersDO.getEduDoctoralCandidateNum()));

            datas.put("eduPost", getTextRenderData(teamUsersDO.getEduPostgraduates()));
            datas.put("peduPost", getTextRenderData(teamUsersDO.getEduPostgraduatesNum()));

            datas.put("underG", getTextRenderData(teamUsersDO.getEduUndergraduate()));
            datas.put("punderG", getTextRenderData(teamUsersDO.getEduUndergraduateNum()));

        }
//  -------------------（二）主要成员----------------------------------------------------

        EnterpriTeamMainUserPoiData mainUserPoiData = new EnterpriTeamMainUserPoiData(teamMainUers, oteamUers);

        datas.put("mainUserPoiData", mainUserPoiData);


//=============================团队标志性成果===================================================
        String teamLandmarkTable  = "";
        List<String> teamLandmarkTitle = new ArrayList<>();
        teamLandmarkTitle.add("序号");
        teamLandmarkTitle.add("成果名称");
        teamLandmarkTitle.add("团队主要成员中的完成人/排名");
        teamLandmarkTitle.add("研发起止日期");
        teamLandmarkTitle.add("正式应用（公开发表）日期");
        teamLandmarkTitle.add("依托计划名称和编号（不超过3项）");
        teamLandmarkTitle.add("证明材料编号");

        RowRenderData teamLandmarkTitleHead = Rows.of(getRowHederList(teamLandmarkTitle)).create();


        String teamLandmarkTableHeader = "<table style='border:1px solid grey;' class=\"ui table\"> " +
                "        <thead> " +
                "        <th style='border:1px solid grey;'>序号</th> " +
                "        <th style='border:1px solid grey;'>成果名称</th> " +
                "        <th style='border:1px solid grey;'>团队主要成员中的完成人/排名</th> " +
                "        <th style='border:1px solid grey;'>研发起止日期</th> " +
                "        <th style='border:1px solid grey;'>正式应用（公开发表）日期</th> " +
                "        <th style='border:1px solid grey;'>依托计划名称和编号（不超过3项）</th> " +
                "        <th style='border:1px solid grey;'>证明材料编号</th> " +
                "        </thead> " +
                "        <tbody> "
                ;


        List<RowRenderData> teamLandRows = new ArrayList<>();
        teamLandRows.add(teamLandmarkTitleHead);
        if (teamLandmarkList != null){
            String appendStr  = "";
            for (int a = 0; a < teamLandmarkList.size(); a++) {
                EnterpriTeamAcheievementsDO award = teamLandmarkList.get(a);
                int num = a + 1;
                teamLandRows.add(Rows.of(
                        num + "",
                        award.getAchName(),
                        award.getAchUsersNo(),
                        award.getAchDateStart() + "~" + award.getAchDateEnd(),
                        award.getAchUseDate(),
                        award.getAchPlanNo()  ,
                        award.getAchEvidenceCode()
                ).textColor("333333").textFontSize(10)
                        .textFontFamily("宋体")
                        .bgColor("FFFFFF").center().create());

                appendStr +=
                        "        <tr> " +  "<td  style='border:1px solid grey;'> " + num +  "</td> " +
                                "            <td  style='border:1px solid grey;'>" + award.getAchName() + " " + "</td> " +
                                "            <td  style='border:1px solid grey;'>" + award.getAchUsersNo()+" " + "</td> " +
                                "            <td  style='border:1px solid grey;'> " +  award.getAchDateStart() + "~" + award.getAchDateEnd() + "</td> " +
                                "            <td  style='border:1px solid grey;'>" + award.getAchUseDate() +" " + "</td> " +
                                "            <td style='border:1px solid grey;'> " +  award.getAchPlanNo()   +  "</td> " +
                                "            <td style='border:1px solid grey;'> " + award.getAchEvidenceCode()   +  "</td> " +
                                "        </tr>  ";

            }

            teamLandmarkTable = teamLandmarkTableHeader +  appendStr +
                    "  </tbody> " +
                    " " +
                    "  </table>";
//            datas.put("teamLandmarkTable",getTableRenderData(teamLandRows));

        }


//////////////////发表论文专著情况////////////////////////////////////////////////////////////


        List<String> publishedPapersTitle = new ArrayList<>();
        publishedPapersTitle.add("序号");
        publishedPapersTitle.add("论文专著名称");
        publishedPapersTitle.add("影响因子");
        publishedPapersTitle.add("年卷页码（xx年xx卷xx页）");
        publishedPapersTitle.add("发表时间（xx年xx月xx日）");
        publishedPapersTitle.add("通讯作者");
        publishedPapersTitle.add("第一作者");
        publishedPapersTitle.add("国内作者（排序）");
        publishedPapersTitle.add("SCI他引次数");
        publishedPapersTitle.add("他引总次数");
        publishedPapersTitle.add("是否国内完成");
        publishedPapersTitle.add("证明材料编号");


        RowRenderData publishedPapersTitleHead = Rows.of(getRowHederList(publishedPapersTitle)).create();

        List<RowRenderData> teamPaperRows = new ArrayList<>();
        teamPaperRows.add(publishedPapersTitleHead);
        if (teamPaperInfos != null){
            for (int a = 0; a < teamPaperInfos.size(); a++) {
                EnterpriTeamPaperInfoDO award = teamPaperInfos.get(a);
                int num = a + 1;
                teamPaperRows.add(Rows.of(
                        num + "",
                        award.getPapername(),
                        award.getInfluencefactor(),
                        award.getYearnumber(),
                        award.getPublishtime(),
                        award.getCommunicationauthor(),
                        award.getFirstauthor(),
                        award.getDomesticauthor(),
                        award.getQuotenumber(),
                        award.getQuotetotalnumber(),
                        award.getWhetherdomesticcompleteStr(),
                        award.getMaterialcode()
                ).textColor("333333").textFontSize(10)
                        .textFontFamily("宋体")
                        .bgColor("FFFFFF").center().create());
            }
            datas.put("paperInfoTable", getTableRenderData(teamPaperRows));
        }

        //--------------------------学术交流情况--------------------------------
        List<String> academicExchangeTitleList = new ArrayList<>();
        academicExchangeTitleList.add("序号");
        academicExchangeTitleList.add("会议名称");
        academicExchangeTitleList.add("时间");
        academicExchangeTitleList.add("地点");
        academicExchangeTitleList.add("主办单位");


        RowRenderData academicExchangeHeadRow = Rows.of(getRowHederList(academicExchangeTitleList)).create();

        List<RowRenderData> academicExchangeRows = new ArrayList<>();
        academicExchangeRows.add(academicExchangeHeadRow);
        if (academicExchangeDOList != null){
            for (int a = 0; a < academicExchangeDOList.size(); a++) {
                EnterpriTeamAcademicExchangeDO award = academicExchangeDOList.get(a);
                int num = a + 1;
                academicExchangeRows.add(Rows.of(
                        num + "",
                        award.getMeetingname(),
                        award.getMeetingtime(),
                        award.getMeetingplace(),
                        award.getHostcompany()
                ).textColor("333333").textFontSize(10)
                        .textFontFamily("宋体")
                        .bgColor("FFFFFF").center().create());
            }
            datas.put("academicExchangeTable", getTableRenderData(academicExchangeRows));
        }

//-----------------------------------所获知识产权和技术标准情况-----附表四-----------------------------------------------------

        String acquiredTable  = "";

        List<String> acquiredRightsStr = new ArrayList<>();
        acquiredRightsStr.add("序号");
        acquiredRightsStr.add("知识产权类别");
        acquiredRightsStr.add("授权项目名称");
        acquiredRightsStr.add("国（区）别");
        acquiredRightsStr.add("授权号");
        acquiredRightsStr.add("授权日期");
        acquiredRightsStr.add("证书编号");
        acquiredRightsStr.add("权利人");
        acquiredRightsStr.add("发明人");
        acquiredRightsStr.add("所对应标志性成果");
        acquiredRightsStr.add("证明材料编号");


        RowRenderData acquiredRightsHead = Rows.of(getRowHederList(acquiredRightsStr)).create();
        List<RowRenderData> acquiredRightsRows = new ArrayList<>();
        acquiredRightsRows.add(acquiredRightsHead);
        if (intellectualPropertyList != null){
            String teamUndertableStr = " <table class=\"ui table\"> " +
                    "        <thead> " +
                    "        <th style='border:1px solid grey;'>序号</th> " +
                    "        <th style='border:1px solid grey;'>知识产权类别</th> " +
                    "        <th style='border:1px solid grey;'>授权项目名称</th> " +
                    "        <th style='border:1px solid grey;'>国（区）别</th> " +
                    "        <th style='border:1px solid grey;'>授权号</th> " +
                    "        <th style='border:1px solid grey;'>授权日期</th> " +
                    "        <th style='border:1px solid grey;'>证书编号</th> " +
                    "        <th style='border:1px solid grey;'>权利人</th> " +
                    "        <th style='border:1px solid grey;'>发明人</th> " +
                    "        <th style='border:1px solid grey;'>所对应标志性成果</th> " +
                    "        <th style='border:1px solid grey;'>证明材料编号</th> " +
                    "        </thead> " +
                    "        <tbody> "
                    ;
            String appendStr  = "";
            for (int a = 0; a < intellectualPropertyList.size(); a++) {
                EnterpriTeamIntellectualPropertyDO award = intellectualPropertyList.get(a);
                String keynotegainStr = CommonUtils.replaceHtmlTagP(award.getKeynotegain());
                String materialcodeStr = CommonUtils.replaceHtmlTagP(award.getMaterialcode());
                int num = a + 1;
                appendStr +=
                        "        <tr> " +  "<td  style='border:1px solid grey '> " + num +  "</td> " +
                                "            <td style='border:1px solid grey ' >" + award.getPropertytype() + " " + "</td> " +
                                "            <td  style='border:1px solid grey '>" + award.getPropertyname()+" " + "</td> " +
                                "            <td  style='border:1px solid grey ' > " + award.getCountry()+" " + "</td> " +
                                "            <td  style='border:1px solid grey;' >" + award.getAuthorizationnumber() +" " + "</td> " +
                                "            <td  style='border:1px solid grey '> " +  award.getAuthorizationtime()   +  "</td> " +
                                "            <td  style='border:1px solid grey '> " + award.getCertificatecode()   +  "</td> " +
                                "            <td  style='border:1px solid grey '> " + award.getObligee()  +  "</td> " +
                                "            <td  style='border:1px solid grey '> " + award.getInventor()  +  "</td> " +
                                "            <td  style='border:1px solid grey '> " + keynotegainStr  +  "</td> " +
                                "            <td style='border:1px solid grey '> " + materialcodeStr  +  "</td> " +
                                "        </tr>  ";

            }

            acquiredTable = teamUndertableStr +  appendStr +
                    "  </tbody> " +
                    " " +
                    "  </table>";
        }


        String teamUndertakes  = "";
//----------------------------------团队承担项目情况-----------附表五----------------------------------

        List<String> teamUndertakesStr = new ArrayList<>();
        teamUndertakesStr.add("序号");
        teamUndertakesStr.add("项目名称");
        teamUndertakesStr.add("研发经费（万元）");
        teamUndertakesStr.add("项目来源");
        teamUndertakesStr.add("项目编号");
        teamUndertakesStr.add("研发起止时间");
        teamUndertakesStr.add("状态（在研/已验收）");
        teamUndertakesStr.add("负责人");
        teamUndertakesStr.add("本团队主要成员中参与人/排序");
        teamUndertakesStr.add("证明材料编号");
        RowRenderData teamUndertakesHead = Rows.of(getRowHederList(teamUndertakesStr)).
                bgColor("FFFFFF").center().create();
        if (teamUndertakesList != null){
            String teamUndertableStr = " <table class=\"ui table\"> " +
                    "        <thead> " +
                    "        <th style='border:1px solid grey;'>序号</th> " +
                    "        <th style='border:1px solid grey;'>项目名称</th> " +
                    "        <th style='border:1px solid grey;'>研发经费（万元）</th> " +
                    "        <th style='border:1px solid grey;'>项目编号</th> " +
                    "        <th style='border:1px solid grey;'>研发起止时间</th> " +
                    "        <th style='border:1px solid grey;'>状态（在研/已验收）</th> " +
                    "        <th style='border:1px solid grey;'>负责人</th> " +
                    "        <th style='border:1px solid grey;'>本团队主要成员中参与人/排序</th> " +
                    "        <th style='border:1px solid grey;'>证明材料编号</th> " +
                    "        </thead> " +
                    "        <tbody> "
                    ;
            int num = 0;
            String appendStr  = "";
            for (int m = 0; m < teamUndertakesList.size(); m++) {
                EnterpriTeamProjectInfoDO award = teamUndertakesList.get(m);
                if (award == null){
                    continue;
                }
                num++;
                appendStr +=
                        "        <tr> " +  "<td  style='border:1px solid grey;'> " + num +  "</td> " +
                                "            <td style='border:1px solid grey;' >" + award.getProname() + " " + "</td> " +
                                "            <td  style='border:1px solid grey;'>" + award.getProsource()+" " + "</td> " +
                                "            <td style='border:1px solid grey;' > " + award.getProcode()+" " + "</td> " +
                                "            <td  style='border:1px solid grey;'>" + award.getState() +" " + "</td> " +
                                "            <td style='border:1px solid grey;'> " +  award.getStarttime() + "~" + award.getEndtime()  +  "</td> " +
                                "            <td style='border:1px solid grey;'> " + award.getState()   +  "</td> " +
                                "            <td style='border:1px solid grey;'> " + CommonUtils.replaceHtmlTagP(award.getResponsibilityname())  +  "</td> " +
                                "            <td style='border:1px solid grey;'> " + CommonUtils.replaceHtmlTagP(award.getMainmember())  +  "</td> " +
                                "            <td style='border:1px solid grey;'> " + CommonUtils.replaceHtmlTagP(award.getMaterialcode())  +  "</td> " +
                                "        </tr>  ";

            }


            teamUndertakes = teamUndertableStr +  appendStr +
            "  </tbody> " +
                    " " +
                    "  </table>";


        }


//-----------------------第三方评价表------附表六---------------------------------

        String thirdTablesStr  = "";

        List<String> thirdVoteStr = new ArrayList<>();
        thirdVoteStr.add("序号");
        thirdVoteStr.add("评价方");
        thirdVoteStr.add("被评价方");
        thirdVoteStr.add("评价要点");
        thirdVoteStr.add("证明材料编号");
        RowRenderData thirdHead = Rows.of(getRowHederList(thirdVoteStr)).bgColor("FFFFFF").center().create();

        String thirdTablesStrHeader = " <table class=\"ui table\"> " +
                "        <thead> " +
                "        <th style='border:1px solid grey;'> 序号</th> " +
                "        <th style='border:1px solid grey;'>评价方</th> " +
                "        <th style='border:1px solid grey;'>被评价方</th> " +
                "        <th style='border:1px solid grey;'>评价要点</th> " +
                "        <th  style='border:1px solid grey;'>证明材料编号</th> " +
                "        </thead> " +
                "        <tbody> "
                ;

        if (thirdList != null){
            String appendStr  = "";
            for (int a = 0; a < thirdList.size(); a++) {
                EnterpriTeamEvaluateOtherDO award = thirdList.get(a);
                int num = a + 1;

                appendStr +=
                        "        <tr> " +  "<td  style='border:1px solid grey;'> " + num +  "</td> " +
                                "            <td style='border:1px solid grey;' >" + award.getActiveevaluate() + " " + "</td> " +
                                "            <td style='border:1px solid grey;' >" + award.getPassiveevaluate()+" " + "</td> " +
                                "            <td  style='border:1px solid grey;'> " + CommonUtils.replaceHtmlTagP(award.getEvaluatemainpoints())+" " + "</td> " +
                                "            <td style='border:1px solid grey;'> " + award.getMaterialcode()  +  "</td> " +
                                "        </tr>  ";
            }
            thirdTablesStr = thirdTablesStrHeader +  appendStr +
                    "  </tbody> " +
                    " " +
                    "  </table>";
        }



//--------------------------团队曾获科技奖励情况-----附件七------------------------------
//
//

        String teamEverTablesStr  = "";

        List<String> teamEverReceiveStr = new ArrayList<>();
        teamEverReceiveStr.add("序号");
        teamEverReceiveStr.add("获奖对象");
        teamEverReceiveStr.add("获奖时间");
        teamEverReceiveStr.add("奖项名称");
        teamEverReceiveStr.add("奖励等级");
        teamEverReceiveStr.add("授奖部门（单位）");
        teamEverReceiveStr.add("团队主要成员中完成人/排名");
        teamEverReceiveStr.add("证明材料编号");

        String teamEverTablesStrHeader = " <table class=\"ui table\"> " +
                "        <thead> " +
                "        <th style='border:1px solid grey;'>序号</th> " +
                "        <th style='border:1px solid grey;'>获奖对象</th> " +
                "        <th style='border:1px solid grey;'>获奖时间</th> " +
                "        <th style='border:1px solid grey;'>奖项名称</th> " +
                "        <th style='border:1px solid grey;'>奖励等级</th> " +
                "        <th style='border:1px solid grey;'>授奖部门（单位）</th> " +
                "        <th style='border:1px solid grey;'>团队主要成员中完成人/排名</th> " +
                "        <th style='border:1px solid grey;'>证明材料编号</th> " +
                "        </thead> " +
                "        <tbody> "
                ;



        RowRenderData teamEverHead = Rows.of(getRowHederList(teamEverReceiveStr)).create();
        if (teamEverList != null){
            String appendStr  = "";
            for (int a = 0; a < teamEverList.size(); a++) {
                EnterpriTeamGetTechAwardDO award = teamEverList.get(a);
                int num = a + 1;

                appendStr +=
                        "        <tr> " +  "<td style='border:1px solid grey;' > " + num +  "</td> " +
                                "            <td style='border:1px solid grey;' >" + award.getAwardspeople() + " " + "</td> " +
                                "            <td  style='border:1px solid grey;'>" + award.getAwardstime()+" " + "</td> " +
                                "            <td  style='border:1px solid grey;'> " + award.getPrizename()+" " + "</td> " +
                                "            <td style='border:1px solid grey;'> " + award.getPrizelevel()  +  "</td> " +
                                "            <td style='border:1px solid grey;'> " + CommonUtils.replaceHtmlTagP(award.getEmpowerdepa())  +  "</td> " +
                                "            <td style='border:1px solid grey;'> " + CommonUtils.replaceHtmlTagP(award.getMainfulfilhumname())  +  "</td> " +
                                "            <td style='border:1px solid grey;'> " + CommonUtils.replaceHtmlTagP(award.getMaterialcode())  +  "</td> " +
                                "        </tr>  ";


            }
            teamEverTablesStr = teamEverTablesStrHeader +  appendStr +
                    "  </tbody> " +
                    " " +
                    "  </table>";
        }


//-----------------------------------------支持单位情况 teamSupportList--是一个列表----------------------------------------------------
        String teamSupportStr = "";

        if (teamSupport != null){
            String headerStringAppend = "<div class=\"ui container\">";
            String endtringAppend = "</div>";
            String infoAppend = "";

            for(EnterpriTeamSupportCompanyDO teamSupportCompanyDO: teamSupport) {
               String  supportInfo  = "  <table class=\"ui celled table\"> " +
                       "            <tbody> " +
                       "            <tr style='border:1px solid grey;'> " +
                       "                <td   style=\"width: 10%; border:1px solid grey; \">单位名称</td> " +
                       "                <td colspan=\"3\" style=\"width: 25%; border:1px solid grey;\">"+teamSupportCompanyDO.getComName()+"</td> " +
                       "                <td style=\"width: 10% ;border:1px solid grey;\">序号</td> " +
                       "                <td style=\"width: 25% ;border:1px solid grey; \">"+teamSupportCompanyDO.getComNum()+"</td>  " +
                       "            </tr> " +
                       "            <tr> " +
                       "                <td style=\"width: 10%; border:1px solid grey; \">所 在 地</td> " +
                       "                <td colspan=\"2\" style=\"width: 25% ; border:1px solid grey;\">"+teamSupportCompanyDO.getComAddress()+"</td> " +
                       "                <td colspan=\"2\"style=\"width: 10% ; border:1px solid grey;\">是否主要支持单位</td> " +
                       "                <td style=\"width: 25% ; border:1px solid grey; \">"+teamSupportCompanyDO.getComIsMainSupport()+"</td>  " +
                       "            </tr> " +
                       " " +
                       "              <tr style='border:1px solid grey;' > " +
                       "                <td style=\"width: 10% ; border:1px solid grey;\">单位性质</td> " +
                       "                <td style=\"width: 25% ; border:1px solid grey;\">"+teamSupportCompanyDO.getComNature()+"</td> " +
                       "                <td style=\"width: 10% ; border:1px solid grey;\">电子信箱</td> " +
                       "                <td style=\"width: 25% ; border:1px solid grey;\">"+teamSupportCompanyDO.getComEmail()+"</td> " +
                       "                <td style=\"width: 10% ; border:1px solid grey;\">传    真</td> " +
                       "                <td style=\"width: 20% ; border:1px solid grey;\">"+teamSupportCompanyDO.getComFox()+"</td> " +
                       "            </tr> " +
                       " " +
                       "            <tr style='border:1px solid grey;' > " +
                       "                <td style=\"width: 10% ;border:1px solid grey; \">联 系 人</td> " +
                       "                <td style=\"width: 25% ; border:1px solid grey;\">"+teamSupportCompanyDO.getComConcat()+"</td> " +
                       "                <td style=\"width: 10% ; border:1px solid grey;\">单位电话</td> " +
                       "                <td style=\"width: 25% ; border:1px solid grey;\">"+teamSupportCompanyDO.getComTelphone()+"</td> " +
                       "                <td style=\"width: 10% ; border:1px solid grey;\">移动电话</td> " +
                       "                <td style=\"width: 20% ; border:1px solid grey; \">"+teamSupportCompanyDO.getComMobilePhone()+"</td> " +
                       "            </tr> " +
                       " " +
                       "              <tr style='border:1px solid grey;'> " +
                       "                <td style=\"width: 10% ; border:1px solid grey; \">法定代表人</td> " +
                       "                <td style=\"width: 25% ; border:1px solid grey; \">"+teamSupportCompanyDO.getComLegalPerson()+"</td> " +
                       "                <td style=\"width: 10% ; border:1px solid grey; \">单位电话</td> " +
                       "                <td style=\"width: 25% ; border:1px solid grey; \">"+teamSupportCompanyDO.getComConcatTelphone()+"</td> " +
                       "                <td style=\"width: 10% ; border:1px solid grey; \">移动电话</td> " +
                       "                <td style=\"width: 20% ; border:1px solid grey; \">"+teamSupportCompanyDO.getComConcatMobilePhone()+"</td> " +
                       "            </tr> " +
                       " " +
                       "            <tr style='border:1px solid grey;' > " +
                       "                <td style=\"width: 10% ; border:1px solid grey; \">通讯地址</td> " +
                       "                <td style=\"width: 25% ; border:1px solid grey; \" colspan=\"3\">"+teamSupportCompanyDO.getComConcatAddress()+"</td> " +
                       "                <td style=\"width: 10% ; border:1px solid grey; \">邮政编码</td> " +
                       "                <td style=\"width: 20% ; border:1px solid grey; \">"+teamSupportCompanyDO.getComPostcode()+"</td> " +
                       "            </tr> " +
                       "            <tr style='border:1px solid grey;' > " +
                       "                <td colspan=\"7\" style='height: 500px;   border:1px solid grey;' valign=\"top\"> " +
                       "                    对团队发展的贡献： " +teamSupportCompanyDO.getComContribution()+
                       "                </td> " +
                       "            </tr> " +
                       "            </tbody> " +
                       "        </table>";

                infoAppend = infoAppend + supportInfo; 
            }

            teamSupportStr = headerStringAppend +infoAppend +  endtringAppend;
        }



        //----------------------------------------- 主要成员情况 带头人 EnterpriTeamLeaderInfoDO----------------------------------------------------



        List<Map<String,Object>> teamInfoMainList = new ArrayList<>();
        String teamInfoMainListStr = "" ;

        if (teamMainUers != null){

            String headerStringAppend = "<div class=\"ui container\">";
            String endtringAppend = "</div>";
            String senterStr = "";
            for(EnterpriTeamLeaderInfoDO teamSupportCompanyDO: teamMainUers) {
                String teamInfoMainListTable = "<table class=\"ui celled table\"> " +
                        "            <tbody> " +
                        "            <tr style='border:1px solid grey;'> " +
                        "                <td style='width: 10% ; border:1px solid grey;'>姓名</td> " +
                        "                <td colspan=\"2\" style='width: 20% ; border:1px solid grey;'>"+teamSupportCompanyDO.getName()+"</td> " +
                        "                <td style='width: 10% ; border:1px solid grey;'>性别</td> " +
                        "                <td style='border:1px solid grey;' >"+teamSupportCompanyDO.getGender()+"</td> " +
                        "                <td style='width: 10% ; border:1px solid grey;'>序号</td> " +
                        "                <td style='border:1px solid grey;' >"+teamSupportCompanyDO.getSerialnumberStr()+"</td> " +
                        "            </tr> " +
                        "            <tr style='border:1px solid grey;'> " +
                        "                <td style='border:1px solid grey;'>出生年月</td> " +
                        "                <td style='border:1px solid grey;'  colspan=\"2\">" + formatDate(teamSupportCompanyDO.getBirthtime())+ "</td> " +
                        "                <td style='border:1px solid grey;' >出 生 地</td> " +
                        "                <td style='border:1px solid grey;' >"+ teamSupportCompanyDO.getBirthaddress() +"</td> " +
                        "                <td style='border:1px solid grey;'>民    族</td> " +
                        "                <td style='border:1px solid grey;'>"+ teamSupportCompanyDO.getNation() +"</td> " +
                        "            </tr> " +
                        "            <tr style='border:1px solid grey;'> " +
                        "                <td style='border:1px solid grey;' >身份证号</td> " +
                        "                <td style='border:1px solid grey;' colspan=\"2\">"+teamSupportCompanyDO.getIdentitynumber() +"</td> " +
                        "                <td style='border:1px solid grey;' >是否归国人员</td> " +
                        "                <td style='border:1px solid grey;' >"+ teamSupportCompanyDO.getWhetherreturntochina() +"</td> " +
                        "                <td style='border:1px solid grey;' >归国时间</td> " +
                        "                <td style='border:1px solid grey;' >"+formatDate(teamSupportCompanyDO.getReturnhometime())+"</td> " +
                        "            </tr> " +
                        "            <tr style='border:1px solid grey;'> " +
                        "                <td style='border:1px solid grey;' >技术职称</td> " +
                        "                <td style='border:1px solid grey;' colspan=\"2\">"+teamSupportCompanyDO.getTechnologytitle()+"</td> " +
                        "                <td style='border:1px solid grey;'>最高学历</td> " +
                        "                <td style='border:1px solid grey;' >"+ teamSupportCompanyDO.getEducation() +"</td> " +
                        "                <td style='border:1px solid grey;' >最高学位</td> " +
                        "                <td style='border:1px solid grey;'>"+teamSupportCompanyDO.getAcademicdegree()+"</td> " +
                        "            </tr> " +
                        "            <tr style='border:1px solid grey;'> " +
                        "                <td style='border:1px solid grey;'>毕业学校</td> " +
                        "                <td style='border:1px solid grey;' colspan=\"2\">"+teamSupportCompanyDO.getGraduateschool()+"</td> " +
                        "                <td style='border:1px solid grey;' >毕业时间</td> " +
                        "                <td style='border:1px solid grey;'>"+formatDate(teamSupportCompanyDO.getGraduationtime()) +"</td> " +
                        "                <td style='border:1px solid grey;'>所学专业</td> " +
                        "                <td style='border:1px solid grey;'>"+teamSupportCompanyDO.getMajor()+"</td> " +
                        "            </tr> " +
                        "            <tr style='border:1px solid grey;'> " +
                        "                <td style='border:1px solid grey;'>电子邮箱</td> " +
                        "                <td style='border:1px solid grey;' colspan=\"2\">"+teamSupportCompanyDO.getMailbox()+"</td> " +
                        "                <td style='border:1px solid grey;' >办公电话</td> " +
                        "                <td style='border:1px solid grey;'>"+teamSupportCompanyDO.getTelephone()+"</td> " +
                        "                <td style='border:1px solid grey;'>移动电话</td> " +
                        "                <td style='border:1px solid grey;'>"+teamSupportCompanyDO.getMobilephone()+"</td> " +
                        "            </tr> " +
                        "            <tr style='border:1px solid grey;'> " +
                        "                <td style='border:1px solid grey;'>通讯地址</td> " +
                        "                <td style='border:1px solid grey;' colspan=\"4\">"+teamSupportCompanyDO.getContactaddress()+"</td> " +
                        "                <td style='border:1px solid grey;' >邮政编码</td> " +
                        "                <td style='border:1px solid grey;'>"+teamSupportCompanyDO.getPostcode()+"</td> " +
                        "            </tr> " +
                        "            <tr style='border:1px solid grey;'> " +
                        "                <td style='border:1px solid grey;'>工作单位</td> " +
                        "                <td style='border:1px solid grey;' colspan=\"4\">"+teamSupportCompanyDO.getWorkcompany()+"</td> " +
                        "                <td style='border:1px solid grey;'>行政职务</td> " +
                        "                <td style='border:1px solid grey;'>"+teamSupportCompanyDO.getAdministrationpost()+"</td> " +
                        "            </tr> " +
                        "            <tr style='border:1px solid grey;'> " +
                        "                <td style='border:1px solid grey;'>二级单位</td> " +
                        "                <td style='border:1px solid grey;' colspan=\"4\">"+teamSupportCompanyDO.getSecondlevelcompany()+"</td> " +
                        "                <td style='border:1px solid grey;'>党  派</td> " +
                        "                <td style='border:1px solid grey;' >"+teamSupportCompanyDO.getParties()+"</td> " +
                        "            </tr> " +
                        "            <tr style='border:1px solid grey;'> " +
                        "                <td  style='border:1px solid grey;width: 20%  ' colspan=\"2\">参加本团队的起止时间</td> " +

                        "                <td  style='border:1px solid grey;' colspan=\"5\">"+ (teamSupportCompanyDO.getAttendstarttime() != null ? (formatDate(teamSupportCompanyDO.getAttendstarttime())+ "~" +formatDate(teamSupportCompanyDO.getAttendendtime())) : "无") +"</td> " +
                        "            </tr> " +
                        "            <tr style='border:1px solid grey;'> " +
                        "                <td style='border:1px solid grey;' colspan=\"2\">主要学习经历</td> " +
                        "                <td style='border:1px solid grey;'  colspan=\"5\">"+teamSupportCompanyDO.getEducationundergo()+"</td> " +
                        "            </tr> " +
                        "            <tr style='border:1px solid grey;'> " +
                        "                <td style='border:1px solid grey;' colspan=\"2\">科研工作经历</td> " +
                        "                <td style='border:1px solid grey;' colspan=\"5\">"+teamSupportCompanyDO.getWorkundergo()+"</td> " +
                        "            </tr> " +
                        "            <tr style='border:1px solid grey;'> " +
                        "                <td style='border:1px solid grey;' colspan=\"2\">主要学术兼职</td> " +
                        "                <td style='border:1px solid grey;' colspan=\"5\">"+teamSupportCompanyDO.getLearningconcurrent()+"</td> " +
                        "            </tr> " +
                        "            <tr style='border:1px solid grey;'> " +
                        "                <td style='border:1px solid grey;' colspan=\"2\">代表性成果</td> " +
                        "                <td style='border:1px solid grey;' colspan=\"5\">"+teamSupportCompanyDO.getDeputyfruit()+"</td> " +
                        "            </tr> " +
                        "            <tr style='border:1px solid grey;'> " +
                        "                <td style='border:1px solid grey;' colspan=\"2\">代表性论文和著作获奖励情况</td> " +
                        "                <td style='border:1px solid grey;' colspan=\"5\">"+teamSupportCompanyDO.getRewardsituation()+"</td> " +
                        "            </tr> " +
                        "            <tr style='border:1px solid grey;'> " +
                        "                <td style='border:1px solid grey;height: 500px;'  colspan=\"7\"  valign=\"top\"> " +
                        "                    对团队发展的贡献： " +teamSupportCompanyDO.getContribution()+
                        "                </td> " +
                        "            </tr> " +
                        "            </tbody> " +
                        "        </table>";
                senterStr = senterStr + teamInfoMainListTable;
            }
              teamInfoMainListStr = headerStringAppend + senterStr + endtringAppend;

        }





        List<Map<String,Object>> teamInfoOtherList = new ArrayList<>();
        String teamInfoOtherListStr = "" ;

        if (oteamUers != null){
            String headerStringAppend = "<div class=\"ui container\">";
            String endtringAppend = "</div>";
            String senterStr = "";
            for(EnterpriTeamOtherMainDO teamSupportCompanyDO: oteamUers) {
               senterStr += "  <table class=\"ui celled table\"> " +
                       "            <tbody> " +
                       "            <tr style='border:1px solid grey;' > " +
                       "                <td style='border:1px solid grey; width: 10%'  >姓名</td> " +
                       "                <td colspan=\"2\" style='border:1px solid grey; width: 20%'  >"+teamSupportCompanyDO.getName()+"</td> " +
                       "                <td  style='border:1px solid grey; width: 10%' >性别</td> " +
                       "                <td style='border:1px solid grey;  '  >"+teamSupportCompanyDO.getGender()+"</td> " +
                       "                <td   style='border:1px solid grey; width: 10%' >序号</td> " +
                       "                <td style='border:1px solid grey '  >"+teamSupportCompanyDO.getSerialnumber()+"</td> " +
                       "            </tr> " +
                       "            <tr style='border:1px solid grey;'> " +
                       "                <td style='border:1px solid grey ' >出生年月</td> " +
                       "                <td colspan=\"2\" style='border:1px solid grey ' >"+formatDate(teamSupportCompanyDO.getBirthtime())+"</td> " +
                       "                <td style='border:1px solid grey ' >出 生 地</td> " +
                       "                <td style='border:1px solid grey ' >"+teamSupportCompanyDO.getBirthaddress()+"</td> " +
                       "                <td style='border:1px solid grey '  >民    族</td> " +
                       "                <td style='border:1px solid grey ' >"+teamSupportCompanyDO.getNation()+"</td> " +
                       "            </tr> " +
                       "            <tr style='border:1px solid grey;'> " +
                       "                <td  style='border:1px solid grey;'>身份证号</td> " +
                       "                <td style='border:1px solid grey;' colspan=\"2\">"+teamSupportCompanyDO.getIdentitynumber()+"</td> " +
                       "                <td style='border:1px solid grey;'>是否归国人员</td> " +
                       "                <td style='border:1px solid grey;' >"+teamSupportCompanyDO.getWhetherreturntochina()+"</td> " +
                       "                <td style='border:1px solid grey;' >归国时间</td> " +
                       "                <td style='border:1px solid grey;'>"+formatDate(teamSupportCompanyDO.getReturnhometime())+"</td> " +
                       "            </tr> " +
                       "            <tr  style='border:1px solid grey;'> " +
                       "                <td style='border:1px solid grey;'>技术职称</td> " +
                       "                <td style='border:1px solid grey;' colspan=\"2\">"+teamSupportCompanyDO.getTechnologytitle()+"</td> " +
                       "                <td  style='border:1px solid grey;' >最高学历</td> " +
                       "                <td style='border:1px solid grey;' >"+teamSupportCompanyDO.getEducation()+"</td> " +
                       "                <td style='border:1px solid grey;'>最高学位</td> " +
                       "                <td style='border:1px solid grey;'>"+teamSupportCompanyDO.getAcademicdegree()+"</td> " +
                       "            </tr> " +
                       "            <tr style='border:1px solid grey;'> " +
                       "                <td style='border:1px solid grey;'>毕业学校</td> " +
                       "                <td style='border:1px solid grey;' colspan=\"2\">"+teamSupportCompanyDO.getGraduateschool()+"</td> " +
                       "                <td style='border:1px solid grey;'> 毕业时间</td> " +
                       "                <td  style='border:1px solid grey;' >"+formatDate(teamSupportCompanyDO.getGraduationtime())+"</td> " +
                       "                <td style='border:1px solid grey;'>所学专业</td> " +
                       "                <td style='border:1px solid grey;'>"+teamSupportCompanyDO.getMajor()+"</td> " +
                       "            </tr> " +
                       "            <tr style='border:1px solid grey;'> " +
                       "                <td style='border:1px solid grey;'>电子邮箱</td> " +
                       "                <td style='border:1px solid grey;' colspan=\"2\">"+teamSupportCompanyDO.getMailbox()+"</td> " +
                       "                <td style='border:1px solid grey;'>办公电话</td> " +
                       "                <td style='border:1px solid grey;'>"+teamSupportCompanyDO.getTelephone()+"</td> " +
                       "                <td style='border:1px solid grey;'>移动电话</td> " +
                       "                <td style='border:1px solid grey;'>"+teamSupportCompanyDO.getMobilephone()+"</td> " +
                       "            </tr> " +
                       "            <tr style='border:1px solid grey;'> " +
                       "                <td  style='border:1px solid grey;'>通讯地址</td> " +
                       "                <td style='border:1px solid grey;' colspan=\"4\">"+teamSupportCompanyDO.getContactaddress()+"</td> " +
                       "                <td style='border:1px solid grey;' >邮政编码</td> " +
                       "                <td style='border:1px solid grey;' >"+teamSupportCompanyDO.getPostcode()+"</td> " +
                       "            </tr> " +
                       "            <tr style='border:1px solid grey;'> " +
                       "                <td style='border:1px solid grey;'>工作单位</td> " +
                       "                <td style='border:1px solid grey;' colspan=\"4\">"+teamSupportCompanyDO.getWorkcompany()+"</td> " +
                       "                <td style='border:1px solid grey;'>行政职务</td> " +
                       "                <td style='border:1px solid grey;'>"+teamSupportCompanyDO.getAdministrationpost()+"</td> " +
                       "            </tr> " +
                       "            <tr style='border:1px solid grey;'> " +
                       "                <td style='border:1px solid grey;'>二级单位</td> " +
                       "                <td style='border:1px solid grey;' colspan=\"4\">"+teamSupportCompanyDO.getSecondlevelcompany()+"</td> " +
                       "                <td style='border:1px solid grey;'>党  派</td> " +
                       "                <td style='border:1px solid grey;'>"+teamSupportCompanyDO.getParties()+"</td> " +
                       "            </tr> " +
                       "            <tr style='border:1px solid grey;'> " +
                       "                <td style='border:1px solid grey; width: 20%'  colspan=\"2\"  >参加本团队的起止时间</td> " +
                       "                <td style='border:1px solid grey;' colspan=\"3\">"+ (teamSupportCompanyDO.getAttendstarttime() != null ? (formatDate(teamSupportCompanyDO.getAttendstarttime()) +"~" + formatDate(teamSupportCompanyDO.getAttendendtime())) : "无")+"</td> " +
                       "                <td style='border:1px solid grey;'>团队内职务</td> " +
                       "                <td  style='border:1px solid grey;'>"+teamSupportCompanyDO.getRewardsituation()+"</td> " +
                       "            </tr> " +
                       "            <tr style='border:1px solid grey;'> " +
                       "                <td style='border:1px solid grey;   height: 500px;'   colspan=\"7\"   valign=\"top\"> " +
                       "                    对团队发展的贡献： " +teamSupportCompanyDO.getContribution() +
                       "                </td> " +
                       "            </tr> " +
                       "            </tbody> " +
                       "        </table>";


             }
            teamInfoOtherListStr = headerStringAppend + senterStr + endtringAppend;
        }


        //团队合作汇总表

        Map<String,List<EnterpriTeamCooperationDO>> cooperationMap = new HashMap<>();
        if (cooperationDOList != null){
            for(EnterpriTeamCooperationDO intrductionDO:cooperationDOList) {
                String type = intrductionDO.getType();
                List<EnterpriTeamCooperationDO> cooperationList = cooperationMap.get(type);
                if(cooperationList == null) {
                    cooperationList = new ArrayList<>();
                    cooperationMap.put(type, cooperationList);
                }
                cooperationList.add(intrductionDO);
            }
        }
        datas.put("teamCooperationTable", cooperationMap);

        Configure config = Configure.builder()
                .bind("teamSupport", policy)
                .bind("teamInfoMainList", policy)
                .bind("teamInfoOtherList", policy)
                .bind("teamCooperationTable", new PoiWordScienceAwardTeamCooperationInfoTablePolicy())
                .bind("mainUserPoiData", new PoiWordScienceAwardTeamMainUserTablePolicy())

                .bind("teamInfoTeamDes", htmlRenderPolicy)
                .bind("teamBuildInfo", htmlRenderPolicy)
                .bind("teamInnovate", htmlRenderPolicy)
                .bind("teamSocial", htmlRenderPolicy)
                .bind("teamDevelopment", htmlRenderPolicy)

                .bind("teamUndertakes", htmlRenderPolicy)
                .bind("acquiredTable", htmlRenderPolicy)
                .bind("teamLandmarkTable", htmlRenderPolicy)
                .bind("thirdTablesStr", htmlRenderPolicy)
                .bind("teamEverTablesStr", htmlRenderPolicy)
                .bind("teamInfoMainListStr", htmlRenderPolicy)
                .bind("teamInfoOtherListStr", htmlRenderPolicy)
                .bind("teamSupportStr", htmlRenderPolicy)



                .build();

         
        String mainCompleteUserInfoStr =  ((DocTeamApplyWord) params).getTeamIntrductionDO().getTeamDes(); // 团队简介
        String teamBuildInfoStr =  ((DocTeamApplyWord) params).getTeamIntrductionDO().getTeamBuildInfo(); // 团队简介
        String teamInnovateStr =  ((DocTeamApplyWord) params).getTeamIntrductionDO().getTeamInnovate(); // 团队简介
        String teamSocialStr =  ((DocTeamApplyWord) params).getTeamIntrductionDO().getTeamSocial(); // 团队简介
        String teamDevelopmentStr =  ((DocTeamApplyWord) params).getTeamIntrductionDO().getTeamDevelopment(); // 团队简介
        datas.put("teamInfoTeamDes", mainCompleteUserInfoStr == null ? "" : mainCompleteUserInfoStr);
        datas.put("teamBuildInfo", teamBuildInfoStr == null ? "" : teamBuildInfoStr);
        datas.put("teamInnovate", teamInnovateStr == null ? "" : teamInnovateStr);
        datas.put("teamSocial", teamSocialStr == null ? "" : teamSocialStr);
        datas.put("teamDevelopment", teamDevelopmentStr == null ? "" : teamDevelopmentStr);
        datas.put("acquiredTable", acquiredTable == null ? "" : acquiredTable);
        datas.put("teamLandmarkTable", teamLandmarkTable == null ? "" : teamLandmarkTable);
        datas.put("thirdTablesStr", thirdTablesStr == null ? "" : thirdTablesStr);
        datas.put("teamInfoMainListStr", teamInfoMainListStr == null ? "" : teamInfoMainListStr);
        datas.put("teamInfoOtherListStr", teamInfoOtherListStr == null ? "" : teamInfoOtherListStr);
        datas.put("teamUndertakes", teamUndertakes == null ? "" : teamUndertakes);
        datas.put("teamEverTablesStr", teamEverTablesStr == null ? "" : teamEverTablesStr);
        datas.put("teamSupportStr", teamSupportStr == null ? "" : teamSupportStr);




        XWPFTemplate template = XWPFTemplate.compile(templatePath, config).render(datas);

        FileOutputStream out = new FileOutputStream(createWordPath);
        template.write(out);
        out.flush();
        out.close();

    }

    /**
     * 获取团队合作情况的单个类型行数据
     * @param type
     * @param cooperationDOList
     * @return
     */
    public static List<RowRenderData> getTeamCooperationTypeRows(String type, List<EnterpriTeamCooperationDO> cooperationDOList) {
        List<RowRenderData> teamCooperationRows = new ArrayList<>();

        if(cooperationDOList == null || cooperationDOList.size() == 0) {
            for(int i=0; i<3; i++) {
                teamCooperationRows.add(Rows.of(
                        i == 0 ? type : "",
                        "",
                        "",
                        ""
                ).textFontSize(10)
                        .textFontFamily("宋体")
                        .bgColor("FFFFFF").center().create());
            }
            return teamCooperationRows;
        }

        for (int a = 0; a < cooperationDOList.size(); a++) {
            EnterpriTeamCooperationDO cooperationDO = cooperationDOList.get(a);
            teamCooperationRows.add(Rows.of(
                    a == 0 ? type : "",
                    cooperationDO.getAchievementsname(),
                    cooperationDO.getCooperationpeopleNo(),
                    cooperationDO.getRemarksNo()
            ).textFontSize(10)
                    .textFontFamily("宋体")
                    .bgColor("FFFFFF").center().create());
        }
        return teamCooperationRows;
    }


    /****
     *
     * @param templatePath
     * @param createWordPath
     * @param params
     * @param listKowleges 成果主要知识产权证明目录
     * @param otherInfoData
     * @throws IOException
     */
    public static void createScicenceWord(String templatePath, String createWordPath,
                                          DocBaseDataWord params,List<EnterpriseScienceAwardKnowledgeInfoDO> listKowleges,
                                          EnterpriseChengguoOtherInfoDO otherInfoData) throws IOException {
        Map<String, Object> datas = new HashMap<String, Object>();


        datas.put("params", params);

        DocCompanyApplyWord   companyWorld = (DocCompanyApplyWord) params;


        datas.put("createDate", ((DocCompanyApplyWord) params).getpInfo().getCreated());
        String applyEnters =  companyWorld.getpInfo().getApplyEnterprise();
        //-----------------------申报单位------------------------------------
        List<RowRenderData> applyRows = new ArrayList<>();

        if (applyEnters.contains(",")){
            String[] strings = null;
            if(applyEnters.startsWith("[") && applyEnters.endsWith("]")) {
                List<String> entList = Constant.gson.fromJson(applyEnters, List.class);
                strings = entList.toArray(new String[0]);
            }else {
                strings = applyEnters.split(",");
            }
            int length =  strings.length ;
            for (int a = 0 ; a < length ;a++){
                RowRenderData ro01 = Rows.of( strings[a]).textFontFamily("宋体").textFontSize(11).create();
                applyRows.add(ro01);
            }
        }else if(applyEnters.startsWith("[") && applyEnters.endsWith("]")) {
            RowRenderData ro01 = Rows.of(applyEnters.substring(2,applyEnters.length() - 2)).textFontFamily("宋体").textFontSize(11).create();
            applyRows.add(ro01);
        } else {
            RowRenderData ro01 = Rows.of(applyEnters).textFontFamily("宋体").textFontSize(11).create();
            applyRows.add(ro01);
        }

        datas.put("applyTable", getTableRenderData(applyRows));

        //--------------------主要完成单位-------------------------------------




        List<RowRenderData> mainRows = new ArrayList<>();

        String mainEnters =  companyWorld.getpInfo().getMasterCompleteEnterprise();

        if (mainEnters != null){
            if (mainEnters != null && mainEnters.contains(",")){
                String[] strings = null;
                if(mainEnters.startsWith("[") && mainEnters.endsWith("]")) {
                    List<String> mainList = Constant.gson.fromJson(mainEnters, List.class);
                    strings = mainList.toArray(new String[0]);
                }else {
                    strings = mainEnters.split(",");
                }
                int length =  strings.length ;
                for (int a = 0 ; a < length ;a++){
                    RowRenderData ro01 = Rows.of( strings[a]).textFontFamily("宋体").textFontSize(11).create();
                    mainRows.add(ro01);
                }
            } else if(mainEnters.startsWith("[") && mainEnters.endsWith("]")) {
                if (mainEnters.length() > 2){
                    RowRenderData ro01 = Rows.of(mainEnters.substring(2,mainEnters.length() - 2)).textFontFamily("宋体").textFontSize(11).create();
                    mainRows.add(ro01);
                }


            }  else {
                RowRenderData ro01 = Rows.of(mainEnters).textFontFamily("宋体").textFontSize(11).create();
                mainRows.add(ro01);
            }

            datas.put("mainTable", getTableRenderData(mainRows));

        }





//  -----------------------六、成果主要知识产权证明目录------------------------------------
        List<String> listKowlegesStr = new ArrayList<>();
        listKowlegesStr.add("知识产权类别");
        listKowlegesStr.add("知识产权名称");
        listKowlegesStr.add("国家（地区）");
        listKowlegesStr.add("授权号");
        listKowlegesStr.add("授权日期");
        listKowlegesStr.add("证书编号");
        listKowlegesStr.add("权利人");
        listKowlegesStr.add("发明人");
        listKowlegesStr.add("专利有效状态");

        RowRenderData header = Rows.of(getRowHederList(listKowlegesStr)).textFontSize(12).bgColor("FFFFFF").center().create();
        List<RowRenderData> knowledgeRows = new ArrayList<>();
        knowledgeRows.add(header);
        for (int a = 0; a < listKowleges.size(); a++) {
            EnterpriseScienceAwardKnowledgeInfoDO award = listKowleges.get(a);
            knowledgeRows.add(Rows.of(
                    award.getKType(),
                    award.getKName(),
                    award.getKArea(),
                    award.getKAuthCode(),
                    award.getKAuthDate(),
                    award.getKBookCode(),
                    award.getKRightUser(),
                    award.getKCreateUser(),
                    award.getKEffectiveStat()).textFontSize(12).bgColor("FFFFFF").create());
        }


        datas.put("knowledgeTable",  getTableRenderData(knowledgeRows));

        //=============================================
        //        单位名称
        List<Map<String,Object>> otherInfoDataList = new ArrayList<>();
        List<EnterpriseChengguoOtherInfoDO> mainCompleteInfoList = otherInfoData.getMainCompleteInfoList();
        if (mainCompleteInfoList != null){
            for(EnterpriseChengguoOtherInfoDO otherInfoDO: mainCompleteInfoList) {
                Map<String,Object> otherData = new HashMap<>();
                otherData.put("comName", getTextRenderData(otherInfoDO.getMainEnterpriseCompanyName()));
                otherData.put("name", getTextRenderData(otherInfoDO.getMainEnterpriseCompleter()));
                otherData.put("sex", getTextRenderData(otherInfoDO.getMainEnterpriseCompleterGender()));
                otherData.put("order", getTextRenderData(otherInfoDO.getMainEnterpriseCompleterSort() + ""));
                otherData.put("birth", getTextRenderData(otherInfoDO.getMainEnterpriseCompleterBirth()));
                otherData.put("userId", getTextRenderData(otherInfoDO.getMainEnterpriseCompleterId()));
                otherData.put("nation", getTextRenderData(otherInfoDO.getMainEnterpriseCompleterNation()));
                otherData.put("country", getTextRenderData(otherInfoDO.getMainEnterpriseCompleterCountry()));
                otherData.put("education", getTextRenderData(otherInfoDO.getMainEnterpriseCompleterEducation()));
                otherData.put("adminOfficePost", getTextRenderData(otherInfoDO.getMainEnterpriseCompleterAdministrativePost()));
                otherData.put("technical", getTextRenderData(otherInfoDO.getMainEnterpriseCompleterTechnicalTitle()));
                otherData.put("major", getTextRenderData(otherInfoDO.getMainEnterpriseCompleterMajorWork()));
                otherData.put("majorStudy", getTextRenderData(otherInfoDO.getMainEnterpriseCompleterMajorStudy()));
                otherData.put("workTel", getTextRenderData(otherInfoDO.getMainEnterpriseCompleterWorkTelphone()));
                otherData.put("workMobile", getTextRenderData(otherInfoDO.getMainEnterpriseCompleterWorkMobile()));
                otherData.put("workEmail", getTextRenderData(otherInfoDO.getMainEnterpriseCompleterWorkEmail()));
                otherData.put("workAddress", getTextRenderData(otherInfoDO.getMainEnterpriseCompleterWorkAddress()));
                otherData.put("workCode", getTextRenderData(otherInfoDO.getMainEnterpriseCompleterWorkAddrCode()));
                otherData.put("workStartEnd", getTextRenderData(otherInfoDO.getMainEnterpriseCompleterPartakeTime()));
                otherData.put("workFor", getTextRenderData(otherInfoDO.getMainEnterpriseCompleterAcademicContribution()));
                otherData.put("workContributePro", getTextRenderData(otherInfoDO.getMainEnterpriseContribution()));
                otherInfoDataList.add(otherData);
            }
        }



        datas.put("otherInfoDataList", otherInfoDataList);

//---------------------------------------团队合作情况汇总------------------------------------------------------


// 标志性成果
// 发表论文专著情况${otherInfo.chengguoDes}
// 所获知识产权和技术标准情况
// 团队承担项目及科研经费情况
// 团队曾获科技奖励情况 EnterpriTeamCooperationDO
// 所获知识产权和技术标准情况

////////////////////////////////////////成员基本情况---->>>> 主要完成人员///////////////////////////////////////////////////////////////////
        List<String> teamCooperStrs = new ArrayList<>();
        teamCooperStrs.add("类别");
        teamCooperStrs.add("成果名称");
        teamCooperStrs.add("本团队中合作者/排序");
        teamCooperStrs.add("备注");

        RowRenderData teamCoopersheader = Rows.of(getRowHederList(teamCooperStrs)).create();

        ListRenderPolicy policy = new ListRenderPolicy();
        HtmlRenderPolicy htmlRenderPolicy = new HtmlRenderPolicy();
        HtmlRenderPolicy htmlRenderPolicy1 = new HtmlRenderPolicy();

        Configure config = Configure.builder()
                .bind("otherInfoDataList", policy)
                .bind("mainCompleteUserInfo", htmlRenderPolicy)
                .bind("taskSourceExt", htmlRenderPolicy)
                .bind("planDetalNameCodeExt", htmlRenderPolicy1)
                .bind("departmentAwardInfoExt", htmlRenderPolicy)
                .bind("enterpriseOpinionExt", htmlRenderPolicy)
                .bind("chengguoDesExt", htmlRenderPolicy)
                .bind("mainTechnologicalInnovationExt", htmlRenderPolicy)
                .bind("thirdOpinionExt", htmlRenderPolicy)
                .bind("chengguoStandardExt", htmlRenderPolicy)


                .build();
        String mainCompleteUserInfoStr =  ((DocCompanyApplyWord) params).getpInfo().getMainCompleteUsers(); // 主要完成人员
        String taskSourceStr =  ((DocCompanyApplyWord) params).getpInfo().getTaskSource(); // 任务来源
        System.out.println("taskSourceStr ================" + taskSourceStr);
        String planDetalNameCodeStr =  ((DocCompanyApplyWord) params).getpInfo().getPlanDetalNameCode(); // 具体计划、基金的名称及编号
        String departmentAwardInfoStr =  ((DocCompanyApplyWord) params).getpInfo().getDepartmentAwardInfo(); // 本成果曾获何部门何种奖励，奖励等级
        String enterpriseOpinionStr =  ((DocCompanyApplyWord) params).getpInfo().getEnterpriseOpinion(); // 本成果曾获何部门何种奖励，奖励等级
        String chengguoDesStr =  ((DocCompanyApplyWord) params).getoInfo().getChengguoDes(); //
        String mainTechnologicalInnovationStr =  ((DocCompanyApplyWord) params).getoInfo().getMainTechnologicalInnovation(); //
        String thirdOpinionStr =  ((DocCompanyApplyWord) params).getoInfo().getThirdOpinion(); //
        String chengguoStandardStr =  ((DocCompanyApplyWord) params).getoInfo().getChengguoStandard(); //


        datas.put("mainCompleteUserInfo", mainCompleteUserInfoStr == null ? "" : mainCompleteUserInfoStr);
        datas.put("taskSourceExt", taskSourceStr == null ? "" : taskSourceStr);
        datas.put("planDetalNameCodeExt", planDetalNameCodeStr == null ? "" : planDetalNameCodeStr);
        datas.put("departmentAwardInfoExt", departmentAwardInfoStr == null ? "" : departmentAwardInfoStr);
        datas.put("enterpriseOpinionExt", enterpriseOpinionStr == null ? "" : enterpriseOpinionStr);
        datas.put("chengguoDesExt", chengguoDesStr == null ? "" : chengguoDesStr);
        datas.put("mainTechnologicalInnovationExt", mainTechnologicalInnovationStr == null ? "" : mainTechnologicalInnovationStr);
        datas.put("thirdOpinionExt", thirdOpinionStr == null ? "" : thirdOpinionStr);
        datas.put("chengguoStandardExt", chengguoStandardStr == null ? "" : chengguoStandardStr);




        XWPFTemplate template = XWPFTemplate.compile(templatePath, config).render(datas);


        FileOutputStream out = new FileOutputStream(createWordPath);
        template.write(out);
        out.flush();
        out.close();
    }

    public static Style getStyle(){
        Style style = new Style();
        style.setFontFamily("宋体");
        style.setFontSize(11);
        return style;
    }

    public static Style getHeadStyle(){
        Style style = new Style();
        style.setColor("333333");
        style.setHighlightColor("388E8E");
        style.setFontFamily("宋体");
        style.setBold(true);
        style.setFontSize(10);
        return style;
    }

    /**
     * 文字样式
     *
     * @param content
     * @return
     */
    private static TextRenderData getTextRenderData(String content) {
        return new TextRenderData( content,getStyle());
    }


    // 表格头文字居中对齐


    /**
     * @param templatePath
     * @param createWordPath
     * @param params
     * @throws IOException
     */
    public static void createPersonalWord(String templatePath, String createWordPath, DocBaseDataWord params,
                                          List<EnterpriPersonalInfoWorkHistoryDO> historyDOs,
                                          List<EnterpriPersonalInfoTenYearsPatentDO> tenYearsPatentDOList) throws IOException {

        Map<String, Object> datas = new HashMap<String, Object>();
        datas.put("params", params);
        DocPersonApplyWord personApplyWord = (DocPersonApplyWord) params;
        try {
            String photoUrl = personApplyWord.getpInfo().getPhoto();
            if(StringUtils.isNotBlank(photoUrl) && !photoUrl.startsWith("http")) {
                BootdoConfig bootdoConfig = getBean("bootdoConfig");
                photoUrl = bootdoConfig.getImgUrlPre() + photoUrl;
            }
            if(StringUtils.isNotBlank(photoUrl)) {
                String spectorStr = "/";
                int lastIndex = photoUrl.lastIndexOf(spectorStr);
                if(lastIndex < 0) {
                    spectorStr = "\\";
                    lastIndex = photoUrl.lastIndexOf(spectorStr);
                }
                if(lastIndex > 0) {
                    String preStr = photoUrl.substring(0, lastIndex);
                    String fileName = URLDecoder.decode(photoUrl.substring(lastIndex + 1), "UTF-8");
                    photoUrl = preStr + spectorStr + URLEncoder.encode(fileName, "UTF-8");
                }
            }
            if (NetUtil.checkSourceIsOk(photoUrl)){
                datas.put("urlHeader", Pictures.ofUrl(photoUrl, PictureType.PNG).size(117, 145).create());
            }
        }catch (Exception ex) {
            logger.error("poi word url exception {}",ex.getMessage());
        }

        List<String> strs = new ArrayList<>();
        strs.add("起止时间");
        strs.add("单位名称");
        strs.add("职  务");
        strs.add("从事工作");

        RowRenderData header = Rows.of(getRowHederList(strs)).bgColor("FFFFFF").center().create();

        List<RowRenderData> rows = new ArrayList<>();
        rows.add(header);

        for (int a = 0; a < historyDOs.size(); a++) {
            EnterpriPersonalInfoWorkHistoryDO workHistoryDO = historyDOs.get(a);
            TextRenderData[] cellDatas = {
                    new TextRenderData(workHistoryDO.getWorkStartEndTime()),
                    new TextRenderData(workHistoryDO.getWorkCompany()),
                    new TextRenderData(workHistoryDO.getWorkPosition()),
                    new TextRenderData(workHistoryDO.getWorkContent())};
            rows.add(Rows.of(cellDatas).textFontFamily("宋体").textFontSize(11).create());

        }

        datas.put("tableHis", getTableRenderData(rows));

        //申报人科技创新业绩指标
        String borderCss = "style='border:1px solid grey;'";
        String colspanCss = " rowspan='COLSPAN_NUM' ";
        Map<Integer, List<String>> kpiRowDataMap = new HashMap<>();
        kpiRowDataMap.put(1, new ArrayList<>());
        kpiRowDataMap.put(2, new ArrayList<>());
        kpiRowDataMap.put(3, new ArrayList<>());
        kpiRowDataMap.put(4, new ArrayList<>());
        kpiRowDataMap.put(5, new ArrayList<>());
        kpiRowDataMap.put(6, new ArrayList<>());
        for (int a = 0; a < tenYearsPatentDOList.size(); a++) {
            EnterpriPersonalInfoTenYearsPatentDO workHistoryDO = tenYearsPatentDOList.get(a);
            Integer indicatorIndex = workHistoryDO.getIndicatorIndex();
            if(indicatorIndex == null) {
                continue;
            }
            List<String> rowsDataList = kpiRowDataMap.get(indicatorIndex);
            if(rowsDataList == null) {
                rowsDataList = new ArrayList<>();
                kpiRowDataMap.put(indicatorIndex, rowsDataList);
            }
            rowsDataList.add("<tr> " +
                    "            <td "+borderCss+"> " + CommonUtils.replaceHtmlTagP(workHistoryDO.getName()) +  "</td> " +
                    "            <td "+borderCss+">" + CommonUtils.replaceHtmlTagP(workHistoryDO.getType()) + " " + "</td> " +
                    "            <td "+borderCss+">" + CommonUtils.replaceHtmlTagP(workHistoryDO.getEffect()) +" " + "</td> " +
                    "         </tr>  ");
        }

        StringBuilder kpiStr = new StringBuilder();
        kpiStr.append("<table "+borderCss+">");
        int cNumOne = kpiRowDataMap.get(1).size() + 1;
        String colspanCssOne = colspanCss.replace("COLSPAN_NUM", (cNumOne == 0 ? 1 : cNumOne) + "");
        kpiStr.append("<tr "+borderCss+">" +
                "        <td "+colspanCssOne+" "+borderCss+">1、近五年内由申报人参与完成并取得授权的专利</td>" +
                "        <td "+borderCss+">专利名称</td>" +
                "        <td "+borderCss+">类型（发明、实用新型、外观设计）</td>" +
                "        <td "+borderCss+">所起作用（主持完成排名1~2、主要参加完成排名3~5、排名6及以后）</td>" +
                "</tr>");
        kpiRowDataMap.get(1).stream().forEach(tr->{kpiStr.append(tr);});

        int cNumTwo = kpiRowDataMap.get(2).size() + 1;
        String colspanCssTwo = colspanCss.replace("COLSPAN_NUM", (cNumTwo == 0 ? 1 : cNumTwo) + "");
        kpiStr.append("<tr "+borderCss+">" +
                "        <td "+colspanCssTwo+" "+borderCss+">2、近五年内由申报人参与完成并获批工法</td>" +
                "        <td "+borderCss+">工法名称</td>" +
                "        <td "+borderCss+">审批机构</td>" +
                "        <td "+borderCss+">所起作用（主持完成排名1~2、主要参加完成排名3~5、排名6及以后）</td>" +
                "</tr>");
        kpiRowDataMap.get(2).stream().forEach(tr->{kpiStr.append(tr);});

        int cNumThree = kpiRowDataMap.get(3).size() + 1;
        String colspanCssThree = colspanCss.replace("COLSPAN_NUM", (cNumThree == 0 ? 1 : cNumThree) + "");
        kpiStr.append("<tr "+borderCss+">" +
                "        <td "+colspanCssThree+" "+borderCss+">3、近五年内由申报人参与完成并通过具有科技成果鉴定权机构鉴定的科技成果(不含QC)</td>" +
                "        <td "+borderCss+">成果名称</td>" +
                "        <td "+borderCss+">鉴定结论（国际领先、国际先进、国内领先、国内先进）</td>" +
                "        <td "+borderCss+">所起作用（主持完成排名1~2、主要参加完成排名3~5、排名6及以后）</td>" +
                "</tr>");

        kpiRowDataMap.get(3).stream().forEach(tr->{kpiStr.append(tr);});

        int cNumFour = kpiRowDataMap.get(4).size() + 1;
        String colspanCssFour = colspanCss.replace("COLSPAN_NUM", (cNumFour == 0 ? 1 : cNumFour) + "");
        kpiStr.append("<tr "+borderCss+">" +
                "        <td "+colspanCssFour+" "+borderCss+">4、近五年内由申报人参与完成的成果所获科技类奖项（不含QC）</td>" +
                "        <td "+borderCss+">名称及发表（发行）时间</td>" +
                "        <td "+borderCss+">获奖名称及发奖机构</td>" +
                "        <td "+borderCss+">所起作用（第一完成人，其他）</td>" +
                "</tr>");
        kpiRowDataMap.get(4).stream().forEach(tr->{kpiStr.append(tr);});

        int cNumFive = kpiRowDataMap.get(5).size() + 1;
        String colspanCssFive = colspanCss.replace("COLSPAN_NUM", (cNumFive == 0 ? 1 : cNumFive) + "");
        kpiStr.append("<tr "+borderCss+">" +
                "        <td "+colspanCssFive+" "+borderCss+">5、近五年内由申报人参与完成并公开发表、发行的论文及专著</td>" +
                "        <td "+borderCss+">专利名称</td>" +
                "        <td "+borderCss+">类型（著作、期刊、会议论文）</td>" +
                "        <td "+borderCss+">所起作用（主持完成排名1~2、主要参加完成排名3~5、排名6及以后）</td>" +
                "</tr>");
        kpiRowDataMap.get(5).stream().forEach(tr->{kpiStr.append(tr);});

        int cNumSix = kpiRowDataMap.get(6).size() + 1;
        String colspanCssSix = colspanCss.replace("COLSPAN_NUM", (cNumSix == 0 ? 1 : cNumSix) + "");
        kpiStr.append("<tr "+borderCss+">" +
                "        <td "+colspanCssSix+" "+borderCss+">6. 近五年内申报人获得的科技类个人荣誉（称号）奖</td>" +
                "        <td "+borderCss+">荣誉名称</td>" +
                "        <td "+borderCss+">发奖机构</td>" +
                "        <td "+borderCss+">获奖年份</td>" +
                "</tr>");
        kpiRowDataMap.get(6).stream().forEach(tr->{kpiStr.append(tr);});

        kpiStr.append("</table>");


        //-------------------------------------------------------------------------------------------------------------------------------------

        HtmlRenderPolicy htmlRenderPolicy = new HtmlRenderPolicy();
        Configure config = Configure.builder()
                .bind("personalSummary", htmlRenderPolicy)
                .bind("companyOpinion", htmlRenderPolicy)
                .bind("kpiStr", htmlRenderPolicy)
                .build();

        String personalSummaryStr =  ((DocPersonApplyWord) params).getpInfo().getPersonalSummary(); // 主要完成人员
        String companyOpinionStr =  ((DocPersonApplyWord) params).getpInfo().getCompanyOpinion(); // 主要完成人员
        datas.put("personalSummary",personalSummaryStr == null  ? "" : personalSummaryStr);
        datas.put("companyOpinion",companyOpinionStr == null  ? "" : companyOpinionStr);
        datas.put("kpiStr",kpiStr.toString());


        XWPFTemplate template = XWPFTemplate.compile(templatePath,config).render(datas);

        FileOutputStream out = new FileOutputStream(createWordPath);
        template.write(out);
        out.flush();
        out.close();
    }

    /***
     * 团队
     * @param header
     * @param enterpriTeamInfoList
     * @param response
     * @throws IOException
     */
     public  static void downExcel(String[] header,  List<EnterpriTeamInfoDO> enterpriTeamInfoList, HttpServletResponse response) throws IOException {
         //声明一个工作簿
         HSSFWorkbook workbook = new HSSFWorkbook();

         HSSFSheet sheet = workbook.createSheet("先进团队成果");
         //设置表格列宽度为10个字节
         sheet.setDefaultColumnWidth(15);
         //创建标题的显示样式
         HSSFCellStyle headerStyle = workbook.createCellStyle();
         headerStyle.setFillForegroundColor(IndexedColors.YELLOW.index);
         headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
         //创建第一行表头
         HSSFRow headrow = sheet.createRow(0);

         //遍历添加表头(下面模拟遍历学生，也是同样的操作过程)
         for (int i = 0; i < header.length; i++) {
             //创建一个单元格
             HSSFCell cell = headrow.createCell(i);
             //创建一个内容对象
             HSSFRichTextString text = new HSSFRichTextString(header[i]);
             //将内容对象的文字内容写入到单元格中
             cell.setCellValue(text);
             cell.setCellStyle(headerStyle);
         }

         //获取所有的employee
         for(int i=0;i<enterpriTeamInfoList.size();i++){
             //创建一行
             HSSFRow row1 = sheet.createRow(i+1);
//             //第一列创建并赋值
             row1.createCell(0).setCellValue(new HSSFRichTextString(enterpriTeamInfoList.get(i).getShowNum()+""));
//             //第二列创建并赋值
             row1.createCell(1).setCellValue(new HSSFRichTextString(enterpriTeamInfoList.get(i).getProType()));
//             //第三列创建并赋值
             row1.createCell(2).setCellValue(new HSSFRichTextString(enterpriTeamInfoList.get(i).getTeamName()));
//             //第4列创建并赋值
                 row1.createCell(3).setCellValue(new HSSFRichTextString(enterpriTeamInfoList.get(i).getLeader()));

//             //第5列创建并赋值
             row1.createCell(4).setCellValue(new HSSFRichTextString(enterpriTeamInfoList.get(i).getEnterpriseName()));
//             //第6列创建并赋值
             row1.createCell(5).setCellValue(new HSSFRichTextString(enterpriTeamInfoList.get(i).getResearchDirection()));
//             //第7列创建并赋值
             row1.createCell(6).setCellValue(new HSSFRichTextString(enterpriTeamInfoList.get(i).getValidateDate()));
//             //籍贯
             row1.createCell(7).setCellValue(new HSSFRichTextString(enterpriTeamInfoList.get(i).getTeamBuildTime()));

             row1.createCell(8).setCellValue(new HSSFRichTextString(enterpriTeamInfoList.get(i).getApplyStat()));
//             //第8列创建并赋值
             row1.createCell(9).setCellValue(new HSSFRichTextString(enterpriTeamInfoList.get(i).getApplyAccount()));
//
         }

         response.setContentType("application/octet-stream");
         response.setHeader("Content-disposition", "attachment;filename=team.xls");

         response.flushBuffer();
         workbook.write(response.getOutputStream());
     }


    /***
     * 团队
     * @param header
     * @param enterpriTeamInfoList
     * @param response
     * @throws IOException
     */
    public  static void downScienceExcel(String[] header, List<EnterpriseProjectInfoDo> enterpriTeamInfoList, HttpServletResponse response) throws IOException {
        //声明一个工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();

        //项目的类型:science_progress 科技进步,science_team科技团队,science_personal科技个人


        HSSFSheet sheet = workbook.createSheet("科学技术奖成果-进步");
        //设置表格列宽度为10个字节
        sheet.setDefaultColumnWidth(20);


        HSSFSheet sheet1 = workbook.createSheet("科学技术奖成果-团队");
        //设置表格列宽度为10个字节
        sheet1.setDefaultColumnWidth(20);

        HSSFSheet sheet2 = workbook.createSheet("科学技术奖成果-个人");
        //设置表格列宽度为10个字节
        sheet2.setDefaultColumnWidth(20);




        //创建标题的显示样式
        HSSFCellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.YELLOW.index);
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        //创建第一行表头
        HSSFRow headrow = sheet.createRow(0);
        for (int i = 0; i < header.length; i++) {
            HSSFCell cell = headrow.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(header[i]);
            cell.setCellValue(text);
            cell.setCellStyle(headerStyle);
        }


        //创建第一行表头
        HSSFRow headrow1 = sheet1.createRow(0);
        for (int i = 0; i < header.length; i++) {
            HSSFCell cell = headrow1.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(header[i]);
            cell.setCellValue(text);
            cell.setCellStyle(headerStyle);
        }




        //创建第一行表头
        HSSFRow headrow2 = sheet2.createRow(0);
        for (int i = 0; i < header.length; i++) {
            HSSFCell cell = headrow2.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(header[i]);
            cell.setCellValue(text);
            cell.setCellStyle(headerStyle);
        }
        List<EnterpriseProjectInfoDo> enterpriseSice = new ArrayList<>();
        List<EnterpriseProjectInfoDo> enterpriseTeam = new ArrayList<>();
        List<EnterpriseProjectInfoDo> enterprisePerson = new ArrayList<>();

        for (EnterpriseProjectInfoDo info:enterpriTeamInfoList ) {

            if("science_team".equalsIgnoreCase(info.getProType())){
                enterpriseTeam.add(info);
            }else if("science_personal".equalsIgnoreCase(info.getProType())){
                enterprisePerson.add(info);
            }else{
                enterpriseSice.add(info);
            }
        }



        //获取所有的employee
        for(int i=0;i<enterpriseSice.size();i++){
            //创建一行
            HSSFRow row1 = sheet.createRow(i+1);
////             //第一列创建并赋值
            row1.createCell(0).setCellValue(new HSSFRichTextString(enterpriseSice.get(i).getShowNum()+""));
////             //第二列创建并赋值
            row1.createCell(1).setCellValue(new HSSFRichTextString(enterpriseSice.get(i).getProCode()));
            row1.createCell(2).setCellValue(new HSSFRichTextString(enterpriseSice.get(i).getProTypeStr()));
////             //第三列创建并赋值
//            row1.createCell(2).setCellValue(new HSSFRichTextString(enterpriTeamInfoList.get(i).getTeamName()));
////             //第4列创建并赋值
            row1.createCell(3).setCellValue(new HSSFRichTextString(enterpriseSice.get(i).getEnterpriseName()));
////             //第5列创建并赋值
            row1.createCell(4).setCellValue(new HSSFRichTextString(enterpriseSice.get(i).getMajor()));
////             //第6列创建并赋值
            row1.createCell(5).setCellValue(new HSSFRichTextString(enterpriseSice.get(i).getCreated()));
////             //第7列创建并赋值
            row1.createCell(6).setCellValue(new HSSFRichTextString(enterpriseSice.get(i).getShowProName()));
            row1.createCell(7).setCellValue(new HSSFRichTextString(enterpriseSice.get(i).getApplyStat()));
            row1.createCell(8).setCellValue(new HSSFRichTextString(enterpriseSice.get(i).getMainCompleteNames()));
//
        }

        for(int i=0;i<enterpriseTeam.size();i++){
            //创建一行
            HSSFRow row1 = sheet1.createRow(i+1);
////             //第一列创建并赋值
            row1.createCell(0).setCellValue(new HSSFRichTextString(enterpriseTeam.get(i).getShowNum()+""));
            row1.createCell(1).setCellValue(new HSSFRichTextString(enterpriseTeam.get(i).getProCode()));
////             //第二列创建并赋值
            row1.createCell(2).setCellValue(new HSSFRichTextString(enterpriseTeam.get(i).getProTypeStr()));
////             //第三列创建并赋值
//            row1.createCell(2).setCellValue(new HSSFRichTextString(enterpriTeamInfoList.get(i).getTeamName()));
////             //第4列创建并赋值
            row1.createCell(3).setCellValue(new HSSFRichTextString(enterpriseTeam.get(i).getEnterpriseName()));
////             //第5列创建并赋值
            row1.createCell(4).setCellValue(new HSSFRichTextString(enterpriseTeam.get(i).getMajor()));
////             //第6列创建并赋值
            row1.createCell(5).setCellValue(new HSSFRichTextString(enterpriseTeam.get(i).getCreated()));
////             //第7列创建并赋值
            row1.createCell(6).setCellValue(new HSSFRichTextString(enterpriseTeam.get(i).getShowProName()));
            row1.createCell(7).setCellValue(new HSSFRichTextString(enterpriseTeam.get(i).getApplyStat()));
            row1.createCell(8).setCellValue(new HSSFRichTextString(enterpriseTeam.get(i).getMainCompleteNames()));
//
        }

        for(int i=0;i<enterprisePerson.size();i++){
            //创建一行
            HSSFRow row1 = sheet2.createRow(i+1);


////             //第一列创建并赋值
            row1.createCell(0).setCellValue(new HSSFRichTextString(enterprisePerson.get(i).getShowNum()+""));
            row1.createCell(1).setCellValue(new HSSFRichTextString(enterprisePerson.get(i).getProCode()));

////             //第二列创建并赋值
            row1.createCell(2).setCellValue(new HSSFRichTextString(enterprisePerson.get(i).getProTypeStr()));
////             //第三列创建并赋值
//            row1.createCell(2).setCellValue(new HSSFRichTextString(enterpriTeamInfoList.get(i).getTeamName()));
////             //第4列创建并赋值
            row1.createCell(3).setCellValue(new HSSFRichTextString(enterprisePerson.get(i).getEnterpriseName()));
////             //第5列创建并赋值
            row1.createCell(4).setCellValue(new HSSFRichTextString(enterprisePerson.get(i).getMajor()));
////             //第6列创建并赋值
            row1.createCell(5).setCellValue(new HSSFRichTextString(enterprisePerson.get(i).getCreated()));
////             //第7列创建并赋值
            row1.createCell(6).setCellValue(new HSSFRichTextString(enterprisePerson.get(i).getShowProName()));
            row1.createCell(7).setCellValue(new HSSFRichTextString(enterprisePerson.get(i).getApplyStat()));
            row1.createCell(8).setCellValue(new HSSFRichTextString(enterprisePerson.get(i).getMainCompleteNames()));
//
        }



        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=science.xls");

        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }


    /***
     * 团队
     * @param header
     * @param enterpriTeamInfoList
     * @param response
     * @throws IOException
     */
    public  static void downQCExcel(String[] header, List<QcProDataDto> enterpriTeamInfoList, HttpServletResponse response) throws IOException {
        //声明一个工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("qc");
        //设置表格列宽度为10个字节
        sheet.setDefaultColumnWidth(20);
        //创建标题的显示样式
        HSSFCellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.YELLOW.index);
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        //创建第一行表头
        HSSFRow headrow = sheet.createRow(0);
        for (int i = 0; i < header.length; i++) {
            HSSFCell cell = headrow.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(header[i]);
            cell.setCellValue(text);
            cell.setCellStyle(headerStyle);
        }
        //获取所有的employee
        for(int i=0;i<enterpriTeamInfoList.size();i++){
            //创建一行
            HSSFRow row1 = sheet.createRow(i+1);
////             //第一列创建并赋值
            row1.createCell(0).setCellValue(new HSSFRichTextString(enterpriTeamInfoList.get(i).getId()+""));
////             //第二列创建并赋值
            row1.createCell(1).setCellValue(new HSSFRichTextString(enterpriTeamInfoList.get(i).getProCode()));
////             //第三列创建并赋值
//            row1.createCell(2).setCellValue(new HSSFRichTextString(enterpriTeamInfoList.get(i).getTeamName()));
////             //第4列创建并赋值
            row1.createCell(2).setCellValue(new HSSFRichTextString(enterpriTeamInfoList.get(i).getProType()));
////             //第5列创建并赋值
            row1.createCell(3).setCellValue(new HSSFRichTextString(enterpriTeamInfoList.get(i).getTopicName()));
////             //第6列创建并赋值
            row1.createCell(4).setCellValue(new HSSFRichTextString(enterpriTeamInfoList.get(i).getGroupName()));
////             //第7列创建并赋值
            row1.createCell(5).setCellValue(new HSSFRichTextString(enterpriTeamInfoList.get(i).getCompanyName()));
            row1.createCell(6).setCellValue(new HSSFRichTextString(enterpriTeamInfoList.get(i).getTopicType()));
            row1.createCell(7).setCellValue(new HSSFRichTextString(enterpriTeamInfoList.get(i).getProfessionalScope()));
            row1.createCell(8).setCellValue(new HSSFRichTextString(enterpriTeamInfoList.get(i).getApplyAccount()));
            row1.createCell(9).setCellValue(new HSSFRichTextString(enterpriTeamInfoList.get(i).getApplyStat()));


        }

        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=QC项目列表.xls");

        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }



    /***
     * 团队
     * @param header
     * @param enterpriTeamInfoList
     * @param response
     * @throws IOException
     */
    public  static void downPersonalExcel(String[] header, List<EnterpriPersonalInfoDO> enterpriTeamInfoList, HttpServletResponse response) throws IOException {
        //声明一个工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("先进个人成果");
        //设置表格列宽度为10个字节
        sheet.setDefaultColumnWidth(20);
        //创建标题的显示样式
        HSSFCellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.YELLOW.index);
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        //创建第一行表头
        HSSFRow headrow = sheet.createRow(0);
        for (int i = 0; i < header.length; i++) {
            HSSFCell cell = headrow.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(header[i]);
            cell.setCellValue(text);
            cell.setCellStyle(headerStyle);
        }
        //获取所有的employee
        for(int i=0;i<enterpriTeamInfoList.size();i++){
            //创建一行
            HSSFRow row1 = sheet.createRow(i+1);
////             //第一列创建并赋值
            row1.createCell(0).setCellValue(new HSSFRichTextString(enterpriTeamInfoList.get(i).getShowNum()+""));
////             //第二列创建并赋值
            row1.createCell(1).setCellValue(new HSSFRichTextString(enterpriTeamInfoList.get(i).getUserName()));
////             //第三列创建并赋值
            row1.createCell(2).setCellValue(new HSSFRichTextString(enterpriTeamInfoList.get(i).getEnterpriseName()));
////             //第4列创建并赋值
            row1.createCell(3).setCellValue(new HSSFRichTextString(enterpriTeamInfoList.get(i).getMajor()));
////             //第5列创建并赋值
            row1.createCell(4).setCellValue(new HSSFRichTextString(enterpriTeamInfoList.get(i).getCreated()+""));

            row1.createCell(5).setCellValue(new HSSFRichTextString(enterpriTeamInfoList.get(i).getApplyStat()+""));
////             //第6列创建并赋值
            row1.createCell(6).setCellValue(new HSSFRichTextString(enterpriTeamInfoList.get(i).getApplyAccount()));

        }

        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=personal.xls");

        response.flushBuffer();
        workbook.write(response.getOutputStream());
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





    public static void main(String[] args) throws IOException {
        List<EnterpriPersonalInfoWorkHistoryDO> historyDOs = new ArrayList<>();
        EnterpriPersonalInfoWorkHistoryDO one = new EnterpriPersonalInfoWorkHistoryDO();
        one.setWorkCompany("aa");
        one.setWorkContent("bb");
        one.setWorkPosition("CCC");
        one.setWorkStartEndTime("DDD");
        historyDOs.add(one);
//
//        createPersonalWord("/Users/fei/Downloads/1111.docx","/Users/fei/Downloads/112.docx",new DocBaseDataWord(),historyDOs);


//        createScicenceWord("/Users/fei/Downloads/11111.docx","/Users/fei/Downloads/112.docx",new DocBaseDataWord());


        Map<String, Object> datas = new HashMap<String, Object>();

        List<String> teamCooperStrs = new ArrayList<>();
        teamCooperStrs.add("类别");
        teamCooperStrs.add("成果名称");
        teamCooperStrs.add("本团队中合作者/排序");
        teamCooperStrs.add("备注");

        RowRenderData teamCoopersheader = Rows.of(getRowHederList(teamCooperStrs)).create();



        List<RowRenderData> rows = new ArrayList<>();
        rows.add(teamCoopersheader);

        for (int a = 0; a < historyDOs.size(); a++) {
            EnterpriPersonalInfoWorkHistoryDO workHistoryDO = historyDOs.get(a);
            rows.add(Rows.of(workHistoryDO.getWorkStartEndTime(), workHistoryDO.getWorkCompany(),
                    workHistoryDO.getWorkPosition(), workHistoryDO.getWorkContent()).create());
        }

        datas.put("tableHis", getTableRenderData(rows));


        XWPFTemplate template = XWPFTemplate.compile("/Users/fei/Downloads/111.docx").render(datas);


        FileOutputStream out = new FileOutputStream("/Users/fei/Downloads/112.docx");
        template.write(out);
        out.flush();
        out.close();

    }



        public static TableRenderData getTableRenderData(List<RowRenderData> rowRenderDataList){
        RowRenderData[] rowArr = new RowRenderData[rowRenderDataList.size()];
        for(int i=0;i<rowArr.length;i++) {
            rowArr[i] = rowRenderDataList.get(i);
        }
        return Tables.of(rowArr).border(TableStyle.BorderStyle.DEFAULT).create();
    }


    public static String formatDate(Date date) {
        String str = DateUtils.format(date);
        if(StringUtils.isBlank(str)) {
            return "无";
        }
        return str;
    }



}
