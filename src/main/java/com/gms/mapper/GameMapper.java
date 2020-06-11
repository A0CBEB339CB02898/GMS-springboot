package com.gms.mapper;

import com.gms.entity.Equipment;
import com.gms.entity.Game;
import com.gms.entity.GameEquipment;
import com.gms.entity.GamePosition;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameMapper {
    @Select("select * from Game")
    List<Game> getAllGame();

    @Select("select * from GamePosition")
    List<GamePosition> getAllGamePosition();

    @Select("select * from GameEquipment")
    List<GameEquipment> getAllGameEquipment();

    @Insert("insert into Game(GameId,GameName,Event,HoldingTime,Sponsor,UserId)" +
            "values(#{gameId},#{gameName},#{event},#{holdingTime},#{sponsor},#{userId})")
    public int insertGame(Game game);
}
