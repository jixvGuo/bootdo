package com.bootdo.system.controller;

import java.util.List;
import java.util.Map;

import com.bootdo.activiti.domain.EnterpriseProjectInfoDo;
import com.bootdo.activiti.service.AwardEnterpriseProjectService;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.controller.BaseScienceTeamController;
import com.bootdo.common.utils.PoiWordUtils;
import com.bootdo.cpe.domain.EnumProjectType;
import com.bootdo.cpe.petroleum_engineering_award.domain.OilProStatEnum;
import com.bootdo.system.domain.UserDO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.system.domain.EnterpriTeamInfoDO;
import com.bootdo.system.service.EnterpriTeamInfoService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

import javax.servlet.http.HttpServletResponse;

import static com.bootdo.common.config.Constant.*;

/**
 * 先进团队评审网页
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-06-30 04:48:14
 */

@Controller
@RequestMapping("/system/enterpriTeamInfo")
public class EnterpriTeamInfoController extends BaseScienceTeamController {
    @Autowired
    private EnterpriTeamInfoService enterpriTeamInfoService;
    @Autowired
    private AwardEnterpriseProjectService awardEnterpriseProjectService;

    private Map<String, Object> listDataParam;

    @GetMapping()
    @RequiresPermissions("system:enterpriTeamInfo:enterpriTeamInfo")
    String EnterpriTeamInfo(@RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        return "system/enterpriTeamInfo/enterpriTeamInfo";
    }

    @ResponseBody
    @GetMapping("/list")
//	@RequiresPermissions("system:enterpriTeamInfo:enterpriTeamInfo")
    public PageUtils list(@RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        UserDO user = getUser();
        List<Long> roleIdList = user.getRoleIds();
        long uid = getUserId();
        if (roleIdList.contains(ROLE_SCIENCE_ASSOCIATION_ID)) {
            //todo 临时使用协会联系人的用户id
            params.put("associationUserId", roleIdList.contains(ROLE_SCIENCE_OFFLINE_VIEW_ID) ? 101 : uid);
        } else if (roleIdList.contains(ROLE_ENTERPRISE_SCIENCE_ID)) {
            params.put("enterpriseUid", uid);
        } else {
            //分派给自己的项目
            params.put("assignUid", uid);
        }

        //查询列表数据
        params.put("proStatStr", "");
        Object keyWordObj = params.get("keyWord");
        if (keyWordObj != null) {
            List<String> proStatList = OilProStatEnum.getStatValByKey(keyWordObj.toString());
            if (proStatList.size() > 0) {
                String str = new String();
                for (String s : proStatList) {
                    str +=  s + ",";
                }
                params.put("proStatStr", str.substring(0, str.length() - 1));
            }
        }

        Query query = new Query(params);
        listDataParam = params;
        List<EnterpriTeamInfoDO> enterpriTeamInfoList = enterpriTeamInfoService.list(query);

        int total = enterpriTeamInfoService.count(query);
        PageUtils pageUtils = new PageUtils(enterpriTeamInfoList, total);
        return pageUtils;
    }


    @RequestMapping("/printExcel")
    public String printExcel(String id, HttpServletResponse response, ModelMap map, @RequestParam Map<String, Object> params) {
        listDataParam.put("limit", "100000");
        Query query = new Query(listDataParam);
        List<EnterpriTeamInfoDO> enterpriTeamInfoList = enterpriTeamInfoService.list(query);
        System.out.println(enterpriTeamInfoList.size());

        String[] title = {"编号", "项目类别", "团队名称", "团队带头人", "申报单位", "专业", "审查日期", "团队成立时间", "状态", "申报账号"};
        try {
            PoiWordUtils.downExcel(title, enterpriTeamInfoList, response);
        } catch (Exception e) {
        }
        map.addAttribute("result", "下载成功");
        return "";
    }

    @GetMapping("/add")
    @RequiresPermissions("system:enterpriTeamInfo:add")
    String add(@RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        return "system/enterpriTeamInfo/add";
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("system:enterpriTeamInfo:edit")
    String edit(@PathVariable("id") Integer id, @RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        EnterpriTeamInfoDO enterpriTeamInfo = enterpriTeamInfoService.get(id);
        map.addAttribute("enterpriTeamInfo", enterpriTeamInfo);
        return "system/enterpriTeamInfo/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("system:enterpriTeamInfo:add")
    public R save(EnterpriTeamInfoDO enterpriTeamInfo) {

        if (enterpriTeamInfo.getId() != null && enterpriTeamInfo.getId() > 0) {
            //修改
            enterpriTeamInfoService.update(enterpriTeamInfo);
            EnterpriseProjectInfoDo projectInfoDo = new EnterpriseProjectInfoDo();
            projectInfoDo.setProType(EnumProjectType.SCIENCE_TEAM.getProType());
            projectInfoDo.setProSubType(EnumProjectType.SCIENCE_TEAM.getProType());
            projectInfoDo.setId(enterpriTeamInfo.getProId());
            projectInfoDo.setMajor(enterpriTeamInfo.getResearchDirection());
            awardEnterpriseProjectService.updateProjectInfo(projectInfoDo);
            return R.ok();
        } else if (enterpriTeamInfoService.save(enterpriTeamInfo) > 0) {
            //TODO 保存项目记录
            EnterpriseProjectInfoDo projectInfoDo = new EnterpriseProjectInfoDo();
            projectInfoDo.setProType(EnumProjectType.SCIENCE_TEAM.getProType());
            projectInfoDo.setProSubType(EnumProjectType.SCIENCE_TEAM.getProType());
            projectInfoDo.setId(enterpriTeamInfo.getProId());
            projectInfoDo.setPublishTaskId(enterpriTeamInfo.getTaskId());
            projectInfoDo.setCreateUid(getUserId());
            projectInfoDo.setChengguo("团队:" + enterpriTeamInfo.getTeamName());
            projectInfoDo.setMajor(enterpriTeamInfo.getResearchDirection());
            awardEnterpriseProjectService.updateProjectInfo(projectInfoDo);
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("system:enterpriTeamInfo:edit")
    public R update(EnterpriTeamInfoDO enterpriTeamInfo) {
        enterpriTeamInfoService.update(enterpriTeamInfo);
        EnterpriseProjectInfoDo projectInfoDo = new EnterpriseProjectInfoDo();
        projectInfoDo.setId(enterpriTeamInfo.getProId());
        projectInfoDo.setPublishTaskId(enterpriTeamInfo.getTaskId());
        projectInfoDo.setCreateUid(getUserId());
        projectInfoDo.setChengguo("团队:" + enterpriTeamInfo.getTeamName());
        projectInfoDo.setProType(EnumProjectType.SCIENCE_TEAM.getProType());
        projectInfoDo.setProSubType(EnumProjectType.SCIENCE_TEAM.getProType());
        awardEnterpriseProjectService.updateProjectInfo(projectInfoDo);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("system:enterpriTeamInfo:remove")
    public R remove(Integer id) {
        if (enterpriTeamInfoService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("system:enterpriTeamInfo:batchRemove")
    public R remove(@RequestParam("ids[]") Integer[] ids) {
        enterpriTeamInfoService.batchRemove(ids);
        return R.ok();
    }

}
