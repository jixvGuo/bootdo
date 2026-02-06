package com.bootdo.cpe.controller.gongfa;

import com.bootdo.activiti.domain.EnterpriseDocUploadDo;
import com.bootdo.activiti.domain.EnterpriseProjectInfoDo;
import com.bootdo.activiti.service.AwardEnterpriseProjectService;
import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.controller.BaseGfProController;
import com.bootdo.common.controller.BaseQcProController;
import com.bootdo.common.service.FileService;
import com.bootdo.common.utils.*;
import com.bootdo.cpe.domain.*;
import com.bootdo.cpe.dto.GfProDataDto;
import com.bootdo.cpe.dto.QcProDataDto;
import com.bootdo.cpe.service.*;
import com.bootdo.cpe.utils.PathUtil;
import com.bootdo.cpe.utils.PoiWordQCProUtils;
import com.bootdo.system.config.ConstantCommonData;
import com.bootdo.system.controller.EnterpriPersonalInfoController;
import com.bootdo.system.domain.UserDO;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

import static com.bootdo.common.config.Constant.*;

/**
 * qc奖
 *
 * @author houzb
 * @version 1.0
 * @date 2022-02-05 10:12
 */
@RequestMapping("/gfAward")
@Controller
public class GongFaController extends BaseGfProController {
    private Logger logger = LoggerFactory.getLogger(EnterpriPersonalInfoController.class);

    private String prefix = "cpe/gongfa";

    @Autowired
    private QcAwardService qcAwardService;
    @Autowired
    private QcGroupApplyInfoService qcGroupApplyInfoService;
    @Autowired
    private QcReportSolveInfoService qcReportSolveInfoService;
    @Autowired
    private QcReportInnovateInfoService qcReportInnovateInfoService;
    @Autowired
    private QcAppraiseActiveScoreService qcAppraiseActiveScoreService;
    @Autowired
    private QcResultSolveScoreService qcResultSolveScoreService;
    @Autowired
    private QcResultInnovateScoreService qcResultInnovateScoreService;
    @Autowired
    private QcSurveyStatisticInfoService qcSurveyStatisticInfoService;
    @Autowired
    private FileService fileService;
    @Autowired
    private AwardEnterpriseProjectService awardEnterpriseProjectService;
    @Autowired
    private GfGroupApplyInfoService gfGroupApplyInfoService;
    @Autowired
    private GfMainCompleteInfoService gfMainCompleteInfoService;
    @Autowired
    ResourceLoader resourceLoader;
    @Autowired
    private BootdoConfig bootdoConfig;

    private Map<String, Object> newParams;

    @RequiresPermissions("gf:to:prolist")
    @RequestMapping("/toProListMain")
    public String toProListMain(@RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        return "cpe/gongfa/gf_pro_list_main";
    }



    /**
     * qc奖项目列表
     * @return
     */
    @RequestMapping("/view/proList")
    public String toQcProjectList(ModelMap map, @RequestParam Map<String, Object> params) {
        packageAwardTaskId(map, params);
        boolean isApply = isTaskIsApply(map, params);
        map.put("isApply", isApply);
        UserDO user = getUser();
        List<Long> roleIdList = user.getRoleIds();
        if(!roleIdList.contains(ROLE_GONGFA_ENTERPRISE_ID)) {
            //非企业角色不显示申请按钮
           map.put("apply_type", null);
        }
        return prefix + "/gf_pro_list";
    }

    /**
     * 获取项目列表
     * @return
     */
    @Deprecated
    @RequestMapping("/get/proList")
    @ResponseBody
    public PageUtils getQcProList(@RequestParam Map<String, Object> params, ModelMap map) {
        UserDO user = getUser();
        Long uid = getUserId();
        List<Long> roleIdList = user.getRoleIds();
        if (roleIdList.contains(ROLE_GONGFA_ASSOCIATION_ID)) {
            //todo 临时使用协会联系人的用户id
            params.put("associationUserId", roleIdList.contains(ROLE_QC_OFFLINE_VIEW_ID) ? 101 : user.getUserId());
        } else if (roleIdList.contains(ROLE_GONGFA_ENTERPRISE_ID)) {
            //企业用户查看自己创建项目
            params.put("enterpriseUid", uid);
        } else {
            //分派给自己的项目
            params.put("ass_assign_uid", uid);
        }
        getProListParamsByRole(params);
        this.newParams = params;

        params.put("proStatStr", "");
        Object keyWordObj = params.get("keyWord");
        if (keyWordObj != null) {
            List<String> proStatList = QcProStatEnum.getStatValByKey(keyWordObj.toString());
            if (proStatList.size() > 0) {
                String str = new String();
                for (String s : proStatList) {
                    str +=  s + ",";
                }
                params.put("proStatStr", str.substring(0, str.length() - 1));
            }
        }

        Query query = new Query(params);
        //TODO 为了下载使用
        this.newParams = params;

        List<QcProDataDto> proDataDtoList = qcAwardService.listProInfo(query);
        int total = qcAwardService.countProInfo(query);
        PageUtils pageUtils = new PageUtils(proDataDtoList, total);
        return pageUtils;
    }

    /**
     * 跳转到申报页面
     * @return
     */
    @RequestMapping("/view/apply")
    public String toGfApply(ModelMap map, @RequestParam Map<String, Object> params) {
        applyParams(map, params);
        Object proIdObj = params.get("proId");
        GfGroupApplyInfoDO data = null;
        EnterpriseProjectInfoDo projectInfoDo = new EnterpriseProjectInfoDo();
        List<GfMainCompleteInfoDO> completeInfoDOList = new ArrayList<>();
        if(proIdObj != null) {
            Map<String, Object> applyMap = new HashMap<>();
            applyMap.put("proId", proIdObj);
            List<GfProDataDto> applyInfoDOList = gfGroupApplyInfoService.list(applyMap);
            data = applyInfoDOList.size() > 0 ? applyInfoDOList.get(0) : new GfGroupApplyInfoDO();
            completeInfoDOList = gfMainCompleteInfoService.list(applyMap);
            projectInfoDo = awardEnterpriseProjectService.get(proIdObj.toString());
            projectInfoDo.initApplyStat();
        }else {
            data = new GfGroupApplyInfoDO();
        }

        map.put("projectInfo",projectInfoDo);
        map.put("completeUserList", completeInfoDOList);
        map.put("data", data);
        return prefix + "/apply/gf_apply";
    }

    /**
     * 跳转到申报小组报表页面
     * @return
     */
    @RequestMapping("/view/applyGroupInfo")
    public String toQcApplyGroupInfo(ModelMap map, @RequestParam Map<String, Object> params) {
        packageAwardTaskId(map, params);
        Object proIdObj = params.get("proId");
        QcGroupApplyInfoDO applyInfoDO = new QcGroupApplyInfoDO();
        if(proIdObj != null) {
            Map<String, Object> groupParamsMap = new HashMap<>();
            groupParamsMap.put("proId", proIdObj);
            List<QcGroupApplyInfoDO> groupApplyInfoDOList = qcGroupApplyInfoService.list(groupParamsMap);
            applyInfoDO = groupApplyInfoDOList.size() > 0 ? groupApplyInfoDOList.get(0) : applyInfoDO;
        }
        map.put("groupInfo", applyInfoDO);
        return prefix + "/apply/qc_apply_group_info";
    }

    /**
     * 跳转到申报小组报告-问题解决型课题
     * @param map
     * @return
     */
    @RequestMapping("/view/applyReportSolve")
    public String toQcApplyReportSolve(ModelMap map, @RequestParam Map<String, Object> params) {
        packageAwardTaskId(map, params);
        Object proIdObj = params.get("proId");
        QcReportSolveInfoDO reportSolveInfoDO = new QcReportSolveInfoDO();
        if(proIdObj != null) {
            Map<String, Object> repMap = new HashMap<>();
            repMap.put("proId", proIdObj);
            List<QcReportSolveInfoDO> list = qcReportSolveInfoService.list(repMap);
            reportSolveInfoDO = list.size() > 0 ? list.get(0) : reportSolveInfoDO;
        }
        map.put("report", reportSolveInfoDO);
        return prefix + "/apply/qc_apply_report_solve";
    }

    /**
     * 跳转到申报小组报告-创新型课题
     * @param map
     * @return
     */
    @RequestMapping("/view/applyReportInnovate")
    public String toQcApplyReportInnovate(ModelMap map, @RequestParam Map<String, Object> params) {
        packageAwardTaskId(map, params);
        Object proIdObj = params.get("proId");
        QcReportInnovateInfoDO reportInnovateInfoDO = new QcReportInnovateInfoDO();
        if(proIdObj != null) {
            Map<String, Object> repMap = new HashMap<>();
            repMap.put("proId", proIdObj);
            List<QcReportInnovateInfoDO> list = qcReportInnovateInfoService.list(repMap);
            reportInnovateInfoDO = list.size() > 0 ? list.get(0) : reportInnovateInfoDO;
        }
        map.put("report", reportInnovateInfoDO);
        return prefix + "/apply/qc_apply_report_innovate";
    }

    /**
     * 跳转到小组现场活动评价表
     * @param map
     * @return
     */
    @RequestMapping("/view/applyScoreActive")
    public String toQcApplyScoreActive(ModelMap map, @RequestParam Map<String, Object> params) {
        packageAwardTaskId(map, params);

        Object proIdObj = params.get("proId");
        QcAppraiseActiveScoreDO activeScoreDO = new QcAppraiseActiveScoreDO();
        if(proIdObj != null) {
            Map<String, Object> repMap = new HashMap<>();
            repMap.put("proId", proIdObj);
            List<QcAppraiseActiveScoreDO> list = qcAppraiseActiveScoreService.list(repMap);
            activeScoreDO = list.size() > 0 ? list.get(0) : activeScoreDO;
        }
        map.put("score", activeScoreDO);

        return prefix + "/apply/qc_apply_active_score";
    }

    /**
     * 跳转到 问题解决型课题成果评价表
     * @param map
     * @return
     */
    @RequestMapping("/view/applyScoreResultSolve")
    public String toQcApplyScoreResultSolve(ModelMap map , @RequestParam Map<String, Object> params) {
        packageAwardTaskId(map, params);

        Object proIdObj = params.get("proId");
        QcResultSolveScoreDO resultSolveScoreDO = new QcResultSolveScoreDO();
        if(proIdObj != null) {
            Map<String, Object> repMap = new HashMap<>();
            repMap.put("proId", proIdObj);
            List<QcResultSolveScoreDO> list = qcResultSolveScoreService.list(repMap);
            resultSolveScoreDO = list.size() > 0 ? list.get(0) : resultSolveScoreDO;
        }
        map.put("score", resultSolveScoreDO);

        return prefix + "/apply/qc_apply_result_solve_score";
    }

    /**
     * 跳转到 创新型课题成果评价表
     * @param map
     * @return
     */
    @RequestMapping("/view/applyScoreResultInnovate")
    public String toQcApplyScoreResultInnovate(ModelMap map, @RequestParam Map<String, Object> params) {
        packageAwardTaskId(map, params);

        Object proIdObj = params.get("proId");
        QcResultInnovateScoreDO resultInnovateScoreDO = new QcResultInnovateScoreDO();
        if(proIdObj != null) {
            Map<String, Object> repMap = new HashMap<>();
            repMap.put("proId", proIdObj);
            List<QcResultInnovateScoreDO> list = qcResultInnovateScoreService.list(repMap);
            resultInnovateScoreDO = list.size() > 0 ? list.get(0) : resultInnovateScoreDO;
        }
        map.put("score", resultInnovateScoreDO);

        return prefix + "/apply/qc_apply_result_innovate_score";
    }

    /**
     * 跳转到 质量管理小组概况统计表
     * @param map
     * @return
     */
    @RequestMapping("/view/applySurveyStatistic")
    public String toQcApplySurveyStatistic(ModelMap map, @RequestParam Map<String, Object> params) {
        packageAwardTaskId(map, params);

        Object proIdObj = params.get("proId");
        QcSurveyStatisticInfoDO surveyStatisticInfoDO = new QcSurveyStatisticInfoDO();
        if(proIdObj != null) {
            Map<String, Object> repMap = new HashMap<>();
            repMap.put("proId", proIdObj);
            List<QcSurveyStatisticInfoDO> list = qcSurveyStatisticInfoService.list(repMap);
            surveyStatisticInfoDO = list.size() > 0 ? list.get(0) : surveyStatisticInfoDO;
        }
        map.put("score", surveyStatisticInfoDO);

        return prefix + "/apply/qc_apply_survey_statistic";
    }

    /**
     * 跳转到 附件上传页签
     * @param map
     * @return
     */
    @RequestMapping("/view/applyAnnex")
    public String toQcApplyAnnex(ModelMap map, @RequestParam Map<String, Object> params) {
        packageAwardTaskId(map, params);
        String proId = (String) params.get("proId");
        List<EnterpriseDocUploadDo> docUploadDoList = fileService.listUploadEnterpriseDocs(params);
        map.put("docUploadDoList",docUploadDoList);
        map.put("fileSize",docUploadDoList.size());
        EnterpriseProjectInfoDo projectInfoDo = awardEnterpriseProjectService.get(proId);
        map.put("projectInfo",projectInfoDo == null ? new EnterpriseProjectInfoDo() : projectInfoDo);
        return prefix + "/apply/qc_apply_annex";
    }

    /**
     * 保存申请的组信息
     * @param map
     * @param qcGroupApplyInfoDO
     * @return
     */
    @RequestMapping("/update/groupInfo")
    @ResponseBody
    @RequiresPermissions("cpe:qcGroupApplyInfo:add")
    public R updateGroupInfo(ModelMap map, QcGroupApplyInfoDO qcGroupApplyInfoDO) {
        Long uid = getUserId();
        qcGroupApplyInfoDO.setOptUid(uid.intValue());
        qcGroupApplyInfoDO.setDeleted(0);
        Date now = new Date();
        qcGroupApplyInfoDO.setUpdated(now);
        Integer id = qcGroupApplyInfoDO.getId();
        int rst = 0;

        EnterpriseProjectInfoDo projectInfoDo = new EnterpriseProjectInfoDo();
        projectInfoDo.setId(qcGroupApplyInfoDO.getProId());
        projectInfoDo.setMajor(qcGroupApplyInfoDO.getProfessionalScope());
        awardEnterpriseProjectService.updateProjectInfo(projectInfoDo);

        if(id == null) {
            qcGroupApplyInfoDO.setCreated(now);
            rst = qcGroupApplyInfoService.save(qcGroupApplyInfoDO);
            id = qcGroupApplyInfoDO.getId();
        }else {
            rst = qcGroupApplyInfoService.update(qcGroupApplyInfoDO);
        }
        R r = rst > 0 ? R.ok() : R.error("更新失败");
        r.put("id", id);
        return r;
    }

    @RequestMapping("/remove/groupInfo")
    @ResponseBody
    @RequiresPermissions("cpe:qcGroupApplyInfo:remove")
    public R removeGroupInfo(Integer id, Integer proId) {
        if(id != null) {
            qcGroupApplyInfoService.remove(id);
        }
        return R.ok();
    }

    @RequestMapping("/qc/printExcel")
    @ResponseBody
    public String printExcel(HttpServletResponse response, ModelMap map, @RequestParam Map<String, Object> params) {



        this.newParams.put("limit", "100000");
        Query query = new Query(this.newParams);

        List<QcProDataDto> proDataDtoList = qcAwardService.listProInfo(query);


        String[] title = {"序号", "成果编号", "类别", "课题名称", "小组名称", "单位名称",  "小组类型","专业范围", "申报账号","状态" };
        try {
            PoiWordUtils.downQCExcel(title, proDataDtoList, response);
        } catch (Exception e) {
        }
        map.addAttribute("result", "下载成功");

        return "";
    }


    /**
     * 打印 记录
     * @return
     */
    @RequestMapping("/print/proinfo")
    public String printProInfo(String  id ,ModelMap map, @RequestParam Map<String, Object> params, HttpServletResponse response)  {
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
            outputStream = response.getOutputStream();

            QcGroupApplyInfoDO applyInfoDO = new QcGroupApplyInfoDO();
            if(proIdObj != null) {
                Map<String, Object> groupParamsMap = new HashMap<>();
                groupParamsMap.put("proId", proIdObj);
                List<QcGroupApplyInfoDO> groupApplyInfoDOList = qcGroupApplyInfoService.list(groupParamsMap);
                applyInfoDO = groupApplyInfoDOList.size() > 0 ? groupApplyInfoDOList.get(0) : applyInfoDO;
            }
            //  问题解决型
            QcReportSolveInfoDO reportSolveInfoDO = new QcReportSolveInfoDO();
            if(proIdObj != null) {
                Map<String, Object> repMap = new HashMap<>();
                repMap.put("proId", proIdObj);
                List<QcReportSolveInfoDO> list = qcReportSolveInfoService.list(repMap);
                reportSolveInfoDO = list.size() > 0 ? list.get(0) : reportSolveInfoDO;
            }
            // 创新型课题
            QcReportInnovateInfoDO reportInnovateInfoDO = new QcReportInnovateInfoDO();
            if(proIdObj != null) {
                Map<String, Object> repMap = new HashMap<>();
                repMap.put("proId", proIdObj);
                List<QcReportInnovateInfoDO> list = qcReportInnovateInfoService.list(repMap);
                reportInnovateInfoDO = list.size() > 0 ? list.get(0) : reportInnovateInfoDO;
            }
            // 活动现场评价
            QcAppraiseActiveScoreDO activeScoreDO = new QcAppraiseActiveScoreDO();
            if(proIdObj != null) {
                Map<String, Object> repMap = new HashMap<>();
                repMap.put("proId", proIdObj);
                List<QcAppraiseActiveScoreDO> list = qcAppraiseActiveScoreService.list(repMap);
                activeScoreDO = list.size() > 0 ? list.get(0) : activeScoreDO;
            }
            //解决成果评价
            QcResultSolveScoreDO resultSolveScoreDO = new QcResultSolveScoreDO();
            if(proIdObj != null) {
                Map<String, Object> repMap = new HashMap<>();
                repMap.put("proId", proIdObj);
                List<QcResultSolveScoreDO> list = qcResultSolveScoreService.list(repMap);
                resultSolveScoreDO = list.size() > 0 ? list.get(0) : resultSolveScoreDO;
            }
            // 创新型成果评价
            QcResultInnovateScoreDO resultInnovateScoreDO = new QcResultInnovateScoreDO();
            if(proIdObj != null) {
                Map<String, Object> repMap = new HashMap<>();
                repMap.put("proId", proIdObj);
                List<QcResultInnovateScoreDO> list = qcResultInnovateScoreService.list(repMap);
                resultInnovateScoreDO = list.size() > 0 ? list.get(0) : resultInnovateScoreDO;
            }
            // 质量管理小组概况统计表
            QcSurveyStatisticInfoDO surveyStatisticInfoDO = new QcSurveyStatisticInfoDO();
            if(proIdObj != null) {
                Map<String, Object> repMap = new HashMap<>();
                repMap.put("proId", proIdObj);
                List<QcSurveyStatisticInfoDO> list = qcSurveyStatisticInfoService.list(repMap);
                surveyStatisticInfoDO = list.size() > 0 ? list.get(0) : surveyStatisticInfoDO;
            }

            QCDocBaseDataWord word = new QCDocBaseDataWord();
            word.setApplyInfoDO(applyInfoDO);
            word.setActiveScoreDO(activeScoreDO);
            word.setReportInnovateInfoDO(reportInnovateInfoDO);
            word.setResultInnovateScoreDO(resultInnovateScoreDO);
            word.setSurveyStatisticInfoDO(surveyStatisticInfoDO);
            word.setReportSolveInfoDO(reportSolveInfoDO);
            word.setResultSolveScoreDO(resultSolveScoreDO);





            File templateFile = PathUtil.getJarResourceFile(ConstantCommonData.TEMPLATE_DOC_QC_APPLY_CLASS_PATH);
            if (templateFile == null) {
                Resource resource = resourceLoader.getResource(ConstantCommonData.TEMPLATE_DOC_QC_APPLY_CLASS_PATH);
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

            PoiWordQCProUtils.createProQCWord(templateFile.getAbsolutePath(), storePath, word);

//            PoiWordUtils.createScicenceWord(templateFile.getAbsolutePath(),
//                    storePath,
//                    word,list,otherInfoDO);

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

//
//        //待下载文件名
//
//          BufferedInputStream bis = null;
//
            //  优质申报表

//
//        OutputStream outputStream = null;
//        File storeFile = null;
//            try {
//                  outputStream = response.getOutputStream();
//                File templateFile = PathUtil.getJarResourceFile(ConstantCommonData.TEMPLATE_DOC_QC_APPLY_CLASS_PATH);
//                if (templateFile == null) {
//                    Resource resource = resourceLoader.getResource(ConstantCommonData.TEMPLATE_DOC_QC_APPLY_CLASS_PATH);
//                    templateFile = resource.getFile();
//                }
//
//
//                String fName = templateFile.getName();
//                String[] fNameArr = fName.split("\\.");
//                String tmpFileName = fNameArr[0] + "_" + System.currentTimeMillis() + RandomStringUtils.randomAlphabetic(5) + "." + fNameArr[1];
//                tmpFileName = URLEncoder.encode(tmpFileName, "UTF-8");
//                response.setHeader("Content-Disposition", "attachment; filename=" + tmpFileName);
//                String storePath = bootdoConfig.getUploadPath() + "/print_doc/" + DateUtils.getCurDate();
//                storeFile = new File(storePath);
//                if (!storeFile.exists()) {
//                    storeFile.mkdirs();
//                }
//                storePath = storePath + "/" + tmpFileName;
//                storeFile = new File(storePath);
//                FileUtils.copyFile(templateFile, storeFile);
//
//
//                PoiWordQCProUtils.createProQCWord(templateFile.getAbsolutePath(), storePath, word);
//
//                InputStream inputStream = new FileInputStream(storeFile);
//                //这个路径为待下载文件的路径
//                  bis = new BufferedInputStream(inputStream);
//                byte[] buff = new byte[1024];
//                int len;
//                //通过while循环写入到指定了的文件夹中
//                while ((len = bis.read(buff)) != -1) {
//                    outputStream.write(buff, 0, len);
//                    outputStream.flush();
////                    read = bis.read(buff);
//                }
//
//            }catch (Exception ex){
//                ex.printStackTrace();
//                //出现异常返回给页面失败的信息
//                map.addAttribute("result", "下载失败");
//            }finally {
//                if (bis != null) {
//                    try {
//                        bis.close();
//                    } catch (IOException e) {
//                        logger.error("close err {}", e.getMessage());
//                    }
//                }
//
//                if (outputStream != null) {
//                    try {
//                        outputStream.flush();
//                        outputStream.close();
//                    } catch (IOException e) {
//                        logger.error("close out err {}", e.getMessage());
//                    }
//                }
////                if (storeFile != null) {
////                    storeFile.deleteOnExit();
////                }
//            }
//
//        map.addAttribute("result", "下载成功");
//        return "enterprise/apply/print";
    }

    /**
     * 下载
     * @return
     */
    @RequestMapping("/download/proinfo")
    public void  downloadProInfo(int id,ModelMap map,  @RequestParam Map<String, Object> params, HttpServletResponse response)throws  Exception {
        packageAwardTaskId(map, params);
        Object proIdObj = id;
        //待下载文件名
        //设置为png格式的文件
        response.setHeader("content-type", "application/msword");
        response.setContentType("application/octet-stream");


        File storeFile = null;

            //  优质申报表
            QcGroupApplyInfoDO applyInfoDO = new QcGroupApplyInfoDO();
            if(proIdObj != null) {
                Map<String, Object> groupParamsMap = new HashMap<>();
                groupParamsMap.put("proId", proIdObj);
                List<QcGroupApplyInfoDO> groupApplyInfoDOList = qcGroupApplyInfoService.list(groupParamsMap);
                applyInfoDO = groupApplyInfoDOList.size() > 0 ? groupApplyInfoDOList.get(0) : applyInfoDO;
            }
           //  问题解决型
            QcReportSolveInfoDO reportSolveInfoDO = new QcReportSolveInfoDO();
            if(proIdObj != null) {
                Map<String, Object> repMap = new HashMap<>();
                repMap.put("proId", proIdObj);
                List<QcReportSolveInfoDO> list = qcReportSolveInfoService.list(repMap);
                reportSolveInfoDO = list.size() > 0 ? list.get(0) : reportSolveInfoDO;
            }
            // 创新型课题
            QcReportInnovateInfoDO reportInnovateInfoDO = new QcReportInnovateInfoDO();
            if(proIdObj != null) {
                Map<String, Object> repMap = new HashMap<>();
                repMap.put("proId", proIdObj);
                List<QcReportInnovateInfoDO> list = qcReportInnovateInfoService.list(repMap);
                reportInnovateInfoDO = list.size() > 0 ? list.get(0) : reportInnovateInfoDO;
            }
            // 活动现场评价
            QcAppraiseActiveScoreDO activeScoreDO = new QcAppraiseActiveScoreDO();
            if(proIdObj != null) {
                Map<String, Object> repMap = new HashMap<>();
                repMap.put("proId", proIdObj);
                List<QcAppraiseActiveScoreDO> list = qcAppraiseActiveScoreService.list(repMap);
                activeScoreDO = list.size() > 0 ? list.get(0) : activeScoreDO;
            }
            //解决成果评价
            QcResultSolveScoreDO resultSolveScoreDO = new QcResultSolveScoreDO();
            if(proIdObj != null) {
                Map<String, Object> repMap = new HashMap<>();
                repMap.put("proId", proIdObj);
                List<QcResultSolveScoreDO> list = qcResultSolveScoreService.list(repMap);
                resultSolveScoreDO = list.size() > 0 ? list.get(0) : resultSolveScoreDO;
            }
            // 创新型成果评价
            QcResultInnovateScoreDO resultInnovateScoreDO = new QcResultInnovateScoreDO();
            if(proIdObj != null) {
                Map<String, Object> repMap = new HashMap<>();
                repMap.put("proId", proIdObj);
                List<QcResultInnovateScoreDO> list = qcResultInnovateScoreService.list(repMap);
                resultInnovateScoreDO = list.size() > 0 ? list.get(0) : resultInnovateScoreDO;
            }
            // 质量管理小组概况统计表
            QcSurveyStatisticInfoDO surveyStatisticInfoDO = new QcSurveyStatisticInfoDO();
            if(proIdObj != null) {
                Map<String, Object> repMap = new HashMap<>();
                repMap.put("proId", proIdObj);
                List<QcSurveyStatisticInfoDO> list = qcSurveyStatisticInfoService.list(repMap);
                surveyStatisticInfoDO = list.size() > 0 ? list.get(0) : surveyStatisticInfoDO;
            }

            QCDocBaseDataWord word = new QCDocBaseDataWord();
            word.setApplyInfoDO(applyInfoDO);
            word.setActiveScoreDO(activeScoreDO);
            word.setReportInnovateInfoDO(reportInnovateInfoDO);
            word.setResultInnovateScoreDO(resultInnovateScoreDO);
            word.setSurveyStatisticInfoDO(surveyStatisticInfoDO);
            word.setReportSolveInfoDO(reportSolveInfoDO);
            word.setResultSolveScoreDO(resultSolveScoreDO);

            File templateFile = PathUtil.getJarResourceFile(ConstantCommonData.TEMPLATE_DOC_QC_APPLY_CLASS_PATH);
            if (templateFile == null) {
                Resource resource = resourceLoader.getResource(ConstantCommonData.TEMPLATE_DOC_QC_APPLY_CLASS_PATH);
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
            PoiWordQCProUtils.createProQCWord(templateFile.getAbsolutePath(), storePath, word);
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




//    /**
//     * 下载项目中的文件列表
//     */
//    @RequestMapping("/downloadProDocFiles")
//    @RequiresPermissions("system:scienceProcess:downloadProDocFiles")
//    @ResponseBody
//    public R downloadUpDocFiles(Integer proId) {
//        if (proId == null) {
//            return R.error("选择要下载的项目");
//        }
//        Map<String, Object> params = new HashMap<>();
//        params.put("proId", proId);
//        List<String> fileUrlList = scienceProcessService.getUploadFileUrlList(proId);
//        String uploadPath = bootdoConfig.getUploadPath();
//
//        Set<String> filePathList = new HashSet<>();
//        fileUrlList.stream().forEach(url -> {
//            String filePath = uploadPath + "/" + url.replaceAll("/files/", "");
//            filePathList.add(filePath);
//        });
//
//        //打包数据下发
//        String curDate = DateUtils.getCurDate();
//        String[] dateArr = curDate.split("-");
//        String userFolderPath = dateArr[0] + "/" + dateArr[1] + "/u_" + getUserId() + "/zip";
//        String storeZipFolder = uploadPath + userFolderPath;
//        File zipFolder = new File(storeZipFolder);
//        if (!zipFolder.exists()) {
//            zipFolder.mkdirs();
//        }
//
//        String zipFileName = System.currentTimeMillis() + "_" + proId;
//
//        List<String> fList = new ArrayList<>();
//        filePathList.stream().forEach(f -> {
//            fList.add(f);
//        });
//        try {
//            ZipCompress.compress(storeZipFolder, zipFileName, fList);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        String zipUrl = "/files/" + userFolderPath + "/" + zipFileName + ".zip";
//        R result = R.ok();
//        result.put("zipUrl", zipUrl);
//        return result;
//    }

    /**
     * 保存成果编号
     * @param proId
     * @param resultCode
     * @return
     */
    @RequestMapping("/saveCode")
    @ResponseBody
    @RequiresPermissions("cpe:qcGroupApplyInfo:saveCode")
    public R updateProResultCode(int proId, String resultCode) {
        if(StringUtils.isBlank(resultCode)) {
            return R.error("编号不能为空");
        }
        int rst = qcAwardService.updateProResultCode(proId, resultCode);
        return  rst > 0 ? R.ok("保存成功") : R.error("保存失败");
    }

}
