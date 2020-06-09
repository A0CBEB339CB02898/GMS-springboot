package com.gms.controller;

import com.gms.entity.field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by PASSERFBER on 2020/6/9 21:49
 */
@RestController
public class fieldController {
    @Autowired
    com.gms.mapper.fieldMapper fieldMapper;
    List<field> fields ;
    @GetMapping("/field")
    public  List<field> fields() {
        List<field> fieldList;
        fieldList = fieldMapper.getAllField();
        return  fieldList;
    }
}
