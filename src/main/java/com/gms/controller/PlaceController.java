package com.gms.controller;

import com.gms.entity.Place;
import com.gms.mapper.PlaceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by PASSERFBER on 2020/6/10 17:46
 */
@RestController
public class PlaceController {
  @Autowired
    PlaceMapper placeMapper;
    private List<Place> placeList;

    @GetMapping("/place")
    public String Place(){
        String string = null;
        placeList = placeMapper.getAllPlace();
        string = placeList.get(1).getIdPlace()+placeList.get(1).getPlaceName() + placeList.get(1).getLocation();
        return string;
    }

}
