package com.gms.mapper;

import com.gms.entity.Place;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by PASSERFBER on 2020/6/10 17:38
 */
@Repository
public interface PlaceMapper {
    @Select("select * from Place")
    List<Place> getAllPlace();
}
