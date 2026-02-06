package com.bootdo.system.domain;

import java.util.Map;

/**
 * 邮件信息
 *
 * @author houzb
 * @version 1.0
 * @date 2021-03-09 0:32
 */
public class MailDO {
    //标题
    private String title;
    //内容
    private String content;
    //接收人邮件地址
    private String[] email;
    //附加，value 文件的绝对地址/动态模板数据
    private Map<String, Object> attachment;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String[] getEmail() {
        return email;
    }

    public void setEmail(String[] email) {
        this.email = email;
    }

    public Map<String, Object> getAttachment() {
        return attachment;
    }

    public void setAttachment(Map<String, Object> attachment) {
        this.attachment = attachment;
    }
}
