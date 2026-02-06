package com.bootdo.common.controller;

import com.bootdo.cpe.domain.EnumProjectType;
import org.springframework.ui.ModelMap;

import java.util.Map;

/**
 * @author houzb
 * @Description
 * @create 2021-03-05 7:04
 */
public class BaseScienceTechnologyController extends BaseScienceProController{
    /**
     * 封装奖项特有的参数 如项目类型
     *
     * @param map
     */
    @Override
    public void packageProParam(ModelMap map, Map<String,Object> params) {
        map.put("proType", EnumProjectType.SCIENCE_PROGRESS);
        if(params != null) {
            params.put("proType", EnumProjectType.SCIENCE_PROGRESS);
        }
    }
}
