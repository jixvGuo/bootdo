package com.bootdo.cpe.service.impl;

import com.bootdo.cpe.dao.QcGroupMemberDao;
import com.bootdo.cpe.domain.QcGroupMember;
import com.bootdo.cpe.service.QcGroupMemberService;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class QcGroupMemberServiceImpl implements QcGroupMemberService {

    @Autowired
    private QcGroupMemberDao qcGroupMemberDao;

    @Override
    public List<QcGroupMember> getByProid(String proid) {
        if (StringUtils.isBlank(proid)) {
            throw new IllegalArgumentException("proid 不能为空");
        }
        return qcGroupMemberDao.selectByProid(proid);
    }

    @Override
    public List<QcGroupMember> listAll() {
        return qcGroupMemberDao.selectAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(QcGroupMember member) {
        validate(member);

        QcGroupMember exist = qcGroupMemberDao.selectByProidAndIdCard(
                member.getProid(), member.getIdCardNumber()
        );

        if (exist != null) {
            return false;
        }

        return qcGroupMemberDao.insert(member) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(QcGroupMember member) {
        validate(member);
        return qcGroupMemberDao.update(member) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveOrUpdate(QcGroupMember member) {
        validate(member);

        QcGroupMember exist = qcGroupMemberDao.selectByProidAndIdCard(
                member.getProid(), member.getIdCardNumber()
        );

        return exist != null ? update(member) : save(member);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveBatch(List<QcGroupMember> list) {
        if (list == null || list.isEmpty()) {
            return false;
        }

        list.forEach(this::validate);

        return qcGroupMemberDao.batchInsert(list) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByProid(String proid) {
        if (StringUtils.isBlank(proid)) {
            throw new IllegalArgumentException("proid 不能为空");
        }
        return qcGroupMemberDao.deleteByProid(proid) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeOne(String proid, String idCardNumber) {
        return qcGroupMemberDao.deleteByProidAndIdCard(proid, idCardNumber) > 0;
    }

    private void validate(QcGroupMember member) {
        if (member == null) {
            throw new IllegalArgumentException("成员不能为空");
        }
        if (StringUtils.isBlank(member.getIdCardNumber())) {
            throw new IllegalArgumentException("身份证不能为空");
        }
//        if (StringUtils.isBlank(member.getMembername())) {
//            throw new IllegalArgumentException("姓名不能为空");
//        }
    }

    @Override
    public int importFromExcel(MultipartFile file, String proId) {
        int successCount = 0;

        try {
            Workbook workbook = WorkbookFactory.create(file.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 2; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                try {
                    QcGroupMember m = new QcGroupMember();
                    m.setProid(proId);
                    m.setMembername(getCell(row, 0));
                    m.setIdCardNumber(getCell(row, 1));
                    m.setResponsibilities(getCell(row, 2));
                    m.setProfessionalTitle(getCell(row, 3));
                    m.setMailingAddress(getCell(row, 4));
                    m.setLocate(getCell(row, 5));
//                    m.setCertificateNumber(getCell(row, 5));

                    qcGroupMemberDao.save(m);
                    successCount++;

                } catch (Exception e) {
                    e.printStackTrace(); // 单行失败不影响其他行
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Excel解析失败", e);
        }

        return successCount;
    }


    private String getCell(Row row, int index) {
        Cell cell = row.getCell(index);
        if (cell == null) return "";

        cell.setCellType(CellType.STRING);
        return cell.getStringCellValue().trim();
    }





    @Override
    public QcGroupMember getOne(String proId, String idCardNumber) {
        return qcGroupMemberDao.selectByProidAndIdCard(proId, idCardNumber);
    }

}
