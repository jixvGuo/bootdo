package com.bootdo.cpe.dao;

import com.bootdo.cpe.domain.QcGroupMember;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QcGroupMemberDao {

    List<QcGroupMember> selectByProid(@Param("proid") String proid);

    QcGroupMember selectByProidAndIdCard(@Param("proid") String proid,
                                         @Param("idCardNumber") String idCardNumber);

    List<QcGroupMember> selectAll();

    int insert(QcGroupMember member);

    int update(QcGroupMember member);

    int deleteByProid(@Param("proid") String proid);

    int deleteByProidAndIdCard(@Param("proid") String proid,
                               @Param("idCardNumber") String idCardNumber);

    int batchInsert(@Param("list") List<QcGroupMember> list);

    void save(QcGroupMember m) throws Exception;
}
