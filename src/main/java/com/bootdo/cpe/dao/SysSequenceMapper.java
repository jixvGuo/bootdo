package com.bootdo.cpe.dao;

import com.bootdo.cpe.domain.SysSequence;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysSequenceMapper {
    /**
     * 根据 prefix 和 year 查询（加行锁 FOR UPDATE）
     */
    SysSequence selectForUpdate(@Param("prefix") String prefix, @Param("year") String year);

    /**
     * 插入新记录（首次使用该规则+年份）
     */
    int insert(SysSequence record);

    /**
     * 更新 current_val（原子递增）
     */
    int updateCurrentValue(@Param("id") Long id, @Param("newVal") Long newVal);
}
