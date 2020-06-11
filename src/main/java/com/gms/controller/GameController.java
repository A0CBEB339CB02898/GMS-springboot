package com.gms.controller;

import com.gms.entity.Game;

import com.gms.entity.GameEquipment;
import com.gms.entity.GamePosition;
import com.gms.mapper.GameMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GameController {
    @Autowired
    public GameMapper gameMapper;
    private List<Game> games;
    private List<GamePosition> gamePositions;
    private List<GameEquipment> gameEquipments;

    @GetMapping("/game/game")
    public String Game(){
        String str=null;
        games = gameMapper.getAllGame();
        str = games.get(0).getGameId()+games.get(0).getGameName();
        System.out.println(str);
        return str;

    }

    @GetMapping("/game/gamePosition")
    public String GamePosition(){
        String str;
        gamePositions = gameMapper.getAllGamePosition();
        str = gamePositions.get(0).getBookId()+gamePositions.get(0).getPositionName();
        System.out.println(str);
        return str;

    }

    @GetMapping("/game/gameEquipment")
    public String GameEquipment(){
        String str=null;
        gameEquipments = gameMapper.getAllGameEquipment();
        str = gameEquipments.get(0).getEquipmentId()+gameEquipments.get(0).getStatus();
        System.out.println(str);
        return str;

    }
}
