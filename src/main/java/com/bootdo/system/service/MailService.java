package com.bootdo.system.service;

import com.bootdo.system.domain.MailDO;

/**
 * 邮件发送服务
 *
 * @author PC
 * @version 1.0
 * @date 2021-03-09 0:31
 */
public interface MailService {
    void sendTextMail(MailDO mailDO);

    void sendHtmlMail(MailDO mailDO,boolean isShowHtml);

    void sendTemplateMail(MailDO mailDO);
}
