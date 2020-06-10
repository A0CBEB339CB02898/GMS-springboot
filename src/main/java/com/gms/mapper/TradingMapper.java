package com.gms.mapper;

import com.gms.entity.Trading;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Orion on 2020/6/9 13:24
 */
@Repository
public interface TradingMapper {
    //搜索所有交易
    @Select("select * from Trading")
    List<Trading> getAllTrading();
    //搜索所有订单号
    @Select("select tradingID from Trading")
    List<Integer> getAllTradingID();

    //新增一条交易
    @Insert("insert into Trading(tradingId,userId,tradingType,tradingTime,counterParty,transactionAmount,tradingContent,isDelete) " +
            "values(#{tradingId},#{userId},#{tradingType},#{tradingTime},#{counterParty},#{transactionAmount},#{tradingContent},#{isDelete})")
    public int insertTrading(Trading trading);



//    @Update("update Employee set Name=#{Name} where FileID=#{id}")
//    public int updateTrading(Trading trading);
}
