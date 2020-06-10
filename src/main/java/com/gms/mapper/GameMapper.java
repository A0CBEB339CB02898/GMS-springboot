package com.gms.mapper;

import com.gms.entity.Game;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameMapper {
    @Select("select * from Game")
    List<Game> getAllGame();
}
