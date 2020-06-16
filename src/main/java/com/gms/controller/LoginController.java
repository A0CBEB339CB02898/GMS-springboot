package com.gms.controller;

import com.alibaba.fastjson.JSONObject;
import com.gms.entity.Route;
import com.gms.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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
        userList = userMapper.login(user.get("username").toString(), md5Pass);
        System.out.println(user.get("username").toString()+"====="+md5Pass);
        JSONObject object = new JSONObject();
        System.out.println(userList);
        if (userList.size() != 0) {
            HttpSession session = request.getSession();
            session.setAttribute("user", userList.get(0));
            object.put("message", "登陆成功");
            object.put("code", 200);
            object.put("user",userList.get(0));
        } else {
            object.put("message", "失败");
            object.put("code", 404);
        }
        return object;
    }

    @PostMapping("/register")
    public JSONObject register(@RequestBody Map user){
        JSONObject object = new JSONObject();

        String md5Pass = DigestUtils.md5DigestAsHex(user.get("password").toString().getBytes());
        System.out.println(user.get("username").toString());
        int line = userMapper.register(user.get("username").toString(), md5Pass,user.get("phoneNum").toString(), 3, user.get("email").toString());
        if(line>=1){
            object.put("message", "注册成功");
            object.put("code", 200);
        }else {
            object.put("message", "注册失败");
            object.put("code", 404);
        }
        return object;
    }

    @PostMapping("/changePassword")
    public JSONObject changePassword(@RequestBody Map user){
        System.out.println(user.get("username"));
        JSONObject object = new JSONObject();
        String md5Pass = DigestUtils.md5DigestAsHex(user.get("password").toString().getBytes());
        System.out.println(md5Pass);
        int line = userMapper.changePassword(md5Pass, user.get("username").toString(), user.get("phoneNum").toString(), user.get("email").toString());
        System.out.println(line);
        if(line>=1){
            object.put("message", "修改成功");
            object.put("code", 200);
        }else {
            object.put("message", "信息有误");
            object.put("code", 404);
        }
        return object;
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
        int interval = request.getSession().getMaxInactiveInterval();
        System.out.println("session过期时间(s):" + interval);
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