package com.gms.controller;

import com.alibaba.fastjson.JSONObject;
import com.gms.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    com.gms.mapper.UserMapper userMapper;
    private List<User> users;

    @PostMapping("/login")
    public JSONObject login(@RequestBody Map user) {
        List<User> userList = new ArrayList<>();
        userList = userMapper.login(user.get("username").toString(), user.get("password").toString());
        JSONObject object = new JSONObject();
        System.out.println(userList);
        if (userList.size() != 0) {
            object.put("message", "登陆成功");
            object.put("code", 200);
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
}