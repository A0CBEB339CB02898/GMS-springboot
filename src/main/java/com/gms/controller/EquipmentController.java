package com.gms.controller;

import com.alibaba.fastjson.JSONObject;
import com.gms.entity.Equipment;
import com.gms.mapper.EquipmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

        if(body.get("equipmentId")==null || body.get("equipmentName")==null || body.get("equipmentCost")==null){
            response.put("msc","fail! "+" 参数缺失，请检查！");
            response.put("code","400");
            return response;
        }
        else{
            String equipmentId = (String)body.get("equipmentId");
            String equipmentName = (String)body.get("equipmentName");
            int equipmentCost = Integer.parseInt(body.get("equipmentCost").toString());

            try{
                equipment.setEquipmentId(equipmentId);
                equipment.setEquipmentName(equipmentName);
                equipment.setEquipmentCost(equipmentCost);

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

    @GetMapping("/equipment/repair")
    public String equipmentRepair(){
        String str="This is repair";
        System.out.println(str);
        return str;
    }

    @GetMapping("/equipment/search")
    public String equipmentSearch(){
        String str="This is search";
        System.out.println(str);
        return str;
    }

    @GetMapping("/equipment/delete")
    public String equipmentDelete(){
        String str="This is delete";
        System.out.println(str);
        return str;
    }

    @GetMapping("/equipment/rent")
    public String equipmentRent(){
        String str="This is rent";
        System.out.println(str);
        return str;
    }

    @GetMapping("/equipment/recycle")
    public String equipmentRecycle(){
        String str="This is recycle";
        System.out.println(str);
        return str;
    }

    @GetMapping("/equipment/standard")
    public String equipmentStandard(){
        String str="This is standard";
        System.out.println(str);
        return str;
    }
}
