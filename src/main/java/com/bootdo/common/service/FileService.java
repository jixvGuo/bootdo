package com.bootdo.common.service;

import com.bootdo.activiti.domain.EnterpriseDocUploadDo;
import com.bootdo.common.domain.FileDO;
import com.bootdo.cpe.domain.SpecialistDocFileInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 文件上传
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-09-19 16:02:20
 */
public interface FileService {
	
	FileDO get(Long id);
	
	List<FileDO> list(Map<String, Object> map);
	List<EnterpriseDocUploadDo> listUploadEnterpriseDocs(Map<String, Object> map);

	List<EnterpriseDocUploadDo> listUploadTaskDocs(Map<String, Object> map);

	List<EnterpriseDocUploadDo> listTaskDocInfo(Map<String, Object> map);



	int count(Map<String, Object> map);
	
	int save(FileDO sysFile);
	int saveEnterpriseDoc(EnterpriseDocUploadDo uploadDo);

	int update(FileDO sysFile);

    int updateEnterpriseDoc(EnterpriseDocUploadDo enterpriseDocUploadDo);

    int deleteEnterpriseDoc(int docId);
    int deleteEnterpriseDocByFileId(long fileId);
	int cleanTaskEnterpriseDoc(String taskId);

	int remove(Long id);
	
	int batchRemove(Long[] ids);

	/**
	 * 判断一个文件是否存在
	 * @param url FileDO中存的路径
	 * @return
	 */
    Boolean isExist(String url);

    public List<SpecialistDocFileInfo> listSpecialistDocFiles(Map<String, Object> params);
	public int saveSpecialistDocFile(SpecialistDocFileInfo fileInfo);

	void deleteSpecialistDoc(Long id);

	List fileTypeList(String proId);
}
