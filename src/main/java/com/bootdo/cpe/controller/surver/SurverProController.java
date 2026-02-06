package com.bootdo.cpe.controller.surver;

import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.controller.BaseSurverController;
import com.bootdo.common.domain.FileDO;
import com.bootdo.common.utils.*;
import com.bootdo.cpe.domain.*;
import com.bootdo.cpe.petroleum_engineering_award.domain.OilAwardApplyInfoDO;
import com.bootdo.cpe.petroleum_engineering_award.domain.OilProApplyInfoDO;
import com.bootdo.cpe.petroleum_engineering_award.domain.OilQualityConfirmFileDO;
import com.bootdo.cpe.petroleum_engineering_award.domain.OilQualityProSituationDO;
import com.bootdo.cpe.service.AwardEnterpriseProjectCommonService;
import com.bootdo.cpe.service.QcAwardService;
import com.bootdo.cpe.service.SurverAwardService;
import com.bootdo.cpe.utils.AwardSurverSubTypeEnum;
import com.bootdo.cpe.utils.PathUtil;
import com.bootdo.cpe.utils.PoiWordQCProUtils;
import com.bootdo.system.config.ConstantCommonData;
import com.bootdo.system.domain.UserDO;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

import static com.bootdo.common.config.Constant.*;

/**
 * 勘察奖项目列表信息
 *
 * @author houzb
 * @version 1.0
 * @date 2022-03-31 21:27
 */
@Controller
@RequestMapping("/surverPro")
public class SurverProController extends BaseSurverController {
    private String prefix = "cpe/survey";

    @Autowired
    private SurverAwardService surverAwardService;
    @Autowired
    private QcAwardService qcAwardService;
    @Autowired
    private BootdoConfig bootdoConfig;
    @Autowired
    private AwardEnterpriseProjectCommonService awardEnterpriseProjectCommonService;

    @RequiresPermissions("surveraward:to:prolist")
    @RequestMapping("/toProListMain")
    public String toProListMain(@RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        return prefix + "/surver_pro_list_main";
    }


    /**
     * 查看项目列表
     * @param map
     * @param params
     * @return
     */
    @RequiresPermissions("surveraward:to:prolist")
    @RequestMapping("/toProList")
    public String toProList(ModelMap map, @RequestParam Map<String, Object> params) {
        packageAwardTaskId(map, params);
        map.put("proSubType", params.get("proSubType"));
        return prefix + "/surver_pro_list";
    }


    /**
     * 获取项目列表
     * @return
     */
    @RequestMapping("/get/proList")
    @ResponseBody
    public PageUtils getSurverProList(@RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        UserDO user = getUser();
        Long uid = getUserId();
        List<Long> roleIdList = user.getRoleIds();
        if (roleIdList.contains(ROLE_SURVER_ASSOCIATION_ID)) {
            //todo 临时使用协会联系人的用户id
            params.put("associationUserId", roleIdList.contains(ROLE_SURVER_OFFLINE_VIEW_ID) ? 101 : user.getUserId());
        } else if (roleIdList.contains(ROLE_ENTERPRISE_SURVER_ID)) {
            //企业用户查看自己创建项目
            params.put("enterpriseUid", uid);
        } else if(roleIdList.contains(ROLE_SURVER_SPECALIST_ID)) {
            //评审专家
            params.put("scoreSpecialistUid", uid);
        }else {
            //分派给自己的项目
            params.put("ass_assign_uid", uid);
        }
        getProListParamsByRole(params);

        params.put("proStatStr", "");
        Object keyWordObj = params.get("keyWord");
        if (keyWordObj != null) {
            List<String> proStatList = QcProStatEnum.getStatValByKey(keyWordObj.toString());
            if (proStatList.size() > 0) {
                String str = new String();
                for (String s : proStatList) {
                    str +=  s + ",";
                }
                params.put("proStatStr", str.substring(0, str.length() - 1));
            }
        }

        Query query = new Query(params);

        List<SurverProjectInfo> proDataDtoList = surverAwardService.listProInfo(query);
        int total = surverAwardService.countProInfo(query);
        PageUtils pageUtils = new PageUtils(proDataDtoList, total);
        return pageUtils;
    }

   /**
     * 保存成果编号
     * @param proId
     * @param resultCode
     * @return
     */
    @RequestMapping("/saveCode")
    @ResponseBody
    @RequiresPermissions("cpe:surverApplyInfo:saveCode")
    public R updateProResultCode(int proId, String resultCode) {
        if(StringUtils.isBlank(resultCode)) {
            return R.error("编号不能为空");
        }
        int rst = qcAwardService.updateProResultCode(proId, resultCode);
        return  rst > 0 ? R.ok("保存成功") : R.error("保存失败");
    }

    /**
     * 下载项目中的文件列表
     */
    @RequestMapping("/downloadProDocFiles")
    @RequiresPermissions("cpe:surverApplyInfo:downloadFiles")
    @ResponseBody
    public R downloadUpDocFiles(Integer proId, String fileType) {
        if (proId == null) {
            return R.error("选择要下载的项目");
        }
        List<String> fileTypeList = AwardSurverSubTypeEnum.getFileTypeList(fileType);
        Map<String, Object> params = new HashMap<>();
        params.put("proId", proId);
        params.put("fileTypeList", fileTypeList);
        List<FileDO> fileDOList = surverAwardService.getUploadFileList(params);

        String uploadPath = bootdoConfig.getUploadPath();

        Set<String> filePathList = new HashSet<>();
        fileDOList.stream().forEach(f -> {
            String url = f.getUrl();
            String filePath = uploadPath + "/" + url.replaceAll("/files/", "");
            filePathList.add(filePath);
        });
        String zipFileName = System.currentTimeMillis() + "_" + proId ;
        AwardEnterpriseProjectDO projectDO = awardEnterpriseProjectCommonService.get(proId);
        if(projectDO != null) {
            zipFileName = projectDO.getChengguo() + "_" + proId  + "_" + System.currentTimeMillis();
        }

        //打包数据下发
        String curDate = DateUtils.getCurDate();
        String[] dateArr = curDate.split("-");
        String userFolderPath =  dateArr[0] + "/" + dateArr[1] + "/u_" + getUserId() + "/zip";
        String storeZipFolder = uploadPath + userFolderPath ;
        File zipFolder = new File(storeZipFolder);
        if(!zipFolder.exists()) {
            zipFolder.mkdirs();
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
