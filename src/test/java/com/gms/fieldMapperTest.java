package com.gms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class fieldMapperTest {
    @Autowired
    com.gms.mapper.fieldMapper fieldMapper;

    @Test
    public void getAllField() throws  Exception{
        fieldMapper.getAllField().forEach(System.out::println);
    }
}
