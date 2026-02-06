package com.bootdo.system.controller;

import com.bootdo.activiti.domain.PublishAwardTaskDo;
import com.bootdo.activiti.service.AwardFlowService;
import com.bootdo.common.controller.BaseController;
import com.bootdo.cpe.domain.EnumAwardType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/publish_task")
public class PublishTaskController extends BaseController {
    @Autowired
    private AwardFlowService awardFlowService;

    @RequestMapping("/lastest_list")
    @ResponseBody
    public List<PublishAwardTaskDo> getLastestTaskList(Integer count, Integer awardType){
        if(count == null) {
            count = 25;
        }
        int at = awardType == null ? EnumAwardType.SCIENCE.getAwrdType() : awardType;
        return awardFlowService.getAwardLastTasksByAwardType(count, at);
    }
}
