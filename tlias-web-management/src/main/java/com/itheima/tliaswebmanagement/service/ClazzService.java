package com.itheima.tliaswebmanagement.service;

import com.itheima.tliaswebmanagement.pojo.Clazz;
import com.itheima.tliaswebmanagement.pojo.PageResult;

import java.time.LocalDate;
import java.util.List;

public interface ClazzService {
    /**
     * 分页查找
     * @param name 班级名称
     * @param begin 范围匹配的开始时间(开课时间)
     * @param end 范围匹配的结束时间(结课时间)
     * @param page 分页页码
     * @param pageSize 每页的元素数量
     * @return
     */
    PageResult<Clazz> Page(String name, LocalDate begin, LocalDate end, Integer page, Integer pageSize);

    /**
     * 根据ID删除班级信息
     * @param id 班级ID
     */
    void delete(Integer id);

    /**
     * 新增班级
     * @param clazz 新增的班级类
     */
    void insert(Clazz clazz);

    /**
     * 根据班级ID查看班级信息
     * @param id 班级ID
     * @return 查找到的班级类
     */
    Clazz getInfo(Integer id);

    /**
     * 查找所有班级
     * @return 一个包含所有班级的集合
     */
    List<Clazz> findAll();

    /**
     * 修改班级信息
     * @param clazz 传入的班级信息
     */
    void update(Clazz clazz);
}
