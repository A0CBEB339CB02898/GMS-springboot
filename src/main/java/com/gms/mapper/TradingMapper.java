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

    //搜索所有未删除的交易
    @Select("select tradingId from Trading where isDelete=0")
    List<Integer> getAllTradingIdNotDelete();

    //搜索所有已删除的交易
    @Select("select tradingId from Trading where isDelete=1")
    List<Integer> getAllTradingIdDeleted();

    //根据订单号搜索交易
    @Select("select * from Trading where tradingId=#{tradingId} and isDelete=0")
    Trading getTradingByID(Trading trading);

    //根据用户ID搜索交易
    @Select("select tradingId from Trading where userId=#{userId}")
    List<Integer> getTradingIdByUserId(Trading trading);

    //根据交易类型搜索交易
    @Select("select tradingId from Trading where tradingType=#{tradingType}")
    List<Integer> getTradingIdByTradingType(Trading trading);

    @Select("select * from Trading where tradingType=#{tradingType} ")
    List<Trading>  getTradingByTradingType(int tradingType);

    //根据交易时间搜索交易
    @Select("select tradingId from Trading where tradingTime>=#{minTime} tradingTime<#{maxTime}")
    List<Integer> getTradingIdByTradingTime(int minTime,int maxTime);


    //新增一条交易
    @Insert("insert into Trading(tradingId,userId,tradingType,tradingTime,counterParty,transactionAmount,tradingContent,isDelete) " +
            "values(#{tradingId},#{userId},#{tradingType},#{tradingTime},#{counterParty},#{transactionAmount},#{tradingContent},#{isDelete})")
    public int insertTrading(Trading trading);


    //删除一条交易
    @Update("update Trading set isDelete=1 where tradingId=#{tradingId}")
    public int deleteTrading(int tradingId);

    //修改一条交易
    @Update("update Trading set userId=#{userId},tradingType=#{tradingType},counterParty=#{counterParty},transactionAmount=#{transactionAmount},tradingContent=#{tradingContent}" +
            " where tradingId=#{tradingId}")
    public int changeTrading(Trading trading);


//    @Update("update Employee set Name=#{Name} where FileID=#{id}")
//    public int updateTrading(Trading trading);
}
