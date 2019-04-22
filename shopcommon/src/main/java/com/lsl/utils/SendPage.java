/** 
 * <pre>项目名称:easyui-dome2 
 * 文件名称:SendPage.java 
 * 包名:com.jk.utils 
 * 创建日期:2018-11-29上午11:55:38 
 * Copyright (c) 2018, All Rights Reserved.</pre> 
 */  
package com.lsl.utils;



/** 
 * <pre>项目名称：easyui-dome2    
 * 类名称：SendPage    
 * 类描述：    
 * 创建人：吕少利   
 * 创建时间：2018-11-29 上午11:55:38    
 * 修改人：吕少利      
 * 修改时间：2018-11-29 上午11:55:38    
 * 修改备注：       
 * @version </pre>    
 */

public class SendPage {

	private Integer page;
	private Integer rows;
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	/* (non-Javadoc)    
	 * @see java.lang.Object#toString()    
	 */
	@Override
	public String toString() {
		return "SendPage [page=" + page + ", rows=" + rows + "]";
	}

	
}
