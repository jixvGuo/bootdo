package com.bootdo.system.shiro;

import java.util.*;

import com.bootdo.common.config.ApplicationContextRegister;
import com.bootdo.system.dao.UserRoleDao;
import com.bootdo.system.domain.UserToken;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.system.dao.UserDao;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.MenuService;

import static com.bootdo.common.config.Constant.*;

public class UserRealm extends AuthorizingRealm {
/*	@Autowired
	UserDao userMapper;
	@Autowired
	MenuService menuService;*/

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		Long userId = ShiroUtils.getUserId();
		MenuService menuService = ApplicationContextRegister.getBean(MenuService.class);
		Set<String> perms = menuService.listPerms(userId);
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setStringPermissions(perms);
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		Map<String, Object> map = new HashMap<>(16);
		map.put("username", username);
		String password = new String((char[]) token.getCredentials());

		UserDao userMapper = ApplicationContextRegister.getBean(UserDao.class);
		// 查询用户信息
		UserDO user = userMapper.list(map).get(0);

		// 账号不存在
		if (user == null) {
			throw new UnknownAccountException("账号或密码不正确");
		}

		// 密码错误
		if (!password.equals(user.getPassword())) {
			throw new IncorrectCredentialsException("账号或密码不正确");
		}

		// 账号锁定
		if (user.getStatus() == 0) {
			throw new LockedAccountException("账号已被锁定,请联系管理员");
		}

		//1 超级
		// 60 科技奖协会联系人
		// 66 工程奖协会联系人
		// QC 70 71
		// 工法 78
		// 勘察   74

		//用户拥有的角色信息
		UserRoleDao roleMapper = ApplicationContextRegister.getBean(UserRoleDao.class);
        List<Long> roleIdList = roleMapper.listRoleId(user.getUserId());
		String currentType = ApplicationContextRegister.getCurrentType();
		System.out.println("当前的类型 currentType" + currentType);
		Boolean isCanLogin = false ;

        if ("1".equalsIgnoreCase(currentType)){
			// 科技
			for(Long m : roleIdList){
				if (m == 1 || m == 60 || m == 61 || m == ROLE_SCIENCE_EXTERNAL_EMPLOYMENT_ID || m== ROLE_SPECIALIST_ID){
					isCanLogin = true ;
				}
			}
		}else if("2".equalsIgnoreCase(currentType)){
			// 优秀勘察
			for(Long m : roleIdList){
				if (m == 1 || m == 74 || m == 73 || m == ROLE_SURVER_EXTERNAL_EMPLOYMENT_ID){
					isCanLogin = true ;
				}
			}
		}else if("3".equalsIgnoreCase(currentType)){
			// QC
			for(Long m : roleIdList){
				if (m == 1 || m == 70 || m == 71 || m == ROLE_QC_EXTERNAL_EMPLOYMENT_ID){
					isCanLogin = true ;
				}
			}
		}else if("4".equalsIgnoreCase(currentType)){
			// 优质工程
			for(Long m : roleIdList){
				if (m == 1 || m ==66 || m == 67 || m == ROLE_GOOD_PRO_EMPLOYMENT_ID){
					isCanLogin = true ;
				}
			}
		}else if("5".equalsIgnoreCase(currentType)){
			// 工法奖
			for(Long m : roleIdList){
				if (m == 1 || m == 78 || m == 79 || m == ROLE_GONGFA_EMPLOYMENT_ID){
					isCanLogin = true ;
				}
			}
		}

// 密码错误
		if (!isCanLogin) {
			throw new DisabledAccountException("请选择正确的模块进入系统");
		}

        user.setRoleIds(roleIdList);

		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
		return info;
	}

}
