/** 
 * <pre>项目名称:ssh_page 
 * 文件名称:MyPage.java 
 * 包名:com.jk.wz.pojo 
 * 创建日期:2018-10-17下午2:42:47 
 * Copyright (c) 2018, wzstart@126.com All Rights Reserved.</pre> 
 */
package com.lsl.utils;

import java.util.List;

/**
 * <pre>
 * 项目名称：ssh_page    
 * 类名称：MyPage    
 * 类描述：    
 * 创建人：wzstart    
 * 创建时间：2018-10-17 下午2:42:47    
 * 修改人：wzstart  
 * 修改时间：2018-10-17 下午2:42:47    
 * 修改备注：       
 * @version
 * </pre>
 */
public class PageUtil {

	// 当前的页数
	private Integer pageNow;

	// 每页的条数
	private Integer pageSize;

	// 总页数
	private Integer countPage;

	// 总条数
	private Long countItem;

	/**
	 * <pre>
	 * 创建一个新的实例 PageUtil.
	 * </pre>
	 */
	public PageUtil() {
		super();
	}

	/**
	 * <pre>
	 * 创建一个新的实例 PageUtil.    
	 *    调用有参的构造方法创建对象的时候 此方法里的代码会被执行
	 * @param pageNow
	 * @param pageSize
	 * @param countItem
	 * </pre>
	 */
	public PageUtil(Integer pageNow, Integer pageSize, Long countItem) {
		super();

		// 如果用户没有传入查看当前的页数 默认查看第1页
		this.pageNow = pageNow == null ? 1 : pageNow;

		// 如果用户没有传入当前页面的条数 默认本页显示3条
		if (pageSize == null) {
			this.pageSize = 3;
		} else {
			this.pageSize = pageSize;
		}

		this.countItem = countItem;

		// 计算页数
		long countPageTemp = countItem / this.pageSize;

		// 判断有没有余数 如果有余数 页数就加1
		if (countItem % this.pageSize > 0) {
			countPageTemp = countPageTemp + 1;
		}

		// 给当前对象的 总页数赋值
		this.countPage = (int) countPageTemp;

		// 限制用户不能查看超过总页数的页数(大于总页数 或者 小于1 都重置为1 )
		if (this.pageNow > this.countPage || this.pageNow <= 0) {
			this.pageNow = 1;
		}
	}

	private List data;

	public List getData() {
		return data;
	}

	public void setData(List data) {
		this.data = data;
	}

	public Integer getPageNow() {
		return pageNow==null?1:pageNow;
	}

	public void setPageNow(Integer pageNow) {
		this.pageNow = pageNow;
	}

	public Integer getPageSize() {
		return pageSize==null?3:pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getCountPage() {
		return countPage;
	}

	public void setCountPage(Integer countPage) {
		this.countPage =  countPage;
	}

	public Long getCountItem() {
		return countItem;
	}

	public void setCountItem(Long countItem) {
		this.countItem = countItem;
	}

	/**
	 * 用来计算分页开始的条数
	 * 
	 * 开始的条数 :(当前页数-1)*pageSize +1
	 */
	public Integer getBeginItem() {
		int start = (pageNow - 1) * pageSize + 1;
		return start;
	}
}
