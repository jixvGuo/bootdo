package com.bootdo.cpe.controller.qc;

import com.bootdo.common.controller.BaseQcProController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * 专家打分信息表
 *
 * @author houzb
 * @version 1.0
 * @date 2022-03-16 6:47
 */
@RequestMapping("/qcScore")
@Controller
public class QcSpecialistScoreController extends BaseQcProController {
    private String prefix = "cpe/qc";


    /**
     * 去qc打分
     * @param params
     * @param map
     * @return
     */
    @RequestMapping("/toScore")
    public String toScorePage(@RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        return prefix + "/score/specialist_score";
    }

    /**
     * 去qc意见
     * @param params
     * @param map
     * @return
     */
    @RequestMapping("/toOpinion")
    public String toOpinionPage(@RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        return prefix + "/score/specialist_opinion";
    }

}
