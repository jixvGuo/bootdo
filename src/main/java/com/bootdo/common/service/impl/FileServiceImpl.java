package com.bootdo.common.service.impl;

import com.bootdo.activiti.domain.EnterpriseDocUploadDo;
import com.bootdo.common.config.BootdoConfig;
import com.bootdo.cpe.domain.SpecialistDocFileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bootdo.common.dao.FileDao;
import com.bootdo.common.domain.FileDO;
import com.bootdo.common.service.FileService;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;


@Service
public class FileServiceImpl implements FileService {
	@Autowired
	private FileDao sysFileMapper;

	@Autowired
	private BootdoConfig bootdoConfig;
	@Override
	public FileDO get(Long id){
		return sysFileMapper.get(id);
	}
	
	@Override
	public List<FileDO> list(Map<String, Object> map){
		return sysFileMapper.list(map);
	}

	@Override
	public List<EnterpriseDocUploadDo> listUploadEnterpriseDocs(Map<String, Object> map) {
		return sysFileMapper.listUploadEnterpriseDocs(map);
	}

	@Override
	public List<EnterpriseDocUploadDo> listUploadTaskDocs(Map<String, Object> map) {
		return sysFileMapper.listUploadTaskDocs(map);
	}

	@Override
	public List<EnterpriseDocUploadDo> listTaskDocInfo(Map<String, Object> map) {
		return sysFileMapper.listTaskDocInfo(map);
	}

	@Override
	public int count(Map<String, Object> map){
		return sysFileMapper.count(map);
	}
	
	@Override
	public int save(FileDO sysFile){
		return sysFileMapper.save(sysFile);
	}

	@Override
	public int saveEnterpriseDoc(EnterpriseDocUploadDo uploadDo) {
		return sysFileMapper.saveEnterpriseDoc(uploadDo);
	}

	@Override
	public int update(FileDO sysFile){
		return sysFileMapper.update(sysFile);
	}

	@Override
	public int updateEnterpriseDoc(EnterpriseDocUploadDo enterpriseDocUploadDo) {

		return sysFileMapper.updateEnterpriseDoc(enterpriseDocUploadDo);
	}

	@Override
	public int deleteEnterpriseDoc(int docId) {
		return sysFileMapper.removeEnterpriseDoc(docId);
	}

	@Override
	public int deleteEnterpriseDocByFileId(long fileId) {
		return sysFileMapper.removeEnterpriseDocByFileId(fileId);
	}

	@Override
	public int cleanTaskEnterpriseDoc(String taskId) {
		return sysFileMapper.cleanTaskEnterpriseDoc(taskId);
	}

	@Override
	public int remove(Long id){
		return sysFileMapper.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return sysFileMapper.batchRemove(ids);
	}

    @Override
    public Boolean isExist(String url) {
		Boolean isExist = false;
		if (!StringUtils.isEmpty(url)) {
			String filePath = url.replace("/files/", "");
			filePath = bootdoConfig.getUploadPath() + filePath;
			File file = new File(filePath);
			if (file.exists()) {
				isExist = true;
			}
		}
		return isExist;
	}

	@Override
	public List<SpecialistDocFileInfo> listSpecialistDocFiles(Map<String, Object> params) {
		return sysFileMapper.listSpecialistDocFiles(params);
	}

	@Override
	public int saveSpecialistDocFile(SpecialistDocFileInfo fileInfo) {
		return sysFileMapper.saveSpecialistDocFile(fileInfo);
	}

	@Override
	public void deleteSpecialistDoc(Long id) {
        sysFileMapper.deleteSpecialistDoc(id);
	}

	@Override
	public List fileTypeList(String proId) {
		return sysFileMapper.fileTypeList(proId);
	}
}
