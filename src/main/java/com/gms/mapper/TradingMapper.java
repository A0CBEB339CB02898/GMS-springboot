package com.gms.mapper;

import com.gms.entity.Trading;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Orion on 2020/6/9 13:24
 */
@Repository
public interface TradingMapper {
    @Select("select * from Trading")
    List<Trading> getAllTrading();
}
