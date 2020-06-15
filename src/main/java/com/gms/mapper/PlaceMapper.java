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

    //查询收费标准
    @Select("select * from Charge")
    List<Charge> getAllCharge();

    //查询场地信息
    @Select("select idPlace,placeName,location from Place where isDelete = '0' ")
    List<Place> getAllPlace();

    //添加场地
    @Insert("insert into Place(placeName,location) values(#{placeName},#{location}) ON DUPLICATE KEY UPDATE placeName = #{placeName},location=#{location},idDelete=0")
    int insertPlace(Place placeAdd);

    //删除场地
    @Update("update Place  set isDelete=1 where idPlace=#{idPlace} ")
     int updatePlace(Place placeDelete);

    //查找预约
    @Select("SELECT  Place.placeName,Place.location, Appointment.startAppointment,Appointment.overAppointment,Appointment.purpose,  User.username,Charge.cost \n" +
            "FROM GMSdb.Place,GMSdb.Appointment,GMSdb.User,GMSdb.Charge \n" +
            "WHERE Appointment.idPlace = Place.idPlace and Appointment.userId= User.userId and Appointment.idCharge=Charge.idCharge and Appointment.isDelete='0';")
    List<Appointment> getAppointment();

    //增加预约
    @Insert("insert into Appointment(idPlace,startAppointment,overAppointment,purpose,userId,idCharge) values (#{idPlace},#{startAppointment},#{overAppointment},#{purpose},#{userId},#{idCharge})ON DUPLICATE KEY UPDATE idPlace=#{idPlace},startAppointment=#{startAppointment},overAppointment=#{overAppointment},userId=#{userId},idCharge=#{idCharge},isDelete=0")
    int insertAppointment(Appointment appointmentAdd);

    //按照用户名查询
    @Select("select Appointment.idAppointment,Place.placeName,Place.location,Appointment.startAppointment,Appointment.overAppointment,Appointment.purpose, User.username,Charge.cost  FROM GMSdb.Place,GMSdb.Appointment,GMSdb.User,GMSdb.Charge  WHERE Appointment.idPlace = Place.idPlace and Appointment.idCharge=Charge.idCharge and Appointment.userId=#{userId} and Appointment.userId = User.userId ")
    List<Appointment> getUserAppointment(String userId);

    //预约退订
     @Update("update Appointment set isDelete= '1' where idAppointment=#{idAppointment}")
    int updateAppointment(Appointment appointmentCancel);

     //预约修改

    @Update("update Appointment set idPlace=#{idPlace},startAppointment=#{startAppointment},overAppointment=#{overAppointment},userId=#{userId},purpose=#{purpose},idCharge=#{idCharge} where idAppointment=#{idAppointment}")
    int changeAppointment(Appointment appointmentChange);
}
