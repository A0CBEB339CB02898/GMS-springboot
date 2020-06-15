package com.gms.mapper;

import com.gms.entity.Appointment;
import com.gms.entity.Charge;
import com.gms.entity.Place;
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

    @Select("SELECT  Place.placeName,Place.location, Appointment.startAppointment,Appointment.overAppointment,Appointment.purpose,  User.username,Charge.cost \n" +
            "FROM GMSdb.Place,GMSdb.Appointment,GMSdb.User,GMSdb.Charge \n" +
            "WHERE Appointment.idPlace = Place.idPlace and Appointment.userId= User.userId and Appointment.idCharge=Charge.idCharge;")
    List<Appointment> getAppointment();

    @Insert("insert into Place(placeName,location) values(#{placeName},#{location}) ON DUPLICATE KEY UPDATE placeName = #{placeName},location=#{location},state=1")
    int insertPlace(Place placeInsert);


    @Update("update Place  set state=0 where idPlace=#{idPlace} ")
     int updatePlace(Place placeUpdate);

    @Insert("insert into Appointment(startAppointment,overAppointment,purpose,userId) values (#{starAppointment},#{overAppointment},#{purpose},#{userId})" + "insert into Appointment(idPlace) from Place where placeName=#{placeName},location=#{location}")
    int insertAppointment(Appointment appointmentInsert);
}
