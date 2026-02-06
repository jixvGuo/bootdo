package com.bootdo.cpe.controller;

import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.controller.BaseScienceTeamController;
import com.bootdo.common.utils.DateUtils;
import com.bootdo.common.utils.PoiWordUtils;
import com.bootdo.cpe.domain.*;
import com.bootdo.cpe.service.*;
import com.bootdo.cpe.utils.PathUtil;
import com.bootdo.system.config.ConstantCommonData;
import com.bootdo.system.domain.*;
import com.bootdo.system.service.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author houzb
 * @Description
 * @create 2020-09-17 22:27
 */
@Controller
@RequestMapping("/enterpriseTeam")
public class EnterpriseTeamController extends BaseScienceTeamController {

    private String prefix = "enterprise/apply/team";

    @Autowired
    private EnterpriTeamMainUsersService enterpriTeamMainUsersService;


    @Autowired
    private EnterpriTeamOtherMainService enterpriTeamOtherMainService;

    @Autowired
    private EnterpriTeamLeaderInfoService enterpriTeamLeaderInfoService; //主要成员情况带头人

    @Autowired
    private EnterpriTeamAcheievementsService acheievementsService;
    //
    @Autowired
    private EnterpriTeamUsersListService teamUsersListService;

    @Autowired
    private EnterpriTeamAcheievementsService enterpriTeamAcheievementsService;

    @Autowired
    private EnterpriTeamPaperInfoService enterpriTeamPaperInfoService;//发表论文情况

    @Autowired
    private EnterpriTeamIntellectualPropertyService enterpriTeamIntellectualPropertyService;//所获知识产权和技术标准情况


    @Autowired
    private EnterpriTeamProjectInfoService enterpriTeamProjectInfoService;//团队承担项目情况
    @Autowired
    private EnterpriTeamEvaluateOtherService enterpriTeamEvaluateOtherService;//团队第三方评价
    @Autowired
    private EnterpriTeamGetTechAwardService enterpriTeamGetTechAwardService;//团队曾获科技奖励情况

    @Autowired
    private EnterpriTeamCooperationService enterpriTeamCooperationService;// 团队合作情况汇总表

    @Autowired
    private EnterpriTeamSupportCompanyService enterpriTeamSupportCompanyService;// 支持单位情况

    @Autowired
    private EnterpriTeamIntrductionService enterpriTeamIntrductionService; //团队介绍信息
    @Autowired
    private EnterpriTeamAcademicExchangeService enterpriTeamAcademicExchangeService;




    @Autowired
    ResourceLoader resourceLoader;
    @Autowired
    private BootdoConfig bootdoConfig;
    @Autowired
    private EnterpriTeamInfoService enterpriTeamInfoService;
    @Autowired
    private EnterpriTeamUsersService enterpriTeamUsersService;

    /**
     * 去添加标志性成果页面
     *
     * @param map
     * @return
     */
    @RequestMapping("/toAchievementsAdd")
    public String toAchievementsAdd(ModelMap map,@RequestParam Map<String, Object> params) {
        packageAwardTaskId(map, params);
        map.put("enterpriTeamAcheievements", new EnterpriTeamAcheievementsDO());
        return prefix + "/achievements_add_03";
    }

    /**
     * 去标志性成果列表页面
     *
     * @param map
     * @return
     */
    @RequestMapping("/toAchievementsList")
    public String toAchievementsList(ModelMap map,@RequestParam Map<String, Object> params) {
        packageAwardTaskId(map, params);
        List<EnterpriTeamAcheievementsDO> acheievementsDOList = acheievementsService.list(params);
        map.put("acheivementsList", acheievementsDOList);
        return prefix + "/achievements_list_03";
    }

//    int id, HttpServletResponse response, ModelMap map, @RequestParam Map<String, Object> params

    /***
     * 先进团队打印
     * @param id
     * @param response
     * @param map
     * @param params
     * @return
     */
    @RequestMapping("/print")
    public String print(int id,HttpServletResponse response, ModelMap map,@RequestParam Map<String, Object> params) {
        //待下载文件名
        //设置为png格式的文件
        packageAwardTaskId(map, params);
        response.setHeader("content-type", "application/msword");
        response.setContentType("application/octet-stream");
        byte[] buff = new byte[1024];
        //创建缓冲输入流
        BufferedInputStream bis = null;
        OutputStream outputStream = null;
        File storeFile = null;
        try {
            outputStream = response.getOutputStream();
            DocTeamApplyWord word = new DocTeamApplyWord();
            EnterpriTeamInfoDO teamInfoDO = enterpriTeamInfoService.get(id);
            word.setTeamInfo(teamInfoDO);
            //TODO 需要根据团队ID获取团队用户分布信息
            Map<String,Object> param = new HashMap<>();
            param.put("proId",teamInfoDO.getProId());
            List<EnterpriTeamUsersDO> list = enterpriTeamUsersService.list(param);// 人员构成获取

            EnterpriTeamUsersDO userInfo = list.size() > 0 ? list.get(0) : new EnterpriTeamUsersDO();//

            word.setTeamUsersDO(userInfo);

//            List<EnterpriTeamUsersListDO>  mainUsers =
//                    teamUsersListService.list(param);//团队人员构成-主要成员

            //带头人
//            Map<String,Object> leaderParams = new HashMap<>();
//            leaderParams.put("userType","leader");
//            leaderParams.putAll(params);
//            List<EnterpriTeamUsersListDO> mainUsers = teamUsersListService.list(leaderParams);

            Map<String, Object> teamParams = new HashMap<>();
            teamParams.put("proId", teamInfoDO.getProId());
            teamParams.put("taskId", teamInfoDO.getTaskId());
            List<EnterpriTeamLeaderInfoDO> mainUsers = enterpriTeamLeaderInfoService.list(teamParams);// 主要成员情况带头人


            //其他人
//            Map<String,Object> otherParams = new HashMap<>();
//            otherParams.put("userType","other");
//            otherParams.putAll(params);
//            List<EnterpriTeamUsersListDO> otherUserList = teamUsersListService.list(otherParams);


            List<EnterpriTeamOtherMainDO>  otherUserList = enterpriTeamOtherMainService.list(teamParams);//主要情况其他主要成员


            //团队介绍信息
            List<EnterpriTeamIntrductionDO> intrductionDOList = enterpriTeamIntrductionService.list(teamParams);
            EnterpriTeamIntrductionDO teamIntrductionDO = intrductionDOList.size() > 0 ? intrductionDOList.get(0) : new EnterpriTeamIntrductionDO();
            word.setTeamIntrductionDO(teamIntrductionDO);

            List<EnterpriTeamAcheievementsDO> enterpriTeamAcheievementsDOList =
                    enterpriTeamAcheievementsService.list(teamParams);//团队标志性成果
            List<EnterpriTeamPaperInfoDO>     enterpriTeamPaperInfoDOList =
                    enterpriTeamPaperInfoService.list(teamParams);//发表论文专著情况
            //学术交流情况
            List<EnterpriTeamAcademicExchangeDO> academicExchangeDOList = enterpriTeamAcademicExchangeService.list(teamParams);

            List<EnterpriTeamIntellectualPropertyDO>  enterpriTeamIntellectualPropertyDOList =
                    enterpriTeamIntellectualPropertyService.list(teamParams);//所获知识产权和技术标准情况

            List<EnterpriTeamProjectInfoDO> enterpriTeamProjectInfoDOList =
                    enterpriTeamProjectInfoService.list(teamParams);//团队承担项目情况

            List<EnterpriTeamEvaluateOtherDO> enterpriTeamEvaluateOtherDOList =
                    enterpriTeamEvaluateOtherService.list(teamParams);//团队第三方评价

            List<EnterpriTeamGetTechAwardDO> enterpriTeamGetTechAwardDOList =
                    enterpriTeamGetTechAwardService.list(teamParams);//团队曾获科技奖励情况


//            List<EnterpriTeamCooperationDO> enterpriTeamCooperationDOList =
//                    enterpriTeamCooperationService.list(teamParams);// 团队合作情况汇总表


            // 奖励情况
            // 团队承担项目情况
            // 所获知识产权和技术标准情况
            // 发表论文专著情况
            //团队标志性成果

            List<EnterpriTeamSupportCompanyDO>
             enterpriTeamSupportCompanyDO = enterpriTeamSupportCompanyService.list(teamParams);


            List<EnterpriTeamCooperationDO> listCooperationDO =
                    new ArrayList();// 团队合作情况汇总表

            //奖励情况
            for(EnterpriTeamGetTechAwardDO intrductionDO:enterpriTeamGetTechAwardDOList) {
                EnterpriTeamCooperationDO one = new EnterpriTeamCooperationDO();
                one.setType("团队曾获科技奖励情况");
                one.setAchievementsname(intrductionDO.getAwardspeople());
                one.setCooperationpeople(intrductionDO.getMainfulfilhumname());
                listCooperationDO.add(one);
            }
// 团队承担项目情况
            for(EnterpriTeamProjectInfoDO intrductionDO:enterpriTeamProjectInfoDOList) {
                EnterpriTeamCooperationDO one = new EnterpriTeamCooperationDO();
                one.setType("团队承担项目及科研经费情况");
                one.setAchievementsname(intrductionDO.getProname());
                one.setCooperationpeople(intrductionDO.getMainmember());
                one.setRemarks("");
                listCooperationDO.add(one);
            }
// 所获知识产权和技术标准情况
            for(EnterpriTeamIntellectualPropertyDO intrductionDO:enterpriTeamIntellectualPropertyDOList) {
                EnterpriTeamCooperationDO one = new EnterpriTeamCooperationDO();
                one.setType("所获知识产权和技术标准情况");
                one.setAchievementsname(intrductionDO.getPropertyname());
                one.setCooperationpeople(intrductionDO.getInventor());
                one.setRemarks("" );
                listCooperationDO.add(one);
            }

// 发表论文专著情况

            for(EnterpriTeamPaperInfoDO intrductionDO:enterpriTeamPaperInfoDOList) {
                EnterpriTeamCooperationDO one = new EnterpriTeamCooperationDO();
                one.setType("发表论文专著情况");
                one.setAchievementsname(intrductionDO.getPapername());
                one.setCooperationpeople(intrductionDO.getFirstauthor());
                one.setRemarks("" );
                listCooperationDO.add(one);
            }

            //团队标志性成果
         for(EnterpriTeamAcheievementsDO intrductionDO:enterpriTeamAcheievementsDOList) {
                EnterpriTeamCooperationDO one = new EnterpriTeamCooperationDO();
                one.setType("标志性成果");
                one.setAchievementsname(intrductionDO.getAchName());
                one.setCooperationpeople(intrductionDO.getAchUsers());
                one.setRemarks("" );
                listCooperationDO.add(one);
            }

//            EnterpriTeamSupportCompanyDO supportCompanyDO =  enterpriTeamSupportCompanyDO.size() > 0 ? enterpriTeamSupportCompanyDO.get(0) : new EnterpriTeamSupportCompanyDO();

            File templateFile = PathUtil.getJarResourceFile(ConstantCommonData.TEMPLATE_DOC_SCIENCE_TEAM_CLASS_PATH);
            if (templateFile == null) {
                Resource resource = resourceLoader.getResource(ConstantCommonData.TEMPLATE_DOC_SCIENCE_TEAM_CLASS_PATH);
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


//     * @param teamMainUers 主要成员
//     * @param teamLandmarkList 团队标志性成果
//     * @param teamPaperInfos 发表论文专著情况
//     * @param intellectualPropertyList 所获知识产权和技术标准情况
//     * @param teamUndertakesList 团队承担项目情况
//     * @param thirdList    第三方评价表
//     * @param teamEverList 团队曾获科技奖励情况
//     * @param cooperationDOList
//                    * @param teamSupport

            PoiWordUtils.createTeamWord(templateFile.getAbsolutePath(), storePath, word,mainUsers,otherUserList,
                    enterpriTeamAcheievementsDOList,enterpriTeamPaperInfoDOList,academicExchangeDOList,
                    enterpriTeamIntellectualPropertyDOList,enterpriTeamProjectInfoDOList,enterpriTeamEvaluateOtherDOList,
                    enterpriTeamGetTechAwardDOList,listCooperationDO,
                    enterpriTeamSupportCompanyDO);

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
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
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

}
