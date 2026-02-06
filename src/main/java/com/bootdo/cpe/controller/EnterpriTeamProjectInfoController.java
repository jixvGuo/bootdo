package com.bootdo.cpe.controller;

import com.bootdo.common.controller.BaseController;
import com.bootdo.common.controller.BaseScienceTeamController;
import com.bootdo.common.domain.DictDO;
import com.bootdo.common.service.DictService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.cpe.domain.EnterpriTeamProjectInfoDO;
import com.bootdo.cpe.service.EnterpriTeamProjectInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

import static com.bootdo.system.config.DictTypeConstant.SCIENCE_TEAM_PROJECT_STAT;

/**
 * 团队承担项目情况
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-09-19 15:42:06
 */

@Controller
@RequestMapping("/cpe/enterpriTeamProjectInfo")
public class EnterpriTeamProjectInfoController extends BaseScienceTeamController {
    private String prefix = "enterprise/apply/team";

    @Autowired
    private EnterpriTeamProjectInfoService enterpriTeamProjectInfoService;
    @Autowired
    private DictService dictService;

    @GetMapping()
    @RequiresPermissions("cpe:enterpriTeamProjectInfo:enterpriTeamProjectInfo")
    String EnterpriTeamProjectInfo(@RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        return "cpe/enterpriTeamProjectInfo/enterpriTeamProjectInfo";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("cpe:enterpriTeamProjectInfo:enterpriTeamProjectInfo")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<EnterpriTeamProjectInfoDO> enterpriTeamProjectInfoList = enterpriTeamProjectInfoService.list(query);
        int total = enterpriTeamProjectInfoService.count(query);
        PageUtils pageUtils = new PageUtils(enterpriTeamProjectInfoList, total);
        return pageUtils;
    }

    @GetMapping("/toList")
//    @RequiresPermissions("cpe:enterpriTeamProjectInfo:enterpriTeamProjectInfo")
    public String toList(@RequestParam Map<String, Object> params, ModelMap map, HttpServletRequest request) {
        packageAwardTaskId(map, params);
        //查询列表数据
        List<EnterpriTeamProjectInfoDO> enterpriTeamProjectInfoList = enterpriTeamProjectInfoService.list(params);
        map.put("list", enterpriTeamProjectInfoList);
        return prefix + "/project_info_list_07";
    }

    @GetMapping("/add")
    @RequiresPermissions("cpe:enterpriTeamProjectInfo:add")
    String add(@RequestParam Map<String, Object> params, ModelMap map, HttpServletRequest request) {
        packageAwardTaskId(map, params);
        List<DictDO> dictDOList = dictService.listByType(SCIENCE_TEAM_PROJECT_STAT);
        map.put("statList", dictDOList);
        map.put("enterpriTeamProjectInfo", new EnterpriTeamProjectInfoDO());
        return prefix + "/project_info_add_07";
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("cpe:enterpriTeamProjectInfo:edit")
    String edit(@PathVariable("id") Integer id, @RequestParam Map<String, Object> params, ModelMap map, HttpServletRequest request) {
        packageAwardTaskId(map, params);
        EnterpriTeamProjectInfoDO enterpriTeamProjectInfo = enterpriTeamProjectInfoService.get(id);
        List<DictDO> dictDOList = dictService.listByType(SCIENCE_TEAM_PROJECT_STAT);
        map.put("statList", dictDOList);
        map.addAttribute("enterpriTeamProjectInfo", enterpriTeamProjectInfo);
        return prefix + "/project_info_add_07";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("cpe:enterpriTeamProjectInfo:add")
    public R save(EnterpriTeamProjectInfoDO enterpriTeamProjectInfo) {
        Integer id = enterpriTeamProjectInfo.getId();
        enterpriTeamProjectInfo.setOptUid(getUserId());
        if (id != null && id > 0) {
            enterpriTeamProjectInfoService.update(enterpriTeamProjectInfo);
            return R.ok();
        }
        if (enterpriTeamProjectInfoService.save(enterpriTeamProjectInfo) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("cpe:enterpriTeamProjectInfo:edit")
    public R update(EnterpriTeamProjectInfoDO enterpriTeamProjectInfo) {
        enterpriTeamProjectInfoService.update(enterpriTeamProjectInfo);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("cpe:enterpriTeamProjectInfo:remove")
    public R remove(Integer id) {
        if (enterpriTeamProjectInfoService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("cpe:enterpriTeamProjectInfo:batchRemove")
    public R remove(@RequestParam("ids[]") Integer[] ids) {
        enterpriTeamProjectInfoService.batchRemove(ids);
        return R.ok();
    }

}
