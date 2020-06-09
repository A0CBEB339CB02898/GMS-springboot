package com.gms.mapper;

import com.gms.entity.equipment;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface equipmentMapper {
    @Select("select * from Equipment")
    List<equipment> getAllEquipment();
}
