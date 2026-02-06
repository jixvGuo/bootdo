package com.bootdo.cpe.controller;

import com.bootdo.common.utils.R;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.cpe.service.ProjectCommonService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/proTask")
public class ProjectTaskAdminController {
    @Autowired
    private ProjectCommonService projectCommonService;
    /**
     * 删除
     */
    @PostMapping( "/remove")
    @ResponseBody
    @RequiresPermissions("taskamin:project:del")
    public R remove(String taskId){
        if(StringUtils.isNotBlank(taskId)) {
            projectCommonService.removeTask(taskId);
        }
        return R.ok();
    }
}
