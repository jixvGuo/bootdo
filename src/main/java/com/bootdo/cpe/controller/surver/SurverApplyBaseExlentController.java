package com.bootdo.cpe.controller.surver;

import com.bootdo.activiti.domain.EnterpriseDocUploadDo;
import com.bootdo.activiti.domain.EnterpriseProjectInfoDo;
import com.bootdo.activiti.service.AwardEnterpriseProjectService;
import com.bootdo.common.controller.BaseSurverController;
import com.bootdo.common.service.FileService;
import com.bootdo.common.utils.DateUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.cpe.domain.*;
import com.bootdo.cpe.service.SurverBaseApplyTableInfoService;
import com.bootdo.cpe.service.SurverConsultApplyProjectProfileService;
import com.bootdo.cpe.service.SurverConsultApplyTableInfoService;
import com.bootdo.cpe.service.SurverDesiginContributeUserInfoService;
import com.bootdo.cpe.utils.PathUtil;
import com.bootdo.cpe.utils.PoiWordSurveyConsultProUtils;
import com.bootdo.system.config.ConstantCommonData;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
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
@RequestMapping("/surverBaseExlentApply")
public class SurverApplyBaseExlentController extends BaseSurverController {
    private String prefix = "cpe/survey";

    @Autowired
    private FileService fileService;
    @Autowired
    private AwardEnterpriseProjectService awardEnterpriseProjectService;
    @Autowired
    private SurverBaseApplyTableInfoService surverBaseApplyTableInfoService;

    @RequestMapping("/toApply")
    public String toApplyDesign(ModelMap map, @RequestParam Map<String, Object> params) {
        params.put("proSubType", "contribution");
        packageAwardTaskId(map, params);
        return prefix + "/apply/apply_base_excellent_award_main";
    }

    @RequestMapping("/toApplyTable")
    public String toApplyTable(ModelMap map, @RequestParam Map<String, Object> params) {
        packageAwardTaskId(map, params);
        List<SurverBaseApplyTableInfoDO> tableInfoDOList = surverBaseApplyTableInfoService.list(params);
        map.put("table", tableInfoDOList.size() > 0 ? tableInfoDOList.get(0) : new SurverBaseApplyTableInfoDO());
        return prefix + "/apply/apply_base_excellent_apply_table_info";
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
        return prefix + "/apply/apply_base_excellent_appendix";
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
        return prefix + "/apply/apply_base_excellent_support";
    }



}
