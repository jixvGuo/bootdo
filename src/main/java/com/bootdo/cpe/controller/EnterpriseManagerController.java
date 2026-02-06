package com.bootdo.cpe.controller;

import com.bootdo.common.controller.BaseController;
import com.bootdo.oa.controller.NotifyController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 企业信息管理
 */
@Controller
@RequestMapping("/enterpriseManager")
public class EnterpriseManagerController extends BaseController {
    @Autowired
    private NotifyController notifyController;

    private String prefix = "enterprise/manager";

    @RequestMapping("/toDeptList")
    public String toManagerList(Long id, Long notifyId, ModelMap model) {
        model.addAttribute("deptId", id);
        if(notifyId != null && notifyId > 0) {
            notifyController.read(notifyId, model);
        }
        return prefix + "/dept/dept";
    }

    /**
     * 去账号管理页面
     * @return
     */
    @RequestMapping("/toAccountList")
    public String toAccountList(int deptId, ModelMap map){
        map.put("deptId", deptId);
        return prefix + "/user/user";
    }
}
