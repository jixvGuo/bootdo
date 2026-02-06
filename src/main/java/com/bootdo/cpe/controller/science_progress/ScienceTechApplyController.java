package com.bootdo.cpe.controller.science_progress;

import com.bootdo.activiti.controller.AwardFlowController;
import com.bootdo.activiti.domain.EnterpriseDocUploadDo;
import com.bootdo.activiti.domain.EnterpriseProjectInfoDo;
import com.bootdo.activiti.service.AwardEnterpriseProjectService;
import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.controller.BaseScienceTechnologyController;
import com.bootdo.common.domain.DictDO;
import com.bootdo.common.service.DictService;
import com.bootdo.common.service.FileService;
import com.bootdo.common.utils.DateUtils;
import com.bootdo.common.utils.PoiWordUtils;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.cpe.domain.DocCompanyApplyWord;
import com.bootdo.cpe.service.ProjectCommonService;
import com.bootdo.cpe.utils.EnumUtils;
import com.bootdo.cpe.utils.PathUtil;
import com.bootdo.system.config.ConstantCommonData;
import com.bootdo.system.config.DictTypeConstant;
import com.bootdo.system.domain.*;
import com.bootdo.system.service.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 科学技术成果信息的处理
 */
@Controller
@RequestMapping("/chengguo_info")
public class ScienceTechApplyController extends BaseScienceTechnologyController {
    private Logger logger = LoggerFactory.getLogger(ScienceTechApplyController.class);
    String prefix = "act/award/chengguo";
    //企业申报的填写的页面前缀
    String prefixEnterpriseApply = "enterprise/apply";

    @Autowired
    private EnterpriseChengguoBaseInfoService enterpriseChengguoBaseInfoService;
    @Autowired
    private EnterpriseChengguoOtherInfoService enterpriseChengguoOtherInfoService;
    @Autowired
    private AwardEnterpriseProjectService awardEnterpriseProjectService;



    @Autowired
    private AwardFlowController awardFlowController;
    @Autowired
    private EnterpriPersonalInfoService enterpriPersonalInfoService;
    @Autowired
    private FileService fileService;
    @Autowired
    private DictService dictService;
    @Autowired
    private EnterpriseScienceAwardKnowledgeInfoService awardKnowledgeInfoService;
    @Autowired
    ResourceLoader resourceLoader;
    @Autowired
    private ProjectCommonService projectCommonService;
    @Autowired
    private BootdoConfig bootdoConfig;
    @Autowired
    private AwardStyleValidateScienceService awardStyleValidateScienceService;

    @RequestMapping("/edit_info")
    public String chengguoEditInfo(ModelMap map, @RequestParam Map<String, Object> params){

        packageAwardTaskId(map, params);

        String eType = (String) params.get("eType");
        String page = (String) params.get("page");
        String proId = (String) params.get("proId");
        if("base_declaration".equals(eType)) {
            List<EnterpriseChengguoBaseInfoDO> enterpriseChengguoBaseInfoDOList = enterpriseChengguoBaseInfoService.list(params);
            EnterpriseChengguoBaseInfoDO enterpriseChengguoBaseInfo = enterpriseChengguoBaseInfoDOList.size() > 0 ? enterpriseChengguoBaseInfoDOList.get(0) : null;
            map.put("enterpriseChengguoBaseInfo",enterpriseChengguoBaseInfo == null ? new EnterpriseChengguoBaseInfoDO() : enterpriseChengguoBaseInfo);
        }else {
            List<EnterpriseChengguoOtherInfoDO> otherInfoDOList = enterpriseChengguoOtherInfoService.list(params);
            EnterpriseChengguoOtherInfoDO otherInfoDO = otherInfoDOList.size() > 0 ? otherInfoDOList.get(0) : new EnterpriseChengguoOtherInfoDO();
            map.put("otherInfo", otherInfoDO);
        }

        EnterpriseProjectInfoDo projectInfoDo = awardEnterpriseProjectService.get(proId);
        projectInfoDo.initApplyStat();
        map.put("projectInfo",projectInfoDo);

        if(StringUtils.isNotBlank(page)) {
            return prefixEnterpriseApply + "/" +page;
        }
        return prefix + "/edit_info_"+eType;
    }


    @RequestMapping("/knowledge_right")
    public String chengguoKnowledge(ModelMap map, @RequestParam Map<String, Object> params){
        packageAwardTaskId(map, params);
        String proId = (String) params.get("proId");
        List<EnterpriseScienceAwardKnowledgeInfoDO> list = awardKnowledgeInfoService.list(params);
        map.put("knowledgeList",list);

        EnterpriseProjectInfoDo projectInfoDo = awardEnterpriseProjectService.get(proId);
        projectInfoDo.initApplyStat();
        map.put("projectInfo",projectInfoDo);

        return prefixEnterpriseApply + "/science/result_knowledge_06";
    }

    /**
     * 移除成果主要知识产权证明目录
     * @param map
     * @param params
     * @return
     */
    @RequestMapping("/knowledge_right/remove")
    @ResponseBody
    public R chengguoKnowledgeRemove(ModelMap map, @RequestParam Map<String, Object> params){
        int rst = awardKnowledgeInfoService.remove(Integer.parseInt(params.get("id").toString()));
        if(rst > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 科技进步奖 成果基本情况
     * @param map
     * @return
     */
    @RequestMapping("/base_info")
    public String chengguoBaseInfo(ModelMap map, @RequestParam Map<String, Object> params){
        packageAwardTaskId(map, params);

        String proId = (String) params.get("proId");

        List<EnterpriseChengguoBaseInfoDO> enterpriseChengguoBaseInfoDOList = enterpriseChengguoBaseInfoService.list(params);
        EnterpriseChengguoBaseInfoDO enterpriseChengguoBaseInfo = enterpriseChengguoBaseInfoDOList.size() > 0 ? enterpriseChengguoBaseInfoDOList.get(0) : null;

        map.put("enterpriseChengguoBaseInfo",enterpriseChengguoBaseInfo == null ? new EnterpriseChengguoBaseInfoDO() : enterpriseChengguoBaseInfo);
//        return prefix + "/chengguo_base_info_edit";
        //取成果所属专业
        List<DictDO> resultMajorList = dictService.listByType(DictTypeConstant.PROFESS_TYPE);

        List<DictDO> achievement_belongs = dictService.listByType(DictTypeConstant.achievement_belongs);


        //成果所属学科分类
//        List<DictDO> resultClassList = dictService.listByType(DictTypeConstant.ENTERPRISE_RESULT_CLASSIC);
        map.put("majors",resultMajorList);
        map.put("classes",achievement_belongs);

        // 保密级别
        List<DictDO> securityLevelList = dictService.listByType(DictTypeConstant.SECURITY_LEVEL);
        map.put("securityList",securityLevelList);

        EnterpriseProjectInfoDo projectInfoDo = awardEnterpriseProjectService.get(proId);
        map.put("projectInfo",projectInfoDo);
        return prefixEnterpriseApply + "/science/baseinfo_01";
    }


    /***
     * 成功简介
     * @param map
     * @return
     */
    @RequestMapping("/res_introduction")
    public String chengguoInfoIntroduction(ModelMap map, @RequestParam Map<String, Object> params){
        packageAwardTaskId(map, params);
        String proId = (String) params.get("proId");

        List<EnterpriseChengguoBaseInfoDO> enterpriseChengguoBaseInfoDOList = enterpriseChengguoBaseInfoService.list(params);
        EnterpriseChengguoBaseInfoDO enterpriseChengguoBaseInfo = enterpriseChengguoBaseInfoDOList.size() > 0 ? enterpriseChengguoBaseInfoDOList.get(0) : null;

        map.put("enterpriseChengguoBaseInfo",enterpriseChengguoBaseInfo == null ? new EnterpriseChengguoBaseInfoDO() : enterpriseChengguoBaseInfo);


        List<EnterpriseChengguoOtherInfoDO> otherInfoDOList = enterpriseChengguoOtherInfoService.list(params);
        EnterpriseChengguoOtherInfoDO otherInfoDO = otherInfoDOList.size() > 0 ? otherInfoDOList.get(0) : new EnterpriseChengguoOtherInfoDO();
        map.put("otherInfo",otherInfoDO);

        EnterpriseProjectInfoDo projectInfoDo = awardEnterpriseProjectService.get(proId);
        map.put("projectInfo",projectInfoDo);

        return prefixEnterpriseApply + "/science/result_desc_02";
    }


    /**
     * 去标志性成果列表页面
     *
     * @param map
     * @param request
     * @return
     */
    @RequestMapping("/toMainEnterprisePersonList")
    public String toMainEnterprisePersonList(ModelMap map, @RequestParam Map<String, Object> params, HttpServletRequest request) {
        packageAwardTaskId(map, params);
        List<EnterpriseChengguoOtherInfoDO> otherInfoDOList = enterpriseChengguoOtherInfoService.list(params);
        EnterpriseChengguoOtherInfoDO otherInfoDO = otherInfoDOList.size() > 0 ? otherInfoDOList.get(0) : null;
        map.put("otherInfoDOList", otherInfoDO == null ? new ArrayList<>() : otherInfoDO.getMainCompleteInfoList());
        return prefixEnterpriseApply + "/science/main_enterprise_person_list";
    }

    /**
     * 去标志性成果列表页面
     *
     * @param map
     * @param request
     * @return
     */
    @RequestMapping("/toMainEnterprisePersonAdd")
    public String toMainEnterprisePersonAdd(ModelMap map, @RequestParam Map<String, Object> params, HttpServletRequest request) {
        packageAwardTaskId(map, params);
        String proId = (String) params.get("proId");
        map.put("otherInfo",new EnterpriseChengguoOtherInfoDO());

        // 性别
        List<DictDO> genders = dictService.listByType(DictTypeConstant.GENDER_TYPE);
        map.put("genderList",genders);

        EnterpriseProjectInfoDo projectInfoDo = awardEnterpriseProjectService.get(proId);
        map.put("projectInfo",projectInfoDo);

        return prefixEnterpriseApply + "/science/result_part_person_07";
    }

    /**
     * 去标志性成果列表页面
     *
     * @param map
     * @param request
     * @return
     */
    @RequestMapping("/toMainEnterprisePersonEdit")
    public String toMainEnterprisePersonEdit(ModelMap map, @RequestParam Map<String, Object> params, HttpServletRequest request) {
        packageAwardTaskId(map, params);
        String proId = (String) params.get("proId");

        List<EnterpriseChengguoOtherInfoDO> otherInfoDOList = enterpriseChengguoOtherInfoService.list(params);
        EnterpriseChengguoOtherInfoDO otherInfoDO = otherInfoDOList.size() > 0 ? otherInfoDOList.get(0) : new EnterpriseChengguoOtherInfoDO();
        map.put("otherInfo",otherInfoDO);

        // 性别
        List<DictDO> genders = dictService.listByType(DictTypeConstant.GENDER_TYPE);
        map.put("genderList",genders);

        EnterpriseProjectInfoDo projectInfoDo = awardEnterpriseProjectService.get(proId);
        map.put("projectInfo",projectInfoDo);

        return prefixEnterpriseApply + "/science/result_part_person_07";
    }
    /**
     * 去标志性成果列表页面
     *
     * @param map
     * @param request
     * @return
     */
    @RequestMapping("/ainEnterprisePerson/remove")
    @ResponseBody
    public R toMainEnterprisePersonRemove(ModelMap map, @RequestParam Map<String, Object> params, HttpServletRequest request) {
        packageAwardTaskId(map, params);
        int id = Integer.parseInt(params.get("id").toString());
        int rst = enterpriseChengguoOtherInfoService.remove(id);
        if(rst == 0) {
            return R.error("删除失败");
        }
        return R.ok();
    }

    @RequestMapping("/main_enterprise_person")
    public String chengguoMainEnterprisePerson(ModelMap map, @RequestParam Map<String, Object> params){
        packageAwardTaskId(map, params);
        String proId = (String) params.get("proId");

        List<EnterpriseChengguoOtherInfoDO> otherInfoDOList = enterpriseChengguoOtherInfoService.list(params);
        EnterpriseChengguoOtherInfoDO otherInfoDO = otherInfoDOList.size() > 0 ? otherInfoDOList.get(0) : new EnterpriseChengguoOtherInfoDO();
        map.put("otherInfo",otherInfoDO);

        // 性别
        List<DictDO> genders = dictService.listByType(DictTypeConstant.GENDER_TYPE);
        map.put("genderList",genders);

        EnterpriseProjectInfoDo projectInfoDo = awardEnterpriseProjectService.get(proId);
        map.put("projectInfo",projectInfoDo);

        return prefixEnterpriseApply + "/science/result_part_person_07";
    }

    @RequestMapping("/commit_review_btn")
    public String chengguoSubmitReview(ModelMap map, @RequestParam Map<String, Object> params){
        packageAwardTaskId(map, params);
        String proId = (String) params.get("proId");

        String validateType = (String) params.get("validateType");
        map.put("validateType",validateType);
        map.put("isCouldStyleValidate",false);
        EnterpriseProjectInfoDo projectInfoDo = awardEnterpriseProjectService.get(proId);
        UserDO userDO = getUser();
        if(projectInfoDo!=null && userDO.getUserId().longValue() == projectInfoDo.getCreateUid().longValue()){
            //项目创建者自己查看页面，即企业用户查看
            map.put("role","enterprise_user");
            String proStat = projectInfoDo.getProStat();
            boolean isSubmitValidateBtn = projectCommonService.isViewApplyValidateBtnByStat(proStat);
            map.put("isSubmitValidateBtn",isSubmitValidateBtn);
            String statShow = EnumUtils.getProjectStatShowStrByStat(proStat);
            map.put("statShow",statShow);
        }
        List<Long> roles = userDO.getRoleIds();
        boolean isValidate = false;
        for(long roleId :roles) {
            if(roleId == 60) {
                //只有协会工作人员可以进行审核
                isValidate = true;
                map.put("isCouldStyleValidate",true);
                break;
            }
        }
        if(projectInfoDo != null) {
            map.put("proInfo", projectInfoDo);
            String associationReviewRst = projectInfoDo.getAssociationReviewRst();
            Long uid = getUserId();

            if("upload".equals(associationReviewRst) && isValidate) {
                //已提交审核
                map.put("ass_validate_pro", 1);
            }
            awardFlowController.toEnterpriseUpDoc(proId, "", map);
        }
        return prefix + "/submit_review_btn";
    }


    /**
     *
     * @return
     */
    @GetMapping("/view_chengguo")
//    @RequiresPermissions("act:award:view_chengguo")
    String toViewChengGuoInfo(ModelMap map, @RequestParam Map<String, Object> params) {
        packageAwardTaskId(map, params);
        //查询当前项目是否为个人

        List<EnterpriPersonalInfoDO> personalInfoDOList = enterpriPersonalInfoService.list(params);
        if(personalInfoDOList.size() > 0) {
            //跳转到个人奖励页面
            return "act/award/chengguo_personal/index";
        }
        return prefix + "/index";
    }

    /**
     * 上传附件
     * @return
     */
    @RequestMapping("/toUploadEnclosure")
    public String toUploadEnclosure(ModelMap map, @RequestParam Map<String, Object> params){
        packageAwardTaskId(map, params);
        String proId = (String) params.get("proId");

        List<EnterpriseDocUploadDo> docUploadDoList = fileService.listUploadEnterpriseDocs(params);
        map.put("docUploadDoList",docUploadDoList);
        map.put("fileSize",docUploadDoList.size());
        EnterpriseProjectInfoDo projectInfoDo = awardEnterpriseProjectService.get(proId);
        map.put("projectInfo",projectInfoDo);
        return prefixEnterpriseApply + "/science/enclosure_08";
    }


    /***
     * 科技企业申报打印
      *
     */
    @RequestMapping("/print")
    @RequiresPermissions("system:enterpriseChengguoBaseInfo:print")
    public String conpanyPrint(int id, HttpServletResponse response, ModelMap map, @RequestParam Map<String, Object> params){

        packageAwardTaskId(map, params);
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
            outputStream = response.getOutputStream();

            DocCompanyApplyWord word = new DocCompanyApplyWord();

            EnterpriseChengguoBaseInfoDO personalInfoDO = enterpriseChengguoBaseInfoService.getByProId(id);
            word.setpInfo(personalInfoDO);
             // 成果其他信息
            Map<String,Object> param = new HashMap<>();
            param.put("proId",params.get("id"));

            List<EnterpriseChengguoOtherInfoDO> otherInfoDOList = enterpriseChengguoOtherInfoService.list(param);
            EnterpriseChengguoOtherInfoDO otherInfoDO = otherInfoDOList.size() > 0 ? otherInfoDOList.get(0) : new EnterpriseChengguoOtherInfoDO();
            if (otherInfoDO != null){
                //将基本信息存储在第一个记录里
                word.setoInfo(otherInfoDO);
            }

            //知识权信息
            Map<String,Object> dataMap = new HashMap<>();
            dataMap.put("proId",personalInfoDO.getProId());


            List<EnterpriseScienceAwardKnowledgeInfoDO> list = awardKnowledgeInfoService.list(dataMap);

            File templateFile = PathUtil.getJarResourceFile(ConstantCommonData.TEMPLATE_DOC_SCIENCE_PROGRESS_CLASS_PATH);
            if (templateFile == null) {
                Resource resource = resourceLoader.getResource(ConstantCommonData.TEMPLATE_DOC_SCIENCE_PROGRESS_CLASS_PATH);
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

            PoiWordUtils.createScicenceWord(templateFile.getAbsolutePath(),
                    storePath,
                    word,list,otherInfoDO);

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


    /**
     * 形式审查
     * @param proId
     * @param map
     * @return
     */
    @GetMapping("/toReview")
    @RequiresPermissions("system:awardStyleValidateScience:add")
    public String toReview(String proId,Integer readonly, ModelMap map){
        map.put("readonly", readonly);
        return executeToReview(proId, map);
    }
    public String executeToReview(String proId, ModelMap map){
        EnterpriseProjectInfoDo projectInfoDo = awardEnterpriseProjectService.get(proId);
        projectInfoDo.initShowProName();
        map.put("projectInfo",projectInfoDo);
        Map<String,Object> params = new HashMap<>();
        params.put("proId",proId);
        List<AwardStyleValidateScienceDO> validateScienceDOList = awardStyleValidateScienceService.list(params);
        map.put("awardStyleValidateScience",validateScienceDOList.size() > 0 ? validateScienceDOList.get(0) : new AwardStyleValidateScienceDO());
        return "system/awardStyleValidateScience/add";
    }

    /**
     * 形式审查结果查看
     * @param map
     * @return
     */
    @RequestMapping("/validateResult")
    public String toValidateAssociation(String proId, ModelMap map, @RequestParam Map<String, Object> params) {
        params.put("readonly", 1);
        map.put("readonly", 1);
        return executeToReview(proId, map);
    }



}
