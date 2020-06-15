package com.gms.controller;

import com.alibaba.fastjson.JSONObject;
import com.gms.entity.*;

import com.gms.mapper.GameMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/game/game/gameEquipment")
    public String GameEquipment(){
        String str=null;
        gameEquipments = gameMapper.getAllGameEquipment();
        str = gameEquipments.get(0).getEquipmentId()+gameEquipments.get(0).getEquipmentName();
        System.out.println(str);
        return str;

    }

    @PostMapping("/game/add")
    public JSONObject GameAdd(@RequestBody Map body){
        JSONObject response = new JSONObject();
        Game game = new Game();

        if(body.get("gameId")==null || body.get("gameName")==null || body.get("event")==null ||
           body.get("holdingTime")==null || body.get("sponsor")==null || body.get("userId")==null){
            response.put("msc","fail! "+" 参数缺失，请检查！");
            response.put("code","400");
            return response;
        }
        else{
            int gameId = Integer.parseInt(body.get("gameId").toString());
            String gameName = (String)body.get("gameName");
            String event = (String)body.get("event");
            int holdingTime = Integer.parseInt(body.get("holdingTime").toString());
            String sponsor = (String)body.get("sponsor");
            int userId = Integer.parseInt(body.get("userId").toString());

            try{
                game.setGameId(gameId);
                game.setGameName(gameName);
                game.setEvent(event);
                game.setHoldingTime(holdingTime);
                game.setSponsor(sponsor);
                game.setUserId(userId);

                gameMapper.InsertGame(game);

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

    @PostMapping("/game/delete")
    public JSONObject GameDelete(@RequestBody Map body){
        JSONObject response = new JSONObject();
        Game game = new Game();

        if(body.get("gameId")==null){
            response.put("msc","fail! "+" 参数缺失，请检查！");
            response.put("code","400");
            return response;
        }
        else{
            int gameId = Integer.parseInt(body.get("gameId").toString());


            try{
                game.setGameId(gameId);

                gameMapper.DeleteGame(game);

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
    //暂时无效
    @GetMapping("/game/search")
    public JSONObject GameSearchByName(String gameName){
        JSONObject response = new JSONObject();
        System.out.println("gameName:"+gameName);
        List<Game> games = new ArrayList<>();
        games = gameMapper.SearchGameByName();
        System.out.println("games:"+games);
        if(games.size()!=0){
            response.put("message", "查询成功");
            response.put("code", 200);
        } else {
            response.put("message", "未找到结果");
            response.put("code", 404);
        }
        response.put("games",games);
        return response;
    }
}
