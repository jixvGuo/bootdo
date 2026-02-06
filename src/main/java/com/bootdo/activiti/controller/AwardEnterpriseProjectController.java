package com.bootdo.activiti.controller;

import com.bootdo.activiti.domain.EnterpriseDocUploadDo;
import com.bootdo.activiti.domain.EnterpriseProjectInfoDo;
import com.bootdo.activiti.domain.PublishAwardTaskDo;
import com.bootdo.activiti.service.AwardEnterpriseProjectService;
import com.bootdo.activiti.service.AwardFlowService;
import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.controller.BaseScienceProController;
import com.bootdo.common.domain.DictDO;
import com.bootdo.common.domain.FileDO;
import com.bootdo.common.domain.Tree;
import com.bootdo.common.service.DictService;
import com.bootdo.common.service.FileService;
import com.bootdo.common.utils.*;
import com.bootdo.cpe.domain.*;
import com.bootdo.cpe.petroleum_engineering_award.domain.OilQualityConfirmFileDO;
import com.bootdo.cpe.petroleum_engineering_award.domain.OilQualityProSituationDO;
import com.bootdo.cpe.petroleum_engineering_award.service.OilQualityConfirmFileService;
import com.bootdo.cpe.petroleum_engineering_award.service.OilQualityProSituationService;
import com.bootdo.cpe.service.ExpertGroupService;
import com.bootdo.cpe.service.ImportCheckExcelDataService;
import com.bootdo.cpe.service.ImportCheckExcelUpdateService;
import com.bootdo.cpe.service.SurverEnterpriseSortInfoService;
import com.bootdo.system.domain.EnterpriPersonalInfoDO;
import com.bootdo.system.domain.EnterpriTeamInfoDO;
import com.bootdo.system.domain.EnterpriseChengguoBaseInfoDO;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.EnterpriPersonalInfoService;
import com.bootdo.system.service.EnterpriTeamInfoService;
import com.bootdo.system.service.EnterpriseChengguoBaseInfoService;
import com.bootdo.system.service.UserService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

import static com.bootdo.common.config.Constant.*;

@Controller
@RequestMapping("/enterprise_pro")
public class AwardEnterpriseProjectController extends BaseScienceProController {
    @Autowired
    private AwardEnterpriseProjectService awardEnterpriseProjectService;

    @Autowired
    private ExpertGroupService expertGroupService;

    @Autowired
    private DictService dictService;
    @Autowired
    private FileService sysFileService;
    @Autowired
    private BootdoConfig bootdoConfig;
    @Autowired
    private UserService userService;
    @Autowired
    TaskService taskService;
    @Autowired
    private AwardFlowService awardFlowService;
    @Autowired
    private OilQualityProSituationService qualityProSituationService;
    @Autowired
    private OilQualityConfirmFileService qualityConfirmFileService;
    @Autowired
    private SurverEnterpriseSortInfoService surverEnterpriseSortInfoService;
    @Autowired
    private ImportCheckExcelDataService importCheckExcelDataService;
    @Autowired
    private ImportCheckExcelUpdateService importCheckExcelUpdateService;
    @Autowired
    private EnterpriPersonalInfoService enterpriPersonalInfoService;
    @Autowired
    private EnterpriTeamInfoService enterpriTeamInfoService;
    @Autowired
    private EnterpriseChengguoBaseInfoService enterpriseChengguoBaseInfoService;


    @RequestMapping("/to_list/{taskId}")
    public String toProList(@PathVariable("taskId") String taskId, ModelMap map) {
        map.put("apply_type", "");
        map.put("publishTaskId", taskId);
        PublishAwardTaskDo taskDo = awardFlowService.getAwardTaskById(taskId);
        String procInsId = taskDo.getProcInsId();
        boolean isReview = false;
        if (StringUtils.isNotBlank(procInsId)) {
            List<Task> taskList = taskService.createTaskQuery()
                    .processInstanceId(procInsId).list();
            Task task = taskList.size() > 0 ? taskList.get(0) : null;
            if (task != null) {
                String defKey = task.getTaskDefinitionKey();
                isReview = defKey.equals("ass_validate_pro");
            }
        }
        map.put("isReview", isReview);
        return "act/award/enterprise_doc_award_pro_list";
    }


    /**
     * 跳转到要个人形式审查申报的项目列表
     *
     * @param map
     * @retur
     */
    @RequestMapping("/to_apply_formal_personal_pros")
    @RequiresPermissions("act:award:apply_pros")
    public String toApplyPersonalFormalProList(@RequestParam Map<String, Object> params, ModelMap map) {
        packageAwardTaskId(map, params);
        map.put("apply_type", "personal");
        return "act/award/formal/enterprise_formal_personal_award_pro_list";
    }

    /**
     * 跳转到要申报的项目列表
     *
     * @param map
     * @return
     */
    @RequestMapping("/to_apply_team_pros")
    @RequiresPermissions("act:award:apply_pros")
    public String toApplyTeamProList(@RequestParam Map<String, Object> params, ModelMap map) {
        UserDO user = getUser();
        List<Long> roleIdList = user.getRoleIds();
        isTaskIsApply(map, params, roleIdList, user);
        packageAwardTaskId(map, params);
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
        map.put("apply_pro_type", "team");
        return "cpe/science/pro_list_team";
    }


    /**
     * 团队 形式审查
     *
     * @param map
     * @return
     */
    @RequestMapping("/to_apply_formal_team_pros")
    @RequiresPermissions("act:award:apply_pros")
    public String toApplyTeamFormalProList(@RequestParam Map<String, Object> params, ModelMap map) {
        map.put("apply_type", "team");
        packageAwardTaskId(map, params);
        return "act/award/formal/enterprise_team_formal_pro_list";
    }


    /**
     * 跳转到要申报的项目列表
     *
     * @param map
     * @return
     */
    @RequestMapping("/to_apply_science_pros")
    @RequiresPermissions("act:award:apply_pros")
    public String toApplyProList(@RequestParam Map<String, Object> params, ModelMap map) {
        map.put("apply_type", "science");
        packageAwardTaskId(map, params);
        List<PublishAwardTaskDo> taskDoList = awardFlowService.getAwardLastTasksByAwardType(20, EnumAwardType.SCIENCE.getAwrdType());
        map.put("taskList", taskDoList);
        return "act/award/enterprise_doc_award_pro_list";
    }


    /**
     * 获取专业审核的项目列表
     *
     * @return
     */
    @RequestMapping("/review_specialist_pros")
    @ResponseBody
    public List<EnterpriseProjectInfoDo> getSpecialistReviewPros(int majorId) {
        Map<String, Object> params = new HashMap<>();
        params.put("uid", getUserId());
        params.put("majorId", majorId);
        List<EnterpriseProjectInfoDo> list = awardEnterpriseProjectService.getSpecialistReviewPros(params);
        return list;
    }

    @ResponseBody
    @GetMapping("/list")
    public PageUtils list(@RequestParam Map<String, Object> params, ModelMap map) {
        //获取用户的角色不同角色查看不同的信息,超级管理员全部的,发布者全部的,分派的人员查看分派的,企业只能看自己创建的,专家可以查看分派的
        //是否为超级管理员全部的,发布者
        packageAwardTaskId(map, params);
        UserDO user = getUser();
        Long uid = getUserId();
        List<Long> roleIdList = user.getRoleIds();
        if (roleIdList.contains(ROLE_SCIENCE_ASSOCIATION_ID)) {
            params.put("associationUserId", user.getUserId());
        } else if (roleIdList.contains(ROLE_ENTERPRISE_SCIENCE_ID)) {
            //企业用户查看自己创建项目
            params.put("enterpriseUid", uid);
        } else {
            //分派给自己的项目
            params.put("ass_assign_uid", uid);
        }
        getProListParamsByRole(params);
        Query query = new Query(params);
        List<EnterpriseProjectInfoDo> proList = awardEnterpriseProjectService.list(query);
        int total = awardEnterpriseProjectService.count(query);
        PageUtils pageUtils = new PageUtils(proList, total);
        return pageUtils;
    }

    @ResponseBody
    @RequestMapping("/create_pro")
    public R createProject(EnterpriseProjectInfoDo projectInfoDo) {
        long uid = getUserId();
        projectInfoDo.setCreateUid(uid);
        long majorId = projectInfoDo.getMajorId();
        if (majorId == 0 || "选择专业".equals(projectInfoDo.getMajor().trim())) {
            return R.error(100, "请选择专业");
        }
        if (majorId > 0) {
            DictDO dictDO = dictService.get(majorId);
            String name = dictDO.getName();
            String major = projectInfoDo.getMajor();
            if (name.equals(major.trim())) {
                //如果用户填写的专业已经存在了，则清除填写的名称
                projectInfoDo.setMajor("");
            } else {
                //用户填写了新的专业名称,新增到专业列表里面
                DictDO majorDo = new DictDO();
                majorDo.setType("major");
                majorDo.setName(name.trim());
                majorDo.setValue("major_" + RandomStringUtils.randomAlphanumeric(5));
                dictService.save(majorDo);
                //设置新增的专业id
                projectInfoDo.setMajorId(majorDo.getId());
            }
        }

        if (awardEnterpriseProjectService.save(projectInfoDo) > 0) {
            Map<String, Object> result = new HashMap<>();
            result.put("proId", projectInfoDo.getId());
            return R.ok(result);
        }
        return R.error();
    }


    /**
     * 上传项目资料文件
     * 存储目录 年/月/u_用户id
     *
     * @param files
     * @return
     */
    @RequestMapping("/upload_doc")
    @ResponseBody
    public R uploadEnterpriseDoc(String taskId, Integer proId, String fileType,String departId, Long expertUid, @RequestPart("file[]") MultipartFile[] files) {
        List<EnterpriseDocUploadDo> uploadFileList = new ArrayList<>();
        int len = files.length;
        long uid = getUserId();
        String curDate = DateUtils.getCurDate();
        String[] dateArr = curDate.split("-");
        String userFolderPath = dateArr[0] + "/" + dateArr[1] + "/u_" + uid + "/";
        String uploadPath = bootdoConfig.getUploadPath() + userFolderPath;
        File fileFolder = new File(uploadPath);
        if (!fileFolder.exists()) {
            fileFolder.mkdirs();
        }
        String fileUrl = "";
        for (int i = 0; i < len; i++) {
            MultipartFile file = files[i];
            String fileName = file.getOriginalFilename();
            int index = fileName.lastIndexOf(".");
            fileName = fileName.substring(0, index) + "_" + System.currentTimeMillis() + RandomStringUtils.randomAlphanumeric(4) + fileName.substring(index);


            fileUrl = "/files/" + userFolderPath + fileName;
            FileDO sysFile = new FileDO(FileType.fileType(fileName), fileUrl, new Date());
            try {
                FileUtil.uploadFile(file.getBytes(), uploadPath, fileName);
            } catch (Exception e) {
                e.printStackTrace();
                return R.error();
            }
            int rstCount = sysFileService.save(sysFile);
            boolean isExpertSign = "expert_sign".equals(fileType);
            if (rstCount > 0) {
                if("import_check_result".equals(fileType)) {
                    //上传形式审查结果记录
                    String path = uploadPath + fileName;
                    try {
                        FileInputStream inputStream = new FileInputStream(new File(path));
                        Map<String, List<Object[]>> resultMap = ExcelUtils.readCpeCheckResultExcel(inputStream);
                        Map<String, Object> params = new HashMap<>();
                        params.put("taskId", taskId);
                        List<EnterpriseChengguoBaseInfoDO> chengguoList = enterpriseChengguoBaseInfoService.list(params);
                        Map<String, List<EnterpriseChengguoBaseInfoDO>> chengguoMap = chengguoList.stream().collect(Collectors.groupingBy(EnterpriseChengguoBaseInfoDO::getChengguoName));
                        Map<String, EnterpriseChengguoBaseInfoDO> chengguoProCodeMap = new HashMap<>();
                        chengguoList.stream().forEach(c->{
                            String proNum = c.getProResultCode();
                            if(StringUtils.isNotBlank(proNum)) {
                                chengguoProCodeMap.put(proNum, c);
                            }
                        });
                        //获取团队
                        List<EnterpriTeamInfoDO> teamList = enterpriTeamInfoService.list(params);
                        Map<String,List<EnterpriTeamInfoDO>> teamMap = teamList.stream().collect(Collectors.groupingBy(EnterpriTeamInfoDO::getTeamName));
                        Map<String, EnterpriTeamInfoDO> teamProCodeMap = new HashMap<>();
                        teamList.stream().forEach(t->{
                            String proNum = t.getProResultCode();
                            if(StringUtils.isNotBlank(proNum)) {
                                teamProCodeMap.put(proNum, t);
                            }
                        });
                        //根据taskId获取个人
                        List<EnterpriPersonalInfoDO> personList = enterpriPersonalInfoService.list(params);
                        Map<String, List<EnterpriPersonalInfoDO>> personMap = personList.stream().collect(Collectors.groupingBy(EnterpriPersonalInfoDO::getUserName));
                        Map<String, EnterpriPersonalInfoDO> personProCodeMap = new HashMap<>();
                        personList.stream().forEach(p->{
                            String proNum = p.getProResultCode();
                            if(StringUtils.isNotBlank(proNum)) {
                                personProCodeMap.put(proNum, p);
                            }
                        });
                        //保存excel数据
                        List<ImportCheckExcelDataDO> excelDataDOList = new ArrayList<>();
                        for(String key: resultMap.keySet()) {
                            List<Object[]> objList = resultMap.get(key);
                            objList.stream().forEach(cellArr->{
                                ImportCheckExcelDataDO checkExcelDataDO = new ImportCheckExcelDataDO();
                                checkExcelDataDO.setTaskId(taskId);
                                checkExcelDataDO.setOptUid((int) uid);
                                String[] arr = key.split("#");
                                String sheetIndex = arr[0];
                                checkExcelDataDO.setExcelTabName(arr.length > 1 ? arr[1] : key);
                                String awardType = "0".equals(sheetIndex) ? "科技奖" : ("1".equals(sheetIndex) ? "团队" : "个人");

                                checkExcelDataDO.setAwardType(awardType);
                                String excelNum = cellArr[0] == null ? "" : cellArr[0].toString();
                                checkExcelDataDO.setExcelNum(excelNum.trim());
                                checkExcelDataDO.setApplyAccount(cellArr[1] == null ? "" : cellArr[1].toString());
                                //科技：成果名称，团队：团队名称,个人：个人名称
                                checkExcelDataDO.setResultName(cellArr[2] == null ? "" : cellArr[2].toString());
                                String eNum = checkExcelDataDO.getExcelNum();
                                if("0".equals(sheetIndex)){
                                   List<EnterpriseChengguoBaseInfoDO> cList = chengguoMap.get(checkExcelDataDO.getResultName());
                                   if(cList == null) {
                                       EnterpriseChengguoBaseInfoDO c = chengguoProCodeMap.get(eNum);
                                       if(c != null) {
                                           cList = new ArrayList<>();
                                           cList.add(c);
                                       }
                                   }
                                   checkExcelDataDO.setProId(cList != null && cList.size() > 0 && StringUtils.isNotBlank(cList.get(0).getProId()) ? Integer.parseInt(cList.get(0).getProId()) : 0);
                                }else if("1".equals(sheetIndex)) {
                                    List<EnterpriTeamInfoDO> tList = teamMap.get(checkExcelDataDO.getResultName());
                                    if(tList == null) {
                                        EnterpriTeamInfoDO t = teamProCodeMap.get(eNum);
                                        if(t != null) {
                                            tList = new ArrayList<>();
                                            tList.add(t);
                                        }
                                    }
                                   checkExcelDataDO.setProId(tList != null && tList.size() > 0 ? tList.get(0).getProId() : 0);
                                }else if("2".equals(sheetIndex)) {
                                    List<EnterpriPersonalInfoDO> pList = personMap.get(checkExcelDataDO.getResultName());
                                    if(pList == null) {
                                       EnterpriPersonalInfoDO p = personProCodeMap.get(eNum);
                                       if(p != null) {
                                           pList = new ArrayList<>();
                                           pList.add(p);
                                       }
                                    }
                                    checkExcelDataDO.setProId(pList != null && pList.size() > 0 ? pList.get(0).getProId() : 0);
                                }
                                checkExcelDataDO.setApplyEnterprise(cellArr[3] == null ? "" : cellArr[3].toString());
                                checkExcelDataDO.setCheckOpinion(cellArr[4] == null ? "" : cellArr[4].toString());
                                checkExcelDataDO.setCheckStat(cellArr[5] == null ? "" : cellArr[5].toString());

                                String validateRst = "";
                                if(checkExcelDataDO.getCheckStat().equals(EnumApplyEnterpriseProStat.NO_SCORE.getStatShowStr())) {
                                    validateRst = EnumApplyEnterpriseProStat.NO_SCORE.getStat();
                                }else if(checkExcelDataDO.getCheckStat().equals(EnumApplyEnterpriseProStat.SCORE.getStatShowStr())) {
                                    validateRst = EnumApplyEnterpriseProStat.SCORE.getStat();
                                }else if(checkExcelDataDO.getCheckStat().equals(EnumApplyEnterpriseProStat.REJECT.getStatShowStr())) {
                                    validateRst = EnumApplyEnterpriseProStat.REJECT.getStat();
                                }else if(checkExcelDataDO.getCheckStat().equals(EnumApplyEnterpriseProStat.DEFER_SCORE.getStatShowStr())) {
                                    validateRst = EnumApplyEnterpriseProStat.DEFER_SCORE.getStat();
                                }
                                checkExcelDataDO.setValidateResult(validateRst);

                                checkExcelDataDO.setMajor(cellArr[6] == null ? "" : cellArr[6].toString());
                                checkExcelDataDO.setProGroupName(cellArr[7] == null ? "" : cellArr[7].toString());
                                excelDataDOList.add(checkExcelDataDO);
                            });
                        }

                        importCheckExcelDataService.saveBatch(excelDataDOList);

                        //录入形式审查结果
                        importCheckExcelUpdateService.addScienceProValidateResult(taskId);
                        importCheckExcelUpdateService.updateScienceProMajorAndGroup(excelDataDOList);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }else if("science_personal_head".equals(fileType)) {
                    //科技奖个人头像上传
                }else if(StringUtils.isNotBlank(departId)) {
                    SurverEnterpriseSortInfoDO sortInfoDO = new SurverEnterpriseSortInfoDO();
                    sortInfoDO.setFileId(sysFile.getId().intValue());
                    sortInfoDO.setOptUid((int) uid);
                    sortInfoDO.setTaskId(taskId);
                    sortInfoDO.setDepartmentId(Integer.parseInt(departId));
                    sortInfoDO.setStorePath(sysFile.getUrl());
                    surverEnterpriseSortInfoService.save(sortInfoDO);
                }else if (isExpertSign) {
                  expertGroupService.updateExpertSignId(sysFile.getId(), taskId, expertUid);
                } else {
                    EnterpriseDocUploadDo uploadDo = new EnterpriseDocUploadDo();
                    uploadDo.setTaskId(taskId);
                    uploadDo.setFileId(sysFile.getId());
                    uploadDo.setProId(proId + "");
                    uploadDo.setFileType(fileType);
                    uploadDo.setFileName(fileName);
                    uploadDo.setUrl(fileUrl);
                    uploadDo.setCreated(DateUtils.getCurDateTime());
                    sysFileService.saveEnterpriseDoc(uploadDo);

                    if (fileType.equals(EnumProjectType.OIL_PRO_QUALITY.getProType() + "_desc") ||
                            fileType.equals(EnumProjectType.OIL_PRO_QUALITY_GOLD.getProType() + "_desc")) {
                        qualityProSituationService.removeByProId(proId);
                        OilQualityProSituationDO qualityProSituationDO = new OilQualityProSituationDO();
                        qualityProSituationDO.setProId(proId);
                        qualityProSituationDO.setFileId(sysFile.getId());
                        qualityProSituationDO.setOptUid(getUserId());
                        qualityProSituationDO.setUrl(fileUrl);
                        qualityProSituationDO.setTaskId(taskId);
                        qualityProSituationService.save(qualityProSituationDO);
                    }
                    if (fileType.startsWith(EnumProjectType.OIL_PRO_QUALITY.getProType() + "_confirm") ||
                            fileType.startsWith(EnumProjectType.OIL_PRO_QUALITY_GOLD.getProType() + "_confirm") ||
                            fileType.startsWith(EnumProjectType.OIL_PRO_INSTALL.getProType() + "_confirm")
                    ) {
                        OilQualityConfirmFileDO confirmFileDO = new OilQualityConfirmFileDO();
                        confirmFileDO.setFileId(sysFile.getId());
                        String[] arr = fileType.split("-");
                        confirmFileDO.setFileType(arr[arr.length - 1]);
                        confirmFileDO.setProId(proId);
                        confirmFileDO.setOptUid(getUserId());
                        confirmFileDO.setTaskId(taskId);
                        confirmFileDO.setUrl(fileUrl);
                        qualityConfirmFileService.save(confirmFileDO);
                    }

                    uploadFileList.add(uploadDo);
                }
            }
        }
        R rst = R.ok();
        rst.put("fileType", fileType);
        rst.put("files", uploadFileList);
        rst.put("fileSize", uploadFileList.size());
        rst.put("fileUrl", fileUrl);
        return rst;
    }


    @ResponseBody
    @RequestMapping("/to_getworker")
    public R toAssignProWorer(@RequestParam Map<String, Object> params, ModelMap map) {
        params.put("roleId", "65");
        List<UserDO> assWorkers = userService.list(params);
        map.put("assWorkers", assWorkers);

        R rst = R.ok();
        rst.put("allWorkers", assWorkers);
        return rst;
    }


    @ResponseBody
    @RequestMapping("/manage_professiondd")
    public String getDataByTaskIdType(@RequestParam Map<String, Object> params, ModelMap map) {
//         List<EnterpriseProjectInfoDo> list =  awardEnterpriseProjectService.getAllProList(params);
//        String procInsId = taskDo.getProcInsId();
//        boolean isAssign = true;
//        if(StringUtils.isNotBlank(procInsId)) {
//            List<Task> taskList = taskService.createTaskQuery()
//                    .processInstanceId(procInsId).list();
//            Task task = taskList.size() > 0 ? taskList.get(0) : null;
//            if(task != null) {
//                String defKey = task.getTaskDefinitionKey();
//                isAssign = defKey.equals("distribute_pros");
//            }
//        }
//        map.put("isAssign",isAssign);
//        map.put("publishTaskId",publishTaskId);
//        params.put("roleId","65");
//        if(isAssign) {
//            //如果是分派阶段则可查询进行分派
//            List<UserDO> assWorkers = userService.list(params);
//            map.put("assWorkers", assWorkers);
//        }
//        // 企业申请的数据
//        map.put("assPros", list);
//
//        List<String> majo = new ArrayList<>();
//
//        for(EnterpriseProjectInfoDo workerUid:list){
//            if (workerUid.getMajor() != null && workerUid.getMajor().length() > 0)
//                if (!majo.contains(workerUid.getMajor())){
//                    majo.add(workerUid.getMajor());
//                }
//        }
//        map.put("profession", majo);

        return "act/award/association_profession_manage";
    }


    /**
     * 待分配专业 分配给外聘人员
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/manage_profession/external")
    public R toManageProfessionExternal(@RequestParam Map<String, Object> params) {
        params.put("roleId", ROLE_SCIENCE_EXTERNAL_EMPLOYMENT_ID);
        List<EnterpriseProjectInfoDo> list = awardEnterpriseProjectService.getAllProList(params);

        R rst = R.ok();
        rst.put("list", list);
        return rst;

    }


    @GetMapping("/pro_tree")
    @ResponseBody
    String tree(@RequestParam Map<String, Object> params) {
        getProListParamsByRole(params);
        Tree<EnterpriseProjectInfoDo> tree = awardEnterpriseProjectService.getTree(params);
        return JSONUtils.gson.toJson(tree);
    }


}
