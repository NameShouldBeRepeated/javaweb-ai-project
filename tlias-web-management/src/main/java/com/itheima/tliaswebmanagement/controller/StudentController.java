package com.itheima.tliaswebmanagement.controller;

import com.itheima.tliaswebmanagement.pojo.Result;
import com.itheima.tliaswebmanagement.pojo.Student;
import com.itheima.tliaswebmanagement.pojo.StudentQueryParam;
import com.itheima.tliaswebmanagement.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/students")

public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 分页查找学生信息
     * @param name 学生姓名
     * @param degree 学生最高学历
     * @param clazzId 学生班级ID
     * @param page 分页页码
     * @param pageSize 分页大小
     * @return
     */
    @GetMapping()
    public Result Page(@RequestParam(required = false) String name,
                       @RequestParam(required = false) Integer degree,
                       @RequestParam(required = false) Integer clazzId,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize){
        log.info("分页查询学生内容");
        StudentQueryParam studentQueryParam = new StudentQueryParam(name,degree,clazzId,page,pageSize);
        return Result.success(studentService.page(studentQueryParam));
    }

    /**
     * 根据ID批量删除学生信息
     * @param ids 学生的ID（可以一次删除多个）
     * @return
     */
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("根据ID删除学生：{}",ids);
        studentService.delete(ids);
        return Result.success();
    }

    /**
     * 添加学生
     * @param student 前端传来的student类
     * @return
     */
    @PostMapping()
    public Result save(@RequestBody Student student){
        log.info("添加学生：{}",student);
        studentService.save(student);
        return Result.success();
    }

    /**
     * 根据学生ID查询学生信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable("id") Integer id){
        Student student =  studentService.getInfo(id);
        return Result.success(student);
    }

    /**
     * 修改学生信息
     * @param student
     * @return
     */
    @PutMapping()
    public Result update(@RequestBody Student student){
        studentService.update(student);
        return Result.success();
    }

    /**
     * 处理学生违纪行为
     * @param id 学生ID
     * @param score 学生扣除分数
     * @return
     */
    @PutMapping("/violation/{id}/{score}")
    public Result violationHandle(@PathVariable Integer id, @PathVariable Integer score){
        studentService.violationHandle(id,score);
        return  Result.success();
    }
}
