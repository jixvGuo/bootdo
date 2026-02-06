package com.bootdo.common.controller;

import com.bootdo.activiti.domain.PublishAwardTaskDo;
import com.bootdo.activiti.service.AwardFlowService;
import com.bootdo.activiti.service.AwardPublishTaskService;
import com.bootdo.common.config.Constant;
import com.bootdo.cpe.domain.EnumAwardType;
import com.bootdo.cpe.domain.EnumProjectType;
import com.bootdo.system.domain.UserDO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Map;

import static com.bootdo.common.config.Constant.*;

/**
 * qc奖的基础父类
 *
 * @author houzb
 * @version 1.0
 * @date 2022-02-05 11:25
 */
@Controller
public class BaseQcProController extends BaseController {
    public String errPage = "error/error_msg";

    @Autowired
    private AwardPublishTaskService awardPublishTaskService;
    @Autowired
    private AwardFlowService awardFlowService;

    public boolean isTaskIsApply(ModelMap map, Map<String, Object> params) {
        boolean isApply = true;
        Object taskIdObj = params.get("taskId");
        if(taskIdObj == null) {
            return false;
        }
        PublishAwardTaskDo awardTaskDo = awardPublishTaskService.getProTaskByTaskId(taskIdObj.toString());
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
            PublishAwardTaskDo taskDo = awardPublishTaskService.getLastProTaskByAwardType(EnumAwardType.QC.getAwrdType() + "");
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
        map.put("apply_type",  "qc");
        //QC奖项目类型,默认只有小组奖项
        params.put("proType", EnumProjectType.QC_PRO_GROUP);
        packageApplyParam(map, params);
    }

     /**
     * 根据角色获取想要获取项目列表的限制参数
     *
     * @param params
     * @return
     */
    public Map<String, Object> getProListParamsByRole(Map<String, Object> params) {
        UserDO userDO = getUser();
        long uid = getUserId();
        List<Long> roleIds = userDO.getRoleIds();
        //统计分数查看的排序规则
        Object selMenu = params.get("selMenu");
        if (selMenu != null && com.bootdo.common.utils.StringUtils.isNotBlank(selMenu.toString())) {
            String menu = selMenu.toString();
            if (menu.equals("score_static_personal")) {
                params.put("sort", "personal_avg_score");
                params.put("order", "desc");
            } else if (menu.equals("score_static_team")) {
                params.put("sort", "team_avg_score");
                params.put("order", "desc");
            } else if (menu.equals("score_static_science")) {
                params.put("sort", "science_avg_score");
                params.put("order", "desc");
            }
        }
        //通过申报任务管理(菜单)进来的可以查看所有的，通过企业形式审查（菜单）进来的只能查看分配的项目
        Object pageSourceObj = params.get("pageSource");
        boolean isValidateFlg = pageSourceObj != null && pageSourceObj.toString().equals(Constant.PAGE_SOURCE_ENTERPRISE_VALIDATE_MENU);
        if (isValidateFlg) {
            //通过企业审查菜单进入的只能查看分派的项目信息
            params.put("ass_assign_uid", uid);
        }
        for (long rid : roleIds) {
            if (rid == ROLE_ADMIN_ID) {
                //超级管理员查看全部的项目
                params.remove("ass_assign_uid");
                break;
            } else if (rid == ROLE_QC_ASSOCIATION_ID) {
                params.put("role", "association_worker");
                //协会工作人员
                //判断是否为发布者
                Object publishTaskIdObj = params.get("publishTaskId");
                if (publishTaskIdObj == null || com.bootdo.common.utils.StringUtils.isBlank(publishTaskIdObj.toString())) {
                    //查看发布的任务列表
                    params.remove("ass_assign_uid");
                    params.put("ass_worker_uid", uid);
                    break;
                }
                String publishTaskId = publishTaskIdObj.toString();
                PublishAwardTaskDo taskDo = awardFlowService.getAwardTaskById(publishTaskId);
                if (taskDo != null && taskDo.getAssociationUserId().equals(uid + "")) {
                    //发布者查看全部的
                    params.remove("ass_assign_uid");
                } else {
                    //只能查看分派的项目
                    params.put("ass_assign_uid", uid);
                }
                break;
            } else if (rid == ROLE_ENTERPRISE_QC_ID) {
                //企业用户
                params.put("createUid", uid);
                break;
            } else if (rid == ROLE_SPECIALIST_ID) {
                //专家只能查看分派的项目
                params.put("ass_assign_uid", uid);
                if (params.get("role").toString().equals("specialist_leader")) {
                    //项目分派的用户id，-100表示系统分派的专家领导审核分数的用户id
                    params.put("pro_assign_uid", -100);
                }
                params.put("role", "specialist");
                break;
            }
        }
        return params;
    }

}
