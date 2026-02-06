package com.bootdo.cpe.controller;

import com.bootdo.common.controller.BaseController;
import com.bootdo.common.controller.BaseScienceTeamController;
import com.bootdo.common.domain.DictDO;
import com.bootdo.common.service.DictService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.cpe.domain.EnterpriTeamLeaderInfoDO;
import com.bootdo.cpe.service.EnterpriTeamLeaderInfoService;
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
 * 主要成员情况带头人
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-09-19 21:13:08
 */

@Controller
@RequestMapping("/cpe/enterpriTeamLeaderInfo")
public class EnterpriTeamLeaderInfoController extends BaseScienceTeamController {
    private String prefix = "enterprise/apply/team";

    @Autowired
    private EnterpriTeamLeaderInfoService enterpriTeamLeaderInfoService;
    @Autowired
    private DictService dictService;

    @GetMapping()
    @RequiresPermissions("cpe:enterpriTeamLeaderInfo:enterpriTeamLeaderInfo")
    String EnterpriTeamLeaderInfo(@RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        return "cpe/enterpriTeamLeaderInfo/enterpriTeamLeaderInfo";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("cpe:enterpriTeamLeaderInfo:enterpriTeamLeaderInfo")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<EnterpriTeamLeaderInfoDO> enterpriTeamLeaderInfoList = enterpriTeamLeaderInfoService.list(query);
        int total = enterpriTeamLeaderInfoService.count(query);
        PageUtils pageUtils = new PageUtils(enterpriTeamLeaderInfoList, total);
        return pageUtils;
    }

    @GetMapping("/toList")
//    @RequiresPermissions("cpe:enterpriTeamLeaderInfo:enterpriTeamLeaderInfo")
    public String toList(@RequestParam Map<String, Object> params, ModelMap map, HttpServletRequest request) {
        packageAwardTaskId(map, params);
        //查询列表数据
        List<EnterpriTeamLeaderInfoDO> enterpriTeamLeaderInfoList = enterpriTeamLeaderInfoService.list(params);
        map.put("list", enterpriTeamLeaderInfoList);

        return prefix + "/leader_info_list_11";
    }

    @GetMapping("/add")
    @RequiresPermissions("cpe:enterpriTeamLeaderInfo:add")
    String add(@RequestParam Map<String, Object> params, ModelMap map, HttpServletRequest request) {
        packageAwardTaskId(map, params);
        map.put("enterpriTeamLeaderInfo", new EnterpriTeamLeaderInfoDO());
        List<DictDO> genderList = dictService.listByType(GENDER_TYPE);
        map.put("genderList", genderList);
        List<DictDO> selYesNoList = dictService.listByType(SELECT_YES_NO);
        map.put("backList", selYesNoList);
        List<DictDO> eduList = dictService.listByType(EDUCATION_TYPE);
        map.put("eduList", eduList);
        List<DictDO> acaList = dictService.listByType(ACADEMICDEGREE_TYPE);
        map.put("acaList", acaList);

        return prefix + "/leader_info_add_11";
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("cpe:enterpriTeamLeaderInfo:edit")
    String edit(@PathVariable("id") Integer id, @RequestParam Map<String, Object> params, ModelMap map, HttpServletRequest request) {
        packageAwardTaskId(map, params);
        EnterpriTeamLeaderInfoDO enterpriTeamLeaderInfo = enterpriTeamLeaderInfoService.get(id);
        map.addAttribute("enterpriTeamLeaderInfo", enterpriTeamLeaderInfo);

        List<DictDO> genderList = dictService.listByType(GENDER_TYPE);
        map.put("genderList", genderList);
        List<DictDO> selYesNoList = dictService.listByType(SELECT_YES_NO);
        map.put("backList", selYesNoList);
        List<DictDO> eduList = dictService.listByType(EDUCATION_TYPE);
        map.put("eduList", eduList);
        List<DictDO> acaList = dictService.listByType(ACADEMICDEGREE_TYPE);
        map.put("acaList", acaList);

        return prefix + "/leader_info_add_11";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("cpe:enterpriTeamLeaderInfo:add")
    public R save(EnterpriTeamLeaderInfoDO enterpriTeamLeaderInfo) {
        Integer id = enterpriTeamLeaderInfo.getId();
        enterpriTeamLeaderInfo.setOptUid(getUserId());
        if (id != null && id > 0) {
            enterpriTeamLeaderInfoService.update(enterpriTeamLeaderInfo);
            return R.ok();
        }
        if (enterpriTeamLeaderInfoService.save(enterpriTeamLeaderInfo) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("cpe:enterpriTeamLeaderInfo:edit")
    public R update(EnterpriTeamLeaderInfoDO enterpriTeamLeaderInfo) {
        enterpriTeamLeaderInfoService.update(enterpriTeamLeaderInfo);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("cpe:enterpriTeamLeaderInfo:remove")
    public R remove(Integer id) {
        if (enterpriTeamLeaderInfoService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("cpe:enterpriTeamLeaderInfo:batchRemove")
    public R remove(@RequestParam("ids[]") Integer[] ids) {
        enterpriTeamLeaderInfoService.batchRemove(ids);
        return R.ok();
    }

}
