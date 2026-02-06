package com.bootdo.common.controller;

import com.bootdo.activiti.domain.EnterpriseProjectInfoDo;
import com.bootdo.activiti.service.AwardEnterpriseProjectService;
import com.bootdo.activiti.service.AwardPublishTaskService;
import com.bootdo.common.utils.CommonUtils;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.cpe.domain.EnumProjectType;
import com.bootdo.system.domain.UserDO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bootdo.common.config.Constant.ROLE_ASSOCIATION_LEADER;

@Controller
public class BaseController {
    @Autowired
    private AwardPublishTaskService awardPublishTaskService;
    @Autowired
    private AwardEnterpriseProjectService awardEnterpriseProjectService;

    public UserDO getUser() {
        return ShiroUtils.getUser();
    }

    public Long getUserId() {
        return getUser().getUserId();
    }

    public String getUsername() {
        return getUser().getUsername();
    }

    public void packageEnterpriseProInfo(HttpServletRequest request, ModelMap map) {
        String proId = request.getParameter("proId");
        String taskId = request.getParameter("taskId");
        String applyId = request.getParameter("applyId");
        map.put("proId", proId);
        map.put("taskId", taskId);
        map.put("applyId", applyId);
    }

    public int pageNumPackage(Map<String, Object> params, ModelMap map) {
        Object pageNum = params.get("pageNum");
        if (pageNum == null) {
            pageNum = 1;
        }
        map.put("pageNum", pageNum);
        int limit = 20;
        int curPageNum = Integer.parseInt(pageNum.toString());
        int offset = (curPageNum - 1) * limit;
        params.put("offset", offset);
        params.put("limit", limit);
        return curPageNum;
    }

    public void applyParams(ModelMap map) {
        packageProParam(map, new HashMap<>());
        packageAwardTaskId(map, new HashMap<>());
        map.put("couldSubmitReview", true);
        createProId(map);
    }
    public int applyParams(ModelMap map,Map<String,Object> params) {
        packageProParam(map, params);
        if(params != null) {
            Object readonlyObj = params.get("readonly");
            map.put("readonly", readonlyObj == null ? 0 : readonlyObj);
            Object tagNameObj = params.get("tagName");
            if(tagNameObj != null) {
                map.put("tagName", tagNameObj);
            }
            Object proSubTypeObj = params.get("proSubType");
            if(proSubTypeObj != null) {
                map.put("proSubType", proSubTypeObj);
            }
        }
        packageAwardTaskId(map, params);
        map.put("couldSubmitReview", true);
        int proId = createProId(map);
        params.put("proId", proId);
        return proId;
    }
    public void packageApplyParam(ModelMap map, Map<String, Object> params) {
        Object proIdObj = params.get("proId");
        int proId = proIdObj != null && StringUtils.isNotBlank(proIdObj.toString()) ? Integer.parseInt(proIdObj.toString()) : 0;
        Object applyIdObj = params.get("applyIdObj");
        String applyId = applyIdObj == null ? CommonUtils.getApplyPersonalId(getUserId()) : applyIdObj.toString();
        map.put("applyId", applyId);
        map.put("proId", proId);
        Object readonlyObj = params.get("readonly");
        map.put("readonly", readonlyObj == null ? 0 : readonlyObj);
        Object proType = params.get("proType");
        if(proType != null) {
            map.put("proType", proType);
        }

        UserDO user = getUser();
        List<Long> roleIdList = user.getRoleIds();
        map.put("isViewProCode", CommonUtils.isViewProCode(roleIdList));

    }
    public void packageAwardTaskId(ModelMap map, Map<String, Object> params){
        //各个奖项复写此方法
    }

    /**
     * 创建项目id，用于区分提交的是否为同一个
     * @param map
     */
    public int createProId(ModelMap map){
        EnterpriseProjectInfoDo projectInfoDo = null;
        Object proIdObj = map.get("proId");
        int proId = proIdObj == null ? 0 : Integer.parseInt(proIdObj.toString());
        if (proId == 0) {
            Long uid = getUserId();
            String taskId = (String) map.get("taskId");
            Object proTypeObj = map.get("proType");
            EnumProjectType proType = proTypeObj == null ? EnumProjectType.BLANK : (EnumProjectType) proTypeObj;
            Object proSubTypeObj = map.get("proSubType");
            String proSubType = proSubTypeObj == null ? null : proSubTypeObj.toString();
            projectInfoDo = awardEnterpriseProjectService.initOneProjectByProSubType(taskId, uid, proType, proSubType);
            proId = projectInfoDo.getId();
        } else {
            projectInfoDo = awardEnterpriseProjectService.get(proId + "");
        }
        map.put("projectInfoDo", projectInfoDo);
        map.put("proId", proId);
        return proId;
    }

    /**
     * 封装奖项特有的参数 如项目类型
     * @param map
     */
    public void packageProParam(ModelMap map ,Map<String,Object> params) {

    }
}
