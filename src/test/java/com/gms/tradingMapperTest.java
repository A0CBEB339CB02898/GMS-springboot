package com.gms;

import com.entity.trading;
import com.gms.GmsApplication;
import com.mapper.tradingMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by Orion on 2020/6/9 14:02
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class tradingMapperTest {

    @Autowired
    com.mapper.tradingMapper tradingMapper;

    @Test
    public void getAllTrading() throws  Exception{
        tradingMapper.getAllTrading().forEach(System.out::println);
    }
}