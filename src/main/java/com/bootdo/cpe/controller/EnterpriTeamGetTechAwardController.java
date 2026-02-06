package com.bootdo.cpe.controller;

import com.bootdo.common.controller.BaseController;
import com.bootdo.common.controller.BaseScienceTeamController;
import com.bootdo.common.domain.DictDO;
import com.bootdo.common.service.DictService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.cpe.domain.EnterpriTeamGetTechAwardDO;
import com.bootdo.cpe.service.EnterpriTeamGetTechAwardService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

import static com.bootdo.system.config.DictTypeConstant.SCIENCE_TEAM_AWARD_LEVE;

/**
 * 团队曾获科技奖励情况
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-09-19 17:23:35
 */

@Controller
@RequestMapping("/cpe/enterpriTeamGetTechAward")
public class EnterpriTeamGetTechAwardController extends BaseScienceTeamController {
    private String prefix = "enterprise/apply/team";

    @Autowired
    private EnterpriTeamGetTechAwardService enterpriTeamGetTechAwardService;
    @Autowired
    private DictService dictService;

    @GetMapping()
    @RequiresPermissions("cpe:enterpriTeamGetTechAward:enterpriTeamGetTechAward")
    String EnterpriTeamGetTechAward(@RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        return "cpe/enterpriTeamGetTechAward/enterpriTeamGetTechAward";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("cpe:enterpriTeamGetTechAward:enterpriTeamGetTechAward")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<EnterpriTeamGetTechAwardDO> enterpriTeamGetTechAwardList = enterpriTeamGetTechAwardService.list(query);
        int total = enterpriTeamGetTechAwardService.count(query);
        PageUtils pageUtils = new PageUtils(enterpriTeamGetTechAwardList, total);
        return pageUtils;
    }

    @GetMapping("/toList")
//    @RequiresPermissions("cpe:enterpriTeamGetTechAward:enterpriTeamGetTechAward")
    public String toList(@RequestParam Map<String, Object> params, ModelMap map, HttpServletRequest request) {
        packageAwardTaskId(map, params);
        //查询列表数据
        List<EnterpriTeamGetTechAwardDO> enterpriTeamGetTechAwardList = enterpriTeamGetTechAwardService.list(params);
        map.put("list", enterpriTeamGetTechAwardList);
        return prefix + "/get_technology_award_list_09";
    }

    @GetMapping("/add")
    @RequiresPermissions("cpe:enterpriTeamGetTechAward:add")
    String add(@RequestParam Map<String, Object> params, ModelMap map, HttpServletRequest request) {
        packageAwardTaskId(map, params);
        List<DictDO> levelList = dictService.listByType(SCIENCE_TEAM_AWARD_LEVE);
        map.put("levelList", levelList);
        map.put("enterpriTeamGetTechAward", new EnterpriTeamGetTechAwardDO());
        return prefix + "/get_technology_award_add_09";
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("cpe:enterpriTeamGetTechAward:edit")
    String edit(@PathVariable("id") Integer id, @RequestParam Map<String, Object> params, ModelMap map, HttpServletRequest request) {
        packageAwardTaskId(map, params);
        List<DictDO> levelList = dictService.listByType(SCIENCE_TEAM_AWARD_LEVE);
        map.put("levelList", levelList);
        EnterpriTeamGetTechAwardDO enterpriTeamGetTechAward = enterpriTeamGetTechAwardService.get(id);
        map.put("enterpriTeamGetTechAward", enterpriTeamGetTechAward);
        return prefix + "/get_technology_award_add_09";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("cpe:enterpriTeamGetTechAward:add")
    public R save(EnterpriTeamGetTechAwardDO enterpriTeamGetTechAward, ModelMap map, HttpServletRequest request) {
        packageEnterpriseProInfo(request, map);
        Integer id = enterpriTeamGetTechAward.getId();
        if (id != null && id > 0) {
            enterpriTeamGetTechAwardService.update(enterpriTeamGetTechAward);
            return R.ok();
        }
        if (enterpriTeamGetTechAwardService.save(enterpriTeamGetTechAward) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("cpe:enterpriTeamGetTechAward:edit")
    public R update(EnterpriTeamGetTechAwardDO enterpriTeamGetTechAward) {
        enterpriTeamGetTechAwardService.update(enterpriTeamGetTechAward);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("cpe:enterpriTeamGetTechAward:remove")
    public R remove(Integer id) {
        if (enterpriTeamGetTechAwardService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("cpe:enterpriTeamGetTechAward:batchRemove")
    public R remove(@RequestParam("ids[]") Integer[] ids) {
        enterpriTeamGetTechAwardService.batchRemove(ids);
        return R.ok();
    }

}
