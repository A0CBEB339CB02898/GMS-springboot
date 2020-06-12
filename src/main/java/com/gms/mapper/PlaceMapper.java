package com.gms.mapper;

import com.gms.entity.Appointment;
import com.gms.entity.Charge;
import com.gms.entity.Place;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by PASSERFBER on 2020/6/10 18:06
 */
@Repository
public interface PlaceMapper {
    @Select("select * from Appointment")
    List<Appointment> getAllAppointment();

    @Select("select * from Charge")
    List<Charge> getAllCharge();

    @Select("select idPlace,placeName,location from Place where state = '1' ")
    List<Place> getAllPlace();



    @Insert("insert into Place(placeName,location) values(#{placeName},#{location}) ON DUPLICATE KEY UPDATE placeName = #{placeName},location=#{location},state=1")
    int insertPlace(Place placeInsert);


    @Update("update Place  set state=0 where idPlace=#{idPlace} ")
     int updatePlace(Place placeUpdate);


}
