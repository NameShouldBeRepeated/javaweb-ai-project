package com.itheima.tliaswebmanagement.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.tliaswebmanagement.mapper.StudentMapper;
import com.itheima.tliaswebmanagement.pojo.PageResult;
import com.itheima.tliaswebmanagement.pojo.Student;
import com.itheima.tliaswebmanagement.pojo.StudentQueryParam;
import com.itheima.tliaswebmanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    /**
     * 分页查询学生信息
     *
     * @param studentQueryParam
     * @return
     */
    @Override
    public PageResult page(StudentQueryParam studentQueryParam) {
        PageHelper.startPage(studentQueryParam.getPage(),studentQueryParam.getPageSize());
        List<Student> dataList = studentMapper.page(studentQueryParam.getName(),studentQueryParam.getDegree(),studentQueryParam.getClazzId());
        Page<Student> page = (Page<Student>) dataList;
        return new PageResult<>(page.getTotal(),page.getResult());
    }

    /**
     * 根据学生IDs批量删除学生嬉戏
     *
     * @param ids 学生ID集合
     */
    @Override
    public void delete(List<Integer> ids) {
        studentMapper.delete(ids);
    }

    /**
     * 添加学生
     *
     * @param student 学生信息
     */
    @Override
    public void save(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.save(student);
    }

    /**
     * 根据ID查询学生信息
     *
     * @param id 学生ID
     * @return
     */
    @Override
    public Student getInfo(Integer id) {
        return studentMapper.getById(id);
    }

    /**
     * 修改学生信息
     *
     * @param student
     */
    @Override
    public void update(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.update(student);
    }

    /**
     * 处理违纪信息
     *
     * @param id    学生ID
     * @param score 学生扣除分数
     */
    @Override
    public void violationHandle(Integer id, Integer score) {
        studentMapper.violationHandle(id,score);
    }
}
