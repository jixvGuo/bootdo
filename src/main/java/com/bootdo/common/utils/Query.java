package com.bootdo.common.utils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 查询参数
 */
public class Query extends LinkedHashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	//
	private int offset;
	// 每页条数
	private int limit;

	public Query(Map<String, Object> params) {
		this.putAll(params);
		// 分页参数
		Object offsetObj = params.get("offset");
		Object limitObj = params.get("limit");
		this.offset = Integer.parseInt(offsetObj == null ? "0" : offsetObj.toString());
		this.limit = Integer.parseInt(limitObj == null ? "10" : limitObj.toString());
		this.put("offset", offset);
		this.put("page", offset / limit + 1);
		this.put("limit", limit);
		Object keyWordObj = params.get("keyWord");
		if(keyWordObj != null && StringUtils.isNotBlank(keyWordObj.toString())) {
			this.put("keyWord", "%" + keyWordObj + "%");
		}
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.put("offset", offset);
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
}
