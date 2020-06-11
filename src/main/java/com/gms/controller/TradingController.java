package com.gms.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.gms.entity.Trading;
import com.gms.mapper.TradingMapper;
import com.sun.javafx.collections.MappingChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.Log;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by Orion on 2020/6/9 16:05
 */
@RestController
public class TradingController {
    @Autowired
    TradingMapper tradingMapper;

    @PostMapping("/trading/add")
    public JSONObject tradingAdd(@RequestBody Map body){
        /**
         * 新增交易事件
         */
        JSONObject response = new JSONObject();
        Trading trading = new Trading();


        //判断必要请求参数
        if(body.get("userId")==null||body.get("tradingType")==null||body.get("counterParty")==null||body.get("transactionAmount")==null){
            response.put("msc","fail! "+" 参数缺失，请检查！");
            response.put("code","400");
            return response;
        }
        else {
            //7个参数 4个必须 2个自动生成 1个delete位
            int userId = Integer.parseInt(body.get("userId").toString());
            int tradingType = Integer.parseInt(body.get("tradingType").toString());
            String counterParty =(String)body.get("counterParty");
            int transactionAmount = Integer.parseInt(body.get("transactionAmount").toString());
            int isDelete=0;
            long tradingTime = System.currentTimeMillis();
            String tradingContent = "无";

            //判断非必要参数
            if(body.get("tradingTime")!=null){
                tradingTime = Integer.parseInt(body.get("tradingTime").toString());
            }
            if(body.get("tradingContent")!=null){
                tradingContent=(String)body.get("tradingContent");
            }

            try {
                //注入数据
                trading.setUserId(userId);
                trading.setTradingType(tradingType);
                trading.setTradingTime(tradingTime);
                trading.setCounterParty(counterParty);
                trading.setTransactionAmount(transactionAmount);
                trading.setIsDelete(isDelete);
                trading.setTradingContent(tradingContent);

                tradingMapper.insertTrading(trading);

                response.put("msg","suc");
                response.put("code",200);
            }
            catch (Exception e){
                response.put("msg",e);
                response.put("code",400);
            }
        }
        return response;
    }


    /**
     * 获取所有tradingId值对比算出自增tradingId
     */
//    @GetMapping("/tradingId")
//    public List<Integer>  tradingID(){
////        JSONObject response = new JSONObject();
//        List<Integer> tradingIDs;
//        tradingIDs=tradingMapper.getAllTradingID();
//        return tradingIDs;
//    }


}
