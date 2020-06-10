package com.gms.mapper;

import com.gms.entity.Charge;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by PASSERFBER on 2020/6/10 17:52
 */

@Repository
public interface ChargeMapper {
    @Select("select * from Charge")
    List<Charge> getAllCharge();

}
