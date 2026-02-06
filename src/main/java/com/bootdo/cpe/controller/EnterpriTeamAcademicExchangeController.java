package com.bootdo.cpe.controller;

import com.bootdo.common.controller.BaseController;
import com.bootdo.common.controller.BaseScienceTeamController;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.cpe.domain.EnterpriTeamAcademicExchangeDO;
import com.bootdo.cpe.service.EnterpriTeamAcademicExchangeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 团队学术交流
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-09-19 01:31:08
 */

@Controller
@RequestMapping("/cpe/enterpriTeamAcademicExchange")
public class EnterpriTeamAcademicExchangeController extends BaseScienceTeamController {
    private String prefix = "enterprise/apply/team";

    @Autowired
    private EnterpriTeamAcademicExchangeService enterpriTeamAcademicExchangeService;

    @GetMapping()
    @RequiresPermissions("cpe:enterpriTeamAcademicExchange:enterpriTeamAcademicExchange")
    String EnterpriTeamAcademicExchange() {
        return "cpe/enterpriTeamAcademicExchange/enterpriTeamAcademicExchange";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("cpe:enterpriTeamAcademicExchange:enterpriTeamAcademicExchange")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<EnterpriTeamAcademicExchangeDO> enterpriTeamAcademicExchangeList = enterpriTeamAcademicExchangeService.list(query);
        int total = enterpriTeamAcademicExchangeService.count(query);
        PageUtils pageUtils = new PageUtils(enterpriTeamAcademicExchangeList, total);
        return pageUtils;
    }

    @GetMapping("/toList")
//    @RequiresPermissions("cpe:enterpriTeamAcademicExchange:enterpriTeamAcademicExchange")
    public String toList(@RequestParam Map<String, Object> params, ModelMap map, HttpServletRequest request) {
        packageAwardTaskId(map, params);
        //查询列表数据
        List<EnterpriTeamAcademicExchangeDO> enterpriTeamAcademicExchangeList = enterpriTeamAcademicExchangeService.list(params);
        map.put("list", enterpriTeamAcademicExchangeList);
        return prefix + "/academic_exchange_list_05";
    }

    @GetMapping("/add")
    @RequiresPermissions("cpe:enterpriTeamAcademicExchange:add")
    String add(@RequestParam Map<String, Object> params, ModelMap map, HttpServletRequest request) {
        packageAwardTaskId(map, params);
        map.put("enterpriTeamAcademicExchange", new EnterpriTeamAcademicExchangeDO());
        return prefix + "/academic_exchange_add_05";
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("cpe:enterpriTeamAcademicExchange:edit")
    String edit(@PathVariable("id") Integer id, @RequestParam Map<String, Object> params, ModelMap map, HttpServletRequest request) {
        packageAwardTaskId(map, params);
        EnterpriTeamAcademicExchangeDO enterpriTeamAcademicExchange = enterpriTeamAcademicExchangeService.get(id);
        map.put("enterpriTeamAcademicExchange", enterpriTeamAcademicExchange);
        return prefix + "/academic_exchange_add_05";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("cpe:enterpriTeamAcademicExchange:add")
    public R save(EnterpriTeamAcademicExchangeDO enterpriTeamAcademicExchange) {
        Integer id = enterpriTeamAcademicExchange.getId();
        enterpriTeamAcademicExchange.setOptUid(getUserId());
        if (id != null && id > 0) {
            enterpriTeamAcademicExchangeService.update(enterpriTeamAcademicExchange);
            return R.ok();
        }
        if (enterpriTeamAcademicExchangeService.save(enterpriTeamAcademicExchange) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("cpe:enterpriTeamAcademicExchange:edit")
    public R update(EnterpriTeamAcademicExchangeDO enterpriTeamAcademicExchange) {
        enterpriTeamAcademicExchangeService.update(enterpriTeamAcademicExchange);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("cpe:enterpriTeamAcademicExchange:remove")
    public R remove(Integer id) {
        if (enterpriTeamAcademicExchangeService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("cpe:enterpriTeamAcademicExchange:batchRemove")
    public R remove(@RequestParam("ids[]") Integer[] ids) {
        enterpriTeamAcademicExchangeService.batchRemove(ids);
        return R.ok();
    }

}
