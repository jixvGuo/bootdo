package com.bootdo.cpe.service.impl;

import com.bootdo.cpe.dao.ImportCheckExcelUpdateDao;
import com.bootdo.cpe.domain.ImportCheckExcelDataDO;
import com.bootdo.cpe.service.ImportCheckExcelUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImportCheckExcelUpdateServiceImpl implements ImportCheckExcelUpdateService {
    @Autowired
    private ImportCheckExcelUpdateDao importCheckExcelUpdateDao;

    @Override
    public int addScienceProValidateResult(String taskId) {
        importCheckExcelUpdateDao.cleanSciencePersonLastValidate(taskId);
        importCheckExcelUpdateDao.addSciencePersonValidateResult(taskId);

        importCheckExcelUpdateDao.cleanScienceTeamLastValidate(taskId);
        importCheckExcelUpdateDao.addScienceTeamValidateResult(taskId);

        importCheckExcelUpdateDao.cleanScienceProceLastValidate(taskId);
        importCheckExcelUpdateDao.addScienceProceValidateResult(taskId);

        return 1;
    }

    @Override
    public int updateScienceProMajorAndGroup(List<ImportCheckExcelDataDO> excelDataDOList) {
        excelDataDOList.stream().forEach(item ->{
            Integer proId = item.getProId();
            if(proId != null && proId > 0) {
                importCheckExcelUpdateDao.updateSciencePersonMajorByExcelData(item);
                importCheckExcelUpdateDao.updateScienceProcessMajorByExcelData(item);
                importCheckExcelUpdateDao.updateScienceTeamMajorByExcelData(item);
                importCheckExcelUpdateDao.updateScienceProGroupByExcelData(item);
            }
        });
        return 1;
    }
}
