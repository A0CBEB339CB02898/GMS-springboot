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

    @Select("select * from Equipment " +
            "where equipmentId = #{equipmentId}")
    List<Equipment> getAllEquipmentById(Equipment equipment);

    @Select("select * from Equipment " +
            "where equipmentName = #{equipmentName}")
    List<Equipment> getAllEquipmentByName(Equipment equipment);

    @Select("select * from Equipment " +
            "where equipmentStatus = #{equipmentStatus}")
    List<Equipment> getAllEquipmentByStatus(Equipment equipment);

    @Select("select * from Equipment " +
            "where equipmentRenterId = #{equipmentRenterId}")
    List<Equipment> getAllEquipmentByRenterId(Equipment equipment);

    @Select("select * from Equipment " +
            "where gameId != 'null'")
    List<Equipment> getAllEquipmentByGameId(Equipment equipment);

    @Insert("insert into Equipment(equipmentId,equipmentName,equipmentCost,equipmentStatus)" +
            "values(#{equipmentId},#{equipmentName},#{equipmentCost},#{equipmentStatus})")
    public int insertEquipment(Equipment equipment);

    @Update("update Equipment set equipmentStatus = 'repair' " +
            "where equipmentId = #{equipmentId}")
    public int repairEquipment(Equipment equipment);

    @Select({"<script>" +
            "select * from Equipment where 1=1" +
            "<if test='#{equipmentId} != null'>" +
            " and equipmentId = #{equipmentId}" +
            "</if>" +
            "<if test='#{equipmentName} != null'>" +
            " and equipmentName = #{equipmentName}" +
            "</if>" +
            "<if test='#{equipmentCost} != null'>" +
            " and equipmentCost = #{equipmentCost}" +
            "</if>" +
            "<if test='#{equipmentStatus} != null'>" +
            " and equipmentStatus = #{equipmentStatus}" +
            "</if>" +
            "<if test='#{equipmentRenterId} != null'>" +
            " and equipmentRenterId = #{equipmentRenterId}" +
            "</if>" +
            "</script>"})
    List<Equipment> searchEquipment(Equipment equipment);

//    @Select("select * from Equipment where 1 " +
//            "and #{str}")
//    List<Equipment> searchEquipment(String str);

    @Delete("delete from Equipment where equipmentId = #{equipmentId}")
    public int deleteEquipment(Equipment equipment);

    @Update("update Equipment set equipmentStatus = 'rent',equipmentRenterId = #{equipmentRenterId},equipmentTime = #{equipmentTime} " +
            "where equipmentId = #{equipmentId}")
    public int rentEquipment(Equipment equipment);

    @Update("update Equipment set equipmentStatus = 'free',equipmentRenterId = null,equipmentTime = null " +
            "where equipmentId = #{equipmentId}")
    public int recycleEquipment(Equipment equipment);

    @Update("update Equipment set equipmentId = #{equipmentId},equipmentName = #{equipmentName},equipmentCost = #{equipmentCost} " +
            "where equipmentId = #{equipmentIdOld}")
    public int changeEquipment(Equipment equipment);
}
