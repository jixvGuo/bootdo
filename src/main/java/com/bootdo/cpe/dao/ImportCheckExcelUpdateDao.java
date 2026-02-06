package com.bootdo.cpe.dao;

import com.bootdo.cpe.domain.ImportCheckExcelDataDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ImportCheckExcelUpdateDao {
    public int cleanSciencePersonLastValidate(String taskId);
    public int updateSciencePersonMajor(String taskId);
    public int addSciencePersonValidateResult(String taskId);

    public int cleanScienceTeamLastValidate(String taskId);
    public int updateScienceTeamMajor(String taskId);
    public int addScienceTeamValidateResult(String taskId);

    public int cleanScienceProceLastValidate(String taskId);
    public int updateSciencePorceMajor(String taskId);
    public int addScienceProceValidateResult(String taskId);

    public int updateSciencePersonMajorByExcelData(@Param("item") ImportCheckExcelDataDO excelDataDO);
    public int updateScienceTeamMajorByExcelData(@Param("item") ImportCheckExcelDataDO excelDataDO);
    public int updateScienceProcessMajorByExcelData(@Param("item") ImportCheckExcelDataDO excelDataDO);
    public int updateScienceProGroupByExcelData(@Param("item") ImportCheckExcelDataDO excelDataDO);


}
