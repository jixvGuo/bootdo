package com.bootdo.system.service;

import java.util.List;

import com.bootdo.common.domain.Tree;
import org.springframework.stereotype.Service;

import com.bootdo.system.domain.RoleDO;

@Service
public interface RoleService {

	RoleDO get(Long id);

	List<RoleDO> list();
	Tree<RoleDO> getTree();

	int save(RoleDO role);

	int update(RoleDO role);

	int remove(Long id);

	List<RoleDO> list(Long userId);

	int batchremove(Long[] ids);
}
