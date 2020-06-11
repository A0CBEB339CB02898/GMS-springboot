package com.gms.mapper;

import com.gms.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    @Select("select * from User where username=#{username} and password=#{password}")
    List<User> login(String username, String password);

    @Insert("insert into User (username, password, phoneNum, posId, email) VALUES (#{username}, #{password}, #{phoneNum}, #{posId}, #{email});")
    int register(String username, String password, String phoneNum, int posId, String email);

    @Update("update User set password=#{password} where (username=#{username} and phoneNum=#{phoneNum}) or (username=#{username} and email=#{email})")
    int changePassword(@Param("password") String password, @Param("username") String username, @Param("phoneNum") String phoneNum, @Param("email") String email);

    @Select("select userId,username,phoneNum,posId,email,state from User")
    List<User> getAllUser();

    @Update("update User set posId=#{posId} where userId=#{userId}")
    int addManager(int posId, int userId);

    @Delete("delete from User where (userId=#{userId})")
    int deleteManager(int userId);

    @Select("select userId,username,phoneNum,posId,email,state from User where username=#{username}")
    List<User> queryManager(String username);

}