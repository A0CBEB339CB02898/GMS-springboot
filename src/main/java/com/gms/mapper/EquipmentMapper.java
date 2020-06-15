package com.gms.mapper;

import com.gms.entity.Equipment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EquipmentMapper {
    @Select("select * from Equipment")
    List<Equipment> getAllEquipment();

    @Insert("insert into Equipment(equipmentId,equipmentName,equipmentCost)" +
            "values(#{equipmentId},#{equipmentName},#{equipmentCost})")
    public int insertEquipment(Equipment equipment);

    @Update("update Equipment set equipmentStatus = 'repair' " +
            "where equipmentId = #{equipmentId}")
    public int repairEquipment(Equipment equipment);

    @Select("select * from Equipment where #{searchSql}")
    List<Equipment> searchEquipment(String searchSql);

    @Delete("delete from Equipment where equipmentId = #{equipmentId}")
    public int deleteEquipment(Equipment equipment);

    @Update("update Equipment set equipmentStatus = 'rent',equipmentRenterId = #{equipmentRenterId},equipmentTime = #{equipmentTime} " +
            "where equipmentId = #{equipmentId}")
    public int rentEquipment(Equipment equipment);

    @Update("update Equipment set equipmentStatus = 'free',equipmentRenterId = null " +
            "where equipmentId = #{equipmentId}")
    public int recycleEquipment(Equipment equipment);
}
