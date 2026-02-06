package com.bootdo.system.service;

import com.bootdo.common.domain.Tree;
import com.bootdo.system.domain.AwardDo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AwardService {

	AwardDo get(Long id);

	List<AwardDo> list();
	Tree<AwardDo> getTree();

	int save(AwardDo role);

	int update(AwardDo role);

	int remove(Long id);

	List<AwardDo> list(Long userId);

	int batchremove(Long[] ids);

	List<Long> getMajorIdsByAwardId(Long awardId);
	List<Long> getAwardIdsByUserId(Long userId);
}
