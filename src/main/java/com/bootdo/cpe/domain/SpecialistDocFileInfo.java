package com.bootdo.cpe.domain;

import com.bootdo.common.utils.StringUtils;

public class SpecialistDocFileInfo {
    private int id;
    private String taskId;
    private Long specialistUid;
    private Long fileId;
    private String fileUrl;
    private String fileName;
    private String groupName;
    private String created;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Long getSpecialistUid() {
        return specialistUid;
    }

    public void setSpecialistUid(Long specialistUid) {
        this.specialistUid = specialistUid;
    }

    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
        if(StringUtils.isNotBlank(fileUrl)) {
            this.fileName = fileUrl.substring(fileUrl.lastIndexOf("/"));
        }
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}
