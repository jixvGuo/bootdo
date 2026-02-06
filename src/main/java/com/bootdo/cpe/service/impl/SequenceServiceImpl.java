package com.bootdo.cpe.service.impl;

import com.bootdo.cpe.dao.SysSequenceMapper;
import com.bootdo.cpe.domain.SysSequence;
import com.bootdo.cpe.service.SequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class SequenceServiceImpl implements SequenceService {

    @Autowired
    private SysSequenceMapper sequenceMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String generateSequence(String prefix) {
        String year = String.valueOf(LocalDateTime.now().getYear());
        String fullPrefix = prefix.toUpperCase(); // 可选：统一转大写

        // 1. 尝试查询现有记录（加锁）
        SysSequence seq = sequenceMapper.selectForUpdate(fullPrefix, year);

        long nextVal;
        if (seq == null) {
            // 2. 首次使用，插入初始记录（current_val = 1）
            seq = new SysSequence();
            seq.setPrefix(fullPrefix);
            seq.setYear(year);
            seq.setCurrentVal(1L);
            seq.setCreateDate(new Date());
            seq.setDelFlag("0");
            sequenceMapper.insert(seq);
            nextVal = 1;
        } else {
            // 3. 已存在，递增
            nextVal = seq.getCurrentVal() + 1;
            sequenceMapper.updateCurrentValue(seq.getId(), nextVal);
        }

        // 4. 格式化为 QC-2026-0001
        return String.format("%s-%s-%04d", fullPrefix, year, nextVal);
    }
}
