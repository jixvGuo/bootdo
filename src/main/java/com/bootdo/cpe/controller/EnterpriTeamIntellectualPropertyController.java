package com.bootdo.cpe.controller;

import com.bootdo.common.controller.BaseController;
import com.bootdo.common.controller.BaseScienceTeamController;
import com.bootdo.common.domain.DictDO;
import com.bootdo.common.service.DictService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.cpe.domain.EnterpriTeamIntellectualPropertyDO;
import com.bootdo.cpe.service.EnterpriTeamIntellectualPropertyService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

import static com.bootdo.system.config.DictTypeConstant.SCIENCE_TEAM_INTELLECTUAL_PROPERTY_TYPE;

/**
 * 所获知识产权和技术标准情况
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-09-19 09:29:22
 */

@Controller
@RequestMapping("/cpe/enterpriTeamIntellectualProperty")
public class EnterpriTeamIntellectualPropertyController extends BaseScienceTeamController {
    private String prefix = "enterprise/apply/team";

    @Autowired
    private EnterpriTeamIntellectualPropertyService enterpriTeamIntellectualPropertyService;
    @Autowired
    private DictService dictService;

    @GetMapping()
    @RequiresPermissions("cpe:enterpriTeamIntellectualProperty:enterpriTeamIntellectualProperty")
    String EnterpriTeamIntellectualProperty(@RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        return "cpe/enterpriTeamIntellectualProperty/enterpriTeamIntellectualProperty";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("cpe:enterpriTeamIntellectualProperty:enterpriTeamIntellectualProperty")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<EnterpriTeamIntellectualPropertyDO> enterpriTeamIntellectualPropertyList = enterpriTeamIntellectualPropertyService.list(query);
        int total = enterpriTeamIntellectualPropertyService.count(query);
        PageUtils pageUtils = new PageUtils(enterpriTeamIntellectualPropertyList, total);
        return pageUtils;
    }

    @GetMapping("/toList")
//    @RequiresPermissions("cpe:enterpriTeamIntellectualProperty:enterpriTeamIntellectualProperty")
    public String toList(@RequestParam Map<String, Object> params, ModelMap map, HttpServletRequest request) {
        packageAwardTaskId(map, params);
        //查询列表数据
        List<EnterpriTeamIntellectualPropertyDO> enterpriTeamIntellectualPropertyList = enterpriTeamIntellectualPropertyService.list(params);
        map.put("list", enterpriTeamIntellectualPropertyList);
        return prefix + "/intellectual_property_list_06";
    }

    @GetMapping("/add")
    @RequiresPermissions("cpe:enterpriTeamIntellectualProperty:add")
    String add(@RequestParam Map<String, Object> params, ModelMap map, HttpServletRequest request) {
        packageAwardTaskId(map, params);
        List<DictDO> types = dictService.listByType(SCIENCE_TEAM_INTELLECTUAL_PROPERTY_TYPE);
        map.put("intProTypes", types);
        map.put("enterpriTeamIntellectualProperty", new EnterpriTeamIntellectualPropertyDO());
        return prefix + "/intellectual_property_add_06";
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("cpe:enterpriTeamIntellectualProperty:edit")
    String edit(@PathVariable("id") Integer id, @RequestParam Map<String, Object> params, ModelMap map, HttpServletRequest request) {
        packageAwardTaskId(map, params);
        EnterpriTeamIntellectualPropertyDO enterpriTeamIntellectualProperty = enterpriTeamIntellectualPropertyService.get(id);
        List<DictDO> types = dictService.listByType(SCIENCE_TEAM_INTELLECTUAL_PROPERTY_TYPE);
        map.addAttribute("intProTypes", types);
        map.addAttribute("enterpriTeamIntellectualProperty", enterpriTeamIntellectualProperty);
        return prefix + "/intellectual_property_add_06";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("cpe:enterpriTeamIntellectualProperty:add")
    public R save(EnterpriTeamIntellectualPropertyDO enterpriTeamIntellectualProperty, ModelMap map, HttpServletRequest request) {
        packageEnterpriseProInfo(request, map);
        Integer id = enterpriTeamIntellectualProperty.getId();
        enterpriTeamIntellectualProperty.setOptUid(getUserId());
        if (id != null && id > 0) {
            enterpriTeamIntellectualPropertyService.update(enterpriTeamIntellectualProperty);
            return R.ok();
        }
        if (enterpriTeamIntellectualPropertyService.save(enterpriTeamIntellectualProperty) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("cpe:enterpriTeamIntellectualProperty:edit")
    public R update(EnterpriTeamIntellectualPropertyDO enterpriTeamIntellectualProperty) {
        enterpriTeamIntellectualPropertyService.update(enterpriTeamIntellectualProperty);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("cpe:enterpriTeamIntellectualProperty:remove")
    public R remove(Integer id) {
        if (enterpriTeamIntellectualPropertyService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("cpe:enterpriTeamIntellectualProperty:batchRemove")
    public R remove(@RequestParam("ids[]") Integer[] ids) {
        enterpriTeamIntellectualPropertyService.batchRemove(ids);
        return R.ok();
    }

}
