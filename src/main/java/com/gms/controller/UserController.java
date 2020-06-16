package com.gms.controller;

import com.alibaba.fastjson.JSONObject;
import com.gms.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    com.gms.mapper.UserMapper userMapper;
    private List<User> users;

    @GetMapping("/users")
    public JSONObject getUser(int page){
        List<User> userList = new ArrayList<>();
        userList = userMapper.getUser(page*10);
        int totalPage = (int)(Math.ceil(userMapper.countUsers()/10));
        JSONObject object = new JSONObject();
        if(userList.size()!=0){
            object.put("message", "请求成功");
            object.put("code", 200);
        }else {
            object.put("message", "失败");
            object.put("code", 404);
        }
        object.put("users", userList);
        object.put("totalPage", totalPage);
        return object;
    }

    @PostMapping("/manageUser/add")
    public JSONObject addManager(@RequestBody Map user){
        JSONObject object = new JSONObject();
        int posId = Integer.parseInt(String.valueOf(user.get("posId")));
        int userId = Integer.parseInt(String.valueOf(user.get("userId")));
        int line = userMapper.addManager(posId, userId);
        if(line>=1){
            object.put("message", "修改成功");
            object.put("code", 200);
        }else {
            object.put("message", "修改失败");
            object.put("code", 404);
        }
        return object;
    }

    @PostMapping("/manageUser/delete")
    public JSONObject deleteManager(@RequestBody Map user){
        JSONObject object = new JSONObject();
        int userId = Integer.parseInt(String.valueOf(user.get("userId")));
        int line = userMapper.deleteManager(userId);
        if(line>=1){
            object.put("message", "删除成功");
            object.put("code", 200);
        }else {
            object.put("message", "删除失败");
            object.put("code", 404);
        }
        return object;
    }

    @GetMapping("/manageUser/query")
    public JSONObject queryManager(String username){
        JSONObject object = new JSONObject();
        System.out.println(username);
        List<User> users = new ArrayList<>();
        users = userMapper.queryManager(username);
        if(users.size()!=0){
            object.put("message", "查询成功");
            object.put("code", 200);
        } else {
            object.put("message", "未找到结果");
            object.put("code", 404);
        }
        object.put("users",users);
        return object;
    }
}
