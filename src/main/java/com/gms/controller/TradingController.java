package com.gms.controller;

import com.alibaba.fastjson.JSONObject;
import com.gms.entity.Trading;
import com.gms.entity.User;
import com.gms.mapper.TradingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by Orion on 2020/6/9 16:05
 */
@RestController
public class TradingController {
    @Autowired
    TradingMapper tradingMapper;

    /**
     * 新增交易
     * @param body
     * @return
     */
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
     * 删除 传入格式{"tradingId":#{tradingId}}
     * 会自动从session中获取当前操作的用户
     * @param body
     * @param session
     * @return
     */
    @PostMapping("/trading/delete")
    public JSONObject tradingDelete(@RequestBody Map body,HttpSession session){
        JSONObject response = new JSONObject();
        Trading trading = new Trading();
        User user = (User) session.getAttribute("user");

        if (isRightUser(Integer.parseInt(body.get("tradingId").toString()),user.getUserId(),user.getPosId())){
            try{
                //传入tradingId
                trading.setTradingId(Integer.parseInt(body.get("tradingId").toString()));
                tradingMapper.deleteTrading(trading.getTradingId());

                response.put("msg","删除成功");
                response.put("code",200);
            }catch (Exception e){
                response.put("msg","fail"+e);
                response.put("code",400);
            }
        }
        else {
            response.put("msg","权限不足");
            response.put("code","400");
        }

        return response;
    }


    @GetMapping("/trading/test")
     public String  tradingTest(@RequestBody Map body){
//        System.out.println(isRightUser(Integer.parseInt(body.get("tradingId").toString()),Integer.parseInt(body.get("userId").toString()),Integer.parseInt(body.get("posId").toString())));
        return "{ 'habi':'habi'}";
    }


    /**
     * 鉴权开始
     */
    /**
     * 鉴权 需要传入要鉴定的用户的userId和posId
     * @param tradingId
     * @param userId
     * @param posId
     * @return bool 是否有权限
     */
    public boolean isRightUser(int tradingId, int userId, int posId){
        boolean isRightUser=false;
        Trading trading = new Trading();
        trading.setTradingId(tradingId);

        Trading trading_result=new Trading();
        //查询传入的交易
        try{
            trading_result=tradingMapper.getTradingByID(trading);
        }catch (Exception e){
            System.out.println("鉴权失败！detail==="+e);
        }

        if (trading_result!=null){
            //鉴定权限
            if(posId==2){
                //鉴定本人
                if (trading_result.getUserId()==userId){
                    isRightUser=true;
                }
                else{
                    System.out.println("非创建者");
                    isRightUser=false;
                }
            }
            else if(posId==1){
                System.out.println("超级管理员");
                isRightUser=true;
            }
            else {
                isRightUser=false;
            }
        }
        else {
            isRightUser=false;
            System.out.println("鉴权失败！detail==="+"查询不到该交易");
        }

        return isRightUser;
    }
    /**
     * 鉴权结束
     */
}
