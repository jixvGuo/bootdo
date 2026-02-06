package com.bootdo.cpe.service.impl;

import com.bootdo.common.utils.StringUtils;
import com.bootdo.cpe.dao.ProjectCommonDao;
import com.bootdo.cpe.domain.EnumApplyEnterpriseProStat;
import com.bootdo.cpe.service.ProjectCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.ref.PhantomReference;

/**
 * @author houzb
 * @Description
 * @create 2020-09-29 1:45
 */
@Service
public class ProjectCommonServiceImpl implements ProjectCommonService {
    @Autowired
    private ProjectCommonDao projectCommonDao;

    /**
     * 获取项目当前的状态值
     *
     * @param proId
     * @return
     */
    @Override
    public String getProjectStat(int proId) {
        return projectCommonDao.getProjectStat(proId);
    }

    /**
     * 是否显示提交审核按钮
     *
     * @param proId 项目id
     * @return true 显示提交审核按钮，false不显示提交审核按钮
     */
    @Override
    public boolean isViewApplyValidateBtn(int proId) {
        String proStat = getProjectStat(proId);
        return isViewApplyValidateBtnByStat(proStat);
    }

    /**
     * 是否显示提交审核按钮
     *
     * @param proStat 项目当前的状态
     * @return
     */
    @Override
    public boolean isViewApplyValidateBtnByStat(String proStat) {
        boolean isBlank = StringUtils.isBlank(proStat);
        boolean isReject = EnumApplyEnterpriseProStat.REJECT.getStat().equals(proStat);
        return isBlank || isReject;
    }

    /**
     * 根据项目id获取创建者用户id
     *
     * @param proId
     * @return
     */
    @Override
    public long getProCreateUid(int proId) {
        return projectCommonDao.getProCreateUid(proId);
    }

    @Override
    public int removePros(String taskId) {
        return projectCommonDao.removePros(taskId);
    }

    @Override
    public int removeTask(String taskId) {
        projectCommonDao.removePros(taskId);
        return projectCommonDao.removeTask(taskId);
    }

    @Override
    public int updateProGroupName(int proId, String groupName) {
        return projectCommonDao.updateProGroupName(proId, groupName);
    }
}
