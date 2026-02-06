package com.bootdo.system.service.impl;

import com.bootdo.common.domain.Tree;
import com.bootdo.common.utils.BuildTree;
import com.bootdo.system.dao.*;
import com.bootdo.system.domain.AwardDo;
import com.bootdo.system.domain.AwardMajorDo;
import com.bootdo.system.service.AwardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
public class AwardServiceImpl implements AwardService {

    public static final String ROLE_ALL_KEY = "\"award_all\"";

    public static final String DEMO_CACHE_NAME = "award";

    @Autowired
    AwardDao awardMapper;
    @Autowired
    RoleMenuDao roleMenuMapper;
    @Autowired
    UserDao userMapper;
    @Autowired
    UserRoleDao userRoleMapper;
    @Autowired
    AwardMajorDao awardMajorMapper;
    @Autowired
    UserAwardDao userAwardMapper;

    @Override
    public List<AwardDo> list() {
        List<AwardDo> roles = awardMapper.list(new HashMap<>(16));
        return roles;
    }

    @Override
    public Tree<AwardDo> getTree() {
        List<Tree<AwardDo>> trees = new ArrayList<Tree<AwardDo>>();
        List<AwardDo> awards = awardMapper.list(new HashMap<>(16));
        for (AwardDo award : awards) {
            Tree<AwardDo> tree = new Tree<AwardDo>();
            tree.setId(award.getId()+"");
            tree.setParentId("0");
            tree.setText(award.getAwardName());
            Map<String, Object> state = new HashMap<>(16);
            state.put("opened", true);
            tree.setState(state);
            trees.add(tree);
        }
        // 默认顶级菜单为０，根据数据库实际情况调整
        Tree<AwardDo> t = BuildTree.build(trees);
        return t;
    }

    @Override
    public List<AwardDo> list(Long userId) {
        List<Integer> rolesIds = userRoleMapper.listAwardId(userId);
        List<AwardDo> awards = awardMapper.list(new HashMap<>(16));
        for (AwardDo awardDO : awards) {
            for (Integer awardId : rolesIds) {
                if (awardId != null && awardDO.getId().longValue() == awardId) {
                    awardDO.setAwardSign("true");
                    break;
                }
            }
        }
        return awards;
    }
    @Transactional
    @Override
    public int save(AwardDo award) {
        int count = awardMapper.save(award);
//        List<Long> majorIds = award.getMajorIds();
        long awardId = award.getId();
        List<AwardMajorDo> ams = new ArrayList<>();
//        for(Long majorId:majorIds) {
//            if(majorId == null) {
//                continue;
//            }
//            AwardMajorDo amDo = new AwardMajorDo();
//            amDo.setAwardId(awardId);
//            amDo.setMajorDictId(majorId);
//            ams.add(amDo);
//        }
        awardMajorMapper.removeByAwardId(awardId);
        if(ams.size() > 0) {
            awardMajorMapper.batchSave(ams);
        }
        return count;
    }

    @Transactional
    @Override
    public int remove(Long id) {
        int count = awardMapper.remove(id);
        userRoleMapper.removeByRoleId(id);
        roleMenuMapper.removeByRoleId(id);
        return count;
    }

    @Override
    public AwardDo get(Long id) {
        AwardDo roleDO = awardMapper.get(id);
        return roleDO;
    }

    @Override
    public int update(AwardDo award) {
        int r = awardMapper.update(award);
//        List<Long> majorIds = award.getMajorIds();
        long awardId = award.getId();
        List<AwardMajorDo> ams = new ArrayList<>();
//        for(Long majorId:majorIds) {
//            if(majorId == null) {
//                continue;
//            }
//            AwardMajorDo amDo = new AwardMajorDo();
//            amDo.setAwardId(awardId);
//            amDo.setMajorDictId(majorId);
//            ams.add(amDo);
//        }
        awardMajorMapper.removeByAwardId(awardId);
        if(ams.size() > 0) {
            awardMajorMapper.batchSave(ams);
        }
        return r;
    }

    @Override
    public int batchremove(Long[] ids) {
        int r = awardMapper.batchRemove(ids);
        return r;
    }

    @Override
    public List<Long> getMajorIdsByAwardId(Long awardId) {
        List<Long> majorIds = awardMajorMapper.listMajorIdByAwardId(awardId);
        return majorIds;
    }

    @Override
    public List<Long> getAwardIdsByUserId(Long userId) {
        List<Long> awardIdList = userAwardMapper.listAwardId(userId);
        return awardIdList;
    }
}
