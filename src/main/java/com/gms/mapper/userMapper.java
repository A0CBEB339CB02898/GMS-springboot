package com.gms.mapper;

import com.gms.entity.User;
import com.gms.entity.trading;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface userMapper {
    @Select("select * from User where username=#{username} and password=#{password}")
    List<User> login(String username, String password);
}