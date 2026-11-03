package com.itheima.tliaswebmanagement.service;

import com.itheima.tliaswebmanagement.pojo.Emp;
import com.itheima.tliaswebmanagement.pojo.EmpQueryParam;
import com.itheima.tliaswebmanagement.pojo.PageResult;

import java.util.List;

public interface EmpService {
    /**
     * 分页查询的方法
     *
     * @param empQueryParam 前端传来的内容进行封装
     * @return
     */
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    /**
     * 保存员工数据
     * @param emp JSON传来的员工对象
     */
    void save(Emp emp);

    /**
     * 批量删除员工
     * @param ids 员工id数组
     */
    void delete(List<Integer> ids);
}
