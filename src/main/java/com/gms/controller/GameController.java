package com.gms.controller;

import com.alibaba.fastjson.JSONObject;
import com.gms.entity.*;

import com.gms.mapper.GameMapper;
import com.sun.xml.internal.bind.v2.TODO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class GameController {
    @Autowired
    public GameMapper gameMapper;
    //private List<Game> games;
    private List<GamePosition> gamePositions;
    private List<GameEquipment> gameEquipments;



    @GetMapping("/game/game")
    public JSONObject Game(int page){
        List<Game> games;
        games = gameMapper.getAllGame(page*10);
        int totalPage = (int)(Math.ceil(gameMapper.countUsers()/10));/**向上取整*/
        System.out.println("games:"+games);
        JSONObject object = new JSONObject();
        if( games.size()!=0){
            object.put("message", "请求成功");
            object.put("code", 200);
        }else{
            object.put("message", "请求失败");
            object.put("code", 404);
        }
        object.put("game",games);
        object.put("totalPage", totalPage);
        return object;
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
        str = gameEquipments.get(0).getEquipmentId()+gameEquipments.get(0).getEquipmentName();
        System.out.println(str);
        return str;

    }

    @PostMapping("/game/add")
    public JSONObject GameAdd(@RequestBody Map body) {
        JSONObject response = new JSONObject();
        Game game = new Game();
        //User user = new User();

        //try {
            //user = (User) session.getAttribute("user");
            if (body.get("gameId") == null || body.get("gameName") == null || body.get("event") == null ||
                body.get("holdingTime") == null || body.get("sponsor") == null || body.get("userId") == null) {
                response.put("msc", "fail! " + " 参数缺失，请检查！");
                response.put("code", "400");
                return response;
            } else {

                int gameId = Integer.parseInt(body.get("gameId").toString());
                String gameName = (String) body.get("gameName");
                String event = (String) body.get("event");
                int holdingTime = Integer.parseInt(body.get("holdingTime").toString());
                String sponsor = (String) body.get("sponsor");
                int userId = Integer.parseInt(body.get("userId").toString());
                //int userId = user.getUserId();

                try {
                    game.setGameId(gameId);
                    game.setGameName(gameName);
                    game.setEvent(event);
                    game.setHoldingTime(holdingTime);
                    game.setSponsor(sponsor);
                    game.setUserId(userId);

                    gameMapper.InsertGame(game);

                    response.put("msg", "suc");
                    response.put("code", 200);

                } catch (Exception e) {
                    response.put("msg", e);
                    response.put("code", 400);
                }
            }
            return response;
//        } catch (NullPointerException e) {
//            response.put("msg", "请登录");
//            response.put("code", 400);
//            return response;
//        }
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

    @PostMapping("/game/edit")
    public JSONObject equipmentChange(@RequestBody Map body){
        JSONObject response = new JSONObject();
        Game game = new Game();

        if(body.get("gameName")==null || body.get("event")==null || body.get("holdingTime")==null || body.get("sponsor")==null){
            response.put("msc","fail! "+" 参数缺失，请检查！");
            response.put("code",404);
            return response;
        }
        else{
            String gameName = (String)body.get("gameName");
            String event = (String)body.get("event");
            int holdingTime = Integer.parseInt(body.get("holdingTime").toString());
            String sponsor = (String)body.get("sponsor");
            int gameId = Integer.parseInt(body.get("gameId").toString());

            try{
                game.setGameId(gameId);
                game.setGameName(gameName);
                game.setEvent(event);
                game.setHoldingTime(holdingTime);
                game.setSponsor(sponsor);

                gameMapper.editGame(game);

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


    @GetMapping("/game/search")
    public JSONObject GameSearchByName(@RequestBody Map body){
        JSONObject response = new JSONObject();
       // System.out.println("gameName:"+gameName);
        List<Game> games;
        games = gameMapper.SearchGameByName(body.get("gameName").toString());
        //games = gameMapper.SearchGameByName();
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

    @PostMapping("/game/addEquipment")
    public JSONObject GameEquipmentAdd(@RequestBody Map body){
        JSONObject response = new JSONObject();
        GameEquipment gameEquipment = new GameEquipment();

        if(body.get("equipmentId")==null || body.get("gameId")==null || body.get("equipmentName")==null){
            response.put("msc","fail! "+" 参数缺失，请检查！");
            response.put("code","400");
            return response;
        }
        else{
            String equipmentId = (String)body.get("equipmentId");
            int gameId = Integer.parseInt(body.get("gameId").toString());
            String equipmentName = (String)body.get("equipmentName");


            try{
                gameEquipment.setEquipmentId(equipmentId);
                gameEquipment.setGameId(gameId);
                gameEquipment.setEquipmentName(equipmentName);
                gameMapper.InsertGameEquipment(gameEquipment);

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


    @PostMapping("/game/addPosition")
    public JSONObject GamePositionAdd(@RequestBody Map body){
        JSONObject response = new JSONObject();
        GamePosition gamePosition = new GamePosition();

        if(body.get("bookId")==null || body.get("gameId")==null || body.get("positionName")==null){
            response.put("msc","fail! "+" 参数缺失，请检查！");
            response.put("code","400");
            return response;
        }
        else{
            int bookId = Integer.parseInt(body.get("bookId").toString());
            int gameId = Integer.parseInt(body.get("gameId").toString());
            String positionName = (String)body.get("positionName");
            System.out.println("position:"+positionName);

            try{
                gamePosition.setBookId(bookId);
                gamePosition.setGameId(gameId);
                gamePosition.setPositionName(positionName);
                gameMapper.InsertGamePosition(gamePosition);

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

    @PostMapping("/game/deleteEquipment")
    public JSONObject GameEquipmentDelete(@RequestBody Map body){
        JSONObject response = new JSONObject();
        GameEquipment gameEquipment = new GameEquipment();

        if(body.get("gameId")==null){
            response.put("msc","fail! "+" 参数缺失，请检查！");
            response.put("code","400");
            return response;
        }
        else{
            int gameId = Integer.parseInt(body.get("gameId").toString());


            try{
                gameEquipment.setGameId(gameId);

                gameMapper.DeleteGameEquipment(gameEquipment);

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
    //
    @PostMapping("/game/deletePosition")
    public JSONObject GameDeletePosition(@RequestBody Map body){
        JSONObject response = new JSONObject();
        GamePosition gamePosition = new GamePosition();

        if(body.get("gameId")==null){
            response.put("msc","fail! "+" 参数缺失，请检查！");
            response.put("code","400");
            return response;
        }
        else{
            int gameId = Integer.parseInt(body.get("gameId").toString());


            try{
                gamePosition.setGameId(gameId);

                gameMapper.DeleteGamePosition(gamePosition);

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
}