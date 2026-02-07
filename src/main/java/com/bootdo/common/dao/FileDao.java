package com.bootdo.common.dao;

import com.bootdo.activiti.domain.EnterpriseDocUploadDo;
import com.bootdo.common.domain.FileDO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bootdo.cpe.domain.SpecialistDocFileInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文件上传
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-03 15:45:42
 */
@Mapper
public interface FileDao {

	FileDO get(Long id);
	
	List<FileDO> list(Map<String,Object> map);
	List<EnterpriseDocUploadDo> listUploadEnterpriseDocs(Map<String, Object> map);

	List<EnterpriseDocUploadDo> listUploadTaskDocs(Map<String, Object> map);

	List<EnterpriseDocUploadDo> listTaskDocInfo(Map<String, Object> map);


	int updateEnterpriseDoc(EnterpriseDocUploadDo enterpriseDocUploadDo);
	int removeEnterpriseDoc(int docId);
	int removeEnterpriseDocByFileId(long fileId);

	int count(Map<String,Object> map);
	
	int save(FileDO file);
	int saveEnterpriseDoc(EnterpriseDocUploadDo uploadDo);

	int update(FileDO file);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	int cleanTaskEnterpriseDoc(String taskId);

	List<SpecialistDocFileInfo> listSpecialistDocFiles(Map<String, Object> params);

    int saveSpecialistDocFile(SpecialistDocFileInfo fileInfo);

	void deleteSpecialistDoc(Long id);

	List fileTypeList(String proId);
}
