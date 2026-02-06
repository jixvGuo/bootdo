package com.bootdo.cpe.controller;

import com.bootdo.activiti.domain.EnterpriseDocUploadDo;
import com.bootdo.activiti.service.AwardEnterpriseProjectService;
import com.bootdo.activiti.service.AwardPublishTaskService;
import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.domain.FileDO;
import com.bootdo.common.service.FileService;
import com.bootdo.common.utils.*;
import com.bootdo.cpe.domain.SpecialistDocFileInfo;
import com.bootdo.system.domain.EnterpriseChengguoBaseInfoDO;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;

@Controller
@RequestMapping("/specialistDoc")
public class SpecialistDocController extends BaseController {

    @Autowired
    private FileService fileService;
    @Autowired
    private AwardEnterpriseProjectService awardEnterpriseProjectService;
    @Autowired
    private AwardPublishTaskService awardPublishTaskService;

    @Autowired
    private BootdoConfig bootdoConfig;
    @Autowired
    private FileService sysFileService;

    /**
     * 去上传奖项相关的资料
     *
     * @return
     */
    @RequestMapping("/view_doc")
    public String toUpSpecialistDoc(@RequestParam Map<String, Object> params, ModelMap map) {
        long specialistUid = getUserId();
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("specialistUid", specialistUid);
        queryParams.put("taskId", params.get("taskId"));
        List<SpecialistDocFileInfo> docUploadDoList = fileService.listSpecialistDocFiles(queryParams);
        map.put("docUploadDoList", docUploadDoList);
        map.put("fileSize", docUploadDoList.size());
        map.put("taskId", params.get("taskId"));

        //去上传
        return "cpe/specialist_doc/specialist_doc_manage";
    }

       /**
     * 上传的页面
     *
     * @return
     */
    @RequestMapping("/toUploadDoc")
    public String toUpload(@RequestParam Map<String, Object> params, String fileType, String departId, ModelMap map) {
        map.put("taskId", params.get("taskId"));
        return "cpe/specialist_doc/upload_specialist_doc";
    }


      /**
     * 上传项目资料文件
     * 存储目录 年/月/u_用户id
     *
     * @param files
     * @return
     */
    @RequestMapping("/uploadDoc")
    @ResponseBody
    public R uploadEnterpriseDoc(String taskId, @RequestPart("file[]") MultipartFile[] files) {
        long specialistUid = getUserId();
        List<SpecialistDocFileInfo> uploadFileList = new ArrayList<>();
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
            if (rstCount > 0) {
                //保存到专家文件库
                SpecialistDocFileInfo docFileInfo = new SpecialistDocFileInfo();
                docFileInfo.setFileId(sysFile.getId());
                docFileInfo.setFileUrl(sysFile.getUrl());
                docFileInfo.setSpecialistUid(specialistUid);
                docFileInfo.setTaskId(taskId);
                sysFileService.saveSpecialistDocFile(docFileInfo);
                uploadFileList.add(docFileInfo);
            }
        }
        R rst = R.ok();
        rst.put("files", uploadFileList);
        rst.put("fileSize", uploadFileList.size());
        rst.put("fileUrl", fileUrl);
        return rst;
    }

    /**
     * 删除
     */
    @PostMapping("/removeDoc")
    @ResponseBody
    public R remove(Long id, HttpServletRequest request) {
        FileDO fileDO = sysFileService.get(id);
        sysFileService.deleteSpecialistDoc(id);
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


}
