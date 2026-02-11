package com.bootdo.cpe.domain;

import java.io.Serializable;

public class QcGroupMember implements Serializable {

    private String proid;                // 项目ID
    private String Membername;           // 姓名
    private String idCardNumber;         // 身份证号
    private String responsibilities;     // 分工
    private String professionalTitle;    // 职称
    private String mailingAddress;
    private String locate;           // 地址
    private String certificateNumber;    // 证书
//
//    public QcGroupMember(String proid, String membername, String idCardNumber,
//                         String responsibilities, String professionalTitle,
//                         String mailingAddress, String certificateNumber) {
//        this.proid = proid;
//        this.membername = membername;
//        this.idCardNumber = idCardNumber;
//        this.responsibilities = responsibilities;
//        this.professionalTitle = professionalTitle;
//        this.mailingAddress = mailingAddress;
//        this.certificateNumber = certificateNumber;
//    }


    public String getProid() {
        return proid;
    }

    public void setProid(String proid) {
        this.proid = proid;
    }

    public String getLocate() {
        return locate;
    }

    public void setLocate(String locate) {
        this.locate = locate;
    }

    public String getMembername() {
        return Membername;
    }

    public void setMembername(String membername) {
        this.Membername = membername;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(String responsibilities) {
        this.responsibilities = responsibilities;
    }

    public String getProfessionalTitle() {
        return professionalTitle;
    }

    public void setProfessionalTitle(String professionalTitle) {
        this.professionalTitle = professionalTitle;
    }

    public String getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(String mailingAddress) {
        this.mailingAddress = mailingAddress;
    }


    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }
}
