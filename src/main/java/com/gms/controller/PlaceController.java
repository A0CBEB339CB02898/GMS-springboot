package com.gms.controller;

import com.gms.entity.Appointment;
import com.gms.entity.Charge;
import com.gms.entity.Place;
import com.gms.mapper.PlaceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by PASSERFBER on 2020/6/10 18:08
 */

@RestController
public class PlaceController {
    @Autowired
    PlaceMapper placeMapper;
     private List<Appointment> appointmentList;
     private List<Place>  placeList;
     private List<Charge> chargeList;

     @GetMapping("/place/appointment")
    public String appointment(){
         String str=null;
         appointmentList = placeMapper.getAllAppointment();
         str = appointmentList.get(1).getIdAppointment()+appointmentList.get(1).getIdPlace()+appointmentList.get(1).getStartAppointment()+appointmentList.get(1).getOverAppointment()+appointmentList.get(1).getUserId()+appointmentList.get(1).getCharacter()+appointmentList.get(1).getIdCharge();
         return str;
     }

    @GetMapping("/place/charge")
    public String Charge(){
        String str=null;
        chargeList=placeMapper.getAllCharge();
        str = chargeList.get(1).getIdCharge()+chargeList.get(1).getIdPlace()+chargeList.get(1).getStartCharge()+chargeList.get(1).getOverCharge()+chargeList.get(1).getLight()+chargeList.get(1).getCost();
        return str;
    }
    @GetMapping("/place/place")
    public String Place(){
        String string = null;
        placeList = placeMapper.getAllPlace();
        string = placeList.get(1).getIdPlace()+placeList.get(1).getPlaceName() + placeList.get(1).getLocation();
        return string;
    }
}
