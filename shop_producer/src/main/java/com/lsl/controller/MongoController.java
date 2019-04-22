package com.lsl.controller;

import com.alibaba.fastjson.JSONObject;
import com.lsl.bean.Good;
import com.lsl.bean.NewGoods;
import com.lsl.bean.ShopGood;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MongoController {
    @Resource
    MongoTemplate mongoTemplate;
    @RabbitListener(queues="good")
    public void addGood(String good) {
        //从消息队列中获取数据
        JSONObject jsonObject = JSONObject.parseObject(good);
        Good goodmo = jsonObject.toJavaObject(Good.class);
         //新建一个条件查询
        Query query = new Query();
        NewGoods newGoods = new NewGoods();
        List<ShopGood> list = new ArrayList<>();
        //创建一个对象
        ShopGood goodToMongo = new ShopGood();
          goodToMongo.setId(goodmo.getId());
          goodToMongo.setGoodName(goodmo.getGoodName());
          goodToMongo.setGoodCount(goodmo.getGoodCount());
          goodToMongo.setGoodImg(goodmo.getGoodImg());
          goodToMongo.setGoodNorms(goodmo.getGoodNorms());
          goodToMongo.setGoodPrice(goodmo.getGoodPrice());
          goodToMongo.setGoodTime(goodmo.getGoodTime());
          goodToMongo.setGoodVender(goodmo.getGoodVender());
        //判断用户是否登录,不登录设置为0
        if(goodmo.getUserId()==null){
            goodmo.setUserId(0);
        }
        //将用户的id存放到要存入MongoDB的表中
        newGoods.setUserid(goodmo.getUserId());
        //创建条件查询
        query.addCriteria(Criteria.where("userid").is(goodmo.getUserId()));
        //从MongoDB数据库中查询数据
        List<NewGoods> newGoods1 = mongoTemplate.find(query, NewGoods.class);
        //判断数据库中是否存在数据
        if(newGoods1.size()!=0){
            //如果存在删除数据库中的数据
            mongoTemplate.remove(query, NewGoods.class);
            //遍历数据库中数据,取出存放里面的list集合
            List<ShopGood> goodList=null;
            for (NewGoods goodfor : newGoods1) {
                goodList=goodfor.getList();
            }
            //定义临时变量
            ShopGood mongdb=null;
            int count=0;
            for (ShopGood shopGood : goodList) {
                //判断商品id是否一致,若一致向list集合添加商品,并改变临时变量count数量                      1,2
                if(goodToMongo.getId()==shopGood.getId()){
                    list.add(shopGood);
                         count++;
                }//判断商品id是否一致,集合商品id是否为空
                else if(goodToMongo.getId()!=shopGood.getId() && shopGood.getId()!=null){
                    list.add(shopGood);
                }//若商品id与集合商品id是否一致 不一致给对象赋值
                if(goodToMongo.getId()!=shopGood.getId()){
                    mongdb=goodToMongo;
                }
            }
            //判断临时变量的值是否改变 若没有改变则添加值
            if(count==0){
                list.add(mongdb);
            }

        }else{
            //若集合为空则直接添加到list
            list.add(goodToMongo);
        }

         newGoods.setList(list);
        //存入到mongdb
        mongoTemplate.insert(newGoods);
    }
}
