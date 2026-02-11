package com.bootdo.cpe.service.impl;

import com.bootdo.common.utils.R;
import com.bootdo.cpe.dao.ProjectCommonDao;
import com.bootdo.cpe.dao.QcAwardDao;
import com.bootdo.cpe.dao.QcGroupMemberDao;
import com.bootdo.cpe.domain.QcGroupMember;
import com.bootdo.cpe.domain.QcProStatEnum;
import com.bootdo.cpe.dto.QcProDataDto;
import com.bootdo.cpe.petroleum_engineering_award.domain.OilProStatEnum;
import com.bootdo.cpe.service.QcAwardService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * QC奖项目服务
 *
 * @author houzb
 * @version 1.0
 * @date 2022-02-05 10:40
 */
@Service
public class QcAwardServiceImpl implements QcAwardService {

    @Autowired
    private QcAwardDao qcAwardDao;
    @Autowired
    private ProjectCommonDao projectCommonDao;

    @Autowired
    private QcGroupMemberDao qcGroupMemberDao;


    /**
     * 获取项目列表
     *
     * @param map
     * @return
     */
    @Override
    public List<QcProDataDto> listProInfo(Map<String, Object> map) {
        List<QcProDataDto> list = qcAwardDao.getProDataList(map);
        List<String> proIds = list.stream()
                .map(dto -> Integer.toString(dto.getProId()))
                .distinct()
                .collect(Collectors.toList());
        Map<String,String> proNameMap = new HashMap<>();
        if (CollectionUtils.isNotEmpty(proIds)){
            List<QcGroupMember> qcGroupMemberList = qcGroupMemberDao.selectByProids(proIds);
            if (CollectionUtils.isNotEmpty(qcGroupMemberList)){
                proNameMap = qcGroupMemberList.stream()
                        .collect(Collectors.groupingBy(
                                QcGroupMember::getProid,
                                Collectors.mapping(QcGroupMember::getMembername, Collectors.joining(","))
                        ));

            }
        }
        //此处查询出对应proId对应的小组成员，然后map对应赋值即可
        list.stream().forEach(pro->{
            pro.initApplyStat();
        });
        //遍历项目列表，进行对符合proid的成员进行赋值
        for (QcProDataDto pro:list){
            if (proNameMap.containsKey(String.valueOf(pro.getProId()))){
                pro.setGroupMember(proNameMap.get(String.valueOf(pro.getProId())));
            }
        }
        return list;
    }

    /**
     * 获取项目的数量
     *
     * @param map
     * @return
     */
    @Override
    public int countProInfo(Map<String, Object> map) {
        return qcAwardDao.countProData(map);
    }

    /**
     * 更新项目的成果编码
     *
     * @param proId
     * @param resultCode
     * @return
     */
    @Override
    public int updateProResultCode(int proId, String resultCode) {
        int rst = projectCommonDao.updateProResultCode(proId, resultCode);
        return rst;
    }

    /**
     * 更新项目状态
     *
     * @param params
     * @return
     */
    @Override
    public int updateProStat(Map<String, Object> params) {
        Object reviewResultObj = params.get("reviewResult");
        if(reviewResultObj != null) {
            //传递了审核结果，则根据审核结果获取项目状态结果
            String reviewRst = reviewResultObj.toString();
            String proStat = "";
            if(QcProStatEnum.PARTAKE_AWARD.getStatDesc().equals(reviewRst)) {
                proStat = QcProStatEnum.PARTAKE_AWARD.getProStat();
            }else if(QcProStatEnum.REJECT.getStatDesc().equals(reviewRst)) {
                proStat = QcProStatEnum.REJECT.getProStat();
            }else if(QcProStatEnum.DELAYED_AWARD.getStatDesc().equals(reviewRst)) {
                proStat = QcProStatEnum.DELAYED_AWARD.getProStat();
            }else if(QcProStatEnum.NO_AWARD.getStatDesc().equals(reviewRst)) {
                proStat = QcProStatEnum.NO_AWARD.getProStat();
            }
            params.put("proStat", proStat);
        }
        Object proStatObj = params.get("proStat");
        if(proStatObj != null) {
            return qcAwardDao.updateProStat(params);
        }
        return 0;
    }

    /**
     * 更新项目状态为审核
     *
     * @param proId
     * @return
     */
    @Override
    public int updateProCheck(int proId) {
        Map<String,Object> params = new HashMap<>();
        params.put("proId", proId);
//        params.put("proStat", QcProStatEnum.TO_VALIDATE.getProStat());
        params.put("proStat","check");

        return updateProStat(params);
    }

    @Override
    public int updateProApply(int proId) {
        Map<String,Object> params = new HashMap<>();
        params.put("proId", proId);
        params.put("proStat", QcProStatEnum.APPLYING.getProStat());
        return updateProStat(params);
    }
}
