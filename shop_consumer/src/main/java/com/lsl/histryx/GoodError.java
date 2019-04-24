package com.lsl.histryx;

import com.lsl.bean.Good;
import com.lsl.bean.ShopGood;
import com.lsl.bean.UserBean;
import com.lsl.service.GoodService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GoodError implements GoodService {
    @Override
    public List<Good> queryGood() {
        ArrayList<Good> list = new ArrayList<>();
        Good good = new Good();
        good.setGoodNorms("连接网络超时,请重新启动");
        list.add(good);
        return list;
    }

    @Override
    public Good getGoodFromId(Integer id) {
        return null;
    }

    @Override
    public List<ShopGood> getGoodFromMongo(Integer userId) {
        return null;
    }

    @Override
    public UserBean getQuery(String account, String userPwd) {
        return null;
    }

    @Override
    public UserBean getUserBeanByPhone(String phone_no) {
        return null;
    }

    @Override
    public void addUser(UserBean userBean) {

    }

    @Override
    public int queryCount(Integer id) {
        return 0;
    }

}
