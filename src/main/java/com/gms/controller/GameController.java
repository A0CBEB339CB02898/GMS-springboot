package com.gms.controller;

import com.gms.entity.Game;

import com.gms.mapper.gameMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GameController {
    @Autowired
    public gameMapper gameMapper;
    private List<Game> games;

    @GetMapping("/game")
    public String Game(){
        String str=null;
        games = gameMapper.getAllGame();
        str = games.get(0).getGameId()+games.get(0).getGameName();
        System.out.println(str);
        return str;
    }
}
