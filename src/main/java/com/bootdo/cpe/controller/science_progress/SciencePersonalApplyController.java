package com.bootdo.cpe.controller.science_progress;

import com.bootdo.common.controller.BaseSciencePersonalController;
import com.bootdo.common.domain.DictDO;
import com.bootdo.common.service.DictService;
import com.bootdo.system.domain.UserDO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.bootdo.common.config.Constant.ROLE_SCIENCE_ASSOCIATION_ID;

/**
 * 个人申请
 *
 * @author houzb
 * @version 1.0
 * @date 2021-05-12 1:02
 */
@Controller
@RequestMapping("/sciencePersonal")
public class SciencePersonalApplyController extends BaseSciencePersonalController {
    @Autowired
    private DictService dictService;

    /**
     * 跳转到要个人申报的项目列表
     *
     * @param map
     * @return
     */
    @RequestMapping("/toApplyPersonalPros")
    @RequiresPermissions("act:award:apply_pros")
    public String toApplyPersonalProList(@RequestParam Map<String, Object> params, ModelMap map) {
        UserDO user = getUser();
        List<Long> roleIdList = user.getRoleIds();
        isTaskIsApply(map, params, roleIdList, user);
        packageAwardTaskId(map, params);
        map.put("apply_pro_type", "personal");
        if(roleIdList.contains(ROLE_SCIENCE_ASSOCIATION_ID)) {
            //TODO 临时取消下发专业，需要根据项目状态判断是否下发
            /*List<DictDO> dictDOList = new ArrayList<>();
            DictDO selOptionDo = new DictDO();
            selOptionDo.setName("请选择");
            dictDOList.add(selOptionDo);
            List<DictDO> dictDOS = dictService.listByType("profession_type");
            dictDOList.addAll(dictDOS);
            map.put("specialTypeList", dictDOList);*/
        }
        return "act/award/chengguo_personal/enterprise_personal_pro_list";
    }


}
