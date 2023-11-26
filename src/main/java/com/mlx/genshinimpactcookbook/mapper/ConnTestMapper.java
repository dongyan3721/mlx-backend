package com.mlx.genshinimpactcookbook.mapper;

import com.mlx.genshinimpactcookbook.domain.ConnTestClass;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ConnTestMapper {
    @Select("select * from test where id = #{id}")
    public ConnTestClass selectClassById(int id);
}
