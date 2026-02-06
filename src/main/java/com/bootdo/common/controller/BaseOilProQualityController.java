package com.bootdo.common.controller;

import com.bootdo.activiti.domain.PublishAwardTaskDo;
import com.bootdo.activiti.service.AwardEnterpriseProjectService;
import com.bootdo.activiti.service.AwardPublishTaskService;
import com.bootdo.cpe.domain.EnumAwardType;
import com.bootdo.cpe.domain.EnumProjectType;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import java.util.Map;

/**
 * @author houzb
 * @Description
 * @create 2021-03-05 7:04
 */
@Controller
public class BaseOilProQualityController extends BaseOilProController{
    @Autowired
    private AwardPublishTaskService awardPublishTaskService;
    @Autowired
    private AwardEnterpriseProjectService awardEnterpriseProjectService;
    /**
     * 封装奖项特有的参数 如项目类型
     *
     * @param map
     */
    @Override
    public void packageProParam(ModelMap map, Map<String,Object> params) {
        map.put("proType", EnumProjectType.OIL_PRO_QUALITY);
        if(params != null) {
            params.put("proType", EnumProjectType.OIL_PRO_QUALITY);
        }
    }


}
