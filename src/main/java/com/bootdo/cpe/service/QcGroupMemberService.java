package com.bootdo.cpe.service;

import com.bootdo.cpe.domain.QcGroupMember;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface QcGroupMemberService {

    List<QcGroupMember> getByProid(String proid);

    List<QcGroupMember> listAll();

    boolean save(QcGroupMember member);

    boolean update(QcGroupMember member);

    boolean saveOrUpdate(QcGroupMember member);

    boolean saveBatch(List<QcGroupMember> list);

    boolean removeByProid(String proid);

    boolean removeOne(String proid, String idCardNumber);

    QcGroupMember getOne(String proId, String idCardNumber);

    int importFromExcel(MultipartFile file, String proId);
}
