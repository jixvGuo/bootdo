package com.bootdo.cpe.controller;

import com.bootdo.common.controller.BaseController;
import com.bootdo.common.controller.BaseScienceTeamController;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.cpe.domain.EnterpriTeamEvaluateOtherDO;
import com.bootdo.cpe.service.EnterpriTeamEvaluateOtherService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 团队第三方评价
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-09-19 16:46:09
 */

@Controller
@RequestMapping("/cpe/enterpriTeamEvaluateOther")
public class EnterpriTeamEvaluateOtherController extends BaseScienceTeamController {
    private String prefix = "enterprise/apply/team";

    @Autowired
    private EnterpriTeamEvaluateOtherService enterpriTeamEvaluateOtherService;

    @GetMapping()
    @RequiresPermissions("cpe:enterpriTeamEvaluateOther:enterpriTeamEvaluateOther")
    String EnterpriTeamEvaluateOther(@RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        return "cpe/enterpriTeamEvaluateOther/enterpriTeamEvaluateOther";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("cpe:enterpriTeamEvaluateOther:enterpriTeamEvaluateOther")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<EnterpriTeamEvaluateOtherDO> enterpriTeamEvaluateOtherList = enterpriTeamEvaluateOtherService.list(query);
        int total = enterpriTeamEvaluateOtherService.count(query);
        PageUtils pageUtils = new PageUtils(enterpriTeamEvaluateOtherList, total);
        return pageUtils;
    }

    @GetMapping("/toList")
//    @RequiresPermissions("cpe:enterpriTeamEvaluateOther:enterpriTeamEvaluateOther")
    public String toList(@RequestParam Map<String, Object> params, ModelMap map, HttpServletRequest request) {
        packageAwardTaskId(map, params);
        //查询列表数据
        List<EnterpriTeamEvaluateOtherDO> enterpriTeamEvaluateOtherList = enterpriTeamEvaluateOtherService.list(params);
        map.put("list", enterpriTeamEvaluateOtherList);

        return prefix + "/evaluate_other_list_08";
    }

    @GetMapping("/add")
    @RequiresPermissions("cpe:enterpriTeamEvaluateOther:add")
    String add(@RequestParam Map<String, Object> params, ModelMap map, HttpServletRequest request) {
        packageAwardTaskId(map, params);
        map.put("enterpriTeamEvaluateOther", new EnterpriTeamEvaluateOtherDO());
        return prefix + "/evaluate_other_add_08";
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("cpe:enterpriTeamEvaluateOther:edit")
    String edit(@PathVariable("id") Integer id, @RequestParam Map<String, Object> params, ModelMap map, HttpServletRequest request) {
        packageAwardTaskId(map, params);
        EnterpriTeamEvaluateOtherDO enterpriTeamEvaluateOther = enterpriTeamEvaluateOtherService.get(id);
        map.put("enterpriTeamEvaluateOther", enterpriTeamEvaluateOther);
        return prefix + "/evaluate_other_add_08";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("cpe:enterpriTeamEvaluateOther:add")
    public R save(EnterpriTeamEvaluateOtherDO enterpriTeamEvaluateOther) {
        Integer id = enterpriTeamEvaluateOther.getId();
        enterpriTeamEvaluateOther.setOptUid(getUserId());
        if (id != null && id > 0) {
            enterpriTeamEvaluateOtherService.update(enterpriTeamEvaluateOther);
            return R.ok();
        }
        if (enterpriTeamEvaluateOtherService.save(enterpriTeamEvaluateOther) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("cpe:enterpriTeamEvaluateOther:edit")
    public R update(EnterpriTeamEvaluateOtherDO enterpriTeamEvaluateOther) {
        enterpriTeamEvaluateOtherService.update(enterpriTeamEvaluateOther);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("cpe:enterpriTeamEvaluateOther:remove")
    public R remove(Integer id) {
        if (enterpriTeamEvaluateOtherService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("cpe:enterpriTeamEvaluateOther:batchRemove")
    public R remove(@RequestParam("ids[]") Integer[] ids) {
        enterpriTeamEvaluateOtherService.batchRemove(ids);
        return R.ok();
    }

}
