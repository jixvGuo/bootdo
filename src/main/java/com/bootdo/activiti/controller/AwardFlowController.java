package com.bootdo.activiti.controller;

import com.bootdo.activiti.domain.AwardUpDocDo;
import com.bootdo.activiti.domain.EnterpriseDocUploadDo;
import com.bootdo.activiti.domain.EnterpriseProjectInfoDo;
import com.bootdo.activiti.domain.PublishAwardTaskDo;
import com.bootdo.activiti.service.AwardEnterpriseProjectService;
import com.bootdo.activiti.service.AwardFlowService;
import com.bootdo.activiti.service.AwardPublishTaskService;
import com.bootdo.activiti.utils.ActivitiUtils;
import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.config.Constant;
import com.bootdo.common.controller.BaseScienceTechnologyController;
import com.bootdo.common.domain.DictDO;
import com.bootdo.common.domain.FileDO;
import com.bootdo.common.service.DictService;
import com.bootdo.common.service.FileService;
import com.bootdo.common.utils.*;
import com.bootdo.cpe.controller.science_progress.ScienceController;
import com.bootdo.cpe.controller.science_progress.ScienceTechApplyController;
import com.bootdo.cpe.domain.EnumAwardType;
import com.bootdo.cpe.domain.EnumProjectType;
import com.bootdo.cpe.domain.RoleAwardParamData;
import com.bootdo.cpe.timer.PublishTaskTimer;
import com.bootdo.cpe.utils.CpeException;
import com.bootdo.cpe.utils.ExceptionEnum;
import com.bootdo.system.domain.AwardDo;
import com.bootdo.system.domain.EnterpriPersonalInfoDO;
import com.bootdo.system.domain.RoleDO;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.*;
import org.activiti.engine.task.Task;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.management.relation.Role;
import java.util.*;

import static com.bootdo.common.config.Constant.*;

@Controller
@RequestMapping("/award_flow")
public class AwardFlowController extends BaseScienceTechnologyController {
    private Logger logger = LoggerFactory.getLogger(AwardFlowController.class);
    String prefix = "act/award";
    @Autowired
    private AwardFlowService awardFlowService;
    @Autowired
    private AwardPublishTaskService awardPublishTaskService;
    @Autowired
    private UserService userService;
    @Autowired
    ActivitiUtils activitiUtils;
    @Autowired
    private DictService dictService;
    @Autowired
    private AwardService awardService;
    @Autowired
    private FileService fileService;
    @Autowired
    private BootdoConfig bootdoConfig;
    @Autowired
    private FileService sysFileService;
    @Autowired
    private AwardEnterpriseProjectService awardEnterpriseProjectService;
    @Autowired
    private EnterpriseChengguoBaseInfoService enterpriseChengguoBaseInfoService;
    @Autowired
    private PublishTaskTimer publishTaskTimer;
    @Autowired
    private EnterpriPersonalInfoService enterpriPersonalInfoService;
    @Autowired
    RoleService roleService;
    @Autowired
    private ScienceController scienceController;

    @GetMapping()
    String Salary() {
        return "activiti/award/award";
    }

    @RequestMapping("/view_up_doc_files")
    public String toViewDocFiles() {
        return prefix + "/view_up_doc_files";

    }

    @RequestMapping("/specialist_leader_validate")
    public String toSpecialistLeaderValidate() {
        return prefix + "/specialist_leader_validate";
    }

    @ResponseBody
    @GetMapping("/list")
    public PageUtils list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);
        List<AwardUpDocDo> awardValidateList = awardFlowService.list(query);
        int total = awardFlowService.count(query);
        PageUtils pageUtils = new PageUtils(awardValidateList, total);
        return pageUtils;
    }


    @RequestMapping("/publish_tasks")
    @RequiresPermissions("act:award:publish_tasks")
    public String toAdminPublishAwardTasks(ModelMap map) {
        UserDO user = getUser();
        List<Long> roleIdList = user.getRoleIds();
        //是否协会联系人
        boolean isAssociationRole = RoleAwardParamData.isAssociationRole(roleIdList);
        map.put("isAssociationRole", isAssociationRole);
        return prefix + "/award_task_list";
    }


    /**
     * @param params
     * @return
     */

    @ResponseBody
    @GetMapping("/list_publish_tasks")
    public PageUtils listPublishAwardTask(@RequestParam Map<String, Object> params) throws CpeException {
        Query query = new Query(params);
        UserDO user = getUser();
        List<Long> roleIdList = user.getRoleIds();
        Object awardIdObj = params.get("awardId");

        RoleAwardParamData paramData = RoleAwardParamData.getInstance(roleIdList, awardIdObj == null ? "" : awardIdObj.toString());

        query.put("isOutWorker", paramData.getIsOutWorker());
        query.put("isSpecialist", paramData.getIsSpecialist());
        query.put("specialUid", user.getUserId());

        // ✅ 统一使用 paramData.getAwardId()
        query.put("awardTypeId", paramData.getAwardId());

        // 其他业务逻辑
        List<Integer> menuIds = new ArrayList<>();
        menuIds.add(121);
        boolean isAssignPro = awardPublishTaskService.isAuthByMenuIds(user.getUserId(), menuIds);

        List<PublishAwardTaskDo> publishAwardTaskDoList = awardPublishTaskService.list(query);
        if(paramData.getIsSpecialist()) {
            publishAwardTaskDoList.forEach(t -> t.setScore(false));
        }
        if(!isAssignPro) {
            publishAwardTaskDoList.forEach(p -> p.setAssign(isAssignPro));
        }

        int total = awardPublishTaskService.count(query);
        return new PageUtils(publishAwardTaskDoList, total);
    }
//    public PageUtils listPublishAwardTask(@RequestParam Map<String, Object> params) throws CpeException {
//        Query query = new Query(params);
//        UserDO user = getUser();
//        List<Long> roleIdList = user.getRoleIds();
//        Object awardIdObj = params.get("awardId");
//
//        //添加调试日志
//        System.out.println(">>> list_publish_tasks - 前端传入 awardId: " + awardIdObj);
//        System.out.println(">>> list_publish_tasks - 用户角色列表: " + roleIdList);
//
//        RoleAwardParamData paramData = RoleAwardParamData.getInstance(roleIdList, awardIdObj == null ? "" : awardIdObj.toString());
//
//        //添加调试日志
//        System.out.println(">>> list_publish_tasks - paramData.awardId: " + paramData.getAwardId());
//        System.out.println(">>> list_publish_tasks - paramData.roleId: " + paramData.getRoleId());
//        System.out.println(">>> list_publish_tasks - paramData.isAssociation: " + paramData.getIsAssociation());
//        System.out.println(">>> list_publish_tasks - paramData.toString: " + paramData.toString());
//
//        boolean isAssociation = paramData.getIsAssociation();
//        query.put("isOutWorker", paramData.getIsOutWorker());
//        query.put("isSpecialist", paramData.getIsSpecialist());
//        query.put("specialUid", user.getUserId());
//
//        if (isAssociation) {
//            //协会工作人员，只能查看自己创建的任务
//            query.put("associationUserId", user.getUserId());
//            int awardTypeId = paramData.getAwardId();
//            query.put("awardTypeId", awardTypeId);
//        }else {
//            int awardTypeId = EnumAwardType.getAwardTypeIdByRoleList(roleIdList);
//            query.put("awardTypeId", awardTypeId);
//        }
//
//        List<Integer> menuIds = new ArrayList<>();
//        menuIds.add(121);
//        boolean isAssignPro = awardPublishTaskService.isAuthByMenuIds(user.getUserId(), menuIds);
//
//        List<PublishAwardTaskDo> publishAwardTaskDoList = awardPublishTaskService.list(query);
//        if(paramData.getIsSpecialist()) {
//            //是专家需要取消查看分数的权限
//            publishAwardTaskDoList.stream().forEach(t->{
//                t.setScore(false);
//            });
//        }
//        if(!isAssignPro) {
//            publishAwardTaskDoList.stream().forEach(p -> {
//               p.setAssign(isAssignPro);
//            });
//        }
//
//        int total = awardPublishTaskService.count(query);
//        PageUtils pageUtils = new PageUtils(publishAwardTaskDoList, total);
//        return pageUtils;
//    }

    /***
     *
     * @param map
     * @return
     */
    @RequiresPermissions("act:award:publish_award_task")
    @RequestMapping("/publish_award_task")
    public String associationPublishAwardTask(String awardId, ModelMap map) throws CpeException {

        //获取协会工作人员
        Map<String, Object> params = new HashMap<>();
        //根据用户登录角色进行查询
        List<Long> roleList = getUser().getRoleIds();

        RoleAwardParamData paramData = RoleAwardParamData.getInstance(roleList, awardId);
        String type = paramData.getType();
        long roleId = paramData.getRoleId();
        int awardTypeId = paramData.getAwardId();

        map.put("type", type);
        map.put("awardTypeId", awardTypeId);
        params.put("roleId", roleId);

        List<UserDO> associationUserList = userService.list(params);
        List<AwardDo> awardDoList = awardService.list();
        for (AwardDo award : awardDoList) {
            Map<String, Object> pa = new HashMap<>();
            pa.put("id", award.getId());
            List<AwardUpDocDo> list = awardFlowService.list(pa);
            if (list.size() > 0) {
                AwardUpDocDo upDocDo = list.get(0);
                award.setAwardUpDocDo(upDocDo);
            }
        }
        List<DictDO> majorList = dictService.listByType("major");
        map.put("majorList", majorList);
        map.put("awards", awardDoList);
        map.put("associationUsers", associationUserList);


        System.out.println("=======================================" + type);
        return prefix + "/publish_award_task_ass";
    }

    /***
     * 发布任务
     * @param awardTaskDo
     * @return
     */
    @ResponseBody
    @RequestMapping("/publish")
    public R publishAwardTask(PublishAwardTaskDo awardTaskDo) {
        if(StringUtils.isBlank(awardTaskDo.getTaskStartTime()) || StringUtils.isBlank(awardTaskDo.getTaskEndTime())
        || StringUtils.isBlank(awardTaskDo.getCheckStartTime()) || StringUtils.isBlank(awardTaskDo.getCheckEndTime())){
            return R.error("请填写时间限制");
        }
        awardPublishTaskService.save(awardTaskDo);
        String taskId = awardTaskDo.getId();
        List<Integer> upDocId = awardTaskDo.getUpDocId();
        if (upDocId != null && upDocId.size() > 0) {
            //更新文件的关联的任务id
            for (int upId : upDocId) {
                EnterpriseDocUploadDo enterpriseDocUploadDo = new EnterpriseDocUploadDo();
                enterpriseDocUploadDo.setTaskId(taskId);
                enterpriseDocUploadDo.setId(upId);
                fileService.updateEnterpriseDoc(enterpriseDocUploadDo);
            }
        }

        publishTaskTimer.getAllNoOverTask();

        Map code = new HashMap();
        code.put("rowId", taskId);

        return R.ok(code);
    }


    /***
     * 文件上传
     * @param awardTaskDo
     * @param file
     * @return
     */
    @ResponseBody
    @RequestMapping("/publish_uploadfile")
    public R publishFileAwardTask(PublishAwardTaskDo awardTaskDo, @RequestPart(value = "file", required = false) MultipartFile file) {
        long fileID = handlePublishAwardTaskUploadFiles(file);

        return R.ok(fileID + "");
    }


    /***
     * 文件上传
     * @param awardTaskDo
     * @return
     */
    @ResponseBody
    @RequestMapping("/publish_uploadheadfile")
    public R publishFile(PublishAwardTaskDo awardTaskDo, @RequestPart(value = "file", required = false) MultipartFile file) {
        long fileID = handlePublishAwardTaskUploadFiles(file);
        FileDO fileinfo = fileService.get(fileID);
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("file", fileinfo);
        return R.ok(hashMap);
    }


    @RequestMapping("/publish_award_task/{tid}/edit")
    public String associationPublishAwardTaskEdit(@PathVariable("tid") String tid, ModelMap map) {
        //获取协会工作人员
        Map<String, Object> params = new HashMap<>();
        params.put("roleId", 60);
        List<UserDO> associationUserList = userService.list(params);
        PublishAwardTaskDo taskDo = awardPublishTaskService.get(tid);
        map.put("associationUsers", associationUserList);
        map.put("awardTask", taskDo);
        associationUserList.stream().forEach(a -> {
            String[] uids = taskDo.getAssociationUserId().split(",");
            final boolean[] isFlg = {false};
            Arrays.stream(uids).forEach(uid -> {
                isFlg[0] = uid.equals(a.getUserId().toString());
            });
            if (isFlg[0]) {
                a.setFlg("true");
            } else {
                a.setFlg("false");
            }
        });
        return prefix + "/publish_award_task_edit";
    }

    /**
     * 去修改发布的奖项任务 时间
     *
     * @param publishTaskId
     * @param map
     * @return
     */
    @RequestMapping("/to_publish_task_edit")
    @RequiresPermissions("act:award:publish_award_task_edit")
    public String toAPublishAwardTask(String publishTaskId, String awardId, ModelMap map) throws CpeException {
        PublishAwardTaskDo awardTaskDo = awardFlowService.getAwardTaskById(publishTaskId);
        //去掉时间末尾的.0

        String startTime = awardTaskDo.getTaskStartTime();
        if (startTime != null && !startTime.isEmpty()) {

            awardTaskDo.setTaskStartTime(startTime.replaceAll("\\.[0-9]*$", ""));
        }

        String endTime = awardTaskDo.getTaskEndTime();

        if (endTime != null && !endTime.isEmpty()) {

            awardTaskDo.setTaskEndTime(endTime.replaceAll("\\.[0-9]*$", ""));
        }


        Map<String, Object> params = new HashMap<>();
        //根据用户登录角色进行查询
        List<Long> roleList = getUser().getRoleIds();
        RoleAwardParamData paramData = RoleAwardParamData.getInstance(roleList, awardId);
        String type = paramData.getType();
        long roleId = paramData.getRoleId();
        int awardTypeId = paramData.getAwardId();

        map.put("type", type);
        map.put("awardTypeId", awardTypeId);
        params.put("roleId", roleId);
        List<UserDO> associationUserList = userService.list(params);
        map.put("associationUsers", associationUserList);

        map.put("awardTask", awardTaskDo);
        associationPublishAwardTask(awardId, map);
        System.out.println(JSONUtils.beanToJson(awardTaskDo) + "数据回显");

        Map<String, Object> newparams = new HashMap<>();
        newparams.put("taskId", publishTaskId);
        newparams.put("fileType", "task_file");

        List<EnterpriseDocUploadDo> docUploadDoList = fileService.listTaskDocInfo(newparams);
        map.put("docUploadDoList", docUploadDoList);


        return prefix + "/publish_award_task_ass_edit";
    }

    /**
     * 去查看发布的奖项任务
     *
     * @param publishTaskId
     * @param map
     * @return
     */
    @RequestMapping("/to_publish_task_watch")
    @RequiresPermissions("act:award:publish_award_task_wach")
    public String toWathchAwardTask(String publishTaskId, String awardId, ModelMap map) throws CpeException {
        PublishAwardTaskDo awardTaskDo = awardFlowService.getAwardTaskById(publishTaskId);
        //去掉时间末尾的.0
        String startTime = awardTaskDo.getTaskStartTime();
        if (startTime != null && !startTime.isEmpty()) {
            awardTaskDo.setTaskStartTime(startTime.replaceAll("\\.[0-9]*$", ""));
        }
        String endTime = awardTaskDo.getTaskEndTime();

        if (endTime != null && !endTime.isEmpty()) {
            awardTaskDo.setTaskEndTime(endTime.replaceAll("\\.[0-9]*$", ""));
        }


        Map<String, Object> params = new HashMap<>();
        params.put("taskId", publishTaskId);
        params.put("fileType", "task_file");

        List<EnterpriseDocUploadDo> docUploadDoList = fileService.listTaskDocInfo(params);
        map.put("docUploadDoList", docUploadDoList);


        map.put("awardTask", awardTaskDo);
        associationPublishAwardTask(awardId, map);

        return prefix + "/publish_award_task_ass_watch";
    }

    /**
     * 修改发布的奖项任务
     *
     * @param awardTaskDo
     * @return
     */
    @ResponseBody
    @RequestMapping("/publish_update")
    public R publishAwardTaskUpdate(PublishAwardTaskDo awardTaskDo, MultipartFile file) throws CpeException {
        String awardId = awardTaskDo.getAwardId();
        List<Long> roleList = getUser().getRoleIds();
        //根据角色判断当前的奖项类型id
        RoleAwardParamData paramData = RoleAwardParamData.getInstance(roleList, awardId);
        int awardTypeId = paramData.getAwardId();
        awardTaskDo.setAwardId(awardTypeId + "");

        handlePublishAwardTaskUploadFiles(file, awardTaskDo);
        //发布消息
        awardPublishTaskService.update(awardTaskDo);
        publishTaskTimer.getAllNoOverTask();

        String taskId = awardTaskDo.getId();
        List<Integer> upDocId = awardTaskDo.getUpDocId();
        if (upDocId != null && upDocId.size() > 0) {
            fileService.cleanTaskEnterpriseDoc(taskId);
            //更新文件的关联的任务id
            for (int upId : upDocId) {
                EnterpriseDocUploadDo enterpriseDocUploadDo = new EnterpriseDocUploadDo();
                enterpriseDocUploadDo.setTaskId(taskId);
                enterpriseDocUploadDo.setId(upId);
                fileService.updateEnterpriseDoc(enterpriseDocUploadDo);
            }
        }


        return R.ok();
    }

    private void handlePublishAwardTaskUploadFiles(MultipartFile file, PublishAwardTaskDo awardTaskDo) {
        if (file != null) {
            System.out.println(file.getOriginalFilename());
            String fileName = file.getOriginalFilename();
            fileName = FileUtil.renameToUUID(fileName);
            FileDO sysFile = new FileDO(FileType.fileType(fileName), "/files/" + fileName, new Date());
            try {
                FileUtil.uploadFile(file.getBytes(), bootdoConfig.getUploadPath(), fileName);
            } catch (Exception e) {
                logger.error("publish task upload file error {}", e.getMessage());
            }
            int rstCount = sysFileService.save(sysFile);
            if (rstCount > 0) {
//                long fileId = sysFile.getId();
//                awardTaskDo.setFileId(fileId);
            }
        }
    }

    /***
     * 上传id
     * @param file 需要上传的文件
     * @return
     */
    private long handlePublishAwardTaskUploadFiles(MultipartFile file) {
        long fileId = 0;
        if (file != null) {
            System.out.println(file.getOriginalFilename());
            String fileName = file.getOriginalFilename();
            fileName = FileUtil.renameToUUID(fileName);
            FileDO sysFile = new FileDO(FileType.fileType(fileName), "/files/" + fileName, new Date());
            try {
                FileUtil.uploadFile(file.getBytes(), bootdoConfig.getUploadPath(), fileName);
            } catch (Exception e) {
                logger.error("publish task upload file error {}", e.getMessage());
            }
            int rstCount = sysFileService.save(sysFile);


            if (rstCount > 0) {
                fileId = sysFile.getId();

            }
        }
        return fileId;
    }


    @RequestMapping("/pro_list/{taskId}")
    public String createProList(@PathVariable("taskId") String taskId, ModelMap map) {
        map.put("taskId", taskId);
        return prefix + "/enterprise_doc_award_apply";
    }

    @RequestMapping("/pro_list_show/{taskId}")
    @ResponseBody
    public List<AwardUpDocDo> getCreateProList(@PathVariable("taskId") String taskId) {
        Map<String, Object> params = new HashMap<>();
        params.put("publishTaskId", taskId);
        return awardFlowService.list(params);
    }


    /**
     * 去创建申请项目
     * 企业可以在一个任务下创建多个项目
     *
     * @return
     */
    @GetMapping("/form/{taskId}")
    String add(@PathVariable("taskId") String taskId, ModelMap map) {
        map.put("publishTaskId", taskId);
        PublishAwardTaskDo taskDo = awardPublishTaskService.get(taskId);
        List<DictDO> majorList = dictService.listByIds(taskDo.getMajorIds());
        map.put("majorList", majorList);
        return "act/award/enterprise_doc_award_apply";
    }

    /**
     * 去创建申请项目 科技奖团队进步
     * 企业可以在一个任务下创建多个项目
     *
     * @param map
     * @return
     */
    @RequestMapping("/apply_team")
    @RequiresPermissions("act:award:apply_team")
    public String toAdminTeamPublishAwardTasks(ModelMap map, @RequestParam Map<String, Object> params) {
        applyParams(map, params);
        //先进团队成果申报
        Object taskIdObj = map.get("taskId");
        String taskId = taskIdObj == null ? "" : taskIdObj.toString();
        if (StringUtils.isBlank(taskId)) {
            return "act/award/no_science_task_tip";
        }

        map.put("couldSubmitReview", true);
        return prefix + "/chengguo_team/index";
    }

    /**
     * 去创建申请项目 科技奖个人进步
     * 企业可以在一个任务下创建多个项目
     *
     * @param map
     * @return
     */
    @RequestMapping("/apply_personal")
    @RequiresPermissions("act:award:apply_personal")
    public String toAdminPersonalPublishAwardTasks(@RequestParam Map<String, Object> params, ModelMap map) {
        applyParams(map, params);
        packageAwardTaskId(map, params);
        map.put("apply_pro_type", "personal");
        String taskId = (String) map.get("taskId");
        if (StringUtils.isBlank(taskId)) {
            return "act/award/no_science_task_tip";
        }
        map.put("couldSubmitReview", true);
        EnterpriseProjectInfoDo projectInfoDo = null;
        int proId = 0;
        if (proId == 0) {
            long uid = getUserId();
            projectInfoDo = awardEnterpriseProjectService.initOneProject(taskId, uid, EnumProjectType.SCIENCE_PERSONAL);
            proId = projectInfoDo.getId();
        } else {
            projectInfoDo = awardEnterpriseProjectService.get(proId + "");
        }
        map.put("projectInfoDo", projectInfoDo);
        map.put("proId", proId);


        return prefix + "/chengguo_personal/index";
    }

    /**
     * 去创建申请项目 科技进步
     * 企业可以在一个任务下创建多个项目
     *
     * @return
     */
    @GetMapping("/chengguo_science")
    @RequiresPermissions("act:award:chengguo_science")
    String chengguoScienceToCreate(@RequestParam Map<String, Object> params, ModelMap map) {
        applyParams(map, params);
        packageAwardTaskId(map, params);
        String taskId = params.get("taskId").toString();
        long uid = getUserId();
        //项目id
        int proId = 0;
        if (proId == 0) {
            EnterpriseProjectInfoDo projectInfoDo = awardEnterpriseProjectService.initOneProject(taskId, uid, EnumProjectType.SCIENCE_PROGRESS);
            proId = projectInfoDo.getId();
        }
        map.put("proId", proId);

        return "act/award/chengguo/index";
    }

    /**
     * 去创建申请项目
     * 企业可以在一个任务下创建多个项目
     * @return
     */
    /*@GetMapping("/chengguo/{taskId}")
    String chengguoEdit(@PathVariable("taskId") String taskId,ModelMap map) {
        map.put("publishTaskId",taskId);
        EnterpriseProjectInfoDo projectInfoDo = new EnterpriseProjectInfoDo();
        long uid = getUserId();
        projectInfoDo.setCreateUid(uid);
        projectInfoDo.setPublishTaskId(taskId);
        awardEnterpriseProjectService.save(projectInfoDo);
        map.put("proId",projectInfoDo.getId());
        return "act/award/chengguo/index";
    }*/


    /**
     * 创建项目
     *
     * @param map
     * @return
     */
    @RequestMapping("/create_pro")
    @ResponseBody
    R enterpriseCreatePro(AwardUpDocDo award, ModelMap map) {
        PublishAwardTaskDo taskDo = awardPublishTaskService.get(award.getPublishTaskId());
        award.setProcInsId(taskDo.getProcInsId());
        award.setContent(taskDo.getTaskName());
        award.setCreateDate(new Date());
        award.setUpdateDate(new Date());
        award.setCreateBy(ShiroUtils.getUserId().toString());
        award.setUpdateBy(ShiroUtils.getUserId().toString());
        award.setDelFlag("1");
        if (awardFlowService.save(award) > 0) {
            R r = R.ok();
            r.put("proId", award.getId());
            return r;
        }
        return R.error();
    }

    /**
     * 去上传奖项相关的资料
     *
     * @return
     */
    @RequestMapping("/to_upload_doc/{proId}")
    public String toEnterpriseUpDoc(@PathVariable("proId") String proId, String procInsId, ModelMap map) {
        map.put("proId", proId);
        Map<String, Object> params = new HashMap<>();
        params.put("proId", proId);
        List<EnterpriseDocUploadDo> docUploadDoList = fileService.listUploadEnterpriseDocs(params);
        map.put("docUploadDoList", docUploadDoList);
        map.put("fileSize", docUploadDoList.size());
        map.put("procInsId", procInsId);
        EnterpriseProjectInfoDo projectInfoDo = awardEnterpriseProjectService.get(proId);
        String assReviewRst = projectInfoDo.getAssociationReviewRst();
        map.put("associationReviewRst", assReviewRst);
        PublishAwardTaskDo taskDo = awardPublishTaskService.getByProId(proId);
        if (taskDo != null) {
            map.put("chengguo", taskDo.getChengguo());
            //项目创建者所属的企业名称
            map.put("enterpriseName", projectInfoDo.getEnterpriseName());
            //项目主要完成人
            map.put("person", taskDo.getPerson());
            map.put("tuandui", taskDo.getTuandui());
            map.put("advancedIndividual", taskDo.getAdvancedIndividual());
            map.put("taskId", taskDo.getId());
            map.put("procInsId", taskDo.getProcInsId());
            map.put("actRunTaskId", taskDo.getActRunTaskId());
        }
        map.put("projectInfoDo", projectInfoDo);
        //是否可以显示提交审核按钮，只有是创建项目本人才有提交审核的权限
        Long userId = getUserId();
        if (userId != null && projectInfoDo.getCreateUid().longValue() == userId.longValue()) {
            map.put("couldSubmitReview", true);
        } else {
            map.put("couldSubmitReview", false);
        }

        if (StringUtils.isNotBlank(assReviewRst) && !"upload".equals(assReviewRst)) {
            //存在审核结果的
            map.put("ass_validate_pro", 0);
        }
        if (taskDo == null) {
            return "";
        }
        //去上传
        return taskDo.getUpDocUrl();
    }

    /**
     * 上传的页面
     *
     * @return
     */
    @RequestMapping("/to_uploadsmall")
    public String toUpload(@RequestParam Map<String, Object> params, String fileType, String departId, ModelMap map) {
        packageAwardTaskId(map, params);
        if (StringUtils.isBlank(fileType)) {
            fileType = "enterprise_doc";
        }
        map.put("fileType", fileType);
        map.put("departId", departId);
        return prefix + "/enterprise_doc_uploadsmall";
    }


    /**
     * 上传个人资料的页面
     *
     * @return
     */
    @RequestMapping("/to_uploadpersonal_small")
    public String toUploadPersonal(String proId, String fileType, ModelMap map) {
        map.put("proId", proId);
        if (StringUtils.isBlank(fileType)) {
            fileType = "enterprise_doc";
        }
        map.put("fileType", fileType);
        return prefix +"/chengguo_personal/enterprise_person_doc_uploadsmall";
    }


    /**
     * 任务资料上传
     *
     * @return
     */
    @RequestMapping("/to_uploadtask_small")
    public String toUploadTaskFile(String taskId,String proId, String fileType, ModelMap map) {
        map.put("proId", proId);
        map.put("taskId",taskId);
        if (StringUtils.isBlank(fileType)) {
            fileType = "enterprise_doc";
        }
        map.put("fileType", fileType);
        return prefix +"/enterprise_task_doc_uploadsmall";
    }






    @RequestMapping("/getAllFils")
    @ResponseBody
    public R loadAllTaskFiles(String rid) {

        Map param = new HashMap();
        param.put("proId", "88888888");
        param.put("fileType", "task_file");
        Map allFils = new HashMap();
        List<EnterpriseDocUploadDo> needChange = fileService.listUploadTaskDocs(param);
        for (int a = 0; a < needChange.size(); a++) {
            EnterpriseDocUploadDo obj = needChange.get(a);
            obj.setProId(rid);

            fileService.updateEnterpriseDoc(obj);
        }

        return R.ok(allFils);
    }


    /**
     * 提交给协会工作人员审核
     *
     * @param proId
     * @return
     */
    @RequestMapping("/up_association_review")
    @ResponseBody
    public R toAssociationReview(String proId) {
        awardFlowService.enterpriseScienceCommitToReview(proId);
        return R.ok();
    }

    /**
     * 协会工作人员形式审查
     *
     * @param id
     * @param procInsId
     * @param map
     * @return
     */
    @RequiresPermissions("act:award:ass_validate_pro")
    @RequestMapping("/ass_validate_pro")
    public String toValidateAssociation(String id, String procInsId, String pageSource, ModelMap map, @RequestParam Map<String, Object> params) {
       return executeToValidateAssociation(id, procInsId, pageSource, map, params);
    }

    public String executeToValidateAssociation(String id, String procInsId, String pageSource, ModelMap map, @RequestParam Map<String, Object> params) {
        //标记是形式审查的页面
        map.put("ass_validate_pro", 1);
        map.put("pageSource", pageSource);
        map.put("procInsId", procInsId);
        //根据proID查看是否为个人奖项
        params.put("proId", id);
        List<EnterpriPersonalInfoDO> personList = enterpriPersonalInfoService.list(params);
        if (personList.size() > 0) {
            EnterpriPersonalInfoDO personalInfoDO = personList.get(0);
            //个人进步奖
            map.put("applyId", personalInfoDO.getApplyId());
            map.put("taskId", personalInfoDO.getTaskId());
            int proId = personalInfoDO.getProId();
            EnterpriseProjectInfoDo projectInfoDo = awardEnterpriseProjectService.get(proId + "");
            map.put("projectInfoDo", projectInfoDo);
            map.put("proId", proId);
            return prefix + "/chengguo_personal/index";
        }
        return scienceController.toUpateChengguoInfo(map,params);
    }

        /**
         * 协会工作人员的形式检查列表,菜单形式审查
         *
         * @return
         */
    @RequestMapping("/association_validate_pros")
    public String toShowAssociationVilateProList(String role, String act, ModelMap map) {
        map.put("pageSource", Constant.PAGE_SOURCE_ENTERPRISE_VALIDATE_MENU);
        //形式审查的菜单只要查出来就可以显示审查按钮
        map.put("isReview", true);
        map.put("role", role);
        map.put("act", act);
        return prefix + "/enterprise_doc_award_pro_list";
    }

    @RequestMapping("/to_specialist_validate")
    public String toSpecialistValidate() {
        return prefix + "/specialist_validate_form";
    }

    //任务拾取
    //我们设置的任务处理人员暂时都只是候选人 ,并不是实际处理人,必须经过任务拾取的过程来确定谁来处理任务 (task assignee)
    //任务拾取的过程,就是给执行任务表指定assginee字段值的过程
    @RequestMapping("/claim_task")
    public String claimTask() {
        //TODO 实际校审的人
        String taskId = "157502";
        String userId = "胡八";
        activitiUtils.claimTask(taskId, userId);
        return "";
        //任务拾取以后, 可以回退给组
        //processEngine.getTaskService().setAssignee(taskId, null);
        //任务拾取以后,可以转给别人去处理(别人可以是组成员也可以不是)
        //processEngine.getTaskService().claim(taskId, "xiaoxi");
    }

    /**
     * 选择专家
     *
     * @return
     */
    @RequestMapping("/sel_specialist")
    public String toSelSpecialist() {
        //TODO 通选择的页面
        return "act/award/association_review_doc";
    }


    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/update")
    public R update(EnterpriseProjectInfoDo proInfo) {
        if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
            return R.error(1, "演示系统不允许修改,完整体验请部署程序");
        }
        String proId = proInfo.getId() + "";
        Long taskAssignee = awardEnterpriseProjectService.getAssignUserIdByProId(proId + "");
        if (taskAssignee == null) {
            return R.error(2, "此项目还没有分派给协会工作人员");
        }
        String actRunTaskId = proInfo.getActRunTaskId();
        Task task = null;
        if (StringUtils.isNotBlank(actRunTaskId)) {
            task = activitiUtils.getTaskByTaskId(actRunTaskId);
        } else {
            List<Task> taskList = activitiUtils.getTaskByProInsId(proInfo.getProcInsId(), taskAssignee.toString());
            if (taskList.size() > 0) {
                //取出一个任务进行变更处理
                task = taskList.get(0);
            }
        }
        if (task != null) {
            String taskId = task.getId();
            //1.与项目绑定 2.更新此项目的审核结果
            proInfo.setActRunTaskId(taskId);
        } else {
            return R.error(3, "没有分派的任务");
        }

        /*String taskKey = task.getTaskDefinitionKey();
        if ("specailist_scroing".equals(taskKey)) {
            //专家修改分数
            proInfo.setProScore(proInfo.getProScore());
        } else if ("specialist_group_leader_review".equals(taskKey)) {
            //专家组长修改分数
            proInfo.setProScore(proInfo.getProScore());
        } else if("end".equals(taskKey)){
            //流程完成，兑现
        }*/
        //TODO 更新项目的分数，及评审结果信息
        awardEnterpriseProjectService.update(proInfo);
        return R.ok();
    }


    @RequestMapping("/association_review/{docId}")
    public String toUpDoc(@PathVariable("docId") String docId, ModelMap map) {
        map.put("docId", docId);
        AwardUpDocDo docDo = awardFlowService.get(docId);
        map.put("docInfo", docDo);
        return "act/award/association_review_doc";
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    public R remove(String id) {
        if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
            return R.error(1, "演示系统不允许修改,完整体验请部署程序");
        }
        if (awardFlowService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    public R remove(@RequestParam("ids[]") String[] ids) {
        if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
            return R.error(1, "演示系统不允许修改,完整体验请部署程序");
        }
        awardFlowService.batchRemove(ids);
        return R.ok();
    }

}
