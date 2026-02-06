package com.bootdo.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONStreamAware;
import com.bootdo.common.controller.BaseSciencePersonalController;
import com.bootdo.system.domain.EnterpriTeamUsersListDO;
import com.bootdo.system.domain.EnterpriseChengguoBaseInfoDO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.system.domain.EnterpriPersonalInfoWorkHistoryDO;
import com.bootdo.system.service.EnterpriPersonalInfoWorkHistoryService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 个人工作经历
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-06-30 04:48:14
 */

@Controller
@RequestMapping("/system/enterpriPersonalInfoWorkHistory")
public class EnterpriPersonalInfoWorkHistoryController extends BaseSciencePersonalController {
    @Autowired
    private EnterpriPersonalInfoWorkHistoryService enterpriPersonalInfoWorkHistoryService;

    @GetMapping()
    @RequiresPermissions("system:enterpriPersonalInfoWorkHistory:enterpriPersonalInfoWorkHistory")
    String EnterpriPersonalInfoWorkHistory(@RequestParam Map<String, Object> params, ModelMap map){
        packageAwardTaskId(map, params);
        return "system/enterpriPersonalInfoWorkHistory/enterpriPersonalInfoWorkHistory";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("system:enterpriPersonalInfoWorkHistory:enterpriPersonalInfoWorkHistory")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<EnterpriPersonalInfoWorkHistoryDO> enterpriPersonalInfoWorkHistoryList = enterpriPersonalInfoWorkHistoryService.list(query);
        int total = enterpriPersonalInfoWorkHistoryService.count(query);
        PageUtils pageUtils = new PageUtils(enterpriPersonalInfoWorkHistoryList, total);
        return pageUtils;
    }



    @ResponseBody
    @GetMapping("/get")
    public R get(String id){
        Map<String,Object> params = new HashMap<>();
        params.put("id",id);
        //查询列表数据
        List<EnterpriPersonalInfoWorkHistoryDO> workHistory = enterpriPersonalInfoWorkHistoryService.list(params);
        R result = R.ok();
        result.put("workInfo",workHistory.size() > 0 ? workHistory.get(0) : new EnterpriseChengguoBaseInfoDO());
        return result;
    }



    @ResponseBody
    @GetMapping("/listAll")
    public R  listAll(@RequestParam Map<String, Object> params) {
        //查询列表数据
        List<EnterpriPersonalInfoWorkHistoryDO> enterpriPersonalInfoWorkHistoryList = enterpriPersonalInfoWorkHistoryService.list(params);
        Map back = new HashMap();

        back.put("workHis",enterpriPersonalInfoWorkHistoryList);

        return R.ok(back);
    }

    @GetMapping("/add")
    @RequiresPermissions("system:enterpriPersonalInfoWorkHistory:add")
    String add(@RequestParam Map<String, Object> params, ModelMap map){
        packageAwardTaskId(map, params);
        return "system/enterpriPersonalInfoWorkHistory/add";
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("system:enterpriPersonalInfoWorkHistory:edit")
    String edit(@PathVariable("id") Integer id, @RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        EnterpriPersonalInfoWorkHistoryDO enterpriPersonalInfoWorkHistory = enterpriPersonalInfoWorkHistoryService.get(id);
        map.addAttribute("enterpriPersonalInfoWorkHistory", enterpriPersonalInfoWorkHistory);
        return "system/enterpriPersonalInfoWorkHistory/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("system:enterpriPersonalInfoWorkHistory:add")
    public R save(EnterpriPersonalInfoWorkHistoryDO enterpriPersonalInfoWorkHistory) {
        Integer workHistoryId = enterpriPersonalInfoWorkHistory.getId();
        if(workHistoryId != null && workHistoryId > 0) {
            enterpriPersonalInfoWorkHistoryService.update(enterpriPersonalInfoWorkHistory);
            return R.ok();
        }else if (enterpriPersonalInfoWorkHistoryService.save(enterpriPersonalInfoWorkHistory) > 0) {
            return R.ok().put("workHistory",enterpriPersonalInfoWorkHistory);
        }
        return R.error();
    }


    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("system:enterpriPersonalInfoWorkHistory:edit")
    public R update(EnterpriPersonalInfoWorkHistoryDO enterpriPersonalInfoWorkHistory) {
        enterpriPersonalInfoWorkHistoryService.update(enterpriPersonalInfoWorkHistory);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("system:enterpriPersonalInfoWorkHistory:remove")
    public R remove(Integer id) {
        if (enterpriPersonalInfoWorkHistoryService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("system:enterpriPersonalInfoWorkHistory:batchRemove")
    public R remove(@RequestParam("ids[]") Integer[] ids) {
        enterpriPersonalInfoWorkHistoryService.batchRemove(ids);
        return R.ok();
    }

}
