package com.gms.mapper;

import com.gms.entity.Appointment;
import com.gms.entity.Charge;
import com.gms.entity.Place;
import org.apache.ibatis.annotations.Select;
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

    @Select("select * from Place")
    List<Place> getAllPlace();

}
