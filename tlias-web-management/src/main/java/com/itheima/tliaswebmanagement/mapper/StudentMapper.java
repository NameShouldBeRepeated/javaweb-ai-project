package com.itheima.tliaswebmanagement.mapper;

import com.itheima.tliaswebmanagement.pojo.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {

    List<Student> page(String name, Integer degree, Integer clazzId);

    void delete(List<Integer> ids);

    @Insert("insert into student(name, no, gender, phone, id_card, is_college, address, degree, graduation_date, clazz_id, create_time, update_time) values (#{name},#{no},#{gender},#{phone},#{idCard},#{isCollege},#{address},#{degree},#{graduationDate},#{clazzId},#{createTime},#{updateTime})")
    void save(Student student);

    @Select("select id, name, no, gender, phone, id_card, is_college, address, degree, graduation_date, clazz_id, violation_count, violation_score, create_time, update_time from student where id = #{id}")
    Student getById(Integer id);

    void update(Student student);

    @Update("update student set violation_count=violation_count+1,violation_score=violation_score+#{score},update_time =now() where id = #{id} ")
    void violationHandle(Integer id, Integer score);

    @Select("select count(*) from student where clazz_id = #{clazzId}")
    Integer countByClazzId(Integer clazzId);

    @Select("select c.name cname,count(s.id) scount from clazz c left join student s on c.id = s.clazz_id group by c.id,c.name order by count(s.id) desc")
    List<Map<String, Object>> countClazzData();

    List<Map> getStudentDegreeData();
}
