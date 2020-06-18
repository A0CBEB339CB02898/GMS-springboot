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

    @PostMapping("manageUser/edit")
    public JSONObject editManager(@RequestBody Map user){
        JSONObject object = new JSONObject();
        int posId = Integer.parseInt(String.valueOf(user.get("posId")));
        int userId = Integer.parseInt(String.valueOf(user.get("userId")));
        System.out.println("posId===="+posId);
        System.out.println("userId==="+userId);
        int line = 0;
        try {
            line = userMapper.editUser(user.get("username").toString(),user.get("phoneNum").toString(),user.get("email").toString(),posId, userId);
        } catch (Exception e){
            object.put("message", "修改失败");
            object.put("code", 404);
        }
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

    /**
     * 动态查询 value=-1即不查询
     * @param userId
     * @param username
     * @return
     */
    @GetMapping("/manageUser/query")
    public JSONObject queryManager(String userId,String username, String phoneNum, String email){
        JSONObject object = new JSONObject();
        final int notSearch=-1;
        int conditionCount=0;
        int managerCount = 0;
        double queryManagerCount = 0;
        int Id;
        User userSearchById = new User();
        List<User> managerList = new ArrayList<>();
        List<User> userListSearchById = new ArrayList<>();
        List<Integer> userIdSearchByName = new ArrayList<>();
        List<Integer> userIdSearchByPhoneNum = new ArrayList<>();
        List<Integer> userIdSearchByEmail = new ArrayList<>();
        List<Integer> userIdList = new ArrayList<>();
        List<Integer> realUserIdList = new ArrayList<>();

        System.out.println(email);

        if(userId!=""){
            Id = Integer.parseInt(userId);
        }else {
            Id=-1;
        }

        //根据用户名查询
        if(username!=""&&userId==""){
            conditionCount++;
            try {
                userIdSearchByName = userMapper.queryManagerByName(username);
                for (int i=0;i<userIdSearchByName.size();i++){
                    userIdList.add(userIdSearchByName.get(i));
                }
            } catch (NullPointerException e){
                object.put("message", e);
                object.put("code", 404);
            }
            object.put("message", "查询成功");
            object.put("code", 200);
            object.put("totalPage",1);
        }

        //根据手机号查询
        if(phoneNum!=""&&userId==""){
            conditionCount++;
            try {
                userIdSearchByPhoneNum = userMapper.queryManagerByPhoneNum(phoneNum);
                System.out.println(userIdSearchByPhoneNum.size());
                for (int i=0;i<userIdSearchByPhoneNum.size();i++){
                    userIdList.add(userIdSearchByPhoneNum.get(i));
                }
            } catch (NullPointerException e){
                object.put("message", e);
                object.put("code", 404);
            }
        }

        //根据邮箱查询
        if(email!=""&&userId==""){
            conditionCount++;
            try {
                userIdSearchByEmail = userMapper.queryManagerByEmail(email);
                System.out.println(userIdSearchByEmail.size());
                for (int i=0;i<userIdSearchByEmail.size();i++){
                    userIdList.add(userIdSearchByEmail.get(i));
                }
            } catch (NullPointerException e){
                object.put("message", e);
                object.put("code", 404);
            }
        }



        //根据Id查询
        if(userId!=""){
            System.out.println(Id);
            try {
                userSearchById = userMapper.queryManagerById(Id);
            } catch (NullPointerException e){
                object.put("message", e);
                object.put("code", 404);
            }
            if(userSearchById!=null){
                object.put("message", "查询成功");
                object.put("code", 200);
                object.put("users",userSearchById);
                object.put("totalPage", 1);
            }else {
                object.put("message", "未找到结果");
                object.put("code", 404);
            }
        }else if(conditionCount==0){
            try {
                managerList = userMapper.queryAllManager();
                managerCount = (int)(Math.ceil(userMapper.countManager()/10));
            } catch (NullPointerException e){
                object.put("message", e);
                object.put("code", 404);
            }
            System.out.println(managerCount);
            object.put("message", "查询成功");
            object.put("code", 200);
            object.put("users",managerList);
            object.put("totalPage",managerCount);
        }else {
            System.out.println("conditionCount===="+conditionCount);
            if(conditionCount>1){
                //根据之前查找到到id数组做处理
                for (int i =0;i<userIdList.size();i++){
                    for (int a=i+1;a<userIdList.size();a++){
                        if(userIdList.get(i)==userIdList.get(a)){
                            realUserIdList.add(userIdList.get(i));
                        }
                    }
                }
                if(realUserIdList.size()==0){
                    object.put("message", "未找到结果");
                    object.put("code", 404);
                }else {
                    for (int i = 0; i < realUserIdList.size(); i++) {
                        userSearchById = userMapper.queryManagerById(realUserIdList.get(i));
                        userListSearchById.add(userSearchById);
                        object.put("message", "查询成功");
                        object.put("code", 200);
                        object.put("users", userListSearchById);
                    }
                }
            }else {
                System.out.println(userIdList.size());
                for (int i = 0; i < userIdList.size(); i++) {
                    userSearchById = userMapper.queryManagerById(userIdList.get(i));
                    queryManagerCount = i+1;
                    userListSearchById.add(userSearchById);
                }
                object.put("message", "查询成功");
                object.put("code", 200);
                object.put("users", userListSearchById);
                object.put("totalPage", (int)(Math.ceil(queryManagerCount/10)));
            }
        }


        return object;
    }
}
