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

    @PostMapping("/equipment/repair")
    public JSONObject equipmentRepair(@RequestBody Map body){
        JSONObject response = new JSONObject();
        Equipment equipment = new Equipment();

        if(body.get("equipmentId")==null){
            response.put("msc","fail! "+" 参数缺失，请检查！");
            response.put("code","400");
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

    @GetMapping("/equipment/search")
    public String equipmentSearch(@RequestBody Map body){
        JSONObject response = new JSONObject();
        Equipment equipment = new Equipment();

        String str = null;
        String strAll = "";
        int i;
        equipments = equipmentMapper.getAllEquipment();
        for(i = 0;i < equipments.size();i++) {
            str = "";
            str += "equipmentId:" + equipments.get(i).getEquipmentId() + ",";
            str += "equipmentName:" + equipments.get(i).getEquipmentName() + ",";
            str += "equipmentCost:" + equipments.get(i).getEquipmentCost() + ",";
            str += "equipmentStatus:" + equipments.get(i).getEquipmentStatus() + ",";
            str += "equipmentTime:" + equipments.get(i).getEquipmentTime() + ",";
            str += "equipmentRenter:" + equipments.get(i).getEquipmentRenterId() + ",";
            strAll += str + "\n";
        }
        return strAll;
//            String equipmentId = (String)body.get("equipmentId");
//            String equipmentName = (String)body.get("equipmentName");
//            int equipmentCost = Integer.parseInt(body.get("equipmentCost").toString());
//            String equipmentStatus = (String)body.get("equipmentId");
//            String equipmentTime = (String)body.get("equipmentName");
//            String equipmentRenter = (String)body.get("equipmentId");
//
//            try{
//                equipment.setEquipmentId(equipmentId);
//                equipment.setEquipmentName(equipmentName);
//                equipment.setEquipmentCost(equipmentCost);
//                equipment.setEquipmentStatus(equipmentStatus);
//                equipment.setEquipmentTime(equipmentTime);
//                equipment.setEquipmentRenter(equipmentRenter);
//
//                equipmentMapper.searchAllEquipment(equipment);
//
//                response.put("msg","suc");
//                response.put("code",200);
//
//            }
//            catch (Exception e){
//                response.put("msg",e);
//                response.put("code",400);
//            }
//
//
//        return response;


    }

    @PostMapping("/equipment/delete")
    public JSONObject equipmentDelete(@RequestBody Map body){
        JSONObject response = new JSONObject();
        Equipment equipment = new Equipment();

        if(body.get("equipmentId")==null){
            response.put("msc","fail! "+" 参数缺失，请检查！");
            response.put("code","400");
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
            response.put("code","400");
            return response;
        }
        else{
            String equipmentId = (String)body.get("equipmentId");
            int equipmentRenterId = Integer.parseInt(body.get("equipmentRenterId").toString());

            try{
                equipment.setEquipmentId(equipmentId);
                equipment.setEquipmentRenterId(equipmentRenterId);

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
            response.put("code","400");
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
