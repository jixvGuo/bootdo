package com.bootdo.cpe.controller;

import com.bootdo.common.controller.BaseController;
import com.bootdo.common.controller.BaseScienceTeamController;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.cpe.domain.EnterpriTeamPaperInfoDO;
import com.bootdo.cpe.service.EnterpriTeamPaperInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 发表论文专著情况
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-09-18 08:05:28
 */

@Controller
@RequestMapping("/cpe/enterpriTeamPaperInfo")
public class EnterpriTeamPaperInfoController extends BaseScienceTeamController {
    private String prefix = "enterprise/apply/team";

    @Autowired
    private EnterpriTeamPaperInfoService enterpriTeamPaperInfoService;

    @GetMapping()
    @RequiresPermissions("cpe:enterpriTeamPaperInfo:enterpriTeamPaperInfo")
    String EnterpriTeamPaperInfo(@RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        return "cpe/enterpriTeamPaperInfo/enterpriTeamPaperInfo";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("cpe:enterpriTeamPaperInfo:enterpriTeamPaperInfo")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<EnterpriTeamPaperInfoDO> enterpriTeamPaperInfoList = enterpriTeamPaperInfoService.list(query);
        int total = enterpriTeamPaperInfoService.count(query);
        PageUtils pageUtils = new PageUtils(enterpriTeamPaperInfoList, total);
        return pageUtils;
    }

    @GetMapping("/toList")
//    @RequiresPermissions("cpe:enterpriTeamPaperInfo:enterpriTeamPaperInfo")
    public String toList(@RequestParam Map<String, Object> params, ModelMap map, HttpServletRequest request) {
        packageAwardTaskId(map, params);
        //查询列表数据
        List<EnterpriTeamPaperInfoDO> enterpriTeamPaperInfoList = enterpriTeamPaperInfoService.list(params);
        map.put("acheivementsList", enterpriTeamPaperInfoList);
        return prefix + "/paper_info_list_04";
    }



    @GetMapping("/add")
    @RequiresPermissions("cpe:enterpriTeamPaperInfo:add")
    String add(@RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        map.put("enterpriTeamPaperInfo", new EnterpriTeamPaperInfoDO());
        return prefix + "/paper_info_add_04";
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("cpe:enterpriTeamPaperInfo:edit")
    String edit(@PathVariable("id") Integer id, @RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        EnterpriTeamPaperInfoDO enterpriTeamPaperInfo = enterpriTeamPaperInfoService.get(id);
        map.put("enterpriTeamPaperInfo", enterpriTeamPaperInfo);
        return prefix + "/paper_info_add_04";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("cpe:enterpriTeamPaperInfo:add")
    public R save(EnterpriTeamPaperInfoDO enterpriTeamPaperInfo) {
        Integer id = enterpriTeamPaperInfo.getId();
        if (id != null && id > 0) {
            enterpriTeamPaperInfoService.update(enterpriTeamPaperInfo);
            return R.ok();
        }
        if (enterpriTeamPaperInfoService.save(enterpriTeamPaperInfo) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("cpe:enterpriTeamPaperInfo:edit")
    public R update(EnterpriTeamPaperInfoDO enterpriTeamPaperInfo) {
        enterpriTeamPaperInfoService.update(enterpriTeamPaperInfo);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("cpe:enterpriTeamPaperInfo:remove")
    public R remove(Integer id) {
        if (enterpriTeamPaperInfoService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("cpe:enterpriTeamPaperInfo:batchRemove")
    public R remove(@RequestParam("ids[]") Integer[] ids) {
        enterpriTeamPaperInfoService.batchRemove(ids);
        return R.ok();
    }

}
