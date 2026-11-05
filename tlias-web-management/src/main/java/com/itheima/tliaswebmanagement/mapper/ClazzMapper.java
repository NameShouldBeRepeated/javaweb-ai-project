package com.itheima.tliaswebmanagement.mapper;

import com.itheima.tliaswebmanagement.pojo.Clazz;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;

import java.util.List;

@Mapper
public interface ClazzMapper {


    List<Clazz> list(String name, LocalDate begin, LocalDate end);

    @Delete("delete from clazz where id = #{id}")
    void delete(Integer id);

    @Insert("insert into clazz(name, room, begin_date, end_date, master_id, subject, create_time, update_time) VALUES(#{name},#{room},#{beginDate},#{endDate},#{masterId},#{subject},#{createTime},#{updateTime}) ")
    void insert(Clazz clazz);

    @Select("select id, name, room, begin_date, end_date, master_id, subject, create_time, update_time from clazz where id = #{id}")
    Clazz getInfo(Integer id);

    @Select("select * from clazz")
    List<Clazz> findAll();

    void update(Clazz clazz);
}
