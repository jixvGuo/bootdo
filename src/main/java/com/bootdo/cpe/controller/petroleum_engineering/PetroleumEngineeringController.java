package com.bootdo.cpe.controller.petroleum_engineering;

import com.bootdo.activiti.domain.PublishAwardTaskDo;
import com.bootdo.activiti.service.AwardFlowService;
import com.bootdo.activiti.service.AwardPublishTaskService;
import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.controller.BaseOilProController;
import com.bootdo.common.domain.FileDO;
import com.bootdo.common.service.FileService;
import com.bootdo.common.utils.*;
import com.bootdo.cpe.domain.EnumAwardType;
import com.bootdo.cpe.petroleum_engineering_award.domain.*;
import com.bootdo.cpe.petroleum_engineering_award.service.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

import static com.bootdo.common.config.Constant.ROLE_ASSOCIATION_OIL_PRO_ID;

/**
 * 石油工程奖
 *
 * @author houzb
 * @version 1.0
 * @date 2021-03-12 6:51
 */
@RequestMapping("/petroleumEngineering")
@Controller
public class PetroleumEngineeringController extends BaseOilProController {
    @Autowired
    private PetroleumEngineeringService petroleumEngineeringService;

    @Autowired
    private FileService sysFileService;

    @Autowired
    private BootdoConfig bootdoConfig;
    @Autowired
    private OilProInstallReviewRecordService oilProInstallReviewRecordService;
    @Autowired
    private OilProQualityReviewRecordService oilProQualityReviewRecordService;
    @Autowired
    private OilProQualityGoldReviewRecordService oilProQualityGoldReviewRecordService;
    @Autowired
    private OilQualityConfirmFileService qualityConfirmFileService;
    @Autowired
    private OilQualityProSituationService qualityProSituationService;
    @Autowired
    private OilAwardApplyInfoService oilAwardApplyInfoService;
    @Autowired
    private OilProApplyInfoService oilProApplyInfoService;
    @Autowired
    private AwardPublishTaskService awardPublishTaskService;

    private String prefix = "engineering";

    @RequiresPermissions("bestproaward:to:mainprolist")
    @RequestMapping("/toProListMain")
    public String toProListMain(@RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        return prefix + "/best_pro_list_main";
    }

    @RequestMapping("/toInstallList")
    public String toInstallProList(@RequestParam Map<String, Object> params, ModelMap map) {
        isTaskIsApply(map, params, true);
        packageAwardTaskId(map, params);
        return prefix + "/pro_install_list";
    }

    @RequestMapping("/installList")
    @ResponseBody
    public PageUtils getInstallProList(@RequestParam Map<String, Object> params, ModelMap map) {
        List<Long> roleList = getUser().getRoleIds();
        //是否为石油工程协会人员,协会人员可以查看申报任务下的全部项目
        boolean isAssociationUser = roleList.contains(ROLE_ASSOCIATION_OIL_PRO_ID);
        if (!isAssociationUser) {
            //非协会人员仅能查看自己提交的项目
            params.put("optUid", getUserId());
        }
        params.put("isAssociationUser", isAssociationUser);
        packageAwardTaskId(map, params);
        Query query = new Query(params);
        query.remove("proId");
        query.remove("applyId");
        query.put("isAssociationUser", isAssociationUser);
        List<OilProInstallBaseInfoDO> installInfoDOList = petroleumEngineeringService.listInstallPro(query);
        int total = petroleumEngineeringService.installProCount(query);
        PageUtils pageUtils = new PageUtils(installInfoDOList, total);
        return pageUtils;
    }


    /**
     * 删除安装工程申报
     */
    @RequestMapping("/removeInstallPro")
    @RequiresPermissions("petroleum_engineering_award:oilProApplyInfo:removeInstallPro")
    @ResponseBody
    public R remove(Integer proId) {
        if (proId != null && petroleumEngineeringService.removeInstallPro(proId) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 提交审核项目
     *
     * @param proId
     * @return
     */
    @RequestMapping("/subCheck")
    @ResponseBody
    public R subCheckPro(Integer proId) {
        if (proId != null && proId > 0 && petroleumEngineeringService.updateProCheck(proId) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 撤回提交审核项目
     *
     * @param proId
     * @return
     */
    @RequestMapping("/cancelCheck")
    @ResponseBody
    @RequiresPermissions("petroleumEngineering:enterpriseApply:cancelReview")
    public R cancelCheckPro(Integer proId) {
        if (proId != null && proId > 0 && petroleumEngineeringService.updateProApply(proId) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 查看安装工程审查结果列表
     *
     * @param params
     * @return
     */
    @RequestMapping("/viewInstallCheckResultList")
    public String toViewInstallCheckResultList(@RequestParam Map<String, Object> params, ModelMap map) {
        applyParams(map, params);
        return prefix + "/pro_install_review_result_list";
    }

    /**
     * 获取安装工程审查结果列表
     *
     * @param params
     * @param map
     * @return
     */
    @RequestMapping("/getViewInstallCheckResultList")
    @ResponseBody
    public PageUtils getViewCheckResultList(@RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        Query query = new Query(params);
        query.remove("applyId");
        List<OilProInstallReviewRecordDO> reviewRecordDOList = oilProInstallReviewRecordService.list(query);
        int total = oilProInstallReviewRecordService.count(query);
        PageUtils pageUtils = new PageUtils(reviewRecordDOList, total);
        return pageUtils;
    }

    /**
     * 查看优质工程审查结果列表
     *
     * @param params
     * @return
     */
    @RequestMapping("/viewQualityCheckResultList")
    public String toViewQualityCheckResultList(@RequestParam Map<String, Object> params, ModelMap map) {
        applyParams(map, params);
        return prefix + "/pro_quality_review_result_list";
    }

    /**
     * 获取优质工程审查结果列表
     *
     * @param params
     * @param map
     * @return
     */
    @RequestMapping("/getViewQualityCheckResultList")
    @ResponseBody
    public PageUtils getViewQualityCheckResultList(@RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        Query query = new Query(params);
        query.remove("applyId");
        List<OilProQualityReviewRecordDO> reviewRecordDOList = oilProQualityReviewRecordService.list(query);
        int total = oilProQualityReviewRecordService.count(query);
        PageUtils pageUtils = new PageUtils(reviewRecordDOList, total);
        return pageUtils;
    }

    /**
     * 查看优质工程金奖审查结果列表
     *
     * @param params
     * @return
     */
    @RequestMapping("/viewQualityGoldCheckResultList")
    public String toViewQualityGoldCheckResultList(@RequestParam Map<String, Object> params, ModelMap map) {
        applyParams(map, params);
        return prefix + "/pro_quality_gold_review_result_list";
    }

    /**
     * 获取优质工程金奖审查结果列表
     *
     * @param params
     * @param map
     * @return
     */
    @RequestMapping("/getViewQualityGoldCheckResultList")
    @ResponseBody
    public PageUtils getViewQualityGoldCheckResultList(@RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        Query query = new Query(params);
        query.remove("applyId");
        List<OilProQualityGoldReviewRecordDO> reviewRecordDOList = oilProQualityGoldReviewRecordService.list(query);
        int total = oilProQualityGoldReviewRecordService.count(query);
        PageUtils pageUtils = new PageUtils(reviewRecordDOList, total);
        return pageUtils;
    }


    /**
     * 跳转到优质工程项目列表
     *
     * @return
     */
    @RequestMapping("/toQualityList")
    public String toQualityProList(@RequestParam Map<String, Object> params,ModelMap map) {
        isTaskIsApply(map, params, true);
        packageAwardTaskId(map, params);
        return prefix + "/pro_quality_list";
    }

    @RequestMapping("/qualityList")
    @ResponseBody
    public PageUtils getQualityProList(@RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        List<Long> roleList = getUser().getRoleIds();
        //是否为石油工程协会人员,协会人员可以查看申报任务下的全部项目
        boolean isAssociationUser = roleList.contains(ROLE_ASSOCIATION_OIL_PRO_ID);
        if (!isAssociationUser) {
            //非协会人员仅能查看自己提交的项目
            params.put("optUid", getUserId());
        }
        Query query = new Query(params);
        query.remove("proId");
        query.remove("applyId");
        query.put("isAssociationUser", isAssociationUser);
        List<OilProQualityInfoDO> installInfoDOList = petroleumEngineeringService.listQualityPro(query);
        int total = petroleumEngineeringService.qualityProCount(query);
        PageUtils pageUtils = new PageUtils(installInfoDOList, total);
        return pageUtils;
    }

    /**
     * 删除安装工程申报
     */
    @RequestMapping("/removeQualityPro")
    @RequiresPermissions("petroleumEngineering:qualityPro:removeQualityPro")
    @ResponseBody
    public R removeQualityPro(Integer proId) {
        if (proId != null && petroleumEngineeringService.removeQualityPro(proId) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 跳转到优质工程项目列表
     *
     * @return
     */
    @RequestMapping("/toQualityGoldList")
    public String toQualityGoldProList(@RequestParam Map<String, Object> params,ModelMap map) {
        isTaskIsApply(map, params, true);
        packageAwardTaskId(map, params);
        return prefix + "/pro_quality_gold_list";
    }


    /**
     * 删除
     */
    @PostMapping("/removeQualityFile/{fileType}")
    @ResponseBody
    public R remove(@PathVariable("fileType") String fileType, Long id, HttpServletRequest request) {
        if ("test".equals(getUsername())) {
            return R.error(1, "演示系统不允许修改,完整体验请部署程序");
        }
        if ("desc".equals(fileType)) {
            petroleumEngineeringService.removeQualityDescFile(id);
        } else if ("confirm".equals(fileType)) {
            petroleumEngineeringService.removeQualityConfirmFile(id);
        } else {
            return R.error("文件类型不存在");
        }
        FileDO fileDO = sysFileService.get(id);
        sysFileService.deleteEnterpriseDocByFileId(id);
        sysFileService.remove(id);
        if (fileDO != null) {
            String fileName = bootdoConfig.getUploadPath() + fileDO.getUrl().replace("/files/", "");
            boolean b = FileUtil.deleteFile(fileName);
            if (!b) {
                return R.ok("数据库记录删除成功，文件删除失败");
            }
        }
        return R.ok();
    }

    /**
     * 下载项目中的文件列表
     */
    @RequestMapping("/downloadProDocFiles")
    @RequiresPermissions("petroleumEngineering:petroleum:downloadProDocFiles")
    @ResponseBody
    public R downloadUpDocFiles(Integer proId) {
        if (proId == null) {
            return R.error("选择要下载的项目");
        }
        Map<String, Object> params = new HashMap<>();
        params.put("proId", proId);
        List<OilQualityConfirmFileDO> confirmFileDOList = qualityConfirmFileService.list(params);
        List<OilQualityProSituationDO> situationDOList = qualityProSituationService.list(params);
        String uploadPath = bootdoConfig.getUploadPath();

        Set<String> filePathList = new HashSet<>();
        confirmFileDOList.stream().forEach(f -> {
            String url = f.getUrl();
            String filePath = uploadPath + "/" + url.replaceAll("/files/", "");
            filePathList.add(filePath);
        });
        situationDOList.stream().forEach(f->{
            String url = f.getUrl();
            String filePath = uploadPath + "/" + url.replaceAll("/files/", "");
            filePathList.add(filePath);
        });

        //打包数据下发
        String curDate = DateUtils.getCurDate();
        String[] dateArr = curDate.split("-");
        String userFolderPath =  dateArr[0] + "/" + dateArr[1] + "/u_" + getUserId() + "/zip";
        String storeZipFolder = uploadPath + userFolderPath ;
        File zipFolder = new File(storeZipFolder);
        if(!zipFolder.exists()) {
            zipFolder.mkdirs();
        }

        long curTime = System.currentTimeMillis();
        String zipFileName = curTime + "_" + proId ;
        List<OilAwardApplyInfoDO> oilAwardApplyInfoDOList = oilAwardApplyInfoService.list(params);
        if(oilAwardApplyInfoDOList.size() == 0) {
            List<OilProApplyInfoDO> applyInfoDOList = oilProApplyInfoService.getByProId(proId);
            if(applyInfoDOList.size() > 0) {
                OilProApplyInfoDO oilProApplyInfoDO = applyInfoDOList.get(0);
                zipFileName = oilProApplyInfoDO.getProjectName() + "_" + proId + "_" + curTime;
            }
        }else {
            OilAwardApplyInfoDO oilAwardApplyInfoDO = oilAwardApplyInfoDOList.get(0);
            zipFileName = oilAwardApplyInfoDO.getProjectName() + "_" + proId  + "_" + curTime;
        }
        zipFileName = zipFileName.replaceAll(" ", "");
        zipFileName = zipFileName.replaceAll("\\+", "");
        zipFileName = zipFileName.replaceAll("\\-", "");
        try {
            String str = URLEncoder.encode(zipFileName, "UTF-8");
            System.out.println(str);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        List<String> fList = new ArrayList<>();
        filePathList.stream().forEach(f->{
            fList.add(f);
        });
        try {
            ZipCompress.compress(storeZipFolder, zipFileName, fList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String zipUrl = "/files/" + userFolderPath + "/" + zipFileName + ".zip";
        R result = R.ok();
        result.put("zipUrl", zipUrl);
        return result;
    }
}
