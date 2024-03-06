package com.b23010225.sastwoc.mapper;

import com.b23010225.sastwoc.pojo.Pancake;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PancakeMapper {
   @Select("select * from pancake")
    List<Pancake> view();

   @Insert("insert into pancake (title, create_time, deadline, is_done) values (#{title},now(),#{deadline},false)")
    void bake(Pancake pancake);

   @Update("update pancake set is_done = 1 where id = #{id}")
    void pan(Integer id);

   @Delete("delete from pancake where id = #{id}")
    void delete(Integer id);
}
