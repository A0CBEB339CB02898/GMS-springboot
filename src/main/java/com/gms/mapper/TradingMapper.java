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
    //搜索所有交易
    @Select("select tradingId from Trading")
    List<Integer> getAllTradingID();

    //搜索所有未删除的交易
    @Select("select * from Trading where isDelete=0")
    List<Trading> getAllTradingNotDelete();

    //根据订单号搜索订单
    @Select("select * from Trading where tradingId=#{tradingId}")
    Trading getTradingByID(Trading trading);

    //新增一条交易
    @Insert("insert into Trading(tradingId,userId,tradingType,tradingTime,counterParty,transactionAmount,tradingContent,isDelete) " +
            "values(#{tradingId},#{userId},#{tradingType},#{tradingTime},#{counterParty},#{transactionAmount},#{tradingContent},#{isDelete})")
    public int insertTrading(Trading trading);


    //删除一条交易
    @Update("update Trading set isDelete=1 where tradingId=#{TradingId}")
    public int deleteTrading(int trading);


//    @Update("update Employee set Name=#{Name} where FileID=#{id}")
//    public int updateTrading(Trading trading);
}
