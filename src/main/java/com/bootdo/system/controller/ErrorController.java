package com.bootdo.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 自定义错误信息页面
 *
 * @author houzb
 * @version 1.0
 * @date 2021-03-13 8:09
 */
@Controller
@RequestMapping("/error")
public class ErrorController {

    private String prefix = "error";


    @RequestMapping("/err")
    public String toError(String msg, ModelMap map){
       map.put("msg", msg);
       return prefix + "/my_error";
    }

}
