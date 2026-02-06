package com.bootdo.cpe.utils;

import javax.swing.text.AbstractWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author houzb
 * @version 1.0
 * @date 2022-03-28 23:19
 */
public enum AwardSurverSubTypeEnum {
    CONTRIBUTION("contribution", "石油工程建设优秀勘察奖", new String[]{"excellent_scanned_copy","excellent_accessory_material_report","excellent_accessory_material_pic","excellent_upload_accessories_catalog",
    "excellent_project_profile_sheet"}, new String[]{"excellent_materials_construction_unit","excellent_materials_construction_unit_acceptance_certificate",
    "excellent_materials_award_certificate", "excellent_materials_survey_certificate","excellent_materials_safety_commitment",
    "excellent_materials_search_report","excellent_materials_technical_document",
    "excellent_materials_water_resource", "excellent_materials_survey_comments"}),
    DESITN("design", "石油工程建设优秀设计奖",new String[]{"appendix_file_design","appendix_file_pic"},
            new String[]{"prove_complete","prove_economics","prove_env","prove_material_use","prove_quality",
            "prove_common_pro_use","prove_waste_discharge","prove_cal_table","prove_bureau_level_award",
            "prove_design_book","prove_last_accident","prove_first_prize","prove_technical_standard",
            "prove_opinions","prove_other_data"}),
    SOFTWARE("software", "石油工程建设优秀勘察设计计算机软件奖", new String[]{"apply_table","manual_book","soft_upload_accessories_catalog",
    "soft_project_profile_sheet"}, new String[]{"iden_cert","user_report","economics","award_cert","first_reprot"}),
    STANDARD("standard", "石油工程建设优秀标准设计奖", new String[]{"apply_table","standard_desc","publish_txt","standard_upload_accessories_catalog",
    "standard_project_profile_sheet"}, new String[]{"use_desc","economics_cert", "bureau_level_award"}),
    CONSULTING("consulting", "石油工程建设优秀咨询奖", new String[]{"apply_table","result_report",
    "recommend_cret", "appendix_imgs", "appendix_imgs_pic"}, new String[]{"pro_proposal","topic_report","study_report",
    "consulting_result", "bureau_level_award", "complete_cret", "consulting_cert", "safe_accident", "first_awrad_report",
    "pro_opinions", "other_source"}),

    ;

    private String subType;
    private String desc;
    /**
     * 文件附件
     */
    private String[] fileAppendix;
    /**
     * 文件证明性材料
     */
    private String[] fileSupport;
    AwardSurverSubTypeEnum(String subType, String desc, String[] fileAppendix, String[] fileSupport) {
        this.subType = subType;
        this.desc = desc;
        this.fileAppendix = fileAppendix;
        this.fileSupport = fileSupport;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String[] getFileAppendix() {
        return fileAppendix;
    }

    public void setFileAppendix(String[] fileAppendix) {
        this.fileAppendix = fileAppendix;
    }

    public String[] getFileSupport() {
        return fileSupport;
    }

    public void setFileSupport(String[] fileSupport) {
        this.fileSupport = fileSupport;
    }

    public static AwardSurverSubTypeEnum getSubTypeEnum(String subType) {
        AwardSurverSubTypeEnum[] subTypeEnumList = AwardSurverSubTypeEnum.values();
        for(AwardSurverSubTypeEnum subTypeEnum: subTypeEnumList) {
            String st = subTypeEnum.getSubType();
            if(subType.equals(st)) {
                return subTypeEnum;
            }
        }
        return null;
    }

    public static List<String> getFileTypeList(String fileType) {
        List<String> typeList = new ArrayList<>();
        boolean isAppendix = fileType.equals("appendix");
        AwardSurverSubTypeEnum[] subTypeEnumList = AwardSurverSubTypeEnum.values();
        for(AwardSurverSubTypeEnum subTypeEnum: subTypeEnumList) {
            if(isAppendix) {
                typeList.addAll(Arrays.asList(subTypeEnum.getFileAppendix()));
            }else {
                typeList.addAll(Arrays.asList(subTypeEnum.getFileSupport()));
            }
        }
        return typeList;
    }
}
