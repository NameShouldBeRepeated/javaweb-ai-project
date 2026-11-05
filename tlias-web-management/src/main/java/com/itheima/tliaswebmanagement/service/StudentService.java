package com.itheima.tliaswebmanagement.service;

import com.itheima.tliaswebmanagement.pojo.PageResult;
import com.itheima.tliaswebmanagement.pojo.Student;
import com.itheima.tliaswebmanagement.pojo.StudentQueryParam;

import java.util.List;

public interface StudentService {
    /**
     * 分页查询学生信息
     * @param studentQueryParam
     * @return
     */
    PageResult page(StudentQueryParam studentQueryParam);

    /**
     * 根据学生IDs批量删除学生嬉戏
     * @param ids 学生ID集合
     */
    void delete(List<Integer> ids);

    /**
     * 添加学生
     * @param student 学生信息
     */
    void save(Student student);

    /**
     * 根据ID查询学生信息
     * @param id 学生ID
     * @return
     */
    Student getInfo(Integer id);

    /**
     * 修改学生信息
     * @param student
     */
    void update(Student student);

    /**
     * 处理违纪信息
     * @param id 学生ID
     * @param score 学生扣除分数
     */
    void violationHandle(Integer id, Integer score);
}
