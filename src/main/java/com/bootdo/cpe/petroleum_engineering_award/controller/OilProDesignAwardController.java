package com.bootdo.cpe.petroleum_engineering_award.controller;

import java.util.List;
import java.util.Map;

import com.bootdo.common.controller.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.cpe.petroleum_engineering_award.domain.OilProDesignAwardDO;
import com.bootdo.cpe.petroleum_engineering_award.service.OilProDesignAwardService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2021-02-20 22:55:27
 */

@Controller
@RequestMapping("/petroleum_engineering_award/oilProDesignAward")
public class OilProDesignAwardController extends BaseController {
    @Autowired
    private OilProDesignAwardService oilProDesignAwardService;

    @GetMapping()
    @RequiresPermissions("petroleum_engineering_award:oilProDesignAward:oilProDesignAward")
    String OilProDesignAward() {
        return "petroleum_engineering_award/oilProDesignAward/oilProDesignAward";
    }

    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("petroleum_engineering_award:oilProDesignAward:oilProDesignAward")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);
        List<OilProDesignAwardDO> oilProDesignAwardList = oilProDesignAwardService.list(query);
        int total = oilProDesignAwardService.count(query);
        PageUtils pageUtils = new PageUtils(oilProDesignAwardList, total);
        return pageUtils;
    }

    @GetMapping("/add")
    @RequiresPermissions("petroleum_engineering_award:oilProDesignAward:add")
    String add() {
        return "petroleum_engineering_award/oilProDesignAward/add";
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("petroleum_engineering_award:oilProDesignAward:edit")
    String edit(@PathVariable("id") Integer id, Model model) {
        OilProDesignAwardDO oilProDesignAward = oilProDesignAwardService.get(id);
        model.addAttribute("oilProDesignAward", oilProDesignAward);
        return "petroleum_engineering_award/oilProDesignAward/edit";
    }


    @RequestMapping("/get")
    @ResponseBody
    R get(Integer id, Model model) {
        OilProDesignAwardDO oilProDesignAward = oilProDesignAwardService.get(id);
        R result = R.ok();
        result.put("data", oilProDesignAward);
        return result;
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
//	@RequiresPermissions("petroleum_engineering_award:oilProDesignAward:add")
    public R save(OilProDesignAwardDO oilProDesignAward) {
        Long optUid = getUserId();
        oilProDesignAward.setOptUid(optUid == null ? "0" : optUid.toString());
        Integer id = oilProDesignAward.getId();
        if(id != null && id > 0) {
            R result = update(oilProDesignAward);
            result.put("id", id);
            return result;
        }
        if (oilProDesignAwardService.save(oilProDesignAward) > 0) {
            R result = R.ok();
            return result;
        }
        return R.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
//	@RequiresPermissions("petroleum_engineering_award:oilProDesignAward:edit")
    public R update(OilProDesignAwardDO oilProDesignAward) {
        oilProDesignAwardService.update(oilProDesignAward);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
//	@RequiresPermissions("petroleum_engineering_award:oilProDesignAward:remove")
    public R remove(Integer id) {
        if (oilProDesignAwardService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("petroleum_engineering_award:oilProDesignAward:batchRemove")
    public R remove(@RequestParam("ids[]") Integer[] ids) {
        oilProDesignAwardService.batchRemove(ids);
        return R.ok();
    }

}
