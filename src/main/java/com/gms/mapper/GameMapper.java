package com.gms.mapper;

import com.gms.entity.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameMapper {
    @Select("select * from Game where IsDeleted=0 limit #{offset},10")
    List<Game> getAllGame(int offset);

    @Select("select * from GamePosition where IsDeleted=0")
    List<GamePosition> getAllGamePosition();

    @Select("select * from GameEquipment where IsDeleted=0")
    List<GameEquipment> getAllGameEquipment();

    @Select("select count(*) from Game where IsDeleted=0;")
    double countUsers();

    @Insert("insert into Game(GameId,GameName,Event,HoldingTime,Sponsor,UserId)" +
            "values(#{gameId},#{gameName},#{event},#{holdingTime},#{sponsor},#{userId})")
    public int InsertGame(Game game);

    @Update("update Game set isDeleted=1 where GameId=#{gameId}")
    public int DeleteGame(Game game);

    @Select("select * from Game where GameName=#{gameName}")
    List<Game> SearchGameByName(Game game);

    @Update("update Game set GameName = #{gameName},Event = #{event},HoldingTime = #{holdingTime},Sponsor = #{sponsor} where GameId = #{gameId}")
    public int editGame(Game game);

    @Insert("insert into GameEquipment(EquipmentId,GameId,EquipmentName) values(#{equipmentId},#{gameId},#{equipmentName})")
    public int InsertGameEquipment(GameEquipment gameEquipment);

    @Insert("insert into GamePosition(BookId,GameId,PositionName)" +
            "values(#{bookId},#{gameId},#{positionName})")
    public int InsertGamePosition(GamePosition gamePosition);

    @Update("update GameEquipment set isDeleted=0 where GameId=#{gameId}")
    //@Update("update GameEquipment set isDeleted = (select isDeleted from Game where  Game.GameId = GameEquipment.GameId)")
    public int DeleteGameEquipment(GameEquipment gameEquipment);

    @Update("update GamePosition set isDeleted=1 where GameId=#{gameId}")
    public int DeleteGamePosition(GamePosition gamePosition);
}
