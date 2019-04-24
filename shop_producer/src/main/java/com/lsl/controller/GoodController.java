package com.lsl.controller;

import com.lsl.bean.Good;
import com.lsl.bean.NewGoods;
import com.lsl.bean.ShopGood;
import com.lsl.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("good")
public class GoodController{

    @Autowired
    GoodService service;

    @Resource
    MongoTemplate mongoTemplate;

    @ResponseBody
    @GetMapping("queryGood")
    public List<Good> queryGood() {

        return service.queryGood();
    }

    /**
     * 根据id查询商品信息
     * @param id
     * @return
     */
    @ResponseBody
    @GetMapping("getGoodFromId")
    public Good getGoodFromId(Integer id) {
        return service.getGoodFromId(id);
    }

    @ResponseBody
    @RequestMapping("getGoodFromMongo")
    public List<ShopGood> getGoodFromMongo(Integer userId){
        Query query = new Query();
        if(userId==null){
            userId=0;
        }
        query.addCriteria(Criteria.where("userid").is(userId));
        List<NewGoods> newGoods = mongoTemplate.find(query, NewGoods.class);
        List<ShopGood> Goodlist = null;
        for (NewGoods goodfor : newGoods) {
            Goodlist=goodfor.getList();
        }
        return Goodlist;
    }
    @ResponseBody
    @RequestMapping("queryCount")
    public int queryCount(Integer id){
       return service.queryCount(id);
    }

}
