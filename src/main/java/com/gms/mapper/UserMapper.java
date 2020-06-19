package com.gms.mapper;

import com.gms.entity.Route;
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

    @Select("select * from User where state=1 limit #{offset},10")
    List<User> getUser(int offset);

    @Select("select count(*) from GMSdb.User where state=1;")
    double countUsers();

    @Update("insert into User (username, password, phoneNum, posId, email) VALUES (#{username}, #{password}, #{phoneNum}, #{posId}, #{email});")
    int addManager(String username, String password, String phoneNum, int posId, String email);

    @Update("update User set state=0 where userId=#{userId}")
    int deleteManager(int userId);

    @Select("select * from GMSdb.User where state=0")
    List<User> getDeletedUsers();

    @Select("select count(*) from GMSdb.User where state=0")
    double countDeletedUsers();

    @Update("update User set state=1 where userId=#{userId}")
    int rollbackUser(int userId);

    @Select("select * from GMSdb.User where posId=2 and state=1")
    List<User> queryAllManager();

    @Select("select count(*) from GMSdb.User where state=1 and posId=2;")
    double countManager();

    @Update("update GMSdb.User set username=#{username},phoneNum=#{phoneNum},email=#{email},posId=#{posId} where userId=#{userId}")
    int editUser(String username, String phoneNum, String email, int posId, int userId);

    @Select("select * from GMSdb.User where userId=#{userId} and posId=2")
    User queryManagerById(int userId);

    @Select("select userId from GMSdb.User where username=#{username} and posId=2")
    List<Integer> queryManagerByName(String username);

    @Select("select userId from GMSdb.User where phoneNum=#{phoneNum} and posId=2")
    List<Integer> queryManagerByPhoneNum(String phoneNum);

    @Select("select userId from GMSdb.User where email=#{email} and posId=2")
    List<Integer> queryManagerByEmail(String email);

    @Select("select * from Routes where posId=#{posId}")
    List<Route> getRoutes(int posId);

    @Select("select * from Routes")
    List<Route> getAllRoutes();
}