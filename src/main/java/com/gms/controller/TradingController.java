package com.gms.controller;

import com.gms.entity.Trading;
import com.gms.mapper.tradingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Orion on 2020/6/9 16:05
 */
@RestController
public class TradingController {
    @Autowired
    tradingMapper tradingMapper;
    private  List<Trading> Tradings;

    @GetMapping("/trading")
    public String trading(){
        String str=null;
        Tradings = tradingMapper.getAllTrading();
        str = Tradings.get(1).getTradingID()+ Tradings.get(1).getCounterParty();
//        System.out.println(str);
        return str;
    }
}
