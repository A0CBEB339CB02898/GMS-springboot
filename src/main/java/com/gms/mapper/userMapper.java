package com.gms.mapper;

import com.gms.entity.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface userMapper {
    @Select("select * from User")
    List<User> getAllUser();
}