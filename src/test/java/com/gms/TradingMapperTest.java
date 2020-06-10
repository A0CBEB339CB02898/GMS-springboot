package com.gms;

import com.gms.entity.Trading;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Created by Orion on 2020/6/9 14:02
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TradingMapperTest {

    @Autowired
    com.gms.mapper.TradingMapper tradingMapper;
    private List<Trading> trading;


    @Test
    public void test() throws  Exception{
        double dou=Integer.parseInt("2.0");
        System.out.println(dou);
    }
}