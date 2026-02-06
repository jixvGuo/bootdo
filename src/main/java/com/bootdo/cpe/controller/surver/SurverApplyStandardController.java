package com.bootdo.cpe.controller.surver;

import com.bootdo.activiti.domain.EnterpriseDocUploadDo;
import com.bootdo.activiti.domain.EnterpriseProjectInfoDo;
import com.bootdo.activiti.service.AwardEnterpriseProjectService;
import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.controller.BaseSurverController;
import com.bootdo.common.service.FileService;
import com.bootdo.common.utils.DateUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.cpe.domain.*;
import com.bootdo.cpe.service.SurverAwardService;
import com.bootdo.cpe.service.SurverDesiginContributeUserInfoService;
import com.bootdo.cpe.service.SurverStandardApplyProjectProfileService;
import com.bootdo.cpe.service.SurverStandardApplyTableInfoService;
import com.bootdo.cpe.utils.PathUtil;
import com.bootdo.cpe.utils.PoiWordSurveyProUtils;
import com.bootdo.cpe.utils.PoiWordSurveyStandardProUtils;
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

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author houzb
 * @version 1.0
 * @date 2022-03-29 8:10
 */
@Controller
@RequestMapping("/surverStandardApply")
public class SurverApplyStandardController extends BaseSurverController {
    private String prefix = "cpe/survey";

    @Autowired
    private SurverDesiginContributeUserInfoService surverDesiginContributeUserInfoService;
    @Autowired
    private FileService fileService;
    @Autowired
    private AwardEnterpriseProjectService awardEnterpriseProjectService;
    @Autowired
    private SurverStandardApplyTableInfoService surverStandardApplyTableInfoService;
    @Autowired
    private SurverStandardApplyProjectProfileService surverStandardApplyProjectProfileService;
    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    private BootdoConfig bootdoConfig;
    @Autowired
    private SurverAwardService surverAwardService;


    @RequestMapping("/toApply")
    public String toApplyStandard(ModelMap map, @RequestParam Map<String, Object> params) {
        params.put("proSubType", "standard");
        packageAwardTaskId(map, params);
        return prefix + "/apply/apply_standard_award_main";
    }

    @RequestMapping("/toProDescAdd")
    public String toProDescAdd(ModelMap map, @RequestParam Map<String, Object> params) {
        packageAwardTaskId(map, params);
        map.put("proDescDO", new SurverSoftApplyProjectProfileDO());
        return prefix + "/apply/apply_standard_pro_desc_add";
    }


    @RequestMapping("/toProDesc")
    public String toProDesc(ModelMap map, @RequestParam Map<String, Object> params) {
        packageAwardTaskId(map, params);
        Query query = new Query(params);
        List<SurverStandardApplyProjectProfileDO> projectProfileDOList = surverStandardApplyProjectProfileService.list(query);
        map.put("list", projectProfileDOList);
        return prefix + "/apply/apply_standard_pro_desc_list";
    }

    @RequestMapping("/toApplyTable")
    public String toApplyTable(ModelMap map, @RequestParam Map<String, Object> params) {
        packageAwardTaskId(map, params);
        List<SurverStandardApplyTableInfoDO> tableInfoDOList = surverStandardApplyTableInfoService.list(params);
        map.put("table", tableInfoDOList.size() > 0 ? tableInfoDOList.get(0) : new SurverStandardApplyTableInfoDO());
        return prefix + "/apply/apply_standard_apply_table_info";
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
        return prefix + "/apply/apply_standard_appendix";
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
        return prefix + "/apply/apply_standard_support";
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
            List<SurverDesiginContributeUserInfoDO> contributeUserInfoDOList = surverDesiginContributeUserInfoService.list(groupParamsMap);// 主要人员情况列表
            List<SurverStandardApplyProjectProfileDO> projectProfileDOList = surverStandardApplyProjectProfileService.list(groupParamsMap);
            List<SurverStandardApplyTableInfoDO> tableInfoDOList = surverStandardApplyTableInfoService.list(groupParamsMap);

            List<SurverProjectInfo> proDataDtoList = surverAwardService.listProInfo(groupParamsMap);


            SurveyStandardDataWord word = new SurveyStandardDataWord();
            word.setInfo(tableInfoDOList.size() > 0 ? tableInfoDOList.get(0) : new SurverStandardApplyTableInfoDO());
            word.setUserInfo(contributeUserInfoDOList);
            word.setSoftApplyProjectProfileDOS(projectProfileDOList);
            word.setPro(proDataDtoList.size() > 0 ? proDataDtoList.get(0):new SurverProjectInfo());

            File templateFile = PathUtil.getJarResourceFile(ConstantCommonData.TEMPLATE_DOC_SURVEY_STANDARD_CLASS_PATH);
            if (templateFile == null) {
                Resource resource = resourceLoader.getResource(ConstantCommonData.TEMPLATE_DOC_SURVEY_STANDARD_CLASS_PATH);
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

            PoiWordSurveyStandardProUtils.createDesignWord(templateFile.getAbsolutePath(), storePath, word);


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
