package com.gms.controller;

import com.alibaba.fastjson.JSONObject;
import com.gms.entity.User;
import com.gms.entity.trading;
import com.gms.mapper.tradingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    com.gms.mapper.userMapper userMapper;
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
}