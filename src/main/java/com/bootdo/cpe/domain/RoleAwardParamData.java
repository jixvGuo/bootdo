package com.bootdo.cpe.domain;

import com.bootdo.common.annotation.Log;
import com.bootdo.cpe.utils.CpeException;
import com.bootdo.cpe.utils.ExceptionEnum;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.bootdo.common.config.Constant.*;

/**
 * 角色的参数信息
 *
 * @author houzb
 * @version 1.0
 * @date 2022-02-16 23:43
 */
public class RoleAwardParamData {
    private long roleId = 0;
    private int awardId = 0;
    private String type;
    private boolean isAssociation;
    //是否为外聘人员
    private boolean isOutWorker;
    //是否为专家
    private boolean isSpecialist;

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public int getAwardId() {
        return awardId;
    }

    public void setAwardId(int awardId) {
        this.awardId = awardId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean getIsAssociation() {
        return isAssociation;
    }

    public void setIsAssociation(boolean association) {
        isAssociation = association;
    }

    public boolean getIsOutWorker() {
        return isOutWorker;
    }

    public void setIsOutWorker(boolean outWorker) {
        isOutWorker = outWorker;
    }

    public boolean getIsSpecialist() {
        return isSpecialist;
    }

    public void setIsSpecialist(boolean specialist) {
        isSpecialist = specialist;
    }

//    public static RoleAwardParamData getInstance(List<Long> roleList, String awardId) throws CpeException {
//        RoleAwardParamData  paramData = new RoleAwardParamData();
//        long roleId = 0;
//        int awardTypeId = 0;
//        int awardIdInt = 0;
//        String type = "2";
//        if(StringUtils.isNotBlank(awardId) && StringUtils.isNumeric(awardId)) {
//            awardIdInt = Integer.parseInt(awardId);
//        }
//        if(roleList.contains(ROLE_SPECIALIST_ID)) {
//            paramData.setIsSpecialist(true);
//        }else {
//            paramData.setIsSpecialist(false);
//        }
//        if(roleList.contains(ROLE_SCIENCE_EXTERNAL_EMPLOYMENT_ID) ||
//           roleList.contains(ROLE_QC_EXTERNAL_EMPLOYMENT_ID) ||
//           roleList.contains(ROLE_SURVER_EXTERNAL_EMPLOYMENT_ID)) {
//            paramData.setIsOutWorker(true);
//        }else {
//            paramData.setIsOutWorker(false);
//        }
//        //符合条件的角色数量
//        int roleCount = 0;
//        long selRoleId = 0;
//        String selType = "2";
//        if(roleList.contains(ROLE_ASSOCIATION_OIL_PRO_ID)) {
//            //石油工程奖联系人
//            type = "2";
//            roleId = ROLE_ASSOCIATION_OIL_PRO_ID;
//            awardTypeId = EnumAwardType.QUALITY.getAwrdType();
//
//            roleCount++;
//            if(awardIdInt == awardTypeId) {
//                selRoleId = roleId;
//                selType = type;
//            }
//        }
//        if(roleList.contains(ROLE_SCIENCE_ASSOCIATION_ID)) {
//            type = "1";
//            roleId = ROLE_SCIENCE_ASSOCIATION_ID;
//            awardTypeId = EnumAwardType.SCIENCE.getAwrdType();
//
//            roleCount++;
//            if(awardIdInt == awardTypeId) {
//                selRoleId = roleId;
//                selType = type;
//            }
//        }
//        if (roleList.contains(ROLE_QC_ASSOCIATION_ID)) {
//            type = "3";
//            roleId = ROLE_QC_ASSOCIATION_ID;
//            awardTypeId = EnumAwardType.QC.getAwrdType();
//
//            roleCount++;
//            if(awardIdInt == awardTypeId) {
//                selRoleId = roleId;
//                selType = type;
//            }
//        }
//
//        if (roleList.contains(ROLE_SURVER_ASSOCIATION_ID)) {
//            type = "2";
//            roleId = ROLE_SURVER_ASSOCIATION_ID;
//            awardTypeId = EnumAwardType.SURVER.getAwrdType();
//
//            roleCount++;
//            if(awardIdInt == awardTypeId) {
//                selRoleId = roleId;
//                selType = type;
//            }
//        }
//        if(roleList.contains(ROLE_GONGFA_ASSOCIATION_ID)) {
//            type = "5";
//            roleId = ROLE_GONGFA_ASSOCIATION_ID;
//            awardTypeId = EnumAwardType.GONGFA.getAwrdType();
//
//            roleCount++;
//            if(awardIdInt == awardTypeId) {
//                selRoleId = roleId;
//                selType = type;
//            }
//        }
//
//        if(awardIdInt == 0) {
//            throw new CpeException(ExceptionEnum.AWARD_TASK_ROLE_NO_SEL_AWARD);
//        }
//        if(roleCount > 1) {
//            awardTypeId = awardIdInt;
//            roleId = selRoleId;
//            System.out.println("多个角色");
//            type = selType;
//        }
//
//        paramData.setIsAssociation(roleCount > 0);
//        paramData.setAwardId(awardTypeId);
//        paramData.setRoleId(roleId);
//        paramData.setType(type);
//        return paramData;
//    }
//

    public static RoleAwardParamData getInstance(List<Long> roleList, String awardId) throws CpeException {
        // 1. 优先解析 awardId
        if (StringUtils.isBlank(awardId) || !StringUtils.isNumeric(awardId)) {
            throw new CpeException(ExceptionEnum.AWARD_TASK_ROLE_NO_SEL_AWARD);
        }
        int awardIdInt = Integer.parseInt(awardId);

        RoleAwardParamData paramData = new RoleAwardParamData();

        // 2. 设置专家/外聘标志
        paramData.setIsSpecialist(roleList.contains(ROLE_SPECIALIST_ID));
        paramData.setIsOutWorker(
                roleList.contains(ROLE_SCIENCE_EXTERNAL_EMPLOYMENT_ID) ||
                        roleList.contains(ROLE_QC_EXTERNAL_EMPLOYMENT_ID) ||
                        roleList.contains(ROLE_SURVER_EXTERNAL_EMPLOYMENT_ID)
        );

        // 3. 核心逻辑：先判断 awardId，再检查角色，有匹配则直接返回

        // 科技奖 (awardId=1)
        if (awardIdInt == EnumAwardType.SCIENCE.getAwrdType()) {
            if (roleList.contains(ROLE_SCIENCE_ASSOCIATION_ID)) {
                paramData.setIsAssociation(true);
                paramData.setAwardId(awardIdInt);
                paramData.setRoleId(ROLE_SCIENCE_ASSOCIATION_ID);
                paramData.setType("1");
                return paramData;
            }
            if (roleList.contains(ROLE_ENTERPRISE_SCIENCE_ID)) {
                paramData.setIsAssociation(false);
                paramData.setAwardId(awardIdInt);
                paramData.setRoleId(ROLE_ENTERPRISE_SCIENCE_ID);
                paramData.setType("1");
                return paramData;
            }
        }

        // 勘察奖 (awardId=2)
        if (awardIdInt == EnumAwardType.SURVER.getAwrdType()) {
            if (roleList.contains(ROLE_SURVER_ASSOCIATION_ID)) {
                paramData.setIsAssociation(true);
                paramData.setAwardId(awardIdInt);
                paramData.setRoleId(ROLE_SURVER_ASSOCIATION_ID);
                paramData.setType("2");
                return paramData;
            }
            if (roleList.contains(ROLE_ENTERPRISE_SURVER_ID)) {
                paramData.setIsAssociation(false);
                paramData.setAwardId(awardIdInt);
                paramData.setRoleId(ROLE_ENTERPRISE_SURVER_ID);
                paramData.setType("2");
                return paramData;
            }
        }

        // QC奖 (awardId=3)
        if (awardIdInt == EnumAwardType.QC.getAwrdType()) {
            if (roleList.contains(ROLE_QC_ASSOCIATION_ID)) {
                paramData.setIsAssociation(true);
                paramData.setAwardId(awardIdInt);
                paramData.setRoleId(ROLE_QC_ASSOCIATION_ID);
                paramData.setType("3");
                return paramData;
            }
            if (roleList.contains(ROLE_ENTERPRISE_QC_ID)) {
                paramData.setIsAssociation(false);
                paramData.setAwardId(awardIdInt);
                paramData.setRoleId(ROLE_ENTERPRISE_QC_ID);
                paramData.setType("3");
                return paramData;
            }
        }

        // 优质工程奖 (awardId=4)
        if (awardIdInt == EnumAwardType.QUALITY.getAwrdType()) {
            if (roleList.contains(ROLE_ASSOCIATION_OIL_PRO_ID)) {
                paramData.setIsAssociation(true);
                paramData.setAwardId(awardIdInt);
                paramData.setRoleId(ROLE_ASSOCIATION_OIL_PRO_ID);
                paramData.setType("2");
                return paramData;
            }
            if (roleList.contains(ROLE_ENTERPRISE_ENGINEER_ID)) {
                paramData.setIsAssociation(false);
                paramData.setAwardId(awardIdInt);
                paramData.setRoleId(ROLE_ENTERPRISE_ENGINEER_ID);
                paramData.setType("2");
                return paramData;
            }
        }

        // 工法奖 (awardId=5)
        if (awardIdInt == EnumAwardType.GONGFA.getAwrdType()) {
            if (roleList.contains(ROLE_GONGFA_ASSOCIATION_ID)) {
                paramData.setIsAssociation(true);
                paramData.setAwardId(awardIdInt);
                paramData.setRoleId(ROLE_GONGFA_ASSOCIATION_ID);
                paramData.setType("5");
                return paramData;
            }
            if (roleList.contains(ROLE_GONGFA_ENTERPRISE_ID)) {
                paramData.setIsAssociation(false);
                paramData.setAwardId(awardIdInt);
                paramData.setRoleId(ROLE_GONGFA_ENTERPRISE_ID);
                paramData.setType("5");
                return paramData;
            }
        }

        // 4. 无匹配角色，抛异常
        System.out.println("error");
        return null;
    }

    public static List<Long> getAwardNoExistsMenuIdListByAwardId(Integer awardId) {
        List<Long> list = new ArrayList<>();
        if(awardId == null) {
            return list;
        }
        List<Long> scienceMenuIdList = new ArrayList<>();
        List<Long> qcMenuIdList = new ArrayList<>();
        List<Long> qualityMenuIdList = new ArrayList<>();
        List<Long> surverMenuIdList = new ArrayList<>();
        //科技奖
        //科学技术奖成果申报
        scienceMenuIdList.add(129L);
        //先进团队成果申报
        scienceMenuIdList.add(130L);
        //先进个人成果申报
        scienceMenuIdList.add(131L);

        //QC奖
        //QC项目申报
        qcMenuIdList.add(287L);

        //优质工程奖
        //石油优质工程奖申报
        qualityMenuIdList.add(244L);

        //勘察相关奖项

        if(awardId == EnumAwardType.SCIENCE.getAwrdType()) {
            list.addAll(qcMenuIdList);
            list.addAll(qualityMenuIdList);
            list.addAll(surverMenuIdList);
        }else if(awardId == EnumAwardType.QC.getAwrdType()) {
            list.addAll(scienceMenuIdList);
            list.addAll(qualityMenuIdList);
            list.addAll(surverMenuIdList);
        }else if(awardId == EnumAwardType.QUALITY.getAwrdType()) {
            list.addAll(scienceMenuIdList);
            list.addAll(qcMenuIdList);
            list.addAll(surverMenuIdList);
        }else if (awardId == EnumAwardType.SURVER.getAwrdType()) {
            list.addAll(scienceMenuIdList);
            list.addAll(qcMenuIdList);
            list.addAll(qualityMenuIdList);
        }
        return list;
    }

    public static boolean isAssociationRole(List<Long> roleList) {
        if(roleList.contains(ROLE_SCIENCE_ASSOCIATION_ID)) {
            return true;
        }
        return false;
    }

}
