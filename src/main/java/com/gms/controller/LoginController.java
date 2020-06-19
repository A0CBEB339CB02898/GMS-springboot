package com.gms.controller;

import com.alibaba.fastjson.JSONObject;
import com.gms.entity.Route;
import com.gms.entity.User;
import com.gms.utils.ValidateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    com.gms.mapper.UserMapper userMapper;
    private List<User> users;

    @PostMapping("/login")
    public JSONObject login(@RequestBody Map user, HttpServletRequest request) {
        List<User> userList = new ArrayList<>();
        String md5Pass = DigestUtils.md5DigestAsHex(user.get("password").toString().getBytes());
        Object code = user.get("code");
        HttpSession session = request.getSession();
        Object ValidateCode = session.getAttribute("ValidateCode");
        System.out.println(code);
        System.out.println(ValidateCode);
        System.out.println(code.equals(ValidateCode));
        userList = userMapper.login(user.get("username").toString(), md5Pass);
        System.out.println(user.get("username").toString()+"====="+md5Pass);
        JSONObject object = new JSONObject();
        System.out.println(userList);
        if (userList.size() != 0 ) {
            session.setAttribute("user", userList.get(0));
            if(code.equals(ValidateCode)){
                object.put("message", "登陆成功");
                object.put("code", 200);
                object.put("user",userList.get(0));
            }else {
                object.put("message", "验证码错误");
                object.put("code", 400);
            }
        } else {
            object.put("message", "失败");
            object.put("code", 404);
        }
        return object;
    }

    @PostMapping("/register")
    public JSONObject register(@RequestBody Map user, HttpServletRequest request){
        JSONObject object = new JSONObject();
        int line=0;
        String md5Pass = DigestUtils.md5DigestAsHex(user.get("password").toString().getBytes());
        Object code = user.get("code");
        HttpSession session = request.getSession();
        Object ValidateCode = session.getAttribute("ValidateCode");

        if(code.equals(ValidateCode)) {
            try {
                line = userMapper.register(user.get("username").toString(), md5Pass, user.get("phoneNum").toString(), 3, user.get("email").toString());
                if (line >= 1) {
                    object.put("message", "注册成功");
                    object.put("code", 200);
                } else {
                    object.put("message", "注册失败");
                    object.put("code", 404);
                }
            } catch (Exception e) {
                object.put("message", "注册失败");
                object.put("code", 404);
            }
        }else {
            object.put("message", "验证码有误");
            object.put("code", 400);
        }

        return object;
    }

    @PostMapping("/changePassword")
    public JSONObject changePassword(@RequestBody Map user,HttpServletRequest request){
        System.out.println(user.get("username"));
        JSONObject object = new JSONObject();
        String md5Pass = DigestUtils.md5DigestAsHex(user.get("password").toString().getBytes());
        Object code = user.get("code");
        HttpSession session = request.getSession();
        Object ValidateCode = session.getAttribute("ValidateCode");

        if(code.equals(ValidateCode)) {
            try {
                int line = userMapper.changePassword(md5Pass, user.get("username").toString(), user.get("phoneNum").toString(), user.get("email").toString());
                if (line >= 1) {
                    {
                        object.put("message", "修改成功");
                        object.put("code", 200);
                    }
                } else {
                    object.put("message", "信息有误");
                    object.put("code", 404);
                }
            }catch (Exception e){
                object.put("message", "修改失败");
                object.put("code", 404);
            }

        }else{
            object.put("message", "验证码错误");
            object.put("code", 400);
        }
        return object;
    }

    /**
     * 获取验证码
     */
    @RequestMapping("/getCaptchaImage")
    @ResponseBody
    public JSONObject getCaptchaBase64(HttpServletRequest request, HttpServletResponse response) {

        JSONObject result = new JSONObject();

        try {

            response.setContentType("image/png");
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Expire", "0");
            response.setHeader("Pragma", "no-cache");

            ValidateCode validateCode = new ValidateCode();

            // 直接返回图片
            // validateCode.getRandomCode(request, response);

            // 返回base64
            String base64String = validateCode.getRandomCodeBase64(request, response);
            result.put("url", "data:image/png;base64," + base64String);
            result.put("message", "created successfull");

        } catch (Exception e) {
            System.out.println(e);
        }

        return result;
    }

    /**
     * 获取session
     */
    @GetMapping("/getSession")
    public JSONObject getSession(HttpServletRequest request){
        JSONObject object = new JSONObject();
        if(request.isRequestedSessionIdValid()){
            object.put("message","获取session成功");
            object.put("code", "200");
        }else {
            object.put("message", "获取session失败");
            object.put("code", 404);
        }
        object.put("user", request.getSession().getAttribute("user"));
        object.put("routes", request.getSession().getAttribute("routes"));
        return object;
    }

    /**
     * 注销登录
     * @param session
     * @return
     */
    @GetMapping("/logout")
    public JSONObject logout(HttpSession session){
        session.invalidate();//使Session变成无效，及用户退出
        JSONObject object = new JSONObject();
        object.put("message","注销成功");
        object.put("code", "200");
        return object;
    }

    @GetMapping("/getRoutes")
    public JSONObject getRoutes(int posId, HttpServletRequest request){
        JSONObject object = new JSONObject();
        List<Route> children = new ArrayList<>();
        if(posId==1 || posId ==2){
            children = userMapper.getAllRoutes();
        }else {
            children = userMapper.getRoutes(3);
        }
        HttpSession session = request.getSession();
        object.put("path","/dashboard");
        object.put("component", "Dashboard");
        object.put("children", children);
        session.setAttribute("routes", object);
        return object;
    }
}