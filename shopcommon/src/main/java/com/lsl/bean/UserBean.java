package com.lsl.bean;

import lombok.Data;

import java.io.Serializable;


@Data
public class UserBean implements Serializable {

   // private static final long serialVersionUID = 2025021531786005462L;
    private Integer id;  //用户ID
    private String  account;//账号
    private String  userName; //用户名
    private String  userPswd;  //用户密码
    private String  userNewPwd; //新密码
    private String  userPhone;//手机号
    private Integer  state;    //黑名单
    private String rememberPwd;  //记住密码

}
