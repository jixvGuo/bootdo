package com.bootdo.activiti.controller;

import com.alibaba.fastjson.JSON;
import com.bootdo.activiti.domain.EnterpriseDocUploadDo;
import com.bootdo.activiti.domain.EnterpriseProjectInfoDo;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.controller.BaseSciencePersonalController;
import com.bootdo.common.domain.DictDO;
import com.bootdo.common.service.DictService;
import com.bootdo.common.service.FileService;
import com.bootdo.common.utils.CommonUtils;
import com.bootdo.common.utils.DateUtils;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.cpe.utils.EnumUtils;
import com.bootdo.system.config.DictTypeConstant;
import com.bootdo.system.domain.*;
import com.bootdo.system.service.EnterpriPersonalInfoScienceKpiService;
import com.bootdo.system.service.EnterpriPersonalInfoService;
import com.bootdo.system.service.EnterpriPersonalInfoTenYearsPatentService;
import com.bootdo.system.service.EnterpriPersonalInfoWorkHistoryService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bootdo.system.config.DictTypeConstant.EDUCATION_TYPE;

@Controller
@RequestMapping("/chengguo_personal")
public class ChengguoPersonalController extends BaseSciencePersonalController {

    String prefix = "act/award/chengguo_personal";
    public static String DEFAULT_HEAD_PHONE_URL ="/img/default_head.jpg";

    @Autowired
    private EnterpriPersonalInfoService personalInfoService;
    @Autowired
    private EnterpriPersonalInfoScienceKpiService scienceKpiService;
    @Autowired
    private EnterpriPersonalInfoWorkHistoryService workHistoryService;

    @Autowired
    private FileService fileService;
//    @Autowired
//    private EnterpriPersonalInfoTenYearsPatentService enterpriPersonalInfoTenYearsPatentService;



    @Autowired
    private EnterpriPersonalInfoTenYearsPatentService tenYearsPatentService;


    @Autowired
    private DictService dictService;

    @RequestMapping("/index")
    public String toPersonalInfo(@RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        return prefix + "/index";
    }

    @RequestMapping("/apply_personal/edit")
    public String toAdminPersonalEdit(@RequestParam Map<String, Object> params, ModelMap map) {
        return executeToAdminPersonalEdit(params, map);
    }
    public String executeToAdminPersonalEdit(@RequestParam Map<String, Object> params, ModelMap map) {
        //先进个人成果申报
        packageAwardTaskId(map, params);

        List<EnterpriPersonalInfoDO> enterpriPersonalInfoDOList = personalInfoService.list(params);
        EnterpriPersonalInfoDO personalInfoDO = enterpriPersonalInfoDOList.size() > 0 ? enterpriPersonalInfoDOList.get(0) : new EnterpriPersonalInfoDO();
        map.put("enterpriPersonalInfo", personalInfoDO);
        map.put("curDate", enterpriPersonalInfoDOList.size() > 0 ? personalInfoDO.getCreated() : DateUtils.getCurDate());


        params.put("personalId", personalInfoDO.getId());
        List<EnterpriPersonalInfoWorkHistoryDO> historyDOList = workHistoryService.list(params);
        map.put("workHistory", historyDOList);

        return "act/award/chengguo_personal/index";
    }
    @RequestMapping("/personalView")
    public String toPersonalView(@RequestParam Map<String, Object> params, ModelMap map) {
        params.put("readonly", 1);
        map.put("readonly", 1);
        return executeToAdminPersonalEdit(params, map);
    }


    @RequestMapping("/base_info")
    public String toBaseInfo(@RequestParam Map<String, Object> params, ModelMap map) {

        packageAwardTaskId(map, params);

        List<EnterpriPersonalInfoDO> enterpriPersonalInfoDOList = personalInfoService.list(params);
        EnterpriPersonalInfoDO personalInfoDO = enterpriPersonalInfoDOList.size() > 0 ? enterpriPersonalInfoDOList.get(0) : new EnterpriPersonalInfoDO();

        if(StringUtils.isBlank(personalInfoDO.getPhoto())) {
            personalInfoDO.setPhoto(DEFAULT_HEAD_PHONE_URL);
        }else {
            try {
                personalInfoDO.setPhoto(URLDecoder.decode(personalInfoDO.getPhoto(), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
        map.put("enterpriPersonalInfo", personalInfoDO);
        map.put("curDate", enterpriPersonalInfoDOList.size() > 0 ? personalInfoDO.getCreated() : DateUtils.getCurDate());

        //是否可以编辑，false不可以编辑，true可以编辑
        map.put("btnUpdateStr", "修改");
        map.put("isEdit", true);

        if (personalInfoDO != null && !EnumUtils.proIsCouldEdit(personalInfoDO.getProStat())) {
            //不可以进行编辑
            map.put("btnUpdateStr", "查看");
            map.put("isEdit", false);
        }

        params.put("personalId", personalInfoDO.getId());
        List<EnterpriPersonalInfoWorkHistoryDO> historyDOList = workHistoryService.list(params);
        map.put("workHistory", historyDOList);


        List<DictDO> professions = dictService.listByType(DictTypeConstant.PROFESS_TYPE);
        map.put("professions", professions); // 专业
        List<DictDO> sexType = dictService.listByType(DictTypeConstant.GENDER_TYPE);
        map.put("genderList", sexType); // 性别
        List<DictDO> eduList = dictService.listByType(EDUCATION_TYPE);
        map.put("eduList", eduList);


        map.put("proStat", "");

        return prefix + "/enterprise_personal_add_index";
    }




    @RequestMapping("/performance_add")
    public String toTenYearAdd(@RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);

        List<EnterpriPersonalInfoDO> enterpriPersonalInfoDOList = personalInfoService.list(params);
        EnterpriPersonalInfoDO personalInfoDO = enterpriPersonalInfoDOList.size() > 0 ? enterpriPersonalInfoDOList.get(0) : new EnterpriPersonalInfoDO();
        map.put("enterpriPersonalInfo", personalInfoDO);
        map.put("curDate", enterpriPersonalInfoDOList.size() > 0 ? personalInfoDO.getCreated() : DateUtils.getCurDate());


        params.put("personalId", personalInfoDO.getId());
        List<EnterpriPersonalInfoWorkHistoryDO> historyDOList = workHistoryService.list(params);
        map.put("workHistory", historyDOList);

        map.put("tenYearsPatent", new EnterpriPersonalInfoTenYearsPatentDO());

        return prefix + "/personal_ten_years_patent";
    }


    /**
     * 新的KPI页面
     *
     * @param map
     * @return
     */
    @RequestMapping("/innovate_kpi")
    public String toInnovateKpi(@RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);

        List<EnterpriPersonalInfoScienceKpiDO> scienceKpiDOList = scienceKpiService.list(params);
        EnterpriPersonalInfoScienceKpiDO scienceKpiDO = scienceKpiDOList.size() > 0 ? scienceKpiDOList.get(0) : new EnterpriPersonalInfoScienceKpiDO();
        map.put("scienceKpi", scienceKpiDO);

        return prefix + "/personal_innovate_kpi";
    }


    /**
     * 申报人科技创新业绩指标
     * @param map
     * @return
     */
    @RequestMapping("/personal_ten_years")
    public String personalBusiness(@RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);

        List<EnterpriPersonalInfoTenYearsPatentDO> tenYearsPatentDOS = tenYearsPatentService.list(params);
        map.put("yearsPatentDO", tenYearsPatentDOS);

        return prefix + "/enterprise_personal_tenyears_list";
    }





    //附件列表
    @RequestMapping("/personal_enclosure_list")
    public String toEnclosureList(@RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);

        List<EnterpriseDocUploadDo> docUploadDoList = fileService.listUploadEnterpriseDocs(params);
        map.put("docUploadDoList",docUploadDoList);

        map.put("proStat","");

        return prefix + "/personal_paper_info";
    }


    //删除提交的信息
    @RequestMapping("/personal_info/delete")
    @ResponseBody
    public R toDeleteBaseInfo(int id) {

        int res = personalInfoService.remove(id);
        if (res > 0) {
            return R.ok();
        } else {
            return R.error();
        }
    }


    /***
     * 获取图片地址
      * @param id
     * @return
     */
    @RequestMapping("/personal_file/filePath")
    @ResponseBody
    public R getFilePath(int id) {

        int res = personalInfoService.remove(id);
        if (res > 0) {
            return R.ok();
        } else {
            return R.error();
        }
    }


}
