package com.mapper;

import com.entity.field;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface fieldMapper {
   @Select("select * from Field")
    List<field> getAllField();

}
