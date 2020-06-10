package com.gms.controller;

import com.gms.entity.Charge;
import com.gms.mapper.ChargeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by PASSERFBER on 2020/6/10 17:56
 */
@RestController
public class ChargeController {
    @Autowired
    ChargeMapper chargeMapper;
    private List<Charge> chargeList;

    @GetMapping("/charge")
    public String charge(){
        String str=null;
        chargeList=chargeMapper.getAllCharge();
        str = chargeList.get(1).getIdCharge()+chargeList.get(1).getIdPlace()+chargeList.get(1).getStartCharge()+chargeList.get(1).getOverCharge()+chargeList.get(1).getLight()+chargeList.get(1).getCost();
        return str;
    }
}
