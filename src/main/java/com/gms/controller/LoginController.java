package com.gms.controller;

import com.gms.entity.User;
import com.gms.entity.trading;
import com.gms.mapper.tradingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class LoginController {
    @Autowired
    com.gms.mapper.userMapper userMapper;
    private List<User> users;

    @GetMapping("/login")
    public List<User> login(){
        List<User> userList = new ArrayList<>();
        userList = userMapper.getAllUser();
        return userList;
    }
}