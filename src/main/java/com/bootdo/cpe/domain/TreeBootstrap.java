package com.bootdo.cpe.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author houzb
 * @Description
 * @create 2020-10-10 4:07
 */
public class TreeBootstrap {

    private String text;
    private String href;
    //父节点的文本
    private String parentText;
    private List<TreeBootstrap> nodes;
    private Map<String, Object> extData;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getParentText() {
        return parentText;
    }

    public void setParentText(String parentText) {
        this.parentText = parentText;
    }

    public List<TreeBootstrap> getNodes() {
        return nodes;
    }

    public void setNodes(List<TreeBootstrap> nodes) {
        this.nodes = nodes;
    }

    public Map<String, Object> getExtData() {
        return extData;
    }

    public void setExtData(Map<String, Object> extData) {
        this.extData = extData;
    }

    public void putExtData(String key, String val) {
        if(this.extData == null) {
            this.extData = new HashMap<>();
        }
        this.extData.put(key, val);
    }
}
