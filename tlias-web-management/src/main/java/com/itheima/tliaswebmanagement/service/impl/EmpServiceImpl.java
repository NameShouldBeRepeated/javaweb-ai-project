package com.itheima.tliaswebmanagement.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.tliaswebmanagement.mapper.EmpExprMapper;
import com.itheima.tliaswebmanagement.mapper.EmpMapper;
import com.itheima.tliaswebmanagement.pojo.Emp;
import com.itheima.tliaswebmanagement.pojo.EmpExpr;
import com.itheima.tliaswebmanagement.pojo.EmpQueryParam;
import com.itheima.tliaswebmanagement.pojo.PageResult;
import com.itheima.tliaswebmanagement.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;
    //----------------------------原始分页查询------------------
//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize) {
//        //获取总记录数
//        Long count = empMapper.count();
//        //获取分页查询结果
//        Integer start = (page - 1)*pageSize;
//        List<Emp> rows = empMapper.list(start, pageSize);
//        //返回封装结果
//        return new PageResult<>(count,rows);
//
//    }

    /**
     * 根据pagehelper分页查询
     *
     * @param empQueryParam@return 封装符合条件员工集合
     */
    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        //设置分页参数
        PageHelper.startPage(empQueryParam.getPage(),empQueryParam.getPageSize());
        //查询
        List<Emp> list = empMapper.list(empQueryParam.getName(),empQueryParam.getGender(),empQueryParam.getBegin(),empQueryParam.getEnd());
        //封装结果
        //这里强转不会报错，因为Page是ArrayList的子类，而ArrayList是List子类
        Page<Emp> p =(Page<Emp>)list;
        return new PageResult<Emp>(p.getTotal(),p.getResult());
    }

    /**
     * 保存员工数据
     *
     * @param emp JSON传来的员工对象
     */
    @Override
    public void save(Emp emp) {
        //保存员工的基本信息
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
        //保存员工的工作经历
        List<EmpExpr> empExpr = emp.getExprList();
        if(!CollectionUtils.isEmpty(empExpr)){
            empExpr.forEach(item->{item.setEmpId(emp.getId());});
            empExprMapper.insertBatch(empExpr);
        }
    }
}
