package com.bootdo.activiti.controller;

import com.bootdo.activiti.service.AwardEnterpriseProjectService;
import com.bootdo.common.controller.BaseController;
import com.bootdo.system.domain.AwardStyleValidatePersonDO;
import com.bootdo.system.domain.AwardStyleValidateScienceDO;
import com.bootdo.system.domain.AwardStyleValidateTeamDO;
import com.bootdo.system.domain.EnterpriseChengguoBaseInfoDO;
import com.bootdo.system.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/style_validate")
public class AwardStyleValidateController extends BaseController {
    @Autowired
    private AwardStyleValidatePersonService awardStyleValidatePersonService;
    @Autowired
    private AwardStyleValidateScienceService awardStyleValidateScienceService;
    @Autowired
    private AwardStyleValidateTeamService awardStyleValidateTeamService;
    @Autowired
    private EnterpriseChengguoBaseInfoService chengguoBaseInfoService;
    @Autowired
    private AwardEnterpriseProjectService enterpriseProjectService;

    String prefix = "act/award/style_validate";

    /**
     * 跳转到形式审查页面
     * @param type
     * @return
     */
    @RequestMapping("/view")
    public String toStyleValidatePage(String type, String proId, ModelMap map){
        Map<String,Object> params = new HashMap<>();
        params.put("proId",proId);
        String applyCompany = enterpriseProjectService.getApplyCompanyByProId(proId);
        if("science".equals(type)) {
            List<AwardStyleValidateScienceDO> scienceDOList = awardStyleValidateScienceService.list(params);
            AwardStyleValidateScienceDO scienceDO = scienceDOList.size() > 0 ? scienceDOList.get(0) : new AwardStyleValidateScienceDO();
            List<EnterpriseChengguoBaseInfoDO> baseInfoDOList = chengguoBaseInfoService.list(params);
            EnterpriseChengguoBaseInfoDO baseInfoDO = baseInfoDOList.size() > 0 ? baseInfoDOList.get(0) : null;
            scienceDO.setApplyName(baseInfoDO != null ? baseInfoDO.getChengguoName() : "");
            scienceDO.setApplyCompany(applyCompany);
            map.put("awardStyleValidateScience",scienceDO);
        }else if("personal".equals(type)) {
            List<AwardStyleValidatePersonDO> personDOList = awardStyleValidatePersonService.list(params);
            map.put("awardStyleValidatePerson",personDOList.size() > 0 ? personDOList.get(0) : new AwardStyleValidatePersonDO());
        }else if("team".equals(type)) {
            List<AwardStyleValidateTeamDO> teamDOList = awardStyleValidateTeamService.list(params);
            map.put("awardStyleValidateTeam",teamDOList.size() > 0 ? teamDOList.get(0) : new AwardStyleValidateTeamDO());
        }
        return prefix + "/style_validate_" + type;
    }
}
