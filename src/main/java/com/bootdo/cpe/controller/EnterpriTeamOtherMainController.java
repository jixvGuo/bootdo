package com.bootdo.cpe.controller;

import com.bootdo.common.controller.BaseController;
import com.bootdo.common.controller.BaseScienceTeamController;
import com.bootdo.common.domain.DictDO;
import com.bootdo.common.service.DictService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.cpe.domain.EnterpriTeamOtherMainDO;
import com.bootdo.cpe.service.EnterpriTeamOtherMainService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

import static com.bootdo.system.config.DictTypeConstant.*;

/**
 * 主要成员情况-其他主要成员
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-09-19 23:28:34
 */

@Controller
@RequestMapping("/cpe/enterpriTeamOtherMain")
public class EnterpriTeamOtherMainController extends BaseScienceTeamController {
    private String prefix = "enterprise/apply/team";

    @Autowired
    private EnterpriTeamOtherMainService enterpriTeamOtherMainService;
    @Autowired
    private DictService dictService;

    @GetMapping()
    @RequiresPermissions("cpe:enterpriTeamOtherMain:enterpriTeamOtherMain")
    String EnterpriTeamOtherMain(@RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        return "cpe/enterpriTeamOtherMain/enterpriTeamOtherMain";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("cpe:enterpriTeamOtherMain:enterpriTeamOtherMain")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<EnterpriTeamOtherMainDO> enterpriTeamOtherMainList = enterpriTeamOtherMainService.list(query);
        int total = enterpriTeamOtherMainService.count(query);
        PageUtils pageUtils = new PageUtils(enterpriTeamOtherMainList, total);
        return pageUtils;
    }

    @GetMapping("/toList")
//    @RequiresPermissions("cpe:enterpriTeamOtherMain:enterpriTeamLeaderInfo")
    public String toList(@RequestParam Map<String, Object> params, ModelMap map, HttpServletRequest request) {
        packageAwardTaskId(map, params);
        //查询列表数据
        List<EnterpriTeamOtherMainDO> enterpriTeamLeaderInfoList = enterpriTeamOtherMainService.list(params);
        map.put("list", enterpriTeamLeaderInfoList);

        return prefix + "/other_main_list_12";
    }

    @GetMapping("/add")
    @RequiresPermissions("cpe:enterpriTeamOtherMain:add")
    String add(@RequestParam Map<String, Object> params, ModelMap map, HttpServletRequest request) {
        packageAwardTaskId(map, params);
        map.put("enterpriTeamOtherMain", new EnterpriTeamOtherMainDO());
        List<DictDO> genderList = dictService.listByType(GENDER_TYPE);
        map.put("genderList", genderList);
        List<DictDO> selYesNoList = dictService.listByType(SELECT_YES_NO);
        map.put("backList", selYesNoList);
        List<DictDO> eduList = dictService.listByType(EDUCATION_TYPE);
        map.put("eduList", eduList);
        List<DictDO> acaList = dictService.listByType(ACADEMICDEGREE_TYPE);
        map.put("acaList", acaList);

        return prefix + "/other_main_add_12";
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("cpe:enterpriTeamOtherMain:edit")
    String edit(@PathVariable("id") Integer id, @RequestParam Map<String, Object> params, ModelMap map, HttpServletRequest request) {
        packageAwardTaskId(map, params);
        EnterpriTeamOtherMainDO enterpriTeamOtherMain = enterpriTeamOtherMainService.get(id);
        map.addAttribute("enterpriTeamOtherMain", enterpriTeamOtherMain);

        List<DictDO> genderList = dictService.listByType(GENDER_TYPE);
        map.put("genderList", genderList);
        List<DictDO> selYesNoList = dictService.listByType(SELECT_YES_NO);
        map.put("backList", selYesNoList);
        List<DictDO> eduList = dictService.listByType(EDUCATION_TYPE);
        map.put("eduList", eduList);
        List<DictDO> acaList = dictService.listByType(ACADEMICDEGREE_TYPE);
        map.put("acaList", acaList);

        return prefix + "/other_main_add_12";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("cpe:enterpriTeamOtherMain:add")
    public R save(EnterpriTeamOtherMainDO enterpriTeamOtherMain) {
        Integer id = enterpriTeamOtherMain.getId();
        enterpriTeamOtherMain.setOptUid(getUserId());
        if (id != null && id > 0) {
            enterpriTeamOtherMainService.update(enterpriTeamOtherMain);
            return R.ok();
        }
        if (enterpriTeamOtherMainService.save(enterpriTeamOtherMain) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("cpe:enterpriTeamOtherMain:edit")
    public R update(EnterpriTeamOtherMainDO enterpriTeamOtherMain) {
        enterpriTeamOtherMainService.update(enterpriTeamOtherMain);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("cpe:enterpriTeamOtherMain:remove")
    public R remove(Integer id) {
        if (enterpriTeamOtherMainService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("cpe:enterpriTeamOtherMain:batchRemove")
    public R remove(@RequestParam("ids[]") Integer[] ids) {
        enterpriTeamOtherMainService.batchRemove(ids);
        return R.ok();
    }

}
