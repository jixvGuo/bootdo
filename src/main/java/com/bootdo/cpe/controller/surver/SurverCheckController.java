package com.bootdo.cpe.controller.surver;

import com.bootdo.common.controller.BaseSurverController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * 形式检查
 *
 * @author houzb
 * @version 1.0
 * @date 2022-04-08 7:12
 */
@Controller
@RequestMapping("/surverReview")
public class SurverCheckController extends BaseSurverController {
    private String prefix = "cpe/survey";

    /**
     * 1、协会联系人分派形式审查任务
     * @return
     */
    @RequestMapping("/toAssign")
    public String toAssignTask(ModelMap map, @RequestParam Map<String, Object> params) {
        packageAwardTaskId(map, params);
        return prefix + "/check/assign_review_task";
    }

    /**
     * 02_咨询类形式审查模板
     * @return
     */
    @RequestMapping("/reviewConsulting")
    public String toReviewConsulting(ModelMap map, @RequestParam Map<String, Object> params) {
        packageAwardTaskId(map, params);
        return prefix + "/check/review_consulting_template";
    }

    /**
     * 06、设计类审查表格
     * @return
     */
    @RequestMapping("/reviewDesign")
    public String toReviewDesign(ModelMap map, @RequestParam Map<String, Object> params) {
        packageAwardTaskId(map, params);
        return prefix + "/check/review_design_template";
    }

    /**
     * 03、软件类审查表格
     * @return
     */
    @RequestMapping("/reviewSoft")
    public String toReviewSoft(ModelMap map, @RequestParam Map<String, Object> params) {
        packageAwardTaskId(map, params);
        return prefix + "/check/review_soft_template";
    }

    /**
     * 04、标准设计类审查表格
     * @return
     */
    @RequestMapping("/reviewStandard")
    public String toReviewStandard(ModelMap map, @RequestParam Map<String, Object> params) {
        packageAwardTaskId(map, params);
        return prefix + "/check/review_standard_template";
    }

    /**
     * 05、勘察类审查表格
     * @return
     */
    @RequestMapping("/reviewSurver")
    public String toReviewSurver(ModelMap map, @RequestParam Map<String, Object> params) {
        packageAwardTaskId(map, params);
        return prefix + "/check/review_surver_template";
    }
}
