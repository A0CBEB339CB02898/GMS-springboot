package com.gms.mapper;

import com.gms.entity.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    @Select("select * from User where username=#{username} and password=#{password}")
    List<User> login(String username, String password);

    @Select("insert into User (username, password, phoneNum, posId, email) VALUES (#{username}, #{password}, #{phoneNum}, #{posId}, #{email});")
    String register(String username, String password, String phoneNum, int posId, String email);

    @Select("select * from User")
    List<User> getAllUser();
}