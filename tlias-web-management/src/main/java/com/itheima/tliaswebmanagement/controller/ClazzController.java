package com.itheima.tliaswebmanagement.controller;

import com.itheima.tliaswebmanagement.anno.Log;
import com.itheima.tliaswebmanagement.pojo.Clazz;
import com.itheima.tliaswebmanagement.pojo.PageResult;
import com.itheima.tliaswebmanagement.pojo.Result;
import com.itheima.tliaswebmanagement.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@Slf4j
@RequestMapping("/clazzs")
@RestController
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    /**
     * 分页查询班级
     * @param name 班级名称
     * @param begin 范围匹配的开始时间(结课时间)
     * @param end 范围匹配的结束时间(结课时间)
     * @param page 分页页码
     * @param pageSize 每页的元素数量
     * @return
     */
    @GetMapping()
    public Result Page(String name,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate end,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize){
        log.info("分页查询");
        PageResult<Clazz> pageResult = clazzService.Page(name,begin,end,page,pageSize);
        return Result.success(pageResult);
    }


    /**
     * 根据ID删除班级
     * @param id 班级ID
     * @return
     */
    @Log
    @DeleteMapping("/{id}")
    public Result Delete(@PathVariable Integer id){
        log.info("根据ID删除班级：{}",id);
        clazzService.delete(id);
        return Result.success();
    }

    /**
     * 新增班级
     * @param clazz 新增班级类
     * @return
     */
    @Log
    @PostMapping()
    public Result Save(@RequestBody Clazz clazz){
        log.info("新增班级");
        clazzService.insert(clazz);
        return Result.success();
    }

    /**
     * 根据ID查找班级
     * @param id 班级ID
     * @return 封装好的班级类
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("根据ID查找班级：{}",id);
        Clazz clazz =clazzService.getInfo(id);
        return Result.success(clazz);
    }

    /**
     * 查找所有班级
     * @return 一个装满所有班级对象的集合
     */
    @GetMapping("/list")
    public Result findAll(){
        log.info("查找所有班级");
        List<Clazz> list = clazzService.findAll();
        return Result.success(list);
    }

    /**
     * 更新班级信息
     * @param clazz 班级类
     * @return
     */
    @Log
    @PutMapping()
    public Result update(@RequestBody Clazz clazz){
        log.info("更新班级信息：{}",clazz);
        clazzService.update(clazz);
        return Result.success();
    }
}
