package com.gms.controller;

import com.alibaba.fastjson.JSONObject;
import com.gms.entity.Equipment;
import com.gms.mapper.EquipmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.dsig.XMLObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class EquipmentController {
    @Autowired
    EquipmentMapper equipmentMapper;
    private  List<Equipment> equipments;

    @GetMapping("/equipment")
    public String equipment(){
        String str=null;
        equipments = equipmentMapper.getAllEquipment();
        str = equipments.get(1).getEquipmentName();
        System.out.println(str);
        return str;
    }

    @PostMapping("/equipment/add")
    public JSONObject equipmentAdd(@RequestBody Map body){
        JSONObject response = new JSONObject();
        Equipment equipment = new Equipment();
        final int pageSize = 10;

        if(body.get("equipmentId")==null || body.get("equipmentName")==null || body.get("equipmentCost")==null){
            response.put("msc","fail! "+" 参数缺失，请检查！");
            response.put("code",404);
            return response;
        }
        else{
            String equipmentId = (String)body.get("equipmentId");
            String equipmentName = (String)body.get("equipmentName");
            int equipmentCost = Integer.parseInt(body.get("equipmentCost").toString());
            String equipmentStatus = "free";

            try{
                equipment.setEquipmentId(equipmentId);
                equipment.setEquipmentName(equipmentName);
                equipment.setEquipmentCost(equipmentCost);
                equipment.setEquipmentStatus(equipmentStatus);

                equipmentMapper.insertEquipment(equipment);

                response.put("msg","suc");
                response.put("code",200);

            }
            catch (Exception e){
                response.put("msg",e);
                response.put("code",400);
            }
        }

        return response;
    }

    @PostMapping("/equipment/repair")
    public JSONObject equipmentRepair(@RequestBody Map body){
        JSONObject response = new JSONObject();
        Equipment equipment = new Equipment();

        if(body.get("equipmentId")==null){
            response.put("msc","fail! "+" 参数缺失，请检查！");
            response.put("code",404);
            return response;
        }
        else{
            String equipmentId = (String)body.get("equipmentId");

            try{
                equipment.setEquipmentId(equipmentId);

                equipmentMapper.repairEquipment(equipment);

                response.put("msg","suc");
                response.put("code",200);

            }
            catch (Exception e){
                response.put("msg",e);
                response.put("code",400);
            }
        }

        return response;
    }

    @PostMapping("/equipment/search")
    public JSONObject equipmentSearch(@RequestBody Map body){
        JSONObject response = new JSONObject();
        Equipment equipment = new Equipment();
        List<Equipment> equipmentList = new ArrayList<>();


        if(body.get("equipmentId")==null && body.get("equipmentName")==null && body.get("equipmentCost")==null && body.get("equipmentStatus")==null && body.get("equipmentRentId")==null){
                if(equipmentList.size()!=0){
                    response.put("message", "请求成功");
                    response.put("code", 200);
                }else {
                    response.put("message", "失败");
                    response.put("code", 404);
                }
                    equipmentList = equipmentMapper.getAllEquipment();
                    response.put("equipments", equipmentList);
            }

//此算法暂时无效
        else {
            if (body.get("equipmentId") != null) {
                String equipmentId = (String) body.get("equipmentId");
                equipment.setEquipmentId(equipmentId);
            }
            if (body.get("equipmentName") != null) {
                String equipmentName = (String) body.get("equipmentName");
                equipment.setEquipmentName(equipmentName);
            }
            if (body.get("equipmentCost") != null) {
                int equipmentCost = Integer.parseInt(body.get("equipmentCost").toString());
                equipment.setEquipmentCost(equipmentCost);
            }
            if (body.get("equipmentStatus") != null) {
                String equipmentStatus = (String) body.get("equipmentStatus");
                equipment.setEquipmentStatus(equipmentStatus);
            }
            if (body.get("equipmentRenterId") != null) {
                int equipmentRenterId = Integer.parseInt(body.get("equipmentRenterId").toString());
                equipment.setEquipmentRenterId(equipmentRenterId);
            }

            try {

                equipmentList = equipmentMapper.searchEquipment(equipment);

                response.put("msg", "suc");
                response.put("code", 200);

            } catch (Exception e) {
                response.put("msg", e);
                response.put("code", 400);
            }
            response.put("equipments", equipmentList);

        }

        return response;


    }

    @PostMapping("/equipment/delete")
    public JSONObject equipmentDelete(@RequestBody Map body){
        JSONObject response = new JSONObject();
        Equipment equipment = new Equipment();

        if(body.get("equipmentId")==null){
            response.put("msc","fail! "+" 参数缺失，请检查！");
            response.put("code",404);
            return response;
        }
        else{
            String equipmentId = (String)body.get("equipmentId");

            try{
                equipment.setEquipmentId(equipmentId);

                equipmentMapper.deleteEquipment(equipment);

                response.put("msg","suc");
                response.put("code",200);

            }
            catch (Exception e){
                response.put("msg",e);
                response.put("code",400);
            }
        }

        return response;
    }

    @PostMapping("/equipment/rent")
    public JSONObject equipmentRent(@RequestBody Map body){
        JSONObject response = new JSONObject();
        Equipment equipment = new Equipment();

        if(body.get("equipmentId")==null || body.get("equipmentRenterId")==null){
            response.put("msc","fail! "+" 参数缺失，请检查！");
            response.put("code",404);
            return response;
        }
        else{
            String equipmentId = (String)body.get("equipmentId");
            int equipmentRenterId = Integer.parseInt(body.get("equipmentRenterId").toString());
            long equipmentTime = System.currentTimeMillis();

            try{
                equipment.setEquipmentId(equipmentId);
                equipment.setEquipmentRenterId(equipmentRenterId);
                equipment.setEquipmentTime(equipmentTime);

                equipmentMapper.rentEquipment(equipment);

                response.put("msg","suc");
                response.put("code",200);

            }
            catch (Exception e){
                response.put("msg",e);
                response.put("code",400);
            }
        }

        return response;
    }

    @PostMapping("/equipment/recycle")
    public JSONObject equipmentRecycle(@RequestBody Map body){
        JSONObject response = new JSONObject();
        Equipment equipment = new Equipment();

        if(body.get("equipmentId")==null){
            response.put("msc","fail! "+" 参数缺失，请检查！");
            response.put("code",404);
            return response;
        }
        else{
            String equipmentId = (String)body.get("equipmentId");

            try{
                equipment.setEquipmentId(equipmentId);

                equipmentMapper.recycleEquipment(equipment);

                response.put("msg","suc");
                response.put("code",200);

            }
            catch (Exception e){
                response.put("msg",e);
                response.put("code",400);
            }
        }

        return response;
    }

    @GetMapping("/equipment/standard")
    public String equipmentStandard(){
        String str="This is standard";
        return str;
    }
}
