package com.itheima.tliaswebmanagement.controller;

import com.itheima.tliaswebmanagement.pojo.Dept;
import com.itheima.tliaswebmanagement.pojo.Result;
import com.itheima.tliaswebmanagement.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;
    //@RequestMapping(value = "/depts",method = RequestMethod.GET)
    @GetMapping
    public Result findAll(){
        //System.out.println("查询所有部门");
        log.info("查询所有部门信息");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    /*
    /**
     * 删除部门 方式2
     * @param deptId 部门id
     * <br>
     * 一旦声明了@RequestParam，如果required是true（默认是true），那么不带这个参数（下面方法的deptId）的话，会报错
     * @return
     */
    /*
    @DeleteMapping("/depts")
    public Result delete(@RequestParam(value = "id",required = false) Integer deptId){
        System.out.println("被删除的部门id"+deptId);
        return Result.success();
    }
    */

    /**
     * 删除部门方式 3：省略@RequestParam
     * <br>
     * <br>
     * 当且仅当前端传递的请求参数与服务端的方法形参名一致时可以使用
     * @param id 部门id
     * @return
     */
    @DeleteMapping
    public Result delete(Integer id){
        //System.out.println("根据ID删除部门"+id);
        log.info("根据id删除部门 {}",id);
        deptService.deleteById(id);
        return Result.success();
    }

    /**
     * 新增部门
     * @param dept 部门名称
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Dept dept){
        //System.out.println("新增部门"+dept);
        log.info("添加部门 {}",dept);
        deptService.add(dept);
        return Result.success();
    }

    /**
     * 根据ID查询部门 方法一
     * @param deptId 部门id
     * @return
     */
    /*@GetMapping("/depts/{id}")
    public Result getInfo(@PathVariable("id") Integer deptId){
        System.out.println("根据ID查询部门"+deptId);

        return Result.success();
    }*/
    /**
     * 根据ID查询部门 方法二
     * @param id 部门id
     * @return
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        //System.out.println("根据ID查询部门"+id);
        log.info("根据id查询部门 {}",id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    /**
     * 修改部门
     * @param dept 修改后的部门信息
     * @return
     */
    @PutMapping
    public Result update(@RequestBody Dept dept){
        //System.out.println("修改部门 "+dept);
        log.info("修改部门 {}",dept);
        deptService.update(dept);
        return Result.success();
    }
}
