package com.bridge.common;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @ClassName: PageVo
 * @author zhenjianfei
 * @date 2020年1月20日
 * @company:TianJian
 */

public class PageResponse<T>   implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="数据",name="PageData")
	private List<T> PageData;
	@ApiModelProperty(value="总数",name="total")
	private long total;

	public List<T> getPageData() {
		return PageData;
	}

	public void setPageData(List<T> pageData) {
		PageData = pageData;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}
}
