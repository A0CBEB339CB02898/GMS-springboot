package com.gms.controller;

import com.gms.entity.Equipment;
import com.gms.mapper.EquipmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class equipmentAPI {
    @Autowired
    EquipmentMapper equipmentMapper;
    private  List<Equipment> equipments;

    @GetMapping("/equipment")
    public String equipment(){
        String str=null;
        equipments = equipmentMapper.getAllEquipment();
        str = equipments.get(1).getNameEquipment();
        System.out.println(str);
        return str;
    }
}
