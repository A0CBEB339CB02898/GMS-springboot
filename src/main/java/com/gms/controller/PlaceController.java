package com.gms.controller;

import com.alibaba.fastjson.JSONObject;
import com.gms.entity.Appointment;
import com.gms.entity.Charge;
import com.gms.entity.Place;
import com.gms.mapper.PlaceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by PASSERFBER on 2020/6/10 18:08
 */

@RestController
public class PlaceController {
    @Autowired
    PlaceMapper placeMapper;
    private List<Appointment> appointmentList;
    private List<Place> placeList;
    private List<Charge> chargeList;

    @PostMapping("/place/addplace")
    public JSONObject placeAdd(@RequestBody Map body) {
        JSONObject response = new JSONObject();
        Place place = new Place();

        if (body.get("placeName") == null || body.get("location") == null) {
            response.put("msc", "fail! " + " 参数缺失，请检查！");
            response.put("code", "400");
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

     @GetMapping("/place/searchplace")
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

     @PostMapping("/place/deleteplace")
    public  JSONObject placeDelete(@RequestBody Map body){
        JSONObject response = new JSONObject();
        Place place = new Place();

        if(body.get("idPlace")==null){
            response.put("msc","fail! "+" 参数缺失，请检查！");
            response.put("code","400");
            return response;
        }
        else{
            int idPlace = Integer.parseInt(body.get("idPlace").toString());
            try{
            place.setIdPlace(idPlace);
            placeMapper.updatePlace(place);

            response.put("msg","suc");
            response.put("code","200");
            }
            catch (Exception e){
                response.put("msg",e);
                response.put("code",400);

            }

        }
        return response;
     }

   @GetMapping("/place/searchcharge")
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


}

