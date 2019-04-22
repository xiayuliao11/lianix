package com.lsl.controller;

import com.alibaba.fastjson.JSONObject;
import com.lsl.bean.Good;
import com.lsl.bean.Shop;
import com.lsl.bean.ShopGood;
import com.lsl.service.GoodService;
import com.lsl.utils.Content;
import org.frameworkset.elasticsearch.ElasticSearchException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("to")
public class GoodController {
    @Autowired
    GoodService goodService;


    @Resource
    private RedisTemplate<String, List<Shop>> redisTemplate;

    @Autowired
    AmqpTemplate amqpTemplate;




    @ResponseBody
    @RequestMapping("getGood")
    public List<Good> queryGood(){
        List<Good> list=goodService.queryGood();
        return list;
    }
    @RequestMapping("tovivw")
    public String tovivw(String name){
        return name;
    }
    @RequestMapping("tologin")
    public String tologin(){
        return "login";
    }
    @RequestMapping("toregister")
    public String toregister(){
        return "register";
    }
    @RequestMapping("toshow")
    public String toshow(){
        return "show";
    }
    @RequestMapping("toshopcar")
    public String toshopcar(){
        return "shop";
    }
    private void setCookie(HttpServletResponse response, int time, String cookieKey) {

        Cookie cookie = new Cookie(Content.uuid, cookieKey);
        //设置在根目录就可以访问
        cookie.setPath("/");
        //设置过期时间
        cookie.setMaxAge(time);
        //将自定义的cookie放到响应头  存放在浏览器内存中
        response.addCookie(cookie);

    }

    @ResponseBody
    @RequestMapping("updateStart")
    public String updateStart(Shop good, HttpServletResponse response, HttpServletRequest request){
        List<Shop> list = new ArrayList<>();
        Shop badGood=null;
        //生成的uuId 存入cooik
        setCookie(response,1800, Content.uuid);
        if(good.getUserId()==0){
            if(redisTemplate.hasKey(Content.uuid)){
                //直接取出key对应的value
                list = redisTemplate.opsForValue().get(Content.uuid);
                //使用iterator()迭代遍历
                Iterator<Shop> iterator = list.iterator();
                //判断是否有下个元素
                if(iterator.hasNext()){
                    //当前循环到的对象
                    badGood=iterator.next();
                    //如果redis中id有与传过来的对象id 名称一致的
                    if(badGood.getId().equals(good.getId())){
                        //   //修改redis仓库的数量 加上前台传过来的数量
                        badGood.setGoodCount(badGood.getGoodCount()+good.getGoodCount());
                        //将之前的对象移除
                        iterator.remove();
                        //将新对象添加到list集合
                        list.add(badGood);
                    }else{

                        //如果redis中对象id有与传过来的对象id名称不一致
                        list.add(good);
                    }
                }
                //将该集合存放到redis中
                redisTemplate.opsForValue().set(Content.uuid,list,30, TimeUnit.MINUTES);
            }else {
                //如果不存在直接将对象添加到list,存进Redis
                list.add(good);
                redisTemplate.opsForValue().set(Content.uuid, list, 30, TimeUnit.MINUTES);
            }
        }else{
            if(redisTemplate.hasKey(Content.uuid+good.getUserId())){
                //直接取出key对应的value
                list = redisTemplate.opsForValue().get(Content.uuid+good.getUserId());
                //使用iterator()迭代遍历
                Iterator<Shop> iterator = list.iterator();
                //判断是否有下个元素
                if(iterator.hasNext()){
                    //当前循环到的对象
                    badGood=iterator.next();
                    //如果redis中id有与传过来的对象id 名称一致的
                    if(badGood.getId().equals(good.getId())){
                        //   //修改redis仓库的数量 加上前台传过来的数量
                        badGood.setGoodCount(badGood.getGoodCount()+good.getGoodCount());
                        //将之前的对象移除
                        iterator.remove();
                        //将新对象添加到list集合
                        list.add(badGood);
                    }else{

                        //如果redis中对象id有与传过来的对象id名称不一致
                        list.add(good);
                    }
                }
                //将该集合存放到redis中
                redisTemplate.opsForValue().set(Content.uuid+good.getUserId(),list,30, TimeUnit.MINUTES);
            }else {
                //如果不存在直接将对象添加到list,存进Redis
                list.add(good);
                redisTemplate.opsForValue().set(Content.uuid+good.getUserId(), list, 30, TimeUnit.MINUTES);
            }
        }

        Good goodMongo= goodService.getGoodFromId(good.getId());
        goodMongo.setUserId(good.getUserId());
        //String string = goodMongo.toString();
        //将对象转化为json对象
        String string = JSONObject.toJSONString(goodMongo);
        amqpTemplate.convertAndSend("good",string);
        return "1";
    }
    @ResponseBody
    @RequestMapping("getShop")
    public List<Good> getShop(Good good,HttpServletRequest request, HttpSession session){
        List<Shop> list=null;
        List<Good> goodlist=new ArrayList<>();
        List<ShopGood> mongo = goodService.getGoodFromMongo(good.getUserId());
        //获取所有的KEY
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
                //循环遍历判断cookies对应的Key是否存在
                for (Cookie cookie : cookies) {
                    if (good.getUserId() == 0) {
                        //如果cookie中存在key 查看Redis是否也存在key
                        if (redisTemplate.hasKey(cookie.getName())) {
                            list = redisTemplate.opsForValue().get(cookie.getName());
                        }
                    }else {
                        //如果cookie中存在key 查看Redis是否也存在key
                        if (redisTemplate.hasKey(cookie.getName()+good.getUserId())) {
                            list = redisTemplate.opsForValue().get(cookie.getName()+good.getUserId());
                        }
                    }
                }
        }
        if(list!=null&&mongo!=null){
            for (Shop shop : list) {
                for (ShopGood shopGood : mongo) {
                    Good good1 = new Good();
                    if(shop.getId().equals(shopGood.getId())){
                        good1.setId(shopGood.getId());
                        good1.setGoodName(shopGood.getGoodName());
                        good1.setGoodCount(shop.getGoodCount());
                        good1.setGoodImg(shopGood.getGoodImg());
                        good1.setGoodTime(shopGood.getGoodTime());
                        good1.setGoodVender(shopGood.getGoodVender());
                        good1.setGoodNorms(shopGood.getGoodNorms());
                        good1.setUserId(shop.getUserId());
                        good1.setGoodPrice(shopGood.getGoodPrice());
                        good1.setHj(shop.getGoodCount()*shopGood.getGoodPrice());
                        goodlist.add(good1);
                    }
                }
            }
        }
        System.out.println(goodlist);
        return goodlist;
    }
}
