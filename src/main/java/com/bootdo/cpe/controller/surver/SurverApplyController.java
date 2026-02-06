package com.bootdo.cpe.controller.surver;

import com.bootdo.activiti.domain.EnterpriseDocUploadDo;
import com.bootdo.activiti.domain.EnterpriseProjectInfoDo;
import com.bootdo.activiti.service.AwardEnterpriseProjectService;
import com.bootdo.common.controller.BaseSurverController;
import com.bootdo.common.service.FileService;
import com.bootdo.common.utils.DateUtils;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.cpe.domain.*;
import com.bootdo.cpe.service.*;
import com.bootdo.cpe.utils.PathUtil;
import com.bootdo.cpe.utils.PoiWordSurveyProUtils;
import com.bootdo.system.config.ConstantCommonData;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bootdo.common.config.BootdoConfig;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author houzb
 * @version 1.0
 * @date 2022-03-24 7:20
 */
@Controller
@RequestMapping("/surverApply")
public class SurverApplyController extends BaseSurverController {
    private String prefix = "cpe/survey";
    @Autowired
    private SurverDesignApplyUserBaseInfoService surverApplyUserBaseInfoService;
    @Autowired
    private SurverDesignApplyTableInfoService surverDesignApplyTableInfoService;
    @Autowired
    private SurverDesiginMajorMaterialsUseInfoService surverDesiginMajorMaterialsUseInfoService;
    @Autowired
    private SurverDesignProQualityInfoService surverDesignProQualityInfoService;
    @Autowired
    private SurverDesignCommonProUseInfoService surverDesignCommonProUseInfoService;
    @Autowired
    private SurverDesignWasteDischargeInfoService surverDesignWasteDischargeInfoService;
    @Autowired
    private SurverDesiginContributeUserInfoService surverDesiginContributeUserInfoService;
    @Autowired
    private SurverDesignApplyProjectProfileService surverDesignApplyProjectProfileService;
    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    private BootdoConfig bootdoConfig;

    @Autowired
    private SurverAwardService surverAwardService;



    @Autowired
    private FileService fileService;
    @Autowired
    private AwardEnterpriseProjectService awardEnterpriseProjectService;

    @RequestMapping("/toApplyDesign")
    public String toApplyDesign(ModelMap map, @RequestParam Map<String, Object> params) {
        params.put("proSubType", "design");
        packageAwardTaskId(map, params);
        return prefix + "/apply/apply_design_award_main";
    }

    @RequestMapping("/toApplyTable")
    public String toApplyTable(ModelMap map, @RequestParam Map<String, Object> params) {
        packageAwardTaskId(map, params);
        List<SurverDesignApplyTableInfoDO> tableInfoDOList = surverDesignApplyTableInfoService.list(params);
        map.put("table", tableInfoDOList.size() > 0 ? tableInfoDOList.get(0) : new SurverDesignApplyTableInfoDO());
        return prefix + "/apply/apply_design_apply_table_info";
    }

    @RequestMapping("/toApplyAppendix")
    public String toApplyAppendix(ModelMap map, @RequestParam Map<String, Object> params) {
        packageAwardTaskId(map, params);
        String proId = (String) params.get("proId");
        List<EnterpriseDocUploadDo> docUploadDoList = fileService.listUploadEnterpriseDocs(params);
        map.put("docUploadDoList",docUploadDoList);
        map.put("fileSize",docUploadDoList.size());
        EnterpriseProjectInfoDo projectInfoDo = awardEnterpriseProjectService.get(proId);
        map.put("projectInfo",projectInfoDo == null ? new EnterpriseProjectInfoDo() : projectInfoDo);
        return prefix + "/apply/apply_design_appendix";
    }
    @RequestMapping("/toApplySupport")
    public String toApplySupport(ModelMap map, @RequestParam Map<String, Object> params) {
        packageAwardTaskId(map, params);
        String proId = (String) params.get("proId");
        List<EnterpriseDocUploadDo> docUploadDoList = fileService.listUploadEnterpriseDocs(params);
        map.put("docUploadDoList",docUploadDoList);
        map.put("fileSize",docUploadDoList.size());
        EnterpriseProjectInfoDo projectInfoDo = awardEnterpriseProjectService.get(proId);
        map.put("projectInfo",projectInfoDo == null ? new EnterpriseProjectInfoDo() : projectInfoDo);
        return prefix + "/apply/apply_design_support";
    }


    @RequestMapping("/toProDesc")
    public String toProDesc(ModelMap map, @RequestParam Map<String, Object> params) {
        packageAwardTaskId(map, params);
        Query query = new Query(params);
        List<SurverDesignApplyProjectProfileDO> projectProfileDOList = surverDesignApplyProjectProfileService.list(query);
        map.put("list", projectProfileDOList);
        return prefix + "/apply/apply_design_pro_desc_list";
    }

    @RequestMapping("/toApplyCommonProUseAdd")
    public String toApplyCommonProUseAdd(ModelMap map, @RequestParam Map<String, Object> params) {
        packageAwardTaskId(map, params);
        map.put("proUseInfoDO", new SurverDesignCommonProUseInfoDO());
        return prefix + "/apply/apply_design_common_pro_use_add";
    }

    @RequestMapping("/toProDescAdd")
    public String toProDescAdd(ModelMap map, @RequestParam Map<String, Object> params) {
        packageAwardTaskId(map, params);
        map.put("proDescDO", new SurverDesignApplyProjectProfileDO());
        return prefix + "/apply/apply_design_pro_desc_add";
    }

     @RequestMapping("/toApplyCommonProUseView")
    public String toApplyCommonProUseView(ModelMap map, @RequestParam Map<String, Object> params) {
         packageAwardTaskId(map, params);
         map.put("isReadonly", 1);
         int id = Integer.parseInt(params.get("id").toString());
         SurverDesignCommonProUseInfoDO proUseInfoDO = surverDesignCommonProUseInfoService.get(id);
         map.put("proUseInfoDO", proUseInfoDO);
        return prefix + "/apply/apply_design_common_pro_use_add";
    }

    @RequestMapping("/toApplyCommonProUseList")
    public String toApplyCommonProUseList(ModelMap map, @RequestParam Map<String, Object> params) {
        packageAwardTaskId(map, params);
        Query query = new Query(params);
        List<SurverDesignCommonProUseInfoDO> proUseInfoDOList = surverDesignCommonProUseInfoService.list(query);
		map.put("list", proUseInfoDOList);
        return prefix + "/apply/apply_design_common_pro_use_list";
    }

    @RequestMapping("/toApplyMajorMaterialsUseAdd")
    public String toApplyMajorMaterialsUseAdd(ModelMap map, @RequestParam Map<String, Object> params) {
        packageAwardTaskId(map, params);
        map.put("majorMaterials", new SurverDesiginMajorMaterialsUseInfoDO());
        return prefix + "/apply/apply_design_major_materials_use_add";
    }
    @RequestMapping("/toApplyMajorMaterialsUseView")
    public String toApplyMajorMaterialsUseView(ModelMap map, @RequestParam Map<String, Object> params) {
        packageAwardTaskId(map, params);
        map.put("isReadonly", 1);
        int id = Integer.parseInt(params.get("id").toString());
        SurverDesiginMajorMaterialsUseInfoDO majorMaterialsUseInfoDO = surverDesiginMajorMaterialsUseInfoService.get(id);
        map.put("majorMaterials", majorMaterialsUseInfoDO);
        return prefix + "/apply/apply_design_major_materials_use_add";
    }
    @RequestMapping("/toApplyMajorMaterialsUseList")
    public String toApplyMajorMaterialsUseList(ModelMap map, @RequestParam Map<String, Object> params) {
        packageAwardTaskId(map, params);
        Query query = new Query(params);
        List<SurverDesiginMajorMaterialsUseInfoDO> surverDesiginMajorMaterialsUseInfoList = surverDesiginMajorMaterialsUseInfoService.list(query);
		map.put("list", surverDesiginMajorMaterialsUseInfoList);
        return prefix + "/apply/apply_design_major_materials_use_list";
    }
    @RequestMapping("/toApplyProQualityAdd")
    public String toApplyProQualityAdd(ModelMap map, @RequestParam Map<String, Object> params) {
        packageAwardTaskId(map, params);
        map.put("proQualityInfoDO", new SurverDesignProQualityInfoDO());
        return prefix + "/apply/apply_design_pro_quality_add";
    }
    @RequestMapping("/toApplyProQualityView")
    public String toApplyProQualityView(ModelMap map, @RequestParam Map<String, Object> params) {
        packageAwardTaskId(map, params);
        map.put("isReadonly", 1);
        int id = Integer.parseInt(params.get("id").toString());
        SurverDesignProQualityInfoDO proQualityInfoDO = surverDesignProQualityInfoService.get(id);
        map.put("proQualityInfoDO", proQualityInfoDO);
        return prefix + "/apply/apply_design_pro_quality_add";
    }
    @RequestMapping("/toApplyProQualityList")
    public String toApplyProQualityList(ModelMap map, @RequestParam Map<String, Object> params) {
        packageAwardTaskId(map, params);
        Query query = new Query(params);
        List<SurverDesignProQualityInfoDO> surverDesiginMajorMaterialsUseInfoList = surverDesignProQualityInfoService.list(query);
		map.put("list", surverDesiginMajorMaterialsUseInfoList);
        return prefix + "/apply/apply_design_pro_quality_list";
    }

    @RequestMapping("/toApplyWasteDischargeAdd")
    public String toApplyWasteDischargeAdd(ModelMap map, @RequestParam Map<String, Object> params) {
        packageAwardTaskId(map, params);
        map.put("wasteInfo", new SurverDesignWasteDischargeInfoDO());
        return prefix + "/apply/apply_design_waste_discharge_add";
    }
    @RequestMapping("/toApplyWasteDischargeView")
    public String toApplyWasteDischargeView(ModelMap map, @RequestParam Map<String, Object> params) {
        packageAwardTaskId(map, params);
        int id = Integer.parseInt(params.get("id").toString());
        SurverDesignWasteDischargeInfoDO wasteDischargeInfoDO = surverDesignWasteDischargeInfoService.get(id);
        map.put("wasteInfo", wasteDischargeInfoDO);
        return prefix + "/apply/apply_design_waste_discharge_add";
    }
    @RequestMapping("/toApplyWasteDischargeList")
    public String toApplyWasteDischargeList(ModelMap map, @RequestParam Map<String, Object> params) {
        packageAwardTaskId(map, params);
        Query query = new Query(params);
        List<SurverDesignWasteDischargeInfoDO> wasteDischargeInfoDOList = surverDesignWasteDischargeInfoService.list(query);
        map.put("list", wasteDischargeInfoDOList);
        return prefix + "/apply/apply_design_waste_discharge_list";
    }

    @RequestMapping("/toApplyContributeUserList")
    public String toApplyContributeUserList(ModelMap map, @RequestParam Map<String, Object> params) {
        packageAwardTaskId(map, params);
        Query query = new Query(params);
        List<SurverDesiginContributeUserInfoDO> contributeUserInfoDOList = surverDesiginContributeUserInfoService.list(query);
		map.put("list", contributeUserInfoDOList);
        return prefix + "/apply/apply_design_contribute_user_list";
    }

    @RequestMapping("/toApplyContributeUserView")
    public String toApplyContributeUserView(ModelMap map, @RequestParam Map<String, Object> params) {
        packageAwardTaskId(map, params);
        int id = Integer.parseInt(params.get("id").toString());
        SurverDesiginContributeUserInfoDO contributeUserInfoDO = surverDesiginContributeUserInfoService.get(id);
        map.put("contributeUserInfo", contributeUserInfoDO);
        return prefix + "/apply/apply_design_contribute_user_add";
    }

    @RequestMapping("/toApplyContributeUserAdd")
    public String toApplyContributeUserAdd(ModelMap map, @RequestParam Map<String, Object> params) {
        packageAwardTaskId(map, params);
        map.put("contributeUserInfo", new SurverDesiginContributeUserInfoDO());
        return prefix + "/apply/apply_design_contribute_user_add";
    }

    @RequestMapping("/toBaseInfoList")
    public String toListApplyBaseInfo(ModelMap map, @RequestParam Map<String, Object> params) {
        packageAwardTaskId(map, params);
        boolean isApply = isTaskIsApply(map, params);
        map.put("isApply", isApply);

        int curPageNum = pageNumPackage(params, map);

        Query query = new Query(params);
        query.put("sort","id");
        query.put("order","asc");
        query.remove("applyId");
        List<SurverDesignApplyUserBaseInfoDO> applyUserBaseInfoDOList = surverApplyUserBaseInfoService.list(query);
        int total = surverApplyUserBaseInfoService.count(query);
        if (applyUserBaseInfoDOList.size() == 0 && curPageNum > 1) {
            curPageNum = curPageNum - 1;
            params.put("pageNum", curPageNum);
            pageNumPackage(params, map);
            query = new Query(params);
            applyUserBaseInfoDOList = surverApplyUserBaseInfoService.list(query);
        }

        map.put("pageNum", curPageNum);
        PageUtils pageUtils = new PageUtils(applyUserBaseInfoDOList, total);
        map.put("pageUtils", pageUtils);

        return prefix + "/apply/apply_user_base_info_list";
    }

    @RequestMapping("/toBaseInfoAadd")
    public String toAdd(ModelMap map, @RequestParam Map<String, Object> params) {
        packageAwardTaskId(map, params);
        return prefix + "/apply/apply_user_base_info_add";
    }

    @RequestMapping("/toBaseInfoView")
    public String toView(ModelMap map, @RequestParam Map<String, Object> params) {
        packageAwardTaskId(map, params);
        map.put("isReadonly", 1);
        //TODO 获取单个用户信息
        return prefix + "/apply/apply_user_base_info_add";
    }
     @RequestMapping("/toBaseInfoEdit")
    public String toEdit(ModelMap map, @RequestParam Map<String, Object> params) {
        packageAwardTaskId(map, params);
        //TODO 获取单个用户信息
        return prefix + "/apply/apply_user_base_info_add";
    }



    /**
     * 打印 记录
     * @return
     */
    @RequestMapping("/print/proinfo")
    public String printProInfo(String  id ,ModelMap map, @RequestParam Map<String, Object> params, HttpServletResponse response) {
        packageAwardTaskId(map, params);
        Object proIdObj = id;
        //待下载文件名
        //设置为png格式的文件
        response.setHeader("content-type", "application/msword");
        response.setContentType("application/octet-stream");
        byte[] buff = new byte[1024];
        //创建缓冲输入流
        BufferedInputStream bis = null;
        OutputStream outputStream = null;
        File storeFile = null;
        try {
            Map<String, Object> groupParamsMap = new HashMap<>();
            groupParamsMap.put("proId", proIdObj);
            groupParamsMap.put("eType", "design");
            groupParamsMap.put("taskId", params.get("taskId"));
            groupParamsMap.put("proType", params.get("proType"));

            outputStream = response.getOutputStream();

//            石油工程建设优秀设计奖申报表格 SurverDesignApplyTableInfoDO
//            主要原材料消耗定额对比 SurverDesiginMajorMaterialsUseInfoDO
//            产品质量指标对比 SurverDesignProQualityInfoDO
//            主要公用工程消耗定额对比列表 SurverDesignCommonProUseInfoDO
//            废水 液 废气 废渣 排放量及排放指标对比 SurverDesignWasteDischargeInfoDO
//            贡献的主要人员情况列表 SurverDesiginContributeUserInfoDO
//            项目简介列表 SurverDesignApplyProjectProfileDO



            List<SurverProjectInfo> proDataDtoList = surverAwardService.listProInfo(groupParamsMap);


            List<SurverDesignApplyTableInfoDO> tableInfoDOList = surverDesignApplyTableInfoService.list(groupParamsMap);//石油工程优秀设计申报表格
              SurverDesignApplyTableInfoDO surverDesignApplyTableInfoDO = tableInfoDOList.size() >0 ? tableInfoDOList.get(0) : new SurverDesignApplyTableInfoDO();
            List<SurverDesiginMajorMaterialsUseInfoDO> surverDesiginMajorMaterialsUseInfoList = surverDesiginMajorMaterialsUseInfoService.list(groupParamsMap);// //  主要原材料消耗定额对比

            List<SurverDesignProQualityInfoDO>   designQualitys = surverDesignProQualityInfoService.list(groupParamsMap);//  产品质量指标列表

            List<SurverDesignCommonProUseInfoDO> proUseInfoDOList = surverDesignCommonProUseInfoService.list(groupParamsMap);// 工程消耗定额对比
            List<SurverDesiginContributeUserInfoDO> contributeUserInfoDOList = surverDesiginContributeUserInfoService.list(groupParamsMap);// 主要人员情况列表

            List<SurverDesignWasteDischargeInfoDO>  surverDesignWasteDischargeInfoDOS =  surverDesignWasteDischargeInfoService.list(groupParamsMap);;// 废水

            List<SurverDesignApplyProjectProfileDO> projectProfileDOList = surverDesignApplyProjectProfileService.list(groupParamsMap);

            SurveyDesignDataWord word = new SurveyDesignDataWord();
            word.setContributeUsers(contributeUserInfoDOList);
            word.setSurverDesignApplyProjectProfileDOS(projectProfileDOList);
            word.setDesignProUseInfoDOS(proUseInfoDOList);
            word.setDesignWasteDischargeInfoDOS(surverDesignWasteDischargeInfoDOS);
            word.setMajorMaterialsUse(surverDesiginMajorMaterialsUseInfoList);
            word.setDesignQualitys(designQualitys);
            word.setInfo(surverDesignApplyTableInfoDO);
            word.setPro( proDataDtoList.size() >0 ? proDataDtoList.get(0) : new SurverProjectInfo());

            File templateFile = PathUtil.getJarResourceFile(ConstantCommonData.TEMPLATE_DOC_SURVEY_DESIGN_CLASS_PATH);
            if (templateFile == null) {
                Resource resource = resourceLoader.getResource(ConstantCommonData.TEMPLATE_DOC_SURVEY_DESIGN_CLASS_PATH);
                templateFile = resource.getFile();
            }

            String fName = templateFile.getName();
            String[] fNameArr = fName.split("\\.");
            String tmpFileName = fNameArr[0] + "_" + System.currentTimeMillis() + RandomStringUtils.randomAlphabetic(5) + "." + fNameArr[1];
            tmpFileName = URLEncoder.encode(tmpFileName, "UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + tmpFileName);
            String storePath = bootdoConfig.getUploadPath() + "/print_doc/" + DateUtils.getCurDate();
            storeFile = new File(storePath);
            if (!storeFile.exists()) {
                storeFile.mkdirs();
            }
            storePath = storePath + "/" + tmpFileName;
            storeFile = new File(storePath);
            FileUtils.copyFile(templateFile, storeFile);

            PoiWordSurveyProUtils.createDesignWord(templateFile.getAbsolutePath(), storePath, word);


            InputStream inputStream = new FileInputStream(storeFile);
            //这个路径为待下载文件的路径
            bis = new BufferedInputStream(inputStream);
            int read = bis.read(buff);

            //通过while循环写入到指定了的文件夹中
            while (read != -1) {
                outputStream.write(buff, 0, buff.length);
                outputStream.flush();
                read = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
            //出现异常返回给页面失败的信息
            map.addAttribute("result", "下载失败");
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (storeFile != null) {
                storeFile.deleteOnExit();
            }
        }
        //成功后返回成功信息
        map.addAttribute("result", "下载成功");
        return "";
    }



}
