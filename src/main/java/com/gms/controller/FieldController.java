package com.gms.controller;

import com.gms.entity.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by PASSERFBER on 2020/6/9 21:49
 */
@RestController
public class FieldController {
    @Autowired
    com.gms.mapper.FieldMapper fieldMapper;
    List<Field> Fields;
    @GetMapping("/field")
    public  List<Field> fields() {
        List<Field> fieldList;
        fieldList = fieldMapper.getAllField();
        return  fieldList;
    }
}
