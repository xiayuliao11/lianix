package com.lsl.service.impl;

import com.lsl.bean.UserBean;
import com.lsl.mapper.LoginMapper;
import com.lsl.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    LoginMapper loginMapper;

    /**
     * 查询用户是否存在
     * @param account
     * @param
     * @return
     */
    @Override
    public UserBean getQuery(String account, String userPswd) {
        return loginMapper.getQuery(account,userPswd);
    }

    @Override
    public UserBean getUser(UserBean user) {
        return loginMapper.getUser(user);
    }

    /**
     * 根据手机号码查询账户信息
     * @param phone_no
     * @return
     */
    @Override
    public UserBean getUserBeanByPhone(String phone_no) {
        return loginMapper.getUserBeanByPhone(phone_no);
    }

    @Override
    public void addUser(UserBean userBean) {
        loginMapper.addUser(userBean);
    }
}
