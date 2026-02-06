package com.bootdo.cpe.controller.petroleum_engineering;

import com.bootdo.activiti.domain.EnterpriseProjectInfoDo;
import com.bootdo.activiti.domain.PublishAwardTaskDo;
import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.controller.BaseOilProInstallController;
import com.bootdo.common.utils.*;
import com.bootdo.cpe.domain.EnterpriseOilProInfo;
import com.bootdo.cpe.domain.EnumProjectType;
import com.bootdo.cpe.domain.PetroleumEngineDocBaseDataWord;
import com.bootdo.cpe.petroleum_engineering_award.domain.*;
import com.bootdo.cpe.petroleum_engineering_award.service.*;
import com.bootdo.cpe.utils.PathUtil;
import com.bootdo.cpe.utils.PoiWordOilProUtils;
import com.bootdo.system.config.ConstantCommonData;
import org.activiti.engine.task.Task;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 石油安装工程申报
 *
 * @author houzb
 * @Description
 * @create 2021-02-20 0:17
 */
@Controller
@RequestMapping("/enterpriseUnitApply")
public class EnterpriseUnitApplyController extends BaseOilProInstallController {
    @Autowired
    private OilProApplyInfoService oilProApplyInfoService;
    @Autowired
    private OilProGeneralSituationService oilProGeneralSituationService;
    @Autowired
    private OilProEngineeAwardService oilProEngineeAwardService;
    @Autowired
    private OilProDesignAwardService oilProDesignAwardService;
    @Autowired
    private OilProUnitOpinionService oilProUnitOpinionService;
    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    private BootdoConfig bootdoConfig;
    @Autowired
    private OilQualityConfirmFileService qualityConfirmFileService;
    @Autowired
    private OilProInstallReviewRecordService oilProInstallReviewRecordService;

    private String prefix = "engineering/installation_award";


    /**
     * 跳转到要个人申报的项目列表
     *
     * @param map
     * @return
     */
    @RequestMapping("/to_apply_personal_pros")
    @RequiresPermissions("act:award:apply_pros")
    public String toApplyPersonalProList(ModelMap map) {
        map.put("apply_type", "personal");
        applyParams(map);

        return "act/award/chengguo_personal/enterprise_personal_pro_list";
    }


    /**
     * 跳转到要 安装工程证实性文件上传
     *
     * @param map
     * @return
     */
    @RequestMapping("/to_upload_real_file")
    public String toUploadFileList(@RequestParam Map<String, Object> params, ModelMap map) {
        applyParams(map, params);
        String fileType = EnumProjectType.OIL_PRO_INSTALL.getProType() + "_confirm";
        map.put("fileType", fileType);
        params.remove("applyId");
        List<OilQualityConfirmFileDO> qualityConfirmFileDOList = qualityConfirmFileService.list(params);
        map.put("docUploadDoList", qualityConfirmFileDOList);

        ArrayList arrayList = new ArrayList();
        arrayList.add("proof_material_cover");
        arrayList.add("commitment");
        arrayList.add("table_contents");
        arrayList.add("main_qualification_certificate");
        arrayList.add("project_approval_documents");
        arrayList.add("project_construction_approval_document");
        arrayList.add("project_quality_assessment_quality_supervision");
        arrayList.add("project_completion_acceptance_documents");
        arrayList.add("project_completion_acceptance_filing_documents");
        arrayList.add("project_completion_final_accounts_audit_report");
        arrayList.add("safety_quality_accidents_wages_owed_migrant_workers");
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


        return prefix + "/quality_install_file_upload";
    }


    /**
     * 跳转到“01、申报单位列表”页面
     *
     * @param params
     * @param map
     * @return
     */
    @RequestMapping("/toUnitList")
//    @RequiresPermissions("petroleumEngineering:enterpriseUnitApply:toUnitList") //TODO 权限暂不做限制
    public String toUnitListPage(@RequestParam Map<String, Object> params, ModelMap map) {
        applyParams(map, params);
        params.put("taskId", map.get("taskId"));
        int curPageNum = pageNumPackage(params, map);
        //查询列表数据
        Query query = new Query(params);
        query.remove("applyId");
        List<OilProApplyInfoDO> oilProApplyInfoList = oilProApplyInfoService.list(query);
        if (oilProApplyInfoList.size() == 0 && curPageNum > 1) {
            map.put("pageNum", curPageNum - 1);
        }
        int total = oilProApplyInfoService.count(query);
        PageUtils pageUtils = new PageUtils(oilProApplyInfoList, total);
        map.put("pageUtils", pageUtils);
        return prefix + "/unit_apply_list_01";
    }


    /**
     * 跳转到“01、申报单位新增”
     * 如果一个奖项其余的概况等未填写完，则返回当前项目下的奖项，只能进行修改操作
     *
     * @param map
     * @param request
     * @return
     */
    @RequestMapping("/toAdd")
    public String toAddPage(@RequestParam Map<String, Object> params, ModelMap map, HttpServletRequest request) {
        applyParams(map, params);
        Object proIdObj = map.get("proId");
        int proId = proIdObj != null ? Integer.parseInt(proIdObj.toString()) : 0;
        List<OilProApplyInfoDO> list = proId > 0 ? oilProApplyInfoService.getByProId(proId) : null;
        OilProApplyInfoDO applyInfoDO = new OilProApplyInfoDO();
        if (list != null && list.size() > 0) {
            applyInfoDO = list.get(0);
        }
        map.put("data", applyInfoDO);
        return prefix + "/unit_apply_add_01";
    }

    /**
     * 跳转到“01、申报单位新增”
     *
     * @param map
     * @param request
     * @return
     */
    @RequestMapping("/toEdit")
    public String toEditPage(@RequestParam Map<String, Object> params, ModelMap map, HttpServletRequest request) {
        map.put("tagName", "修改");
        applyParams(map, params);
        int id = Integer.parseInt(params.get("id").toString());
        OilProApplyInfoDO oilProApplyInfoDO = oilProApplyInfoService.get(id);
        map.put("data", oilProApplyInfoDO);
        return prefix + "/unit_apply_add_01";
    }


    /**
     * 跳转到“01、申报单位新增”
     *
     * @param map
     * @param params
     * @return
     */
    @RequestMapping("/toPrint")
    @RequiresPermissions("petroleumEngineering:enterpriseUnitApply:toPrint")
    public void toPrintPage(ModelMap map, @RequestParam Map<String, Object> params, HttpServletResponse response, HttpServletRequest request) throws IOException {
        PetroleumEngineDocBaseDataWord baseDataWord = new PetroleumEngineDocBaseDataWord();
        //获取word的内容
        Object idObj = params.get("id");
        int id = idObj != null ? Integer.parseInt(idObj.toString()) : 0;
        OilProApplyInfoDO applyInfoDO = oilProApplyInfoService.get(id);
        if (applyInfoDO == null) {
            try {
                request.getRequestDispatcher("/error/err?msg=申报单位未填写").forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            }
            return;
        }
        baseDataWord.setProApplyInfoDO(applyInfoDO);

        //获取列表信息
        File templateFile = PathUtil.getJarResourceFile(ConstantCommonData.TEMPLATE_DOC_ENGINEERING_OIL_INSTALL_APPLY_CLASS_PATH);
        if (templateFile == null) {
            Resource resource = resourceLoader.getResource(ConstantCommonData.TEMPLATE_DOC_ENGINEERING_OIL_INSTALL_APPLY_CLASS_PATH);
            templateFile = resource.getFile();
        }
        String fName = templateFile.getName();
        String[] fNameArr = fName.split("\\.");
        String tmpFileName = applyInfoDO.getComanyName() + "安装工程_" + System.currentTimeMillis() + RandomStringUtils.randomAlphabetic(5) + "." + fNameArr[1];
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
        List<OilProUnitOpinionDO> unitOpinionDOList = oilProUnitOpinionService.getByProId(proId);
        baseDataWord.setOpinionDOList(unitOpinionDOList);
        List<OilProGeneralSituationDO> situationDOList = oilProGeneralSituationService.getByProId(proId);
        OilProGeneralSituationDO situationDO = situationDOList.size() > 0 ? situationDOList.get(0) : new OilProGeneralSituationDO();

        params.put("offset", 0);
        params.put("limit", 20);
        Query query = new Query(params);
        //获取列表不能使用概况的id
        query.remove("id");
        query.remove("applyId");
        query.put("proId", proId);
        query.put("taskId", taskId);
        List<OilProEngineeAwardDO> engineeAwardDOItemList = oilProEngineeAwardService.list(query);
        List<OilProDesignAwardDO> designAwardDOItemList = oilProDesignAwardService.list(query);
        situationDO.setEngineeAwardList(engineeAwardDOItemList);
        situationDO.setDesignAwardList(designAwardDOItemList);
        baseDataWord.setSituationDO(situationDO);

        PoiWordOilProUtils.createOilProWord(templateFile.getAbsolutePath(), storePath, baseDataWord);

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
     * 跳转到“02、申报工程概况列表”
     *
     * @param map
     * @param params
     * @return
     */
    @RequestMapping("/toEngineeDescList")
    public String toEngineeDescList(@RequestParam Map<String, Object> params, ModelMap map) {
        applyParams(map, params);
        int curPageNum = pageNumPackage(params, map);
        Query query = new Query(params);
        query.remove("applyId");
        List<OilProGeneralSituationDO> oilProGeneralSituationList = oilProGeneralSituationService.list(query);
        if (oilProGeneralSituationList.size() == 0 && curPageNum > 1) {
            curPageNum = curPageNum - 1;
            params.put("pageNum", curPageNum);
            pageNumPackage(params, map);
            query = new Query(params);
            oilProGeneralSituationList = oilProGeneralSituationService.list(query);
        }
        int total = oilProGeneralSituationService.count(query);
        PageUtils pageUtils = new PageUtils(oilProGeneralSituationList, total);
        map.put("pageUtils", pageUtils);
        map.put("pageNum", curPageNum);
        return prefix + "/unit_enginee_desc_list_02";
    }

    /**
     * 跳转到“02、申报工程概况新增”
     *
     * @param map
     * @param request
     * @return
     */
    @RequestMapping("/toEngineeDescAdd")
//    @RequiresPermissions("petroleumEngineering:enterpriseUnitApply:toEngineeDescAdd")
    public String toEngineeDescAdd(@RequestParam Map<String, Object> params, ModelMap map, HttpServletRequest request) {
        boolean isApply = isTaskIsApply(map, params, false);
        if(!isApply) {
            return errPage;
        }

        int proId = applyParams(map, params);

        OilProGeneralSituationDO situationDO = new OilProGeneralSituationDO();
        OilProDesignAwardDO designAwardDO = new OilProDesignAwardDO();
        OilProEngineeAwardDO engineeAwardDO = new OilProEngineeAwardDO();

        List<OilProGeneralSituationDO> situationDOList = proId > 0 ? oilProGeneralSituationService.getByProId(proId) : new ArrayList<>();
        if (situationDOList != null && situationDOList.size() > 0) {
            situationDO = situationDOList.get(0);
            Integer engineeDescId = situationDO.getId();

            params.put("offset", 0);
            params.put("limit", 20);
            Query query = new Query(params);
            //获取列表不能使用概况的id
            query.remove("id");
            query.remove("applyId");
            query.put("proId", situationDO.getProId());
            query.put("taskId", situationDO.getTaskId());
            query.put("engineeDescId", engineeDescId);

            List<OilProEngineeAwardDO> engineeAwardDOItemList = oilProEngineeAwardService.list(query);
            List<OilProDesignAwardDO> designAwardDOItemList = oilProDesignAwardService.list(query);
            situationDO.setEngineeAwardList(engineeAwardDOItemList);
            situationDO.setDesignAwardList(designAwardDOItemList);

            if (designAwardDOItemList != null && designAwardDOItemList.size() > 0) {
                designAwardDO = designAwardDOItemList.get(0);
            }
            if (engineeAwardDOItemList != null && engineeAwardDOItemList.size() > 0) {
                engineeAwardDO = engineeAwardDOItemList.get(0);
            }
        }

        map.put("data", situationDO);
        map.put("designAward", designAwardDO);
        map.put("engineeAward", engineeAwardDO);
        return prefix + "/unit_enginee_desc_add_02";
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
        OilProInstallReviewRecordDO reviewRecordDO = new OilProInstallReviewRecordDO();
        Object idObj = params.get("reviewId");
        int id = idObj != null ? Integer.parseInt(idObj.toString()) : 0;
        if (id > 0) {
            reviewRecordDO = oilProInstallReviewRecordService.get(id);
        }
        int proId = proIdObj == null ? 0 : Integer.parseInt(proIdObj.toString());
        if (proId > 0) {
            Object readonlyObj = params.get("readonly");
            if(id == 0 && readonlyObj != null && "1".equals(readonlyObj.toString())) {
                Map<String, Object> paramMap = new HashMap<>();
                paramMap.put("proId", proId);
                paramMap.put("sort", "id");
                paramMap.put("order", "desc");
                List<OilProInstallReviewRecordDO> curReviewList = oilProInstallReviewRecordService.list(paramMap);
                if (curReviewList.size() > 0) {
                    reviewRecordDO = curReviewList.get(0);
                }
            }
            ReviewInstallProBaseInfo baseInfoDO = oilProInstallReviewRecordService.getReviewProBaseInfoByProId(proId);
            if (baseInfoDO != null) {
                reviewRecordDO.setApplyUnit(baseInfoDO.getUnitName());
                reviewRecordDO.setApplyAwardName(baseInfoDO.getProjectName());
            }
        }
        map.put("data", reviewRecordDO);
        return prefix + "/review_install_pro";
    }

    /**
     * 跳转到“02、申报工程概况打印”
     *
     * @param map
     * @param request
     * @return
     */
    @RequestMapping("/toEngineeDescEdit")
//    @RequiresPermissions("petroleumEngineering:enterpriseUnitApply:toEngineeDescEdit")
    public String toEngineeDescEdit(@RequestParam Map<String, Object> params, ModelMap map, HttpServletRequest request) {
        map.put("tagName", "修改");
        map.put("readonly", params.get("readonly"));
        packageAwardTaskId(map, params);
        int id = Integer.parseInt(params.get("id").toString());
        OilProGeneralSituationDO oilProGeneralSituationDO = oilProGeneralSituationService.get(id);
        pageNumPackage(params, map);
        Query query = new Query(params);
        //获取列表不能使用概况的id
        query.remove("id");
        query.put("engineeDescId", id);

        List<OilProEngineeAwardDO> engineeAwardDOList = oilProEngineeAwardService.list(query);
        List<OilProDesignAwardDO> designAwardDOList = oilProDesignAwardService.list(query);
        oilProGeneralSituationDO.setEngineeAwardList(engineeAwardDOList);
        oilProGeneralSituationDO.setDesignAwardList(designAwardDOList);

        map.put("data", oilProGeneralSituationDO);
        map.put("designAward", new OilProDesignAwardDO());
        map.put("engineeAward", new OilProEngineeAwardDO());

        return prefix + "/unit_enginee_desc_add_02";
    }

    /**
     * 跳转到“02、申报工程概况打印”
     *
     * @param map
     * @param request
     * @return
     */
    @RequestMapping("/toEngineeDescPrint")
    public String toEngineeDescPrint(Integer id, ModelMap map, HttpServletRequest request) {
//        OilProGeneralSituationDO oilProGeneralSituationDO = oilProGeneralSituationService.get(id);
//        map.put("data", oilProGeneralSituationDO);
        return prefix + "/unit_enginee_desc_print_02";
    }

    /**
     * 跳转到“03、有关单位意见列表”
     *
     * @param map
     * @param params
     * @return
     */
    @RequestMapping("/toUnitOpinionList")
    public String toUnitOpinionList(@RequestParam Map<String, Object> params, ModelMap map) {
        applyParams(map, params);
        int curPageNum = pageNumPackage(params, map);
        //查询列表数据
        Query query = new Query(params);
        query.remove("applyId");
        List<OilProUnitOpinionDO> oilProUnitOpinionDOList = oilProUnitOpinionService.list(query);
        if (oilProUnitOpinionDOList.size() == 0 && curPageNum > 1) {
            map.put("pageNum", curPageNum - 1);
        }
        int total = oilProApplyInfoService.count(query);
        PageUtils pageUtils = new PageUtils(oilProUnitOpinionDOList, total);
        map.put("pageUtils", pageUtils);
        return prefix + "/unit_opinion_list_03";
    }

    /**
     * 跳转到“01、申报单位新增”
     *
     * @param map
     * @param request
     * @return
     */
    @RequestMapping("/toOpinionEdit")
    public String tOpinionEditPage(@RequestParam Map<String, Object> params, ModelMap map, HttpServletRequest request) {
        map.put("tagName", "修改");
        Object tagNameObj = params.get("tagName");
        if (tagNameObj != null) {
            map.put("tagName", tagNameObj);
        }
        int id = Integer.parseInt(params.get("id").toString());
        applyParams(map, params);
        OilProUnitOpinionDO oilProUnitOpinionDO = oilProUnitOpinionService.get(id + "");
        map.put("data", oilProUnitOpinionDO);
        return prefix + "/unit_opinion_add_03";
    }

    /**
     * 跳转到“03、有关单位意见新增”
     *
     * @param map
     * @param request
     * @return
     */
    @RequestMapping("/toUnitOpinionAdd")
    public String toUnitOpinionAdd(@RequestParam Map<String, Object> params, ModelMap map, HttpServletRequest request) {
        applyParams(map, params);
        /*Object proIdObj = map.get("proId");
        int proId = proIdObj != null ? Integer.parseInt(proIdObj.toString()) : 0;
        List<OilProUnitOpinionDO> unitOpinionDOList = proId > 0 ? oilProUnitOpinionService.getByProId(proId) : null;*/
        OilProUnitOpinionDO oilProUnitOpinionDO = new OilProUnitOpinionDO();
        /*if (unitOpinionDOList != null && unitOpinionDOList.size() > 0) {
            oilProUnitOpinionDO = unitOpinionDOList.get(0);
            map.put("desc", "请先修改，完成奖项其他内容填报才可添加新的内容");
        }*/
        map.put("data", oilProUnitOpinionDO);
        return prefix + "/unit_opinion_add_03";
    }

    /**
     * 跳转到“03、有关单位意见新增”
     *
     * @param map
     * @param request
     * @return
     */
    @RequestMapping("/toUnitOpinionPrint")
    public String toUnitOpinionPrint(ModelMap map, HttpServletRequest request) {
        //TODO 获取列表信息
        return prefix + "/unit_opinion_print_03";
    }

}
