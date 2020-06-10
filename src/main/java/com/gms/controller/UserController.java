package com.gms.controller;

import com.alibaba.fastjson.JSONObject;
import com.gms.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    com.gms.mapper.UserMapper userMapper;
    private List<User> users;

    @GetMapping("/users")
    public JSONObject getAllUser(){
        List<User> userList = new ArrayList<>();
        userList = userMapper.getAllUser();
        JSONObject object = new JSONObject();
        if(userList.size()!=0){
            object.put("message", "请求成功");
            object.put("code", 200);
        }else {
            object.put("message", "失败");
            object.put("code", 404);
        }
        object.put("users", userList);
        return object;
    }
}
