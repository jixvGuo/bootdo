package com.bootdo.cpe.controller.surver;

import com.bootdo.activiti.domain.AwardScoreDetailInfo;
import com.bootdo.activiti.domain.EnterpriseProjectInfoDo;
import com.bootdo.activiti.service.AwardEnterpriseProjectService;
import com.bootdo.activiti.service.SpecialistService;
import com.bootdo.common.controller.BaseSurverController;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.cpe.domain.EnumProjectType;
import com.bootdo.cpe.domain.ExpertGroupDO;
import com.bootdo.cpe.service.ExpertGroupService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/surverScore")
public class SurverScoreController extends BaseSurverController {
    private String prefix = "cpe/survey";
    @Autowired
    private SpecialistService specialistService;
    @Autowired
    private ExpertGroupService expertGroupService;
    @Autowired
    private AwardEnterpriseProjectService awardEnterpriseProjectService;

    @RequestMapping("/proList")
    @RequiresPermissions("surveraward:score:prolist")
    public String toSurverScorePro(@RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        Object proSubType = params.get("proSubType");
        map.put("proSubType", proSubType);
        return prefix + "/specialist/score/score_pro_list";
    }

    /**
     * 评分标准表格
     * @return
     */
    @RequestMapping("/standardTable")
    public String scoreStandardView() {
        return prefix + "/specialist/score/score_standard_main";
    }

    /**
     * 优秀工程勘察设计软件与优秀工程标准设计评审标准分值表
     * @return
     */
    @RequestMapping("/standardScore")
    public String scoreStandardScoreView() {
        return prefix + "/specialist/score/score_pro_standard_table";
    }

    /**
     * 优秀工程设计评审标准分值表
     * @return
     */
    @RequestMapping("/standardDesign")
    public String scoreStandardDesignView() {
        return prefix + "/specialist/score/score_pro_destin_table";
    }


    /**
     * 去打分页面
     * @param params
     * @param map
     * @return
     */
    @RequestMapping("/toScore")
    public String toScorePage(@RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        Object proSubType = params.get("proSubType");
        map.put("proSubType", proSubType);
        map.put("major", params.get("major"));
        //科技奖只有一个内容信息进行评审，因此子项为0
        params.put("uid", getUserId());

        List<AwardScoreDetailInfo> scoreList = specialistService.getProScoreDetails(params);
        double totalScore = 0;
        for (AwardScoreDetailInfo scoreInfo : scoreList) {
            if (StringUtils.isBlank(scoreInfo.getScoreTxt())) {
                map.put(scoreInfo.getScoreKey(), scoreInfo.getScoreVal());
            } else {
                map.put(scoreInfo.getScoreKey(), scoreInfo.getScoreTxt());
            }
            totalScore += scoreInfo.getScoreVal();
        }
        map.put("totalScore", totalScore);
        map.put("itemId", 0);
        EnterpriseProjectInfoDo projectInfoDo = awardEnterpriseProjectService.get(params.get("proId").toString());
        map.put("proInfo", projectInfoDo);
        return prefix + "/specialist/score/score_major_group_table";
    }

       /**
     * 添加专家账号
     * @return
     */
    @RequestMapping("/associationViewScore")
    @RequiresPermissions("surveraward:specialist_score:check")
    public String expertCheckScore(@RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        List<String> majorList = awardEnterpriseProjectService.getProMajorList(params);
        map.put("profession", majorList);
        //用于入库标记账号的奖项类型
        String proType = EnumProjectType.SURVER_PRO.getProType();
        map.put("proType", proType);
        Map<String, Object> selParams = new HashMap<>();
        selParams.put("taskId", params.get("taskId"));
        selParams.put("groupName", params.get("major"));
        selParams.put("proType", params.get("proSubType"));
        List<ExpertGroupDO> selList = expertGroupService.list(selParams);
        map.put("selInfoList", selList);
        map.put("proSubType", params.get("proSubType"));

        return prefix + "/specialist/score/surver_expert_check_score";
    }

    @RequestMapping("/toCheckScoreProList")
    public String toCheckScoreProList(@RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        map.put("account", params.get("account"));
        map.put("major", params.get("major"));
        map.put("proType", params.get("proSubType"));
        return prefix + "/specialist/score/surver_expert_check_score_pro_list";
    }


      /**
     * 获取当前用户参与的项目信息
     *
     * @param map
     * @return
     */
    @RequestMapping("/getCurLeaderPro")
    @ResponseBody
    public PageUtils getDataByScoreType(String proType, String major, String account, ModelMap map) {
        //根据用户id获取当前分派的打分任务列表
        Map<String, Object> params = new HashMap<>();
        params.put("proType", "surver_pro");
        if (StringUtils.isBlank(major)) {
            //默认指定一个不存在的专业
            major = "暂无NULL";
        }
        params.put("scoreMajor", major);
        String[] accArr = account.split("\\(");
        if (accArr.length > 1) {
            account = accArr[accArr.length - 1].replace(")", "");
        }
        if (StringUtils.isBlank(account)) {
            //指定一个暂无账号
            account = "-123La暂无";
        }

        params.put("scoreAccount", account);
        params.put("applyType", "surver");
        List<EnterpriseProjectInfoDo> list = awardEnterpriseProjectService.list(params);
        int total = awardEnterpriseProjectService.count(params);
        PageUtils pageUtils = new PageUtils(list, total);
        return pageUtils;
    }

}
