package com.gms.mapper;

import com.gms.entity.Appointment;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by PASSERFBER on 2020/6/10 18:06
 */
@Repository
public interface AppointmentMapper {
    @Select("select * from Appointment")
    List<Appointment> getAllAppointment();

}
