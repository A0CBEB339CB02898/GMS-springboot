package com.gms;

import com.gms.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    com.gms.mapper.UserMapper userMapper;
    private List<User> userList;


    @Test
    public void getAllUser() throws Exception {
//        System.out.println(userMapper.changePassword("12345","roo2", "111", "hgmkk2299@icloud.com"));
    }
}