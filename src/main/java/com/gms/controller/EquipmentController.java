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

    @GetMapping("/equipment/search2")
    public JSONObject equipmentSearch2(){
        JSONObject response = new JSONObject();
//        Equipment equipment = new Equipment();
        List<Equipment> equipmentList = new ArrayList<>();
        equipmentList = equipmentMapper.getAllEquipment();
//        String str = null;
//        String strAll = "";
//        int i;
//        equipments = equipmentMapper.getAllEquipment();
//        for(i = 0;i < equipments.size();i++) {
//            str = "";
//            str += "equipmentId:" + equipments.get(i).getEquipmentId() + ",";
//            str += "equipmentName:" + equipments.get(i).getEquipmentName() + ",";
//            str += "equipmentCost:" + equipments.get(i).getEquipmentCost() + ",";
//            str += "equipmentStatus:" + equipments.get(i).getEquipmentStatus() + ",";
//            str += "equipmentTime:" + equipments.get(i).getEquipmentTime() + ",";
//            str += "equipmentRenter:" + equipments.get(i).getEquipmentRenterId() + ",";
//            strAll += str + "\n";
//        }
//        return strAll;
        if(equipmentList.size()!=0){
            response.put("message", "请求成功");
            response.put("code", 200);
        }else {
            response.put("message", "失败");
            response.put("code", 404);
        }
        response.put("equipments",equipmentList);


        return response;


    }

    @PostMapping("/equipment/search")
    public JSONObject equipmentSearch(@RequestBody Map body){
        JSONObject response = new JSONObject();
        Equipment equipment = new Equipment();
        List<Equipment> equipmentList = new ArrayList<>();
        String searchSql = "";
        int i = 0;

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
        else {
//此算法暂时无效

            if(body.get("equipmentId")!=null){
                String equipmentId = (String)body.get("equipmentId");
                equipment.setEquipmentId(equipmentId);
                i = 1;
                searchSql += "equipmentId = " + equipmentId;
            }
            if(body.get("equipmentName")!=null){
                String equipmentName = (String)body.get("equipmentName");
                equipment.setEquipmentName(equipmentName);
                if(i == 0){
                    i = 1;
                    searchSql += "equipmentName = " + equipmentName;
                }
                else{
                    searchSql += " and equipmentName = " + equipmentName;
                }
            }
            if(body.get("equipmentCost")!=null){
                int equipmentCost = Integer.parseInt(body.get("equipmentCost").toString());
                equipment.setEquipmentCost(equipmentCost);
                if(i == 0){
                    i = 1;
                    searchSql += "equipmentCost = " + equipmentCost;
                }
                else{
                    searchSql += " and equipmentCost =" + equipmentCost;
                }
            }
            if(body.get("equipmentStatus")!=null){
                String equipmentStatus = (String)body.get("equipmentStatus");
                equipment.setEquipmentStatus(equipmentStatus);
                if(i == 0){
                    i = 1;
                    searchSql += "equipmentStatus = " + equipmentStatus;
                }
                else{
                    searchSql += " and equipmentStatus = " + equipmentStatus;
                }
            }
            if(body.get("equipmentRenterId")!=null){
                int equipmentRenterId = Integer.parseInt(body.get("equipmentRenterId").toString());
                equipment.setEquipmentRenterId(equipmentRenterId);
                if(i == 0){
                    i = 1;
                    searchSql += "equipmentRenterId = " + equipmentRenterId;
                }
                else{
                    searchSql += " and equipmentRenterId = " + equipmentRenterId;
                }
            }

            try{
                System.out.println(searchSql);

                equipmentList = equipmentMapper.searchEquipment(searchSql);

                response.put("msg","suc");
                response.put("code",200);

            }
            catch (Exception e){
                response.put("msg",e);
                response.put("code",400);
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
