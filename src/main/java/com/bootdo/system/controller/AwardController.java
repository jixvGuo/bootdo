package com.bootdo.system.controller;

import com.bootdo.common.annotation.Log;
import com.bootdo.common.config.Constant;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.domain.DictDO;
import com.bootdo.common.domain.Tree;
import com.bootdo.common.service.DictService;
import com.bootdo.common.utils.R;
import com.bootdo.system.domain.AwardDo;
import com.bootdo.system.service.AwardService;
import com.google.gson.Gson;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/sys/award")
@Controller
public class AwardController extends BaseController {
	String prefix = "system/award";
	@Autowired
	AwardService awardService;
	@Autowired
	DictService dictService;

	@RequiresPermissions("sys:award:award")
	@GetMapping()
	String award() {
		return prefix + "/award";
	}

	@RequiresPermissions("sys:award:award")
	@GetMapping("/list")
	@ResponseBody()
	List<AwardDo> list() {
		List<AwardDo> roles = awardService.list();
		return roles;
	}

	@GetMapping("/tree")
	@ResponseBody
	public String tree() {
		Tree<AwardDo> tree = awardService.getTree();
		Gson gson = new Gson();
		return gson.toJson(tree);
	}

	@Log("添加奖项")
	@RequiresPermissions("sys:award:add")
	@GetMapping("/add")
	String add(ModelMap map) {
        List<DictDO> dictDOS = dictService.listByType("major");
        map.put("majorList",dictDOS);
        return prefix + "/add";
	}

	@Log("编辑奖项")
	@RequiresPermissions("sys:award:edit")
	@GetMapping("/edit/{id}")
	String edit(@PathVariable("id") Long id, Model model) {
		List<DictDO> dictDOS = dictService.listByType("major");
		List<Long> majorIds = awardService.getMajorIdsByAwardId(id);
		majorIds.stream().forEach(mid->{
			dictDOS.stream().forEach(d->{
				if(d.getId().intValue() == mid.longValue()) {
					d.setFlg("true");
				}
			});
		});
		model.addAttribute("majorList",dictDOS);
		AwardDo awardDo = awardService.get(id);
		model.addAttribute("award", awardDo);
		return prefix + "/edit";
	}

	@Log("保存奖项")
	@RequiresPermissions("sys:award:add")
	@PostMapping("/save")
	@ResponseBody()
	R save(AwardDo award) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (awardService.save(award) > 0) {
			return R.ok();
		} else {
			return R.error(1, "保存失败");
		}
	}

	@Log("更新奖项")
	@RequiresPermissions("sys:award:edit")
	@PostMapping("/update")
	@ResponseBody()
	R update(AwardDo award) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (awardService.update(award) > 0) {
			return R.ok();
		} else {
			return R.error(1, "保存失败");
		}
	}

	@Log("删除奖项")
	@RequiresPermissions("sys:award:remove")
	@PostMapping("/remove")
	@ResponseBody()
	R save(Long id) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		if (awardService.remove(id) > 0) {
			return R.ok();
		} else {
			return R.error(1, "删除失败");
		}
	}
	
	@RequiresPermissions("sys:award:batchRemove")
	@Log("批量删除奖项")
	@PostMapping("/batchRemove")
	@ResponseBody
	R batchRemove(@RequestParam("ids[]") Long[] ids) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		int r = awardService.batchremove(ids);
		if (r > 0) {
			return R.ok();
		}
		return R.error();
	}
}
