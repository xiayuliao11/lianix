/**
 * <pre>项目名称:maven_dmo 
 * 文件名称:QueryParam.java 
 * 包名:com.jk.utils 
 * 创建日期:2018年12月17日下午10:10:30 
 * Copyright (c) 2018, All Rights Reserved.</pre> 
 */  
package com.lsl.utils;

import lombok.Data;

/**
 * 手机登录
 */
@Data
public class QueryParam {

    private String phone_no;//发送验证码的手机号
	
    private String verify_code;//用户输入的验证码
    
}
