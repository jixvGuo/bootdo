package com.bootdo.activiti.controller;

import com.bootdo.activiti.domain.EnterpriseDocUploadDo;
import com.bootdo.activiti.domain.EnterpriseProjectInfoDo;
import com.bootdo.activiti.service.AwardEnterpriseProjectService;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.controller.BaseScienceTeamController;
import com.bootdo.common.domain.DictDO;
import com.bootdo.common.service.DictService;
import com.bootdo.common.service.FileService;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.cpe.domain.EnterpriTeamIntrductionDO;
import com.bootdo.cpe.domain.EnumApplyEnterpriseProStat;
import com.bootdo.cpe.service.EnterpriTeamIntrductionService;
import com.bootdo.cpe.utils.EnumUtils;
import com.bootdo.system.config.DictTypeConstant;
import com.bootdo.system.domain.*;
import com.bootdo.system.service.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bootdo.system.config.DictTypeConstant.SCIENCE_TEAM_USER_RESEARCH_FIELD;

@Controller
@RequestMapping("/chengguo_team")
public class ChengguoTeamController extends BaseScienceTeamController {

    String prefix = "act/award/chengguo_team";
    String prefixEnterpriseApply = "enterprise/apply/team";
    @Autowired
    private EnterpriTeamInfoService enterpriTeamInfoService;
    @Autowired
    private EnterpriTeamMainUsersService enterpriTeamMainUsersService;
    @Autowired
    private EnterpriTeamUsersService enterpriTeamUsersService;
    @Autowired
    private DictService dictService;
    @Autowired
    private EnterpriTeamUsersListService enterpriTeamUsersListService;
    @Autowired
    private AwardEnterpriseProjectService awardEnterpriseProjectService;
    @Autowired
    private EnterpriTeamIntrductionService enterpriTeamIntrductionService;
    @Autowired
    private FileService fileService;

    @RequestMapping("/apply_team/edit")
    @RequiresPermissions("act:award:apply_team")
    public String toAdminPersonalEdit(@RequestParam Map<String, Object> params, ModelMap map){
        return executeToAdminPersonalEdit(params, map);
    }
    public String executeToAdminPersonalEdit(@RequestParam Map<String, Object> params, ModelMap map){
        applyParams(map, params);
        packageAwardTaskId(map, params);
        //团队申报
        map.put("opt_type","edit");
        return prefix + "/index";
    }

    @RequestMapping("/teamView")
    public String toTeamView(@RequestParam Map<String, Object> params, ModelMap map){
        params.put("readonly", 1);
        map.put("readonly", 1);
        return executeToAdminPersonalEdit(params, map);
    }

    @RequestMapping("/apply_team/state")
    @RequiresPermissions("act:award:apply_team")
    public String toTeamApplyState(@RequestParam Map<String, Object> params, ModelMap map){
        packageAwardTaskId(map, params);
        map.put("opt_type","edit");
        return prefix + "/enterprise_team_state";
    }


    @RequestMapping("/base_info")
    public String toBaseInfo(@RequestParam Map<String, Object> params, ModelMap map){
        packageAwardTaskId(map, params);
        Object proIdObj = params.get("proId");
        String proId = proIdObj != null ? proIdObj.toString() : "-1";
        List<EnterpriTeamInfoDO> list = enterpriTeamInfoService.list(params);
        map.put("teamInfoList",list);
        EnterpriseProjectInfoDo projectInfoDo = awardEnterpriseProjectService.get(proId);
        map.put("btnUpdateStr","修改");
        //是否可以编辑，false不可以编辑，true可以编辑
        map.put("isEdit",true);
        if(projectInfoDo != null && !EnumUtils.proIsCouldEdit(projectInfoDo.getProStat())) {
            //不可以进行编辑
            map.put("btnUpdateStr","查看");
            map.put("isEdit",false);
        }
        return prefixEnterpriseApply + "/base_info_list_02";
    }

    //新增
    @RequestMapping("/base_info_add")
    public String toBaseInfoAdd(String isUpdate, @RequestParam Map<String, Object> params, ModelMap map){
        packageAwardTaskId(map, params);
        map.put("id",params.get("id"));
        Object proIdObj = params.get("proId");
        String proId = proIdObj != null ? proIdObj.toString() : "-1";

        List<EnterpriTeamInfoDO> list = enterpriTeamInfoService.list(params);
        EnterpriTeamInfoDO teamInfoDO = list.size() > 0 ? list.get(0) : new EnterpriTeamInfoDO();
        map.put("enterpriTeamInfo", teamInfoDO);

        List<DictDO> researchDirectionList = dictService.listByType(DictTypeConstant.PROFESS_TYPE);
        List<DictDO> classTypeList = dictService.listByType(DictTypeConstant.achievement_belongs);
        map.put("researchDirectList",researchDirectionList);
        map.put("classTypeList",classTypeList);
        map.put("isUpdate",isUpdate);

        EnterpriseProjectInfoDo projectInfoDo = awardEnterpriseProjectService.get(proId);
        map.put("projectInfo",projectInfoDo);

        return prefixEnterpriseApply + "/base_info_add_02";
    }

     //新增
    @RequestMapping("/base_info_del")
    public @ResponseBody
    R toBaseInfoDel(int id, ModelMap map){
       int rst = enterpriTeamInfoService.remove(id);
       if(rst == 0) {
           return R.error("删除失败");
       }
        return R.ok();
    }

    //打印
    @RequestMapping("/base_info_print")
    public String toBaseInfoPrint(@RequestParam Map<String, Object> params, ModelMap map){
        packageAwardTaskId(map, params);
        map.put("id",params.get("id"));
        List<EnterpriTeamInfoDO> list = enterpriTeamInfoService.list(params);
        EnterpriTeamInfoDO teamInfoDO = list.size() > 0 ? list.get(0) : new EnterpriTeamInfoDO();
        map.put("enterpriTeamInfo",teamInfoDO);
        return prefixEnterpriseApply + "/base_info_print_02";
    }



   // 团队简介
    @RequestMapping("/team_intrduction")
    public String chengguoTeamIntrduction(Boolean isOnlyView, @RequestParam Map<String, Object> params, ModelMap map){
        packageAwardTaskId(map, params);
        map.put("isOnlyView",isOnlyView);

        List<EnterpriTeamIntrductionDO> intrductionDOList = enterpriTeamIntrductionService.list(params);
        map.put("projectInfo",intrductionDOList.size() > 0 ? intrductionDOList.get(0) : new EnterpriTeamIntrductionDO());

        return prefixEnterpriseApply + "/team_introduction";
    }

    // 四、主要科技成就及发展情况
//    Major technological achievements and development
    @RequestMapping("/major_achievement_dev")
    public String chengguoTeamMajorAchievement(Boolean isOnlyView,@RequestParam Map<String, Object> params, ModelMap map){
        packageAwardTaskId(map, params);
        map.put("isOnlyView",isOnlyView);

        List<EnterpriTeamIntrductionDO> intrductionDOList = enterpriTeamIntrductionService.list(params);
        map.put("projectInfo",intrductionDOList.size() > 0 ? intrductionDOList.get(0) : new EnterpriTeamIntrductionDO());

        return prefixEnterpriseApply + "/major_achieventment_dev";
    }





    /**
     * 上传附件
     * @return
     */
    @RequestMapping("/toUploadTeamFile")
    public String toUploadEnclosure(Boolean isOnlyView,@RequestParam Map<String, Object> params, ModelMap map){
        packageAwardTaskId(map, params);
        map.put("isOnlyView",isOnlyView);
        Object proIdObj = map.get("proId");
        String proId = proIdObj == null ? "0" : proIdObj.toString();

        List<EnterpriseDocUploadDo> docUploadDoList = fileService.listUploadEnterpriseDocs(params);
        map.put("docUploadDoList",docUploadDoList);
        map.put("fileSize",docUploadDoList.size());
        EnterpriseProjectInfoDo projectInfoDo = awardEnterpriseProjectService.get(proId);
        map.put("projectInfo",projectInfoDo);
        return prefixEnterpriseApply + "/team_attachement_17";
    }

    @RequestMapping("/members")
    public String toMembers( @RequestParam Map<String, Object> params, ModelMap map){
        packageAwardTaskId(map, params);
        Object proIdObj = params.get("proId");
        String proId = proIdObj != null ? proIdObj.toString() : "-1";

        //TODO 汇总人员情况信息
        EnterpriTeamUsersDO teamUsersDO = new EnterpriTeamUsersDO();//人员构成
        List<EnterpriTeamUsersDO> usersDOList = enterpriTeamUsersService.list(params);
        if(usersDOList.size() > 0) {
            teamUsersDO = usersDOList.get(0);
        }
        map.put("enterpriTeamUsers",teamUsersDO);

        map.put("enterpriTeamUsersList",new EnterpriTeamUsersListDO());//团队主要构成

        List<DictDO> researchFiledList = dictService.listByType(SCIENCE_TEAM_USER_RESEARCH_FIELD);
        map.put("researchFiledList",researchFiledList);//团队奖用户研究领域列表选项

        //带头人
        Map<String,Object> leaderParams = new HashMap<>();
        leaderParams.put("userType","leader");
        leaderParams.putAll(params);
        List<EnterpriTeamUsersListDO> leaderUserList = enterpriTeamUsersListService.list(leaderParams);
        map.put("leaderUserList",leaderUserList);

        //其他人
        Map<String,Object> otherParams = new HashMap<>();
        otherParams.put("userType","other");
        otherParams.putAll(params);
        List<EnterpriTeamUsersListDO> otherUserList = enterpriTeamUsersListService.list(otherParams);
        map.put("otherUserList",otherUserList);

        List<EnterpriTeamMainUsersDO> mainUsersDOList = enterpriTeamMainUsersService.list(params);
        EnterpriTeamMainUsersDO mainUsersDO = mainUsersDOList.size() > 0 ? mainUsersDOList.get(0) : new EnterpriTeamMainUsersDO();
        map.put("enterpriTeamMainUsers",mainUsersDO);

        EnterpriseProjectInfoDo projectInfoDo = awardEnterpriseProjectService.get(proId);
        map.put("projectInfo",projectInfoDo);

        return prefixEnterpriseApply + "/member_info_add";
    }

     @RequestMapping("/members_print")
    public String toMembersPrint(@RequestParam Map<String, Object> params, ModelMap map){
        packageAwardTaskId(map, params);
        //TODO 汇总人员情况信息
        EnterpriTeamUsersDO teamUsersDO = new EnterpriTeamUsersDO();
        map.put("enterpriTeamUsers",teamUsersDO);

        List<EnterpriTeamMainUsersDO> mainUsersDOList = enterpriTeamMainUsersService.list(params);
        EnterpriTeamMainUsersDO mainUsersDO = mainUsersDOList.size() > 0 ? mainUsersDOList.get(0) : new EnterpriTeamMainUsersDO();
        map.put("enterpriTeamMainUsers",mainUsersDO);
//        return prefix + "/members";
        return prefixEnterpriseApply + "/member_info_print";
    }

    @RequestMapping("/info_edit")
    public String toInfoEdit(@RequestParam Map<String, Object> params, ModelMap map){
        packageAwardTaskId(map, params);
        List<EnterpriTeamInfoDO> list = enterpriTeamInfoService.list(params);
        EnterpriTeamInfoDO teamInfoDO = list.size() > 0 ? list.get(0) : new EnterpriTeamInfoDO();
        map.put("enterpriTeamInfo",teamInfoDO);
        return prefix + "/info_edit";
    }





    //标致性成果
    @RequestMapping("/mark_chengguo")
    public String toMarkChengguo(@RequestParam Map<String, Object> params, ModelMap map){
        packageAwardTaskId(map, params);
        return prefix + "/mark_chengguo";
    }
    //论文学术情况
    @RequestMapping("/paper_info")
    public String toPaperInfo(@RequestParam Map<String, Object> params, ModelMap map){
        packageAwardTaskId(map, params);
        return prefix + "/paper_info";
    }
    //学术交流
    @RequestMapping("/academic_exchange")
    public String toAcademicExchange(@RequestParam Map<String, Object> params, ModelMap map){
        packageAwardTaskId(map, params);
        return prefix + "/academic_exchange";
    }
    //知识产权和技术标准
    @RequestMapping("/technical_standard")
    public String toTechnicalStandard(@RequestParam Map<String, Object> params, ModelMap map){
        packageAwardTaskId(map, params);
        return prefix + "/technical_standard";
    }
    //承担项目情况
    @RequestMapping("/projects")
    public String toProjects(@RequestParam Map<String, Object> params, ModelMap map){
        packageAwardTaskId(map, params);
        return prefix + "/projects";
    }
    //第三方评价
    @RequestMapping("/third_evaluate")
    public String toThirdEvaluate(@RequestParam Map<String, Object> params, ModelMap map){
        packageAwardTaskId(map, params);
        return prefix + "/third_evaluate";
    }

    //已有奖励
    @RequestMapping("/awards")
    public String toAwards(@RequestParam Map<String, Object> params, ModelMap map){
        packageAwardTaskId(map, params);
        return prefix + "/awards";
    }
    //技术趋势
    @RequestMapping("/technology_trends")
    public String toTrends(@RequestParam Map<String, Object> params, ModelMap map){
        packageAwardTaskId(map, params);
        return prefix + "/technology_trends";
    }

    //合作
    @RequestMapping("/cooperation")
    public String toCooperation(@RequestParam Map<String, Object> params, ModelMap map){
        packageAwardTaskId(map, params);
        return prefix + "/cooperation";
    }
    //带头人
    @RequestMapping("/leader")
    public String toLeader(@RequestParam Map<String, Object> params, ModelMap map){
        packageAwardTaskId(map, params);
        return prefix + "/leader";
    }
    //其他成员窗口
    @RequestMapping("/ohter_member_window")
    public String toOtherMemberWindow(@RequestParam Map<String, Object> params, ModelMap map){
        packageAwardTaskId(map, params);
        return prefix + "/ohter_member_window";
    }
    //支持单位
    @RequestMapping("/support_company")
    public String toSupportCompany(@RequestParam Map<String, Object> params, ModelMap map){
        packageAwardTaskId(map, params);
        return prefix + "/support_company";
    }
    //附件列表
    @RequestMapping("/enclosure_list")
    public String toEnclosureList(@RequestParam Map<String, Object> params, ModelMap map){
        packageAwardTaskId(map, params);
        return prefix + "/enclosure_list";
    }

}
