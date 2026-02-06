package com.bootdo.system.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.bootdo.cpe.domain.EnterpriseAccountInitPwdData;
import com.bootdo.system.vo.CompanyInfoVO;
import com.bootdo.system.vo.UserVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.bootdo.common.domain.Tree;
import com.bootdo.system.domain.DeptDO;
import com.bootdo.system.domain.UserDO;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface UserService {
	UserDO get(Long id);

	List<UserDO> list(Map<String, Object> map);
	List<Long> getUidsByNames(String[] userNames);
	List<Long> getUidsByLoginUserNames(String[] userNames);
	List<Long> getUidByLoginUserName(String username);

	int count(Map<String, Object> map);

	int save(UserDO user);

	/**
	 * 保存企业账号初始密码
	 * @param initPwdData
	 * @return
	 */
	int saveEnterpriseInitPwdData(EnterpriseAccountInitPwdData initPwdData);

	int savePre(UserDO user);

	int update(UserDO user);

	int remove(Long userId);

	/**
	 * 根据登录账号删除用户信息
	 * @param loginAccount
	 * @return
	 */
	int removeByLoginAccount(String loginAccount);

	/**
	 * 逻辑删除用户信息
	 * @param loginAccount
	 * @return
	 */
	int delUserByAccount(String loginAccount);

	int batchremove(Long[] userIds);

	boolean exit(Map<String, Object> params);

	Set<String> listRoles(Long userId);

	int resetPwd(UserVO userVO,UserDO userDO) throws Exception;
	int adminResetPwd(UserVO userVO) throws Exception;
	Tree<DeptDO> getTree();

	/**
	 * 更新个人信息
	 * @param userDO
	 * @return
	 */
	int updatePersonal(UserDO userDO);

	/**
	 * 更新个人图片
	 * @param file 图片
	 * @param avatar_data 裁剪信息
	 * @param userId 用户ID
	 * @throws Exception
	 */
    Map<String, Object> updatePersonalImg(MultipartFile file, String avatar_data, Long userId) throws Exception;

    int registerCompanyInfo(CompanyInfoVO companyInfoVO);
}
