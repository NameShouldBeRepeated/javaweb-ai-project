package com.itheima.tliaswebmanagement.mapper;

import com.itheima.tliaswebmanagement.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {

    /**
     * 查找所有部门，按照修改时间倒序排列
     * @return 一个包含所有部门信息的List集合
     */
    @Select("SELECT id , name, create_time, update_time FROM dept ORDER BY update_time DESC ")
    public List<Dept> findAll();

    /**
     * 根据id删除部门
     * @param id 部门id
     */
    @Delete("DELETE FROM dept WHERE id = #{id}")
    void deleteById(Integer id);

    /**
     * 新增部门
     * @param dept 新部门
     */
    @Insert("INSERT INTO dept(name,create_time,update_time) VALUES (#{name},#{createTime},#{updateTime})")
    void insert(Dept dept);

    /**
     * 根据id查询部门
     * @param id 部门id
     * @return 查询到的部门
     */
    @Select("SELECT id, name, create_time, update_time FROM dept WHERE id = #{id}")
    Dept getById(Integer id);

    /**
     * 修改部门
     * @param dept 修改的部门
     */
    @Update("UPDATE dept SET name = #{name},update_time = #{updateTime} WHERE id = #{id}")
    void update(Dept dept);
}
