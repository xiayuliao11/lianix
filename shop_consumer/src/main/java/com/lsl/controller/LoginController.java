package com.lsl.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.alibaba.fastjson.JSONObject;
import com.lsl.bean.UserBean;
import com.lsl.service.GoodService;
import com.lsl.utils.ConstanType;
import com.lsl.utils.HttpClient;
import com.lsl.utils.QueryParam;
import com.lsl.utils.Result;

import org.apache.tomcat.util.bcel.classfile.Constant;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.BreakIterator;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;


@Controller
@RequestMapping("login")
public class LoginController {

   private static Logger log= LoggerFactory.getLogger(LoginController.class);

    @Autowired
    GoodService goodService;

    //注入redis
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @ResponseBody
    @RequestMapping(value = "getQuery",method = RequestMethod.GET)
    public UserBean getQuery(String account, String userPswd, HttpSession session, HttpServletResponse response, HttpServletRequest request){
        UserBean userBean= goodService.getQuery(account,userPswd);
        if(userBean==null){
            log.debug("用户名密码错误");
            log.info("用户名密码错误");
            return null;
        }else {
            log.debug("人员登录信息");
            log.info("人员登录信息");
            return userBean;
        }
//        //　要想在cookie中存储中文，那么必须使用URLEncoder类里面的encode(String s, String enc)方法进行中文转码，例如：
//        //　　在获取cookie中的中文数据时，再使用URLDecoder类里面的decode(String s, String enc)进行解码，例如：
//        String jsonString = JSONObject.toJSONString(userBean);
//        String encode = null;
//        try {
//            encode = URLEncoder.encode(jsonString,"UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }



    }

//    @ResponseBody
//    @RequestMapping(value = "getUser",method = RequestMethod.GET)
//    public String getUser(UserBean user){
//        UserBean userBean =loginServiceDome.getUser(user);
//        return "1";
//    }
    /**
     * 注销
     */
    @ResponseBody
    @RequestMapping("logout")
    public void logout(HttpSession session){
           session.invalidate();
    }
    /**
     * 用手机号获得code
     *
     */
    @RequestMapping("getRandomCode")
    @ResponseBody
    public Result getRandomCode(QueryParam queryParam, HttpSession session){
        Result result = new Result();
        //根据手机获取用户
        UserBean userBeanByPhone=goodService.getUserBeanByPhone(queryParam.getPhone_no());
        session.setAttribute("userBean",userBeanByPhone);

        if(userBeanByPhone!=null&&userBeanByPhone.getState()==1){
            //每次都从redis中取一下当前手机号 如果有值 说明是刷新界面多次获取验证码
            Boolean aBoolean = redisTemplate.hasKey(ConstanType.multiple_code + queryParam.getPhone_no());
            //两次获取验证码的时间没有超过40s
            if(aBoolean){
                result.setCode(2);   //对象已存在
                return  result;
            }else{

                //产生一个6位随机验证码
                int random = (int)(Math.random() * 899999 + 100000);
                Long count = redisTemplate.opsForValue().increment(ConstanType.phone_code + queryParam.getPhone_no(), 1);
                if(count>3){
                    Duration duration = Duration.between(LocalDateTime.now(), LocalDate.now().plusDays(1).atTime(0, 0, 0));
                    redisTemplate.expire(ConstanType.phone_code+queryParam.getPhone_no(),duration.toMinutes(),TimeUnit.SECONDS);
                    result.setCode(4);
                    return result;
                }//发送次数达到上限
                System.out.println(random);
                // 调用接口发送验证码(60s不能重复发送 24h只能发三次)
                HashMap<String, Object> params = new HashMap<>();
                params.put("key","a171d11f051086a8985d078b02156c8d");//接口名称上查看
                params.put("mobile",queryParam.getPhone_no());//输入的手机号码
                params.put("tpl_id","124703");//短信模板id
                params.put("tpl_value","%23code%23%3d"+random);//变量名和变量值对

                String sendGet = HttpClient.sendGet("http://v.juhe.cn/sms/send", params);
                //把当前的手机号存到redis中 并设置40秒的过期时间  判断是否多次登录
                //把验证码放到缓层中
                redisTemplate.opsForValue().set(ConstanType.multiple_code+queryParam.getPhone_no(),random+"",2,TimeUnit.MINUTES);
                result.setCode(1);   //不存在
                return result;
            }
        }else{
            result.setCode(0);//在黑名单
            return result;
        }

    }
    /**
     * 验证验证码是否正确
     */
    @ResponseBody
    @RequestMapping("checkCode")
    public Result checkCode(QueryParam queryParam,HttpSession session){
        Result result=new Result();
        //用户当前输入的验证码
        String verify_code = queryParam.getVerify_code();
        // 从Redis获取验证码
        String codefromredis = redisTemplate.opsForValue().get(ConstanType.multiple_code + queryParam.getPhone_no());
      //比对验证码 如果用户输入的验证码正确则登录
        if(codefromredis!=null&&verify_code.equals(codefromredis)){
            // 登录成功 根据手机号 查询出用户的信息 保存到session
            UserBean userBeanByPhone=goodService.getUserBeanByPhone(queryParam.getPhone_no());
            //必须以user为key 不然拦截器会认为没有登录
            session.setAttribute("user", userBeanByPhone);
            //删除存在的code存在的key值
          //  redisTemplate.delete(ConstanType.phone_code+queryParam.getPhone_no());
            result.setUserBean(userBeanByPhone);
            return result;//校验成功
        }else{

           result.setCode(0);
           return result;//检验失败
        }
    }
    @ResponseBody
    @RequestMapping("registerUser")
    public String addUser(UserBean userBean){
        goodService.addUser(userBean);
        return "1";
    }
       @ResponseBody
       @RequestMapping("getLoginUser")
       public UserBean getLoginUser(HttpSession session){
       UserBean user = (UserBean) session.getAttribute("userBean");
       return user;
   }
}
