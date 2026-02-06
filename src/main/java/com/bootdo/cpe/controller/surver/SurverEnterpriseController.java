package com.bootdo.cpe.controller.surver;

import com.bootdo.common.controller.BaseSurverController;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.cpe.domain.ApplyEnterpriseInfo;
import com.bootdo.cpe.domain.QcProStatEnum;
import com.bootdo.cpe.domain.SurverProjectInfo;
import com.bootdo.cpe.service.QcAwardService;
import com.bootdo.cpe.service.SurverAwardService;
import com.bootdo.system.domain.UserDO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static com.bootdo.common.config.Constant.*;

/**
 * 勘察奖项目列表信息
 *
 * @author houzb
 * @version 1.0
 * @date 2022-03-31 21:27
 */
@Controller
@RequestMapping("/surverEnterprise")
public class SurverEnterpriseController extends BaseSurverController {
    private String prefix = "cpe/survey";

    @Autowired
    private SurverAwardService surverAwardService;

    @RequiresPermissions("surveraward:enterprise:list")
    @RequestMapping("/toList")
    public String toProListMain(@RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        return prefix + "/surver_enterprise_list";
    }

    /**
     * 获取项目列表
     * @return
     */
    @RequestMapping("/get/enterpriseList")
    @ResponseBody
    public PageUtils getSurverProList(@RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        Query query = new Query(params);
        List<Long> roleIdList = getUser().getRoleIds();
        boolean isAssociation = roleIdList.contains(ROLE_SCIENCE_ASSOCIATION_ID) ||
                   roleIdList.contains(ROLE_QC_ASSOCIATION_ID) || roleIdList.contains(ROLE_SURVER_ASSOCIATION_ID)
                 || roleIdList.contains(ROLE_ASSOCIATION_OIL_PRO_ID);
        query.put("isAssocation", isAssociation);
        query.put("enUid", getUserId());
        List<ApplyEnterpriseInfo> proDataDtoList = surverAwardService.listEnterpriseInfo(query);
        int num = query.getOffset();
        AtomicInteger atomicInteger = new AtomicInteger(num + 1);
        proDataDtoList.stream().forEach(pro->{
            pro.setNum(atomicInteger.getAndIncrement());
        });
        int total = surverAwardService.countEnterpriseInfo(query);
        PageUtils pageUtils = new PageUtils(proDataDtoList, total);
        return pageUtils;
    }


}
