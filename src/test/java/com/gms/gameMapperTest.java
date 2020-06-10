package com.gms;

import com.gms.entity.Game;
import com.gms.entity.trading;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class gameMapperTest {

    @Autowired
    com.gms.mapper.GameMapper gameMapper;
    private List<Game> games;


    @Test
    public void getAllGame() throws  Exception{
        games = gameMapper.getAllGame();
        System.out.println(games.get(1).getGameId());
        System.out.println(games.get(1).getGameName());
    }
}