package com.bootdo.common.controller;

import com.bootdo.activiti.domain.PublishAwardTaskDo;
import com.bootdo.activiti.service.AwardPublishTaskService;
import com.bootdo.cpe.domain.EnumAwardType;
import com.bootdo.cpe.petroleum_engineering_award.domain.OilProBaseInfoDO;
import com.bootdo.cpe.petroleum_engineering_award.domain.OilProQualityInfoDO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import java.util.Map;

/**
 * 石油安装工程的基础父类
 *
 * @author houzb
 * @version 1.0
 * @date 2021-03-23 11:53
 */
@Controller
public class BaseOilProController extends BaseController {
    public String errPage = "error/error_msg";

    @Autowired
    private AwardPublishTaskService awardPublishTaskService;

    public boolean isTaskIsApply(ModelMap map, Map<String, Object> params, boolean isList) {
        boolean isApply = true;
        PublishAwardTaskDo awardTaskDo = awardPublishTaskService.getLastProTaskByAwardType(EnumAwardType.QUALITY.getAwrdType() + "");
        if (awardTaskDo == null) {
            isApply = false;
            map.put("msg", "请耐心等待协会工作人员创建申报任务");
        } else if (awardTaskDo.isApplyEnd()) {
            isApply = false;
            map.put("msg", "任务申报时间已结束,需要使用请联系相关协会工作人员");
        }
        Object readonlyObj = params.get("readonly");
        if(readonlyObj != null && "1".equals(readonlyObj.toString())) {
            isApply = true;
        }
        //列表的申请按钮是否显示判断
        if(isList && awardTaskDo != null) {
            awardTaskDo.initStat();
            isApply = awardTaskDo.isApply();
        }
        map.put("isApply", isApply);
        return isApply;
    }

    @Override
    public void packageAwardTaskId(ModelMap map, Map<String, Object> params) {
        Object paramsTaskIdObj = params.get("taskId");
        Object proIdObj = params.get("proId");
        int proId = proIdObj != null ? Integer.parseInt(proIdObj.toString()) : 0;
        if(paramsTaskIdObj == null && proId > 0) {
            //根据项目id获取taskId
            paramsTaskIdObj = awardPublishTaskService.getTaskIdByProId(proId);
        }
        //TODO 需要改成获取最新的 石油优质工程奖任务的 查询
        String taskId = "";
        if(paramsTaskIdObj == null) {
            PublishAwardTaskDo taskDo = awardPublishTaskService.getLastProTaskByAwardType(EnumAwardType.QUALITY.getAwrdType() + "");
            taskId = taskDo != null ? taskDo.getId() : "";
        }else {
            taskId = paramsTaskIdObj.toString();
        }
        if (StringUtils.isBlank(taskId)) {
            //TODO 抛出异常 用于提醒用户还未创建任务
            return;
        }
        params.put("taskId", taskId);
        map.put("taskId", taskId);

        packageApplyParam(map, params);
    }
}
