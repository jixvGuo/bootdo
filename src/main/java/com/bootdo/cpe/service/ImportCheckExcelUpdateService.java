package com.bootdo.cpe.service;

import com.bootdo.cpe.domain.ImportCheckExcelDataDO;

import java.util.List;

public interface ImportCheckExcelUpdateService {

    public int addScienceProValidateResult(String taskId);

    public int updateScienceProMajorAndGroup(List<ImportCheckExcelDataDO> excelDataDOList);

}
