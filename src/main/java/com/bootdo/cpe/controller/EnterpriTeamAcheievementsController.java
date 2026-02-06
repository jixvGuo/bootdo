package com.bootdo.cpe.controller;

import com.bootdo.common.controller.BaseController;
import com.bootdo.common.controller.BaseScienceTeamController;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.cpe.domain.EnterpriTeamAcheievementsDO;
import com.bootdo.cpe.service.EnterpriTeamAcheievementsService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 团队标志性成果
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-09-17 22:54:16
 */

@Controller
@RequestMapping("/cpe/enterpriTeamAcheievements")
public class EnterpriTeamAcheievementsController extends BaseScienceTeamController {
    private String prefix = "enterprise/apply/team";

    @Autowired
    private EnterpriTeamAcheievementsService enterpriTeamAcheievementsService;

    @GetMapping()
    @RequiresPermissions("cpe:enterpriTeamAcheievements:enterpriTeamAcheievements")
    String EnterpriTeamAcheievements() {
        return "cpe/enterpriTeamAcheievements/enterpriTeamAcheievements";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("cpe:enterpriTeamAcheievements:enterpriTeamAcheievements")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<EnterpriTeamAcheievementsDO> enterpriTeamAcheievementsList = enterpriTeamAcheievementsService.list(query);
        int total = enterpriTeamAcheievementsService.count(query);
        PageUtils pageUtils = new PageUtils(enterpriTeamAcheievementsList, total);
        return pageUtils;
    }

    @GetMapping("/add")
    @RequiresPermissions("cpe:enterpriTeamAcheievements:add")
    String add(@RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        return "cpe/enterpriTeamAcheievements/add";
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("cpe:enterpriTeamAcheievements:edit")
    String edit(@PathVariable("id") Integer id, @RequestParam Map<String, Object> params, ModelMap map, HttpServletRequest request) {
        packageAwardTaskId(map, params);
        EnterpriTeamAcheievementsDO enterpriTeamAcheievements = enterpriTeamAcheievementsService.get(id);
        map.put("enterpriTeamAcheievements", enterpriTeamAcheievements);
        return prefix + "/achievements_add_03";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("cpe:enterpriTeamAcheievements:add")
    public R save(EnterpriTeamAcheievementsDO enterpriTeamAcheievements, ModelMap map, HttpServletRequest request) {
        packageEnterpriseProInfo(request, map);
        Integer id = enterpriTeamAcheievements.getId();
        if (id != null && id > 0) {
            enterpriTeamAcheievementsService.update(enterpriTeamAcheievements);
            return R.ok();
        }
        enterpriTeamAcheievements.setOptUid(getUserId());
        if (enterpriTeamAcheievementsService.save(enterpriTeamAcheievements) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("cpe:enterpriTeamAcheievements:edit")
    public R update(EnterpriTeamAcheievementsDO enterpriTeamAcheievements) {
        enterpriTeamAcheievementsService.update(enterpriTeamAcheievements);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("cpe:enterpriTeamAcheievements:remove")
    public R remove(Integer id, ModelMap map, HttpServletRequest request) {
        packageEnterpriseProInfo(request, map);
        if (enterpriTeamAcheievementsService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("cpe:enterpriTeamAcheievements:batchRemove")
    public R remove(@RequestParam("ids[]") Integer[] ids) {
        enterpriTeamAcheievementsService.batchRemove(ids);
        return R.ok();
    }

}
