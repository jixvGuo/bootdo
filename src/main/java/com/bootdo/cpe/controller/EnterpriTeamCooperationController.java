package com.bootdo.cpe.controller;

import com.bootdo.common.controller.BaseController;
import com.bootdo.common.controller.BaseScienceTeamController;
import com.bootdo.common.domain.DictDO;
import com.bootdo.common.service.DictService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.cpe.domain.EnterpriTeamCooperationDO;
import com.bootdo.cpe.service.EnterpriTeamCooperationService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

import static com.bootdo.system.config.DictTypeConstant.SCIENCE_TEAM_COOPERATION_TYPE;

/**
 * 团队合作情况汇总表
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-09-19 20:08:27
 */

@Controller
@RequestMapping("/cpe/enterpriTeamCooperation")
public class EnterpriTeamCooperationController extends BaseScienceTeamController {
    private String prefix = "enterprise/apply/team";

    @Autowired
    private EnterpriTeamCooperationService enterpriTeamCooperationService;
    @Autowired
    private DictService dictService;

    @GetMapping()
    @RequiresPermissions("cpe:enterpriTeamCooperation:enterpriTeamCooperation")
    String EnterpriTeamCooperation() {
        return "cpe/enterpriTeamCooperation/enterpriTeamCooperation";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("cpe:enterpriTeamCooperation:enterpriTeamCooperation")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<EnterpriTeamCooperationDO> enterpriTeamCooperationList = enterpriTeamCooperationService.list(query);
        int total = enterpriTeamCooperationService.count(query);
        PageUtils pageUtils = new PageUtils(enterpriTeamCooperationList, total);
        return pageUtils;
    }

    @GetMapping("/toList")
//    @RequiresPermissions("cpe:enterpriTeamCooperation:enterpriTeamCooperation")
    public String toList(@RequestParam Map<String, Object> params, ModelMap map, HttpServletRequest request) {
        packageAwardTaskId(map, params);
        //查询列表数据
        List<EnterpriTeamCooperationDO> enterpriTeamCooperationList = enterpriTeamCooperationService.list(params);

        map.put("list", enterpriTeamCooperationList);
        return prefix + "/cooperation_list_10";
    }

    @GetMapping("/add")
    @RequiresPermissions("cpe:enterpriTeamCooperation:add")
    String add(@RequestParam Map<String, Object> params, ModelMap map, HttpServletRequest request) {
        packageAwardTaskId(map, params);
        List<DictDO> cooperationList = dictService.listByType(SCIENCE_TEAM_COOPERATION_TYPE);
        map.put("cooperationList", cooperationList);
        map.put("enterpriTeamCooperation", new EnterpriTeamCooperationDO());
        return prefix + "/cooperation_add_10";
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("cpe:enterpriTeamCooperation:edit")
    String edit(@PathVariable("id") Integer id, @RequestParam Map<String, Object> params, ModelMap map, HttpServletRequest request) {
        packageAwardTaskId(map, params);
        EnterpriTeamCooperationDO enterpriTeamCooperation = enterpriTeamCooperationService.get(id);
        map.put("enterpriTeamCooperation", enterpriTeamCooperation);
        List<DictDO> cooperationList = dictService.listByType(SCIENCE_TEAM_COOPERATION_TYPE);
        map.put("cooperationList", cooperationList);
        return prefix + "/cooperation_add_10";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("cpe:enterpriTeamCooperation:add")
    public R save(EnterpriTeamCooperationDO enterpriTeamCooperation, @RequestParam Map<String, Object> params, ModelMap map, HttpServletRequest request) {
        packageAwardTaskId(map, params);
        Integer id = enterpriTeamCooperation.getId();
        if (id != null && id > 0) {
            enterpriTeamCooperationService.update(enterpriTeamCooperation);
            return R.ok();
        }
        if (enterpriTeamCooperationService.save(enterpriTeamCooperation) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("cpe:enterpriTeamCooperation:edit")
    public R update(EnterpriTeamCooperationDO enterpriTeamCooperation) {
        enterpriTeamCooperationService.update(enterpriTeamCooperation);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("cpe:enterpriTeamCooperation:remove")
    public R remove(Integer id) {
        if (enterpriTeamCooperationService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("cpe:enterpriTeamCooperation:batchRemove")
    public R remove(@RequestParam("ids[]") Integer[] ids) {
        enterpriTeamCooperationService.batchRemove(ids);
        return R.ok();
    }

}
