package com.gms.controller;

import com.gms.entity.equipment;
import com.gms.mapper.equipmentMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class equipmentAPI {
    @Autowired
    equipmentMapper equipmentMapper;
    private  List<equipment> equipments;

    @GetMapping("/equipment")
    public String equipment(){
        String str=null;
        equipments = equipmentMapper.getAllEquipment();
        str = equipments.get(1).getNameEquipment();
        System.out.println(str);
        return str;
    }
}
