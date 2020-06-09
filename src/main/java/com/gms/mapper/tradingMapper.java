package com.gms.mapper;

import com.gms.entity.trading;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Orion on 2020/6/9 13:24
 */
@Repository
public interface tradingMapper {
    @Select("select * from Trading")
    List<trading> getAllTrading();
}
