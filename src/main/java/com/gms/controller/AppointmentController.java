package com.gms.controller;

import com.gms.entity.Appointment;
import com.gms.mapper.AppointmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by PASSERFBER on 2020/6/10 18:08
 */

@RestController
public class AppointmentController {
    @Autowired
     AppointmentMapper appointmentMapper;
     private List<Appointment> appointmentList;

     @GetMapping("/appointment")
//    public String appointment(){
//         String str=null;
//         appointmentList = appointmentMapper.getAllAppointment();
//         str = appointmentList.get(1).getIdAppointment()+appointmentList.get(1).getIdPlace()+appointmentList.get(1).getStartAppointment()+appointmentList.get(1).getOverAppointment()+appointmentList.get(1).getUserId()+appointmentList.get(1).getCharacter()+appointmentList.get(1).getIdCharge();
//         return str;
//     }
    public List<Appointment> Appointment(){
         appointmentList = appointmentMapper.getAllAppointment();
         return appointmentList;
     }

}
