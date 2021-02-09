package com.bridge.common;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @ClassName: PageVo
 * @author zhenjianfei
 * @date 2020年1月20日
 * @company:TianJian
 */

public class PageRequest {
	@ApiModelProperty(value="页码",name="pageNum",example="1",required=true)
	private int pageNum;
	@ApiModelProperty(value="每页数目",name="pageSize",example="10",required=true)
	private int pageSize;


	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


}
