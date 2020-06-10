package com.gms.mapper;

import com.gms.entity.Field;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FieldMapper {
   @Select("select * from Field")
    List<Field> getAllField();

}
