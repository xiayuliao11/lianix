package com.lsl.service;

import com.lsl.bean.Good;
import com.lsl.bean.ShopGood;
import com.lsl.bean.UserBean;
import com.lsl.histryx.GoodError;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "good-server",fallback = GoodError.class)
public interface GoodService {
    @RequestMapping("/good/queryGood")
    List<Good> queryGood();

    @GetMapping("/good/getGoodFromId")
    Good getGoodFromId(@RequestParam Integer id);

    @GetMapping("/good/getGoodFromMongo")
    List<ShopGood> getGoodFromMongo(@RequestParam Integer userId);
    
    @GetMapping("/login/getQuery")
    UserBean getQuery(@RequestParam String account, @RequestParam String userPswd);

    @GetMapping("/login/getUserBeanByPhone")
    UserBean getUserBeanByPhone(@RequestParam String phone_no);

    @PostMapping("/login/addUser")
    void addUser(@RequestBody UserBean userBean);

    @GetMapping("/good/queryCount")
    int queryCount(@RequestParam Integer id);

}
