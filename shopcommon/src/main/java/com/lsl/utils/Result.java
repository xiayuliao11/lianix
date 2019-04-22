/**
 * <pre>项目名称:maven_dmo 
 * 文件名称:Result.java 
 * 包名:com.jk.utils 
 * 创建日期:2018年12月21日下午6:49:40 
 * Copyright (c) 2018, All Rights Reserved.</pre> 
 */  
package com.lsl.utils;


import com.lsl.bean.UserBean;
import lombok.Data;

/** 
 * 返回给前台的状态
 */
@Data
public class Result {
	//返回给前台的状态码
	private Integer code;
	
	private String msg;
	
	private Object data;
	
	private boolean state=true;

	private UserBean userBean;

}
