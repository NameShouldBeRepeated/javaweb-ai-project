package com.itheima.tliaswebmanagement.service;

import com.itheima.tliaswebmanagement.pojo.Dept;

import java.util.List;

public interface DeptService {

    public List<Dept> findAll();

    /**
     * 根据id删除部门
     * @param id 部门id
     */
    void deleteById(Integer id);

    /**
     * 增加部门
     * @param dept 新增的部门
     */
    void add(Dept dept);

    /**
     * 根据id查询部门
     * @param id 部门id
     * @return 查询到的部门
     */
    Dept getById(Integer id);

    void update(Dept dept);
}
