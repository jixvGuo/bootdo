package com.bootdo.cpe.controller.petroleum_engineering;

import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.controller.BaseOilProQualityGoldController;
import com.bootdo.common.utils.DateUtils;
import com.bootdo.common.utils.JSONUtils;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.cpe.domain.*;
import com.bootdo.cpe.petroleum_engineering_award.domain.*;
import com.bootdo.cpe.petroleum_engineering_award.service.*;
import com.bootdo.cpe.utils.PathUtil;
import com.bootdo.cpe.utils.PoiWordOilProUtils;
import com.bootdo.system.config.ConstantCommonData;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

/**
 * “10、石油优质工程金奖申报表”
 * @author houzb
 * @Description
 * @create 2021-02-20 0:36
 */
@Controller
@RequestMapping("/enterpriseQualityGoldAward")
public class EnterpriseQualityGoldAwardController extends BaseOilProQualityGoldController {

    private String prefix = "engineering/quality_gold_award";
    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    private BootdoConfig bootdoConfig;

    @Autowired
    private OilAwardContributionUsersService oilAwardContributionUsersService;
    @Autowired
    private OilAwardPartakeUnitService oilAwardPartakeUnitService;
    @Autowired
    private OilAwardApplyInfoService oilAwardApplyInfoService;
    @Autowired
    private OilAwardGetInfoService oilAwardGetInfoService;
    @Autowired
    private OilAwardUnitInfoService oilAwardUnitInfoService;
    @Autowired
    private OilQualityProSituationService qualityProSituationService;
    @Autowired
    private OilQualityConfirmFileService qualityConfirmFileService;
    @Autowired
    private OilProQualityGoldReviewRecordService oilProQualityGoldReviewRecordService;
    @Autowired
    private OilProQualityReviewRecordService oilProQualityReviewRecordService;



    /**
     * 跳转到“01、石油优质工程新增”页面
     * @param map
     * @return
     */
    @RequestMapping("/toAddGold")
//    @RequiresPermissions("petroleumEngineering:qualityGoldPro:toAddGold")
    public String toAddGoldPage(@RequestParam Map<String,Object> params, ModelMap map) {
        boolean isApply = isTaskIsApply(map, params, false);
        if(!isApply) {
            return errPage;
        }
        applyParams(map, params);
        map.put("tagName", "新增");
        OilAwardApplyInfoDO data = new OilAwardApplyInfoDO();//
        OilAwardGetInfoDO designAwardDO = new OilAwardGetInfoDO();//获奖信息
        OilAwardUnitInfoDO comany = new OilAwardUnitInfoDO();//单位信息
        OilAwardPartakeUnitDO awardPartakeUnitDO =  new OilAwardPartakeUnitDO();//

        Object proIdObj = params.get("proId");
        if(proIdObj != null) {
            params.remove("applyId");
            List<OilAwardApplyInfoDO> awardApplyInfoDOList = oilAwardApplyInfoService.list(params);
            if(awardApplyInfoDOList.size() > 0) {
                map.put("tagName", "修改");
                data = awardApplyInfoDOList.get(0);
                Map<String,Object> query = new HashMap<>();
                query.put("proId", proIdObj);
                query.put("applyInfoId", data.getId());
                List<OilAwardGetInfoDO> getInfoDOList = oilAwardGetInfoService.list(query);
                List<OilAwardUnitInfoDO> unitInfoDOList = oilAwardUnitInfoService.list(query);
                List<OilAwardPartakeUnitDO> partakeUnitDOList = oilAwardPartakeUnitService.list(query);
                map.put("getInfoList", getInfoDOList);
                map.put("unitInfoList", unitInfoDOList);
                map.put("partakeUnitDOList", partakeUnitDOList);
            }
        }

        map.put("data",data);
        map.put("awards",designAwardDO);
        map.put("company",comany);
        map.put("build",awardPartakeUnitDO);

        return prefix + "/quality_gold_engin_add_01";
    }

    /**
     * 跳转到“01、石油优质工程新增”页面
     *
     * @param map
     * @return
     */
    @RequestMapping("/toEditApply")
//    @RequiresPermissions("petroleumEngineering:qualityGoldPro:toEditApply")
    public String toEditPage(@RequestParam Map<String, Object> params, ModelMap map) {
        return toAddGoldPage(params, map);
    }

    /**
     * 跳转到“01、石油优质工程新增”页面
     * @param map
     * @param request
     * @return
     */
    @RequestMapping("/toUploadGoldFile")
    public String toUploadFileGoldPage(@RequestParam Map<String,Object> params, ModelMap map, HttpServletRequest request) {
        applyParams(map, params);
        String fileType = EnumProjectType.OIL_PRO_QUALITY_GOLD.getProType() + "_confirm";
        map.put("fileType", fileType);

        params.remove("applyId");
        List<OilQualityConfirmFileDO> qualityConfirmFileDOList = qualityConfirmFileService.list(params);
        map.put("docUploadDoList", qualityConfirmFileDOList);

        ArrayList arrayList = new ArrayList();
        arrayList.add("proof_material_cover_type");
        arrayList.add("commitment_type");
        arrayList.add("table_contents_type");
        arrayList.add("main_qualification_certificate_type");
        arrayList.add("project_preliminary_design_approval_document");
        arrayList.add("project_approval_documentstype");
        arrayList.add("project_construction_approval_document_type");
        arrayList.add("project_quality_assessment_quality_supervision_type");
        arrayList.add("project_completion_acceptance_documents_type");
        arrayList.add("project_completion_acceptance_filing_documents_type");
        arrayList.add("project_completion_final_accounts_audit_report_type");
        arrayList.add("safety_quality_accidents_wages_owed_migrant_workers_type");
        arrayList.add("bureau_level_quality_engineering_award_certificate_type");
        arrayList.add("contract_signed_application_unit_construction_unit_type");
        arrayList.add("Other_materials_describing_quality_project_type");

        List<OilQualityConfirmFileDO> qualityIn = new ArrayList<>();

        for (int i=0; i<qualityConfirmFileDOList.size(); i++)
        {
            OilQualityConfirmFileDO fileDO = qualityConfirmFileDOList.get(i);
            boolean isContains =  arrayList .contains(fileDO.getFileType());
            if (isContains){
                qualityIn.add(fileDO);
            }
        }

        map.put("docUploadDoListIn", qualityIn);

        ArrayList arrayListOut = new ArrayList();
        arrayListOut.add("main_applying_qualification_certificate_foreign_contracted_project_management_type");
        arrayListOut.add("project_approval_documents_sub_type");
        arrayListOut.add("engineering_construction_contractor_service_contract_technical_agreement_type");
        arrayListOut.add("project_completion_acceptance_data_type");

        arrayListOut.add("evaluation_opinions_project_users_type");
        arrayListOut.add("main_qualification_opinion_type");
        arrayListOut.add("bureau_level_quality_engineering_award_certificate_sub_type");
        arrayListOut.add("design_award_certificate_design_quality_evaluation_certificate_competent_authority_type");
        arrayListOut.add("safety_quality_accident_certificate_project_type");
        arrayListOut.add("other_related_information_type");


        List<OilQualityConfirmFileDO> qualityOut = new ArrayList<>();

        for (int i=0; i<qualityConfirmFileDOList.size(); i++)
        {
            OilQualityConfirmFileDO fileDO = qualityConfirmFileDOList.get(i);
            boolean isContains =  arrayListOut .contains(fileDO.getFileType());
            if (isContains){
                qualityOut.add(fileDO);
            }
        }
        map.put("docUploadDoListOut", qualityOut);



        // 分组处理 境内工程

        //
        //
       //
        //
        //
        //
//
//
//
//
//
//
//
        // 分组处理 境内工程




        return prefix + "/quality_gold_engin_file_upload_01";
    }


    @RequestMapping("/toUploadConfirmDialog")
    public String toUploadConfirmFilePage(@RequestParam Map<String, Object> params, ModelMap map){
        applyParams(map, params);
        map.put("fileType", params.get("fileType"));
        return prefix + "/quality_gold_confirm_doc_upload";
    }


     /**
     * 跳转到“02、突出贡献者名单列表”页面
     * @param map
     * @param
     * @return
     */
    @RequestMapping("/toContributorList")
    public String toContributorListPage(@RequestParam Map<String ,Object> params, ModelMap map ) {
        applyParams(map, params);
        params.put("taskId", map.get("taskId"));
        params.put("type", map.get("1"));

        int curPageNum = pageNumPackage(params, map);
        Query query = new Query(params);
        query.put("sort","id");
        query.put("order","asc");
        query.remove("applyId");
        List<OilAwardContributionUsersDO> oilAwardContributionUsersList = oilAwardContributionUsersService.list(query);
        int total = oilAwardContributionUsersService.count(query);
        if(oilAwardContributionUsersList.size() == 0 && curPageNum > 1) {
            curPageNum = curPageNum - 1;
            params.put("pageNum", curPageNum);
            pageNumPackage(params, map);
            query = new Query(params);
            oilAwardContributionUsersList = oilAwardContributionUsersService.list(query);
        }
        map.put("pageNum", curPageNum);
        PageUtils pageUtils = new PageUtils(oilAwardContributionUsersList, total);
        map.put("pageUtils",pageUtils);
        return prefix + "/quality_gold_contributor_list_02";
    }

     /**
     * 跳转到“02、突出贡献者名单新增”页面
     * @param map
     * @param request
     * @return
     */
    @RequestMapping("/toContributorAdd")
    public String toContributorAddPage(@RequestParam Map<String,Object> params, ModelMap map, HttpServletRequest request) {
        applyParams(map, params);
        map.put("data", new OilAwardContributionUsersDO());
        map.put("proId", params.get("proId"));
        map.put("taskId", params.get("taskId") );
        map.put("applyId",params.get("applyId")  );

        return prefix + "/quality_gold_contributor_add_02";
    }

    /**
     * 跳转到“02、突出贡献者名单打印”页面
     * @param map
     * @param request
     * @return
     */
    @RequestMapping("/toContributorPrint")
    public String toContributorPrintPage(@RequestParam Map<String ,Object> params, ModelMap map, HttpServletRequest request) {
        applyParams(map, params);
        params.put("proId", map.get("proId"));

        Map<String ,Object> paramsNew = new HashMap<>();
        paramsNew.put("proId",map.get("proId"));

        List<OilAwardContributionUsersDO> oilAwardContributionUsersList = oilAwardContributionUsersService.list(paramsNew);
        int total = oilAwardContributionUsersList.size();

        PageUtils pageUtils = new PageUtils(oilAwardContributionUsersList, total);
        map.put("pageUtils",pageUtils);

        map.put("proId",map.get("proId"));

        return prefix + "/quality_gold_contributor_print_02";
    }

    /**
     * 跳转到“01、申报单位新增”
     *
     * @param map
     * @param params
     * @return
     */
    @RequestMapping("/toPrint")
    @RequiresPermissions("petroleumEngineering:qualityGoldPro:toPrint")
    public void toPrintPageContrbute(ModelMap map, @RequestParam Map<String, Object> params, HttpServletResponse response) throws IOException {
        applyParams(map, params);
        //获取列表信息
        File templateFile = PathUtil.getJarResourceFile(ConstantCommonData.TEMPLATE_DOC_OUTSTANDing_CONTRIBUTION_CLASS_PATH);
        if (templateFile == null) {
            Resource resource = resourceLoader.getResource(ConstantCommonData.TEMPLATE_DOC_OUTSTANDing_CONTRIBUTION_CLASS_PATH);
            templateFile = resource.getFile();
        }
        String fName = templateFile.getName();
        String[] fNameArr = fName.split("\\.");
        String tmpFileName = fNameArr[0] + "_" + System.currentTimeMillis() + RandomStringUtils.randomAlphabetic(5) + "." + fNameArr[1];
        tmpFileName = URLEncoder.encode(tmpFileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + tmpFileName);
        String storePath = bootdoConfig.getUploadPath() + "/print_doc/" + DateUtils.getCurDate();
        File storeFile = new File(storePath);
        if (!storeFile.exists()) {
            storeFile.mkdirs();
        }
        storePath = storePath + "/" + tmpFileName;
        storeFile = new File(storePath);
        FileUtils.copyFile(templateFile, storeFile);

        PetroleumEngineContributeDocBaseDataWord baseDataWord = new PetroleumEngineContributeDocBaseDataWord();
        //获取word的内容
        Object proIdObj = params.get("proId");
        int proId = proIdObj != null ? Integer.parseInt(proIdObj.toString()) : 0;
        OilQualityProBaseInfo proBaseInfo = proId > 0 ? oilAwardApplyInfoService.getQualityProBaseInfo(proId) : new OilQualityProBaseInfo();

        Map<String ,Object> paramsNew = new HashMap<>();
        paramsNew.put("proId",map.get("proId"));

        List<OilAwardContributionUsersDO> oilAwardContributionUsersList = oilAwardContributionUsersService.list(paramsNew);

        baseDataWord.setOpinionDOList(oilAwardContributionUsersList);
        baseDataWord.setProjectName(proBaseInfo.getProjectName());
        baseDataWord.setUseName(proBaseInfo.getUserName());
        PoiWordOilProUtils.createOilContributeProWord(templateFile.getAbsolutePath(), storePath, baseDataWord);

        InputStream inputStream = new FileInputStream(storeFile);
        //这个路径为待下载文件的路径
        BufferedInputStream bis = new BufferedInputStream(inputStream);
        byte[] buff = new byte[1024];
        int read = bis.read(buff);
        OutputStream outputStream = response.getOutputStream();
        //通过while循环写入到指定了的文件夹中
        while (read != -1) {
            outputStream.write(buff, 0, buff.length);
            outputStream.flush();
            read = bis.read(buff);
        }
        bis.close();
    }
    /**
     * 突出贡献将的 编辑 / 修改”
     * @param map
     * @param request
     * @return
     */
    @RequestMapping("/toEdit")
    public String toEditPage(@RequestParam Map<String, Object> params, ModelMap map, HttpServletRequest request) {
        applyParams(map, params);
        int id = Integer.parseInt(params.get("id").toString());
        OilAwardContributionUsersDO oilProApplyInfoDO = oilAwardContributionUsersService.get(id);
        map.put("data", oilProApplyInfoDO);
        if (oilProApplyInfoDO != null){
            map.put("proId", oilProApplyInfoDO.getProId());
            map.put("taskId", oilProApplyInfoDO.getTaskId());
            map.put("applyId", oilProApplyInfoDO.getApplyId());

        }
        return prefix + "/quality_gold_contributor_add_02";
    }


    /**
     *  14、石油优质 金奖 工程奖形式审查表
     *
     * @return
     */
    @RequestMapping("/petroleumgold")
    public String PetroleumInstallationReview(String taskId,String proId, ModelMap map) {

        return prefix +"/petroleum_gold_project_review";
    }

    /**
     * 跳转到“03、石油优质工程简介上传”页面
     *
     * @param map
     * @param request
     * @return
     */
    @RequestMapping("/toEnginDescUpload")
    public String toEngineeDescUploadPage(@RequestParam Map<String, Object> params, ModelMap map, HttpServletRequest request) {
        applyParams(map, params);
        params.remove("applyId");
        List<OilQualityProSituationDO> qualityProSituationDOList = qualityProSituationService.list(params);

        map.put("docList", qualityProSituationDOList);
        return prefix + "/quality_gold_desc_upload_03";
    }

    /**
     * 任务资料上传
     *
     * @return
     */
    @RequestMapping("/toUploadSituationDoc")
    public String toUploadTaskFile(String taskId,String proId, ModelMap map) {
        map.put("proId", proId);
        map.put("taskId",taskId);
        String fileType = EnumProjectType.OIL_PRO_QUALITY_GOLD.getProType() + "_desc";
        map.put("fileType", fileType);
        return prefix +"/quality_gold_situation_doc_upload";
    }

    /**
     * 12、石油安装工程形式审查表
     *
     * @return
     */
    @RequestMapping("/toReview")
    public String toPetroleumInstallationReview(@RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        Object proIdObj = params.get("proId");
        OilProQualityGoldReviewRecordDO reviewRecordDO = new OilProQualityGoldReviewRecordDO();
        Object idObj = params.get("reviewId");
        int id = idObj != null ? Integer.parseInt(idObj.toString()) : 0;
        if(id > 0) {
            reviewRecordDO = oilProQualityGoldReviewRecordService.get(id);
        }
        int proId = proIdObj == null ? 0 : Integer.parseInt(proIdObj.toString());
        if(proId > 0) {
            Object readonlyObj = params.get("readonly");
            if(id == 0 && readonlyObj != null && "1".equals(readonlyObj.toString())) {
                Map<String,Object> paramMap = new HashMap<>();
                paramMap.put("proId", proId);
                paramMap.put("sort","id");
                paramMap.put("order","desc");
                List<OilProQualityGoldReviewRecordDO> curReviewList = oilProQualityGoldReviewRecordService.list(paramMap);
                if(curReviewList.size() > 0) {
                    reviewRecordDO = curReviewList.get(0);
                }
            }
            ReviewQualityProBaseInfo baseInfoDO = oilProQualityReviewRecordService.getReviewProBaseInfoByProId(proId);
            if(baseInfoDO != null) {
                reviewRecordDO.setApplyUnit(baseInfoDO.getUnitName());
                reviewRecordDO.setApplyAwardName(baseInfoDO.getProjectName());
            }
        }
        map.put("data", reviewRecordDO);
        return prefix +"/review_quality_pro";
    }






    /**
     * 打印文件
     * @return
     */
    @RequestMapping("/toPrintGoldFile")
    public void toPrintGoldFile(ModelMap map, @RequestParam Map<String, Object> params, HttpServletResponse response, HttpServletRequest request) throws IOException {
        PetroleumEngineAwardDocBaseDataWord baseDataWord = new PetroleumEngineAwardDocBaseDataWord();
        //获取word的内容
        Object idObj = params.get("id");
        int id = idObj != null ? Integer.parseInt(idObj.toString()) : 0;
        OilAwardApplyInfoDO applyInfoDO = oilAwardApplyInfoService.get(id);
        if(applyInfoDO == null) {
            try {
                request.getRequestDispatcher("/error/err?msg=申报单位未填写").forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            }
            return;
        }
        baseDataWord.setOilAwardApplyInfoDO(applyInfoDO);
//        //获取列表信息
        File templateFile = PathUtil.getJarResourceFile(ConstantCommonData.PETROLEUM_QUAlITY_PROJECT_DEClARATION_FORM_CLASS_PATH);
        if (templateFile == null) {
            Resource resource = resourceLoader.getResource(ConstantCommonData.PETROLEUM_QUAlITY_PROJECT_DEClARATION_FORM_CLASS_PATH);
            templateFile = resource.getFile();
        }
        String fName = templateFile.getName();
        String[] fNameArr = fName.split("\\.");
        String tmpFileName = applyInfoDO.getFullNameUnit() +
                "优质工程金奖_" + RandomStringUtils.randomAlphabetic(5) + "." + fNameArr[1];
        tmpFileName = URLEncoder.encode(tmpFileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=" + tmpFileName);
        String storePath = bootdoConfig.getUploadPath() + "/print_doc/" + DateUtils.getCurDate();
        File storeFile = new File(storePath);
        if (!storeFile.exists()) {
            storeFile.mkdirs();
        }
        storePath = storePath + "/" + tmpFileName;
        storeFile = new File(storePath);
        FileUtils.copyFile(templateFile, storeFile);


        int proId = Integer.parseInt(applyInfoDO.getProId());
        String taskId = applyInfoDO.getTaskId();


        List<OilAwardGetInfoDO> unitOpinionDOList = oilAwardGetInfoService.getByProId(proId);// 获奖信息表(ass_oil_award_get_info):
        baseDataWord.setOilAwardGetInfoDOS(unitOpinionDOList);

        List<OilAwardUnitInfoDO> oilAwardUnitInfoDOS = oilAwardUnitInfoService.getByProId(proId);// 单位信息表(ass_oil_award_unit_info):
        baseDataWord.setOilAwardUnitInfoDOList(oilAwardUnitInfoDOS);

        List<OilAwardPartakeUnitDO> oilAwardPartakeUnitDOS = oilAwardPartakeUnitService.getByProId(proId);// 参见单位表(ass_oil_award_partake_unit):s
        baseDataWord.setOilAwardPartakeUnitDOS(oilAwardPartakeUnitDOS);



        Map<String, Object> paramsNew = new HashMap<>();
        paramsNew.put("proId",map.get("proId"));

        List<OilAwardContributionUsersDO> oilAwardContributionUsersList = oilAwardContributionUsersService.list(paramsNew);
        baseDataWord.setContributionUsersDOS(oilAwardContributionUsersList);


        PetroleumEngineAwardDocTableData tableData = new PetroleumEngineAwardDocTableData();


        tableData.setOilAwardGetInfoDOS(unitOpinionDOList);
        tableData.setOilAwardPartakeUnitDOS(oilAwardPartakeUnitDOS);
        tableData.setOilAwardUnitInfoDOList(oilAwardUnitInfoDOS);
        baseDataWord.setTableData(tableData);


        PoiWordOilProUtils.createQualityOilProWord(templateFile.getAbsolutePath(), storePath, baseDataWord);

        InputStream inputStream = new FileInputStream(storeFile);
        //这个路径为待下载文件的路径
        BufferedInputStream bis = new BufferedInputStream(inputStream);
        byte[] buff = new byte[1024];
        int read = bis.read(buff);
        OutputStream outputStream = response.getOutputStream();
        //通过while循环写入到指定了的文件夹中
        while (read != -1) {
            outputStream.write(buff, 0, buff.length);
            outputStream.flush();
            read = bis.read(buff);
        }
        bis.close();
    }


}
