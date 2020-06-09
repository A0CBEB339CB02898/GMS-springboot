package com.gms.controller;

import com.gms.entity.trading;
import com.gms.mapper.tradingMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Orion on 2020/6/9 16:05
 */
@RestController
public class tradingAPI {
    @Autowired
    tradingMapper tradingMapper;
    private  List<trading> tradings;

    @GetMapping("/trading")
    public String trading(){
        String str=null;
        tradings = tradingMapper.getAllTrading();
        str = tradings.get(1).getTradingID()+tradings.get(1).getCounterParty();
        System.out.println(str);
        return str;
    }
}
