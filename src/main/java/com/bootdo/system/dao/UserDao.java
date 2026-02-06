package com.bootdo.system.dao;

import com.bootdo.cpe.domain.EnterpriseAccountInitPwdData;
import com.bootdo.system.domain.UserDO;

import java.util.List;
import java.util.Map;

import com.bootdo.system.vo.CompanyInfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-03 09:45:11
 */
@Mapper
public interface UserDao {

	UserDO get(Long userId);

	List<UserDO> list(Map<String,Object> map);

	List<Long> getUidsByNames(@Param("userNames") String[] userNames);

	List<Long> getUidsByLoginUserNames(@Param("userNames") String[] userNames);

	List<Long> getUidByLoginUserName(@Param("username") String username);

	int count(Map<String,Object> map);

	int save(UserDO user);

	int saveEnterpriseInitPwdData(EnterpriseAccountInitPwdData initPwdData);

	int update(UserDO user);

	int remove(Long userId);

	int removeByLoginAccount(String loginAccount);

	/**
	 * 逻辑删除登录账号
	 * @param loginAccount
	 * @return
	 */
	int delUserByAccount(String loginAccount);

	int batchRemove(Long[] userIds);

	Long[] listAllDept();

	/**
	 * 公司是否已注册
	 * @param companyName
	 * @return
	 */
	int getIsRegCompany(@Param("companyName") String companyName);

    int registerCompanyInfo(CompanyInfoVO companyInfoVO);

}
