package com.bootdo.system.controller;

import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bootdo.activiti.domain.EnterpriseProjectInfoDo;
import com.bootdo.activiti.service.AwardEnterpriseProjectService;
import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.controller.BaseSciencePersonalController;
import com.bootdo.common.utils.*;
import com.bootdo.cpe.domain.DocPersonApplyWord;
import com.bootdo.cpe.domain.EnumProjectType;
import com.bootdo.cpe.petroleum_engineering_award.domain.OilProStatEnum;
import com.bootdo.cpe.utils.PathUtil;
import com.bootdo.system.config.ConstantCommonData;
import com.bootdo.system.domain.*;
import com.bootdo.system.service.EnterpriPersonalInfoTenYearsPatentService;
import com.bootdo.system.service.EnterpriPersonalInfoWorkHistoryService;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.system.service.EnterpriPersonalInfoService;

import javax.servlet.http.HttpServletResponse;

import static com.bootdo.common.config.Constant.ROLE_SCIENCE_ASSOCIATION_ID;
import static com.bootdo.common.config.Constant.ROLE_SCIENCE_OFFLINE_VIEW_ID;

/**
 * 企业报奖个人信息
 *
 * @author chglee
 * @email mrhouzhibin@163.com
 * @date 2020-06-30 04:48:14
 */

@Controller
@RequestMapping("/system/enterpriPersonalInfo")
public class EnterpriPersonalInfoController extends BaseSciencePersonalController {
    private Logger logger = LoggerFactory.getLogger(EnterpriPersonalInfoController.class);
    @Autowired
    private EnterpriPersonalInfoService enterpriPersonalInfoService;

    @Autowired
    private EnterpriPersonalInfoWorkHistoryService enterpriPersonalInfoWorkHistoryService;


    @Autowired
    private EnterpriPersonalInfoTenYearsPatentService tenYearsPatentService;


    @Autowired
    private AwardEnterpriseProjectService awardEnterpriseProjectService;

    @Autowired
    ResourceLoader resourceLoader;

    @Autowired
    private BootdoConfig bootdoConfig;

    private Map<String, Object> newParams;

    @GetMapping()
    @RequiresPermissions("system:enterpriPersonalInfo:enterpriPersonalInfo")
    String EnterpriPersonalInfo(@RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        return "system/enterpriPersonalInfo/enterpriPersonalInfo";
    }

    @ResponseBody
    @GetMapping("/list")
    public PageUtils list(@RequestParam Map<String, Object> params, ModelMap map) {
        //TODO 企业用户只能查看自己的项目,非企业用户可以查看所有的列表
        packageAwardTaskId(map, params);

        Long userId = getUserId();
        long uid = userId != null ? userId : 0;
        UserDO userDO = getUser();
        List<Long> roleList = userDO.getRoleIds();
        long optUid = 0;
        for (long roleId : roleList) {
            if (roleId == 60 || roleId == 1 || roleId == 62) {
                optUid = 0;
                break;
            } else {
                optUid = uid;
            }
        }
        params.put("optUid", optUid);
        UserDO user = getUser();
        List<Long> roleIdList = user.getRoleIds();
        if (roleIdList.contains(ROLE_SCIENCE_ASSOCIATION_ID)) {
            //todo 临时使用协会联系人的用户id
            params.put("associationUserId", roleIdList.contains(ROLE_SCIENCE_OFFLINE_VIEW_ID) ? 101 : user.getUserId());
        }
        //查询列表数据
        params.put("proStatStr", "");
        Object keyWordObj = params.get("keyWord");
        if (keyWordObj != null) {
            List<String> proStatList = OilProStatEnum.getStatValByKey(keyWordObj.toString());
            if (proStatList.size() > 0) {
                String str = new String();
                for (String s : proStatList) {
                    str += s + ",";
                }
                params.put("proStatStr", str.substring(0, str.length() - 1));
            }
        }
        Query query = new Query(params);
        this.newParams = params;
        List<EnterpriPersonalInfoDO> enterpriPersonalInfoList = enterpriPersonalInfoService.list(query);
        int total = enterpriPersonalInfoService.count(query);
        PageUtils pageUtils = new PageUtils(enterpriPersonalInfoList, total);
        return pageUtils;
    }


    @RequestMapping("/printExcel")
    @ResponseBody
    public String printExcel(HttpServletResponse response, ModelMap map, @RequestParam Map<String, Object> params) {

        this.newParams.put("limit", "100000");
        Query query = new Query(this.newParams);

        List<EnterpriPersonalInfoDO> enterpriPersonalInfoList = enterpriPersonalInfoService.list(query);
        System.out.println(enterpriPersonalInfoList.size());


        String[] title = {"编号", "姓名", "申报单位", "专业", "填写日期", "状态", "申报账号"};
        try {
            PoiWordUtils.downPersonalExcel(title, enterpriPersonalInfoList, response);
        } catch (Exception e) {
        }
        map.addAttribute("result", "下载成功");

        return "";
    }


    @GetMapping("/add")
    @RequiresPermissions("system:enterpriPersonalInfo:add")
    String add(@RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        return "system/enterpriPersonalInfo/add";
    }

    @GetMapping("/edit/{id}")
    @RequiresPermissions("system:enterpriPersonalInfo:edit")
    String edit(@PathVariable("id") Integer id, @RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        EnterpriPersonalInfoDO enterpriPersonalInfo = enterpriPersonalInfoService.get(id);
        map.addAttribute("enterpriPersonalInfo", enterpriPersonalInfo);
        return "system/enterpriPersonalInfo/edit";
    }


    @RequestMapping("/print")
    public String printPersonal(int id, HttpServletResponse response, @RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        //待下载文件名
        //设置为png格式的文件
        response.setHeader("content-type", "application/msword");
        response.setContentType("application/octet-stream");
        byte[] buff = new byte[1024];
        //创建缓冲输入流
        BufferedInputStream bis = null;
        OutputStream outputStream = null;
        File storeFile = null;
        try {
            outputStream = response.getOutputStream();

            DocPersonApplyWord word = new DocPersonApplyWord();
            EnterpriPersonalInfoDO personalInfoDO = enterpriPersonalInfoService.get(id);
            if(StringUtils.isNotBlank(personalInfoDO.getPhoto())) {
                try {
                    personalInfoDO.setPhoto(URLDecoder.decode(personalInfoDO.getPhoto(), "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
            }
            Map<String, Object> param = new HashMap<>();
            param.put("personalId", id);

            List<EnterpriPersonalInfoWorkHistoryDO> historyDO = enterpriPersonalInfoWorkHistoryService.list(param);

            String isEnjoyGovernmentSubsidies = personalInfoDO.getIsEnjoyGovernmentSubsidies();
            if ("2".equals(isEnjoyGovernmentSubsidies)) {
                personalInfoDO.setIsEnjoyGovernmentSubsidies("否");
            } else {
                personalInfoDO.setIsEnjoyGovernmentSubsidies("是");
            }
            word.setpInfo(personalInfoDO);

            Map<String, Object> tenyearParam = new HashMap<>();
            tenyearParam.put("proId", personalInfoDO.getProId());
            tenyearParam.put("taskId", personalInfoDO.getTaskId());

            List<EnterpriPersonalInfoTenYearsPatentDO> tenYearsPatentDOList = tenYearsPatentService.list(tenyearParam);

            System.out.println("AA-A" + JSONUtils.beanToJson(tenYearsPatentDOList));

            File templateFile = PathUtil.getJarResourceFile(ConstantCommonData.TEMPLATE_DOC_SCIENCE_PERSONAL_CLASS_PATH);
            if (templateFile == null) {
                Resource resource = resourceLoader.getResource(ConstantCommonData.TEMPLATE_DOC_SCIENCE_PERSONAL_CLASS_PATH);
                templateFile = resource.getFile();
            }
            String fName = templateFile.getName();
            String[] fNameArr = fName.split("\\.");
            String tmpFileName = fNameArr[0] + "_" + System.currentTimeMillis() + RandomStringUtils.randomAlphabetic(5) + "." + fNameArr[1];
            tmpFileName = URLEncoder.encode(tmpFileName, "UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + tmpFileName);
            String storePath = bootdoConfig.getUploadPath() + "/print_doc/" + DateUtils.getCurDate();
            storeFile = new File(storePath);
            if (!storeFile.exists()) {
                storeFile.mkdirs();
            }
            storePath = storePath + "/" + tmpFileName;
            storeFile = new File(storePath);
            FileUtils.copyFile(templateFile, storeFile);


            PoiWordUtils.createPersonalWord(templateFile.getAbsolutePath(), storePath, word, historyDO, tenYearsPatentDOList);

            InputStream inputStream = new FileInputStream(storeFile);
            //这个路径为待下载文件的路径
            bis = new BufferedInputStream(inputStream);
            int read = bis.read(buff);

            //通过while循环写入到指定了的文件夹中
            while (read != -1) {
                outputStream.write(buff, 0, buff.length);
                outputStream.flush();
                read = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
            //出现异常返回给页面失败的信息
            map.addAttribute("result", "下载失败");
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    logger.error("close err {}", e.getMessage());
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    logger.error("close out err {}", e.getMessage());
                }
            }
            if (storeFile != null) {
                storeFile.deleteOnExit();
            }
        }
        //成功后返回成功信息
        map.addAttribute("result", "下载成功");
        return "enterprise/apply/print";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("system:enterpriPersonalInfo:add")
    public R save(EnterpriPersonalInfoDO enterpriPersonalInfo) {
        enterpriPersonalInfo.setOptUid(getUserId());


        //保存项目记录
        EnterpriseProjectInfoDo projectInfoDo = new EnterpriseProjectInfoDo();
        projectInfoDo.setId(enterpriPersonalInfo.getProId());
        projectInfoDo.setPublishTaskId(enterpriPersonalInfo.getTaskId());
        projectInfoDo.setCreateUid(getUserId());
        projectInfoDo.setChengguo("先进个人:" + enterpriPersonalInfo.getUserName());
        projectInfoDo.setMajor(enterpriPersonalInfo.getWorkMajor());
        projectInfoDo.setProType(EnumProjectType.SCIENCE_PERSONAL.getProType());
        projectInfoDo.setProSubType(EnumProjectType.SCIENCE_PERSONAL.getProType());
        awardEnterpriseProjectService.updateProjectInfo(projectInfoDo);

        int proId = projectInfoDo.getId();
        enterpriPersonalInfo.setProId(proId);
        Integer personId = enterpriPersonalInfo.getId();
        if (personId != null && personId > 0) {
            enterpriPersonalInfoService.update(enterpriPersonalInfo);
        } else {
            personId = enterpriPersonalInfoService.save(enterpriPersonalInfo);
        }

//		获取 记录的ID
        if (personId > 0) {
            // 保存成功需要返回 保存的用户ID
            return R.ok().put("userId", enterpriPersonalInfo.getId());
        }
        return R.error();
    }


    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("system:enterpriPersonalInfo:edit")
    public R update(EnterpriPersonalInfoDO enterpriPersonalInfo) {
        enterpriPersonalInfoService.update(enterpriPersonalInfo);
        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("system:enterpriPersonalInfo:remove")
    public R remove(Integer id) {
        if (enterpriPersonalInfoService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("system:enterpriPersonalInfo:batchRemove")
    public R remove(@RequestParam("ids[]") Integer[] ids) {
        enterpriPersonalInfoService.batchRemove(ids);
        return R.ok();
    }

}
