package com.lsl.utils;

import java.util.List;


/**
 * 
 * <pre>项目名称：easyui-dome2    
 * 类名称：PageResult    
 * 类描述：    
 * 创建人：吕少利   
 * 创建时间：2018-11-29 上午11:49:10    
 * 修改人：吕少利      
 * 修改时间：2018-11-29 上午11:49:10    
 * 修改备注：       
 * @version </pre>
 */

  public class PageResult {

	private Integer total;
	
	private List  rows;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

	/* (non-Javadoc)    
	 * @see java.lang.Object#toString()    
	 */
	@Override
	public String toString() {
		return "PageResult [total=" + total + ", rows=" + rows + "]";
	}

	   
	/**    
	 * <pre>创建一个新的实例 PageResult.    
	 *    
	 * @param total
	 * @param rows</pre>    
	 */
	public PageResult(Integer total, List rows) {
		super();
		this.total = total;
		this.rows = rows;
	}

	   
	/**    
	 * <pre>创建一个新的实例 PageResult.    
	 *    </pre>    
	 */
	public PageResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	
   

	
}
