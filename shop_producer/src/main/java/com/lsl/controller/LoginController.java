package com.lsl.controller;


import com.lsl.bean.UserBean;
import com.lsl.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("login")
public class LoginController{
    @Autowired
    LoginService loginService;

    /**
     * 查询用户是否存在
     * @param
     * @return
     */


    @ResponseBody
    @RequestMapping("getQuery")
    public UserBean getQuery(String account, String userPswd) {

        return loginService.getQuery(account,userPswd);
    }

    /**
     * 根据电话号码获取用户信息
     * @param phone_no
     * @return
     */

    @ResponseBody
    @RequestMapping("getUserBeanByPhone")
    public UserBean getUserBeanByPhone(String phone_no) {
        UserBean userBeanByPhone = loginService.getUserBeanByPhone(phone_no);
        return userBeanByPhone;
    }

    /**
     * 新增用户信息
     * @param userBean
     */

    @ResponseBody
    @PostMapping("addUser")
    public void addUser(@RequestBody UserBean userBean) {
        loginService.addUser(userBean);
    }

//    //@Override
//    @ResponseBody
//    @GetMapping("getUser")
//    public UserBean getUser(@RequestBody UserBean user) {
//        return loginService.getUser(user);
//    }
}
