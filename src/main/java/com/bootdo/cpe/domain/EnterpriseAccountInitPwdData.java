package com.bootdo.cpe.domain;

/**
 * 企业账号初始密码记录
 *
 * @author houzb
 * @version 1.0
 * @date 2021-03-10 0:59
 */
public class EnterpriseAccountInitPwdData {
    private long uid;
    private String pwd;

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
