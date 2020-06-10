package com.gms.mapper;

import com.gms.entity.Equipment;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EquipmentMapper {
    @Select("select * from Equipment")
    List<Equipment> getAllEquipment();
}
