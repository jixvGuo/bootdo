package com.bootdo.system.controller;

import com.bootdo.common.controller.BaseController;
import com.bootdo.common.domain.DictDO;
import com.bootdo.common.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/sys/major")
@Controller
public class MajorController extends BaseController {

    @Autowired
    private DictService dictService;

    @RequestMapping("/list")
    @ResponseBody
    public List<DictDO> getMajorList(){
        List<DictDO> dictDOS = dictService.listByType("major");
        return dictDOS;
    }
}
