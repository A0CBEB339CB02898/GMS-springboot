package com.gms;

import com.gms.entity.Game;
import com.gms.entity.GameEquipment;
import com.gms.entity.GamePosition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GameMapperTest {

    @Autowired
    com.gms.mapper.GameMapper gameMapper;
    private List<Game> games;
    private List<GamePosition> gamePositions;
    private List<GameEquipment> gameEquipments;


//    @Test
//    public void getAllGame() throws  Exception{
//        games = gameMapper.getAllGame();
//        System.out.println(games.get(1).getGameId());
//        System.out.println(games.get(1).getGameName());
//    }

    @Test
    public void getAllGamePosition() throws  Exception{
        gamePositions = gameMapper.getAllGamePosition();
        System.out.println(gamePositions.get(1).getGameId());
        System.out.println(gamePositions.get(1).getBookId());
    }

    @Test
    public void getAllGameEquipment() throws  Exception{
        gameEquipments = gameMapper.getAllGameEquipment();
        System.out.println(gameEquipments.get(1).getGameId());
        System.out.println(gameEquipments.get(1).getEquipmentId());
    }
}