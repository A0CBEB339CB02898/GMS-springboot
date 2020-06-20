package com.gms.controller;

import com.alibaba.fastjson.JSONObject;
import com.gms.entity.Appointment;
import com.gms.entity.Charge;
import com.gms.entity.Place;
import com.gms.mapper.PlaceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by PASSERFBER on 2020/6/10 18:08
 */

@RestController
public class PlaceController {
    @Autowired
    PlaceMapper placeMapper;

    @PostMapping("/place/addPlace")
    public JSONObject placeAdd(@RequestBody Map body) {
        JSONObject response = new JSONObject();
        Place place = new Place();

        if (body.get("placeName") == ""|| body.get("location") == "") {
            System.out.println(body.get("placeName")+"场地名字");
            System.out.println(body.get("location")+"场地位置");
            response.put("msg", "fail! " + " 参数缺失，请检查！");
            response.put("code", 404);
        } else {

            String placeName = (String) body.get("placeName");
            String location = (String) body.get("location");

                try {
                    place.setPlaceName(placeName);
                    place.setLocation(location);
                    placeMapper.insertPlace(place);
                    response.put("msg", "suc");
                    response.put("code", 200);

                } catch (Exception e) {
                    response.put("msg", e);
                    response.put("code", 400);
                }

        }
        return response;
    }

     @GetMapping("/place/searchPlace")
    public  JSONObject getAllPlace(){
        List<Place> placeList;
        placeList = placeMapper.getAllPlace();
        JSONObject object = new JSONObject();
        if( placeList.size()!=0){
            object.put("message", "请求成功");
            object.put("code", 200);
        }else{
            object.put("message", "请求失败");
            object.put("code", 404);
        }
       object.put("place",placeList);
        return object;
     }

     @PostMapping("/place/deletePlace")
    public  JSONObject placeDelete(@RequestBody Map body){
        JSONObject response = new JSONObject();
          Place place = new Place();

            int idPlace = Integer.parseInt(body.get("idPlace").toString());
            place.setIdPlace(idPlace);
            System.out.println(place);

          if (idPlace > 0){
              placeMapper.updatePlace(place);
            response.put("msg","suc");
            response.put("code",200);
            }
           else{
                response.put("msg","err");
                response.put("code",400);
            }

        return response;
     }

     @PostMapping("/place/changePlace")
   public  JSONObject placeChange(@RequestBody Map body){
        JSONObject response = new JSONObject();
        Place placeChange = new Place();
        int idPlace = Integer.parseInt(body.get("idPlace").toString());
        String placeName= (String) body.get("placeName");
        String location= (String) body.get("location");
        if(idPlace >0){
            placeChange.setIdPlace(idPlace);
            placeChange.setPlaceName(placeName);
            placeChange.setLocation(location);
            placeMapper.changePlace(placeChange);
            response.put("msg","修改成功");
            response.put("code",200);
        }else{
            response.put("msg","修改失败");
            response.put("code",400);
        }
        return response;
     }

   @GetMapping("/place/searchCharge")
    public JSONObject getAllCharge(){
        List<Charge> chargeList;
        chargeList= placeMapper.getAllCharge();
        JSONObject object = new JSONObject();
        if (chargeList.size() !=0){
            object.put("message","请求成功");
            object.put("code","200");
        }else {
            object.put("message", "请求失败");
            object.put("code", 404);
        }
        object.put("charge",chargeList);
        return object;
     }

   @GetMapping("/place/searchAppointment")
    public  JSONObject getAppointment(){
        List<Appointment>appointmentList;
       appointmentList = placeMapper.getAppointment();
       System.out.println(appointmentList);
        JSONObject object = new JSONObject();
        if(appointmentList.size()!=0){
            object.put("msg","请求成功");
            object.put("code","200");

        }else{
            object.put("msg","请求失败");
            object.put("code","404");
        }
        object.put("appointment",appointmentList);

        return object;

   }

   @GetMapping("/place/searchUserAppointment")
    public JSONObject getUserAppointment(int userId ){
        List<Appointment> userAppointmentList;
        userAppointmentList = placeMapper.getUserAppointment(userId);
        JSONObject object = new JSONObject();

        if(userAppointmentList.size()!=0){
            object.put("msg","请求成功");
            object.put("code",200);
        }else{
            object.put("msg","请求失败");
            object.put("code",404);
        }
        object.put("userAppointment",userAppointmentList);
        return object;
}

   @PostMapping("/place/addAppointment")
    public JSONObject appointmentAdd(@RequestBody Map body){
        JSONObject response = new JSONObject();
        Appointment appointment = new Appointment();

        int userId = Integer.parseInt(String.valueOf(body.get("userId")));
        int  idPlace = Integer.parseInt(body.get("idPlace").toString());
        int startAppointment = Integer.parseInt( body.get("startAppointment").toString());
        int overAppointment = Integer.parseInt( body.get("overAppointment").toString()) ;
        String light= (String) body.get("light");
        String purpose = (String) body.get("purpose");
        String placeName = (String) body.get("placeName");
        String week = (String) body.get("week");
       System.out.println(userId);
       System.out.println(idPlace);
       System.out.println(week);
       System.out.println(startAppointment);
       System.out.println(overAppointment);
       System.out.println(light);
       System.out.println(purpose);
       System.out.println(placeName);
       try{
           appointment.setIdPlace(idPlace);
           appointment.setStartAppointment(startAppointment);
           appointment.setOverAppointment(overAppointment);
           appointment.setPurpose(purpose);
           appointment.setUserId(userId);
           appointment.setLight(light);
           appointment.setPlaceName(placeName);
           appointment.setWeek(week);
           placeMapper.insertAppointment(appointment);

           response.put("msg","suc");
           response.put("code",200);
       }
       catch (Exception e){
           response.put("msg",e);
           response.put("code",400);
       }
       return response;
   }

   @PostMapping("/place/deleteAppointment")
    public JSONObject deleteAppointment(@RequestBody Map body ){
        JSONObject response = new JSONObject();
        Appointment appointmentDelete=new Appointment();
        if (body.get("idAppointment")==""){
            response.put("msc","fail! "+" 参数缺失，请检查！");
            response.put("code",400);
            return response;
        }else{
            int idAppointment = Integer.parseInt(body.get("idAppointment").toString());
            try{
                appointmentDelete.setIdAppointment(idAppointment);
                placeMapper.updateAppointment(appointmentDelete);

                response.put("msg","suc");
                response.put("code",200);
            }
            catch (Exception e){
                response.put("msg",e);
                response.put("code",400);
            }
        }
               return  response;
   }

   @PostMapping("/place/changeAppointment")
    public JSONObject changeAppointment(@RequestBody Map body){
        JSONObject  response = new JSONObject();
        Appointment appointment = new Appointment();
        int idAppointment = Integer.parseInt(body.get("idAppointment").toString());
        String location= (String) body.get("location");
        String week= (String) body.get("week");
        int startAppointment = Integer.parseInt(body.get("startAppointment").toString());
        int overAppointment = Integer.parseInt(body.get("overAppointment").toString());
        String purpose = (String) body.get("purpose");
        String light = (String) body.get("light");
        String placeName= (String) body.get("placeName");


        appointment.setIdAppointment(idAppointment);
        appointment.setLocation(location);
        appointment.setPlaceName(placeName);
        appointment.setWeek(week);
        appointment.setStartAppointment(startAppointment);
        appointment.setOverAppointment(overAppointment);
        appointment.setPurpose(purpose);
        appointment.setLight(light);
        placeMapper.changeAppointment(appointment);

        response.put("msg","suc");
        response.put("code",200);



    return response;
   }
}

